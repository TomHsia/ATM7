package com.shia.atm7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List trans;

    public TransactionAdapter(List trans) {
        this.trans = trans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.row_trans,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction tran = (Transaction) trans.get(position);
        holder.dateView.setText(tran.getDate());
        holder.amountView.setText(tran.getAmount()+"");
        holder.typeView.setText(tran.getType()+"");



    }

    @Override
    public int getItemCount() {
        if (trans != null){
            return trans.size();
        }else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView dateView;
        private final TextView amountView;
        private final TextView typeView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.col_date);
            amountView = itemView.findViewById(R.id.col_amount);
            typeView = itemView.findViewById(R.id.col_type);
        }
    }
}
