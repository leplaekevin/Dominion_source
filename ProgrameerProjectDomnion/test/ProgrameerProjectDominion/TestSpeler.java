/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Pc_Kevin
 */
public class TestSpeler {
    private Dominion spel;
    private Speler spelerEen;
    private int handGroote;
    private int drawDeckGroote;
    private int drawDeckStartGroote;
    private Deck spelerDrawDeck;
    private Deck spelerFoldDeck;
    private int grooteStartHand;
    private ArrayList <String> spelers;
    private ArrayList <String> gekozenVooraad;

    @Before
    public void setUp()
    {
        spelers = new ArrayList();
        spelers.add("kevin");
        spelers.add("Ducan");
        gekozenVooraad = new ArrayList();
        gekozenVooraad.add("basis");
        spel = new Dominion(spelers,gekozenVooraad);
        spelerEen = spel.geefSpelerEén();
        spelerDrawDeck = spelerEen.geefDrawDeck();
        spelerFoldDeck=spelerEen.geefFoldDeck();
        grooteStartHand = 5 ;
        drawDeckStartGroote = 5;
    }

    @Test
    public void neemEenKaartVanHetDeck()
    {
        spelerEen.neemKaartVanDeck();
        handGroote = spelerEen.geefGrooteHand();
        drawDeckGroote = spelerDrawDeck.geefDeckGroote();
        
        assertEquals(grooteStartHand+1, handGroote);
        assertEquals(drawDeckStartGroote-1,drawDeckGroote);
    }
    
    
    @Test
    public void eindigBeurt()
    {
        ArrayList foldDeckNaBeurtEén = spelerFoldDeck.geefDeck();
        spelerEen.eindigBeurt();

        //System.out.println("eind:fold deck"+spelerEen.geefFoldDeck().geefDeck());
        //System.out.println("eind:draw deck"+spelerEen.geefDrawDeck().geefDeck());
        //System.out.println("eind:inhoud hand"+spelerEen.geefHand().geefInhoudHand());

        
        assertEquals(5, foldDeckNaBeurtEén.size());
        assertEquals(grooteStartHand, spelerEen.geefGrooteHand());//is 1
  
    }
    
    @Test
    public void speelDrieBeurten()
    {
        int VerwachtfoldDeckNaDrieBeurten =6;
        int VerwachtdrawDeckNaDrieBeurten =2;
        int VerwachtHandNaDrieBeurten =5; 
 
        
        System.out.println("start:fold deck"+spelerEen.geefFoldDeck().geefDeckMetKaartNamen());
        System.out.println("start:draw deck"+spelerEen.geefDrawDeck().geefDeckMetKaartNamen());
        System.out.println("start:inhoud hand"+spelerEen.geefHand().geefDeckMetKaartNamen());
        System.out.println();
        
        String kaartnaam = "koper";
        spel.koopKaart(kaartnaam);
        spelerEen.eindigBeurt();
        
               
        System.out.println("eind:fold deck"+spelerEen.geefFoldDeck().geefDeckMetKaartNamen());
        System.out.println("eind:draw deck"+spelerEen.geefDrawDeck().geefDeckMetKaartNamen());
        System.out.println("eind:inhoud hand"+spelerEen.geefHand().geefDeckMetKaartNamen());
        System.out.println();
        
        spel.koopKaart(kaartnaam);
        spelerEen.eindigBeurt();
       
       
        System.out.println("eind:fold deck"+spelerEen.geefFoldDeck().geefDeckMetKaartNamen());
        System.out.println("eind:draw deck"+spelerEen.geefDrawDeck().geefDeckMetKaartNamen());
        System.out.println("eind:inhoud hand"+spelerEen.geefHand().geefDeckMetKaartNamen());
                System.out.println();
        
        spel.koopKaart(kaartnaam);
        spelerEen.eindigBeurt();
       
        System.out.println("eind:fold deck"+spelerEen.geefFoldDeck().geefDeckMetKaartNamen());
        System.out.println("eind:draw deck"+spelerEen.geefDrawDeck().geefDeckMetKaartNamen());
        System.out.println("eind:inhoud hand"+spelerEen.geefHand().geefDeckMetKaartNamen());

        int foldDeckNaDrieBeurten =spelerEen.geefFoldDeck().geefDeckGroote();
        int drawDeckNaDrieBeurten =spelerEen.geefDrawDeck().geefDeckGroote();
        int HandNaDrieBeurten =spelerEen.geefHand().geefDeck().size();
        
        assertEquals(VerwachtfoldDeckNaDrieBeurten, foldDeckNaDrieBeurten);
        assertEquals(VerwachtdrawDeckNaDrieBeurten, drawDeckNaDrieBeurten);
        assertEquals(VerwachtHandNaDrieBeurten, HandNaDrieBeurten);
        
    }
}
