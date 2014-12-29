import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Wizard extends Being{

    Controller controller;

    List<Gesture> rightHandHistory = new ArrayList<Gesture>();
    List<Gesture> leftHandHistory = new ArrayList<Gesture>();
    List<Spell> rightHandSpellHistory = new ArrayList<Spell>();
    List<Spell> leftHandSpellHistory = new ArrayList<Spell>();
    List<Spell> activeCastSpells = new ArrayList<Spell>();

    List<SpellConstraint> constraints = new ArrayList<SpellConstraint>();

    List<Spell> enchantments = new ArrayList<Spell>();

    List<ViewOf<Wizard>> opponents = new ArrayList<ViewOf<Wizard>>();

    List<ViewOf<Being>> allBeings = new ArrayList<ViewOf<Being>>();

    List<ViewOf<Being>> corpses = new ArrayList<ViewOf<Being>>();

    List<ViewOf<Monster>> myMonsters = new ArrayList<ViewOf<Monster>>();

    List<ViewOf<Elemental>> elementals = new ArrayList<ViewOf<Elemental>>();

    List<ViewOf<Monster>> monsters = new ArrayList<ViewOf<Monster>>();

    List<ViewOf<Monster>> attackingMonsters = new ArrayList<ViewOf<Monster>>();

    public static class Turn{
        public static enum Type{Fire, Ice, Left, Right}

        Wizard caster;
        Gesture right;
        Gesture left;
        Being rightTarget;
        Being leftTarget;
        Type rightType;
        Type leftType;
        Spell rightTargetSpell;
        Spell leftTargetSpell;
        boolean bothHands;

        public Turn(Wizard caster, Gesture right, Gesture left, Type typeRight, Type typeLeft, ViewOf rightTarget, ViewOf leftTarget, Spell rightTargetSpell, Spell leftTargetSpell){
            if (caster == null || right == null || left == null || right == Gesture.Unknown || left == Gesture.Unknown){
                throw new IllegalArgumentException();
            }
            if (right == Gesture.Clap ^ left == Gesture.Clap){
                throw new IllegalArgumentException();
            }
            if (right == Gesture.Stab && left == Gesture.Stab){
                throw new IllegalArgumentException();
            }
            this.caster = caster;
            this.right = right;
            this.left = left;
            rightType = typeRight;
            leftType = typeLeft;
            this.rightTarget = rightTarget.seen;
            this.leftTarget = leftTarget.seen;
            this.rightTargetSpell = rightTargetSpell;
            this.leftTargetSpell = leftTargetSpell;
        }
    }

    public Wizard(Controller controller){
        health = 15;
        maxHealth = 15;
        this.controller = controller;
    }

    public abstract Turn doTurn();

    public void die(){}

    public void resurrect(){//Automatically disqualified if called by wizard
        health = 15;
    }

    protected List<Gesture> rightHandHistory(){
        return Collections.unmodifiableList(rightHandHistory);
    }

    protected List<Gesture> leftHandHistory(){
        return Collections.unmodifiableList(leftHandHistory);
    }

    protected List<Spell> rightHandSpellHistory(){
        return Collections.unmodifiableList(rightHandSpellHistory);
    }

    protected List<Spell> leftHandSpellHistory(){
        return Collections.unmodifiableList(leftHandSpellHistory);
    }

    protected List<Spell> activeSpellsOnMe(){
        return Collections.unmodifiableList(activeSpells);
    }

    protected List<Spell> activeSpellsCastByMe(){
        return Collections.unmodifiableList(activeCastSpells);
    }

    protected List<Spell> activeSpellsCastBy(ViewOf<Wizard> opponent){
        List<Spell> spells = new ArrayList<Spell>();
        for (Spell spell : opponent.seen.activeCastSpells){
            Being target = spell.target;
            for (ViewOf<Being> being : allBeings){
                if (being.visible && being.seen == target){
                    spells.add(spell);
                }
            }
        }
        return Collections.unmodifiableList(spells);
    }

    protected List<Spell> activeSpellsOn(ViewOf<Being> other){
        if (other.visible){
            return other.seen.activeSpells;
        }
        return null;
    }

    protected int turnNumber(){
        return turnNum;
    }

    protected List<ViewOf<Wizard>> opponents(){
        return Collections.unmodifiableList(opponents);
    }

    protected List<Gesture> historyOfRightHandOf(ViewOf<Wizard> opponent){
        return Collections.unmodifiableList(opponent.rightHistory);
    }

    protected List<Gesture> historyOfLeftHandOf(ViewOf<Wizard> opponent){
        return Collections.unmodifiableList(opponent.leftHistory);
    }

    protected List<Spell> spellsCastByRightHandOf(ViewOf<Wizard> opponent){
        return Collections.unmodifiableList(opponent.rightSpellHistory);
    }

    protected List<Spell> spellsCastByLeftHandOf(ViewOf<Wizard> opponent){
        return Collections.unmodifiableList(opponent.leftSpellHistory);
    }

    protected ViewOf<Being> targetOf(ViewOf<Monster> monster){
        Being target = monster.seen.target;
        for (ViewOf<Being> being : allBeings){
            if (being.seen == target){
                return being;
            }
        }
        return null;
    }

    protected ViewOf<Wizard> ownerOf(ViewOf<Monster> monster){
        Wizard owner = monster.seen.owner;
        for (ViewOf<Wizard> opponent : opponents){
            if (opponent.seen == owner){
                return opponent;
            }
        }
        return null;
    }

    protected ViewOf<Wizard> createrOf(ViewOf<Monster> monster){
        Wizard owner = monster.seen.creator;
        for (ViewOf<Wizard> opponent : opponents){
            if (opponent.seen == owner){
                return opponent;
            }
        }
        return null;
    }

    protected List<ViewOf<Being>> corpses(){
        return Collections.unmodifiableList(corpses);
    }

    protected List<Spell> spellsOn(ViewOf<Wizard> opponent){
        if (opponent.visible){
            return Collections.unmodifiableList(opponent.seen.enchantments);
        }
        return null;
    }

    protected int healthOf(ViewOf<Being> being){
        if (being.visible){
            return being.seen.health;
        }
        return Integer.MAX_VALUE;
    }

    protected int maxHealthOf(ViewOf<Being> being){
        if (being.visible){
            return being.seen.maxHealth;
        }
        return Integer.MAX_VALUE;
    }

    protected List<ViewOf<Monster>> myMonsters(){
        return Collections.unmodifiableList(myMonsters);
    }

    protected List<ViewOf<Monster>> monsters() {
        List<ViewOf<Monster>> visibleMonsters = new ArrayList<ViewOf<Monster>>();
        for (ViewOf<Monster> monster : monsters){
            if (monster.visible){
                visibleMonsters.add(monster);
            }
        }
        return Collections.unmodifiableList(visibleMonsters);
    }

    protected List<ViewOf<Monster>> attackingMonsters(){
        return Collections.unmodifiableList(attackingMonsters);
    }

    protected List<ViewOf<Elemental>> elementals(){
        return Collections.unmodifiableList(elementals);
    }

    protected ViewOf<Wizard> casterOf(Spell spell){
        Wizard caster = spell.caster;
        for (ViewOf<Wizard> opponent : opponents){
            if (opponent.seen == caster){
                return opponent;
            }
        }
        return null;
    }

    protected void changeTarget(ViewOf<Monster> monster, ViewOf<Being> newTarget){
        if (monster.seen.owner == this){
            controller.addCommand(new MonsterCommand(monster.seen, this,newTarget.seen));
        }
    }

    protected boolean isAlive(ViewOf<Being> other){
        return other.seen.alive;
    }

    protected boolean isResistantToFire(ViewOf<Being> other) {//Returns false if invisible.
        return other.visible && other.seen.resistantToFire;
    }

    protected boolean isResistantToIce(ViewOf<Being> other) {//Returns false if invisible.
        return other.visible && other.seen.resistantToIce;
    }

    protected boolean isVisible(ViewOf<Being> other){
        return other.visible;
    }

    protected boolean isBlind(){
        return blind;
    }

    protected boolean isInvisible(){
        return invisible;
    }

    protected boolean isBlind(ViewOf<Being> other) {//Returns false if you can't see the other.
        return other.visible && other.seen.blind;
    }

    protected boolean isInvisible(ViewOf<Being> other) {//Returns false if you are blind.
        return !blind && other.seen.invisible;
    }

    protected boolean isPoisoned(){
        return poisoned;
    }

    protected boolean isInfected(){
        return infected | poisoned;
    }

    protected int howLongTillDeadByDiseaseOrPoison(){
        if (isInfected()){
            return turnWhenInfected + 6 - turnNum;
        }
        return Integer.MAX_VALUE;
    }

    protected boolean isPoisoned(ViewOf<Being> other) {//Returns false if other can't be seen by you.
        return other.visible && other.seen.poisoned;
    }

    protected boolean isInfected(ViewOf<Being> other) {//Returns false if can't be seen.
        return other.visible && other.seen.infected | other.seen.poisoned;
    }
}
