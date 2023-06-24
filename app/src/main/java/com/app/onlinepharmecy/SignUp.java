package com.app.onlinepharmecy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.onlinepharmecy.Models.SignUpModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.app.onlinepharmecy.R;
import com.app.onlinepharmecy.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Dialog dialog_loading,dialog_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog_loading = new Dialog(this);
        dialog_loading.setContentView(R.layout.dialog_loading);
        dialog_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_loading.setCancelable(false);

        dialog_exit = new Dialog(this);
        dialog_exit.setContentView(R.layout.dialog_exits);
        dialog_exit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_exit.setCancelable(false);

        auth= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        binding.btnSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                //For empty edittext
                if (binding.etName.getText().toString().isEmpty()){
                    binding.etName.setError("Enter your name");
                    return;
                }
                if (binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError("Enter email");
                    return;
                }
                if (binding.etPass.getText().toString().isEmpty()){
                    binding.etPass.setError("Enter password");
                    return;
                }

                if (binding.etCpass.getText().toString().isEmpty()){
                    binding.etCpass.setError("Enter password");
                    return;
                }

                if (!binding.etPass.getText().toString().equals(binding.etCpass.getText().toString())){
                    binding.etCpass.setError("Not Matched");
                    return;
                }

                if (binding.etPass.getText().toString().equals(binding.etCpass.getText().toString())){
                    dialog_loading.show();
                    auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) { // task e authentication er sob result thake(Firebase authenticatin e ja ja hoy)


                            if(task.isSuccessful()){
                                dialog_loading.cancel();
                                // Use user class for pass data **import java class**
                                SignUpModel u1=new SignUpModel(binding.etName.getText().toString(),binding.etPass.getText().toString(),binding.etPass.getText().toString());
                                // Collect individual user id. Use this Id to collect user info in different Nodes
                                String id=task.getResult().getUser().getUid();
                                // Main node er under e User name er child create hobe er Under e Id er chile create hobe then Id er under e Value store hobe
                                database.getReference().child("User").child(id).setValue(u1);

                                Intent i1=new Intent(SignUp.this,MainActivity.class);
                                startActivity(i1);
                                finish();

                                Toast.makeText(SignUp.this,"User created succesfully",Toast.LENGTH_SHORT).show();


                            }
                            else{
                                dialog_loading.cancel();
                                Toast.makeText(SignUp.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show(); //task e exception o thake
                            }
                        }
                    });// (addOnCompleteListener) Ei porjonto code korle Sign Up kora jabe abong Firebase a data record o hoye jabe.
                    //Now save user info (Username,Email,Password) in real time data base (Firebase)

                }



            }




        });







        binding.txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed() {


        dialog_exit.show();

        CardView d_cancel,d_exit;
        d_cancel=dialog_exit.findViewById(R.id.btn_de_cncl);
        d_exit=dialog_exit.findViewById(R.id.btn_exit);

        d_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        d_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_exit.cancel();
            }
        });




    }

}