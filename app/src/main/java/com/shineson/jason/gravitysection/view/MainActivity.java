package com.shineson.jason.gravitysection.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.shineson.jason.gravitysection.R;
import com.shineson.jason.gravitysection.view.maincollectionview.MainCollectionFragmentActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentActivity = new Intent(this, MainCollectionFragmentActivity.class);
        startActivity(intentActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
