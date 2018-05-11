# Testausdokumentti
Ohjelman luokkien testaus tapahtuu JUnit-testeillä, ui-pakkausta ei testata ollenkaan.

## JUnit-testit
logic-pakkaus: Koko logic-pakkaus on testattu, kaikki pakkauksen sisältämien olioiden metodit toimivat kuten pitääkin.
Asteroidien toimintaa testaa [AsteroidTest](https://github.com/mancato/otm-harjoitustyo/blob/master/src/test/java/logictest/AsteroidTest.java), 
AsteroidFieldin [AsteroidFieldTest](https://github.com/mancato/otm-harjoitustyo/blob/master/src/test/java/logictest/AsteroidFieldTest.java),
aluksen [ShipTest](https://github.com/mancato/otm-harjoitustyo/blob/master/src/test/java/logictest/ShipTest.java) ja 
ammusten [AmmoTest](https://github.com/mancato/otm-harjoitustyo/blob/master/src/test/java/logictest/AmmoTest.java).

io-pakkaus: Controls-metodia ei ole testattu, sillä se vaatii näppäimien painamista, ja sen toiminta on melko itsestään selvää. Tiedon tallentamiseen tarkoitettua FileHandler-luokkaa on testattu, testit kokeilevat tiedostoon kirjoittamista, korkeimman tuloksen lukemista ja kaikkien tulosten lataamista tiedostosta. FileHandlerin testauksesta vastaa [FileHandlerTest](https://github.com/mancato/otm-harjoitustyo/blob/master/src/test/java/iotest/FileHandlerTest.java)
