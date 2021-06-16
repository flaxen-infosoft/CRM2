package com.example.crm;

import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressAlert {
	public static ProgressDialog make(Context context, String msg) {
		ProgressDialog loading = new ProgressDialog(context);
		loading.setCancelable(false);
		loading.setMessage(msg);
		loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		return loading;
	}
}
