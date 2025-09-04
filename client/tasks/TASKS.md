# Tasks

Client side tasks:
- [ ] Add proper validation handling for a single field
- [ ] Use proper enumerations for form-field names and error messages
- [ ] Add angular translation and translation files (https://angular.dev/guide/i18n)
- [ ] Create the nullable-component wrapper


Überlegungen zum Komponentenmodell:
- Wie wird die Nullability umgesetzt?
  - jedes Nullable-Feld ist eine FormGroup mit zwei Feldern
  - Die generische Nullable-Komponente beeinflusst die andere Komponente (d.h. disablen)
  - (?) Wird das Disable-Flag vererbt oder überschrieben?
  - Wird das in der Feld-Eigenen Komponente eingebaut? Oder als Wrapper?
- Für jedes Feld eine Komponente?
  - (+) Würde das ersetzen durch eine andere/manuelle Komponente vereinfachen.
  - (+) Würde verschachtelte Items vereinfachen, insb. das Anzeigen in einem anderen Bereich
  - (+) Würde ev. die Custom-Validierung vereinfachen, weil alles schön in der Komponente wäre
  - (-) Erzeugt wieder sehr viele Komponenten
  - (-) "Verwässert" das HTML
  - (?) Wie/Wo würde man die Nullability einbauen (weil: jedes Nullable-Feld ist eine FormGroup mit zwei Feldern)

Guter Link wie man Enums übersetzt: https://dev.to/constjs/angular-translate-enums-i18n-57k3
