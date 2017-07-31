package com.leondev.humovil.Model;

/**
 * Created by leondev on 31/07/17.
 */

public class ModelUtilization {

    private int drawImg;
    private double Meta;
    private double Cueros;
    private double HUtilizationVar;

    public ModelUtilization(int drawImg, double meta, double cueros, double HUtilizationVar) {
        this.drawImg = drawImg;
        this.Meta = meta;
        this.Cueros = cueros;
        this.HUtilizationVar = HUtilizationVar;
    }

    public int getDrawImg() {
        return drawImg;
    }

    public void setDrawImg(int drawImg) {
        this.drawImg = drawImg;
    }

    public double getMeta() {
        return Meta;
    }

    public void setMeta(double meta) {
        Meta = meta;
    }

    public double getCueros() {
        return Cueros;
    }

    public void setCueros(double cueros) {
        Cueros = cueros;
    }

    public double getHUtilizationVar() {
        return HUtilizationVar;
    }

    public void setHUtilizationVar(double HUtilizationVar) {
        this.HUtilizationVar = HUtilizationVar;
    }

}
