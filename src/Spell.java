public abstract class Spell {

    Being target;

    Wizard caster;

    Type type;

    public static enum Type{Protection, Summoning, Damaging, Enchantment}

    int turnWhenCast;
    int turn;

    static Spell getSpell(String name){
        //TODO
        return null;
    }

    abstract String getName();

    abstract boolean stillEffective();

    abstract void act();

    public boolean changeParameter(Object... objects){//Return true if parameters are valid for this spell, false otherwise.
        return false;
    }
}
