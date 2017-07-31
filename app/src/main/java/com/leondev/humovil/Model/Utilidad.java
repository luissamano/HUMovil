package com.leondev.humovil.Model;

/**
 * Created by leondev on 29/07/17.
 */

public class Utilidad {

    private static String Planta;
    private String Inicio;
    private String Final;
    private double Cueros;
    private double Porcentaje;
    private double HUtilizationVar;


    public Utilidad(String planta, String inicio, String aFinal, double cueros, double porcentaje, double HUtilizationVar)
    {
        Planta = planta;
        Inicio = inicio;
        Final = aFinal;
        Cueros = cueros;
        Porcentaje = porcentaje;
        this.HUtilizationVar = HUtilizationVar;
    }

    public String getPlanta() {
        return Planta;
    }

    public static void setPlanta(String planta) {
        Planta = planta;
    }

    public String getInicio() {
        return Inicio;
    }

    public void setInicio(String inicio) {
        Inicio = inicio;
    }

    public String getFinal() {
        return Final;
    }

    public void setFinal(String aFinal) {
        Final = aFinal;
    }

    public double getCueros() {
        return Cueros;
    }

    public void setCueros(double cueros) {
        Cueros = cueros;
    }

    public double getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        Porcentaje = porcentaje;
    }

    public double getHUtilizationVar() {
        return HUtilizationVar;
    }

    public void setHUtilizationVar(double HUtilizationVar) {
        this.HUtilizationVar = HUtilizationVar;
    }
}
