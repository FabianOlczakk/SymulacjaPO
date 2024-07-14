# ENG

# Cell Simulation

This project allows for the simulation of the behavior of biological cells (agents) based on specified parameters.

A map can host up to two types (species) of cells - A and B, which will avoid each other and compete for territory.

The simulation can display a very large number of agents simultaneously, as calculations for each are performed by the computer's graphics processor using a compute shader. This allows for computations involving millions of cells at once, compared to only a few thousand using a CPU.

## Documentation
- [link](https://fabianolczakk.github.io/SymulacjaPO/)

## Simulation Parameters Description
- `(enum) Presets` - allows selection of saved parameter presets
- `(int) Window width` - window width
- `(int) Window height` - window height
- `(int) Simulation width` - simulation map width
- `(int) Simulation height` - simulation map height
- `(float) Evaporate speed` - determines how quickly the cell trails fade
- `(float) Diffuse speed` - determines how quickly the cell trails spread across the map
- `(int) Agents count` - number of cells on the map
- `(enum) Spawn mode` - initial placement of cells on the map
- `(float) Cells probability` - determines the probability of the occurrence of species B cells
- `(Vector3f) Color` - species color **(color also changes cell behavior)**
- `(float) Sensor angle spacing` - determines the cell's vision angle
- `(float) Turn speed` - determines how quickly cells can change direction
- `(float) Sensor offset distance` - sensor offset
- `(int) Sensor size` - sensor size

## Requirements and Notes
- JDK 18
- OpenGL 4
- The simulation does not run on ARM architecture processors
- When using macOS, the simulation must be started with the VM option `-XstartOnFirstThread`
- It is preferable to run the simulation on a computer with a dedicated graphics card
- In case of a visible drop in frames per second or slow simulation performance, reduce the number of agents (`AgentsCount`) or set the `SensorSize` parameter to 1

## Examples of Operation
**Videos**
- [Video 1](https://drive.google.com/file/d/1CH20Elts1MaTj9sAusZillSwuzq2FqB3/view?usp=sharing)
- [Video 2](https://drive.google.com/file/d/1mIgGy2z8D_BLDFF2lEp7PvGV38tLzHWU/view?usp=sharing)

**Preset Images**

The following presets are available for selection in the program settings window.

- `Preset_1`

![1.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/1.1.png)
![1.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/1.2.png)

- `Preset_2`

![2.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/2.1.png)
![2.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/2.2.png)

- `Preset_3`

![3.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/3.1.png)
![3.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/3.2.png)

- `Preset_4`

![4.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/4.1.png)
![4.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/4.2.png)

- `Preset_5`

![5.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/5.1.png)
![5.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/5.2.png)

- `Preset_6`

![6.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/6.1.png)
![6.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/6.2.png)

- `Preset_7`

![7.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/7.1.png)
![7.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/7.2.png)

## Authors
- [@FabianOlczakk](https://github.com/FabianOlczakk)
- @Michał Patronik


# PL

# Symulacja komórek

Projekt pozwala symulować zachowanie komórek (agentów) biologicznych według podanych parametrów.

Na mapie można umieścić maksymalnie dwa rodzaje (gatunki) komórek - A oraz B, które będą się wzajemnie unikać oraz walczyć o teren.

Symulacja jest w stanie wyświetlać bardzo dużą ilość agentów na raz, ponieważ obliczenia dla każdego z nich są wykonywane przez procesor graficzny komputera dzięki użyciu shadera obliczeniowego (compute shadera). Pozwala to wykonywać obliczenia dla milionów komórek na raz, a przy użyciu procesora cpu jedynie kilka tysięcy.


## Dokumentacja
- [link](https://fabianolczakk.github.io/SymulacjaPO/)


## Opis parametrów symulacji
- `(enum) Presets` - pozwala wybrać zapisane presety parametrów
- `(int) Window width` - szerokość okna
- `(int) Window height` - wysokość okna
- `(int) Simulation width` - szerokość mapy symulacji
- `(int) Simulation height` - wysokość mapy symulacji
- `(float) Evaporate speed` - określa jak szybko zanika ślad po komórkach
- `(float) Diffuse speed` - określa jak szybko ślad po komórkach rozprasza się po mapie
- `(int) Agents count` - ilość komórek na mapie
- `(enum) Spawn mode` - początkowe ułożenie komórek na mapie
- `(float) Cells probability` - określa prawdopodobieństwo występowania komórek z gatunku B
- `(Vector3f) Color` - kolor gatunku **(kolor zmienia również zachowanie komórek)**
- `(float) Sensor angle spacing` - określa jak daleko widzi komórka pod kątem, w którym się znajduje
- `(float) Turn speed` - określa jak szybko komórki mogą zmieniać kierunek
- `(float) Sensor offset distance` - przesunięcie sensora
- `(int) Sensor size` - rozmiar sensora

  
## Wymagania i uwagi
- JDK 18
- OpenGL 4
- Symulacja nie działa na procesorach o architekturze ARM
- Używając systemu macOS należy uruchomić symulacje z opcją VM `-XstartOnFirstThread`
- Preferuje uruchomić symulacje na komputerze z dedykowaną kartą graficzną
- W razie widocznego spadku liczby klatek na sekundę lub wolnego przebiegu symulacji należy zmniejszyć liczbę agentów (`AgentsCount`) lub ustawić parametr `SensorSize` na 1


## Przykłady działania
**Filmy**
- [Film 1](https://drive.google.com/file/d/1CH20Elts1MaTj9sAusZillSwuzq2FqB3/view?usp=sharing)
- [Film 2](https://drive.google.com/file/d/1mIgGy2z8D_BLDFF2lEp7PvGV38tLzHWU/view?usp=sharing)

**Zdjęcia presetów**

Poniższe presety są dostępne do wybrania w oknie ustawień programu.


- `Preset_1`

![1.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/1.1.png)
![1.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/1.2.png)


- `Preset_2`

![2.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/2.1.png)
![2.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/2.2.png)


- `Preset_3`

![3.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/3.1.png)
![3.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/3.2.png)


- `Preset_4`

![4.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/4.1.png)
![4.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/4.2.png)


- `Preset_5`

![5.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/5.1.png)
![5.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/5.2.png)


- `Preset_6`

![6.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/6.1.png)
![6.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/6.2.png)


- `Preset_7`

![7.1](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/7.1.png)
![7.2](https://github.com/FabianOlczakk/SymulacjaPO/blob/master/images/7.2.png)


## Autorzy

- [@FabianOlczakk](https://github.com/FabianOlczakk)
- @Michał Patronik
