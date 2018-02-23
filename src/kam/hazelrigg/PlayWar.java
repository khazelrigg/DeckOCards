package kam.hazelrigg;

public class PlayWar {

    public static void main(String[] args) {
        System.out.println(warAverages(10000));
    }

    private static long warAverages(int games) {
        long average = 0;
        for (int i = 0; i < games; i++) {
            average += playWar();
        }
        return average / games;
    }

    private static int playWar() {
        War war = new War();
        while (war.takeTurn() == 0) {
            //nothing
        }
        return war.getTurnCount();
    }
}
