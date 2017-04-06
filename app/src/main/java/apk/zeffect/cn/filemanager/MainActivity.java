package apk.zeffect.cn.filemanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import apk.zeffect.cn.filemanager.activity.SettingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this).content(R.string.sure_to_exit_app).positiveText(R.string.sure).negativeText(R.string.cancel).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_setting:
                gotoTarget(SettingActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoTarget(Class<?> pClass) {
        Intent tempIntent = new Intent(this, pClass);
        startActivity(tempIntent);
    }
}
