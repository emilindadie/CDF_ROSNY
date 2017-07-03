package com.example.emili.cdf_rosny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.emili.cdf_rosny.data.Contact;
import com.example.emili.cdf_rosny.data.User;
import com.example.emili.cdf_rosny.data.UserAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = "";


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<User> liste_users;
    UserAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = (RecyclerView) findViewById(R.id.ma_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        liste_users = new ArrayList<User>();

        databaseReference = firebaseDatabase.getInstance().getReference("users");

        searchUser();

    }

    public void searchUser() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot users_callback : dataSnapshot.getChildren()){

                    User emilin = users_callback.getValue(User.class);

                    liste_users.add(emilin);
                }

                contactAdapter = new UserAdapter(getApplicationContext(), liste_users);
                recyclerView.setAdapter(contactAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
