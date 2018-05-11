# Arkkitehtuurikuvaus

## Rakenne
Ohjelman pakkausrakenne on seuraavanlainen:

<img src="https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png" width="300">

Pakkauksessa ui sijaitsee main-luokka, joka sisältää ohjelman rungon. Main kommunikoi ui-pakkauksesta löytyvien näkymien, tiedon tallennuksen/lataamisen, pelin näppäinkarttojen ja pelin olioiden kanssa. Pakkaus io sisältää tiedon tallennuksen ja lataamisen sekä kartoitukset näppäimille eri tilanteisiin. Logic sisältää kaikki pelissä tarvittavat oliot.

Alla olevassa kuvassa näkyy luokkakaavio ohjelman rakenteesta (ei sisällä graafista käyttöliittymää):

<img src="https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/465b64f7.png" width="600">

## Käyttöliittymä

Ohjelmassa on kaksi päänäkymää, päävalikko sekä itse peli. Valikosta pystyy käynnistämään pelin ja pääsee tarkastelemaan pelin ohjetta ja aiempia pistemääriä. Pelinäkymässä näkyy myös pelin päätyttyä "Game Over"-ruutu, johon pelaaja voi asettaa kolmen merkin pituisen "nimen".

## Sovelluslogiikka

Kuvassa näkyy kuinka aluksella ampuminen tapahtuu kun välilyöntiä painetaan:

<img src="https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Aluksella%20ampuminen.png" width="600">

## Tietojen tallennus

Sovellus tallentaa pelin huipputulokset erilliseen tekstitiedostoon. Tiedostosta myös ladataan tulokset sovellukseen tarkastelua varten.
