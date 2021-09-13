package com.example.crm;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class alertdialong_adapter extends RecyclerView.Adapter<alertdialong_adapter.ViewHolder> {

    private ArrayList<leadsname> localDataSet;
    leadsname object;
    AlertDialog.Builder builderSingle;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        ImageButton buttonview2;
        Button buttonview1;


      //  private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View
          //  imageView =    view.findViewById(R.id.countryimage) ;
            textView = (TextView) view.findViewById(R.id.alertleadname);
          //  buttonview1 = view.findViewById(R.id.startendbutton);
            //buttonview2 = view.findViewById(R.id.recordbutton);

        }
        int position  = ViewHolder.super.getAdapterPosition();
        public TextView getTextView() {
            return textView;
        }
       /*public Button getbuttonView1() {
            return buttonview1;
        }
        public ImageButton getbuttonView2() {
            return buttonview2;
        }*/

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
    public alertdialong_adapter(ArrayList<leadsname> dataSet,  AlertDialog.Builder builder ) {
        this.builderSingle=builder;
        this.localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alertcard, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        leadsname object = localDataSet.get(position);
        viewHolder.getTextView().setText(object.getName());
       // String uri = localDataSet.get(position).getCountryflag();

       /* viewHolder.getbuttonView1().setOnClickListener(new View.OnClickListener() {
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
        });*/

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("dfbfbadsj");
                builderSingle.setCancelable(true);
               builderSingle.setOnDismissListener(new DialogInterface.OnDismissListener() {
                   @Override
                   public void onDismiss(DialogInterface dialog) {
                       System.out.println("dialong dismiss");
                   }
               });
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

