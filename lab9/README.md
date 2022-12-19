## Zadanie 1 
Wymyslic wlasna maszyne stanow (maszyna stanow jest modelowana przez sieć Petri, w której każda tranzycja ma dokładnie jedno miejsce wejściowe i jedno miejsce wyjściowe), zasymulowac przyklad i dokonac analizy grafu osiagalnosci oraz niezmiennikow

### Sieć:

<div align="center">
<img width="500px" src="/lab9/assets/graph1-1.gif">
</div>

### Analiza niezmienników:

<div align="center">
<img width="500px" src="/lab9/assets/graph1-3.png">
</div>

Sieć jest pokryta przez pozytywne niezmienniki miejsc, z czego wynika, że jest ograniczona.
Wszystkie niezmienniki tranzycji są dodatnie, więc sieć może być też żywa.


### Graf osiągalności:

<div align="center">
<img width="500px" src="/lab9/assets/graph1-2.png">
</div>

Graf osiągalności jest silnie spójny, z tego wynika, że sieć ta jest odwracalna

## Zadanie 2 
Zasymulowac siec jak ponizej.
Dokonac analizy niezmiennikow przejsc. Jaki wniosek mozna wyciagnac o odwracalnosci sieci ? Wygenerowac graf osiagalnosci. Prosze wywnioskowac z grafu, czy siec jest zywa. Prosze wywnioskowac czy jest ograniczona. Objasnic wniosek.

### Sieć:

<div align="center">
<img width="500px" src="/lab9/assets/graph2-1.gif">
</div>

### Analiza niezmienników:

<div align="center">
<img width="500px" src="/lab9/assets/graph2-3.png">
</div>

Sieć nie ma niezmienników przejść, co za tym idzie nie wiemy czy jest ograniczona i żywa.
Sieć nie posiada niezmienników tranzycji, co sugeruje, że nie jest ona odwracalna.


### Graf osiągalności:

<div align="center">
<img width="500px" src="/lab9/assets/graph2-2.png">
</div>

Graf osiągalności potwierdza, że nie jest odwracalna ani żywa ponieważ powstaje zakleszczenie po trafieniu do S3.

## Zadanie 3
zasymulowac wzajemne wykluczanie dwoch procesow na wspolnym zasobie. Dokonac analizy niezmiennikow miejsc oraz wyjasnic znaczenie rownan (P-invariant equations). Ktore rownanie pokazuje dzialanie ochrony sekcji krytycznej ?

### Sieć:

<div align="center">
<img width="500px" src="/lab9/assets/graph3-1.gif">
</div>

1. Na początku  umożliwiamy udostępnienie zasobu jednemu z dwóch procesów. 
2. W zależności od wyboru można wykonać tylko 1 tranzycję (zajęciu zasobu przez jeden proces)
3. Następnie drugi proces oczekiwaniu na zwolnienie.
4. Cały proces powtarza się.

### Analiza niezmienników:

<div align="center">
<img width="500px" src="/lab9/assets/graph3-2.png">
</div>

Działanie ochrony sekcji krytycznej :
**M(P1) + M(P2) + M(P3) = 1**
Pokazuje, że zasób może być tylko w trzech stanach
* Dostępny dla obu procesów
* Dostępny dla pierszego procesu
* Dostępny dla drugiego procesu

Pozostałe dwa równanaia informują nas o stanie znajdowania się w sekcji krytycznej i poza nią

## Zadanie 4 
Uruchomic problem producenta i konsumenta z ograniczonem buforem (mozna posluzyc sie przykladem, menu:file, examples). Dokonac analizy niezmiennikow. Czy siec jest zachowawcza ? Ktore rownanie mowi nam o rozmiarze bufora ?

### Sieć:

<div align="center">
<img width="500px" src="/lab9/assets/graph4-1.gif">
</div>

### Analiza niezmienników:

<div align="center">
<img width="500px" src="/lab9/assets/graph4-2.png">
</div>

O rozmiarze bufora informuje nas równanie:
**M(P6) + M(P7) = 3**
* P7 przechowuje liczbę miejsc wolnych w buforze. 
* P6 przechowuje liczbę miejsc zajętych w buforze.

Sieć jest zachowawcza gdyż każda tranzycja ma tyle samo miejsc wejściowych co wyjściowych.

## Zadanie 5
Stworzyc symulacje problemu producenta i konsumenta z nieograniczonym buforem. Dokonac analizy niezmiennikow. Zaobserwowac brak pelnego pokrycia miejsc.

### Sieć:

<div align="center">
<img width="500px" src="/lab9/assets/graph5-1.gif">
</div>

Można zaobserować że po każdej tranzycji T0 ( Producent) liczba znaczników w miejscu P6 rosła o 1, natomiast po T1 (Konsument) malała o 1

### Analiza niezmienników:

<div align="center">
<img width="500px" src="/lab9/assets/graph5-2.png">
</div>

Sieć nie jest pokryta całkowicie niezmienikami miejsc, a dokładnie brakuje miejsca P6, które to jest nieskończonym buforem. Z tego powodu nie wiemy czy sięć jest ograniczona , lecz przez obserwacje działania sieci dowiadujemy się że nie jest ona ograniczona.

## Zadanie 6
Zasymulowac prosty przyklad ilustrujacy zakleszczenie. Wygenerowac graf osiagalnosci i zaobserwowac znakowania, z ktoroch nie mozna wykonac przejsc. Zaobserwowac wlasciwosci sieci w "State Space Analysis". Ponizej przyklad sieci z mozliwoscia zakleszczenia (mozna wymyslic inny):

### Sieć:

<div align="center">
<img width="500px" src="/lab9/assets/graph6-1.gif">
</div>

### Graf osiągalności:

<div align="center">
<img width="500px" src="/lab9/assets/graph6-2.png">
</div>

### State Space Analysis:

<div align="center">
<img width="500px" src="/lab9/assets/graph6-3.png">
</div>


