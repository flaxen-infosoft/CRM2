package com.example.crm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class alertdialong_adapter extends RecyclerView.Adapter<alertdialong_adapter.ViewHolder> {

    private ArrayList<leadsname> localDataSet;
    leadsname object;



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        Button buttonview1,buttonview2;

      //  private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View
          //  imageView =    view.findViewById(R.id.countryimage) ;
            textView = (TextView) view.findViewById(R.id.leadname);
            buttonview1 = view.findViewById(R.id.startendbutton);
            buttonview2 = view.findViewById(R.id.recordbutton);


        }
        int position  = ViewHolder.super.getAdapterPosition();
        public TextView getTextView() {
            return textView;
        }
        public Button getbuttonView1() {
            return buttonview1;
        }
        public Button getbuttonView2() {
            return buttonview2;
        }

        @Override
        public void onClick(View v) {
            int position  = ViewHolder.super.getAdapterPosition();
            System.out.println(position);

        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public alertdialong_adapter(ArrayList<leadsname> dataSet) {
        this.localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_ofsales_lead_meet, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        object = localDataSet.get(position);
        viewHolder.getTextView().setText(localDataSet.get(position).getLeadsname());
       // String uri = localDataSet.get(position).getCountryflag();
      //
        viewHolder.getbuttonView1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // onClickInterface.setClick(position);
            }
        });
        viewHolder.getbuttonView2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // onClickInterface.setClick(position);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // onClickInterface.setClick(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

