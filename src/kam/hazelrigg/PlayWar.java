package kam.hazelrigg;

import java.util.Scanner;

public class PlayWar {
    public static void main(String[] args) {
        War war = new War();
        while (true) {
            boolean winner = war.takeTurn();
            if (winner) {
                System.out.println(war.getTurns());
                break;
            }
        }
    }
}
