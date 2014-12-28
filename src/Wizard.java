import java.util.ArrayList;
import java.util.List;

/**
 * Created by pears_000 on 12/27/2014.
 */
public abstract class Wizard implements Being{

    public static class Turn{
        public static enum Type{Fire, Ice, Left, Right;}

        Wizard caster;
        Gesture right;
        Gesture left;
        List<Being> targets;
        Type leftType;
        Type rightType;
        boolean bothHands;

        public Turn(Wizard caster, Gesture right, Gesture left, Type typeLeft, Type typeRight, Being rightTarget, Being leftTarget, Command... commands){
            if (right == Gesture.Clap ^ left == Gesture.Clap){
                throw new IllegalArgumentException();
            }
            if (right == Gesture.Stab && left == Gesture.Stab){
                throw new IllegalArgumentException();
            }
            for (SpellConstraint constraint : caster.spellConstraints){
                if (!constraint.allowed(caster, right, left)){
                    throw new IllegalArgumentException();
                }
            }
            //TODO
        }
    }

    public List<Gesture> rightHandHistory;
    public List<Gesture> leftHandHistory;

    public List<Wizard> opponents;

    public int health;

    public List<Monster> allMonsters;
    public List<Monster> myMonsters;
    public List<Elemental> elementals;

    public List<Spell> spellHistory;
    public List<Spell> enchantments;

    public List<SpellConstraint> spellConstraints;

    public boolean castShortLightningBolt;

    public Wizard(List<Wizard> opponents){
        rightHandHistory = new ArrayList<Gesture>();
        leftHandHistory = new ArrayList<Gesture>();
        this.opponents = opponents;
        allMonsters = new ArrayList<Monster>();
        myMonsters = new ArrayList<Monster>();
        elementals = new ArrayList<Elemental>();
        spellHistory = new ArrayList<Spell>();
        enchantments = new ArrayList<Spell>();
    }

    public abstract Turn doTurn();

    private static class Command {
    }
}
