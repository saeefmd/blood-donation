package com.saeefmd.official.blood_donation.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saeefmd.official.blood_donation.adapter.DonorListAdapter;
import com.saeefmd.official.blood_donation.data.Variables;
import com.saeefmd.official.blood_donation.model.DonorModel;
import com.saeefmd.official.blood_donation.model.DonorData;
import com.saeefmd.official.blood_donation.R;

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

public class BloodResultActivity extends Activity {

    private static final String TAG = "BloodResultActivity";

    private String data;

    private List<DonorModel> donorModelList;

    private RecyclerView donorListRv;

    private String requiredBlood;
    private String preferredLocation;
    private String bloodGroupText;

    private ProgressBar progressBar;

    private LinearLayout errorMessageLayout;

    private TextView resultDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_result);

        progressBar = findViewById(R.id.progressBar);
        resultDescription = findViewById(R.id.result_description_tv);

        errorMessageLayout = findViewById(R.id.error_message_layout);
        errorMessageLayout.setVisibility(View.INVISIBLE);

        donorListRv = findViewById(R.id.donor_list_rv);
        donorListRv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        requiredBlood = intent.getStringExtra("bloodGroup");
        preferredLocation = intent.getStringExtra("location");

        bloodGroupText = bloodGroupInText(requiredBlood);

        if (preferredLocation.equals("Select")) {
            resultDescription.setText(requiredBlood + " Donors in All of Dhaka");
        } else {
            resultDescription.setText(requiredBlood + " Donors in " + preferredLocation);
        }

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
                data = getData(Variables.BASE_URL + bloodGroupText +".json");
                Log.d(TAG, data);
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

            Log.d(TAG, "Post Execute Triggered");

            progressBar.setVisibility(View.GONE);

            donorModelList = new ArrayList<>();

            try {
                Gson gson = new Gson();
                Type mapType = new TypeToken<Map<String, DonorData>>() {}.getType();
                Map<String, DonorData> map = gson.fromJson(data, mapType);

                Log.d(TAG, map.toString());

                Set<Map.Entry<String, DonorData>> entrySet = map.entrySet();
                Iterator iterator = entrySet.iterator();

                Log.d(TAG, "Entry set size: " + entrySet.size());

                for (int j = 0; j < entrySet.size(); j++) {

                    try {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = entry.getKey().toString();

                        Log.d(TAG, key);

                        System.out.println("Check: " + key);

                        DonorData donorEntry = map.get(key);

                        Log.d(TAG, donorEntry.toString());

                        System.out.println("Check: " + donorEntry.toString());

                        if (preferredLocation.equals("Select")) {
                            DonorModel donorModel = new DonorModel(key, donorEntry);

                            System.out.println("Check: " + donorModel.toString());

                            donorModelList.add(donorModel);

                        } else  {

                            if (donorEntry.getLocation().equals(preferredLocation)) {
                                DonorModel donorModel = new DonorModel(key, donorEntry);

                                System.out.println("Check: " + donorModel.toString());

                                donorModelList.add(donorModel);
                            }
                        }

                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

                Log.d(TAG, e.getMessage());
                errorMessageLayout.setVisibility(View.VISIBLE);
            }

            System.out.println("Test: "+ donorModelList.toString());

            if ((donorModelList != null) && donorModelList.size() > 0 ) {

                DonorListAdapter donorListAdapter = new DonorListAdapter(donorModelList, BloodResultActivity.this);
                donorListRv.setAdapter(donorListAdapter);
            } else {

                errorMessageLayout.setVisibility(View.VISIBLE);
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

    private String bloodGroupInText(String group) {

        switch (group) {
            case "A+":
                return "A_Positive";
            case "A-":
                return "A_Negative";
            case "B+":
                return "B_Positive";
            case "B-":
                return "B_Negative";
            case "O+":
                return "O_Positive";
            case "O-":
                return "O_Negative";
            case "AB+":
                return "AB_Positive";
            case "AB-":
                return "AB_Negative";
            default:
                return null;
        }
    }
}
