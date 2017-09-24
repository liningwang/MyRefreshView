package com.wangln.myrefreshview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BestRefreshView bestRefreshView;
    TextView foot;
    TextView head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bestRefreshView = (BestRefreshView) findViewById(R.id.brv);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
//        GridView gv = (GridView) findViewById(R.id.gv);
        String[] data = getResources().getStringArray(R.array.data);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,data);
//        gv.setAdapter(arrayAdapter);
        View view =  LayoutInflater.from(this).inflate(R.layout.header_v,null);
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_v,null);
//        View footer_bg = LayoutInflater.from(this).inflate(R.layout.footer_bg,null);
//        View head_bg = LayoutInflater.from(this).inflate(R.layout.header_bg,null);
        head = (TextView) view.findViewById(R.id.tv_head);
        foot = (TextView) footer.findViewById(R.id.footer_tv1);
        bestRefreshView.setHeaderLayout(view);
        bestRefreshView.setFooterLayout(footer);
//        bestRefreshView.setHeadBackgroud(head_bg);
//        bestRefreshView.setFooterBackground(footer_bg);
        bestRefreshView.setOnRefreshLisetner(new OnRefreshListner() {
            @Override
            public void onPull(float value, int mode) {
                Log.d("wang","onPull value " + value + " mode " + mode);
                if(mode == BestRefreshView.PULL_DOWN) {
                    head.setText("下拉 " + value);
                }
                if(mode == BestRefreshView.PULL_UP) {
                    foot.setText("上拉 " + value);
                }
            }

            @Override
            public void onPullToRefresh(float value, int mode) {
                Log.d("wang","onPullToRefresh value " + value + " mode " + mode);

            }

            @Override
            public void onRefresh(int mode) {
                Log.d("wang","onRefresh mode " + mode);
                if(mode == BestRefreshView.PULL_DOWN) {
                    head.setText("刷新中...");
                }
                if(mode == BestRefreshView.PULL_UP) {
                    foot.setText("加载中...");
                }
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bestRefreshView.endRefresh();
                            }
                        });
                       }
                }.start();
            }

            @Override
            public void onRelease(float value, int mode) {
                Log.d("wang","onRelease value " + value + " mode " + mode);
                if(mode == BestRefreshView.PULL_DOWN) {
                    head.setText("刷新释放");
                }
                if(mode == BestRefreshView.PULL_UP) {
                    foot.setText("加载释放");
                }
            }
        });
    }
    public void onEnd(View view) {
        bestRefreshView.endRefresh();
    }
}
