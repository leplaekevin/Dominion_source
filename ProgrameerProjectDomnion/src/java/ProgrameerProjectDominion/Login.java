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
public class Login {

    //naam en wachtwoord
    //da moet naar den database
    private String gebruikersnaam;
    private String wachtwoord;
    private String sqlString;
    private ArrayList lijst;
    private boolean ingelogt;

    public boolean login(String naam, String wachtwoord) {
        //vaste variabelen veranderen door de ingevoerde gegevens
        this.gebruikersnaam = naam;
        this.wachtwoord = wachtwoord;

        //ingelogt op false zetten,zodra true word je ingelogt
        ingelogt = false;
        try {
            ingelogt = DBConnectSecure(gebruikersnaam, wachtwoord);
        } catch (Exception e) {
            System.out.println("fout voor secure connection" + e.getMessage());
        }
        return ingelogt;
    }

    public boolean DBConnectSecure(String naam, String wachtwoord) throws SQLException {
        sqlString = "SELECT * FROM Spelers WHERE naam = '" + naam + "' AND wachtwoord = '" + wachtwoord + "';";
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
//       dataSource.setUser("DEMK");
//        dataSource.setPassword("Laiko123");
//        dataSource.setPort(3306);
//        dataSource.setServerName("sql.egondebaene.be");
//        dataSource.setDatabaseName("DEMK");

        try {
            java.sql.Connection conn = dataSource.getConnection();
            System.out.println("connectie met database is gelukt");

            java.sql.Statement stmt = conn.createStatement();
            ResultSet databankgegevens = stmt.executeQuery(sqlString);

            ResultSetMetaData databankgegevensmd = databankgegevens.getMetaData();
            int aantalKolommen = databankgegevensmd.getColumnCount();

            lijst = new ArrayList<String>();
            while (databankgegevens.next()) {

                for (int i = 1; i <= aantalKolommen; i++) {
                    lijst.add(databankgegevens.getString(i));

                }

            }
            if (lijst.isEmpty()) {
                ingelogt = false;
            } else {
                ingelogt = true;
            }
            databankgegevens.close();
            stmt.close();
            System.out.println("select geslaagd");
        } catch (Exception ex) {
            System.out.println("Er ging iets miss of de gebruikersnaam bestaad al: " + ex.getMessage());
            //als je deze fout krijgt is de kans groot dat de gebruiker al bestaad
        }
        return ingelogt;
    }
}
