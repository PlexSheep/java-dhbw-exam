# Herangehensweise
1. Datenbankstruktur
2. Klassenstruktur
3. Arbeitsteilung
4. Zeitplanung
5. Implementierung
6. Zusammenfassung

# Zeitplan
| Datum      | Ziel                                                           | Person                            |
|------------|----------------------------------------------------------------|-----------------------------------|
| 2023-05-22 | Datenbankstruktur, Klassenstruktur & Arbeitsteilung            | Alle                              |
| 2023-06-01 | Implementierung der Klassen die unsere Daten darstellen sollen | Christoph & Domminik              |
| 2023-06-05 | Implementierung des GUI ohne Backend Funktionen                | Marco & Moritz                    |
| 2023-06-05 | Implementierung des Datenbank Controllers                      | Tobias                            |
| 2023-06-25 | Implementierung der Verbindung von Backend und Frontend        | Marco, Christoph & Moritz         |
| 2023-06-27 | Bugfixing                                                      | Marco, Christoph, Tobias & Moritz |
| 2023-06-28 | Dokumentation                                                  | Alle                              |

# Datenmodell
- Accounts Table
  - IBAN (primary key)
  - type
  - balance
  - debtLimit
- Employee Table
  - ID (primary key für DB)
  - name
  - addresse
  - email
  - telephone
  - Birthday
  - user_id (Id die bei der Anmeldung verwendet wird)
- Client Table
  - ID (primary key für DB)
  - name
  - addresse
  - email
  - telephone
  - Birthday
  - user_id (Id die bei der Anmeldung verwendet wird)
- Client-zu-Account Table
  - client (user_id)
  - account (iban)
- Transaction Table
  - ID (primary key)
  - sender
  - recipient
  - amount
  - timestamp

# Klassenmodell
(siehe Klassendiagramm)
- Person
  - Client
  - Employee
- Account
  - GiroAccount
  - CreditAccount
  - FixedAccount
  - DebitAccount
- DatabaseController
- Authentication
- GUI
- UserData
- Transaction
- ChangeCredential
- AdminConsole
