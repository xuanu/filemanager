package apk.zeffect.cn.filemanager.utils;

import apk.zeffect.cn.filemanager.R;

/**
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/04/06
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class Utils {
    /***
     * 把文件类型type转成对应的资源文件
     * @param type 文件类型
     * @return
     */
    public static int type2drawable(String type) {
        int retuDrawable = R.drawable.vector_drawable_ic_unknow;
        if (type.equals("apk")) {
            retuDrawable = R.drawable.vector_drawable_ic_app;
        } else if (type.equals("doc") || type.equals("docx")) {
            retuDrawable = R.drawable.vector_drawable_ic_doc;
        } else if (type.equals("eps")) {
            retuDrawable = R.drawable.vector_drawable_ic_eps;
        } else if (type.equals("jpg") || type.equals("png") || type.equals("gif") || type.equals("jpeg") || type.equals("bmp")) {
            retuDrawable = R.drawable.vector_drawable_ic_jpg;
        } else if (type.equals("mp3") || type.equals("wav") || type.equals("ogg") || type.equals("midi")) {
            retuDrawable = R.drawable.vector_drawable_ic_mp3;
        } else if (type.equals("mp4")) {
            retuDrawable = R.drawable.vector_drawable_ic_mp4;
        } else if (type.equals("pdf")) {
            retuDrawable = R.drawable.vector_drawable_ic_pdf;
        } else if (type.equals("ppt") || type.equals("pptx")) {
            retuDrawable = R.drawable.vector_drawable_ic_ppt;
        } else if (type.equals("psd")) {
            retuDrawable = R.drawable.vector_drawable_ic_psd;
        } else if (type.equals("txt") || type.equals("java") || type.equals("c") || type.equals("cpp") || type.equals("py") || type.equals("xml") || type.equals("json") || type.equals("log")) {
            retuDrawable = R.drawable.vector_drawable_ic_txt;
        } else if (type.equals("xls") || type.equals("xlsx")) {
            retuDrawable = R.drawable.vector_drawable_ic_xls;
        } else if (type.equals("zip") || type.equals("jar") || type.equals("rar") || type.equals("gz") || type.equals("img")) {
            retuDrawable = R.drawable.vector_drawable_ic_zip;
        } else if (type.equals("dir")) {
            retuDrawable = R.drawable.vector_drawable_ic_dir;
        }
        return retuDrawable;
    }
}
