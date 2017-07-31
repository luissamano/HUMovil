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
import com.leondev.humovil.ConsultasSQL.UtilidadSQLmixto;
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

import static android.R.attr.columnCount;
import static android.R.attr.rowCount;
import static com.leondev.humovil.R.id.lvHU;

public class ActivityPlanta
        extends AppCompatActivity{

    int Opc = 0;
    private String plantas[];
    private String resultado = "";

    private Spinner spPlanta;
    private EditText etInicioPlanta;
    private EditText etFinalPlanta;
    private Button btnBuscarPlanta;
    private ListView lvHU;
    private ArrayList<ModelUtilization> datos;

    SimpleAdapter AD;
    private ProgressDialog progress_dialog;
    private CONN connectionClass;


    String pla;
    int año;
    float cueros;
    float var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);


        spPlanta = (Spinner) this.findViewById(R.id.spPlanta);
        etInicioPlanta = (EditText) this.findViewById(R.id.etInicioPlanta);
        etFinalPlanta = (EditText) this.findViewById(R.id.etFinalPlanta);
        btnBuscarPlanta = (Button) this.findViewById(R.id.btnBuscarPlanta);
        lvHU = (ListView) this.findViewById(R.id.lvHU);

        btnBuscarPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String planta = plantas[spPlanta.getSelectedItemPosition()];
                String inicio = etInicioPlanta.getText().toString();
                String fin = etFinalPlanta.getText().toString();
                int opc = 0;

                GetUtilidad hiloUtilidad = new GetUtilidad(planta,inicio,fin,opc);
                hiloUtilidad.execute();

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

            //new UtilidadSQLmixto(planta,inicio,fin,opc).Utilidadsql();

            pla = ModelYear.getPlanta();
            año = ModelYear.getAño();
            cueros = ModelYear.getCueros();
            var = ModelYear.getHUtilizationVar();

            String pla = ModelYear.getPlanta();
            int año = ModelYear.getAño();
            float cueros = ModelYear.getCueros();
            float var = ModelYear.getHUtilizationVar();
            System.out.append("\nPlanta"+pla+"\nAño"+año+"\nCueros"+cueros+"\nHU"+var+"");


            datos = new ArrayList<ModelUtilization>();

            datos.add(new ModelUtilization(R.drawable.money, 0.0, cueros, var));

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


                UtilizationAdapter adapter;
                adapter = new UtilizationAdapter(getApplicationContext(), datos);
                lvHU.setAdapter(adapter);

                //System.out.append("\nPlanta"+pla+"\nAño"+año+"\nCueros"+cueros+"\nHU"+var+"");
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
