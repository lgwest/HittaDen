package me.noip.west.hittaden;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NyKontroll extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_kontroll);
    }

    @Override
    protected void onPause() {

        super.onPause();
        TextView myBearingEdit = findViewById(R.id.myBearing);
        String bearingTxt = myBearingEdit.getText().toString();
        MyPosition.setMyBearing(Integer.parseInt(bearingTxt));
        TextView myDistanceEdit = findViewById(R.id.myDistance);
        String distanceTxt = myDistanceEdit.getText().toString();
        MyPosition.setMyDistance(Integer.parseInt(distanceTxt));
    }

    public void onOk(View view) {
        finish();
    }

}
