package com.javaguru.learnjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://silicon-technologies.co.in/LearnWithVideo/";

    @GET("getList.php")
    Call<List<Tutorials>> getTutorials();
}
