package me.noip.west.hittaden;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class NyKontroll extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_kontroll);
    }

    public void onOk(View view) {
        finish();
    }

}
