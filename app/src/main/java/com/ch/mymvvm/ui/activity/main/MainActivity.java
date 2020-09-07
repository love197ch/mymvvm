package com.ch.mymvvm.ui.activity.main;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;

import com.ch.mymvvm.BR;
import com.ch.mymvvm.R;
import com.ch.mymvvm.base.BaseMvvmActivity;
import com.ch.mymvvm.databinding.ActivityMainBinding;
import com.ch.mymvvm.ui.fragment.ConferenceFragment;
import com.ch.mymvvm.ui.fragment.home.HomeFragment;
import com.ch.mymvvm.ui.fragment.MessageFragment;
import com.ch.mymvvm.ui.fragment.MineFragment;

import java.util.List;

import androidx.lifecycle.Observer;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private ConferenceFragment conferenceFragment;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        homeFragment = new HomeFragment();
        addFragment(getSupportFragmentManager(), homeFragment.getClass(), R.id.fragment_container, null);
        viewModel.uc.refreshing.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //结束刷新
                btn_Nav_Click(viewModel.check.get());
            }
        });
    }

    public void btn_Nav_Click(int l) {
        switch (l) {
            case 1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                addFragment(getSupportFragmentManager(), homeFragment.getClass(), R.id.fragment_container, null);
                break;
            case 2:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                }
                addFragment(getSupportFragmentManager(), messageFragment.getClass(), R.id.fragment_container, null);
                break;
            case 3:
                if (conferenceFragment == null) {
                    conferenceFragment = new ConferenceFragment();
                }
                addFragment(getSupportFragmentManager(), conferenceFragment.getClass(), R.id.fragment_container, null);
                break;
            case 4:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                addFragment(getSupportFragmentManager(), mineFragment.getClass(), R.id.fragment_container, null);
                break;
        }
    }

    /**
     * Fragment的添加
     *
     * @param manager     Fragment管理器
     * @param aClass      相应的Fragment对象的getClass
     * @param containerId 容器的id
     * @param args        需要传值的话可将bundle填入  不需要传值就填null
     */
    protected void addFragment(FragmentManager manager, Class<? extends Fragment> aClass, int containerId, Bundle args) {
        String tag = aClass.getName();
        Fragment fragment = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction(); // 开启一个事务
        if (fragment == null) {// 没有添加
            try {
                fragment = aClass.newInstance(); // 通过反射 new 出一个 fragment 的实例
                transaction.add(containerId, fragment, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isAdded()) {
                if (fragment.isHidden()) {
                    transaction.show(fragment);
                }
            } else {
                transaction.add(containerId, fragment, tag);
            }
        }
        if (fragment != null) {
            fragment.setArguments(args);
            hideBeforeFragment(manager, transaction, fragment);
            transaction.commit();
        }
    }


    /**
     * 除当前 fragment 以外的所有 fragment 进行隐藏
     *
     * @param manager
     * @param transaction
     * @param currentFragment
     */
    private void hideBeforeFragment(FragmentManager manager, FragmentTransaction transaction, Fragment currentFragment) {
        List<Fragment> fragments = manager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != currentFragment && !fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
    }

    //点击返回键，返回到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
