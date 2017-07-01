package com.mybannerlist.myBanner;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 RecyclerView 实现轮播图
 * Created by YSH on 2017/7/1.
 */

public class RecyclerBanner extends FrameLayout {

    /**
     * Banner
     */
    RecyclerView recyclerView;

    GradientDrawable defaultDrawable;
    GradientDrawable selectedDrawable;

    BannerAdapter adapter;

    private List<BannerEntity> datas = new ArrayList<>();

    public interface BannerEntity{
        String getUrl();
    }

    public RecyclerBanner(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
