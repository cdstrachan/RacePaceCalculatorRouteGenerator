## Companion app
Main app is here: https://github.com/cdstrachan/RacePaceCalculator

This a a simple route-generator for the race pace calculator.

## Usage
usage Java -JAR..... /path-to-source-file.txt

## Sample input file
input.txt

Times are hh:mm:ss
Columns of numbers are elevation (m) for each km. There should be 1 for each distance.
EG distance of 10km requires 10 lines.

```
raceName|MyRace
plannedRaceTimeFirst|02:44:00
plannedRaceTimeLast|06:59:00
plannedRaceTimeDelta|00:15:00
startDelay|00:00:30
firstFade|0
lastFade|5
distance|10
8
11
-2
-12
1
-12
-5
2
-18
2
```