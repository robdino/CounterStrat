import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TeamComp <TeamComp extends Hero>
{
    private ArrayList<Hero> blueTeam;
    private ArrayList<Hero> redTeam;
    private ArrayList<Hero> allChars;

    private ArrayList<String> charNames;

    TeamComp()
    {
        allChars = new ArrayList<>(28);
        buildCharacterRoster();

        System.out.println("Enter the heroes for the BLUE TEAM:");
        blueTeam    = buildTeam();

        System.out.println("Enter the heroes for the RED TEAM:");
        redTeam     = buildTeam();
    }

    TeamComp(TeamComp copy)
    {
        System.out.println("Don't.");
    }

    private ArrayList<Hero> buildTeam ()
    {
        ArrayList<Hero> array;
        array = collectHeros();
        return array;
    }

    public void displayBlueTeam ()
    {
        System.out.print("BLUE Team:\t");

        for (int x = 0; x < 6; x++)
            System.out.print(blueTeam.get(x).getName() + "\t");

        System.out.println("\n");
    }

    public void displayRedTeam ()
    {
        System.out.print("RED Team:\t");

        for (int x = 0; x < 6; x++)
            System.out.print(redTeam.get(x).getName() + "\t");

        System.out.println("\n");
    }

    private void displayHeros()
    {
        System.out.println(
                "1.  D.Va"      +
                "\t2.  Orisa"     +
                "\t3.  Reinhardt" +
                "\t4.  Roadhog"   +
                "\t5.  Winston"   +
                "\t6.  Hammond"   +
                "\t7.  Zarya"     + "\n" +
                "8.  Bastion"   +
                "\t9.  Doomfist"  +
                "\t10. Genji"     +
                "\t11. Hanzo"     +
                "\t12. Junkrat"   +
                "\t13. McCree"    +
                "\t14. Mei"       + "\n" +
                "15. Pharah"    +
                "\t16. Reaper"    +
                "\t17. Soldier 76"+
                "\t18. Sombra"    +
                "\t19. Symmetra"  +
                "\t20. Torbjorn"  +
                "\t21. Tracer"    + "\n" +
                "22. Widowmaker"+
                "\t23. Ana"       +
                "\t24. Brigitte"  +
                "\t25. Lucio"     +
                "\t26. Mercy"     +
                "\t27. Moira"     +
                "\t28. Zenyatta");
    }

    private ArrayList<Hero> collectHeros ()
    {
        Integer heroNum = -1;
        ArrayList<Hero> theTeam = new ArrayList<>();
        displayHeros();

        for (int element = 0; element < 6; element++)
        {
            heroNum = getIntInput();
           if (heroNum >= 1 && heroNum <= 22)
               theTeam.add(allChars.get(heroNum - 1));
           else
           {
               System.out.println("Hero number out of bounds. Pick again.");
               element--;
           }
        }

        return theTeam;
    }

    private Integer getIntInput()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("> ");
        Integer i = keyboard.nextInt();
        return i;
    }

    public boolean buildCharacterRoster ()  //load in each hero into Hero array. We catch errors like file not found, etc.
    {
        Integer i = 0;
        BufferedReader in = null;
        String st;
        Integer colonIndex;
        Integer commaOne;
        Integer commaTwo;

        while (++i < 23)
        {
            Hero temp = new Hero(); //We need a fresh object every iteration of adding to the list. Otherwise same object gets added N times
            File file = new File("hero_" + i.toString() + ".txt");

            try {
                in = new BufferedReader(new FileReader(file));
            }
            catch (Exception e) {
                System.out.println("Error on hero load in. File not found for Hero " + i.toString());
            }

            try {
                while ((st = in.readLine()) != null)
                {
                  if (st.contains("Hero: "))
                  {
                      colonIndex = st.indexOf(":");
                      temp.setName(st.substring(colonIndex + 2));
                  }
                  else if (st.contains("Role"))
                  {
                      colonIndex = st.indexOf(":");
                      temp.setRole(st.substring(colonIndex + 2));
                  }
                  else if (st.contains("Counters"))
                  {
                      colonIndex = st.indexOf(":");  //find all 3 positions
                      commaOne = st.indexOf(",");
                      commaTwo = st.lastIndexOf(",");

                      temp.setThisCounters(st.substring(colonIndex + 2, commaOne), 0); //set thisCounter
                      temp.setThisCounters(st.substring(commaOne + 2, commaTwo), 1);
                      temp.setThisCounters(st.substring(commaTwo + 2), 2);
                  }
                  else if (st.contains("CounteredBy"))
                  {
                      colonIndex = st.indexOf(":");
                      commaOne = st.indexOf(","); //finds first occurrence
                      commaTwo = st.lastIndexOf(",");

                      temp.setCounteredBy(st.substring(colonIndex + 2, commaOne), 0); //first counteredBy
                      temp.setCounteredBy(st.substring(commaOne + 2, commaTwo), 1);   //second counteredBy
                      temp.setCounteredBy(st.substring(commaTwo + 2), 2);   //third counteredBy

                      allChars.add(temp);   //We finally add the hero at the end
                  }
                  else
                  {
                      System.out.println("Error, line cannot be parsed.");
                  }
                }
            }
            catch (Exception e)
            {
                System.out.println("Error on line read. Hero " + i);
            }
        }
        return true;
    }

    public void analysis()
    {
        //check for a tank, an attack, and a healer
        //check for counters red -> blue
        //check for counters blue -> red

        //!!!!!// 1 is tank, 2 is attacker, and 3 is healer

        if( !listContainsRole(blueTeam, 1))
           System.out.println("You team does not have a tank.");
        if( !listContainsRole(blueTeam, 2))
            System.out.println("You team does not have an attacker.");
        if( !listContainsRole(blueTeam, 3))
            System.out.println("You team does not have a healer.");

        //check to see if red team counters anything on the blue team

            checkForCounters(blueTeam, redTeam);
    }

    /*
    Here we check to see if anything on TeamTwo counters teamOne
    -get the name of each thing on teamTWo
    -check two against the chars of each character on team one
    */

    private void checkForCounters (ArrayList<Hero> teamOne, ArrayList<Hero> teamTwo) //Add correct pronouns thru parameters for the function!!
    {
        for (int i = 0; i < 6; i++) //go thru each char with i
        {
            for (int n = 0; n < 3; n++)     //go thru each counter of each char with n
            {
                if (contains(teamOne, teamTwo.get(i).getThisCounters(n))) //check if one of the chars on RED counters BLUE chars
                {
                    System.out.println("Enemy " + teamTwo.get(i).getName() + " counters your " + teamTwo.get(i).getThisCounters(n) );
                }
            }
        }

    }

    private boolean listContainsRole(ArrayList<Hero> team, int x)
    {
        switch (x)
        {
            case 1:
                for (int i = 0; i < 6; i++)
                {
                    if( "Tank".equals(blueTeam.get(i).getRole()) )
                        return true;
                }
                break;
            case 2:
                for (int i = 0; i < 6; i++)
                {
                    if( "Attack".equals(blueTeam.get(i).getRole()) )
                        return true;
                }
                break;
            case 3:
                for (int i = 0; i < 6; i++)
                {
                    if( "Healer".equals(blueTeam.get(i).getRole()) )
                        return true;
                }
                break;
            default:
                System.out.println("Number is invalid for case switch.");
        }

        return false;
    }

    private int indexOf (final ArrayList<Hero>  teamOne, final String st)
    {
        for (int i = 0; i < 6; i++)
        {
            if(st.equals( teamOne.get(i).getName()) )
                return i;
        }
        return -1;
    }

    private boolean contains (final ArrayList<Hero>  teamOne, final String st)
    {
        for (int i = 0; i < 6; i++)
        {
            if(st.equals( teamOne.get(i).getName()) )
                return true;
        }
        return false;
    }
}
