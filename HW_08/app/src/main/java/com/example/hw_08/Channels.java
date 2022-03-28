package com.example.hw_08;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Channels extends Activity {
    String[][][] url = {
            {
                    {"https://thanhnien.vn/rss/the-gioi/kinh-te-the-gioi-228.rss","Kinh Tế Thế Giới"},
                    {"https://thanhnien.vn/rss/the-gioi/quan-su-144.rss","Quân Sự"},
                    {"https://thanhnien.vn/rss/van-hoa/cau-chuyen-149.rss","Câu Chuyện"},
                    {"https://thanhnien.vn/rss/giai-tri/phim-101.rss","Phim"},
                    {"https://thanhnien.vn/rss/doi-song/nguoi-song-quanh-ta-231.rss","Người Sống Quanh Ta"},
                    {"https://thanhnien.vn/rss/the-thao/bong-da-quoc-te-380.rss","Bóng Đá Quanh Ta"}
            },
            {
                    {"https://vnexpress.net/rss/the-gioi.rss","Thế Giới"},
                    {"https://vnexpress.net/rss/thoi-su.rss","Thời Sự"},
                    {"https://vnexpress.net/rss/kinh-doanh.rss","Kinh Doanh"},
                    {"https://vnexpress.net/rss/startup.rss","Start Up"},
                    {"https://vnexpress.net/rss/giai-tri.rss","Giải Trí"},
                    {"https://vnexpress.net/rss/giao-duc.rss","Giáo Dục"},
                    {"https://vnexpress.net/rss/tam-su.rss","Tâm Sự"}

            },
            {
                    {"https://tuoitre.vn/rss/the-gioi.rss","Thế Giới"},
                    {"https://tuoitre.vn/rss/kinh-doanh.rss","Kinh Doanh"},
                    {"https://tuoitre.vn/rss/xe.rss","Xe"},
                    {"https://tuoitre.vn/rss/van-hoa.rss","Văn Hoá"},
                    {"https://tuoitre.vn/rss/the-thao.rss","Thể Thao"},
                    {"https://tuoitre.vn/rss/khoa-hoc.rss","Khoa Học"},
                    {"https://tuoitre.vn/rss/thoi-su.rss","Thời Sự"}
            },

    };

    String[] baos = {"Thanh Nien", "VN Express", "Tuoi Tre"};
    String[] chanelsname;
    int index;
    TextView title;
    ListView channels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        Intent callingIntent = getIntent();
        Bundle myBundle = callingIntent.getExtras();
        index = myBundle.getInt("Index");
        chanelsname = new String[url[index].length];
        for(int i =0;i<url[index].length;i++)
        {
            chanelsname[i] = url[index][i][1];
        }
        title = (TextView) findViewById(R.id.titlechannel);
        title.setText("CHANNELS IN " + baos[index]);

        channels = (ListView) findViewById(R.id.channels);
        ArrayAdapter<String> adapterNews = new ArrayAdapter<String>(Channels.this, android.R.layout.simple_list_item_1, chanelsname);
        channels.setAdapter(adapterNews);
        channels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int i, long id) {
                Intent callShowHeadlines = new Intent(Channels.this, Headlines.class);
                //prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putInt("Index",index);
                myData.putString("URL", url[index][i][0]);
                myData.putString("Caption", url[index][i][1] + " - " + baos[index]);
                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });
    }
}