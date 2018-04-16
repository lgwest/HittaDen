package me.noip.west.hittaden;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class KartSkala extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart_skala);

    }

    @Override
    protected void onPause() {

        super.onPause();
        TextView mapScaleEdit = findViewById(R.id.kartSkala);
        String scaleTxt = mapScaleEdit.getText().toString();
        MyPosition.mapScale = Integer.parseInt(scaleTxt);
    }

    public void onMapScaleOk(View view) {
        finish();
    }

}
