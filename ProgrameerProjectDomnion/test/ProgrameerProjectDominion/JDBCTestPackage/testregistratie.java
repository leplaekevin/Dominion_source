/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrameerProjectDominion.JDBCTestPackage;

import ProgrameerProjectDominion.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Gebruiker
 */
public class testregistratie {

    private Registratie regi;
    private Login login;
    private ServletGameplay test;

    @Before
    public void setUp() {
        regi = new Registratie();
        login = new Login();
      //  test = new test();
    }

//    @Test
//    public void testRegistratie() {
//        regi.Registratie();
//    }
    @Test
    public void testRegi() {
        regi.Registratie("jefke","123");
    }
    
    @Test
    public void testlogin() {
        assertEquals(true,login.login("michiel","123"));
    }
//     @Test
//    public void testdoorgeven() {
//        
//       System.out.println(Spelers);
//    }
    
}
