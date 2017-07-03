package com.example.emili.cdf_rosny;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emili.cdf_rosny.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;


public class MainActivity extends AppCompatActivity {
    Handler handler;
    private static final String TAG = "TAG";
    EditText nom, prenom, email, email2, password, password2;
    Button inscription;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String monEmail, monPassword, monNom, monPrenom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Base de donnée Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        this.handler = new Handler();

        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        email = (EditText) findViewById(R.id.email);
        email2 = (EditText) findViewById(R.id.email2);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);
        inscription = (Button) findViewById(R.id.inscription);


        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nom.getText().toString().length() != 0 | prenom.getText().toString().length() != 0 |
                email.getText().toString().length() != 0 |  email2.getText().toString().length() != 0
                        |  email2.getText().toString().length() != 0 |  password.getText().toString().length() != 0
                        |  password2.getText().toString().length() != 0){


                    if(email.getText().toString().equals(email2.getText().toString())){

                        if(password.getText().toString().equals(password2.getText().toString())){
                            monEmail = email.getText().toString();
                            monPassword = password.getText().toString();
                            monNom = nom.getText().toString();
                            monPrenom= prenom.getText().toString();
                            createAccount(monEmail, monPassword);

                        }
                        else {

                            Toast.makeText(getApplicationContext(), "Les deux mot de passe ne correspondes pas", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else {

                        Toast.makeText(getApplicationContext(), "Les deux email ne correspondes pas", Toast.LENGTH_SHORT).show();

                    }
                }
                else {

                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_connexion, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_connexion:

                Intent intent = new Intent(MainActivity.this, ConnexionActivity.class);
                startActivity(intent);

                // Comportement du bouton "A Propos"
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Votre compte a été créer", Toast.LENGTH_SHORT).show();
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    onAuthSuccess(task.getResult().getUser());


                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    finish();

                }
                }
            });
    }

    private void onAuthSuccess(final FirebaseUser user) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                writeNewUser(user.getUid(), monNom, monPrenom, user.getEmail());

               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getApplicationContext(), "Votre compte a été créer", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(MainActivity.this, ConnexionActivity.class);
                      startActivity(intent);
                   }
               });
            }
        });
        thread.start();
        // Write new user

    }

    /*
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }*/

    private void writeNewUser(String userId, String nom, String prenom, String email) {
        User user = new User(nom, prenom, email);
        mDatabase.child("users").child(userId).setValue(user);
    }

}
