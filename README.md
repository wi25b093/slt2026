# Java - Game - TicTacToe

**FHTW – BWI-VZ-2-SS2026-SLT-DE/165298**
Projekt zur Veranschaulichung des Software Lifecycle anhand einer Tic-Tac-Toe-Implementierung in Java.

Repository: <https://github.com/wi25b093/slt2026>

---

## User Stories

| ID    | Story |
|-------|-------|
| US-01 | Als Spieler möchte ich einen Zug machen, indem ich ein leeres Feld auswähle. |
| US-02 | Als Spieler möchte ich den aktuellen Spielstand sehen. |
| US-03 | Als Spieler möchte ich bei Sieg/Niederlage/Unentschieden benachrichtigt werden. |
| US-04 | Als Spieler möchte ich nach dem Spielende ein neues Spiel starten können. |

---

## Build & Ausführung

### Aus IntelliJ
1. Projekt öffnen, Maven sync abwarten
2. `Main.java` öffnen → grünen Run-Pfeil klicken

### Über die Konsole
\`\`\`bash
mvn clean package
java -jar target/tictactoe.jar
\`\`\`

Das fertige JAR steht zusätzlich nach jedem Merge auf `main` unter
**Releases** auf GitHub zum Download bereit.

---

## Tests

\`\`\`bash
mvn test
\`\`\`

- JUnit 5
- Jede Methode hat mindestens 2 Tests (positiv + negativ)
- Tests laufen automatisch in der GitHub-Actions-CI-Pipeline

---

## Software Lifecycle / Workflow

Der Ablauf für eine User Story:

1. **Planung – Kanban Board**: User Story im GitHub Project (Kanban) als Item, Spalten `Todo → In Progress → Done`, jede Story hat eindeutige ID (US-01 ... US-04).
2. **Story übernehmen**: von `Todo` auf `In Progress` ziehen, sich als Assignee setzen.
3. **Feature-Branch anlegen**: Branch-Naming `feature/US-<id>-<kurzbeschreibung>`, z.B. `feature/US-01-make-move`.
4. **Entwicklung**: Kleine, sprechende Commits wie `feat(US-01): add place() method to Board`, lokal mit `mvn test` validieren.
5. **Push & Pull Request**: Branch pushen, PR gegen `main` öffnen, CI muss grün sein.
6. **Merge**: über GitHub-UI mergen, Branch löschen, Story auf `Done` ziehen.
7. **Deployment**: Merge auf `main` triggert Release-Workflow → JAR wird als GitHub Release veröffentlicht.

---

## CI/CD

Zwei GitHub-Actions-Workflows in `.github/workflows/`:

| Workflow      | Datei         | Trigger                          | Zweck |
|---------------|---------------|----------------------------------|-------|
| **CI**        | `ci.yml`      | Push auf jeden Branch, PR        | `mvn clean compile` + `mvn test` |
| **CD/Release**| `release.yml` | Push/Merge auf `main`            | Baut `tictactoe.jar`, legt GitHub-Release an |

### Branch Protection
Der `main`-Branch ist geschützt: direktes Pushen ist nicht erlaubt, Merge nur über Pull Request mit erfolgreichem CI-Check.

---

## Projektstruktur

\`\`\`
slt2026/
├── .github/workflows/
│   ├── ci.yml
│   └── release.yml
├── src/
│   ├── main/java/at/fhtw/tictactoe/
│   │   ├── Main.java
│   │   ├── TicTacToe.java
│   │   ├── Player.java
│   │   └── Board.java
│   └── test/java/at/fhtw/tictactoe/
│       ├── TicTacToeTest.java
│       ├── PlayerTest.java
│       └── BoardTest.java
├── .gitignore
├── pom.xml
└── README.md
\`\`\`
