package com.example.administrator.mvvmrecycleview;

import android.databinding.DataBindingUtil;
import android.databinding.adapters.TextViewBindingAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private com.example.administrator.mvvmrecycleview.ActivityMainBinding mActivityMainBinding;
    private MainActivityViewModel mMainActivityViewModel;
    private MainActivityUIViewModel mMainActivityUIViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainActivityViewModel = new MainActivityViewModel(this);
        mMainActivityUIViewModel = new MainActivityUIViewModel(this);
        mActivityMainBinding.setViewModel(mMainActivityViewModel);
        mActivityMainBinding.setUiViewModel(mMainActivityUIViewModel);

    }

    public static class MainActivityUIViewModel {
        private MainActivity mMainActivity;
        private MainActivityViewModel mMainActivityViewModel;
        public MainActivityUIViewModel(MainActivity mMainActivity) {
            this.mMainActivity = mMainActivity;
            this.mMainActivityViewModel=mMainActivity.mMainActivityViewModel;
        }

        public TextViewBindingAdapter.OnTextChanged position=new TextViewBindingAdapter.OnTextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))mMainActivityViewModel.positionStr=s.toString();
            }
        };

        public View.OnClickListener add=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.add();
            }
        };

        public View.OnClickListener delete=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.delete();
            }
        };

        public View.OnClickListener change=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.change();
            }
        };
    }
}
