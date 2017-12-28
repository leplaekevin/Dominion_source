/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Pc_Kevin
 */
public class Speler {

    private String naam;
    private int actiePunten;
    private int koopPunten;
    private Deck foldDeck;
    private Deck drawDeck;
    private Deck spelerhand;
    private Deck speelVeld;
    private ArrayList <Kaart> wegTeGooienHand;
    private ArrayList <Kaart> wegTeGooienSpeelVeld;
    private int munten;
    private int actieMunten;
    private int uitgegevenMunten; 

    public Speler(String naam,Deck soortenVooraadKaarten) //soortenVooraadKaarten houden voor aantal bij te houden in array ? 
    {
        this.naam = naam;
        koopPunten = 1;//1
        actiePunten = 1;//1
        munten=0;
        actieMunten=0;//0
        uitgegevenMunten=0;
        foldDeck = new Deck();
        drawDeck = new Deck();
        speelVeld = new Deck();
        
        for(int i=0;i<7;i++)
        {
            drawDeck.steekKaartInDeck(new Kaart("koper"));
        }
        for(int i=0;i<3;i++)
        {
            drawDeck.steekKaartInDeck(new Kaart("landgoed"));
        }
        Collections.shuffle(drawDeck.geefDeck());//mss in comentaar
        spelerhand = new Deck();
        for(int i=0;i<5;i++)
        {
            this.neemKaartVanDeck();
        }
    }

    public String geefNaam() {
        return naam;   
    }

    public int geefActiePunten() {
        return actiePunten;
    }

    public int geefKoopPunten() {
        return koopPunten;  
    }
    
    public int geefMuntenVanSpeler(){
        return munten;
    }
    
    public void neemKaartVanDeck() {
        if(drawDeck.geefDeck().isEmpty())
        {       
                Collections.shuffle(foldDeck.geefDeck());
                drawDeck = foldDeck;
                foldDeck = new Deck();
                if(!drawDeck.geefDeck().isEmpty())
                {
                    spelerhand.steekKaartInDeck(drawDeck.neemBovensteKaart());
                }
        }
        else{
            spelerhand.steekKaartInDeck(drawDeck.neemBovensteKaart());
        }
    }

    public void eindigBeurt() {
        wegTeGooienHand = spelerhand.geefDeck();
        int grooteHand= wegTeGooienHand.size();
        wegTeGooienSpeelVeld=speelVeld.geefDeck();
        
        int grooteKaartenSpeelVeld= wegTeGooienSpeelVeld.size();
        
        for (int i = 0; i < grooteHand; grooteHand--) {
            foldDeck.steekKaartInDeck(wegTeGooienHand.get(grooteHand-1));
        }
        for (int i = 0; i < grooteKaartenSpeelVeld; i++) {
            System.out.println("ProgrameerProjectDominion.Speler.eindigBeurt()"+grooteKaartenSpeelVeld);
            System.out.println("ProgrameerProjectDominion.Speler.eindigBeurt()"+wegTeGooienSpeelVeld);
            foldDeck.steekKaartInDeck(wegTeGooienSpeelVeld.get(grooteKaartenSpeelVeld-1));
        }
        spelerhand = new Deck();
        for (int i = 0; i < 5; i++) {
            if (drawDeck.geefDeck().size() != 0) {
                this.neemKaartVanDeck();
                
            } else {
                
                Collections.shuffle(foldDeck.geefDeck());
                drawDeck = foldDeck;
                foldDeck = new Deck();
                this.neemKaartVanDeck();
            }
        speelVeld = new Deck();
        koopPunten=1;
        actiePunten=1;
        actieMunten=0;
        munten=0;
        uitgegevenMunten=0;
        }
    }

    public int geefGrooteHand()//TODO: doe weg = test klasse
    {
        return spelerhand.geefDeck().size();
    }

    public Deck geefFoldDeck() {
        return foldDeck;
    }
    
    public Deck geefSpeelVeld() {
        return speelVeld;
    }
    
    public void ontvangKoopPunten(int aantal){
        koopPunten+=aantal;
    }
    
    public void ontvangKaart(Kaart ontvangenKaart){
        foldDeck.steekKaartInDeck(ontvangenKaart);
    }
    
    public Deck geefDrawDeck() {
        return drawDeck;
    }
    
    public int geefUitgegevenMunten(){
        return uitgegevenMunten;
    }
    
    public void verhoogGebruikteMunten(int gebruikteMunten){
        uitgegevenMunten+= gebruikteMunten;
    }
    
    public Deck geefHand() {
        return spelerhand;
    }
    
    public void neemKaartenVanDeck(int teNemenKaarten)
    {
        while (teNemenKaarten!=0)
        {
            this.neemKaartVanDeck();
            teNemenKaarten--;
        }
    }
    
    public void verminderKoopPuntenMet(int koopPuntenverschil){
        this.koopPunten -=koopPuntenverschil;
    }
    
    public void verminderActiePuntenMet(int actiePuntenverschil){
        this.actiePunten -=actiePuntenverschil;
    }
    
    public void extraActiePunten(int extraActiepunten){
        actiePunten += extraActiepunten;
    }
    
    public void extraKoopPunten(int extraKooppunten){
        koopPunten += extraKooppunten;
    }
    
    public void extraActieMunten(int extraActieMunten){
        actieMunten += extraActieMunten;
    }
    
    public void berekenMunten()
    {
        int kost =0;
        for(int i=0;i<this.spelerhand.geefDeck().size();i++)
        {
            switch(spelerhand.geefKaartnaamVan(i).toLowerCase())//enkel de munten want munten van actiekaarten gaan in de int actiemunten
            {
                case "koper":
                    kost+=1;
                    break;
                case "zilver":
                    kost+=2;
                    break;
                case "goud":
                    kost+=3;
                    break;
            }
        }
        kost+=actieMunten;
        kost -= uitgegevenMunten;
        this.munten = kost;
    }
    
    public int berekenOverwinningsPunten()
    {
        int score = 0;
        for(int i=0;i<this.spelerhand.geefDeck().size();i++)
        {
            Kaart kaart =(Kaart) spelerhand.geefDeck().get(i);
            score+=kaart.geefPunten();
        }
        for(int i=0;i<this.drawDeck.geefDeck().size();i++)
        {
            Kaart kaart =(Kaart) drawDeck.geefDeck().get(i);
            score+=kaart.geefPunten();
        }
        for(int i=0;i<this.foldDeck.geefDeck().size();i++)
        {
            Kaart kaart =(Kaart) foldDeck.geefDeck().get(i);
            score+=kaart.geefPunten();
        }
        return score;
    }
    
    public void speelRaadsheer()
    {
        System.err.println("ProgrameerProjectDominion.Speler.speelRaadsheer()");
        for(int i=0;i<geefFoldDeck().geefDeckGroote();i++)
        {
            drawDeck.geefDeck().add(foldDeck.geefDeck().get(i));
        }
        Collections.shuffle(geefDrawDeck().geefDeck());
        foldDeck = new Deck();
    }
    
    public void voerMilitieUit()
    {
        foldDeck.geefDeck().add(spelerhand.neemBovensteKaart());
        foldDeck.geefDeck().add(spelerhand.neemBovensteKaart());
    }

    @Override
    public String toString()
    {
     return geefNaam();
    }
}
