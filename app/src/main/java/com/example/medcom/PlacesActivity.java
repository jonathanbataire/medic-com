package com.example.medcom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesActivity extends AppCompatActivity {

    private int PROXIMITY_RADIUS = 10000;
    private ArrayList<Place> placesArrayList;
    private TextView noResult;
    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Place place = placesArrayList.get(position);
            //Toast.makeText(PlacesActivity.this, "You Clicked: " + place.getPlace_id(), Toast.LENGTH_SHORT).show();
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query="+
                            place.getName() +"&query_place_id="+ place.getPlace_id());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        noResult = findViewById(R.id.no_result);

        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("latitude",0);
        double lng = intent.getDoubleExtra("longitude",0);
        String message = intent.getStringExtra("message");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle((message.equals("hospital") ? "Hospitals": message.equals("fire station") ? "Fire Stations": "Police Stations"));
        toolbar.setSubtitle((message.equals("hospital") ? "For health assistance": message.equals("fire station") ? "For fire emergencies":
                "Need to contact police") + " near you");
        setSupportActionBar(toolbar);

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        apiService.getPlaces(lat + "," + lng,message,true,"distance",message,
                "")
                .enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject places = response.body();
                assert places != null;
                JsonArray results = places.getAsJsonArray("results");
                if(results.size() < 1){
                    noResult.setVisibility(View.VISIBLE);
                }else {
                    Gson gson = new Gson();
                    Type placeListType = new TypeToken<ArrayList<Place>>(){}.getType();

                    placesArrayList = gson.fromJson(results, placeListType);
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    PlacesRecyclerAdapter adapter = new PlacesRecyclerAdapter(PlacesActivity.this, placesArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(onItemClickListener);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PlacesActivity.this));
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                noResult.setVisibility(View.VISIBLE);
                Toast.makeText(PlacesActivity.this,"Error fetching data, Make sure you have an internet connection",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }


}