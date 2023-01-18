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

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * < Your DoD goes here >

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| YES   | F01 | Roll a dice |  S | 01/01/23 |  |
| YES   | F02 | Roll eight dices  |  S | 01/12/23  |
| x   | F03 | Select how many games as command-line arg.  |  P  |   |
| YES   | F04 | End of turn with three skulls | S | 01/12/23 |  |
| YES   | F05 | End of game when player reaches 6000 points| S | |
| YES   | F06 | Player keeping random dice at their turn | B (F02) | 01/16/2023|  | 
| YES   | F07 | Score points: diamonds and coins | B (F04) | | 
| x   | F08 | Score points: 3-of-a-kind | B (F04) | | 
| x   | F09 | Implement Card Draw | B | |

