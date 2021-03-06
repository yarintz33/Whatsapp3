package com.example.whatsapp3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp3.Message;
import com.example.whatsapp3.R;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView content;
        private TextView message;
        private TextView lasdate;

        //public CardView cardView;
        private MessageViewHolder(View itemView){
            super(itemView);
            content = itemView.findViewById(R.id.messageContent);
            id = itemView.findViewById(R.id.messageId);
            //message = itemView.findViewById(R.id.ContactItemLatMessage);*/
            //lastdate =
            //profilePic = itemView.findViewById(R.id.profileImage);
            //cardView = itemView.findViewById(R.id.contactCardInList);
        }
    }

    private LayoutInflater mInflater;
    private List<Message> messages;
    //private contactsClickListener listener;


    public MessagesListAdapter(Context context){
        mInflater = LayoutInflater.from(context);


    }

    @Override
    public MessageViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
        View itemView = mInflater.inflate(R.layout.contact_item_layout, parent,false);
        return  new MessageViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (messages != null){
            final  Message current = messages.get(position);
            holder.content.setText(current.getContent());
            int p = position;
            /*holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(contacts.get(p));
                }
            });*/
        }
    }

    @Override
    public int getItemCount() {
        if(messages!= null){
            return  messages.size();
        }
        return 0;
    }

    public void setPosts(List<Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }
    /*public void addContact(PostContact contact){
        this.contacts.add(contact);
        notifyDataSetChanged();
    }*/
    public List<Message> getPosts() {return messages;}
}
