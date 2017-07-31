package com.leondev.humovil.Model;

/**
 * Created by leondev on 30/07/17.
 */

public class ModelYear {

    private static String Planta ;
    private static int Año;
    private static float Cueros;
    private static float tilizationVar;

    public ModelYear(String planta, int año, float cueros, float HUtilizationVar) {
        this.Planta = planta;
        this.Año = año;
        this.Cueros = cueros;
        this.tilizationVar = HUtilizationVar;
    }

    public static String getPlanta() {
        return Planta;
    }

    public static void setPlanta(String planta) {
        Planta = planta;
    }

    public static int getAño() {
        return Año;
    }

    public static void setAño(int año) {
        Año = año;
    }

    public static float getCueros() {
        return Cueros;
    }

    public static void setCueros(float cueros) {
        Cueros = cueros;
    }

    public static float getHUtilizationVar() {
        return tilizationVar;
    }

    public static void setHUtilizationVar(float HUtilizationVar) {
        tilizationVar = HUtilizationVar;
    }

}
