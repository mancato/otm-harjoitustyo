# Asteroids
Asteroids, klassikopeli vuodelta 1979. Tavoitteena on ampua asteroideja avaruusaluksella. 

Release löytyy täältä: [Release](https://github.com/mancato/otm-harjoitustyo/releases/tag/asteroids)

## Dokumentaatio
[Vaativuusmäärittely](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/vaativuusmaarittely.md)

[Tuntikirjanpito](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/mancato/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Maven
Projektin compilaaminen:
```
mvn compile exec:java -Dexec.mainClass=ui.Main

```
Testit toimivat normaalisti
```
mvn test
```
ja jacoco:report:
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
