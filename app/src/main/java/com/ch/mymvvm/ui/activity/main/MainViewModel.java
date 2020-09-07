package com.ch.mymvvm.ui.activity.main;

import android.app.Application;

import com.ch.mymvvm.R;
import com.ch.mymvvm.ui.fragment.ConferenceFragment;
import com.ch.mymvvm.ui.fragment.home.HomeFragment;
import com.ch.mymvvm.ui.fragment.MessageFragment;
import com.ch.mymvvm.ui.fragment.MineFragment;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * @Author: Administrator
 * @Time: 2020/9/3 17:42
 * @Company：ch
 * @Description: 功能描述
 */
public class MainViewModel extends BaseViewModel {

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private ConferenceFragment conferenceFragment;

    public ObservableInt check = new ObservableInt(1);
    public UIChangeObservable uc = new UIChangeObservable();
    public ObservableInt color = new ObservableInt(getTextColor());
    public ObservableInt color1 = new ObservableInt(getTextColor1());
    public ObservableInt color2 = new ObservableInt(getTextColor2());
    public ObservableInt color3 = new ObservableInt(getTextColor3());
    public ObservableInt icon = new ObservableInt(getStatusIcon());
    public ObservableInt icon1 = new ObservableInt(getStatusIcon1());
    public ObservableInt icon2 = new ObservableInt(getStatusIcon2());
    public ObservableInt icon3 = new ObservableInt(getStatusIcon3());

    public class UIChangeObservable {
        //点击事件完成
        public SingleLiveEvent refreshing = new SingleLiveEvent<>();
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public int getStatusIcon() {
        switch (check.get()) {
            case 1:
                return R.drawable.ic_main_dzyh;
        }
        return R.drawable.ic_main_dzy;
    }

    public int getStatusIcon1() {
        switch (check.get()) {
            case 2:
                return R.drawable.ic_main_dhysh;
        }
        return R.drawable.ic_main_dhys;
    }

    public int getStatusIcon2() {
        switch (check.get()) {
            case 3:
                return R.drawable.ic_main_dszh;
        }
        return R.drawable.ic_main_dsz;
    }

    public int getStatusIcon3() {
        switch (check.get()) {
            case 4:
                return R.drawable.ic_main_dtxlh;
        }
        return R.drawable.ic_main_dtxl;
    }

    public int getTextColor() {
        if (check.get() == 1) {
            return R.color.main_menu_text_color_over;
        } else {
            return R.color.main_menu_text_color;
        }
    }

    public int getTextColor1() {
        if (check.get() == 2) {
            return R.color.main_menu_text_color_over;
        } else {
            return R.color.main_menu_text_color;
        }
    }

    public int getTextColor2() {
        if (check.get() == 3) {
            return R.color.main_menu_text_color_over;
        } else {
            return R.color.main_menu_text_color;
        }
    }

    public int getTextColor3() {
        if (check.get() == 4) {
            return R.color.main_menu_text_color_over;
        } else {
            return R.color.main_menu_text_color;
        }
    }

    public BindingCommand lHomeOnClickCommand = new BindingCommand(() -> {
        check.set(1);
        change();
        uc.refreshing.call();
    });

    public BindingCommand lMessageOnClickCommand = new BindingCommand(() -> {
        check.set(2);
        change();
        uc.refreshing.call();
    });

    public BindingCommand lConferenceOnClickCommand = new BindingCommand(() -> {
        check.set(3);
        change();
        uc.refreshing.call();
    });

    public BindingCommand lMineOnClickCommand = new BindingCommand(() -> {
        check.set(4);
        change();
        uc.refreshing.call();
    });

    private void change() {
        color.set(getTextColor());
        color1.set(getTextColor1());
        color2.set(getTextColor2());
        color3.set(getTextColor3());
        icon.set(getStatusIcon());
        icon1.set(getStatusIcon1());
        icon2.set(getStatusIcon2());
        icon3.set(getStatusIcon3());
    }

}
