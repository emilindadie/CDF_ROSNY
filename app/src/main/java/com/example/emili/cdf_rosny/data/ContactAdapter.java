package com.example.emili.cdf_rosny.data;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emili.cdf_rosny.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by emili on 05/06/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Contact> contactList = Collections.emptyList();
    Context context;
    int taille;
    LayoutInflater inflater;

    public ContactAdapter(Context context, List<Contact> contactList){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.contactList = contactList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.contact, parent, false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
        Contact contact = contactList.get(position);
        myHolder.prenom.setText(contact.prenom);
        myHolder.numero.setText(String.valueOf(contact.numero));
        myHolder.imageView.setImageResource(contact.imageID);
    }

    @Override
    public int getItemCount() {
        taille = contactList.size();
        return taille;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        View trait_horizontal;
        CardView cardView;
        TextView prenom;
        TextView numero;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);

            prenom = (TextView) itemView.findViewById(R.id.prenom);
            numero = (TextView) itemView.findViewById(R.id.numero);
            trait_horizontal = (View) itemView.findViewById(R.id.primerdivisor);
            imageView= (ImageView) itemView.findViewById(R.id.mon_image);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
