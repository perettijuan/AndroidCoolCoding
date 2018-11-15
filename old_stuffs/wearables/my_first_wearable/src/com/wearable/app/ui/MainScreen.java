package com.wearable.app.ui;

import com.wearable.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class MainScreen extends Activity {


    private TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {


            @Override
            public void onLayoutInflated(WatchViewStub view) {
                txtText = (TextView) view.findViewById(R.id.text);
                System.out.println("FOUND " + txtText != null);
            }
        });
    }

}
