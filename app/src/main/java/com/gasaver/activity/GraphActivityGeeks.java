package com.gasaver.activity;

import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.Response.GraphReportsResponse;
//import com.gasaver.adapter.GraphViewPagerAdapter;
import com.gasaver.adapter.GraphViewPagerAdapter;
import com.gasaver.adapter.ViewPagerAdapter;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.google.gson.JsonObject;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphActivityGeeks extends AppCompatActivity implements View.OnClickListener{

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_graph_geeks);
//    }

    // creating a variable
    // for our graph view.

    ViewPager mViewPager;

    //ViewPagerAdapter mViewPagerAdapter;
    GraphViewPagerAdapter mViewPagerAdapter;

    ImageView iv_left_nav_viewpager, iv_right_nav_viewpager, iv_left_nav_proj, iv_right_nav_proj, iv_left_nav_prop, iv_right_nav_prop, iv_left_nav_agents, iv_right_nav_agents, iv_left_nav_dev, iv_right_nav_dev;

    //    int[] images = {R.drawable.sample2};
    int[] images = {R.drawable.sample2, R.drawable.sample3, R.drawable.sample4, R.drawable.sample5};


    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_geeks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("My Graph");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

        fetchGraphReports();

        //  getGraphReports  GraphReportsResponse  fetchGraphReports

        iv_left_nav_viewpager = findViewById(R.id.iv_left_nav_viewpager);
        iv_right_nav_viewpager = findViewById(R.id.iv_right_nav_viewpager);
        mViewPager = findViewById(R.id.viewpager);

        iv_left_nav_viewpager.setOnClickListener((View.OnClickListener) this);
        iv_right_nav_viewpager.setOnClickListener((View.OnClickListener) this);

        //AdvancedBannerSlidSearch
        mViewPager = findViewById(R.id.viewpager);

        // on below line we are initializing our graph view.
//        graphView = findViewById(R.id.idGraphView);

        // on below line we are adding data to our graph view.
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
//                // on below line we are adding
//                // each point on our x and y axis.
//                new DataPoint(0, 1),
//                new DataPoint(1, 3),
//                new DataPoint(2, 4),
//                new DataPoint(3, 9),
//                new DataPoint(4, 6),
//                new DataPoint(5, 3),
//                new DataPoint(6, 6),
//                new DataPoint(7, 1),
//                new DataPoint(8, 2)
//        });

        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
//        graphView.setTitle("My Graph View");

        // on below line we are setting
        // text color to our graph view.
//        graphView.setTitleColor(R.color.purple_200);

        // on below line we are setting
        // our title text size.
//        graphView.setTitleTextSize(18);

        // on below line we are adding
        // data series to our graph view.
//        graphView.addSeries(series);
    }

    //  getGraphReports  GraphReportsResponse  fetchGraphReports

    private void fetchGraphReports() {

        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();

//        jsonObject.addProperty("user_id", "2168");
//        jsonObject.addProperty("token", "51da1d874ab9626e5f851d02fa31472259d759ae508c988f38184582c0433fb1");


        jsonObject.addProperty("user_id", "119");
        jsonObject.addProperty("token", "24631cdd0323cea063e6cb4e5b2b0f6606540a5ae48428ed41e412131efb3c5a");

        Call<GraphReportsResponse> call = apiInterface.fetchGraphReports(jsonObject);

        call.enqueue(new Callback<GraphReportsResponse>() {
//        call.enqueue(new Callback<BannersResponse.AddsDetail>() {

            @Override
            public void onResponse(Call<GraphReportsResponse> call, Response<GraphReportsResponse> response) {
//            public void onResponse(Call<BannersResponse.AddsDetail> call, Response<BannersResponse.AddsDetail> response) {

                GraphReportsResponse graphReportsResponse = response.body();
//                BannersResponse bannersResponse = response.body();
//                BannersResponse.AddsDetail bannersResponse = response.body();


//                if (bannersResponse != null && !bannersResponse.getBanners().isEmpty()) {
//                if (bannersResponse != null && !bannersResponse.getAddsDetails().isEmpty()) {

                if (graphReportsResponse != null && !graphReportsResponse.getGraphReport().isEmpty()) {
//                if (graphReportsResponse != null && !graphReportsResponse.getStatusCode()) {
//                    if (response.body() != null && response.body().getStatusCode()) {

                    if (graphReportsResponse.getMessage() != null && !graphReportsResponse.getMessage().isEmpty()) {
//                    if (response.body().getMessage() != null && !response.body().getMessage().isEmpty()) {


                        // Initializing the ViewPagerAdapter
//                    mViewPagerAdapter = new ViewPagerAdapter(getActivity(), bannersResponse.getBanners());
//                    mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), graphReportsResponse.getAddsDetails());
//                    mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), bannersResponse.getBasePath());

//                        ViewPagerAdapter idGraphView = new ViewPagerAdapter(getApplicationContext(), graphReportsResponse.getGraphReport());
//                            ViewPager idGraphView = new ViewPagerAdapter(getApplicationContext(), graphReportsResponse.getGraphReport());

//                        mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), graphReportsResponse.getGraphReport());
//                        mViewPagerAdapter = new GraphViewPagerAdapter(GraphActivityGeeks.this, graphReportsResponse.getGraphReport());


                        // Adding the Adapter to the ViewPager

//                        mViewPager.setAdapter(mViewPagerAdapter);
//                            mViewPager.setAdapter(idGraphView);

                    }
                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<GraphReportsResponse> call, Throwable t) {
//            public void onFailure(Call<BannersResponse.AddsDetail> call, Throwable t) {

                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_nav_viewpager:
                mViewPager.arrowScroll(FOCUS_LEFT);
                break;
            case R.id.iv_right_nav_viewpager:
                mViewPager.arrowScroll(FOCUS_RIGHT);
                break;
        }

    }
}