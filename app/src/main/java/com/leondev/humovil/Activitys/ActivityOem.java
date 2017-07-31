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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.leondev.humovil.Adapter.UtilizationAdapter;
import com.leondev.humovil.Conexion.CONN;
import com.leondev.humovil.ConsultasSQL.UtilidadSQLplanta;
import com.leondev.humovil.Model.ModelUtilization;
import com.leondev.humovil.Model.ModelYear;
import com.leondev.humovil.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityOem extends AppCompatActivity {


    private SimpleAdapter AD, AD1;
    private ArrayList<ModelUtilization> datos;
    private CONN connectionClass;

    private String pla;
    private int año;
    private float cueros;
    private float var;
    private String plantas[];
    private String oems[];
    private int pos;
    private String resultado = "";

    private Spinner spPlantaOem;
    private Spinner spOemOem;
    private EditText etInicioOem;
    private EditText etFinalOem;
    private Button btnBuscarOem;
    private ListView lvHUoem;
    private ProgressDialog progress_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oem);

        spPlantaOem = (Spinner) this.findViewById(R.id.spPlantaOem);
        spOemOem = (Spinner) this.findViewById(R.id.spOemOem);
        etInicioOem = (EditText) this.findViewById(R.id.etInicioOem);
        etFinalOem = (EditText) this.findViewById(R.id.etFinalOem);
        btnBuscarOem = (Button) this.findViewById(R.id.btnBuscarOem);


        spPlantaOem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos = spPlantaOem.getSelectedItemPosition();
                GetOemPlanta hilo = new GetOemPlanta();
                hilo.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spOemOem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBuscarOem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String planta = plantas[spPlantaOem.getSelectedItemPosition()];
                String oem = oems[spOemOem.getSelectedItemPosition()];
                String inicio = etInicioOem.getText().toString();
                String fin = etFinalOem.getText().toString();




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

                oems = new String[15];

                while (rs.next()) {

                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("A", rs.getString("DescrOem"));
                    data.add(datanum);

                    String[] from = {"A"};
                    int[] views = {R.id.tvMod};
                    AD1 = new SimpleAdapter(this.getBaseContext(), data, R.layout.tvmodel, from, views);
                    //spPlanta.setAdapter(AD);

                    oems[i] = rs.getString("DescrOem");
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
            ActivityOem.this.progress_dialog.dismiss();
            if (ActivityOem.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityOem.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spPlantaOem.setAdapter(AD);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityOem.this);
            ActivityOem.this.progress_dialog.setMessage("Please wait...");
            ActivityOem.this.progress_dialog.setProgressStyle(0);
            ActivityOem.this.progress_dialog.setCancelable(false);
            ActivityOem.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityOem.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }

    private class GetOemPlanta extends AsyncTask<String, Void, String> {

        private GetOemPlanta() {}

        protected String doInBackground(String... paramVarArgs) {

            Oem("SELECT  DISTINCT vwO.DescrOem FROM dbo.tblHideUtilizationJDE AS tbl1 LEFT OUTER JOIN dbo.vwOems AS vwO ON tbl1.Oem = vwO.OEM WHERE tbl1.Planta ='"+plantas[pos]+"' ");
            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityOem.this.progress_dialog.dismiss();
            if (ActivityOem.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityOem.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {
                spOemOem.setAdapter(AD1);
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityOem.this);
            ActivityOem.this.progress_dialog.setMessage("Please wait...");
            ActivityOem.this.progress_dialog.setProgressStyle(0);
            ActivityOem.this.progress_dialog.setCancelable(false);
            ActivityOem.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityOem.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }




    private class GetUtilidad extends AsyncTask<String, Void, String> {

        private String planta;
        private String inicio;
        private String fin;
        private int opc;

        public GetUtilidad(String planta, String inicio, String fin, int opc) {
            this.planta = planta;
            this.inicio = inicio;
            this.fin = fin;
            this.opc = opc;
        }

        protected String doInBackground(String... paramVarArgs) {

            new UtilidadSQLplanta(planta,inicio,fin,opc).Utilidadsql();

            pla = ModelYear.getPlanta();
            año = ModelYear.getAño();
            cueros = ModelYear.getCueros();
            var = ModelYear.getHUtilizationVar();

            datos = new ArrayList<ModelUtilization>();
            datos.add(new ModelUtilization(R.drawable.money, 0.0, cueros, var));

            return "Ready!!";

        }

        protected void onPostExecute(String paramString) {
            ActivityOem.this.progress_dialog.dismiss();
            if (ActivityOem.this.resultado.equals("ERROR DE CONEXION"))
            {
                Toast.makeText(ActivityOem.this.getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("------->", paramString);
            if (!paramString.equals("Nologin"))
            {


                UtilizationAdapter adapter;
                adapter = new UtilizationAdapter(getApplicationContext(), datos);
                lvHUoem.setAdapter(adapter);

                //System.out.append("\nPlanta"+pla+"\nAño"+año+"\nCueros"+cueros+"\nHU"+var+"");
                return;
            }
        }

        protected void onPreExecute() {
            progress_dialog = new ProgressDialog(ActivityOem.this);
            ActivityOem.this.progress_dialog.setMessage("Please wait...");
            ActivityOem.this.progress_dialog.setProgressStyle(0);
            ActivityOem.this.progress_dialog.setCancelable(false);
            ActivityOem.this.progress_dialog.show();
        }

        protected void onProgressUpdate(Void... paramVarArgs) {
            Toast.makeText(ActivityOem.this.getApplicationContext(), "Progreso del hilo", Toast.LENGTH_LONG).show();
        }

    }

}
