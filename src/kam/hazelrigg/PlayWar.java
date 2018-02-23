package kam.hazelrigg;

import java.util.Scanner;

public class PlayWar {
    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        War war = new War();
        int winner = 0;
        while (winner == 0) {
            winner = war.takeTurn();
        }
        if (winner == 1) {
            System.out.println("Player One Wins");
        } else {
            System.out.println("Player Two Wins");
        }
        System.out.println("TURNS: " + war.getTurns());
    }
}
