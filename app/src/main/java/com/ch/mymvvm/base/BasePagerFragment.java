package com.ch.mymvvm.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.ch.mymvvm.BR;
import com.ch.mymvvm.R;
import com.ch.mymvvm.databinding.FragmentHomeBinding;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by goldze on 2017/7/17.
 * 抽取的二级BasePagerFragment
 */

public abstract class BasePagerFragment extends BaseFragment<FragmentHomeBinding, BaseViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    protected abstract List<Fragment> pagerFragment();

    protected abstract List<String> pagerTitleString();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mFragments = pagerFragment();
        titlePager = pagerTitleString();
        //设置Adapter
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), mFragments, titlePager);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }

    @Override
    public void initViewObservable() {
        BarUtils.setStatusBarColor(getActivity(), Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(getActivity(), true);
    }
}
