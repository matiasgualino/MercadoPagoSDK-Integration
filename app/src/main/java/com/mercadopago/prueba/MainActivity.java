package com.mercadopago.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.mercadopago.mpos.*;
import com.mercadopago.mpos.core.MercadoPago;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

         // Button onClick method
   public void submit(View view) {

        new MercadoPago.StartActivityBuilder()
                .setActivity(this)
                .setAmount(new BigDecimal("100"))
                .setAppID("1066063558168912")
                .setCallbackURL("http://www.afip.gov.ar")
                .setExternalReference("PRUEBA")
                .startMPOSFlow();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MercadoPago.MPOS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String paymentJSON = data.getStringExtra("payment");
                Toast.makeText(this, paymentJSON, Toast.LENGTH_LONG).show();
            } else {
                String error = data.getStringExtra("error");
                String message = data.getStringExtra("message");
                Toast.makeText(this, error + " - " + message, Toast.LENGTH_LONG).show();
            }
        }
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
}
