# java-dhbw-exam

## Requirements (German)
### Finale Aufgabe
Schreiben Sie eine Bankanwendung „BankingApp“:
#### Bank Kunde:
- [x] Login/Logout
- [x] Benutzerdaten pflegen (Name, Geburtsdatum, Adresse, E-Mail, Telefonnummer, ...) - d.h. anlegen, ändern, löschen
- [x] Konto anlegen (z.B. Girokonto, Festgeldkonto, Kreditkarte, Depot, ...)
- [x] Konto ändern, 
- [x] Konto auflösen, 
- [x] Zahlungseingang, 
- [x] Zahlungsausgang, 
- [x] Überweisung auf anderes Konto, 
- [x] Prüfungen, ob Konto gedeckt, 
- [x] Dispo anzeigen
#### Bänker:
- [x] Login/Logout
- [ ] Report 
  - über alle zugehörigen Kunden
  - alle Konten
  - Report über alle Kunden mit einem negativen Kontostand
- [ ] Freigabe von neuen Konten
- [ ] bestimmen ob und wie weit der Kunde ins Minus darf
#### Allgemeines:
- [x] Das Programm muss eine UI-Schnittstelle haben mit Java AWT/ Swing.
- [x] Das Programm soll Intuitive Bedienbar sein.
- [ ] Das Programm muss seine Daten dauerhaft abspeichern (z.B. Persistenz mit JDBC). 
  - D.h. wenn neue Benutzer angelegt wurden und neue Konten 
    dann müssen diese Daten nach Neustart im Programm verfügbar sein.
- [x] Überlegen Sie sich ein passendes, realistische Klassen-/Objektmodell
  - (z.B. Konto, Girokonto, Festgeldkonto, Kreditkarte, Depot, ...)
- [x] Leiten Sie sinnvoll ab, 
  - verteilen Sie die Attribute und Methoden so, dass die Objektorientierung einen Sinn ergibt
- [x] Es muss eine sinnvolle Vererbungshierarchie erkennbar sein.
  - Arbeiten Sie ggf. mit abstrakten Klassen
- [x] Setzen Sie ggf. Polymorphie ein
- [x] Denken Sie an Datenkapselung
- [ ] Denken Sie an die Fehlerbehandlung (Eingabe, Konvertierungen, Überläufe, ...)
- [ ] Formatieren Sie im Team konsistent
- [x] Wählen Sie sinnvolle Bezeichner (entscheiden Sie sich für eine Sprache)
#### Team-Setup und Projektplanung:
- [x] Überlegen Sie wie Sie an das Projekt heran gehen wollen
- [x] Überlegen Sie sich ein Vorgehensmodell und einen Zeitplan
- [x] Legen Sie fest wer was bis wann macht
- [x] Beginnen Sie mit dem Datenmodel
- [x] Abstrahieren Sie ein Klassenmodell
     - Attribute, Vererbung, Methoden
- [ ] Dokumentieren Sie die Ergebnisse
#### Abgabeinhalte:
- [ ] Als Zip-Datei mit Namen „Banking App <Gruppen-Id>.zip“
- [x] Jeglichen Source Code und komplett lauffähiges Projekt
- [x] Packen Sie das Projekt in ausführbare „jar“-Files. 
  - Die „jar“-Files sollen auf der Konsole ausführbar sein : java -jar BankingApp ...
- [x] Komplette Dokumentation inkl. Anleitung, 
    - wie das Programm zu installieren, 
    - auszuführen
    - zu bedienen ist 
      - Pfade
      - Login-Daten wie Benutzer
      - PW
      - Demodaten, 
    - Design Dokumente,
    - Informationen zu externer Software wie z.B. Datenbanken, 
    - Web-Server usw.
- [ ] Klassendiagramm
- [x] Daran denken initial auch ein System mit Daten zu liefern.
- [ ] Video als Demonstration aller Funktionalität KEINEN SOURCE CODE. 
- [ ] Demonstration von fehlerhafter Eingabe durch den Benutzer


## Test Credentials
| Username | Password      | Role |
|----------|---------------|------|
| 85167524 | test          | Client |
| 77067103 | test          | Client |
| 1 | (kein passwort)          | Employee |

## Dependencies
- see `pom.xml` for maven dependencies
