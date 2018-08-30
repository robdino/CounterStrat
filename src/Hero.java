import java.util.ArrayList;

public class Hero
{
    private String Name;
    private String Role;
    private String [] thisCounters;
    private String [] counteredBy;

    Hero()
    {
        Name = "placeholder";
        Role = "placeholder";
        thisCounters = new String [] {"juan", "two", "three"};
        counteredBy = new String [] {"juan", "two", "three"};
    }

    Hero (Hero copy)
    {
        this.Name = copy.Name;
        this.Role = copy.Role;
        this.thisCounters = copy.thisCounters;
        this.counteredBy = copy.counteredBy;
    }

    public String getName ()
    {
        return Name;
    }

    public String setName (String newName)
    {
        this.Name = newName;
        return this.Name;
    }

    public String getRole ()
    {
        return Role;
    }

    public String setRole (String newRole)
    {
        this.Role = newRole;
        return this.Role;
    }

    public String getThisCounters (Integer x)
    {
        return thisCounters[x];
    }

    public String setThisCounters (String  newThisCounters, Integer thisOne)
    {
        this.thisCounters [thisOne] = newThisCounters;
        return this.thisCounters[thisOne];
    }

    public String getCounteredBy (Integer x)
    {
        return counteredBy [x];
    }

    public String setCounteredBy (String newCounteredBy, Integer thisOne)
    {
        this.counteredBy [thisOne] = newCounteredBy;
        return this.counteredBy[thisOne];
    }

    public boolean setHero (Hero copy)
    {
        this.Name = copy.Name;
        this.Role = copy.Role;
        this.thisCounters = copy.thisCounters;
        this.counteredBy = copy.counteredBy;

        return true;
    }


}
