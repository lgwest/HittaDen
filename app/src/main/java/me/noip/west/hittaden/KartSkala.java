package me.noip.west.hittaden;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KartSkala extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart_skala);
    }

    public void onMapScaleOk(View view) {
        finish();
    }

}
