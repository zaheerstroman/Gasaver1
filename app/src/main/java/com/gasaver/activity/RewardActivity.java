package com.gasaver.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gasaver.R;
import com.gasaver.Response.GetRewardDataResponse;
import com.gasaver.databinding.ActivityRewardBinding;
import com.gasaver.databinding.RewardsListItemBinding;
import com.gasaver.network.APIClient;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardActivity extends AppCompatActivity {

    ActivityRewardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reward_ativity);

        binding = ActivityRewardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getReportsData();
        getRewardsData();
//        getRewards();

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("My Rewards");
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //do something you want
//                finish();
//            }
//        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

//        getSupportActionBar().setTitle("ViewAttachment");
//        getSupportActionBar().setTitle("Terms & Conditions");
        getSupportActionBar().setTitle("My Rewards");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

    }

    private void getRewardsData() {

        CommonUtils.showLoading(this, "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));

//        Call<UploadDataResponse> call = apiInterface.getRewardsData(jsonObject);
        Call<GetRewardDataResponse> call = apiInterface.getRewardsData(jsonObject);


        call.enqueue(new Callback<GetRewardDataResponse>() {
            @Override
            public void onResponse(Call<GetRewardDataResponse> call, Response<GetRewardDataResponse> response) {
                try {
                    CommonUtils.hideLoading();
                    binding.recyclerviewRewardList.setAdapter(new RewardActivity.UploadAdapter(RewardActivity.this, response.body().getData()));
//                    binding.recyclerview_Reward_List.setAdapter(new RewardActivity.UploadAdapter(RewardActivity.this, response.body().getData()));
//                    binding.recyclerviewList.setAdapter(new UploadActivity.UploadAdapter(UploadActivity.this,response.body().getData()));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<GetRewardDataResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });


    }


    class UploadAdapter extends RecyclerView.Adapter<RewardActivity.UploadAdapter.ViewHolder> {
        //        ArrayList<UploadDataResponse.UploadData> list;
        ArrayList<GetRewardDataResponse.GetRewardData> list;

        Context context;

        public UploadAdapter(Context context, ArrayList<GetRewardDataResponse.GetRewardData> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public RewardActivity.UploadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            UploadsListItemBinding uploadsListItemBinding=UploadsListItemBinding.inflate( LayoutInflater.from(parent.getContext()));
            RewardsListItemBinding uploadsListItemBinding = RewardsListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new RewardActivity.UploadAdapter.ViewHolder(uploadsListItemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull RewardActivity.UploadAdapter.ViewHolder holder, int position) {
//            UploadDataResponse.UploadData uploadData = list.get(position);
            GetRewardDataResponse.GetRewardData uploadData = list.get(position);

            holder.binding.tvStationCode1.setText(uploadData.getStation_name() + " (" + uploadData.getStationId() + ")");
            holder.binding.tvLocation1.setText(uploadData.getBrand());
            holder.binding.tvStatus1.setText(uploadData.getStatus());

//            holder.binding.tvAmnt1.setText(uploadData.getAmount());
//            holder.binding.tvType1.setText(uploadData.getSubcategory_name());
            holder.binding.tvTime1.setText(uploadData.getLastupdated());
//            holder.binding.tvCallBuilderRewards.setText(uploadData.getRelevant());


//            holder.binding.llContact1.setText(uploadData.getStation_name());
//            holder.binding.tvCallBuilderRewards.setText(uploadData.getStation_name());
//            holder.binding.tvCallBuilderRewardsPoints.setText(uploadData.getStation_name());
            holder.binding.tvCallBuilderRewardsPoints.setText(uploadData.getRewards());


//         station_name
//            tv_call_builder_Rewards_Points
//                    tv_call_builder_Rewards
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            //            UploadsListItemBinding binding;
            RewardsListItemBinding binding;


            public ViewHolder(@NonNull RewardsListItemBinding ubinding) {
                super(ubinding.getRoot());
                binding = ubinding;
            }
        }
    }

}