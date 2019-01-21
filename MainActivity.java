package com.i550.traintickets;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.i550.traintickets.Model.DataModel;
import com.i550.traintickets.Model.StationsDataBase;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TrainTickets";
    private static final String ABOUT_TAG = "ABOUT";
    public static final String STATION = "Station";
    private DataModel dm;
    FragmentManager fm;
    Fragment container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        container = fm.findFragmentById(R.id.fragment_container);
        if (container == null) {
            container = new MainFragment();
            fm.beginTransaction().add(R.id.fragment_container, container).commit();
        }


        dm = ViewModelProviders.of(this).get(DataModel.class);                              //viewmodel with all data
        String jsonString = "";
        Resources r = getResources();
        try (InputStream in = new BufferedInputStream(r.openRawResource(R.raw.trains_json))) {      //file with JSON = R.raw.trains_json
            ByteArrayOutputStream out = new ByteArrayOutputStream();                                //reading JSON file
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) out.write(buffer, 0, bytesRead);
            out.close();
            jsonString = new String(out.toByteArray());
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Invalid database file", Toast.LENGTH_SHORT);
            toast.show();
        }
        if (!jsonString.isEmpty()) {
            Gson gson = new Gson();                                                                 //parcing JSON to viewmodel
            StationsDataBase sdb = gson.fromJson(jsonString, StationsDataBase.class);
            Log.i(TAG, "Set JSON " + jsonString);
            dm.setDb(sdb);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.schedule:
                fm.beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                break;

            case R.id.about:
                fm.beginTransaction().replace(R.id.fragment_container, new AboutFragment()).addToBackStack(ABOUT_TAG).commit();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
