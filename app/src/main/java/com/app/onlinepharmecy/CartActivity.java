package com.app.onlinepharmecy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.onlinepharmecy.Adapters.CartAdapter;
import com.app.onlinepharmecy.Models.CartData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.app.onlinepharmecy.R;
import com.app.onlinepharmecy.databinding.ActivityCartBinding;


import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    ActivityCartBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    ArrayList<CartData> list;
    Dialog dialog_loading,dialog_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog_loading = new Dialog(this);
        dialog_loading.setContentView(R.layout.dialog_loading);
        dialog_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_loading.setCancelable(false);
        dialog_loading.show();
        database = FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();
        list=new ArrayList<>();

        CartAdapter adapter=new CartAdapter(list,CartActivity.this);
        binding.rvCart.setAdapter(adapter);
        binding.rvCart.setLayoutManager(new LinearLayoutManager(CartActivity.this));

        database.getReference().child("User").child(auth.getUid()).child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //For each loop // Collect data at last

                list.clear();
                for (DataSnapshot datasnapshot:snapshot.getChildren()){
                    CartData u1=datasnapshot.getValue(CartData.class);

                    list.add(u1);

                    adapter.notifyDataSetChanged();
                    if (list.isEmpty()){
                        Toast.makeText(CartActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }
                }
                dialog_loading.cancel();
            }







            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.getReference().child("User").child(auth.getUid()).child("Cart").removeValue();
                list.clear();
                CartAdapter adapter=new CartAdapter(list,CartActivity.this);
                binding.rvCart.setAdapter(adapter);
                binding.rvCart.setLayoutManager(new LinearLayoutManager(CartActivity.this));

            }
        });


        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("User").child(auth.getUid()).child("Cart").removeValue();
                list.clear();
                CartAdapter adapter=new CartAdapter(list,CartActivity.this);
                binding.rvCart.setAdapter(adapter);
                binding.rvCart.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                Toast.makeText(CartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
            }
        });



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}