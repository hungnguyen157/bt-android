package com.example.hw_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Channels extends AppCompatActivity {
    String[][][] url = {
            {
                    {"https://vnexpress.net/rss/the-gioi.rss","Thế Giới"},
                    {"https://vnexpress.net/rss/thoi-su.rss","Thời Sự"},
                    {"https://vnexpress.net/rss/kinh-doanh.rss","Kinh Doanh"}
            },
            {
                    {"https://vnexpress.net/rss/the-gioi.rss","Thế Giới"},
                    {"https://vnexpress.net/rss/thoi-su.rss","Thời Sự"},
                    {"https://vnexpress.net/rss/kinh-doanh.rss","Kinh Doanh"}
            },
            {
                    {"https://vnexpress.net/rss/the-gioi.rss","Thế Giới"},
                    {"https://vnexpress.net/rss/thoi-su.rss","Thời Sự"},
                    {"https://vnexpress.net/rss/kinh-doanh.rss","Kinh Doanh"}
            },

    };

    String[] baos = {"Thanh Nien", "VN Express", "Tuoi Tre"};
    String[] channel_names = {"Thế Giới", "Thời Sự", "Kinh Doanh"};

    int index;

    ListView channels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        Intent callingIntent = getIntent();
        Bundle myBundle = callingIntent.getExtras();
        index = myBundle.getInt("Index");

        this.setTitle("Channels in " + baos[index]);

        channels = (ListView) findViewById(R.id.channels);
        ArrayAdapter<String> adapterNews = new ArrayAdapter<String>(Channels.this, android.R.layout.simple_list_item_1, channel_names);
        channels.setAdapter(adapterNews);
        channels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int i, long id) {
                Intent callShowHeadlines = new Intent(Channels.this, Headlines.class);
                //prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putString("URL", url[index][i][0]);
                myData.putString("Caption", url[index][i][1] + " - " + baos[index]);
                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });
    }
}