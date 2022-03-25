package com.example.hw_08;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Headlines extends Activity {
    ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
    ListView headlines; String URL = "", Caption = ""; SingleItem selectedNewsItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);
        headlines = (ListView) this.findViewById(R.id.headlines);
// find out which intent is calling us & grab data bundle holding selected url & caption sent to us
        Intent callingIntent = getIntent();
        Bundle myBundle = callingIntent.getExtras();
        URL = myBundle.getString("URL");
        Caption = myBundle.getString("Caption");
        this.setTitle("ITEMS IN CHANNELS " + Caption);
        headlines = (ListView) this.findViewById(R.id.headlines);
        headlines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int index, long id) {
                selectedNewsItem = newsList.get(index);
                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }
        });
// get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(Headlines.this);
        downloader.execute(URL, Caption);
    }

    public void showNiceDialogBox(SingleItem selectedStoryItem, Context context){
// make a nice-looking dialog box (story summary, btnClose, btnMore)
// CAUTION: (check)on occasions title and description are the same!
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())){ description = ""; }
        try {
//CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder.setIcon(R.drawable.logo_thanhnien)
                    .setTitle(Html.fromHtml(Caption) )
                    .setMessage(title + "\n\n" + Html.fromHtml(description) + "\n")
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichOne) {
                            Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                            startActivity(browser);
                        }}) //setNegativeButton
                    .show();
        }
        catch (Exception e) { Log.e("Error DialogBox", e.getMessage() ); }
    }//showNiceDialogBox
}