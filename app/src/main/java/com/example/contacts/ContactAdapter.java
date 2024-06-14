package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    Context ct;
    int type;
    int[] imgs = {R.drawable.e, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.l};
    ;

    ArrayList<ContactNew> contacts;
    private SelectListenr selectListenr;

    public ContactAdapter(Context ct2, ArrayList<ContactNew> contacts2, SelectListenr selectListenr2, int type) {
        ct = ct2;
        contacts = contacts2;
        this.type = type;
        selectListenr = selectListenr2;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view;
        if (type == 1) {
            view = inflater.inflate(R.layout.contact, parent, false);
        } else {
            view = inflater.inflate(R.layout.fav_contact, parent, false);
        }

        return new ContactViewHolder(view, selectListenr);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).name);
        holder.phone.setText(contacts.get(position).phone);
        holder.id.setText(contacts.get(position).id);
        holder.email.setText(contacts.get(position).email);
        holder.isfavorite.setText(contacts.get(position).isFavorite);
        holder.dp.setImageResource(getProfilePic(contacts.get(position).id));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, phone, email, isfavorite;
        ImageView dp;
        ConstraintLayout contactCard;

        public ContactViewHolder(@NonNull View itemView, SelectListenr selectListenr2) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone);
            dp = itemView.findViewById(R.id.contactImage);

            id = itemView.findViewById(R.id.contact_id);
            email = itemView.findViewById(R.id.contact_email);
            isfavorite = itemView.findViewById(R.id.contact_isFavorite);
            isfavorite = itemView.findViewById(R.id.contact_isFavorite);
            contactCard = itemView.findViewById(R.id.contactCard);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectListenr2 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            selectListenr2.onItemCLicked(position, type);
                        }
                    }
                }
            });
        }
    }

    public int getProfilePic(String id) {
        int idLastNum = Integer.parseInt(id.substring(id.length() - 1));
        return imgs[idLastNum];
    }
}

