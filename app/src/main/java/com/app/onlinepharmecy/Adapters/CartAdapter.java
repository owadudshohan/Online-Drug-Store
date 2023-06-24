package com.app.onlinepharmecy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.onlinepharmecy.Models.CartData;
import com.app.onlinepharmecy.R;
import com.app.onlinepharmecy.databinding.SampleCartBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<CartData> list;
    Context context;


    public CartAdapter(ArrayList<CartData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_cart,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        CartData data=list.get(position);

        holder.binding.txtMName.setText(data.getMd_name());
        holder.binding.txtMDose.setText(data.getMd_dose());
        holder.binding.txtMPrice.setText("Total Price "+data.getMd_price()+" MRP-৳");
        holder.binding.txtMAmnt.setText("Total Amount "+data.getMd_amnt());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SampleCartBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleCartBinding.bind(itemView);
        }
    }
}
