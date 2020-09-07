package com.ch.mymvvm.ui.fragment.home.tabbar1;

import android.app.Application;

import com.ch.mymvvm.BR;
import com.ch.mymvvm.R;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.MultiItemViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @Author: Administrator
 * @Time: 2020/9/7 15:14
 * @Company：ch
 * @Description: 功能描述
 */
public class TabBar1ViewModel extends BaseViewModel {
    private static final String MultiRecycleType_Head = "head";
    private static final String MultiRecycleType_Main = "main";

    public TabBar1ViewModel(@NonNull Application application) {
        super(application);
        //模拟10个条目，数据源可以来自网络
        for (int i = 0; i < 20; i++) {
            String text = "第" + i + "条";
//            if (i == 0) {
//                MultiItemViewModel item = new TabBar1RecycleHeadViewModel(this);
//                //条目类型为头布局
//                item.multiItemType(MultiRecycleType_Head);
//                observableList.add(item);
//            } else {
            MultiItemViewModel item = new TabBar1RecycleMainItemViewModel(this, text);
            //条目类型为右布局
            item.multiItemType(MultiRecycleType_Main);
            observableList.add(item);
            //}
        }

    }

    //给RecyclerView添加ObservableList
    public ObservableList<MultiItemViewModel> observableList = new ObservableArrayList<>();
    //RecyclerView多布局添加ItemBinding
    public ItemBinding<MultiItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<MultiItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MultiItemViewModel item) {
            //通过item的类型, 动态设置Item加载的布局
            String itemType = (String) item.getItemType();
            if (MultiRecycleType_Head.equals(itemType)) {
                //设置头布局
                itemBinding.set(BR.viewModel, R.layout.item_tabbar1_head);
            } else if (MultiRecycleType_Main.equals(itemType)) {
                //设置主布局
                itemBinding.set(BR.viewModel, R.layout.item_tabbar1_main);
            }
        }
    });
}
