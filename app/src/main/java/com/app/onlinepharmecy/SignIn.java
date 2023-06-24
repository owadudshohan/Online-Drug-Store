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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.app.onlinepharmecy.R;
import com.app.onlinepharmecy.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {

    ActivitySignInBinding binding;
    FirebaseAuth auth;
    Dialog dialog_loading,dialog_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog_loading = new Dialog(this);
        dialog_loading.setContentView(R.layout.dialog_loading);
        dialog_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_loading.setCancelable(false);

        dialog_exit = new Dialog(this);
        dialog_exit.setContentView(R.layout.dialog_exits);
        dialog_exit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_exit.setCancelable(false);

        auth=FirebaseAuth.getInstance();


        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For empty edittext
                if (binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError("Enter email");
                    return;
                }
                if (binding.etPass.getText().toString().isEmpty()){
                    binding.etPass.setError("Enter password");
                    return;
                }

                if (!binding.etEmail.getText().toString().isEmpty() & !binding.etPass.getText().toString().isEmpty() ){

                    dialog_loading.show();
                    auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                dialog_loading.cancel();
                                Intent i1=new Intent(SignIn.this,MainActivity.class);
                                startActivity(i1);
                                finish();
                            }
                            else{
                                dialog_loading.cancel();
                                Toast.makeText(SignIn.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }



            }
        });













        binding.txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        if(auth.getCurrentUser()!=null){ //User age theke log in thakle direct Main activity te niye jave logout na korle
            Intent i2=new Intent(SignIn.this,MainActivity.class);
            startActivity(i2);
            finish();
        }
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