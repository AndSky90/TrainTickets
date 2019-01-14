package com.i550.traintickets;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.i550.traintickets.Model.DataModel;
import com.i550.traintickets.Model.StationsDataBase;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements FragmentClickListener {
    private static final String TAG = "550";
    private static final int LIST_FROM = 0;
    private static final int LIST_TO = 1;
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
    public void onFragmentClick(int id) {                                                           //handling button clicks and fragments replacement
        switch (id) {

            case R.id.from_button: {
                ListStationsFragment fragmentReplace;
                fragmentReplace = ListStationsFragment.newInstance(LIST_FROM);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragmentReplace, fragmentReplace.TAG)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(fragmentReplace.TAG)
                        .commit();
                break;
            }
            case R.id.to_button: {
                ListStationsFragment fragmentReplace;
                fragmentReplace = ListStationsFragment.newInstance(LIST_TO);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragmentReplace, fragmentReplace.TAG)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(fragmentReplace.TAG)
                        .commit();
                break;
            }
            default:
                Log.i(TAG, "Default click (SWAP)");
                break;
        }
    }
}
 /*   ListStationsFragment fragment = ListStationsFragment.newInstance(LIST_FROM);
                fragment.setTargetFragment(MainFragment.this, LIST_FROM);
                        fm.beginTransaction().replace(R.id.fragment_container, fragment);*/