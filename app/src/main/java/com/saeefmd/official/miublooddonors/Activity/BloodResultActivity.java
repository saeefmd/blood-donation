package com.saeefmd.official.miublooddonors.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saeefmd.official.miublooddonors.Adapter.DonorListAdapter;
import com.saeefmd.official.miublooddonors.Data.Variables;
import com.saeefmd.official.miublooddonors.Model.DonorModel;
import com.saeefmd.official.miublooddonors.Model.DonorList;
import com.saeefmd.official.miublooddonors.R;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BloodResultActivity extends AppCompatActivity {

    private String data;

    private List<DonorModel> donorModelList;

    private RecyclerView donorListRv;

    private String requiredBlood;
    private String preferredLocation;

    private ProgressBar progressBar;

    private LinearLayout errorMessageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_result);

        progressBar = findViewById(R.id.progressBar);

        errorMessageLayout = findViewById(R.id.error_message_layout);
        errorMessageLayout.setVisibility(View.GONE);

        donorListRv = findViewById(R.id.donor_list_rv);
        donorListRv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();

        requiredBlood = intent.getStringExtra("bloodGroup");
        preferredLocation = intent.getStringExtra("location");

        try {
            new ParseResult().execute();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private class ParseResult extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                data = getData(Variables.BASE_URL + requiredBlood +".json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBar.setVisibility(View.GONE);

            donorModelList = new ArrayList<>();

            try {

                Gson gson = new Gson();
                Type mapType = new TypeToken<Map<String, DonorList>>() {}.getType();
                Map<String, DonorList> map = gson.fromJson(data, mapType);

                Set<Map.Entry<String, DonorList>> entrySet = map.entrySet();
                Iterator iterator = entrySet.iterator();

                for (int j = 0; j < entrySet.size(); j++) {

                    try {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = entry.getKey().toString();

                        System.out.println("Check: " + key);

                        DonorList donorEntry = map.get(key);

                        System.out.println("Check: " + donorEntry.toString());

                        if (donorEntry.getLocation().equals(preferredLocation)) {

                            DonorModel donorModel = new DonorModel(key, donorEntry);

                            System.out.println("Check: " + donorModel.toString());

                            donorModelList.add(donorModel);
                        }

                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

                errorMessageLayout.setVisibility(View.VISIBLE);
            }

            System.out.println("Test: "+ donorModelList.toString());

            if ((donorModelList != null) && donorModelList.size() > 0 ) {

                DonorListAdapter donorListAdapter = new DonorListAdapter(donorModelList, BloodResultActivity.this);
                donorListRv.setAdapter(donorListAdapter);
            }

        }
    }

    public String getData(String url) throws IOException{

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }

    }
}
