package base;

import java.util.List;

public abstract class Wizard extends Being{

    Gesture[] leftHandHistory;

    Gesture[] rightHandHistory;

    Gesture[] enemyLeftHandHistory;

    Gesture[] enemyRightHandHistory;

    List<Spell> mySpellsCastLastTurn;

    List<Spell> enemySpellsCastLastTurn;

    List<Spell> myActiveSpells;

    List<Spell> enemysActiveSpells;

    List<Spell> activeSpellsOnMe;

    List<Monster> monstersAttackingMe;

    List<Monster> myMonsters;

    List<Monster> enemysMonsters;

    List<Elemental> elementals;

    Controller controller;

    public enum Gesture {WiggledFingers, ProfferedPalm, Snap, Wave, DigitPoint, Clap, Stab, Nothing, Unknown}

    public Wizard(Controller c){
        this.controller = c;

    }

    public boolean isResistantToFire(){
        return resistantToFire;
    }

    public boolean isResistantToIce(){
        return resistantToIce;
    }

    public boolean isBlind(){
        return blind;
    }

    public boolean isInvisible(){
        return invisible;
    }

    public abstract void doTurn();


}
