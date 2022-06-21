package com.example.whatsapp3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp3.Activities.contactsClickListener;
import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView id;
        private TextView message;
        private TextView lasdate;
        private ShapeableImageView profilePic;
        public CardView cardView;
        private ContactViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.contactItemNickname);
//            id = itemView.findViewById(R.id.messageId);
            message = itemView.findViewById(R.id.ContactItemLatMessage);
            //lastdate =
            profilePic = itemView.findViewById(R.id.profileImage);
            cardView = itemView.findViewById(R.id.contactCardInList);
        }
    }

    private final LayoutInflater mInflater;
    private List<PostContact> contacts;
    private contactsClickListener listener;


    public ContactsListAdapter(Context context, contactsClickListener listener){
        mInflater = LayoutInflater.from(context);
        this.listener = listener;

    }

    @Override
    public ContactViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
        View itemView = mInflater.inflate(R.layout.contact_item, parent,false);
        return  new ContactViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (contacts != null){
            final  PostContact current = contacts.get(position);
            holder.name.setText(current.getName());
            int p = position;
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(contacts.get(p));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(contacts!= null){
            return  contacts.size();
        }
        return 0;
    }

    public void setPosts(List<PostContact> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }
    /*public void addContact(PostContact contact){
        this.contacts.add(contact);
        notifyDataSetChanged();
    }*/
    public List<PostContact> getPosts() {return contacts;}
}
