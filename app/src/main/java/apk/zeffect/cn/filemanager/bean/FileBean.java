package apk.zeffect.cn.filemanager.bean;

import java.io.File;

/**
 * 文件的JAVABEAN，保存一些其它的信息
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

public class FileBean {
    private int id;
    private File file;

    public int getId() {
        return id;
    }

    public FileBean setId(int pId) {
        id = pId;
        return this;
    }

    public File getFile() {
        return file;
    }

    public FileBean setFile(File pFile) {
        file = pFile;
        return this;
    }
}
