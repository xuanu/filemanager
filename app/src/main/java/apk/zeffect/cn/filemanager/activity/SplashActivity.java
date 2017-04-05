package apk.zeffect.cn.filemanager.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import apk.zeffect.cn.filemanager.MainActivity;
import apk.zeffect.cn.filemanager.R;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    /***
     * 去主页面
     */
    private void gotoMain() {
        Intent tempIntent = new Intent(this, MainActivity.class);
        startActivity(tempIntent);
        SplashActivity.this.finish();
    }


    private void checkPermission() {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                gotoMain();
            }

            @Override
            public void onDenied(String permission) {
                new MaterialDialog.Builder(SplashActivity.this).content(R.string.permission_miss).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        SplashActivity.this.finish();
                    }
                }).show();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
