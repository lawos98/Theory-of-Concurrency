# Laboratorium 2 - semafory

- Semafor to mechanizm synchronizacji procesów zaproponowany przez Dijkstrę.
- Semafor jest zmienną całkowitą, która przyjmuje wartości nieujemne (≥0) lub — w przypadku
    semaforów binarnych — logiczne. Zmienna semaforowa musi mieć nadaną początkową wartość
    (nieujemną).
- Po nadaniu początkowej wartości zmiennej semaforowej można na niej wykonywać tylko dwa
    rodzaje operacji:
       1. **P — opuszczanie semafora** (hol. proberen), powoduje zmniejszenie wartości zmiennej
          semaforowej,
       2. **V — podnoszenie semafora** (hol. verhogen). powoduje zwiekszanie wartości zmiennej
          semaforowej.
- Wykonując operację semaforową, proces może zastać zablokowany (przejść w stan oczekiwania).
    Typowym przypadkiem jest blokowanie w operacji opuszczania semafora. Operacja opuszczania
    nie zakończy się do czasu, aż wartość zmiennej semaforowej będzie na tyle duża, że zmniejszenie
    jej wartości w wyniku tej operacji nie spowoduje przyjęcia wartości ujemnej.
- Rodzaje semaforów
    1. **semafor binarny** ma dwa stany: true (podniesiony otwarty) i false (opuszczony
       zamknięty). Wielokrotne podnoszenie takiego semafora nie zmieni jego stanu — skutkiem
       będzie stan otwarcia. W niektórych rozwiązaniach przyjmuje się, że próba podniesienia
       otwartego semafora sygnalizowana jest błędem.
    2. **semafor ogólny** „pamięta” liczbę operacji podniesienia. Zwykle inicjalizowany jest iloscia
       dostępnego zasobu. Można bez blokowania procesu wykonać tyle operacji opuszczenia
       semafora, aby jego wartość była nieujemna.
- Zadanie
    ◦ zaimplementowac [semafor binarny](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab2/sem.html)  za pomoca metod wait i notify/notifyall, uzyc go do
       synchronizacji wyscigu z poprzedniego laboratorium.
    ◦ zaimplementowac [semafor licznikowy](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab2/sem.html) (ogolny) za pomoca metod wait i notify/notifyall.
       Przetestowac semafor na prostej symulacji sklepu samoobsługowego z ograniczoną ilością
       koszyków.
