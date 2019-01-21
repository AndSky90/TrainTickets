package com.i550.traintickets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i550.traintickets.BuildConfig;
import com.i550.traintickets.R;

public class AboutFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        TextView version = v.findViewById(R.id.version_text_view);
        version.setText(String.valueOf(BuildConfig.VERSION_CODE));
        return v;
    }
}
