package com.llw.slidelayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.llw.slidelayoutdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();//初始化视图

        showImg();//显示网络图片
    }


    /**
     * 初始化视图
     */
    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);//绑定视图

        StatusBarUtils.setImmersionStateMode(this);//透明状态栏

        //滑动偏移监听事件
        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int toolbarHeight = appBarLayout.getTotalScrollRange();
                int dy = Math.abs(verticalOffset);
                if (dy <= toolbarHeight) {
                    float scale = (float) dy / toolbarHeight;
                    float alpha = scale * 255;
                    binding.flLayout.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));//渐变背景透明度
                    binding.tvTitle.setTextColor(Color.argb((int) alpha,0,0,0));//渐变文字颜色透明度
                }
            }
        });

        //设置点击置顶的ImageView
        binding.goTopScrollview.setImageViewOnClickGoToFirst(binding.ivReturnTop);

        //ScrollView滑动超过屏幕高度则显示置顶按钮,不设置的话就会使用自定义View中的默认高度
        DisplayMetrics metric = new DisplayMetrics();//获取屏幕高度
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        binding.goTopScrollview.setScreenHeight(metric.heightPixels);//设置高度
    }

    /**
     * 使用Glide加载显示网络图片 记得加网络权限和http地址url访问许可
     */
    private void showImg() {
        Glide.with(this)
                .load("http://gank.io/images/2c924db2a1b84c5d8fdb9f8c5f6d1b71")
                .into(binding.ivOne);
        Glide.with(this)
                .load("http://gank.io/images/92989b6a707b44dfb1c734e8d53d39a2")
                .into(binding.ivTwo);
        Glide.with(this)
                .load("http://gank.io/images/4817628a6762410895f814079a6690a1")
                .into(binding.ivThree);
        Glide.with(this)
                .load("http://gank.io/images/f9523ebe24a34edfaedf2dd0df8e2b99")
                .into(binding.ivFour);
        Glide.with(this)
                .load("http://gank.io/images/4002b1fd18544802b80193fad27eaa62")
                .into(binding.ivFive);
    }


}
