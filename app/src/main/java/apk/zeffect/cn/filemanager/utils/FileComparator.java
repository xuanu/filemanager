package apk.zeffect.cn.filemanager.utils;

import java.util.Comparator;

import apk.zeffect.cn.filemanager.bean.FileBean;

/**
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

public interface FileComparator {
    /**
     * 按文件名排序
     */
    class NameComparator implements Comparator<FileBean> {

        @Override
        public int compare(FileBean o1, FileBean o2) {
            if (o1.getFile().isDirectory() && o2.getFile().isFile())
                return -1;
            if (o1.getFile().isFile() && o2.getFile().isDirectory())
                return 1;
            return o1.getFile().getName().compareTo(o2.getFile().getName());
        }
    }
}
