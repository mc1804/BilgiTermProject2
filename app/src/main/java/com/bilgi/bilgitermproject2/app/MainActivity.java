package com.bilgi.bilgitermproject2.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


/**
 * Ana aktivite. Kullanicinin islemlerde kullanacagi koordinat sayisini belirleyecegi aktivitedir.
 */
public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_NUMBER_OF_COORDINATES = "com.bilgi.bilgitermproject2.app.EXTRA_NUMBER_OF_COORDINATES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Ekrandan alinacak koordinat sayisini GetCoordinatesActivity aktivitesine gonderir
     * ve GetCoordinatesActivity aktivitesini baslatir
     * @param view
     */
    public void sendNumberOfCoordinates(View view) {
        Intent intent = new Intent(this, GetCoordinatesActivity.class);
        EditText editText = (EditText) findViewById(R.id.number_of_coordinates);

        Integer numberOfCoordinates = Integer.parseInt(editText.getText().toString());
        intent.putExtra(EXTRA_NUMBER_OF_COORDINATES, numberOfCoordinates);
        startActivity(intent);
    }
}
