package apk.zeffect.cn.filemanager;

import android.app.Application;

import apk.zeffect.cn.filemanager.fragment.SetttingFragment;
import apk.zeffect.cn.filemanager.orm.FileOrm;
import apk.zeffect.cn.filemanager.utils.sp.PreferencesUtils;

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

public class MyApp extends Application {

    static MyApp instance;
    /***
     * 是否显示隐藏文件
     */
    private boolean mShowHide;

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FileOrm.init(this);
        mShowHide = PreferencesUtils.getBoolean(this, SetttingFragment.SHOW_HIDE_SWITCH_KEY, false);
    }

    public boolean isShowHide() {
        return mShowHide;
    }

    public MyApp setShowHide(boolean pShowHide) {
        mShowHide = pShowHide;
        return this;
    }
}
