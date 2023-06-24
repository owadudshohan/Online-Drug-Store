package com.app.onlinepharmecy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.onlinepharmecy.Models.MedicineModel;
import com.app.onlinepharmecy.R;
import com.app.onlinepharmecy.databinding.SampleMedicineBinding;


import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    ArrayList<MedicineModel> list;
    Context context;
    private OnItemClickListener listener;
    public MedicineAdapter(ArrayList list, Context context) {
        this.list = list;
        this.context = context;
    }





    @NonNull
    @Override
    public MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sample_medicine,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {

    MedicineModel model=list.get(position);

    holder.binding.txtMName.setText(model.getM_name());
    holder.binding.txtMDose.setText(model.getDose());
    holder.binding.txtMPrice.setText("Price "+model.getPrice()+" MRP-৳");
    holder.binding.mImg.setImageResource(model.getImage());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MedicineModel item);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SampleMedicineBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleMedicineBinding.bind(itemView);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                MedicineModel item = list.get(position);
                listener.onItemClick(item);
            }
        }


    }
}
