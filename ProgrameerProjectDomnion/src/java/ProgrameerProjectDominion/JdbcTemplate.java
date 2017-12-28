
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcTemplate {

    public ArrayList<String> lijst;
    public ArrayList<String> kaartID;
    public ArrayList<String> resultselect;
    public ArrayList<String> Naam;

    
    //select Functies   
    //TODO:¨Prepare statement voor injectie te voorkomen
    public ArrayList DBselect(String tabel, String kolom, String waarde) {
        try {
            String select = "SELECT * FROM " + tabel + " WHERE " + kolom + " = '" + waarde + "'";
            resultselect = DBconnect(select, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectSpelerCheck(String naam) {
        try {
            String select = "SELECT Naam FROM spelers WHERE Naam = '" + naam + "'";
            resultselect = DBconnect(select, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectZoekenOpData(String tabel, String zoekwaarde, String kolom, String waarde) {
        try {
            String select = "SELECT " + zoekwaarde + " FROM " + tabel + " WHERE " + kolom + " = '" + waarde + "'";
            resultselect = DBconnect(select, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    //insert functies
    public void DBInsert(String tabel, String ID, String waarde, String kaartnaam) {

        try {
            String selectvoorupdateString = "SELECT * FROM Kaarten WHERE Kaartnaam = '" + kaartnaam + "'";

            kaartID = DBconnect(selectvoorupdateString, true);
            String updateString = "INSERT INTO " + tabel + " VALUES (" + ID + "," + kaartID.get(0) + "," + waarde + ");";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    public void DBInsertSpelersDataInSpel(String speler, int IDAflegstapel, int IDAftrekstapel, int IDHand, int Actiepunten, int Kooppunten) {

        try {
            String selectvoorupdateString = "SELECT * FROM spelers WHERE Naam = '" + speler + "'";

            Naam = DBconnect(selectvoorupdateString, true);

            String updateString = "INSERT INTO spelersinspel (Naam,IDAflegstapel,IDAftrekstapel,IDHand,Actiepunten,Kooppunten) VALUES ('" + Naam.get(0) + "'," + IDAflegstapel + "," + IDAftrekstapel + "," + IDHand + "," + Actiepunten + "," + Kooppunten + ");";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    public void DBInsertAflegstapel(String kaartnaam, int aflegstapelnr) {

        try {
            String selectvoorupdateString = "SELECT * FROM kaarten WHERE Kaartnaam = '" + kaartnaam + "'";

            kaartID = DBconnect(selectvoorupdateString, true);
            String updateString = "INSERT INTO aflegstapel (IDKaart,IDAflegstapel) VALUES (" + kaartID.get(0) + "," + aflegstapelnr + ");";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    public void DBInsertAftrekstapel(String kaartnaam, int aftrekstapelnr) {

        try {
            String selectvoorupdateString = "SELECT * FROM kaarten WHERE Kaartnaam = '" + kaartnaam + "'";

            kaartID = DBconnect(selectvoorupdateString, true);
            String updateString = "INSERT INTO aftrekstapel (IDKaart,IDAftrekstapel) VALUES (" + kaartID.get(0) + "," + aftrekstapelnr + ");";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    public void DBInsertHand(int handnr, String kaartnaam) {

        try {
            String selectvoorupdateString = "SELECT * FROM kaarten WHERE Kaartnaam = '" + kaartnaam + "'";

            kaartID = DBconnect(selectvoorupdateString, true);
            String updateString = "INSERT INTO hand(IDHand, IDKaart) VALUES (" + handnr + ", " + kaartID.get(0) + ");";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    public void DBInsertVoorraad(String kaartnaam) {

        try {
            String selectvoorupdateString = "SELECT * FROM kaarten WHERE Kaartnaam = '" + kaartnaam + "'";

            kaartID = DBconnect(selectvoorupdateString, true);
            String updateString = "INSERT INTO voorraad (IDKaart,AantalKaarten) VALUES (" + kaartID.get(0) + ",10);";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    // Update functies
    public void DBUpdate(String tabel, String ID, String waarde) {
        DBUpdate(tabel, ID, waarde, null);
    }

    public void DBUpdate(String tabel, String ID, String waarde, String kaartnaam) {

        try {
            if (kaartnaam != null) {
                String selectvoorupdateString = "SELECT * FROM Kaarten WHERE Kaartnaam = '" + kaartnaam + "'";
                kaartID = DBconnect(selectvoorupdateString, true);
            }
            String updateString = "UPDATE " + tabel + " SET column1=value1,column2=value2 WHERE some_column=some_value;";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }

    public void DBUpdateVoorraad(String kaartnaam) {

        try {
            if (kaartnaam != null) {
                String selectvoorupdateString = "SELECT * FROM Kaarten WHERE Kaartnaam = '" + kaartnaam + "'";
                kaartID = DBconnect(selectvoorupdateString, true);
            }
            String updateString = "UPDATE voorraad SET AantalKaarten= AantalKaarten-1 WHERE IDKaart='" + kaartID.get(0) + "';";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in insert" + e.getMessage());
        }
    }
    
    //delete functies

    public void DBDelete(String tabelnaam) {
        try {

            String updateString = "DELETE FROM " + tabelnaam + ";";
            DBconnect(updateString, false);

        } catch (Exception e) {
            System.out.println("fout in delete" + e.getMessage());
        }
    }

// connectie naar DB
    public ArrayList DBconnect(String sqlString, boolean BoolSelect) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Driver loaded!");

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver niet gevonden, toevoegen via de properties: " + ex.getMessage());
        }
        MysqlDataSource dataSource = new MysqlDataSource();
//        //gegevens van de database
        dataSource.setUser("Duncan");
        dataSource.setPassword("duncan");
        dataSource.setPort(3306);
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("projecthowest");
       // gegevens van de database
//        dataSource.setUser("DEMK");
//        dataSource.setPassword("Laiko123");
//        dataSource.setPort(3306);
//        dataSource.setServerName("sql.egondebaene.be");
//        dataSource.setDatabaseName("DEMK");
        try {
            java.sql.Connection conn = dataSource.getConnection();
            //System.out.println("connectie met database is gelukt");

            java.sql.Statement stmt = conn.createStatement();
            if (BoolSelect) {
                ResultSet databankgegevens = stmt.executeQuery(sqlString);

                ResultSetMetaData databankgegevensmd = databankgegevens.getMetaData();
                int aantalKolommen = databankgegevensmd.getColumnCount();

                lijst = new ArrayList<String>();
                while (databankgegevens.next()) {

                    for (int i = 1; i <= aantalKolommen; i++) {
                        lijst.add(databankgegevens.getString(i));

                    }

                }
                databankgegevens.close();
                stmt.close();
                //System.out.println("select geslaagd");
            } else {
                stmt.executeUpdate(sqlString);
                //System.out.println("query geslaagd");
                //sluiten van statement
                stmt.close();
            }
            //sluiten van connectie
            conn.close();
        } catch (Exception ex) {
            System.out.println("Dataconnectie gefaald: " + ex.getMessage());
        }
        
        return lijst;
    }
}
