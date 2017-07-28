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

public class ActivityProg extends AppCompatActivity {

    private SimpleAdapter AD, AD1;
    private ProgressDialog progress_dialog;
    private CONN connectionClass;

    private String plantas[];
    private String pros[];
    private int pos;
    private String resultado = "";


    private Spinner spPlantaProg;
    private Spinner spProgProg;
    private EditText etInicioProg;
    private EditText etFinalProg;
    private Button btnBuscarProg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog);

        spPlantaProg = (Spinner) this.findViewById(R.id.spPlantaProg);
        spProgProg = (Spinner) this.findViewById(R.id.spProgProg);
        etInicioProg = (EditText) this.findViewById(R.id.etInicioProg);
        etFinalProg = (EditText) this.findViewById(R.id.etFinalProg);
        btnBuscarProg = (Button) this.findViewById(R.id.btnBuscarProg);


        spPlantaProg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = spPlantaProg.getSelectedItemPosition();
                GetOemPlanta hilo = new GetOemPlanta();
                hilo.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spProgProg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBuscarProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


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
            Log.e("ERROR_Planta_Cons",e.getMessage());
        }
    }

    public void Oem(String COMANDOSQL) {
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

                pros = new String[15];

                while (rs.next()) {

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString("DescrProgama"));
                    data.add(datanum);

                    String[] from = {"A"};
                    int[] views = {R.id.tvMod};
                    AD1 = new SimpleAdapter(this.getBaseContext(), data, R.layout.tvmodel, from, views);
                    //spPlanta.setAdapter(AD);

                    pros[i] = rs.getString("DescrProgama");
                    i++;

                }//while(rs.next())


            }//else


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("progs",e.getMessage());
        }
    }

    private class GetDataPlanta extends AsyncTask<String, Void, String> {

        private GetDataPlanta() {}

        protected String doInBackground(String... paramVarArgs) {

            Planta("SELECT DISTINCT Planta FROM tblHideUtilizationJDE");
            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityProg.this.progress_dialog.dismiss();
            if (ActivityProg.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityProg.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spPlantaProg.setAdapter(AD);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityProg.this);
            ActivityProg.this.progress_dialog.setMessage("Please wait...");
            ActivityProg.this.progress_dialog.setProgressStyle(0);
            ActivityProg.this.progress_dialog.setCancelable(false);
            ActivityProg.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityProg.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }

    private class GetOemPlanta extends AsyncTask<String, Void, String> {

        private GetOemPlanta() {}

        protected String doInBackground(String... paramVarArgs) {

            Oem("SELECT DISTINCT vwO.DescrProgama FROM dbo.tblHideUtilizationJDE AS tbl1 LEFT OUTER JOIN dbo.vwProgramas AS vwO ON tbl1.Programa = vwO.Programa WHERE tbl1.Planta = '"+plantas[pos]+"' ");

            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityProg.this.progress_dialog.dismiss();
            if (ActivityProg.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityProg.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spProgProg.setAdapter(AD1);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityProg.this);
            ActivityProg.this.progress_dialog.setMessage("Please wait...");
            ActivityProg.this.progress_dialog.setProgressStyle(0);
            ActivityProg.this.progress_dialog.setCancelable(false);
            ActivityProg.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityProg.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }


}
