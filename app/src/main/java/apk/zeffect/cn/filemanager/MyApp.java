package apk.zeffect.cn.filemanager;

import android.app.Application;

import apk.zeffect.cn.filemanager.orm.FileOrm;

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
    @Override
    public void onCreate() {
        super.onCreate();
        FileOrm.init(this);
    }
}
