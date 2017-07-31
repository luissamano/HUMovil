package com.leondev.humovil.ConsultasSQL;

import android.util.Log;

import com.leondev.humovil.Conexion.CONN;
import com.leondev.humovil.Model.ModelYear;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by leondev on 29/07/17.
 */

public class UtilidadSQLmixto {

    private String Planta;
    private String Tipo;
    private String Inicio;
    private String Final;
    private int Opc;

    private String COMANDOSQL;
    private ResultSet rs;
    private CONN connectionClass;


    public UtilidadSQLmixto(String _planta, String _tipo, String _inicio, String _final, int _opc) {
        Planta = _planta;
        Tipo = _tipo;
        Inicio = _inicio;
        Final = _final;
        this.Opc = _opc;


        if (Opc==0) {
            COMANDOSQL = "SELECT Planta, Año, Cueros, HUtilizationVar FROM ( SELECT Planta, SUM(Cueros) AS Cueros, Año, CASE WHEN SUM(ft2) = 0 THEN 0 ELSE ((SUM(netft2) / SUM(ft2)) * 100) END AS Porcentage, SUM([Bud Footage]) AS BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT Planta, DATEPART(Year, Fecha) AS Año, SUM(Cueros) AS Cueros, SUM(FT2) AS Ft2, CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) AS Avg, BHTcuero, CAST(SUM(FT2) / SUM(Cueros) - BHTcuero AS dec(18, 2)) AS [H Size Var], BHcosto, CAST((CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) - BHTcuero) * BHcosto * SUM(Cueros) AS dec(18, 2)) AS [H. Size Var $], SUM(NetFT2) AS Netft2, CAST(SUM(NetFT2) / SUM(FT2) * 100 AS dec(18, 2)) AS AHU, BHU, CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END AS [Bud Footage], CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2) AS [Footage Var], CAST((CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2)) * BHcosto AS dec(18, 2)) AS [H. utilization $ Var] FROM dbo.tblHideUtilizationJDE WHERE Fecha BEtween '"+_inicio+"' AND '"+_final+"' AND Planta = '"+_planta+"' GROUP BY Planta, Año, Fecha, BHTcuero, BHcosto, BHU) AS p GROUP BY Planta, Año) AS Final";
        } else if (Opc==1) {
            COMANDOSQL = "SELECT Planta, Año, Cueros, HUtilizationVar FROM ( SELECT Planta, SUM(Cueros) AS Cueros, Año, CASE WHEN SUM(ft2) = 0 THEN 0 ELSE ((SUM(netft2) / SUM(ft2)) * 100) END AS Porcentage, SUM([Bud Footage]) AS BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT Planta, DATEPART(Year, Fecha) AS Año, SUM(Cueros) AS Cueros, SUM(FT2) AS Ft2, CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) AS Avg, BHTcuero, CAST(SUM(FT2) / SUM(Cueros) - BHTcuero AS dec(18, 2)) AS [H Size Var], BHcosto, CAST((CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) - BHTcuero) * BHcosto * SUM(Cueros) AS dec(18, 2)) AS [H. Size Var $], SUM(NetFT2) AS Netft2, CAST(SUM(NetFT2) / SUM(FT2) * 100 AS dec(18, 2)) AS AHU, BHU, CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END AS [Bud Footage], CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2) AS [Footage Var], CAST((CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2)) * BHcosto AS dec(18, 2)) AS [H. utilization $ Var] FROM dbo.tblHideUtilizationJDE WHERE Fecha BEtween '"+_inicio+"' AND '"+_final+"' AND Planta = '"+_planta+"' GROUP BY Planta, Año, Fecha, BHTcuero, BHcosto, BHU) AS p GROUP BY Planta, Año) AS Final";
        } else if (Opc==2) {
            COMANDOSQL = "SELECT Planta, Año, Cueros, HUtilizationVar FROM ( SELECT Planta, SUM(Cueros) AS Cueros, Año, CASE WHEN SUM(ft2) = 0 THEN 0 ELSE ((SUM(netft2) / SUM(ft2)) * 100) END AS Porcentage, SUM([Bud Footage]) AS BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT Planta, DATEPART(Year, Fecha) AS Año, SUM(Cueros) AS Cueros, SUM(FT2) AS Ft2, CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) AS Avg, BHTcuero, CAST(SUM(FT2) / SUM(Cueros) - BHTcuero AS dec(18, 2)) AS [H Size Var], BHcosto, CAST((CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) - BHTcuero) * BHcosto * SUM(Cueros) AS dec(18, 2)) AS [H. Size Var $], SUM(NetFT2) AS Netft2, CAST(SUM(NetFT2) / SUM(FT2) * 100 AS dec(18, 2)) AS AHU, BHU, CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END AS [Bud Footage], CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2) AS [Footage Var], CAST((CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2)) * BHcosto AS dec(18, 2)) AS [H. utilization $ Var] FROM dbo.tblHideUtilizationJDE WHERE Fecha BEtween '"+_inicio+"' AND '"+_final+"' AND Planta = '"+_planta+"' GROUP BY Planta, Año, Fecha, BHTcuero, BHcosto, BHU) AS p GROUP BY Planta, Año) AS Final";
        } else if (Opc==3) {
            COMANDOSQL = "SELECT Planta, Año, Cueros, HUtilizationVar FROM ( SELECT Planta, SUM(Cueros) AS Cueros, Año, CASE WHEN SUM(ft2) = 0 THEN 0 ELSE ((SUM(netft2) / SUM(ft2)) * 100) END AS Porcentage, SUM([Bud Footage]) AS BFootage, SUM([H. utilization $ Var]) AS HUtilizationVar FROM ( SELECT Planta, DATEPART(Year, Fecha) AS Año, SUM(Cueros) AS Cueros, SUM(FT2) AS Ft2, CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) AS Avg, BHTcuero, CAST(SUM(FT2) / SUM(Cueros) - BHTcuero AS dec(18, 2)) AS [H Size Var], BHcosto, CAST((CAST(SUM(FT2) / SUM(Cueros) AS dec(18, 2)) - BHTcuero) * BHcosto * SUM(Cueros) AS dec(18, 2)) AS [H. Size Var $], SUM(NetFT2) AS Netft2, CAST(SUM(NetFT2) / SUM(FT2) * 100 AS dec(18, 2)) AS AHU, BHU, CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END AS [Bud Footage], CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2) AS [Footage Var], CAST((CASE WHEN BHU = 0 THEN 0 ELSE CAST((SUM(netft2) / BHU) * 100 AS dec(18, 2)) END - SUM(FT2)) * BHcosto AS dec(18, 2)) AS [H. utilization $ Var] FROM dbo.tblHideUtilizationJDE WHERE Fecha BEtween '"+_inicio+"' AND '"+_final+"' AND Planta = '"+_planta+"' GROUP BY Planta, Año, Fecha, BHTcuero, BHcosto, BHU) AS p GROUP BY Planta, Año) AS Final";
        }//if-else

    }

    public void UtilidadSql () {

        connectionClass = new CONN();
        try {

            Connection conn = connectionClass.CONN();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(this.COMANDOSQL);

            while(rs.next())
            {
                ModelYear.setPlanta(rs.getString("Planta"));
                ModelYear.setAño(rs.getInt("Año"));
                ModelYear.setCueros(rs.getFloat("Cueros"));
                ModelYear.setHUtilizationVar(rs.getFloat("HUtilizationVar"));

            }

        }catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception ex){
            Log.e("UtilidadSQLplanta",ex.getMessage());
        }
    }

}
