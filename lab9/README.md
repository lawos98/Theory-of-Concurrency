## Zadanie 1 
Wymyslic wlasna maszyne stanow (maszyna stanow jest modelowana przez sieć Petri, w której każda tranzycja ma dokładnie jedno miejsce wejściowe i jedno miejsce wyjściowe), zasymulowac przyklad i dokonac analizy grafu osiagalnosci oraz niezmiennikow

### Wymyślona maszyna stanów:

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
