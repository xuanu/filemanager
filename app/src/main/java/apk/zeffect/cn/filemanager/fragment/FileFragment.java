package apk.zeffect.cn.filemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import apk.zeffect.cn.filemanager.R;
import apk.zeffect.cn.filemanager.adapter.FileAdapter;
import apk.zeffect.cn.filemanager.bean.FileBean;
import apk.zeffect.cn.filemanager.utils.FileComparator;
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
        //
        List<StorageBean> tempStorageBeen = StorageUtils.getStorageData(getContext());
        for (int i = 0; i < tempStorageBeen.size(); i++) {
            mFiles.add(new FileBean().setFile(new File(tempStorageBeen.get(i).getPath())));
        }
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
                    mFiles.clear();
                    mFiles.addAll(buildFiles(tempBean.getFile()));
                    mFileAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private List<FileBean> buildFiles(File pFile) {
        List<FileBean> retuList = new LinkedList<>();
        if (pFile != null) {
            for (File tempFile : FileUtils.getFileList(pFile.getAbsolutePath())) {
                retuList.add(new FileBean().setFile(tempFile));
            }
        }
        Collections.sort(retuList, new FileComparator.NameComparator());
        return retuList;
    }
}
