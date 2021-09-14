package com.example.crm.EmployeeManagement;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import com.example.crm.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;

public class ChooseTargetLocation extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SearchView searchView;
    PlacesClient placesClient;
    ArrayList<SpannableString> places;
    ListView list;
    ArrayAdapter<SpannableString> adapter;
    List<AutocompletePrediction> preds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_targetlocatiion);
        searchView = findViewById(R.id.search);
        Places.initialize(getApplicationContext(), "AIzaSyAJe6reTdRY5qFB-P6fZBzCs_exCV0Hsfc");
        placesClient = Places.createClient(this);
        list = findViewById(R.id.listView);
        places = new ArrayList<>();
        adapter = new ArrayAdapter<>(ChooseTargetLocation.this, android.R.layout.simple_list_item_1, places);
        list.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchLocation(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {
                    places.clear();
                    adapter.notifyDataSetChanged();
                } else
                    searchLocation(newText);
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutocompletePrediction prediction = preds.get(position);
            }
        });
    }

    synchronized public void searchLocation(String text) {

        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        RectangularBounds indiaBounds = RectangularBounds.newInstance(
                new LatLng(23.63936, 68.14712),
                new LatLng(28.20453, 97.34466));

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationBias(indiaBounds)
                .setOrigin(new LatLng(22.7196, 75.8577))
                .setCountries("IN")
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(text)
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {

            preds = response.getAutocompletePredictions();
            places.clear();
            if (text != null && text.length() != 0 && preds.size() == 0) {
                SpannableString noData = new SpannableString("No results found");
                noData.setSpan(new ForegroundColorSpan(Color.GRAY), 0, noData.length(), 0);
                noData.setSpan(new RelativeSizeSpan(0.8f), 0, noData.length(), 0);
                places.add(noData);
            } else {
                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append(prediction.getPrimaryText(new StyleSpan(Typeface.BOLD)));
                    builder.append("\n");
                    SpannableString secondary = prediction.getSecondaryText(null);
                    secondary.setSpan(new ForegroundColorSpan(Color.GRAY), 0, secondary.length(), 0);
                    secondary.setSpan(new RelativeSizeSpan(0.8f), 0, secondary.length(), 0);
                    builder.append(secondary);
                    places.add(SpannableString.valueOf(builder));
                }
            }
            adapter.notifyDataSetChanged();

        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e("123", "Place not found: " + apiException.getStatusCode());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.7196, 75.8577), 10f)); // latlng of indore
    }
}