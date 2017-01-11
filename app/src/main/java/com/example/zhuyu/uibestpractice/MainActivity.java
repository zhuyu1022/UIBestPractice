package com.example.zhuyu.uibestpractice;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLayoutChangeListener {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button sendBtn;
    private List<Msg> msglist = new ArrayList<>();
    private int type = -1;
    MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editText = (EditText) findViewById(R.id.editText);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(this);
        initlist();
        adapter = new MsgAdapter(msglist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnLayoutChangeListener(this);
       recyclerView.requestFocus();//为防止edittext获取用户焦点，一打开界面就谈起软键盘的现象

    }

    /**
     * 获取键盘高度，这里我们设置这个值为屏幕高度的四分之一
     */
    private int getkeyboardhigh() {
        Point point = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(point);
        int screenhigh = point.y;
       return  screenhigh/3;

    }

    private void initlist() {
        Msg msg1 = new Msg("哈哈哈，你个大傻逼", Msg.TYPE_SEND);
        Msg msg2 = new Msg("你特么是谁啊", Msg.TYPE_RECEIVE);
        Msg msg3 = new Msg("你猜啊~", Msg.TYPE_SEND);
        Msg msg4 = new Msg("哈哈哈，你个大傻逼", Msg.TYPE_RECEIVE);
        Msg msg5 = new Msg("你特么是谁啊", Msg.TYPE_SEND);
        Msg msg6 = new Msg("你猜啊~", Msg.TYPE_RECEIVE);
        msglist.add(msg1);
        msglist.add(msg2);
        msglist.add(msg3);
        msglist.add(msg4);
        msglist.add(msg5);
        msglist.add(msg6);
        msglist.add(msg5);
        msglist.add(msg6);
        msglist.add(msg5);
    }

    @Override
    public void onClick(View v) {
        if (v == sendBtn) {

            String str = editText.getText().toString();
            if (!TextUtils.isEmpty(str)) {
                Msg msg = new Msg(str, type);
                msglist.add(msg);
                adapter.notifyItemInserted(msglist.size() - 1);//插入信息到最后一行，同时又刷新recyclerView
                recyclerView.scrollToPosition(msglist.size() - 1);//滚动recyclerView，定位到最后一行
                editText.setText("");
                type = type * (-1);
            }
        }

    }

    /**
     * 视图变化监听器，可以用来检测软键盘是否弹起
     * @param v
     * @param left    改变后的试图的上下左右坐标
     * @param top
     * @param right
     * @param bottom
     * @param oldLeft   改变前的视图上下左右坐标
     * @param oldTop
     * @param oldRight
     * @param oldBottom
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //屏蔽掉activity加载一开始也会错误得到软键盘谈起
        if (oldBottom!=0) {
            //发现下面这句代码放在软键盘弹起监听中不起作用，只好放在这儿
            recyclerView.scrollToPosition(msglist.size() - 1);//滚动recyclerView，定位到最后一行
            int high=bottom - top;//现在的屏幕高度
            int oldHigh=oldBottom - oldTop;//原来的屏幕高度
            //通过差值判断，软键盘的弹起收起
            if (oldHigh-high>getkeyboardhigh()){
                Toast.makeText(this, "键盘弹起", Toast.LENGTH_SHORT).show();
            }
            else if (high-oldHigh>getkeyboardhigh()){
                Toast.makeText(this, "键盘收起", Toast.LENGTH_SHORT).show();
            }
       }

    }


}
