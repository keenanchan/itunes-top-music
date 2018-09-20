package com.example.keenanchan.itunestopmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.keenanchan.itunestopmusic.adapter.CustomAdapter;
import com.example.keenanchan.itunestopmusic.model.Example;
import com.example.keenanchan.itunestopmusic.network.GetDataService;
import com.example.keenanchan.itunestopmusic.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //handle for Retrofit instance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<Example> call = service.getExample();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                System.out.println("responded! " + response.body().getFeed().getResults().size());
                generateFromExample(response.body());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                System.out.println("failed!");
            }
        });
    }

    private void generateFromExample(Example example) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, example);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
