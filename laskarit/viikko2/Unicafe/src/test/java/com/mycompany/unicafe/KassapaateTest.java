package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(400);
    }

    @Test
    public void alustusOikein(){
        assertTrue(kassa.kassassaRahaa() == 100000 && kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void edullinenKateismaksuHyvaksytty(){
        assertTrue(kassa.syoEdullisesti(240)==0 && kassa.kassassaRahaa() == 100240 && kassa.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void edullinenKateismaksuHylatty(){
        
        assertTrue(kassa.syoEdullisesti(230)==230 && kassa.kassassaRahaa() == 100000 && kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void maukasKateismaksuHyvaksytty(){
        assertTrue(kassa.syoMaukkaasti(400)==0 && kassa.kassassaRahaa() == 100400 && kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void maukasKateismaksuHylatty(){
        assertTrue(kassa.syoMaukkaasti(390)==390 && kassa.kassassaRahaa() == 100000 && kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void edullinenKorttimaksuHyvaksytty(){
        assertTrue(kassa.syoEdullisesti(kortti) && kassa.edullisiaLounaitaMyyty() == 1 && kortti.saldo()==160 && kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void edullinenKorttimaksuHylatty(){
        kassa.syoEdullisesti(kortti);
        assertTrue(!kassa.syoEdullisesti(kortti) && kassa.edullisiaLounaitaMyyty() == 1 && kortti.saldo()==160 && kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void maukasKorttimaksuHyvaksytty(){
        assertTrue(kassa.syoMaukkaasti(kortti) && kassa.maukkaitaLounaitaMyyty() == 1 && kortti.saldo()==0 && kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void maukasKorttimaksuHylatty(){
        kassa.syoMaukkaasti(kortti);
        assertTrue(!kassa.syoMaukkaasti(kortti) && kassa.maukkaitaLounaitaMyyty() == 1 && kortti.saldo()==0 && kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void ArvonLatausKortilleOnnistuu(){
        kassa.lataaRahaaKortille(kortti,10);
        assertTrue(kassa.kassassaRahaa() == 100000+10 && kortti.saldo() == 410);
    }
    @Test
    public void ArvonLatausKortilleEiOnnistu(){
        kassa.lataaRahaaKortille(kortti,-10);
        assertTrue(kassa.kassassaRahaa() == 100000 && kortti.saldo() == 400);
    }        
}
