package apk.zeffect.cn.filemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import apk.zeffect.cn.filemanager.R;
import apk.zeffect.cn.filemanager.bean.FileBean;
import apk.zeffect.cn.filemanager.utils.Utils;

/**
 * 应用分类加载
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

public class FileAdapter extends BaseAdapter {
    private List<FileBean> mFiles;
    private Context mContext;

    public FileAdapter(Context pContext, List<FileBean> pBeen) {
        this.mFiles = pBeen;
        this.mContext = pContext;
    }

    @Override
    public int getCount() {
        return mFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return mFiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder tempHolder;
        if (convertView == null) {
            tempHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_file_layout, null);
            tempHolder.nameTv = (TextView) convertView.findViewById(R.id.ifl_file_name_tv);
            tempHolder.iconImg = (ImageView) convertView.findViewById(R.id.ifl_type_img);
            convertView.setTag(tempHolder);
        } else {
            tempHolder = (ViewHolder) convertView.getTag();
        }
        tempHolder.nameTv.setText(mFiles.get(position).getShowName());
        tempHolder.iconImg.setImageResource(Utils.type2drawable(mFiles.get(position).getType()));
        return convertView;
    }

    private static class ViewHolder {
        private TextView nameTv;
        private ImageView iconImg;
    }

}
