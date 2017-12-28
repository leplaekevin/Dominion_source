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
public class Dominion {

    private ArrayList<Speler> spelers;
    private int NrActieveSpeler;
    private int aantalSpelers;
    private Speler activeSpeler;
    private ArrayList<Integer> eindScore;
    private Deck soortenVooraadKaarten;
    private ArrayList<Integer> aantalVooraadKaarten;
    private int legeVooraadStapels;

    public Dominion(ArrayList<String> gekregenSpelers, ArrayList<String> gekregensoortVooraadKaarten) {

        spelers = new ArrayList();
        for (int i = 0; i < gekregenSpelers.size(); i++) {
            spelers.add(new Speler(gekregenSpelers.get(i), soortenVooraadKaarten));
        }
        NrActieveSpeler = 0;
        activeSpeler = spelers.get(0);
        aantalSpelers = gekregenSpelers.size();
        soortenVooraadKaarten = new Deck();
        eindScore = new ArrayList();

        ArrayList vooraad = new ArrayList();
        System.err.println("in Dominion = " + gekregensoortVooraadKaarten.size());
        if (gekregensoortVooraadKaarten.size() < 2) {
            System.err.println("presetDeck type = " + gekregensoortVooraadKaarten);
            vooraad = vooraadPreSet(gekregensoortVooraadKaarten.get(0).toString());
        } else {
            gekregensoortVooraadKaarten.addAll(Arrays.asList("vloek", "provincie", "hertogdom", "landgoed", "goud", "zilver", "koper"));
            vooraad = gekregensoortVooraadKaarten;
        }

        for (int i = 0; i < 17; i++) {
            soortenVooraadKaarten.geefDeck().add(new Kaart(vooraad.get(i).toString()));

        }
        int overwiningskaartenStuks = 12;
        int koninkrijkkaarten = 10;
        int vloekKaartenStuks = 30;
        int koperKaartenStuks = 60;
        int zilverKaartenStuks = 40;
        int GoudKaartenStuks = 20;

        switch (aantalSpelers) {
            case 3:
                vloekKaartenStuks = 20;
                overwiningskaartenStuks = 10;
                break;
            case 2:
                vloekKaartenStuks = 10;
                overwiningskaartenStuks = 8;
                break;
        }

        aantalVooraadKaarten = new ArrayList();
        aantalVooraadKaarten.addAll(Arrays.asList(koperKaartenStuks, zilverKaartenStuks, GoudKaartenStuks, overwiningskaartenStuks, overwiningskaartenStuks, overwiningskaartenStuks,
                vloekKaartenStuks, koninkrijkkaarten, koninkrijkkaarten, koninkrijkkaarten, koninkrijkkaarten, koninkrijkkaarten, koninkrijkkaarten, koninkrijkkaarten,
                koninkrijkkaarten, koninkrijkkaarten, koninkrijkkaarten));

    }

    public ArrayList vooraadPreSet(String soort) {
        ArrayList vooraad = new ArrayList();

        switch (soort.toLowerCase()) {
            case "basis":
                vooraad.addAll(Arrays.asList("kelder", "markt", "militie", "mijn", "slotgracht", "verbouwing", "smidse", "dorp", "houthakker", "werkplaats"));
                break;
            case "geld":
                vooraad.addAll(Arrays.asList("avonturier", "bureaucraat", "raadsheer", "kapel", "feest", "laboratorium", "markt", "mijn", "geldschieter", "troonzaal"));
                break;
            case "interactie":
                vooraad.addAll(Arrays.asList("bureaucraat", "raadsheer", "raadzaal", "festival", "bibliotheek", "militie", "slotgracht", "spion", "dief", "dorp"));
                break;
            case "vertekend":
                vooraad.addAll(Arrays.asList("kelder", "kapel", "feest", "tuinen", "laboratorium", "dief", "dorp", "heks", "houthakker", "werkplaats"));
                break;
            case "dorpsplein":
                vooraad.addAll(Arrays.asList("bureaucraat", "kelder", "festival", "bibliotheek", "markt", "verbouwing", "smidse", "troonzaal", "dorp", "houthakker"));
                break;
        }
        vooraad.addAll(Arrays.asList("vloek", "provincie", "hertogdom", "landgoed", "goud", "zilver", "koper"));
        return vooraad;
    }

    private String geefKaartVanVooraadAan(String naam, Speler speler, int kostVanKaart) {//ToDo: uit elkaar halen void en return

        if (speler.geefKoopPunten() > 0) {

            int index = soortenVooraadKaarten.geefDeckMetKaartNamen().indexOf(naam.toLowerCase());
            int kost = kostVanKaart;
            int gevondenAantal = aantalVooraadKaarten.get(index);
            if (gevondenAantal > 0) {

                aantalVooraadKaarten.set(index, gevondenAantal - 1);
                activeSpeler.geefFoldDeck().geefDeck().add(new Kaart(naam.toLowerCase()));
                activeSpeler.verminderKoopPuntenMet(1);
                activeSpeler.verhoogGebruikteMunten(kost);
                return "U heeft een " + naam + " ontvangen      ";

            } else {
                System.out.println("Er zit geen " + naam + " meer in de voorraad");
                return "Er zit geen " + naam + " meer in de voorraad";
            }
        } else {
            System.out.println("U heeft geen kooppunten meer   ");
            return "U heeft geen kooppunten meer    ";
        }

    }

    public int geefAantalKaartenVan(String kaart) {
        int index = soortenVooraadKaarten.geefDeckMetKaartNamen().indexOf(kaart.toLowerCase());
        int gevondenAantal = aantalVooraadKaarten.get(index);
        return gevondenAantal;
    }

    public String koopKaart(String gekochteKaartNaam) {
        gekochteKaartNaam = gekochteKaartNaam.toLowerCase();
        Kaart gekochteKaart = new Kaart(gekochteKaartNaam);
        if (this.geefVooraad().geefDeckMetKaartNamen().contains(gekochteKaartNaam))//Zit het in de vooraad ?
        {
            int kostVanKaart = gekochteKaart.geefKost();
            if (this.geefActieveSpeler().geefMuntenVanSpeler() >= kostVanKaart)//geeft speler genoeg coins
            {
                String errorMelding = this.geefKaartVanVooraadAan(gekochteKaartNaam, activeSpeler, kostVanKaart);
                return errorMelding;
            } else {
                System.err.println("U heeft niet genoeg muntem om " + gekochteKaartNaam + " te kopen");
                System.err.println("munten:" + this.geefActieveSpeler().geefMuntenVanSpeler() + "kost:" + gekochteKaart.geefKost());
                return "U heeft niet genoeg muntem om " + gekochteKaartNaam + " te kopen";
            }
        } else {
            System.err.println("Deze kaart zit niet in de vooraad   ");
            return "Deze kaart zit niet in de vooraad   ";
        }
    }

    public String speelKaart(String gespeeldeKaart)
    {
        String errormessage = "";
        if (activeSpeler.geefActiePunten() > 0) {
            if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains(gespeeldeKaart))//TODO:return als het niet kan ? 
            {
                errormessage += "U heeft een " + gespeeldeKaart + " gespeeld. </br>";
                activeSpeler.geefSpeelVeld().geefDeck().add(new Kaart(gespeeldeKaart));
                activeSpeler.geefHand().verwijderKaartMetNaamUitHand(gespeeldeKaart);
                String gekozenKaartNaam = "kelder";

                switch (gespeeldeKaart.toLowerCase()) {
                    case "smidse":
                        activeSpeler.neemKaartenVanDeck(3);
                        break;
                    case "slotgracht":
                        activeSpeler.neemKaartenVanDeck(2);
                        break;
                    case "kelder":
                        //TODO:leg x aangtal kaarten af +1 actie voor elk
                        int extraActiePunten = 0;
                        int extrakaarten = 0;
                        for (int i = 0; i < activeSpeler.geefHand().geefDeckGroote(); i++) {
                            if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("landgoed")) {
                                activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("landgoed"));
                                activeSpeler.geefHand().verwijderKaartMetNaamUitHand("landgoed");
                                extraActiePunten += 1;
                                extrakaarten += 1;
                            }
                            if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("hertogdom")) {
                                activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("hertogdom"));
                                activeSpeler.geefHand().verwijderKaartMetNaamUitHand("hertogdom");
                                extraActiePunten += 1;
                                extrakaarten += 1;
                            }
                            if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("provincie")) {
                                activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("provincie"));
                                activeSpeler.geefHand().verwijderKaartMetNaamUitHand("provincie");
                                extraActiePunten += 1;
                                extrakaarten += 1;
                            }
                            if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("vloek")) {
                                activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("vloek"));
                                activeSpeler.geefHand().verwijderKaartMetNaamUitHand("vloek");
                                extraActiePunten += 1;
                                extrakaarten += 1;
                            }
                        }
                        activeSpeler.neemKaartenVanDeck(extrakaarten);
                        activeSpeler.extraActiePunten(extraActiePunten);
                        break;
                    case "markt":
                        activeSpeler.neemKaartVanDeck();
                        ;
                        activeSpeler.extraActiePunten(1);
                        activeSpeler.extraKoopPunten(1);
                        activeSpeler.extraActieMunten(1);
                        break;
                    case "militie":
                        for (int i = 0; i < aantalSpelers; i++) {
                            if (spelers.get(i) != activeSpeler) {
                                if (spelers.get(i).geefHand().geefDeckMetKaartNamen().contains("slotgracht")) {
                                    errormessage += spelers.get(i) + " heeft een slotgracht gespeeld.</br>";
                                }//counter 
                                else {
                                    spelers.get(i).voerMilitieUit();
                                    errormessage += spelers.get(i) + " heeft 2 kaarten weggegooid.</br>";
                                }
                            }
                        }
                        activeSpeler.extraActieMunten(2);
                        break;
                    case "mijn":
                        if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("koper")) {
                            activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("zilver"));
                            activeSpeler.geefHand().verwijderKaartMetNaamUitHand("koper");
                            errormessage += "U heeft een zilver ontvangen.</br>";
                        }
                        if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("zilver")) {
                            activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("goud"));
                            activeSpeler.geefHand().verwijderKaartMetNaamUitHand("zilver");
                            errormessage += "U heeft een goud ontvangen.</br>";
                        }

                        break;
                    case "verbouwing":
                        //TODO:vernietig een kaart en neem 1 die max 2 meer kost
                        if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("vloek")) {
                            activeSpeler.geefHand().verwijderKaartMetNaamUitHand("koper");
                        }
                        activeSpeler.extraActieMunten(2);
                        break;
                    case "dorp":
                        activeSpeler.neemKaartVanDeck();
                        activeSpeler.extraActiePunten(2);
                        break;
                    case "houthakker":
                        activeSpeler.extraKoopPunten(1);
                        activeSpeler.extraActieMunten(2);
                        break;
                    case "werkplaats":
                        //neem een kaart die max 4 kost
                        gekozenKaartNaam = "zilver";
                        Kaart gekozenKaart = new Kaart(gekozenKaartNaam);
                        if (new Kaart(gekozenKaartNaam).geefKost() < 5) {
                            activeSpeler.ontvangKaart(gekozenKaart);
                            errormessage += "U heeft een " + gekozenKaartNaam + " ontvangen</br>";
                        } else {
                            errormessage += "gekozenKaart kost meer dan 5 munten.</br>";
                            System.out.println("gekozenKaart kost meer dan 5 munten");
                        }
                        break;
                    case "avonturier":
                        //neem kaarten (en leg ze weg ) tot je 2 geldkaarten hebt en neem deze in je hand
                        for (int i = 0; i < 2; i++) {
                            if (activeSpeler.geefDrawDeck().geefDeckMetKaartNamen().contains("koper")) {
                                activeSpeler.geefHand().geefDeck().add(new Kaart("koper"));
                                activeSpeler.geefDrawDeck().verwijderKaartMetNaamUitHand("koper");
                                errormessage += "U heeft een koper ontvangen</br>";
                            }
                            errormessage += "U heeft een geen koper meer</br>";
                        }
                        break;
                    case "bureaucraat":
                        //TODO:aanval elke speler legt een overwinningskaart op zijn drawdeck
                        activeSpeler.geefDrawDeck().steekKaartInDeck(new Kaart("zilver"));
                        aantalVooraadKaarten.set(6, aantalVooraadKaarten.get(2) - 1);
                        for (int i = 0; i < aantalSpelers; i++) {
                            if (spelers.get(i).geefHand().geefDeck().contains("landgoed") && spelers.get(i) != activeSpeler) {
                                spelers.get(i).geefHand().verwijderKaartMetNaamUitHand("landgoed");
                                activeSpeler.geefDrawDeck().geefDeck().add(new Kaart("landgoed"));
                                errormessage += spelers.get(i) + " heeft een landgoed op wijn deck gelegt.</br>";
                            } else if (spelers.get(i).geefHand().geefDeck().contains("hertogdom") && spelers.get(i) != activeSpeler) {
                                spelers.get(i).geefHand().verwijderKaartMetNaamUitHand("hertogdom");
                                activeSpeler.geefDrawDeck().geefDeck().add(new Kaart("hertogdom"));
                                errormessage += spelers.get(i) + " heeft een hertogdom op wijn deck gelegt.</br>";
                            } else if (spelers.get(i).geefHand().geefDeck().contains("provincie") && spelers.get(i) != activeSpeler) {
                                spelers.get(i).geefHand().verwijderKaartMetNaamUitHand("provincie");
                                activeSpeler.geefDrawDeck().geefDeck().add(new Kaart("provincie"));
                                errormessage += spelers.get(i) + " heeft een provincie op wijn deck gelegt.</br>";
                            }
                        }
                        break;
                    case "raadsheer":
                        activeSpeler.extraActieMunten(2);
                        activeSpeler.speelRaadsheer();//TODO:vraag speler of hij zijn drawdeck op zijn folddeck wil leggen
                        break;
                    case "kapel":
                        //TODO:speler mag 4 kaarten vernietigen
                        for (int i = 0; i < 4; i++) {
                            if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("vloek")) {
                                activeSpeler.geefHand().verwijderKaartMetNaamUitHand("vloek");
                                errormessage += "U heeft een vloek vernietigd</br>";
                            }
                        }
                        break;
                    case "raadzaal":
                        activeSpeler.neemKaartenVanDeck(3);
                        activeSpeler.extraActiePunten(1);
                        for (int i = 0; i < aantalSpelers; i++) {
                            spelers.get(i).neemKaartVanDeck();
                            errormessage += spelers.get(i) + " heeft een kaart ontvangen.</br>";
                        }
                        break;
                    case "feest":
                        //TODO:vernietig deze kaart en neem een kaart van max 5
                        errormessage += "U heeft uw feest vernietigd voor en 5 munten ontvangen</br>";
                        activeSpeler.geefSpeelVeld().verwijderKaartMetNaamUitHand(gespeeldeKaart);
                        activeSpeler.extraActieMunten(5);

                        break;
                    case "festival":
                        activeSpeler.extraActiePunten(2);
                        activeSpeler.extraKoopPunten(1);
                        activeSpeler.extraActieMunten(2);
                        break;
                    case "laboratorium":
                        activeSpeler.neemKaartenVanDeck(2);
                        activeSpeler.extraActiePunten(1);
                        break;
                    case "bibliotheek":
                        //neem kaarten tot je 7 kaarten in je hand hebt getrokken actiekaarten mag je vervangen door andere kaarten
                        for (int i = activeSpeler.geefHand().geefDeck().size(); i != 7; i++)//men moet nog kiezen of men actiekaarten wil weg gooien
                        {
                            activeSpeler.neemKaartVanDeck();
                        }
                        break;
                    case "geldschieter":
                        if (activeSpeler.geefHand().geefDeckMetKaartNamen().contains("koper")) {
                            activeSpeler.geefHand().verwijderKaartMetNaamUitHand("koper");
                            errormessage += "u  heeft een koper verwijderd.</br>";
                            activeSpeler.extraActieMunten(3);
                        }
                        break;
                    case "spion":
                        //TODO:elke speler toont zijn bovenste kaart van drawdeck en actievespeler beslist of deze weg mag naar het folddeck
                        activeSpeler.extraActiePunten(1);
                        activeSpeler.neemKaartVanDeck();
                        break;
                    case "dief":
                        //TODO:elke speler toont zijn bovenstekaarten en actieve speler mag 1 stelen of vernietigen de kaarten worden dan naar fold deck gedaan
                        for (int i = 0; i < aantalSpelers; i++) {
                            if (spelers.get(i).geefDrawDeck().geefDeck().contains("koper") && spelers.get(i) != activeSpeler) {
                                spelers.get(i).geefDrawDeck().verwijderKaartMetNaamUitHand("koper");
                                activeSpeler.geefFoldDeck().geefDeck().add(new Kaart("koper"));
                                errormessage += "U heeft een koper gestolen van " + spelers.get(i) + "</br>";
                            }
                        }
                        break;
                    case "troonzaal":
                        //volgende actiekaart wordt 2x gespeeld
                        System.out.println("U heeft een kaart gespeeld die niets doet :D");
                        errormessage += "U heeft een kaart gespeeld die niets doet :D</br>";
                        break;
                    case "heks":
                        activeSpeler.neemKaartenVanDeck(2);
                        for (int i = 0; i < aantalSpelers; i++) {
                            if (spelers.get(i) != activeSpeler && aantalVooraadKaarten.get(6) > 0) {
                                if (spelers.get(i).geefHand().geefDeckMetKaartNamen().contains("slotgracht")) {
                                    errormessage += spelers.get(i) + " heeft een slotgracht gespeeld.</br>";
                                }//counter
                                else {
                                    spelers.get(i).geefFoldDeck().steekKaartInDeck(new Kaart("vloek"));//uit vooraad doen
                                    aantalVooraadKaarten.set(6, aantalVooraadKaarten.get(6) - 1);
                                    errormessage += spelers.get(i) + " heeft een vloek ontvanegn.</br>";
                                }
                            }
                        }
                        break;
                }
                activeSpeler.verminderActiePuntenMet(1);
            } else {
                errormessage += "U heeft deze kaart niet in uw hand";
                System.err.println("U heeft deze kaart niet in uw hand");
            }

        } else {
            errormessage += "U heeft niet genoeg actiepunten";
            System.err.println("U heeft niet genoeg actiepunten");
        }
        return errormessage;
    }

    public Deck geefVooraad() {
        return soortenVooraadKaarten;
    }

    public ArrayList<Integer> geefAantalVooraad() {
        return aantalVooraadKaarten;
    }

    public ArrayList geefSpelers() {
        return spelers;
    }

    public Speler geefSpelerEén() {
        return spelers.get(0);
    }

    public Speler geefSpelerTwee() {
        return spelers.get(1);
    }

    //DO:wat als er maar 2 spelers zijn
    public Speler geefSpelerDrie() {
        return spelers.get(2);
    }

    //DO:wat als er maar 2 spelers zijn
    public Speler geefSpelerVier() {
        return spelers.get(3);
    }

    public Speler geefActieveSpeler() {
        return activeSpeler;
    }

    public ArrayList geefeindScore() {
        return eindScore;
    }

    public int geefLegeVooraadStapels() {
        return legeVooraadStapels;
    }

    public void zetActieveSpeler(int spelerNr) {
        activeSpeler = spelers.get(spelerNr);
    }

    public int berekenLegeVooraadStapels() {
        int legestapels = 0;
        for (int i = 0; i < aantalVooraadKaarten.size(); i++) {
            if (aantalVooraadKaarten.get(i) < 1) {
                legestapels += 1;
            }
            if (aantalVooraadKaarten.get(5) < 1) {
                legestapels += 10;
            }
        }
        return legestapels;
    }

    public void speelBeurt() {

        activeSpeler.eindigBeurt();
        legeVooraadStapels = berekenLegeVooraadStapels();
        if (legeVooraadStapels > 2) {
            spelBeeindigen();
        }
        if (NrActieveSpeler < aantalSpelers - 1) {
            NrActieveSpeler += 1;
        } else {
            NrActieveSpeler = 0;
        }
        zetActieveSpeler(NrActieveSpeler);
    }

    public int geefActieveSpelernr() {
        return NrActieveSpeler;
    }

    public void spelBeeindigen() {
        //opvullen
        for (int i = 0; i < spelers.size(); i++) {
            eindScore.add(spelers.get(i).berekenOverwinningsPunten());
        }
        System.out.println(eindScore.toString());
        //selection sort
        for (int i = 0; i < spelers.size(); i++) {
            int posMax = i;
            for (int j = posMax + 1; j < spelers.size(); j++) {
                if (eindScore.get(j) > eindScore.get(posMax)) {
                    posMax = j;
                }
            }
            int tmp = eindScore.get(i);
            eindScore.set(i, eindScore.get(posMax));
            eindScore.set(posMax, tmp);

            Speler tmpp = spelers.get(i);
            spelers.set(i, spelers.get(posMax));
            spelers.set(posMax, tmpp);
        }
    }

    public boolean zitErIetsInActieveSpelerFoldDeck()//TODO: weg ?temp
    {
        if (activeSpeler.geefFoldDeck().geefDeck().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
