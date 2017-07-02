package com.mybannerlist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    ConvenientBanner mBanner;

    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    MyAdapter myAdapter;

    List<String> networkImage = new ArrayList<>();
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };


    private List<String> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mData = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            mData.add("数据"+i);
        }

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBanner.startTurning(3000);
    }

    private void initView() {
        View header = LayoutInflater.from(this).inflate(R.layout.rv_header_banner, null);
        mBanner = (ConvenientBanner) header.findViewById(R.id.banner);
        //设置高度是屏幕1/4
        mBanner.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getWindowManager().getDefaultDisplay().getHeight()/3));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initBanner();

        myAdapter = new MyAdapter();
        myAdapter.addDatas(mData);

        myAdapter.setmHeaderView(mBanner);
        mRecyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initBanner(){
        networkImage = Arrays.asList(images);

        mBanner.setPages(new CBViewHolderCreator<NetWorkImageHolderView>() {
            @Override
            public NetWorkImageHolderView createHolder() {
                return new NetWorkImageHolderView();
            }
        }, networkImage)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setPageIndicator(new int[] { R.drawable.indicator_gray, R.drawable.indicator_red })
                .setOnItemClickListener(this)
                .setScrollDuration(1500);

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(MainActivity.this, "Banner:"+position, Toast.LENGTH_SHORT).show();
    }


//    public class LocalImageHolderView implements Holder<Integer>{
//
//        private ImageView imageView;
//
//        @Override
//        public View createView(Context context) {
//            imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            return imageView;
//        }
//
//        @Override
//        public void UpdateUI(Context context, int position, Integer data) {
//            imageView.setImageResource(data);
//        }
//    }

    public class NetWorkImageHolderView implements Holder<String>{

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.rv_header_img, null);
            imageView = (ImageView) view.findViewById(R.id.iv_head);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            //Glide.with(context).load(data.getImgUrl()).into(imageView);
            Log.d("imgUrl", "UpdateUI: "+data);
            Glide.with(context).load(data).placeholder(R.mipmap.ic_launcher_round).into(imageView);
        }
    }


}
