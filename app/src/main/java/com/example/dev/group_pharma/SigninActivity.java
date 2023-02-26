package com.example.dev.group_pharma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         TextView signup= findViewById(R.id.signup_text_view);
         final EditText phone = findViewById(R.id.phone_edit_text);
         final EditText password = findViewById(R.id.password_edit_text);
         final Button login = findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String phone_text= phone.getText().toString();
                 final String password_text= password.getText().toString();

                 if(phone_text.isEmpty() || password_text.isEmpty())
                 {
                     Toast.makeText(SigninActivity.this, "Please enter your email or password", Toast.LENGTH_SHORT).show();
                 }
                 else {

                      databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {
                              if(snapshot.hasChild(phone_text)){

                                  final String getPassword = snapshot.child(phone_text).child("password").getValue(String.class);
                                  if(getPassword.equals((password_text))){

                                      Toast.makeText(SigninActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();

                                      startActivity(new Intent(SigninActivity.this,Homepage.class));
                                      finish();
                                  }

                                  else {
                                      Toast.makeText(SigninActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                  }

                              }
                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error) {

                          }
                      });
                 }
             }
         });

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                 startActivity(intent);
             }
         });
    }
}