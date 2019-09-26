package com.saeefmd.official.miublooddonors.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saeefmd.official.miublooddonors.Model.DonorModel;
import com.saeefmd.official.miublooddonors.R;

import java.util.List;

public class DonorListAdapter extends RecyclerView.Adapter {

    List<DonorModel> donorModelList;
    Context context;

    public DonorListAdapter(List<DonorModel> donorModelList, Context context) {
        this.donorModelList = donorModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_donors, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        ((MyViewHolder) viewHolder).donorNameTv.setText(donorModelList.get(position).getDonorList().getName());
        ((MyViewHolder) viewHolder).donorDepartmentTv.setText(donorModelList.get(position).getDonorList().getDepartment());
        ((MyViewHolder) viewHolder).donorLocationTv.setText(donorModelList.get(position).getDonorList().getLocation());
        ((MyViewHolder) viewHolder).donorMobileTv.setText(donorModelList.get(position).getDonorList().getMobile());
    }

    @Override
    public int getItemCount() {
        return donorModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView donorNameTv;
        TextView donorDepartmentTv;
        TextView donorLocationTv;
        TextView donorMobileTv;

        Button callBt;
        Button messageBt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            donorNameTv = itemView.findViewById(R.id.donor_name_tv);
            donorDepartmentTv = itemView.findViewById(R.id.donor_department_tv);
            donorLocationTv = itemView.findViewById(R.id.donor_location_tv);
            donorMobileTv = itemView.findViewById(R.id.donor_mobile_tv);

            callBt = itemView.findViewById(R.id.call_bt);
            messageBt = itemView.findViewById(R.id.message_bt);
        }
    }
}
