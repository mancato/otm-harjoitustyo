# Asteroids
Asteroids, klassikopeli vuodelta 1979. Tavoitteena on ampua asteroideja avaruusaluksella.

Release löytyy täältä: [Release](https://github.com/mancato/otm-harjoitustyo/releases/tag/1.0)

## Dokumentaatio

[Käyttöohje](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaativuusmäärittely](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/vaativuusmaarittely.md)

[Tuntikirjanpito](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

## Maven
Projektin compilaaminen:
```
mvn compile exec:java -Dexec.mainClass=ui.Main

```
Testit toimivat normaalisti
```
mvn test
```
ja jacoco:report (HUOM! raportissa saattaa lukea väärä rivikattavuus pakkauksen io kohdalla vaikkei sitä testata toistaiseksi ollenkaan):
```
mvn jacoco:report
```
sekä .jar:n generointi:
```
mvn package
```
Checkstyle:
```
mvn jxr:jxr checkstyle:checkstyle
```
JavaDoc:
```
mvn javadoc:javadoc
```
