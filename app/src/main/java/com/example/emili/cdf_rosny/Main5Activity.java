package com.example.emili.cdf_rosny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.emili.cdf_rosny.data.Message;
import com.example.emili.cdf_rosny.data.MessageAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button envoyer;
    private EditText message_edit;
    private ImageView image_button;
    public static final String anonymous = "anonyme";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    private String nom;
    private ChildEventListener childEventListener;

    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("messages");
        envoyer = (Button) findViewById(R.id.envoyer_message);
        message_edit = (EditText) findViewById(R.id.message_chat);
        image_button = (ImageView) findViewById(R.id.image_button);
        nom = anonymous;
        recyclerView = (RecyclerView) findViewById(R.id.chat_groupe);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final List<Message> mes_messages = new ArrayList<Message>();
        messageAdapter = new MessageAdapter(Main5Activity.this, mes_messages);
        recyclerView.setAdapter(messageAdapter);


        message_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if(charSequence.toString().trim().length() > 0){
                        envoyer.setEnabled(true);
                    }
                    else {
                        envoyer.setEnabled(false);
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        message_edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)
        });

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message mon_message = new Message(nom, message_edit.getText().toString(), null);
            databaseReference.push().setValue(mon_message);

                message_edit.setText("");
            }
        });





        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Message message = dataSnapshot.getValue(Message.class);
                    mes_messages.add(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addChildEventListener(childEventListener);
    }
}
