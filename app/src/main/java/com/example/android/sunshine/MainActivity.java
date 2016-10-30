package com.example.android.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ForecastFragment fragment = new ForecastFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, "forecastFragment")
                    .commit();
        }
        Log.e(LOG_TAG, "on Create");
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_map) {
            openPreferedLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferedLocationInMap(){
        String location = Utility.getPreferredLocation(this);
        Uri uri = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        if ((intent.resolveActivity(getPackageManager())) != null) {
            startActivity(intent);
        } else {
            Log.d(MainActivity.class.getSimpleName(), "Couldn't call " + location + ", no receiving apps installed!");
        }
    }
}
