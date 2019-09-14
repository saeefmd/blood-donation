package com.saeefmd.official.miublooddonors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saeefmd.official.miublooddonors.Model.BloodGroup;
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
    String data;

    List<BloodGroup> bloodGroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_result);

        new ParseResult().execute();
    }


    public class ParseResult extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                data = getData("https://miu-blood-donors.firebaseio.com/.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            bloodGroupList = new ArrayList<>();

            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Map<String, DonorList>>>() {}.getType();
            Map<String,Map<String, DonorList>> map = gson.fromJson(data, mapType);
            System.out.println(map.size());


            Set<Map.Entry<String,Map<String, DonorList>>> entrySet = map.entrySet();
            Iterator iterator = entrySet.iterator ();


            for(int j = 0; j < entrySet.size(); j++) {

                try {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = entry.getKey().toString();
                    Map<String, DonorList> donorEntry = map.get(key);

                    Set<Map.Entry<String, DonorList>> entrySet1 = donorEntry.entrySet();
                    Iterator iterator1 = entrySet1.iterator ();

                    List<DonorList> donorLists = new ArrayList<>();

                    for(int k = 0; k < entrySet1.size(); k++) {
                        try {
                            Map.Entry entry1 = (Map.Entry) iterator1.next();
                            String mobilekey = entry1.getKey().toString();
                            DonorList donors = donorEntry.get(mobilekey);

                            donorLists.add(donors);
                        }
                        catch(NoSuchElementException e) {
                            e.printStackTrace();
                        }
                    }

                    BloodGroup bloodGroup = new BloodGroup(key, donorLists);

                    bloodGroupList.add(bloodGroup);

                }
                catch(NoSuchElementException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Test "+ bloodGroupList.toString());

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
