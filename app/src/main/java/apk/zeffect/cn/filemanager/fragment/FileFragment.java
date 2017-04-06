package apk.zeffect.cn.filemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import apk.zeffect.cn.filemanager.MyApp;
import apk.zeffect.cn.filemanager.R;
import apk.zeffect.cn.filemanager.adapter.FileAdapter;
import apk.zeffect.cn.filemanager.bean.FileBean;
import apk.zeffect.cn.filemanager.utils.OpenFiles;
import apk.zeffect.cn.filemanager.utils.comparator.FileComparator;
import apk.zeffect.cn.filemanager.utils.file.FileUtils;
import apk.zeffect.cn.filemanager.utils.sdcard.StorageBean;
import apk.zeffect.cn.filemanager.utils.sdcard.StorageUtils;

/**
 * 显示文件目录页面
 * <p>
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/04/05
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class FileFragment extends Fragment {
    private View mRootView;
    private ListView mListView;
    private FileAdapter mFileAdapter;
    private List<FileBean> mFiles = new LinkedList<>();
    private String mRootDirs = "";
    private SwipeRefreshLayout mRefreshLayout;
    /**
     * 当前目录
     */
    private File mNowFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_file, container, false);
            initView(mRootView);
        }
        return mRootView;
    }

    private void initView(View parentView) {
        mRefreshLayout = (SwipeRefreshLayout) parentView.findViewById(R.id.ff_refresh_srl);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mNowFile != null) {
                    refreshFiles(mNowFile);
                }
                mRefreshLayout.setRefreshing(false);
            }
        });
        //
        List<StorageBean> tempStorageBeen = StorageUtils.getStorageData(getContext());
        StringBuilder tempDirs = new StringBuilder();
        for (int i = 0; i < tempStorageBeen.size(); i++) {
            StorageBean tempBean = tempStorageBeen.get(i);
            if (!tempBean.getRemovable() && tempBean.getMounted().equals(Environment.MEDIA_MOUNTED)) {
                tempDirs.append(tempBean.getPath());
                mFiles.add(buildFile(tempBean.getPath()));
            }
        }
        mRootDirs = tempDirs.toString();
        //
        mListView = (ListView) parentView.findViewById(R.id.ff_file_dirs_lv);
        mFileAdapter = new FileAdapter(getContext(), mFiles);
        mListView.setAdapter(mFileAdapter);
        //
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileBean tempBean = (FileBean) parent.getAdapter().getItem(position);
                if (tempBean.getFile().isDirectory()) {
                    refreshFiles(tempBean.getFile());
                } else if (tempBean.getFile().isFile()) {
                    startActivity(OpenFiles.openFile(tempBean.getFile().getAbsolutePath()));
                }
            }
        });
    }

    private void refreshFiles(File pFile) {
        mFiles.clear();
        if (pFile != null && !mRootDirs.contains(pFile.getAbsolutePath())) {//如果是顶级目录就不增加
            mFiles.add(buildFile(pFile.getParentFile(), "..."));
        }
        mNowFile = pFile;
        mFiles.addAll(buildFiles(pFile));
        mFileAdapter.notifyDataSetChanged();
    }

    private List<FileBean> buildFiles(File pFile) {
        List<FileBean> retuList = new LinkedList<>();
        if (pFile != null) {
            for (File tempFile : FileUtils.getFileList(pFile.getAbsolutePath())) {
                if (!MyApp.getInstance().isShowHide() && tempFile.isHidden()) {
                    continue;
                }
                retuList.add(buildFile(tempFile));
            }
        }
        Collections.sort(retuList, new FileComparator.NameComparator());
        return retuList;
    }

    private FileBean buildFile(File tempFile) {
        return buildFile(tempFile, "");
    }

    private FileBean buildFile(String tempFile) {
        return buildFile(new File(tempFile), "");
    }


    private FileBean buildFile(File tempFile, String name) {
        // TODO 文件类型先用最后一个.的位置后的东西，没有考虑小数点在前的情况
        String type = (tempFile.isDirectory() ? "dir" : tempFile.getName().substring(tempFile.getName().lastIndexOf(".") + 1)).toLowerCase();
        FileBean tempFileBean = new FileBean().setFile(tempFile);
        tempFileBean.setLastModified(tempFile.lastModified())
                .setPath(tempFile.getAbsolutePath())
                .setType(type)
                .setShowName(TextUtils.isEmpty(name) ? tempFile.getName() : name);
        return tempFileBean;
    }
}
