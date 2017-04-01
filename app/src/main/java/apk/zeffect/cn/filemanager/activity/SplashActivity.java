package apk.zeffect.cn.filemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import apk.zeffect.cn.filemanager.MainActivity;

/**
 * 引导页面。可以用来做广告
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

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gotoMain();
    }

    private void gotoMain() {
        Intent tempIntent = new Intent(this, MainActivity.class);
        startActivity(tempIntent);
    }
}
