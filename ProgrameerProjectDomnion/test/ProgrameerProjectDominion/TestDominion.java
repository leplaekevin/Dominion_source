package ProgrameerProjectDominion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Pc_Kevin
 */
public class TestDominion {
        private Dominion spelMetTwee;
        private ArrayList <String> spelers;
        private ArrayList <String> gekozenVooraad;
        private Dominion spelMetDrie;
        private Dominion spelMetVier;
        private Dominion preSetDominion;
        private ArrayList <String> preSetVooraadGeld;
        private  int verwachteGrooteVooraad;
        
@Before
public void setUp()
{
   //SETUP - ASSIGN
    spelers = new ArrayList();
    spelers.add("Kevin");
    spelers.add("Egon");
   gekozenVooraad = new ArrayList();
   gekozenVooraad.add("basis");
   preSetVooraadGeld = new ArrayList();
   preSetVooraadGeld.add("geld");
   spelMetTwee= new Dominion(spelers,gekozenVooraad);
   spelers.add("Duncan");
   spelMetDrie= new Dominion(spelers,gekozenVooraad);
   spelers.add("Michiel");
   spelMetVier= new Dominion(spelers,gekozenVooraad);
   gekozenVooraad = new ArrayList();
   gekozenVooraad.add("basis");
   preSetDominion = new Dominion(spelers, preSetVooraadGeld);
   verwachteGrooteVooraad = 17;
}

@Test
public void spelMetPreSetVooraad()
{
    System.out.println("voooraad: "+preSetDominion.geefVooraad().geefDeckMetKaartNamen());
    
    
    assertEquals(verwachteGrooteVooraad, preSetDominion.geefVooraad().geefDeck().size());
}


@Test
public void spelMetMeerdereSpelersOpZetten()
{
   String gevondenSpelerUitSpelMetTwee = spelMetTwee.geefActieveSpeler().geefNaam();
   //String onbestaandeSpelerUitSpelMetDrie = spelMetDrie.geefSpelerVier().geefNaam();
    String onbestaandeSpelerUitSpelMetVier = spelMetVier.geefSpelerVier().geefNaam();
   
    assertEquals("Kevin", gevondenSpelerUitSpelMetTwee);
    //assertNull(onbestaandeSpelerUitSpelMetDrie);
    assertEquals("Michiel", onbestaandeSpelerUitSpelMetVier);
}

@Test
public void startPuntenVanSpelers()
{
    int startActiePuntenSpelerEen = spelMetTwee.geefActieveSpeler().geefActiePunten();
    int startKoopPuntenSpelerEen = spelMetTwee.geefActieveSpeler().geefKoopPunten();
    
    assertEquals(1,startActiePuntenSpelerEen);
    assertEquals(1,startKoopPuntenSpelerEen);
}

@Test
 public void vooraadDominionSpelMet2Testen()
 {

     int aantalKaartenInVooraad =spelMetTwee.geefVooraad().geefDeck().size();
     
     int aantalKaartenOpEersteStapel =spelMetTwee.geefAantalKaartenVan("koper");
             
     
     assertEquals(verwachteGrooteVooraad, aantalKaartenInVooraad);
     assertEquals(60, aantalKaartenOpEersteStapel);
     
 }
 
 @Test
 public void heeftSpelerEénJuisteVariablen()
 {
     Speler speler = spelMetTwee.geefActieveSpeler();
     ArrayList drawDeck = speler.geefDrawDeck().geefDeck();
     ArrayList foldDeck = speler.geefFoldDeck().geefDeck();
     ArrayList hand = speler.geefHand().geefDeck();
     
     int grooteDrawDeck = drawDeck.size();
     int grooteFoldDeck = foldDeck.size();
     int grooteHand = hand.size();
     
     assertEquals(5, grooteHand);
     assertEquals(5, grooteDrawDeck);
     assertEquals(0, grooteFoldDeck);
 }

  @Test
 public void neemKaartVanVooraad()
 {   
     Speler speler = spelMetTwee.geefActieveSpeler();
     int aantalKaartenInVooraad =spelMetTwee.geefVooraad().geefDeck().size();
     ArrayList foldDeck = speler.geefFoldDeck().geefDeck();

     spelMetTwee.koopKaart("koper");
     int aantalMaxKoper=60;
     int aantalverwachteKoper=aantalMaxKoper-1;
     int aantalKaartenOpKoperStapel =spelMetTwee.geefAantalKaartenVan("koper");
          int grooteFoldDeck = foldDeck.size();
             
     assertEquals(aantalverwachteKoper, aantalKaartenOpKoperStapel);
     assertEquals(verwachteGrooteVooraad, aantalKaartenInVooraad);
     assertEquals(1, grooteFoldDeck);
 }
 
 
 
}
