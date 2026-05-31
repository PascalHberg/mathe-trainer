# Vercel Deployment Guide - Mathe Trainer

## Step-by-Step Anleitung zur Bereitstellung auf Vercel

### 📋 Voraussetzungen
- Vercel Account (kostenlos auf vercel.com)
- GitHub Account mit diesem Repository
- Java 11+ und Maven 3.6+ (lokal für Tests)

### 🚀 Deployment Schritte

#### 1. **Vercel Account erstellen**
   - Gehe zu https://vercel.com
   - Melde dich mit deinem GitHub Account an
   - Autorisiere Vercel zum Zugriff auf deine Repositories

#### 2. **Repository mit Vercel verbinden**
   - Gehe zu https://vercel.com/new
   - Wähle "Import Git Repository"
   - Suche `PascalHberg/mathe-trainer`
   - Klicke auf "Import"

#### 3. **Build-Einstellungen konfigurieren**
   - **Framework Preset**: Other (da Java-Projekt)
   - **Build Command**: `mvn clean package -DskipTests`
   - **Output Directory**: `target`
   - **Install Command**: (leer lassen)

#### 4. **Umgebungsvariablen (optional)**
   Falls deine App Umgebungsvariablen benötigt:
   - Gehe zu Project Settings → Environment Variables
   - Füge erforderliche Variablen hinzu

#### 5. **Deploy**
   - Klicke auf "Deploy"
   - Vercel führt automatisch:
     - Maven build aus
     - JAR-Datei erzeugt
     - App deployt

#### 6. **Überprüfe den Deployment**
   - Warte auf grünen Checkmark
   - Klicke auf die Preview URL
   - Testen Sie die Anwendung

### 🔄 Automatische Deployments
Nach erfolgreichem Initial-Deployment:
- Jeder Push zu `main`/default branch = automatisches Deployment
- Pull Requests erhalten Preview-URLs
- Rollback zu vorherigen Versionen ist möglich

### ⚙️ Weitere Optionen

**Custom Domain hinzufügen:**
1. Settings → Domains
2. Füge deine Domain hinzu
3. Aktualisiere DNS-Einträge nach Anleitung

**Vercel CLI (optional):**
```bash
npm i -g vercel
vercel --prod
```

### 📊 Performance & Limits
- **Kostenlos bis zu**: 6000 Build-Minuten/Monat
- **Execution Timeout**: 60 Sekunden (Pro) / 10 Sekunden (Free)
- **RAM**: 1GB verfügbar

### 🐛 Troubleshooting

**Build schlägt fehl:**
- Überprüfe `pom.xml` auf fehlerhafte Dependencies
- Siehe Build Logs in Vercel Dashboard
- Stelle sicher, Java 11+ wird verwendet

**App startet nicht:**
- Prüfe die `vercel.json` Konfiguration
- Überprüfe RAM-Anforderungen der JVM
- Implementiere Serverless Functions falls nötig

### 📝 Anpassungen für Production
Falls die App größer wird:
1. Erwäge Plan-Upgrade für mehr Build-Minuten
2. Implementiere Caching-Strategie
3. Optimiere JAR-Größe (shading, minification)

---
**Fragen?** Siehe Vercel Docs: https://vercel.com/docs
