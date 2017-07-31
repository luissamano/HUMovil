package com.leondev.humovil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leondev.humovil.Conexion.ComprovarConn;
import com.microsoft.azure.mobile.MobileCenter;
import com.microsoft.azure.mobile.analytics.Analytics;
import com.microsoft.azure.mobile.crashes.Crashes;
import com.microsoft.azure.mobile.push.Push;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileCenter.start(getApplication(), "a3886684-abc4-4813-af84-960a8422134c",
                Analytics.class, Crashes.class);

        MobileCenter.start(getApplication(), "a3886684-abc4-4813-af84-,960a8422134c",
                Push.class);


        ComprovarConn conn = new ComprovarConn();
        boolean conne = conn.isAccesoInternet();

        if (!conne)
        {
            Toast.makeText(this,"Status connetion is null", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Status connetion is succeful", Toast.LENGTH_LONG).show();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();

                Push.setEnabled(true);
                Push.isEnabled();

                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.activity_contacto, null);

                final EditText etAsunto = (EditText) dialoglayout.findViewById(R.id.et_EmailAsunto);
                final EditText etMensaje = (EditText) dialoglayout.findViewById(R.id.et_EmailMensaje);

                Button btnEnviarMail = (Button) dialoglayout.findViewById(R.id.btnEnviarMail);
                btnEnviarMail.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {


                        String subject = etAsunto.getText().toString();
                        String message = etMensaje.getText().toString();

                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"LuisMartin.Samano@gstautoleather.com"});
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, "" + message);


                        // need this to prompts email client only
                        email.setType("message/rfc822");
                        startActivity(email);

                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialoglayout);
                builder.setIcon(R.drawable.email);
                builder.show();

            }
        });
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
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);


            builder.setTitle("About");
            builder.setIcon(R.drawable.informatio);
            builder.setMessage("This application is property of GST Autoleather, Developed by the team ITleon.\n\nsc.");


            //Button One : Yes
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(MainActivity.this, "Yes button Clicked!", Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog diag = builder.create();
            diag.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Push.checkLaunchedFromNotification(this, intent);
    }


}
