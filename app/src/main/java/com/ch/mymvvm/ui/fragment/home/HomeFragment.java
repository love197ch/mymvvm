package com.ch.mymvvm.ui.fragment.home;

import android.graphics.Color;

import com.blankj.utilcode.util.BarUtils;
import com.ch.mymvvm.base.BasePagerFragment;
import com.ch.mymvvm.ui.fragment.home.tabbar1.TabBar1Fragment;
import com.ch.mymvvm.ui.fragment.home.tabbar2.TabBar2Fragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * @Author: Administrator
 * @Time: 2019/7/30 15:18
 * @Company：ch
 * @Description: 首页
 */
public class HomeFragment extends BasePagerFragment {

    @Override
    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new TabBar1Fragment());
        list.add(new TabBar2Fragment());
        return list;
    }

    @Override
    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("page1");
        list.add("page2");
        return list;
    }

}
