package com.javaguru.learnjava;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;



public class RewardedAd {

    // private InterstitialAd mInterstitialAd;
    private static RewardedVideoAd mRewardedVideoAd;

    //our id = "ca-app-pub-8535542520913118/5896805038"
    private static final String rewarded_video_add_unit_id = "ca-app-pub-8535542520913118/5896805038j";


    private static void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(RewardedAd.rewarded_video_add_unit_id,
                new AdRequest.Builder().build());

    }


    public static void init(final Context context) {



        MobileAds.initialize(context, "ca-app-pub-8535542520913118~4637332181");

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

            @Override
            public void onRewardedVideoCompleted() {
                //Toast.makeText(context, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, IndexActivity.class);
                //context.startActivity(intent);
                //finish();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                //Toast.makeText(Add.this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                //        rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                //Toast.makeText(Add.this, "onRewardedVideoAdLeftApplication",
                //       Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdClosed() {

              //  Toast.makeText(context, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
               //Toast.makeText(context, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLoaded() {
                mRewardedVideoAd.show();
                //Toast.makeText(context, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRewardedVideoAdOpened() {
                //Toast.makeText(Add.this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoStarted() {
                //Toast.makeText(Add.this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
            }


        });

        loadRewardedVideoAd();

    }

    private static void showRewardedAd() {
        mRewardedVideoAd.show();
    }

}
