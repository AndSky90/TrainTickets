package com.i550.traintickets;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.i550.traintickets.Model.DataModel;
import com.i550.traintickets.Model.StationsDataBase;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TICKETS";
    private static final String filename = "StationsDataBase";
    private DataModel dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = ViewModelProviders.of(this).get(DataModel.class);

        Intent intent = new Intent();
        intent.setType("text/plain");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();
                    String jsonString;
                    Resources myResources = getResources();
                    InputStream myFile = myResources.openRawResource(R.raw.myfilename);


                    try (InputStream in = new BufferedInputStream(new FileInputStream(path))) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        int bytesRead;
                        byte[] buffer = new byte[1024];
                        while ((bytesRead = in.read(buffer)) > 0) out.write(buffer, 0, bytesRead);
                        out.close();
                        jsonString = new String(out.toByteArray());
                    } catch (IOException e) {
                        Log.e("TICKETS", "" + e);
                        break;
                    }
                    Gson gson = new Gson();
                    StationsDataBase sdb = gson.fromJson(jsonString, StationsDataBase.class);
                    Log.i(TAG, "Set JSON " + jsonString);
                    dm.setDb(sdb);
                }
        }
    }


}
