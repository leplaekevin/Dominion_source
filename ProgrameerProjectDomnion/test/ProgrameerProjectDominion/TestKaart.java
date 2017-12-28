/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import java.util.*;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Pc_Kevin
 */
public class TestKaart   {
    
    private JdbcTemplate template;
    private ArrayList testKaart;
    Kaart kaart = new Kaart("Slotgracht");
    private String  kost ;
    
    
    @Test
    public void zoekKost()
    {
        int gevondenId = kaart.geefId();
        int gevondenKost = kaart.geefKost();
        String gevondenNaam = kaart.geefKaartnaam();
        
        assertEquals(16, gevondenId);
        assertEquals(2, gevondenKost);
        assertEquals("slotgracht", gevondenNaam);
        
    }
    


}

