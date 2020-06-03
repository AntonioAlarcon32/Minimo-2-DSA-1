package com.example.minimo2prueba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET ("users/{user}")
    Call<User> getUserInfo(@Path("user") String user);

    @GET ("users/{user}/followers")
    Call<List<User>> getFollowers(@Path("user") String user);
}
