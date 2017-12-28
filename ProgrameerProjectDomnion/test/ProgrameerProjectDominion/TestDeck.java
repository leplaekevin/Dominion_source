/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Pc_Kevin
 */
public class TestDeck {
    
    private Dominion spel;
private Speler speler;
private Deck drawDeck;
private ArrayList<String> spelers;
private ArrayList<String> gekozenVooraad;
        
@Before
public void setUp()
{
   //SETUP - ASSIGN
    spelers = new ArrayList();
    spelers.add("kevin");
    spelers.add("duncan");
    gekozenVooraad = new ArrayList();
    gekozenVooraad.add("basis");
    spel = new Dominion(spelers,gekozenVooraad);
    speler = spel.geefActieveSpeler();
   drawDeck = speler.geefDrawDeck();
}
        
@Test
public void steekKaartInDeck()
{
    System.out.println(drawDeck.geefDeckMetKaartNamen());
   drawDeck.steekKaartInDeck(new Kaart("koper"));
   System.out.println(drawDeck.geefDeckMetKaartNamen());
   String gevondenKaart = drawDeck.geefKaartnaamVan(5);
    assertEquals("koper", gevondenKaart);
}

@Test
public void neemEenKaartVanHetDeck()
{
   speler.neemKaartVanDeck();
   int grooteDrawDeck=speler.geefDrawDeck().geefDeckGroote();
   int GrooteHand=speler.geefGrooteHand();
   
    System.out.println(spel.geefVooraad().toString());
    assertEquals(4, grooteDrawDeck);
    assertEquals(6, GrooteHand);
    
}
    
}

