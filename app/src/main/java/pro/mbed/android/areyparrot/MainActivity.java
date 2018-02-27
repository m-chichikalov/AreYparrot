package pro.mbed.android.areyparrot;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
                            implements SoundLevelUpdatable {
    private static final int PERMISSION_REQUEST_RECORD = 0;
    TextView rms, peak;
    SamplingSoundMeter samplingSoundMeter;
    DrawOscView plotter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        peak = (TextView) findViewById(R.id.peak);
        rms = (TextView) findViewById(R.id.rms);

        plotter = (DrawOscView) findViewById(R.id.plotter);

        getPermission();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                samplingSoundMeter = new SamplingSoundMeter(MainActivity.this);
                samplingSoundMeter.start(50);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    public void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.RECORD_AUDIO},
                    PERMISSION_REQUEST_RECORD);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.MODIFY_AUDIO_SETTINGS},
                    PERMISSION_REQUEST_RECORD);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void updateDrawing(int peakValue, int rmsValue) {
        plotter.updateCanvas(peakValue, rmsValue);
        peak.setText("Peak: " + peakValue);
        rms.setText("RMS: " + rmsValue);
    }
}
