package com.example.emili.cdf_rosny.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.emili.cdf_rosny.R;


import java.util.Collections;
import java.util.List;

/**
 * Created by emili on 05/06/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> contactList = Collections.emptyList();
    Context context;
    int taille;
    LayoutInflater inflater;

    public UserAdapter(Context context, List<User> contactList){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.contactList = contactList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.users, parent, false);
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
       User user = contactList.get(position);
        myHolder.prenom.setText(user.getPrenom());
        myHolder.nom.setText(user.getNom());
        myHolder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        taille = contactList.size();
        return taille;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView prenom;
        TextView nom;
        TextView email;
        ImageView image_user;


        public MyHolder(View itemView) {
            super(itemView);
            image_user = (ImageView) itemView.findViewById(R.id.image_user);
            prenom = (TextView) itemView.findViewById(R.id.userPrenom);
            nom = (TextView) itemView.findViewById(R.id.userNom);
            email  = (TextView) itemView.findViewById(R.id.userEmail);
        }
    }
}
