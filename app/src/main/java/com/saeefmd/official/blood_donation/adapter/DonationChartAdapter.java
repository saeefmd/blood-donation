package com.saeefmd.official.blood_donation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.model.DonationChartModel;

import java.util.List;

public class DonationChartAdapter extends RecyclerView.Adapter<DonationChartAdapter.ChartViewHolder> {

    private Context mContext;
    private List<DonationChartModel> mChartModelList;

    public DonationChartAdapter(Context mContext, List<DonationChartModel> mChartModelList) {
        this.mContext = mContext;
        this.mChartModelList = mChartModelList;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.donation_chart_item, parent, false);

        return new ChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {

        holder.bloodGroupTv.setText(mChartModelList.get(position).getBloodGroup());
        holder.canDonateToTv.setText(mChartModelList.get(position).getCanDonateTo());
        holder.canReceiveFromTv.setText(mChartModelList.get(position).getCanReceiveFrom());

    }

    @Override
    public int getItemCount() {
        return mChartModelList.size();
    }

    public class ChartViewHolder extends RecyclerView.ViewHolder {

        TextView bloodGroupTv;
        TextView canDonateToTv;
        TextView canReceiveFromTv;

        public ChartViewHolder(@NonNull View itemView) {
            super(itemView);

            bloodGroupTv = itemView.findViewById(R.id.chart_blood_group_tv);
            canDonateToTv = itemView.findViewById(R.id.chart_donate_to_tv);
            canReceiveFromTv = itemView.findViewById(R.id.chart_receive_from_tv);
        }
    }
}
