package com.ch.mymvvm.ui.fragment.home.tabbar1;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import me.goldze.mvvmhabit.base.MultiItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：
 */

public class TabBar1RecycleMainItemViewModel extends MultiItemViewModel<TabBar1ViewModel> {
    public ObservableField<String> text = new ObservableField<>("");

    public TabBar1RecycleMainItemViewModel(@NonNull TabBar1ViewModel viewModel, String text) {
        super(viewModel);
        this.text.set(text);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //拿到position
            int position = viewModel.observableList.indexOf(TabBar1RecycleMainItemViewModel.this);
            ToastUtils.showShort("position：" + position);
        }
    });
}
