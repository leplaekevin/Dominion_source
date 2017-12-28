/*nog te doen
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import java.util.*;

/**
 *
 * @author Pc_Kevin
 */
public class Kaart {
    private JdbcTemplate template;
    private ArrayList kaartdata;
    
    
    public Kaart ()
    {}
    
    public Kaart (String soort)
    {
        kaartDataOphalen(soort);
    }
    
    public ArrayList geefKaartData()
    {
        return kaartdata;
    }
    
    public ArrayList kaartDataOphalen(String soort)
    { 
        template =new JdbcTemplate();
        kaartdata = template.DBselect("kaarten", "kaartnaam", soort);
        return kaartdata;
    }
    
    public int geefId(){
       String id = kaartdata.get(0).toString();
       return Integer.parseInt(id);
    }
    
    public String geefKaartnaam(){

       return kaartdata.get(1).toString();
    }
    
    public int geefKaartenTrekken(){
       String kaartenTrekken = kaartdata.get(2).toString();
       return Integer.parseInt(kaartenTrekken);
    }
    
    public int geefMunten(){
       String munten = kaartdata.get(3).toString();
       return Integer.parseInt(munten);
    }
    
    public int geefActies(){
       String acties = kaartdata.get(4).toString();
       return Integer.parseInt(acties);
    }
    
    public int geefKopen(){
       String kopen = kaartdata.get(5).toString();
       return Integer.parseInt(kopen);
    }
    
    public int geefPunten(){
       String punten = kaartdata.get(6).toString();
       return Integer.parseInt(punten);
    }
    
    public int geefKost(){
       String kost = kaartdata.get(7).toString();
       return Integer.parseInt(kost);
    }
    
    public int geefSpecialeActies(){
       String specialeActies = kaartdata.get(8).toString();
       return Integer.parseInt(specialeActies);
    }
    
    public int geefKaartAantal(){
    String specialeActies = kaartdata.get(9).toString();
    return Integer.parseInt(specialeActies);
    }
    
    @Override
    public String toString()
    {
       return kaartdata.toString();
    }
}