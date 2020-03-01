package com.rpiproject.seekthermalstreamviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.longdo.mjpegviewer.MjpegView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final String STREAM_SOURCE_URL = "http://159.65.13.254:5000/";
    private MjpegView viewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpStreamView();

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if (viewer != null) {
                    viewer.stopStream();
                    viewer.startStream();
                }
            }
        }, 0, 2000);
    }

    private void setUpStreamView() {
        viewer = findViewById(R.id.streamViewer);
        viewer.setMode(MjpegView.MODE_BEST_FIT);
        viewer.setAdjustHeight(true);
        viewer.setUrl(STREAM_SOURCE_URL);
        viewer.startStream();
    }
}
