# Käyttöohje
Lataa tiedosto ---
## Ohjelman käynnistäminen
```
java -jar Asteroids.jar
```

## Päävalikko
Päävalikosta pystyy käynnistämään pelin painamalla P-näppäintä. Pelin ohjeita voi katsella painamalla G-näppäintä pohjassa. Samaan tapaan 20 korkeinta pelin tulosta saa näkyviin "Hall of Famessa" painamalla pohjassa H-näppäintä.

## Pelinäkymä

### Pelin kulku
Pelin tavoitteena on väistellä aluksella asteroideja ja tuhota niitä aluksen tykin avulla. Kun asteroidia ampuu, saa pelaaja pisteitä ja asteroidi hajoaa kahdeksi pienemmäksi asteroidiksi. Kun asteroidit ovat tarpeeksi pieniä, ne häviävät kokonaan. Kun kaikki asteroidit on tuhottu, ilmestyy peliin lisää asteroideja. Pelaajalla on rajallinen määrä elämiä, joita menettää kun alus törmää asteroidiin.

### Aluksen kontrollit
Alusta liikutetaan nuolinäppäimillä. Ylös-napista aluksen moottori aktivoituu, sivulle-napeista alusta pystyy kääntämään. Aluksen tykkiä käytetään välilyöntiä painamalla.

### Muut pelinäkymän kontrollit
Painamalla R-näppäintä peli alkaa uudestaan alusta. Q-näppäimellä pääsee takaisin pävalikkoon. Pelin päätyttyä "Game over"-näkymässä pelaaja voi asettaa kolmimerkkisen tunnuksen nuolinäppäimiä käyttämällä. Kun tunnus on syötetty, Enter:iä painamalla pelin pistemäärä, päivämäärä ja tunnus tallentuvat.
