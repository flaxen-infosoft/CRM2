package com.example.crm.HRManagement;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crm.CustomProgressAlert;
import com.example.crm.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PdfViewerActivity extends AppCompatActivity {
	PDFView pdfView;
	String url;
	ProgressDialog loading;


//	public PdfViewerActivity(String url) {
//		this.url = url;
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdf_viewer);

		loading = CustomProgressAlert.make(this, "Loading...");
		loading.show();

		pdfView = findViewById(R.id.pdfView);
		url = getIntent().getStringExtra("pdfurl");
		new PdfDownload().execute(url);
	}

	private class PdfDownload extends AsyncTask<String, Void, InputStream>
	{

		@Override
		protected InputStream doInBackground(String... strings) {
			InputStream inputStream = null   ;
			try {
				URL url = new URL(strings[0]);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				if (urlConnection.getResponseCode() == 200) {
					inputStream = new BufferedInputStream(urlConnection.getInputStream());
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return inputStream;
		}

		@Override
		protected void onPostExecute(InputStream inputStream)
		{
			pdfView.fromStream(inputStream).load();
			loading.dismiss();
		}
	}
}