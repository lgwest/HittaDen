package me.noip.west.hittaden;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KartSkala extends Activity {

    TextView mapScaleEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart_skala);

        mapScaleEdit = findViewById(R.id.kartSkala);
        mapScaleEdit.setText(Integer.toString(HittaDen.mapScale));
    }

    @Override
    protected void onPause() {
        super.onPause();
        String scaleTxt = mapScaleEdit.getText().toString();
        HittaDen.mapScale = Integer.parseInt(scaleTxt);
    }

    public void onMapScaleOk(View view) {
        finish();
    }
}
