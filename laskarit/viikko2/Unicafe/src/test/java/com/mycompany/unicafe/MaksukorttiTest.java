package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void latausToimii(){
        kortti.lataaRahaa(10);
        assertTrue(kortti.saldo()==20);
    }

    @Test
    public void saldonVahennys(){
        assertTrue(kortti.otaRahaa(10) && kortti.saldo()==0);
    }

    @Test
    public void saldonLiiallinenVahennys(){
        assertTrue(!kortti.otaRahaa(20) && kortti.saldo()==10);
    }
}
