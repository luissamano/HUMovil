package com.leondev.humovil.Conexion;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by leondev on 26/07/17.
 */

public class CONN {

    @SuppressLint("NewApi")

    Connection conn = null;

    public Connection CONN()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnURL = null;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = ("jdbc:jtds:sqlserver://gsthum.gstautoleather.com/dbHideUtilization;instance=SQLEXPRESS;user=usrHideUt;password=usrHideUt2016");
            conn = DriverManager.getConnection(ConnURL);

        } catch (SQLException se) {
            Log.e("ERRO------>",se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO------>",e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO------>",e.getMessage());
        }

        return conn;
    }

    public void ExitConn ()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
