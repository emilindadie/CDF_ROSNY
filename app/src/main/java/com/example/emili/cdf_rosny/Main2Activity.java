package com.example.emili.cdf_rosny;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.emili.cdf_rosny.data.FeedsAdapter;
import com.example.emili.cdf_rosny.data.FeedsModel;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity {

    FeedsModel model;
    List<FeedsModel> liste;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FeedsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        liste = new ArrayList<>();

        for(int i= 0; i < 10; i++){
            model = new FeedsModel("1", "Master", "Handi");
            liste.add(model);
        }
        adapter = new FeedsAdapter(getApplicationContext(), liste);
        recyclerView.setAdapter(adapter);

    }
}
