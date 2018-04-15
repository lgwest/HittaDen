package me.noip.west.hittaden;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HittaDen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitta_den);
    }

    public void onChangeMapScale(View view) {
        Intent intent = new Intent(this, KartSkala.class);
        startActivity(intent);
    }

    public void onNyKontroll(View view) {
        Intent intent = new Intent(this, NyKontroll.class);
        startActivity(intent);
    }
    public void onUppdatera(View view) {
        Intent intent = new Intent(this, Uppdatera.class);
        startActivity(intent);
    }
}
