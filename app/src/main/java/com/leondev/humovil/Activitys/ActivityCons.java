package com.leondev.humovil.Activitys;

import android.app.Activity;
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

public class ActivityCons extends AppCompatActivity {

    private Spinner spPlantaCons;
    private Spinner spConsCons;
    private EditText etInicioCons;
    private EditText etFinalCons;
    private Button btnBuscarCons;

    private SimpleAdapter AD, AD1;
    private ProgressDialog progress_dialog;
    private CONN connectionClass;

    private String plantas[];
    private String cons[];
    private int pos;
    private String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons);

        spPlantaCons = (Spinner) this.findViewById(R.id.spPlantaCons);
        spConsCons = (Spinner) this.findViewById(R.id.spConsCons);

        spPlantaCons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getId()) {

                    case R.id.spPlantaCons:
                            pos = spPlantaCons.getSelectedItemPosition();
                            GetConsPlanta hilo = new GetConsPlanta();
                            hilo.execute();
                        break;

                    case R.id.spConsCons:

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spConsCons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

    public void Cons(String COMANDOSQL) {
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

                cons = new String[15];

                while (rs.next()) {

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString("DescrConstruccion"));
                    data.add(datanum);

                    String[] from = {"A"};
                    int[] views = {R.id.tvMod};
                    AD1 = new SimpleAdapter(this.getBaseContext(), data, R.layout.tvmodel, from, views);
                    //spPlanta.setAdapter(AD);

                    cons[i] = rs.getString("DescrConstruccion");
                    i++;

                }//while(rs.next())


            }//else


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e){
            Log.e("ERROR_Cons_Cons",e.getMessage());
        }
    }


    private class GetDataPlanta extends AsyncTask<String, Void, String> {

        private GetDataPlanta() {}

        protected String doInBackground(String... paramVarArgs) {

            Planta("SELECT DISTINCT Planta FROM tblHideUtilizationJDE");
            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityCons.this.progress_dialog.dismiss();
            if (ActivityCons.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityCons.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spPlantaCons.setAdapter(AD);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityCons.this);
            ActivityCons.this.progress_dialog.setMessage("Please wait...");
            ActivityCons.this.progress_dialog.setProgressStyle(0);
            ActivityCons.this.progress_dialog.setCancelable(false);
            ActivityCons.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityCons.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }

    private class GetConsPlanta extends AsyncTask<String, Void, String> {

        private GetConsPlanta() {}

        protected String doInBackground(String... paramVarArgs) {

            Cons("SELECT  DISTINCT vwO.DescrConstruccion FROM  dbo.tblHideUtilizationJDE AS tbl1 LEFT OUTER JOIN dbo.vwConstrucciones AS vwO ON tbl1.Construccion = vwO.Construccion WHERE tbl1.Planta = '"+plantas[pos]+"' ");

            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityCons.this.progress_dialog.dismiss();
            if (ActivityCons.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityCons.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spConsCons.setAdapter(AD1);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityCons.this);
            ActivityCons.this.progress_dialog.setMessage("Please wait...");
            ActivityCons.this.progress_dialog.setProgressStyle(0);
            ActivityCons.this.progress_dialog.setCancelable(false);
            ActivityCons.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityCons.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }

}
