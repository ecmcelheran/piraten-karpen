# A1 - Piraten Karpen

  * Author: Emily McElheran
  * Email: mcelhere@mcmaster.ca

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 
  * To run with different player strategies (where # is the number of players with that strategy):
    * `mvn exec:java -Dexec.args="-r # -c #"`
    * `mvn exec:java -Dexec.args="--random # --combo #"`
  * To run in 'trace mode':
    * `mvn exec:java -Dexec.args="-tr"`
  * To change number of games in the simulation (where # is the number of games):
    * `mvn exec:java -Dexec.args="-games #"`


Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * The specified feature is functional, object-oriented, and delivered

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| YES   | F01 | Roll a dice |  D | 01/01/23 | 01/18/23 |
| YES   | F02 | Roll eight dices  |  D | 01/12/23  | 01/18/23 |
| x   | F03 | Select how many games as command-line arg.  | D | 01/24/23 | 01/25/23  |
| YES   | F04 | End of turn with three skulls | D | 01/12/23 | 01/20/23 |
| YES   | F05 | End of game when player reaches 6000 points| D | 01/20/23 | 01/18/23 |
| YES   | F06 | Player keeping random dice at their turn | D | 01/16/2023| 01/20/23 | 
| YES   | F07 | Score points: diamonds and coins | D | 01/20/23 | 01/18/23 |
| YES   | F08 | Track percentage of wins of each player | D | 01/15/23 | 01/16/23 |
| x   | F09 | Implement Strategy to maximize combos | D | 01/23/23 | 01/24/23 | 
| x   | F10 | Score points: Combos | D |01/23/23 | 01/24/23 |
| x   | F11 | Implement Card Draw into Turn | D | 01/25/23 | 01/25/23 |
| x   | F12 | Introduce sea battle card | D | 01/25/23 | 01/25/23 |
| x   | F13 | Give option of command line arguments for trace mode, number of games and player strategy | D | 01/24/23 | 01/25/23 |
| x   | F14 | Introduce monkey business card | D | 01/25/23 | 01/26/23 |
| x   | F15 | Introduce gold, diamond, skull, and sea battle cards | D | 01/28/23 | 01/29/23 |

