
# Symulacja komórek

Projekt pozwala symulować zachowanie komórek (agentów) biologicznych według podanych parametrów.

Na mapie można umieścić maksymalnie dwa rodzaje (gatunki) komórek - A oraz B, które będą się wzajemnie unikać.

Symulacja jest w stanie wyświetlać bardzo dużą ilość agentów na raz, ponieważ obliczenia dla każdego z nich są wykonywane przez procesor graficzny komputera dzięki użyciu shadera obliczeniowego (compute shadera). Pozwala to wykonywać obliczenia dla milionów komórek na raz gdzie procesor cpu był w stanie udźwignąć ich jedynie kilka tysięcy.




## Opis parametrów symulacji
- **(int) Window width** - szerokość okna
- **(int) Window height** - wysokość okna
- **(int) Simulation width** - szerokość mapy symulacji
- **(int) Simulation height** - wysokość mapy symulacji
- **(float) Evaporate speed** - określa jak szybko zanika ślad po komórkach
- **(float) Diffuse speed** - określa jak szybko ślad po komórkach rozprasza się po mapie
- **(int) Agents count** - ilość komórek na mapie
- **(enum) Spawn mode** - początkowe ułożenie komórek na mapie
- **(float) Cells probability** - określa prawdopodobieństwo występowania komórek z gatunku B
 - **(Vector3f) Color** - kolor gatunku
 - **(float) Sensor angle spacing** - TO DO
  - **(float) Turn speed** - określa jak szybko komórki mogą zmieniać kieruek
  - **(float) Sensor offset distance** - TO DO
  - **(int) Sensor size** - TO DO

  
## Wymagania i uwagi
- JDK 18
- OpenGL 4
- Symulacja nie działa na procesorach o architektórze ARM
- Używając systemu macOS należy uruchomić symulacje z opcją VM **-XstartOnFirstThread**
- Preferuje uruchomić symulacje na komputerze z dedykowaną kartą graficzną
- W razie widocznego spadku liczby klatek na sekundę lub wolnego przebiegu symulacji należy zmniejszyć liczbę agentów (**AgentsCount**) oraz ustawić parametr **SensorSize** na 1


## Przykłady działania
- [Film 1](https://drive.google.com/file/d/1CH20Elts1MaTj9sAusZillSwuzq2FqB3/view?usp=sharing)
- [Film 2](https://drive.google.com/file/d/1CH20Elts1MaTj9sAusZillSwuzq2FqB3/view?usp=sharing)


## Autorzy

- [@FabianOlczakk](https://github.com/FabianOlczakk)
- @Michał Patronik

