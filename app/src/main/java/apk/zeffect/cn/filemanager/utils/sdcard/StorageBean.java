package apk.zeffect.cn.filemanager.utils.sdcard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 存储空间对象。
 *
 * @author fanjiao
 */
public class StorageBean implements Parcelable {
    /**
     * 存储空间路根径
     */
    private String path;
    /**
     * 存储空间状态（Environment.MEDIA_BAD_REMOVAL：不正常移除，Environment.MEDIA_CHECKING：正在检测储存卡，Environment.MEDIA_MOUNTED：正常使用状态，Environment.MEDIA_MOUNTED_READ_ONLY：只读模式，Environment.MEDIA_NOFS：存储卡不可用，Environment.REMOVED：已经被移除了，Environment.MEDIA_UNMOUNT：存储卡不可用）。
     */
    private String mounted;
    /**
     * 是否已经被移除了。（true：已经移除了。false：没有被移除）
     */
    private boolean removable;
    /**
     * 总空间大小（单位：byte）
     */
    private long totalSize;
    /**
     * 可使用空间大小（单位：byte）
     */
    private long availableSize;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMounted() {
        return mounted;
    }

    public void setMounted(String mounted) {
        this.mounted = mounted;
    }

    public boolean getRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(long availableSize) {
        this.availableSize = availableSize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.mounted);
        dest.writeByte(removable ? (byte) 1 : (byte) 0);
        dest.writeLong(this.totalSize);
        dest.writeLong(this.availableSize);
    }

    public StorageBean() {
    }

    protected StorageBean(Parcel in) {
        this.path = in.readString();
        this.mounted = in.readString();
        this.removable = in.readByte() != 0;
        this.totalSize = in.readLong();
        this.availableSize = in.readLong();
    }

    public static final Creator<StorageBean> CREATOR = new Creator<StorageBean>() {
        @Override
        public StorageBean createFromParcel(Parcel source) {
            return new StorageBean(source);
        }

        @Override
        public StorageBean[] newArray(int size) {
            return new StorageBean[size];
        }
    };
}
