package com.example.zhuyu.uibestpractice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ZHUYU on 2017/1/10 0010.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> msglist;
    public MsgAdapter(List<Msg> msglist){
        this.msglist=msglist;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout layout_send;
        private RelativeLayout layout_receive;
        private TextView sendText;
        private TextView receiveText;

        public ViewHolder(View itemView) {
            super(itemView);
            layout_send= (RelativeLayout) itemView.findViewById(R.id.layout_send);
            layout_receive= (RelativeLayout) itemView.findViewById(R.id.layout_receive);
            sendText= (TextView) itemView.findViewById(R.id.sendText);
            receiveText= (TextView) itemView.findViewById(R.id.receiveText);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg=msglist.get(position);
            //先判断当前子项的消息类型，
            //根据类型设置好布局显示方式后再绑定数据
            if (msg.getType()==Msg.TYPE_RECEIVE){
                holder.layout_send.setVisibility(View.GONE);
                holder.layout_receive.setVisibility(View.VISIBLE);
                holder.receiveText.setText(msg.getContent());

            }else{
                holder.layout_send.setVisibility(View.VISIBLE);
                holder.layout_receive.setVisibility(View.GONE);
                holder.sendText.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msglist.size();
    }


}
