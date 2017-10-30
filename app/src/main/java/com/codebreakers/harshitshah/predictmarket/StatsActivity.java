package com.codebreakers.harshitshah.predictmarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StatsActivity extends AppCompatActivity {
    private TextView displayComapnyName;
    private String stockSymbolArray[]={"Select a stock","ACC.NS", "Adani Ports and Special Economic Zone Ltd.",
            "AMBUJACEM.NS", "ASIANPAINT.NS","AXISBANK.NS",
            "BAJAJ-AUTO.NS" ,"BANKBARODA.NS" ,"BHEL.NS"
            ,"BPCL.NS","BHARTIARTL.NS" ,"BOSCHLTD.NS"
            ,"CAIRN.NS" ,"CIPLA.NS" ,"","DRREDDY.NS"
            ,"GAIL.NS","GRASIM.NS" ,"HCLTECH.NS" ,"HDFCBANK.NS"
            ,"HEROHONDA.NS" ,"HINDALCO.NS" ,"HINDUNILVR.NS"
            ,"HDFC.NS" ,"ITC.NS" ,"ICICIBANK.NS"
            ,"IDEA.NS" ,"INDUSINDBK.NS","INFOSYSTCH.NS" ,"KOTAKBANK.NS"
            ,"LT.NS" ,"LUPIN.NS" ,"M&M.NS" ,"MARUTI.NS"
            ,"NTPC.NS" ,"ONGC.NS" ,"POWERGRID.NS"
            ,"PNB.NS" ,"RELIANCE.NS" ,"SBIN.NS" ,"SUNPHARMA.NS"
            ,"TCS.NS" ,"TATAMOTORS.NS" ,"TATAPOWER.NS"
            , "TATASTEEL.NS", "TECHM.NS", "ULTRACEMCO.NS" ,"SESAGOA.NS"
            ,"WIPRO.NS" ,"YESBANK.NS" ,"ZEEL.NS"};
    private Button refresh,go;
    private Spinner month,date;
    private String months[]={"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
    private String dates[]={"1","2","3","4","5",
            "6","7","8","9", "10", "11", "12"
            ,"13","14","15","16","17","18"
            ,"19","20","21","22","23",
            "24","25","26",
            "27","28","29","30","31"};
    private String url,stockSymbol;
    private String m,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        Intent intent=getIntent();
        final int position=intent.getExtras().getInt("pos");
        String companyname=intent.getExtras().getString("companyName");
        displayComapnyName= (TextView) findViewById(R.id.companyname);
        displayComapnyName.setText(companyname);
        month= (Spinner) findViewById(R.id.month);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, months);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        month.setAdapter(spinnerArrayAdapter);
        date= (Spinner) findViewById(R.id.date);
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, dates);
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        date.setAdapter(spinnerArrayAdapter1);

        refresh= (Button) findViewById(R.id.refresh);
        refresh.setVisibility(View.INVISIBLE);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockSymbol=stockSymbolArray[position];
                m=String.valueOf(month.getSelectedItemPosition());
                d=String.valueOf(date.getSelectedItem());
                url="http://real-chart.finance.yahoo.com/table.csv?s="+stockSymbol+"&a="+m+"&b="+d+"&c=2016&d="+m+"&e="+d+"&f=2016&g=d=.csv";
                new StockFetcher().execute(url);
            }
        });
        go= (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockSymbol=stockSymbolArray[position];
                m=String.valueOf(month.getSelectedItemPosition());
                d=String.valueOf(date.getSelectedItem());
                url="http://real-chart.finance.yahoo.com/table.csv?s="+stockSymbol+"&a="+m+"&b="+d+"&c=2016&d="+m+"&e="+d+"&f=2016&g=d=.csv";
                new StockFetcher().execute(url);


            }
        });
    }

    static class Stock {
       String open;
        String close;
        String high;
        String low;
        }

    class StockFetcher extends AsyncTask<String, Void, String[]> {
        String details[]=new String[5];
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(StatsActivity.this,R.style.AppTheme_Dark_Dialog);
            dialog.setTitle("Downloading...");
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            String url1=params[0];
            Stock s=new Stock();

            try {
                URL yahoo = new URL(url1);
                HttpURLConnection connection= (HttpURLConnection) yahoo.openConnection();
                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(is);

                String line=br.readLine();
                String line2=br.readLine();
                Log.d("harshit",line2);
                //Only split on commas that aren't in quotes
                String[] stockinfo = line2.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                StockHelper sh = new StockHelper();
                //Open http connection



                s.open=stockinfo[1];
                s.high=stockinfo[2];
                s.low=stockinfo[3];
                s.close=stockinfo[4];
                details[0]=s.open;
                details[1]=s.high;
                details[2]=s.low;
                details[3]=s.close;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return details;
        }

        @Override
        protected void onPostExecute(String[] aVoid) {
            dialog.dismiss();
            TextView open= (TextView) findViewById(R.id.open);
            TextView high= (TextView) findViewById(R.id.high);
            TextView low= (TextView) findViewById(R.id.low);
            TextView close= (TextView) findViewById(R.id.close);
            open.setText(aVoid[0]);
            high.setText(aVoid[1]);
            low.setText(aVoid[2]);
            close.setText(aVoid[3]);
            refresh.setVisibility(View.VISIBLE);

        }
    }
}
