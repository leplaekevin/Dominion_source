/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion.JDBCTestPackage;

import ProgrameerProjectDominion.JdbcTemplate;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Gebruiker
 */
public class TestJDBC {

    public String tabel;
    public String kolom;
    public String kaartnaam;
    public String zoekwaarde;
    private JdbcTemplate template;
    public String tabelinsert;
    public String handid;
    public String aantalkaarten;
    public String aantepassenkaart;

    @Before
    public void setUp() {
        template = new JdbcTemplate();
        tabel = "kaarten";
        kolom = "Kaartnaam";
        kaartnaam = "Slotgracht";
        zoekwaarde = "kost";
        //test2
        tabelinsert = "hand";
        handid = "13";
        aantalkaarten = "5";
        aantepassenkaart = "slotgracht";

    }

    //@Test
    public void searchData() {
        try {
            ArrayList testData = template.DBselect(tabel, kolom, kaartnaam);
            String gevondenData = testData.get(1).toString();

            assertEquals("Slotgracht", gevondenData);
        } catch (Exception ex) {
            System.out.println("why does the catch show too");
        }

    }

    //@Test
    public void zetData() {
        // tabel naam, IDvanhand/deck , waarde/aantalkaarten,kaartnaam
        //bevoorbeeld hand,13,10,slotgracht
        try {
            template.DBInsert(tabelinsert, handid, aantalkaarten, kaartnaam);
            

        } catch (Exception ex) {
            System.out.println("error in zet data kaart size:" + template.kaartID.size());
          
        }

    }
@Test
    public void updatedata() {
        // tabel naam, IDvanhand/deck , waarde/aantalkaarten,kaartnaam
        //bevoorbeeld hand,13,10,slotgracht
        try {
            template.DBUpdateVoorraad("Slotgracht");   

            
        } 
        catch (Exception ex) {
            System.out.println("error in zet data kaart size:" + template.kaartID.size());
            

        }

    }
  
@Test
    public void testKostZoeken() {
        // tabel naam, IDvanhand/deck , waarde/aantalkaarten,kaartnaam
        //bevoorbeeld hand,13,10,slotgracht
        try {  
            ArrayList gevondenKaartKost = template.DBselectZoekenOpData(tabel, zoekwaarde, kolom, kaartnaam);
            String gevondenKost = gevondenKaartKost.get(1).toString();
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testKostZoeken: ");
            

        }

    }

@Test
    public void testInsertInAflegstapel(){
        try {  
            template.DBInsertAflegstapel("Slotgracht", 1);
            template.DBInsertAflegstapel("Avonturier",1);
            template.DBInsertAflegstapel("Provincie",2);
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testInsertInAflegStapel");
            

        }
    }

@Test
    public void testInsertInAftrekstapel(){
        try {  
            template.DBInsertAftrekstapel("Slotgracht",1);
            template.DBInsertAftrekstapel("Avonturier",1);
            template.DBInsertAftrekstapel("Provincie",1);
            template.DBInsertAftrekstapel("Houthakker",5);
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testInsertInAflegStapel");
            

        }
    }

@Test
    public void testInsertInHand(){
        try {  
            template.DBInsertHand(1,"Slotgracht");
            template.DBInsertHand(1, kaartnaam);
            template.DBInsertHand(2,"Avonturier");
            template.DBInsertHand(4,"Provincie");
            template.DBInsertHand(1,"Houthakker");
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testInsertInHand");
            

        }
    }

@Test
    public void testInsertInVoorraad(){
        try {  
            template.DBInsertVoorraad("Slotgracht");
            template.DBInsertVoorraad("Avonturier");
            template.DBInsertVoorraad("Provincie");
            template.DBInsertVoorraad("Houthakker");
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testInsertInVoorraad");
            

        }
    }
    
/*@Test
    public void testDelete(){
        try {  
            template.DBDelete("hand");
            template.DBDelete("aflegstapel");
            template.DBDelete("aftrekstapel");
            template.DBDelete("voorraad");
            
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testInsertInVoorraad");
            

        }
    }
*/
    @Test
    public void testInsertSpelersData(){
        try {  
            template.DBInsertSpelersDataInSpel("Duncan", 1, 2, 3, 4, 18);
            
            
            
        } 
        catch (Exception ex) {
            System.out.println("error in testSpelerDataInsert");
            

        }
    }

}



    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}


