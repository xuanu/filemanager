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
 * 1. 确定一个文件名是否相同用文件路径+时间
 *
 * @author zzx
 */

public class FileBean {
    private int id;
    private File file;
    /***
     * 文件类型，根据文件后缀来区分
     */
    private String type;
    /***
     * 文件路径，因为File字段不会存到数据库
     */
    private String path;
    /***
     * 最后修改时间
     */
    private long lastModified;

    private String showName;

    public String getShowName() {
        return showName;
    }

    public FileBean setShowName(String pShowName) {
        showName = pShowName;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileBean setPath(String pPath) {
        path = pPath;
        return this;
    }

    public long getLastModified() {
        return lastModified;
    }

    public FileBean setLastModified(long pLastModified) {
        lastModified = pLastModified;
        return this;
    }

    public String getType() {
        return type;
    }

    public FileBean setType(String pType) {
        type = pType;
        return this;
    }

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
