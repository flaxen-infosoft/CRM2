package com.example.crm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.crm.SalesManagement.Clientnew;
import com.example.crm.SalesManagement.Followupnew;
import com.example.crm.SalesManagement.Historynew;
import com.example.crm.SalesManagement.LeadsActivitynew;
import com.example.crm.SalesManagement.Meetingnew;
//import com.example.crm.SalesManagement.Reportnew;
import com.example.crm.SalesManagement.Reportnew;
import com.google.android.material.card.MaterialCardView;

public class SalesDashboardnew extends AppCompatActivity {
MaterialCardView leads;
    WebView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesdashboardnew);
        leads=findViewById(R.id.leadscard);


    }

    public void leadsclick(View view)
    {
        Intent intent=new Intent(SalesDashboardnew.this, LeadsActivitynew.class);
        startActivity(intent);

    }

    public void followupclick(View view)
    {
        Intent intent=new Intent(SalesDashboardnew.this, Followupnew.class);
        startActivity(intent);

    }

    public void clientsclick(View view) {
        Intent intent=new Intent(SalesDashboardnew.this, Clientnew.class );
        startActivity(intent);

    }
    public void meetingclick(View view) {
        Intent intent=new Intent(SalesDashboardnew.this, Meetingnew.class );
        startActivity(intent);

    }

    public void leaveclick(View view)
    { Intent intent=new Intent(SalesDashboardnew.this, LeaveApplicationNew.class);
        startActivity(intent);

    }
    public void reportingclick(View view)
    { Intent intent=new Intent(SalesDashboardnew.this, Reportnew.class);
        startActivity(intent);

    }

    public void webmailclick(View view)
    { wv = new WebView(SalesDashboardnew.this);
        setContentView(wv);

        wv.setWebViewClient(new MyBrowser());
        MyWebChromeClient myWebChromeClient = new MyWebChromeClient();
        wv.setWebChromeClient(myWebChromeClient);
        wv.getSettings().getAllowContentAccess();
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setSupportMultipleWindows(true);
        wv.loadUrl("https://flaxeninfosoft.com:2096/");


        //   wv.loadUrl("https://indicraft.co/account.php");
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult jsResult) {
            System.out.println(message);
            wv.goBack();
            return false;
        }
    }

    public void historyclick(View view)
    {Intent intent=new Intent(SalesDashboardnew.this, Historynew.class);
        startActivity(intent);

    }

}