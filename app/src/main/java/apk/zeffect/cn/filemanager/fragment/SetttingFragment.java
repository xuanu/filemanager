package apk.zeffect.cn.filemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import apk.zeffect.cn.filemanager.MyApp;
import apk.zeffect.cn.filemanager.R;
import apk.zeffect.cn.filemanager.utils.sp.PreferencesUtils;

/**
 * 设置界面，暂时只有一个设置
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/04/06
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class SetttingFragment extends Fragment {
    public static final String SHOW_HIDE_SWITCH_KEY = "show_hide_switch_key";
    private Switch mHideSwi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View tempView = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(tempView);
        return tempView;
    }

    private void initView(View parent) {
        mHideSwi = (Switch) parent.findViewById(R.id.fs_switch_hide_swi);
        boolean showHide = PreferencesUtils.getBoolean(getContext(), SHOW_HIDE_SWITCH_KEY, false);
        MyApp.getInstance().setShowHide(showHide);
        mHideSwi.setChecked(showHide);
        mHideSwi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferencesUtils.putBoolean(getContext(), SHOW_HIDE_SWITCH_KEY, isChecked);
                MyApp.getInstance().setShowHide(isChecked);
            }
        });
    }
}
