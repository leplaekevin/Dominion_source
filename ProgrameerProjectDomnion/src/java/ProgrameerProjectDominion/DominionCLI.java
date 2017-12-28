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
public class DominionCLI 
{
    private Dominion gameEngine;
    private int aantalSpelers;
    private int actie;
    private ArrayList <String> spelers;   
    private ArrayList <String> vooraadKaarten;
    private boolean endGame;
    
    public static void main( String[] args)
    {
        DominionCLI dcli = new DominionCLI();
        dcli.start();
    }
    
    public DominionCLI()
    {
        aantalSpelers=0;
        spelers= new ArrayList();
        while(aantalSpelers>4 || aantalSpelers<2)
        {
             aantalSpelers=vraagSpelerAantalSpelers();
        }
        for(int i=0;i<aantalSpelers;i++)
        {
         //vraag naam speler
            String ontvangenNaam = vraagSpelerNaam(i + 1);
            spelers.add(ontvangenNaam);
        }
        vooraadKaarten = new ArrayList();
        //vooraadKaarten.add("basis");
        String cc ="";
        while(!cc.equals("basis") && !cc.equals("geld") && !cc.equals("interactie") && !cc.equals("vertekend") && !cc.equals("dorpsplein"))
        {
            cc =vraagSpelerSoortPreMadeDeck().toLowerCase();
            System.err.println(cc);
        }
        vooraadKaarten.add(cc);
        gameEngine = new Dominion(spelers,vooraadKaarten);
        endGame=false;
    }

    private void start()
    {
        System.out.println("WELCOME TO DOMINION");
        System.out.println("__________________");
        while(!endGame)
        {
        gameLoop();
        }
    }
    
    public void gameLoop()
    {
        actie =0;
        while(actie!=3 && actie!=4 )
        {
        gameEngine.geefActieveSpeler().berekenMunten();
        toonHuidigeSituatie();
        toonMogelijkeZetten();
        actie = vraagSpelerActie();
        verwerkSpelerActie(actie);  
        }
    }

    public void toonHuidigeSituatie()
    {
        System.out.println("");
        System.out.println("------------Speler: "+gameEngine.geefActieveSpeler().geefNaam()+"-------------");
        System.out.println("Tafel: "+gameEngine.geefActieveSpeler().geefSpeelVeld().geefDeckMetKaartNamen());//TODO: wat ligt er op tafel? mss gwn kaarten naar fold deck en een tmparray tafel met de weg gegooide kaarten 
        System.out.println("Hand: "+gameEngine.geefActieveSpeler().geefHand().geefDeckMetKaartNamen());
        System.out.println("drawDeck: "+gameEngine.geefActieveSpeler().geefDrawDeck().geefDeckMetKaartNamen());
        System.out.println("foldDeck: "+gameEngine.geefActieveSpeler().geefFoldDeck().geefDeckMetKaartNamen());
        System.out.println("actiepunten: "+gameEngine.geefActieveSpeler().geefActiePunten());
        System.out.println("kooppunten: "+gameEngine.geefActieveSpeler().geefKoopPunten());
        System.out.println("geld: "+gameEngine.geefActieveSpeler().geefMuntenVanSpeler()); //Controleren: geldophalen 
    }
    
    public void toonMogelijkeZetten()
    {
        System.out.println("");
        System.out.println("____________Acties______________");
        System.out.println("1 = koop een kaart"); 
        System.out.println("2 = speel een kaart"); 
        System.out.println("3 = eindig beurt "); 
        System.out.println("4 = eindig spel "); 
    }
    
    public void toonMogelijkeVooraadKaarten()
    {
        System.out.println("6 kost: Avonturier");
        System.out.println("5 kost: Raadzaal,Heks,Festival,Laboratorium,Bibliotheek,Markt,Mijn");
        System.out.println("4 kost: Smidse,Bureaucraat,Spion,Dief,Troonzaal,Feest,Militie,Geldschieter,Verbouwing");
        System.out.println("3 kost: Raadsheer,Dorp,Houthakker,Werkplaats");
        System.out.println("2 kost: Kelder,Kapel");
    }
    
    public void toonVooraadKaarten()
    {  
        System.out.println("kaarten: "+gameEngine.geefVooraad().geefDeckMetKaartNamen());
        System.out.println("aantal:  "+gameEngine.geefAantalVooraad());
    }
    
    public int vraagSpelerActie()//waarom lijt hij Strings ?
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Kies een actie(nummer): ");
        int n = reader.nextInt();
        return n;
    }
    
    public int vraagSpelerAantalSpelers()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Met hoeveel spelers wilt u spelen(2 tot 4): ");
        int n = reader.nextInt();
        return n;
    }
    
    public String vraagSpelerActieKoopKaart()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Kies een kaart(type de naam): ");
        String n = reader.next();
        return n;
    }
    
    public String vraagSpelerNaam(int speler)
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Naam van speler " + speler + ": (type de naam): ");
        String n = reader.next();
        return n;
    }
    
    public String vraagSpelerActieSpeelKaart()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Kies een kaart(type de naam): ");
        String n = reader.next();
        return n;
    }
    
    public String vraagSpelerSoortPreMadeDeck()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("basis | geld | interactie | vertekend | dorpsplein");
        System.out.println("Kies een voorgemaakt deck(type de naam): ");
        String n = reader.next();
        return n;
    }
    
    public void verwerkSpelerActie(int actie)// in eng ??? als methodes de same zijn
    {
        switch(actie)
        {
            case 1:  //1=koop
                if(gameEngine.geefActieveSpeler().geefKoopPunten()!=0)
                    verwerkKoopKaart();
                else
                    System.err.println("U heeft geen kooppunten meer");
                break;
            case 2:  //2=speel kaart
                if(gameEngine.geefActieveSpeler().geefActiePunten()!=0)
                    verwerkSpeelKaart();
                else
                    System.err.println("U heeft geen actiepunten meer");
                break;
            case 3:  //3=end turn
                verwerkEindigBeurt();
                break; 
            case 4:
                verwerkEindigSpel();
                endGame=true;
                break;
        }
    }
    
    public void verwerkKoopKaart()
    {
        System.out.println("-----------Vooraad--------------");
        System.out.println("coins: "+gameEngine.geefActieveSpeler().geefMuntenVanSpeler());
        System.out.println("koop punten: "+gameEngine.geefActieveSpeler().geefKoopPunten());  
        toonVooraadKaarten();
        String gekochteKaart = vraagSpelerActieKoopKaart();
        gameEngine.koopKaart(gekochteKaart);
        System.err.println("aantal:  "+gameEngine.geefAantalVooraad());  
    }

    public void verwerkEindigBeurt()
    {  
        gameEngine.speelBeurt();//gameEngine.geefActieveSpeler()
        if(gameEngine.geefLegeVooraadStapels()>2)
        {
            verwerkEindigSpel();
            endGame=true;
        }
    }
    
    public void verwerkEindigSpel()
    {  
        gameEngine.spelBeeindigen();
        for(int i =0;i< gameEngine.geefSpelers().size();i++)
        {
            Speler gevondenSpeler =(Speler) gameEngine.geefSpelers().get(i);
            System.out.println(gevondenSpeler.geefNaam()+" heeft "+gameEngine.geefeindScore().get(i)+" punten");
        }
    }
    
    public void verwerkSpeelKaart()
    {
        System.out.println("-------Speel kaart---------");
        toonHuidigeSituatie();
        String gespeeldeKaart =vraagSpelerActieSpeelKaart();
        gameEngine.speelKaart(gespeeldeKaart);
    }
}
