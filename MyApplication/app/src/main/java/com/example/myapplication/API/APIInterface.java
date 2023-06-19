package com.example.myapplication.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);
    @POST("/api/users")
    Call<User> createUser(@Body User user);

}