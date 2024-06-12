package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    Context ct;
    int[] imgs;

    ArrayList<ContactNew> contacts;
    private SelectListenr selectListenr;

    public ContactAdapter(Context ct2, ArrayList<ContactNew> contacts2, int[] imgs2, SelectListenr selectListenr2) {
        ct = ct2;
        contacts = contacts2;
        imgs = imgs2;
        selectListenr = selectListenr2;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.contact, parent, false);
        return new ContactViewHolder(view, selectListenr);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).name);
        holder.phone.setText(contacts.get(position).phone);
        holder.id.setText(contacts.get(position).id);
        holder.email.setText(contacts.get(position).email);
        holder.isfavorite.setText(contacts.get(position).isFavorite);

        holder.dp.setImageResource(imgs[position]);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, phone, email, isfavorite;
        ImageView dp;

        public ContactViewHolder(@NonNull View itemView, SelectListenr selectListenr2) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone);
            dp = itemView.findViewById(R.id.contactImage);

            id = itemView.findViewById(R.id.contact_id);
            email = itemView.findViewById(R.id.contact_email);
            isfavorite = itemView.findViewById(R.id.contact_isFavorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectListenr2 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            selectListenr2.onItemCLicked(position);
                        }
                    }
                }
            });
        }
    }
}

