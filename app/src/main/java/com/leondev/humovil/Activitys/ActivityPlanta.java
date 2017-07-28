package com.leondev.humovil.Activitys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.leondev.humovil.Conexion.CONN;
import com.leondev.humovil.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.columnCount;
import static android.R.attr.rowCount;

public class ActivityPlanta
        extends AppCompatActivity{

    private String plantas[];
    private String resultado = "";

    Spinner spPlanta;
    EditText etInicioPlanta;
    EditText etFinalPlanta;
    Button btnBuscarPlanta;

    SimpleAdapter AD;
    private ProgressDialog progress_dialog;
    private CONN connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);


        spPlanta = (Spinner) this.findViewById(R.id.spPlanta);
        etInicioPlanta = (EditText) this.findViewById(R.id.etInicioPlanta);
        etFinalPlanta = (EditText) this.findViewById(R.id.etFinalPlanta);

        GetDataPlanta hilo = new GetDataPlanta();
        hilo.execute();

    }


    public void Planta(String COMANDOSQL) {
        ResultSet rs;
        int i = 0;

        connectionClass = new CONN();
        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(COMANDOSQL);

            if (conn == null) {
                Toast.makeText(this.getBaseContext(), "DB not connected", Toast.LENGTH_LONG).show();
            } else {

                List<Map<String, String>> data = null;
                data = new ArrayList<Map<String, String>>();

                plantas = new String[5];

                while (rs.next()) {

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString("Planta"));
                    data.add(datanum);

                    String[] from = {"A"};
                    int[] views = {R.id.tvMod};
                    AD = new SimpleAdapter(this.getBaseContext(), data, R.layout.tvmodel, from, views);
                    //spPlanta.setAdapter(AD);

                    plantas[i] = rs.getString("Planta");
                    i++;

                }//while(rs.next())


            }//else


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
    }

    private class GetDataPlanta extends AsyncTask<String, Void, String> {

        private GetDataPlanta() {}

        protected String doInBackground(String... paramVarArgs) {

            Planta("SELECT DISTINCT Planta FROM tblHideUtilizationJDE");
            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityPlanta.this.progress_dialog.dismiss();
            if (ActivityPlanta.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityPlanta.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spPlanta.setAdapter(AD);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityPlanta.this);
            ActivityPlanta.this.progress_dialog.setMessage("Please wait...");
            ActivityPlanta.this.progress_dialog.setProgressStyle(0);
            ActivityPlanta.this.progress_dialog.setCancelable(false);
            ActivityPlanta.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityPlanta.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }

}
