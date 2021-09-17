package com.example.crm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.Retro.RetrofiBase2;
import com.example.crm.SalesManagement.RetroInterface;
import com.example.crm.SalesManagement.SalesMeetingAdapter;
import com.example.crm.SalesManagement.model.ClientListItem;
import com.example.crm.SalesManagement.model.Meeting;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesMeetActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ArrayList<Meeting> meetings;
    SalesMeetingAdapter adapter;
    RecyclerView recyclerView;
    RetroInterface retrofi;
    ArrayList<ClientListItem> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_meet);
        retrofi = RetrofiBase2.initretro().create(RetroInterface.class);
        clients = new ArrayList<>();
        clients.add(new ClientListItem("-1", "Select your client"));
        initUI();
    }

    private void onFabClick() {
        fab.setOnClickListener(v -> {
            buildAlertDialog();
        });
    }

    private void getClients(Call<ArrayList<ClientListItem>> call, FetchingClientsCallbacks fetchingClientsCallbacks) {
        call.enqueue(new Callback<ArrayList<ClientListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ClientListItem>> call, Response<ArrayList<ClientListItem>> response) {
                if (response.isSuccessful()) {
                    clients = new ArrayList<>();
                    clients.add(new ClientListItem("-1", "Select your client"));
                    clients.addAll(response.body());
                    fetchingClientsCallbacks.onComplete(clients);
                } else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ClientListItem>> call, Throwable t) {
                //error
            }
        });
    }


    private void buildAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        Call<ArrayList<ClientListItem>> call = retrofi.fetchClients();
        View content = LayoutInflater.from(this).inflate(R.layout.add_meeting_dialog, null);
        MaterialCardView schedule = content.findViewById(R.id.schedule);
        MaterialCardView cancel = content.findViewById(R.id.cancel);
        Spinner spinner = content.findViewById(R.id.spinner);

        schedule.setOnClickListener(v -> {
            if (spinner.getSelectedItemPosition() == 0) {
                Toast.makeText(SalesMeetActivity.this, "Please select client", Toast.LENGTH_SHORT).show();
            } else {
                createMeeting(spinner.getSelectedItemPosition());
                call.cancel();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(v -> {
            call.cancel();
            dialog.dismiss();
        });

        FetchingClientsCallbacks fetchingClientsCallbacks = clients -> {
            ArrayList<String> names = new ArrayList<>();
            for (ClientListItem clientListItem : clients) {
                names.add(clientListItem.getName());
            }
            String[] stringArray = names.toArray(new String[0]);
            ArrayAdapter adapter = new ArrayAdapter(SalesMeetActivity.this, android.R.layout.simple_spinner_item, stringArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        };
        if (clients != null && clients.size() == 1) {
            getClients(call, fetchingClientsCallbacks);
        } else fetchingClientsCallbacks.onComplete(clients);

        dialog.setTitle("Select your client");
        dialog.setView(content);
        dialog.show();
    }

    private void createMeeting(int selectedItemPosition) {
        Meeting meeting = new Meeting();
        meeting.setTitle(clients.get(selectedItemPosition - 1).getName());
        meeting.setClientid(clients.get(selectedItemPosition - 1).getId());
        //TODO change sales id of the logged in user (1 for now)
        meeting.setSalesid("1");

        Call<ArrayList<Meeting>> call = retrofi.createMeeting(meeting.getTitle(), meeting.getClientid(), meeting.getSalesid());
        call.enqueue(new Callback<ArrayList<Meeting>>() {
            @Override
            public void onResponse(Call<ArrayList<Meeting>> call, Response<ArrayList<Meeting>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().size() != 0 && !response.body().get(0).getId().equals("")) {
                        meetings.add(0, response.body().get(0));
                        adapter.notifyItemInserted(0);
                    } else {
                        Toast.makeText(SalesMeetActivity.this, "External Error: Failed to create meeting", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SalesMeetActivity.this, "Internal Error: Failed to create meeting", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Meeting>> call, Throwable t) {
                //error
            }
        });

    }

    private void getMeetingData() {

        Call<ArrayList<Meeting>> call = retrofi.fetchMeetings();
        call.enqueue(new Callback<ArrayList<Meeting>>() {
            @Override
            public void onResponse(Call<ArrayList<Meeting>> call, Response<ArrayList<Meeting>> response) {
                if (response.isSuccessful()) {
                    meetings = response.body();
                    initRecyclerView();
                } else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Meeting>> call, Throwable t) {
                //error
            }
        });

    }

    private void initRecyclerView() {

        adapter = new SalesMeetingAdapter(meetings, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initUI() {
        fab = findViewById(R.id.fabbutton);
        recyclerView = findViewById(R.id.meeting_recycler_view);
        getMeetingData();
        onFabClick();
    }

    public interface FetchingClientsCallbacks {
        void onComplete(ArrayList<ClientListItem> clients);
    }

}