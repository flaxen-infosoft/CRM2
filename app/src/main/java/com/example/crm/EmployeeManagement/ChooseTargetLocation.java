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
import android.widget.Toast;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseTargetLocation extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SearchView searchView;
    PlacesClient placesClient;
    ArrayList<SpannableString> places;
    ListView list;
    ArrayAdapter<SpannableString> adapter;
    List<AutocompletePrediction> preds;
    SearchView.OnQueryTextListener queryTextListener;
    ExtendedFloatingActionButton showPath;
    MarkerOptions marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_targetlocatiion);
        searchView = findViewById(R.id.search);
        showPath = findViewById(R.id.showPath);
        Places.initialize(getApplicationContext(), "AIzaSyAJe6reTdRY5qFB-P6fZBzCs_exCV0Hsfc");
        placesClient = Places.createClient(this);
        list = findViewById(R.id.listView);
        places = new ArrayList<>();
        adapter = new ArrayAdapter<>(ChooseTargetLocation.this, android.R.layout.simple_list_item_1, places);
        list.setAdapter(adapter);
        queryTextListener = new SearchView.OnQueryTextListener() {
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
        };
        searchView.setOnQueryTextListener(queryTextListener);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutocompletePrediction prediction = preds.get(position);
                searchView.clearFocus();
                searchView.setOnQueryTextListener(null);
                searchView.setQuery(prediction.getFullText(null).toString(), true);
                searchView.setOnQueryTextListener(queryTextListener);
                places.clear();
                adapter.notifyDataSetChanged();
                processPrediction(prediction);
            }
        });

        showPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (marker == null) {
                    Toast.makeText(ChooseTargetLocation.this, "Please select destination", Toast.LENGTH_SHORT).show();
                } else {
                    drawPathFromFlaxenToDestinationMarker();
                }
            }
        });

    }

    private void drawPathFromFlaxenToDestinationMarker() {
        map.clear();
        LatLng source = new LatLng(22.72795, 75.88519);
        map.addMarker(new MarkerOptions().position(source).title("Marker at FlaxenInfosoft"));
        LatLng destination = marker.getPosition();
        map.addMarker(marker.title("Marker in Madrid"));


        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAJe6reTdRY5qFB-P6fZBzCs_exCV0Hsfc")
                .build();

        String strSource = "" + source.latitude + "," + source.longitude;
        String strDestination = "" + destination.latitude + "," + destination.longitude;
        DirectionsApiRequest req = DirectionsApi.getDirections(context, strSource, strDestination);
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("123", ex.getLocalizedMessage());
        }
        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(10);
            map.addPolyline(opts);
        }
        map.getUiSettings().setZoomControlsEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(source, 6));
    }

    private void processPrediction(AutocompletePrediction prediction) {

        String placeId = prediction.getPlaceId();

        List<Place.Field> placeFields = Collections.singletonList(Place.Field.LAT_LNG);
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields)
                .build();

        placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                Place place = fetchPlaceResponse.getPlace();
                LatLng latLng = place.getLatLng();
                plotLatlng(latLng);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChooseTargetLocation.this, "Failed to select the place", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void plotLatlng(LatLng latLng) {
        marker = new MarkerOptions().position(latLng);
        map.clear();
        map.addMarker(marker);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20f));
    }

    synchronized public void searchLocation(String text) {

        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        //TODO change the rect with indore rect
        RectangularBounds indiaBounds = RectangularBounds.newInstance(
                new LatLng(23.63936, 68.14712),
                new LatLng(28.20453, 97.34466));

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationBias(indiaBounds)
                .setOrigin(new LatLng(22.7196, 75.8577))
                .setCountries("IN")
                .setTypeFilter(TypeFilter.ESTABLISHMENT)
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