# Mathe Trainer - Java Edition

**High-Performance Math Learning Application**

Diese Anwendung ist ein Java-Port der Python-Version mit erheblichen Leistungsverbesserungen.

## Features

- ✨ **Addition, Subtraktion und Multiplikation üben**
- 🎯 **Anpassbare Schwierigkeitsgrade** (Max-Wert 5-100)
- 🔊 **Text-to-Speech** für Aufgabenvortrag
- 📊 **Highscore-Verwaltung** mit persistenter Speicherung
- ⚡ **Performance-optimiert** mit caching und effizienten Algorithmen

## Anforderungen

- **Java 11 oder höher**
- **Maven 3.6+**

## Installation

```bash
mvn clean package
```

## Ausführung

```bash
java -jar target/mathe-trainer-1.0.0.jar
```

Oder direkt mit Maven:

```bash
mvn exec:java -Dexec.mainClass="com.mathetrainer.Main"
```

## Performance-Verbesserungen gegenüber Python

1. **Schnellerer Start** - Compiled bytecode statt Interpretation
2. **Bessere Speicherverwaltung** - JVM Garbage Collection
3. **Threading für Speech** - Non-blocking Text-to-Speech
4. **Caching** - Highscore im RAM gecacht
5. **Effiziente Swing GUI** - Native Look & Feel
6. **Statische Ressourcen** - Random instance wiederverwendet

## Projektstruktur

```
├── src/
│   ├── main/java/com/mathetrainer/
│   │   ├── Main.java                 # Entry point
│   │   ├── logic/MathLogic.java       # Math problem generation
│   │   ├── data/StatsManager.java     # Score persistence
│   │   ├── audio/SpeechEngine.java    # Text-to-Speech
│   │   └── ui/MatheTrainerGUI.java    # GUI implementation
│   └── test/...
├── pom.xml                           # Maven configuration
└── data/                             # Highscore storage
```

## Lizenz

MIT License - siehe LICENSE Datei
