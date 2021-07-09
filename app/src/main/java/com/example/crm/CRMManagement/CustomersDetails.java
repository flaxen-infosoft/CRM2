package com.example.crm.CRMManagement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crm.CustomToast;
import com.example.crm.Model.Customer;
import com.example.crm.R;
import com.example.crm.Retro.RetroInterface;
import com.example.crm.Retro.Retrofi;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersDetails extends AppCompatActivity {
	Spinner spinner;
	RecyclerView recyclerView;
	CardView cardView;
	ArrayList<Customer> customers;
	CustomerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customers_details);

		recyclerView = findViewById(R.id.customer_details_recycler);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		fetchCustomers();

//        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Manage Customer", "View Customers"}));
//
//        List<String> stringList=new ArrayList<>();
//        stringList.add("1");
//        stringList.add("1");
//        stringList.add("1");

//        recyclerView.setAdapter(new CustomerAdapter(stringList));


	}

	private void fetchCustomers() {
		RetroInterface ri = Retrofi.initretro().create(RetroInterface.class);
		Call<ArrayList<Customer>> call = ri.getAllCustomers();

		call.enqueue(new Callback<ArrayList<Customer>>() {
			@Override
			public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response) {
				if (response.isSuccessful()) {
					customers = response.body();
					updateUI();
				} else {
					CustomToast.makeText(CustomersDetails.this, "Failed: " + response.message(), 0, Color.RED);
				}
			}

			@Override
			public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {
				CustomToast.makeText(CustomersDetails.this, "Failed: " + t.getMessage(), 0, Color.RED);
			}
		});

	}

	private void updateUI() {
		adapter = new CustomerAdapter();
		recyclerView.setAdapter(adapter);

	}

	private class CustomerViewHolder extends RecyclerView.ViewHolder {
		TextView Name,Description;
		public CustomerViewHolder(@NonNull @NotNull View itemView) {
			super(itemView);
			Name= itemView.findViewById(R.id.nameofCustomer);
	Description=itemView.findViewById(R.id.descriptionofCustomer);
		}
	}

	private class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

		@NonNull
		@NotNull
		@Override
		public CustomerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(CustomersDetails.this).inflate(R.layout.customer_list_item, parent, false);
			return new CustomerViewHolder(v);
		}

		@Override
		public void onBindViewHolder(@NonNull @NotNull CustomerViewHolder holder, int position) {
		Customer customer = customers.get(position);
		holder.Name.setText(customer.getName());
		holder.Description.setText("Opted for "+customer.getPackage_name()+" Plan for "+customer.getDomain()+" deal closed by rkoio");
holder.itemView.setOnClickListener(view ->{
	Intent intent = new Intent(CustomersDetails.this,CustomerDetails.class);
	intent.putExtra("customer", customers.get(position));
	startActivity(intent);
});
		}

		@Override
		public int getItemCount() {
			return customers.size();
		}
	}
}