package apk.zeffect.cn.filemanager.utils.sdcard;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 内置存储卡，外置存储卡路径工具类
 *
 * @author fanjiao
 */
public class StorageUtils {
    /**
     * 存储对象列表，（顺序为：内置存储卡，外置存储卡1号，外置存储卡2号，外置存储卡3号，）
     *
     * @param pContext 上下文
     * @return 返回存储对象列表
     */
    public static ArrayList<StorageBean> getStorageData(Context pContext) {
        final StorageManager storageManager = (StorageManager) pContext.getSystemService(Context.STORAGE_SERVICE);
        try {
            final Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");
            final Class<?> storageValumeClazz = Class.forName("android.os.storage.StorageVolume");
            final Method getPath = storageValumeClazz.getMethod("getPath");
            Method isRemovable = storageValumeClazz.getMethod("isRemovable");
            Method mGetState = null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                try {
                    mGetState = storageValumeClazz.getMethod("getState");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            final Object invokeVolumeList = getVolumeList.invoke(storageManager);
            final int length = Array.getLength(invokeVolumeList);
            ArrayList<StorageBean> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                final Object storageValume = Array.get(invokeVolumeList, i);
                final String path = (String) getPath.invoke(storageValume);
                final boolean removable = (Boolean) isRemovable.invoke(storageValume);
                String state = null;
                if (mGetState != null) {
                    state = (String) mGetState.invoke(storageValume);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        state = Environment.getStorageState(new File(path));
                    } else {
                        if (removable) {
                            state = EnvironmentCompat.getStorageState(new File(path));
                        } else {
                            state = Environment.MEDIA_MOUNTED;
                        }
                        final File externalStorageDirectory = Environment.getExternalStorageDirectory();
                    }
                }
                long totalSize = 0;
                long availaleSize = 0;
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    totalSize = StorageUtils.getTotalSize(path);
                    availaleSize = StorageUtils.getAvailableSize(path);
                }
                if (totalSize <= 0 || availaleSize <= 0) {
                    continue;
                }
                StorageBean storageBean = new StorageBean();
                storageBean.setAvailableSize(availaleSize);
                storageBean.setTotalSize(totalSize);
                storageBean.setMounted(state);
                storageBean.setPath(path);
                storageBean.setRemovable(removable);
                list.add(storageBean);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回占用的空间大小 单位byte
     *
     * @param path 文件路径
     * @return 大小值
     */
    public static long getTotalSize(String path) {
        try {
            final StatFs statFs = new StatFs(path);
            long blockSize = 0;
            long blockCountLong = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = statFs.getBlockSizeLong();
                blockCountLong = statFs.getBlockCountLong();
            } else {
                blockSize = statFs.getBlockSize();
                blockCountLong = statFs.getBlockCount();
            }
            return blockSize * blockCountLong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 返回可用空间大小 单位byte
     *
     * @param path 文件路径
     * @return 大小值
     */
    public static long getAvailableSize(String path) {
        try {
            final StatFs statFs = new StatFs(path);
            long blockSize = 0;
            long availableBlocks = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                availableBlocks = statFs.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static final long A_GB = 1073741824;
    public static final long A_MB = 1048576;
    public static final int A_KB = 1024;

    /**
     * 格式化Byte数值
     *
     * @param space Byte数值
     * @return 格式化后的字符串
     */
    public static String fmtSpace(long space) {
        if (space <= 0) {
            return "0";
        }
        double gbValue = (double) space / A_GB;
        if (gbValue >= 1) {
            return String.format("%.2fGB", gbValue);
        } else {
            double mbValue = (double) space / A_MB;
            Log.e("GB", "gbvalue=" + mbValue);
            if (mbValue >= 1) {
                return String.format("%.2fMB", mbValue);
            } else {
                final double kbValue = space / A_KB;
                return String.format("%.2fKB", kbValue);
            }
        }
    }
}
