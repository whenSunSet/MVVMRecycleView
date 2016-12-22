package com.example.administrator.mvvmrecycleview;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.mvvmrecycleview.recycleViewUtil.ItemViewArg;

public class MainActivityViewModel {
    public MainActivity baseActivity;
    public ItemViewArg.ItemViewSelector<MainActivityItemViewModel> mViewSelector;
    public final ObservableList<MainActivityItemViewModel> dataItems= new ObservableArrayList<>();
    public String positionStr;

    public MainActivityViewModel(MainActivity b) {
        baseActivity=b;
        mViewSelector=new ItemViewArg.ItemViewSelector<MainActivityItemViewModel>() {
            @Override
            public void select(ItemViewArg.ItemView itemView, int position, MainActivityItemViewModel item) {
                itemView.set(com.example.administrator.mvvmrecycleview.BR.viewModel,item.type==1?R.layout.item_one:R.layout.item_two);
            }

            @Override
            public int viewTypeCount() {
                return 2;
            }
        };
        for (int i = 0; i < 20; i++) {
            if (i%2==1){
                dataItems.add(new MainActivityItemViewModel(b,1,String .valueOf(i)));
            }else {
                dataItems.add(new MainActivityItemViewModel(b,2,String .valueOf(i)));
            }
        }
    }

    public void add(){
        if (TextUtils.isEmpty(positionStr)||dataItems.size()<Integer.parseInt(positionStr))return;
        dataItems.add(Integer.parseInt(positionStr),new MainActivityItemViewModel(baseActivity,1,"增加"));
    }

    public void delete(){
        if (TextUtils.isEmpty(positionStr)||dataItems.size()<=Integer.parseInt(positionStr))return;
        dataItems.remove(Integer.parseInt(positionStr));
    }

    public void change(){
        if (TextUtils.isEmpty(positionStr)||dataItems.size()<=Integer.parseInt(positionStr))return;
        MainActivityItemViewModel mainActivityItemViewModel = dataItems.get(Integer.parseInt(positionStr));
        mainActivityItemViewModel.text.set("修改");
    }

    public static class MainActivityItemViewModel{
        public MainActivity mBaseActivity;
        public int type;
        public final ObservableField<String> text=new ObservableField<>();

        public MainActivityItemViewModel(MainActivity baseActivity, int type, String text) {
            mBaseActivity = baseActivity;
            this.type = type;
            this.text.set(text);
        }

        public View.OnClickListener click=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mBaseActivity, ("你点击了"+text.get()), Toast.LENGTH_SHORT).show();
                }
            };
    }

}