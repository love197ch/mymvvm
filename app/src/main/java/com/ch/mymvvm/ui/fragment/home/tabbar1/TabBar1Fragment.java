package com.ch.mymvvm.ui.fragment.home.tabbar1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.mymvvm.BR;
import com.ch.mymvvm.R;
import com.ch.mymvvm.databinding.FragmentHomeBinding;
import com.ch.mymvvm.databinding.FragmentTabbar1Binding;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * @Author: Administrator
 * @Time: 2020/9/7 14:51
 * @Company：ch
 * @Description: 功能描述
 */
public class TabBar1Fragment extends BaseFragment<FragmentTabbar1Binding, TabBar1ViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tabbar1;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        //给RecyclerView添加Adpter，请使用自定义的Adapter继承BindingRecyclerViewAdapter，重写onBindBinding方法，里面有你要的Item对应的binding对象。
        // Adapter属于View层的东西, 不建议定义到ViewModel中绑定，以免内存泄漏
        binding.setAdapter(new BindingRecyclerViewAdapter());
    }
}