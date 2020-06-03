package com.example.minimo2prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputLayout userText;
    OkHttpClient client;
    Retrofit retrofit;
    UserService userService;
    HttpLoggingInterceptor interceptor;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText = findViewById(R.id.userText);

        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient
                .Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(" ")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        userService = retrofit.create(UserService.class);
        progressBar = (ProgressBar) findViewById(R.id.myProgressBar);

    }

    public void onClickSearch (View v) {

            String user = userText.getEditText().getText().toString();
            if (user.equals(""))
                Toast.makeText(getApplicationContext(), "Introduce un usuario", Toast.LENGTH_LONG).show();
            else {
                Call<User> userCall = userService.getUserInfo(user);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        progressBar.setVisibility(View.VISIBLE);
                        User user1 = response.body();
                        Call<List<User>> followersCall = userService.getFollowers(user);
                        followersCall.enqueue(new Callback<List<User>>() {
                            @Override
                            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                                user1.setUserFollowers(response.body());
                                userInstance.getInstance().setUser(user1);
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<List<User>> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }

    }
}
