package com.javaguru.learnjava;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Interstitial{

    private static InterstitialAd interstitialAd;

    public static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-8535542520913118/1494525591";

    public static void loadInterstitial(Context context){
        try{
            interstitialAd = new InterstitialAd(context);
            interstitialAd.setAdUnitId(Interstitial.AD_UNIT_ID_INTERSTITIAL);
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    if (interstitialAd.isLoaded()) {
                        try{
                            interstitialAd.show();
                        }catch(Exception e){}
                    }
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {

                }
            });
        }catch(Exception e){}

    }


}