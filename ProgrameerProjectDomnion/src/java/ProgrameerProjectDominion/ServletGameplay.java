/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.JSONObject;

/**
 *
 * @author michield
 */
public class ServletGameplay extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Registratie registratie;
    private Dominion gameEngine;
    private JdbcTemplate template;
    private ArrayList<String> spelers;
    private ArrayList<String> hand, speelveld;
    private ArrayList<String> arraylistVoorraard;
    private ArrayList<String> arrayVanKaarten;
    private Integer actiepunten, aankooppunten, munten, aantalvloek, aantalprovincie,
            aantalhertogdom, aantallandgoed, aantalgoud, aantalzilver, aantalkoper;
    private String takemelding;
    private Login login;
    public ArrayList<String> arrayVanGebruikers;
    String aantalSpelers;
    Integer teller = 2;
    private String gebruiker1;
    private String gebruiker2;
    private String gebruiker3;
    private String gebruiker4;
    private String gast1;
    private String gast2;
    private String gast3;
    private String gast4;
    private String scoreGebruiker1;
    private String scoreGebruiker2;
    private String scoreGebruiker3;
    private String scoreGebruiker4;
    private ArrayList<String> arrayEindscore;

    private boolean connectieIsGelukt;

    public ServletGameplay() {
        login = new Login();
        arrayVanGebruikers = new ArrayList();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter jsonwriter = response.getWriter();
        String operation;
        operation = request.getParameter("operation");
        String teKrijgenDeckNaam = request.getParameter("doorTegevenDeck");
        String teKrijgenKaart1 = request.getParameter("doorTeSturenKaart1");
        String teKrijgenKaart2 = request.getParameter("doorTeSturenKaart2");
        String teKrijgenKaart3 = request.getParameter("doorTeSturenKaart3");
        String teKrijgenKaart4 = request.getParameter("doorTeSturenKaart4");
        String teKrijgenKaart5 = request.getParameter("doorTeSturenKaart5");
        String teKrijgenKaart6 = request.getParameter("doorTeSturenKaart6");
        String teKrijgenKaart7 = request.getParameter("doorTeSturenKaart7");
        String teKrijgenKaart8 = request.getParameter("doorTeSturenKaart8");
        String teKrijgenKaart9 = request.getParameter("doorTeSturenKaart9");
        String teKrijgenKaart10 = request.getParameter("doorTeSturenKaart10");
        String actieKaartNaam = request.getParameter("kaartnaam");

        PrintWriter out = response.getWriter();

        String gebruikersnaam = request.getParameter("gebruikersnaam");
        String wachtwoord = request.getParameter("wachtwoord");
        String checkwachtwoord = request.getParameter("checkwachtwoord");

        boolean checkLogin = false;
        boolean checkRegistratie = true;

        operation = request.getParameter("operation");
        String doorTeGevenAantalSpelers = request.getParameter("gekozenAantalSpelers");

        JSONObject aantalSpelersObj = new JSONObject();

        JSONObject registreerObj = new JSONObject();
        JSONObject loginObj = new JSONObject();
        JSONObject scoreObj = new JSONObject();

        String teKrijgenGast = request.getParameter("gast");
//        String teKrijgenGast2 = request.getParameter("gast2");
//        String teKrijgenGast3 = request.getParameter("gast3");
//        String teKrijgenGast4 = request.getParameter("gast4");
        String teKrijgenGebruiker1 = request.getParameter("doorTeSturenGebruiker1");
        String teKrijgenGebruiker2 = request.getParameter("doorTeSturenGebruiker2");
        String teKrijgenGebruiker3 = request.getParameter("doorTeSturenGebruiker3");
        String teKrijgenGebruiker4 = request.getParameter("doorTeSturenGebruiker4");

        aantalvloek = aantalkoper = aantalzilver = aantalgoud = aantallandgoed = aantalhertogdom = aantalprovincie = 10;
        JSONObject jsonobj = new JSONObject();
        switch (operation) {
            case "load":

                Integer i = 0;

                spelers = arrayVanGebruikers;
                System.out.println(spelers);

                arraylistVoorraard = new ArrayList();

                System.out.println(arrayVanKaarten);
                if (Objects.isNull(arrayVanKaarten)) { // todo: if en else moet nog weg
                    arraylistVoorraard.add("basis");
                } else {
                    arraylistVoorraard = arrayVanKaarten;
                }

//                arraylistVoorraard = arrayVanKaarten;
                jsonobj.put("aantalspelers", spelers.size());
                gameEngine = new Dominion(spelers, arraylistVoorraard);
                System.out.println("arraylist voorraad" + arraylistVoorraard);
                System.out.println(arraylistVoorraard.size());
                //speel random aantal beurten
                // gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());gameEngine.speelBeurt(gameEngine.geefActieveSpeler());
                System.out.println("arraylist" + arraylistVoorraard);
                hand = gameEngine.geefActieveSpeler().geefHand().geefDeckMetKaartNamen();
                jsonobj.put("aantalInHand", hand.size() + 1);
                System.out.println(hand);
                jsonobj.put("hand0", hand.get(0).toLowerCase());

                for (i = 1; i < hand.size(); i++) {
                    jsonobj.put("hand" + i, hand.get(i).toLowerCase());

                }
                jsonobj.put("aantalkoper", gameEngine.geefAantalVooraad().get(0).toString());
                jsonobj.put("aantalzilver", gameEngine.geefAantalVooraad().get(1).toString());

                jsonobj.put("aantalgoud", gameEngine.geefAantalVooraad().get(2).toString());

                jsonobj.put("aantallandgoed", gameEngine.geefAantalVooraad().get(3).toString());

                jsonobj.put("aantalhertogdom", gameEngine.geefAantalVooraad().get(4).toString());

                jsonobj.put("aantalprovincie", gameEngine.geefAantalVooraad().get(5).toString());

                jsonobj.put("aantalvloek", gameEngine.geefAantalVooraad().get(6).toString());

                for (i = 1; i < 11; i++) {
                    jsonobj.put("kaart" + i, gameEngine.geefAantalVooraad().get(i + 6).toString());
                }
                // gameEngine
                actiepunten = gameEngine.geefActieveSpeler().geefActiePunten();
                aankooppunten = gameEngine.geefActieveSpeler().geefKoopPunten();
                //bereken aantal munten doen alvorens weer te geven
                gameEngine.geefActieveSpeler().berekenMunten();
                //deck kaartnamen voorraard
                munten = gameEngine.geefActieveSpeler().geefMuntenVanSpeler();

                for (i = 1; i < 11; i++) {
                    jsonobj.put("kaartnaam" + i, gameEngine.geefVooraad().geefDeckMetKaartNamen().get(i + 6).toString());
                }

                System.out.println(munten);
                jsonobj.put("actiepunten", actiepunten.toString());
                jsonobj.put("aankooppunten", aankooppunten.toString());
                jsonobj.put("munten", munten.toString());
                jsonobj.put("spelernr", gameEngine.geefActieveSpelernr());

                System.out.println(gameEngine.geefActieveSpelernr());
                jsonwriter.print(jsonobj);
                break;
            case "take":
                String kaartnaam = request.getParameter("kaartnaam");
                takemelding = gameEngine.koopKaart(kaartnaam);
                jsonobj.put("melding", takemelding);

                jsonobj.put("aantalkoper", gameEngine.geefAantalVooraad().get(0).toString());
                jsonobj.put("aantalzilver", gameEngine.geefAantalVooraad().get(1).toString());

                jsonobj.put("aantalgoud", gameEngine.geefAantalVooraad().get(2).toString());

                jsonobj.put("aantallandgoed", gameEngine.geefAantalVooraad().get(3).toString());

                jsonobj.put("aantalhertogdom", gameEngine.geefAantalVooraad().get(4).toString());

                jsonobj.put("aantalprovincie", gameEngine.geefAantalVooraad().get(5).toString());

                jsonobj.put("aantalvloek", gameEngine.geefAantalVooraad().get(6).toString());

                for (i = 1; i < 11; i++) {
                    jsonobj.put("kaart" + i, gameEngine.geefAantalVooraad().get(i + 6).toString());
                }
                // gameEngine
                actiepunten = gameEngine.geefActieveSpeler().geefActiePunten();
                aankooppunten = gameEngine.geefActieveSpeler().geefKoopPunten();
                //bereken aantal munten doen alvorens weer te geven
                gameEngine.geefActieveSpeler().berekenMunten();
                //deck kaartnamen voorraard
                munten = gameEngine.geefActieveSpeler().geefMuntenVanSpeler();
                System.out.println(aankooppunten);
                System.out.println("munten: " + munten);
                jsonobj.put("actiepunten", actiepunten.toString());
                jsonobj.put("aankooppunten", aankooppunten.toString());
                jsonobj.put("munten", munten.toString());
                if (gameEngine.zitErIetsInActieveSpelerFoldDeck()) {
                    jsonobj.put("aflegdeck", gameEngine.geefActieveSpeler().geefFoldDeck().geefBovensteKaartNaam());
                    jsonobj.put("legInAflegDeck", true);//controleer
                } else {
                    jsonobj.put("legInAflegDeck", gameEngine.zitErIetsInActieveSpelerFoldDeck());
                }
                jsonwriter.print(jsonobj);
                break;

            case "selecteerKaarten":

                arrayVanKaarten = new ArrayList();
                System.out.println(teKrijgenDeckNaam);
                if (teKrijgenDeckNaam == null) {
                    arrayVanKaarten.addAll(Arrays.asList(teKrijgenKaart1, teKrijgenKaart2,
                            teKrijgenKaart3, teKrijgenKaart4, teKrijgenKaart5, teKrijgenKaart6,
                            teKrijgenKaart7, teKrijgenKaart8, teKrijgenKaart9, teKrijgenKaart10));

                    System.out.println("Arraylist van kaarten " + arrayVanKaarten);
                } else {
                    arrayVanKaarten.add(teKrijgenDeckNaam);
                    System.out.println(arrayVanKaarten);
                }

                break;
            case "endturn":

                gameEngine.speelBeurt();

                jsonobj.put("beurtbeindigt", "volgende speler!");

                hand = gameEngine.geefActieveSpeler().geefHand().geefDeckMetKaartNamen();
                jsonobj.put("aantalInHand", hand.size() + 1);
                System.out.println(hand);
                jsonobj.put("hand0", hand.get(0).toLowerCase());
                System.out.println("drawdeck:" + gameEngine.geefActieveSpeler().geefDrawDeck());
                for (i = 1; i < hand.size(); i++) {
                    jsonobj.put("hand" + i, hand.get(i).toLowerCase());

                }
                // gameEngine
                actiepunten = gameEngine.geefActieveSpeler().geefActiePunten();
                aankooppunten = gameEngine.geefActieveSpeler().geefKoopPunten();
                //bereken aantal munten doen alvorens weer te geven
                gameEngine.geefActieveSpeler().berekenMunten();
                jsonobj.put("naamActiveSpeler", gameEngine.geefActieveSpeler().geefNaam());
                //deck kaartnamen voorraard
                munten = gameEngine.geefActieveSpeler().geefMuntenVanSpeler();

                System.out.println(munten);
                jsonobj.put("aantalspelers", spelers.size());
                jsonobj.put("actiepunten", actiepunten.toString());
                jsonobj.put("aankooppunten", aankooppunten.toString());
                jsonobj.put("munten", munten.toString());
                jsonobj.put("spelernr", gameEngine.geefActieveSpelernr());
                if (gameEngine.zitErIetsInActieveSpelerFoldDeck()) {
                    jsonobj.put("aflegdeck", gameEngine.geefActieveSpeler().geefFoldDeck().geefBovensteKaartNaam());
                    jsonobj.put("legInAflegDeck", true);//controleer
                } else {
                    jsonobj.put("legInAflegDeck", gameEngine.zitErIetsInActieveSpelerFoldDeck());
                }
                if (gameEngine.geefLegeVooraadStapels()>2){
                jsonobj.put("gameMoetEindigen", true);
                }else{
                jsonobj.put("gameMoetEindigen", false);
                }
                jsonwriter.print(jsonobj);
                break;
            case "playcard":

                jsonobj.put("meldingActieKaart", gameEngine.speelKaart(actieKaartNaam));
                // gameEngine
                actiepunten = gameEngine.geefActieveSpeler().geefActiePunten();
                aankooppunten = gameEngine.geefActieveSpeler().geefKoopPunten();
                //bereken aantal munten doen alvorens weer te geven
                gameEngine.geefActieveSpeler().berekenMunten();
                //deck kaartnamen voorraard
                munten = gameEngine.geefActieveSpeler().geefMuntenVanSpeler();

                jsonobj.put("actiepunten", actiepunten.toString());
                jsonobj.put("aankooppunten", aankooppunten.toString());
                jsonobj.put("munten", munten.toString());
                hand = gameEngine.geefActieveSpeler().geefHand().geefDeckMetKaartNamen();
                jsonobj.put("hand0", hand.get(0).toLowerCase());
                jsonobj.put("aantalInHand", hand.size() + 1);
                for (i = 1; i < hand.size(); i++) {
                    jsonobj.put("hand" + i, hand.get(i).toLowerCase());

                }
                speelveld = gameEngine.geefActieveSpeler().geefSpeelVeld().geefDeckMetKaartNamen();
                jsonobj.put("aantalInSpeelveld", speelveld.size());
                for (i = 0; i < speelveld.size(); i++) {
                    jsonobj.put("speelveld" + i, speelveld.get(i).toLowerCase());

                }
                jsonwriter.print(jsonobj);
                jsonwriter.flush();
                break;

            case "login":

                checkLogin = login.login(gebruikersnaam, wachtwoord);
                loginObj.put("gebruikersnaam", gebruikersnaam);
                loginObj.put("wachtwoord", wachtwoord);
                loginObj.put("teller", teller);
                loginObj.put("doorTeSturenGebruiker1", teKrijgenGebruiker1);
                loginObj.put("doorTeSturenGebruiker2", teKrijgenGebruiker2);
                loginObj.put("doorTeSturenGebruiker3", teKrijgenGebruiker3);
                loginObj.put("doorTeSturenGebruiker4", teKrijgenGebruiker4);
                if (!teKrijgenGast.equals("no")) {

                    checkLogin = true;
                }

                if (checkLogin) {
                    if (teller == 2) {
                        if (!teKrijgenGast.equals("no")) {

                            arrayVanGebruikers.add(teKrijgenGast);
                            System.out.println(arrayVanGebruikers);
                        } else if (!teKrijgenGebruiker1.equals("")) {
                            gebruiker1 = teKrijgenGebruiker1;
                            arrayVanGebruikers.add(gebruiker1);
                            System.out.println(arrayVanGebruikers);
                        }

                    }

                    if (teller == 3) {
                        if (!teKrijgenGast.equals("no")) {

                            arrayVanGebruikers.add(teKrijgenGast);
                            System.out.println(arrayVanGebruikers);
                        } else if (!teKrijgenGebruiker2.equals("") && !teKrijgenGebruiker2.equals(gebruiker1)) {
                            gebruiker2 = teKrijgenGebruiker2;
                            arrayVanGebruikers.add(gebruiker2);
                            System.out.println(arrayVanGebruikers);
                        } else {
                            checkLogin = false;
                            teller -= 1;
                        }
                    }

                    if (teller == 4) {
                        if (!teKrijgenGast.equals("no")) {

                            arrayVanGebruikers.add(teKrijgenGast);
                            System.out.println(arrayVanGebruikers);
                        } else if (!teKrijgenGebruiker3.equals("") && !teKrijgenGebruiker3.equals(gebruiker1) && !teKrijgenGebruiker3.equals(gebruiker2)) {
                            gebruiker3 = teKrijgenGebruiker3;
                            arrayVanGebruikers.add(gebruiker3);
                            System.out.println(arrayVanGebruikers);
                        } else {
                            checkLogin = false;
                            teller -= 1;
                        }
                    }

                    if (teller == 5) {
                        if (!teKrijgenGast.equals("no")) {

                            arrayVanGebruikers.add(teKrijgenGast);
                            System.out.println(arrayVanGebruikers);
                        } else if (!teKrijgenGebruiker4.equals("") && !teKrijgenGebruiker4.equals(gebruiker1) && !teKrijgenGebruiker4.equals(gebruiker2) && !teKrijgenGebruiker4.equals(gebruiker3)) {
                            gebruiker4 = teKrijgenGebruiker4;
                            arrayVanGebruikers.add(gebruiker4);
                            System.out.println(arrayVanGebruikers);
                        } else {
                            checkLogin = false;
                            teller -= 1;
                        }
                    }

                    if (teller < Integer.parseInt(aantalSpelers)
                            + 1) {
                        teller += 1;

                    }

                }

                loginObj.put(
                        "success", checkLogin ? "true" : "false");
                System.out.println(
                        "test");
                System.out.println(
                        "object" + loginObj);
                out.print(loginObj);

                break;

            case "registreren":

                if (!gebruikersnaam.equals(
                        "") && !wachtwoord.equals("")) {
                    if (wachtwoord.equals(checkwachtwoord)) {
                        registratie = new Registratie();
                        connectieIsGelukt = registratie.Registratie(gebruikersnaam, wachtwoord);

                    } else {
                        checkRegistratie = false;
                    }
                }

                if (connectieIsGelukt
                        == false) {
                    checkRegistratie = false;
                }

                registreerObj.put(
                        "gebruikersnaam", gebruikersnaam);
                registreerObj.put(
                        "wachtwoord", wachtwoord);
                registreerObj.put(
                        "checkwachtwoord", checkwachtwoord);
                registreerObj.put(
                        "registratieSuccess", checkRegistratie ? "true" : "false");

                out.print(registreerObj);

                System.out.println(registreerObj);

                break;

            case "keuzeAantalSpelers":
                System.out.println(
                        "test");
                aantalSpelersObj.put(
                        "aantalSpelers", doorTeGevenAantalSpelers);
                out.print(aantalSpelersObj);

                arrayVanGebruikers.clear();

                System.out.println(aantalSpelersObj);
                aantalSpelers = doorTeGevenAantalSpelers;
                teller = 2;

                break;

            case "aantalSpelersDoorgeven":
                aantalSpelersObj.put(
                        "aantalSpelersDoorgeven", Integer.parseInt(aantalSpelers));
                out.print(aantalSpelersObj);

                System.out.println(
                        "aantal doorgegeven spelers " + aantalSpelers);

                break;
            case "endGame":
                //eindig game
                gameEngine.spelBeeindigen();    

                break;

            case "scoreboard":

                arrayVanGebruikers = gameEngine.geefSpelers();
                arrayEindscore = gameEngine.geefeindScore();

                scoreObj.put(
                        "gebruiker1", arrayVanGebruikers.get(0));
                scoreObj.put(
                        "gebruiker2", arrayVanGebruikers.get(1));
                scoreObj.put(
                        "score1", arrayEindscore.get(0));
                scoreObj.put(
                        "score2", arrayEindscore.get(1));

                if (gameEngine.geefSpelers()
                        .size() > 2) {
                    scoreObj.put("gebruiker3", arrayVanGebruikers.get(2));
                    scoreObj.put("score3", arrayEindscore.get(2));
                }

                if (gameEngine.geefSpelers()
                        .size() > 3) {
                    scoreObj.put("gebruiker4", arrayVanGebruikers.get(3));
                    scoreObj.put("score4", arrayEindscore.get(3));
                }

                scoreObj.put(
                        "aantalSpelers", gameEngine.geefSpelers().size());

                System.out.println(
                        "aantalSpelers " + gameEngine.geefSpelers().size());
                out.print(scoreObj);
                break;

            default:
                jsonobj.put(
                        "status", "NOK");
                jsonobj.put(
                        "errormessage", "Invalid operation");
                jsonwriter.print(jsonobj);

                break;
        }
        System.out.println(arrayVanGebruikers);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
