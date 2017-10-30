package com.codebreakers.harshitshah.predictmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    String name[]={"Select a stock","ACC Ltd.", "Adani Ports and Special Economic Zone Ltd.",
            "Ambuja Cements Ltd.", "Asian Paints Ltd.","Axis Bank Ltd.",
            "Bajaj Auto Ltd." ,"Bank of Baroda" ,"Bharat Heavy Electricals Ltd."
            ,"Bharat Petroleum Corporation Ltd.","Bharti Airtel Ltd." ,"Bosch Ltd."
            ,"Cairn India Ltd." ,"Cipla Ltd." ,"Coal India Ltd.","Dr Reddys Laboratories Ltd"
            ,"GAIL (India) Ltd","Grasim Industries Ltd." ,"HCL Technologies Ltd." ,"HDFC Bank Ltd."
            ,"Hero MotoCorp Ltd." ,"Hindalco Industries Ltd." ,"Hindustan Unilever Ltd."
            ,"Housing Development Finance Corporation Ltd." ,"I T C Ltd." ,"ICICI Bank Ltd."
            ,"Idea Cellular Ltd." ,"IndusInd Bank Ltd.","nfosys Ltd." ,"Kotak Mahindra Bank Ltd."
            ,"Larsen & Toubro Ltd." ,"Lupin Ltd." ,"Mahindra & Mahindra Ltd." ,"Maruti Suzuki India Ltd."
            ,"NTPC Ltd." ,"Oil & Natural Gas Corporation Ltd." ,"Power Grid Corporation of India Ltd."
            ,"Punjab National Bank" ,"Reliance Industries Ltd." ,"State Bank of India" ,"Sun Pharmaceutical Industries Ltd."
            ,"Tata Consultancy Services Ltd." ,"Tata Motors Ltd." ,"Tata Power Co. Ltd."
            ,"Tata Steel Ltd." ,"Tech Mahindra Ltd." ,"UltraTech Cement Ltd." ,"Vedanta Ltd."
            ,"Wipro Ltd." ,"Yes Bank Ltd." ,"Zee Entertainment Enterprises Ltd."};

    private Button goButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);

        final Spinner companyList= (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, name);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        companyList.setAdapter(spinnerArrayAdapter);

        goButton= (Button) findViewById(R.id.go);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName= companyList.getSelectedItem().toString();
                int position=companyList.getSelectedItemPosition();
                Log.d("harshit",companyName);
                Intent i=new Intent(getApplicationContext(),StatsActivity.class);
                i.putExtra("companyName",companyName);
                i.putExtra("pos",position);
                startActivity(i);

            }
        });

    }
}
