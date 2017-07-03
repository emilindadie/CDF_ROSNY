package com.example.emili.cdf_rosny.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emili.cdf_rosny.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by emili on 08/06/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    int taille;
    LayoutInflater inflater;
    List<Message> messageList = Collections.emptyList();


    public MessageAdapter(Context context, List<Message> messageList){

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.messageList = messageList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.message, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holderl = (MyHolder) holder;
        Message current = messageList.get(position);
        holderl.message.setText(current.getMessage());
        holderl.nom.setText(current.getNom());
        boolean isPhoto = current.getPhotoUrl() != null;
        if (isPhoto) {
            holderl.message.setText(current.getMessage());
            holderl.nom.setText(current.getNom());
            holderl.photoURL.setImageResource(Integer.valueOf(current.getPhotoUrl()));

        } else {
            holderl.message.setText(current.getMessage());
            holderl.nom.setText(current.getNom());
        }
    }

    @Override
    public int getItemCount() {
        taille = messageList.size();
        return taille;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView photoURL;
        TextView message;
        TextView nom;

        public MyHolder(View itemView) {
            super(itemView);

            photoURL= (ImageView) itemView.findViewById(R.id.photo_profil);
            message = (TextView) itemView.findViewById(R.id.message_text);
            nom = (TextView) itemView.findViewById(R.id.auteur);
        }
    }
}
