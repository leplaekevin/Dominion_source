/*
 * Made and owned by Egon De Baene.
 */
package ProgrameerProjectDominion;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egond
 */
public class Registratie {

    //naam en wachtwoord
    //da moet naar den database
    private String gebruikersnaam;
    private String wachtwoord;
    private String sqlString;
    private boolean registratiegelukt;

    public boolean Registratie(String naam,String wachtwoord) {
        //vaste variabelen veranderen door de ingevoerde gegevens
        gebruikersnaam = naam;
        this.wachtwoord = wachtwoord;
        try {
            DBConnectSecure(gebruikersnaam, wachtwoord);
        } catch (Exception e) {
            System.out.println("fout voor secure connection" + e.getMessage());
        }
        return registratiegelukt;
    }

    public void DBConnectSecure(String naam, String wachtwoord) throws SQLException {
        sqlString = "INSERT INTO Spelers VALUES ('" + naam + "','" + wachtwoord + "');";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver niet gevonden, toevoegen via de properties: " + ex.getMessage());
        }
        MysqlDataSource dataSource = new MysqlDataSource();
        //gegevens van de database
        dataSource.setUser("Duncan");
        dataSource.setPassword("duncan");
        dataSource.setPort(3306);
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("projecthowest");

        try {
            java.sql.Connection conn = dataSource.getConnection();
            System.out.println("connectie met database is gelukt");

            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            System.out.println("query geslaagd");
            registratiegelukt = true;
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Er ging iets mis of de gebruikersnaam bestaat al: " + ex.getMessage());
            registratiegelukt = false;
            //als je deze fout krijgt is de kans groot dat de gebruiker al bestaad
        }
    }
}
