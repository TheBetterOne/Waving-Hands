/**
 * Created by pears_000 on 12/27/2014.
 */
public abstract class Spell {

    public static enum Type{Protection, Summoning, Damaging, Enchantment}

    int turnWhenCast;
    int turn;

    abstract void castOn(Being... targets);

    static Spell getSpell(String name){
        //TODO
        return null;
    }

    abstract String getName();

    abstract boolean stillEffective();
}
