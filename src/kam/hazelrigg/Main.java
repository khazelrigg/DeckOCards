package kam.hazelrigg;

public class Main {

    public static void main(String[] args) {
        long avg = 0;
        int games = 100;
        for (int i = 0; i < games; i++) {
            CardGame war = new War();
            war.play();
            avg += war.getTurns();
        }
        System.out.println("Avg turns " + avg / games);
    }
}
