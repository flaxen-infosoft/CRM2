package com.example.crm;

import static com.example.crm.R.drawable.pdficonnnn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.HRManagement.PdfViewerActivity;
import com.example.crm.Model.Customer;
import com.example.crm.Retro.Apicontrollerflaxen;
import com.example.crm.Retro.ResponsePOJO;
import com.example.crm.SalesManagement.Clientnew;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sendnvoice extends AppCompatActivity
{
    MaterialCardView upload,selectpr;
    TextView t;
    private  int reqcode=9,rescode=88;
    String encodedpdf;
    boolean isselected;
    ImageView img;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendnvoice);
        upload=findViewById(R.id.uploadpr);
        selectpr=findViewById(R.id.selectpr);


        selectpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent select=new Intent(Intent.ACTION_GET_CONTENT);
                select.setType("application/pdf");
                Intent.createChooser(select,"choose a file");
                startActivityForResult(select,reqcode);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {uploadproposalmethod();


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        t=findViewById(R.id.uploadtext);
        img=findViewById(R.id.imageView19);

        if(requestCode==reqcode&&resultCode==RESULT_OK&&data!=null)
        {
            Uri path=data.getData();
            String s=path.toString();
            File myfile=new File(s);


            try {InputStream inputStream=Sendnvoice.this.getContentResolver().openInputStream(path);
                byte[] pdfinbytes=new byte[inputStream.available()];
                inputStream.read(pdfinbytes);
                encodedpdf= Base64.encodeToString(pdfinbytes,Base64.DEFAULT);
                t.setText(myfile.getName()+".pdf");
                img.setImageResource(pdficonnnn);
                isselected=true;

                inputStream.close();
                Toast.makeText(Sendnvoice.this,"pdf selected",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void uploadproposalmethod() {
        if (isselected == true) {

            Customer c = (Customer) getIntent().getSerializableExtra("id");
            c.setInvoice(encodedpdf);

            System.out.println(c.getInvoice());
            Call<ResponsePOJO> call3 = Apicontrollerflaxen.getInstance().getapi().uploadinvoice(c);
            call3.enqueue(new Callback<ResponsePOJO>() {
                @Override
                public void onResponse(Call<ResponsePOJO> call3, Response<ResponsePOJO> response) {
                    Intent intent = new Intent(Sendnvoice.this, Clientnew.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("ikk","hello");

                    startActivity(intent);
                    Toast.makeText(Sendnvoice.this, response.body().getMessage() + "", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponsePOJO> call3, Throwable t) {
                    System.out.println(t);
                    Toast.makeText(Sendnvoice.this, t + "", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void viewpdf(View view) {


        Customer c = (Customer) getIntent().getSerializableExtra("id");
        if(c.getInvoice().isEmpty())
        {
           Toast.makeText(Sendnvoice.this,"No Previous invoice uploaded",Toast.LENGTH_SHORT).show();
        }
      //  System.out.println(c.getProposal());
       else {
            Intent intent = new Intent(Sendnvoice.this, PdfViewerActivity.class);
            intent.putExtra("pdfurl", c.getInvoice());
            startActivity(intent);
        }
    }
}//written by AAYUSH MALVIYA