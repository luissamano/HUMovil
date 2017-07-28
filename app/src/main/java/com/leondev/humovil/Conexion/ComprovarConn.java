package com.leondev.humovil.Conexion;

/**
 * Created by leondev on 26/07/17.
 */

public class ComprovarConn {

    private boolean AccesoInternet = false;

    public ComprovarConn() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            AccesoInternet = (val == 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isAccesoInternet() {
        return AccesoInternet;
    }
}
