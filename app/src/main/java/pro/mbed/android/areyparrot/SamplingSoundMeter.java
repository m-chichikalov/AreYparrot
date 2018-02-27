package pro.mbed.android.areyparrot;

import android.media.audiofx.Visualizer;
import android.os.Handler;


/**
 * Created by m_chichi on 25.02.2018.
 */

public class SamplingSoundMeter {
    private SoundLevelUpdatable updatable;
    private Handler handler;
    private Runnable sampler;
    Visualizer visualizer;
    Visualizer.MeasurementPeakRms measure;

    public SamplingSoundMeter(SoundLevelUpdatable updatable) {
        this.updatable = updatable;
        visualizer = new Visualizer(0);
        visualizer.setMeasurementMode(Visualizer.MEASUREMENT_MODE_PEAK_RMS);
        handler = new Handler();
        measure = new Visualizer.MeasurementPeakRms();
    }

    public void start(final int intervalMillis) {
        stop();

        sampler = new Runnable() {
            @Override
            public void run() {
                updateStatus();
                handler.postDelayed(sampler, intervalMillis);
            }
        };

        visualizer.setEnabled(true);
        sampler.run();

    }

    public void stop() {
        handler.removeCallbacks(sampler);
        visualizer.setEnabled(false);
    }

    private void updateStatus() {
        visualizer.getMeasurementPeakRms(measure);
        updatable.updateDrawing(measure.mPeak, measure.mRms);
    }
}
