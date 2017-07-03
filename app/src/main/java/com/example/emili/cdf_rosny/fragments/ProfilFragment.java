package com.example.emili.cdf_rosny.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emili.cdf_rosny.CameraActivity;
import com.example.emili.cdf_rosny.ConnexionActivity;
import com.example.emili.cdf_rosny.Main2Activity;
import com.example.emili.cdf_rosny.Main3Activity;
import com.example.emili.cdf_rosny.Main4Activity;
import com.example.emili.cdf_rosny.Main5Activity;
import com.example.emili.cdf_rosny.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfilFragment extends Fragment {
    Context ctx;
    TextView nom, email, id, prenom;
    FirebaseUser user;
    DatabaseReference databaseReference;
    DatabaseReference nomUser, prenomUser;
    Button photo, go_to_recyclerView, chat;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfilFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nomUser = databaseReference.child("users").child(user.getUid()).child("nom");
        prenomUser = databaseReference.child("users").child(user.getUid()).child("prenom");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        nom = (TextView) view.findViewById(R.id.nom);
        email = (TextView) view.findViewById(R.id.email);
        id = (TextView) view.findViewById(R.id.id);
        prenom = (TextView) view.findViewById(R.id.prenom);
        photo = (Button) view.findViewById(R.id.photo);
        chat = (Button) view.findViewById(R.id.go_chat);
        go_to_recyclerView = (Button) view.findViewById(R.id.aller_recyclerView);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        ctx = getActivity();
        if (savedInstanceState != null) {

        }
        if(user != null){

            String MonEmail = user.getEmail();
            String MonId = user.getUid();

            nomUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.getValue(String.class);
                    nom.setText(name);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            prenomUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String Monprenom = dataSnapshot.getValue(String.class);
                    prenom.setText(Monprenom);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            email.setText(MonEmail);
            id.setText(MonId);

        }else {

            Intent intent = new Intent(getActivity(), ConnexionActivity.class);
            startActivity(intent);

        }

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean succes = checkCameraHardware(ProfilActivity.this);

                //if(succes){
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);

                // }else {
                //Toast.makeText(ProfilActivity.this, "Votre appareil ne possede pas de camera.", Toast.LENGTH_LONG).show();
                // }
            }
        });

        go_to_recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Main4Activity.class);
                startActivity(intent);
            }
        });


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Main5Activity.class);
                startActivity(intent);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
