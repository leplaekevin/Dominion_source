/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;
import java.util.*;


/**
 *
 * @author Pc_Kevin
 */
public class Deck {
    private ArrayList <Kaart> soortenKaarten;
    private ArrayList <Integer> aantalKaarten;
    
    public Deck()
    {
        soortenKaarten = new ArrayList();
        aantalKaarten = new ArrayList();
    }

    public Deck(ArrayList aantalKaarten,ArrayList soortenKaarten)
    {
        
        this.soortenKaarten=soortenKaarten;
        this.aantalKaarten=aantalKaarten;
    }
    public Deck(ArrayList aantalKaarten)
    {
        
        this.soortenKaarten=soortenKaarten;
        this.aantalKaarten=aantalKaarten;
    }
        
    
    public void steekKaartInDeck(Kaart soort)
    {
        soortenKaarten.add(soort);
    }
    public String geefBovensteKaartNaam()
    {
        int index =soortenKaarten.size()-1;
        return soortenKaarten.get(index).geefKaartnaam().toString();
    }
    
    public Kaart neemBovensteKaart()//In orde ?
    {
        return geefKaartInDeckOp(soortenKaarten.size()-1);
    }
//    private void verwijderKaartVanStapel()// verwijderen ?
//    {
//        soortenKaarten.remove(soortenKaarten.size()-1);
//    }
    
    public void verwijderKaartMetNaamUitHand(String gespeeldeKaart)
    {
        boolean gevonden = false;
        for(int i=0;!gevonden &&i<soortenKaarten.size();i++)//-1????
            
            if(gespeeldeKaart.equals(soortenKaarten.get(i).geefKaartnaam().toString()))
            {
                gevonden= true;
                soortenKaarten.remove(i);
            }
    }
    
        private Kaart geefKaartInDeckOp(int index)
    {
        if(soortenKaarten.size() != 0)
        {
        Kaart kaart = soortenKaarten.get(index);
        soortenKaarten.remove(index);
        return kaart;
        }
        else
        {
            return null;
        }
    }
    public String geefKaartnaamVan(int i)
    {
        return soortenKaarten.get(i).geefKaartnaam();
    }
    
    public ArrayList geefDeck()
    {
        return soortenKaarten;
    }
    
    public ArrayList geefDeckMetKaartNamen() //!!Array Wordt omgekeerd afgedrukt maar code gewijst is alles in orde
    {
        ArrayList terugTeGevenDeck = new ArrayList();
        for(int groote=soortenKaarten.size();  groote!=0;groote--)
        {
            terugTeGevenDeck.add(soortenKaarten.get(groote-1).geefKaartnaam());
        }
        return terugTeGevenDeck;
    }

    public int geefDeckGroote()
    {
        return soortenKaarten.size();
    }
    
    public ArrayList geefAantalKaarten()
    {
        return aantalKaarten;
    }
    public int geefMuntenVanKaart(int pos)
    {
        return soortenKaarten.get(pos).geefMunten();
    }
    public int geefKostVanKaart(int pos)
    {
        return soortenKaarten.get(pos).geefKost();
    }

    @Override
    public String toString()
    {
        return soortenKaarten.toString();
    }
}
