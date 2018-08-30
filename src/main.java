public class main {

    public static void main (String [] args) {

        System.out.println("Welcome to CounterStrat.");
        TeamComp<Hero> game1 = new TeamComp<Hero>();
        game1.displayBlueTeam();
        game1.displayRedTeam();
        game1.analysis();
    }
}
