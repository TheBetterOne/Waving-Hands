import java.util.List;

public abstract class Being {
    List<Spell> activeSpells;

    int health;

    int maxHealth;

    boolean alive = true;

    boolean blind = false;
    boolean invisible = false;
    boolean poisoned = false;
    boolean infected = false;
    int turnWhenInfected = 0;

    int turnNum = 0;

    boolean resistantToFire = false;
    boolean resistantToIce = false;

}
