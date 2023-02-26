package com.example.dev.group_pharma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {



DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://group-pharma1-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        TextView signin= findViewById(R.id.signIn_text_view);
        final EditText fullname =findViewById(R.id.firstname_signup);
        final EditText email =findViewById(R.id.email_signup);
        final EditText phone =findViewById(R.id.sign_phone_1);
        final EditText password =findViewById(R.id.password_signup);
        final Button registerBtn =findViewById(R.id.signup_button);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullnametxt =fullname.getText().toString();
                final String emailtxt =email.getText().toString();
                final String phonetxt =phone.getText().toString();
                final String passwordtxt =password.getText().toString();



                // checks if user fill all the fields before sending data to firebase

                if(fullnametxt.isEmpty() || emailtxt.isEmpty() ||phonetxt.isEmpty() ||passwordtxt.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else {


                    databaseReference.child("users").child(phonetxt).child("fullname").setValue(fullnametxt);
                    databaseReference.child("users").child(phonetxt).child("email").setValue(emailtxt);
                    databaseReference.child("users").child(phonetxt).child("password").setValue(passwordtxt);

                    Toast.makeText(SignupActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this,Homepage.class));
                    finish();
                }
            }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


    }

}
