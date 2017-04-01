package apk.zeffect.cn.filemanager.utils.file;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件操作相关
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/04/01
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 *         // TODO 用@see描述一下当前类的方法及简单解释
 */

public class FileUtils {
    /***
     * 返回指定目录下的文件
     * @param strPath 目录
     * @return 当前文件夹下的文件
     */
    public static List<File> getFileList(String strPath) {
        List<File> retuList = new LinkedList<>();
        File dir = new File(strPath);
        if (dir.exists()) {
            retuList.addAll(Arrays.asList(dir.listFiles()));
        }
        return retuList;
    }
}
