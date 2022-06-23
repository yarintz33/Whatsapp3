package com.example.whatsapp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class contactRecycleViewAdapter extends RecyclerView.Adapter<contactRecycleViewAdapter.MyViewHolder> {
    Context context;
    List<ContactItem>list;

    public contactRecycleViewAdapter(Context context, List<ContactItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public contactRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactRecycleViewAdapter.MyViewHolder holder, int position) {
        holder.profileIMG.setImageResource(list.get(position).getProfileIMG());
        holder.nickName.setText(list.get(position).getNickName());
        holder.lastMessage.setText(list.get(position).getLastMessage());
        holder.lastMessage.setText(list.get(position).getLastMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView profileIMG;
        TextView nickName, lastMessage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIMG=itemView.findViewById(R.id.profileImage);
            nickName = itemView.findViewById(R.id.contactItemNickname);
            lastMessage = itemView.findViewById(R.id.ContactItemLatMessage);

        }
    }
}
