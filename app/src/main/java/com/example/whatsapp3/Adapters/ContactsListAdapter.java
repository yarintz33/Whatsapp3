package com.example.whatsapp3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp3.R;
import com.example.whatsapp3.PostContact;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView id;

        private ContactViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.contactName);
            id = itemView.findViewById(R.id.contactId);
        }
    }

    private final LayoutInflater mInflater;
    private List<PostContact> contacts;

    public ContactsListAdapter(Context context){ mInflater = LayoutInflater.from(context);}

    @Override
    public ContactViewHolder onCreateViewHolder (ViewGroup parent, int ViewType){
        View itemView = mInflater.inflate(R.layout.contact_item_layout, parent,false);
        return  new ContactViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (contacts != null){
            final  PostContact current = contacts.get(position);
            holder.name.setText(current.getName());
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
    public void addContact(PostContact contact){
        this.contacts.add(contact);
        notifyDataSetChanged();
    }
    public List<PostContact> getPosts() {return contacts;}
}
