# Arkkitehtuurikuvaus

## Rakenne
Ohjelman pakkausrakenne on seuraavanlainen:

<img src="https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png" width="300">

Pakkauksessa ui sijaitsee main-luokka, joka sisältää ohjelman rungon. Main kommunikoi graafisen käyttöliittymän, tiedon tallennuksen/lataamisen ja pelin olioiden kanssa. Pakkaus io sisältää tiedon tallennuksen ja lataamisen. Logic sisältää kaikki pelissä tarvittavat oliot.

Alla olevassa kuvassa näkyy luokkakaavio ohjelman rakenteesta (ei sisällä graafista käyttöliittymää):

<img src="https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/2fd135f8.png" width="400">

## Käyttöliittymä

Ohjelmassa on kaksi päänäkymää, päävalikko sekä itse peli. Valikosta pystyy käynnistämään pelin ja pääsee tarkastelemaan pelin ohjetta ja aiempia pistemääriä. Pelinäkymässä näkyy myös pelin päätyttyä "Game Over"-ruutu, johon pelaaja voi asettaa kolmikirjaimisen "nimen".

## Sovelluslogiikka

Kuvassa näkyy kuinka aluksella ampuminen tapahtuu kun välilyöntiä painetaan:

<img src="https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Aluksella%20ampuminen.png" width="600">
