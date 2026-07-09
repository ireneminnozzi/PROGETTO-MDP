THE QUIET FOREST

Progetto per il corso di Metodologie di Programmazione (AA 2025/26) - Università di Camerino.

Applicativo Java con interfaccia grafica sviluppato in JavaFX, ispirato ai classici RPG: il giocatore interpreta una strega che esplora la foresta di Lunargento, 
raccoglie risorse, combatte nemici, crea pozioni, sblocca abilità e completa quest.
Il gioco utilizza una mappa esplorabile, un sistema di combattimento a turni, inventario, crafting e musica di sottofondo.

Descrizione
Il gioco si sviluppa in quattro fasi principali:

1. Capanna – Introduzione
Il giocatore inizia nella capanna della strega, dove può:

leggere la descrizione iniziale

prepararsi all’esplorazione

accedere all’inventario e alle abilità

2. Esplorazione della Foresta
La foresta è composta da diverse aree collegate, ogni area contiene:

risorse raccoglibili

nemici specifici

collegamenti verso altre zone

Le risorse si rigenerano automaticamente quando il giocatore torna alla capanna.

3. Combattimento
Sistema a turni con:

statistiche del nemico

armi e magie

abilità sbloccabili

calcolo del danno

effetti grafici

Alla fine del combattimento il giocatore ottiene loot e può proseguire l’esplorazione.

4. Crafting e Abilità
Il giocatore può:

raccogliere erbe e materiali

creare pozioni tramite ricette

sbloccare abilità nel proprio skill tree

potenziare attacco, difesa e magia


Prerequisiti
JDK 21 o superiore
Gradle

Istruzioni
git clone https://github.com/ireneminnozzi/PROGETTO-MDP.git
cd PROGETTO-MDP

Build
./gradlew build        # Linux/Mac
.\gradlew build        # Windows PowerShell
Esecuzione
./gradlew run          # Linux/Mac
.\gradlew run          # Windows PowerShell

Struttura del progetto
Il codice è organizzato nel package it.unicam.cs.mpgc.rpg130657 seguendo il pattern MVC:

Package	Responsabilità
model:	Logica e regole di gioco, senza dipendenze da GUI o persistenza
persistence:	Interfacce repository e implementazioni JSON con Gson
controller:	Unico mediatore tra GUI e model/persistence
gui	:Interfaccia grafica Swing
app:	Classe Main, composition root dell'applicazione

Comandi
Mouse	Interazione con pulsanti UI
Click	Raccolta risorse / combattimento
UI	Navigazione tra scene (Capanna, Foresta, Combattimento)
