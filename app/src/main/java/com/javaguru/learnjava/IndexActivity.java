package com.javaguru.learnjava;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class IndexActivity extends AppCompatActivity {

    private AdView mAdView;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "clickCounter";
    public static int  count = 0;
    public static int a = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        final ListView listView = (ListView) findViewById(R.id.Index);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8535542520913118/7241676299");
        mAdView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Tutorials>> call = api.getTutorials();

        call.enqueue(new Callback<List<Tutorials>>() {
            @Override
            public void onResponse(Call<List<Tutorials>> call, Response<List<Tutorials>> response) {
                List<Tutorials> tutorials = response.body();


                    String[] tutorialName = new String[tutorials.size()];
                    final String[] tutorialLink = new String[tutorials.size()];


                    for (int i = 0; i < tutorials.size(); i++) {
                        tutorialName[i] = tutorials.get(i).getName();
                        tutorialLink[i] = tutorials.get(i).getLink();

                    }


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int count = getCount();

                        if(count < 3) {
                            try {

                               Intent intent = new Intent(IndexActivity.this, YoutubeVideo.class);
                                intent.putExtra("tutorialLink", tutorialLink[position]);
                                startActivity(intent);

                            }

                            catch (Exception e){
                                Toast.makeText(IndexActivity.this, "Video player not supported. Please install Youtube", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }

                    public int getCount() {

                        count = mPreferences.getInt("count",0);
                        if(count < 3 ) {
                            mPreferences.edit().putInt("count", count + 1).commit();
                        }
                        else {
                            mPreferences.edit().putInt("count", 0).commit();
                            //MyApplication.loadInterstitial(this);
                            RewardedAd.init(IndexActivity.this);
                        }
                        return count;

                    }

                });

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                R.layout.list_items,
                                R.id.list_content,
                                tutorialName
                        )
                );



            }

            @Override
            public void onFailure(Call<List<Tutorials>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
