package com.example.sti_agent.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.Constant;
import com.example.sti_agent.Model.CustomerModel.CustomerDetail;
import com.example.sti_agent.Model.TransactionHistroy.History;
import com.example.sti_agent.R;
import com.example.sti_agent.interfaces.ItemClickListener;
import com.example.sti_agent.operation_activity.PolicyPaymentActivity;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Context context;
    private List<CustomerDetail> mCustomerDetailList;


    public CustomerAdapter(Context context, List<CustomerDetail> mCustomerDetail) {
        this.context = context;
        this.mCustomerDetailList = mCustomerDetail;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        CustomerDetail transactionOption = mCustomerDetailList.get(i);

        holder.mCustomerName.setText(transactionOption.getLastName()+" "+transactionOption.getFirstName());

        holder.mAddress.setText(transactionOption.getAddress());
        holder.mEmail.setText(transactionOption.getEmail());
        holder.mPhonenumber.setText(transactionOption.getPhone());
        Picasso.with(context).load(transactionOption.getPicture())
                .centerCrop()
                .resize(100,100)
                .into(holder.mUserThumbnail);
        //holder.mUserThumbnail.setText(transactionOption.getEmail());


        holder.setItemClickListener(pos -> {
            /*if (transactionOption.getStatus().equals("Pending")) {

                String p_tag = transactionOption.getPolicyNumber();
                String poliy_type;


                if (p_tag.contains("MOT")) {
                    poliy_type = "vehicle";
                } else if (p_tag.contains("SWISS")) {
                    poliy_type = "swiss";
                } else if (p_tag.contains("ET")) {
                    poliy_type = "travel";
                } else if (p_tag.contains("ACC/AR")) {
                    poliy_type = "all_risk";
                } else if (p_tag.contains("MAR")) {
                    poliy_type = "marine";
                } else {
                    poliy_type = "vehicle";
                }


                nextActivity(mCustomerDetailList.get(pos).getReference(), mCustomerDetailList.get(pos).getPolicyNumber(),
                        mCustomerDetailList.get(pos).getAmount(), poliy_type, PolicyPaymentActivity.class);
            }*/
        });


    }

    private void nextActivity(String ref, String policy_num, String amount, String poliy_type, Class productActivityClass) {
        Intent i = new Intent(context, productActivityClass);
        i.putExtra(Constant.REF, ref);
        i.putExtra(Constant.POLICY_NUM, policy_num);
        i.putExtra(Constant.TOTAL_PRICE, amount);
        i.putExtra(Constant.POLICY_TYPE, poliy_type);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return mCustomerDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * ButterKnife Code
         **/
        @BindView(R.id.transaction_card)
        MaterialCardView mProductCard;
        @BindView(R.id.user_thumbnail)
        CircleImageView mUserThumbnail;
        @BindView(R.id.customerName)
        TextView mCustomerName;
        @BindView(R.id.phone)
        TextView mPhonenumber;
        @BindView(R.id.email)
        TextView mEmail;
        @BindView(R.id.address)
        TextView mAddress;

        /**
         * ButterKnife Code
         **/

        ItemClickListener itemClickListener;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

    }
}

