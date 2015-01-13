package base;

public abstract class Controller {

    public abstract void doGestures(Wizard caller,//Only allowed to be called once per doTurn call.
                                    Wizard.Gesture left,
                                    Wizard.Gesture right,
                                    String leftTargetSpell,//Used if spell requires both hands.
                                    String rightTargetSpell,
                                    Being leftTarget,//Leave null for opponent.
                                    Being rightTarget,//Leave null for opponent.
                                    Object... spellArguments);//Arguments are pulled as required from spells.

    public abstract void changeTarget(Wizard caller,
                                      Monster monster,
                                      Being newTarget);//Leave null for opponent.

    public abstract void activateSpell(Wizard caller,
                                       Spell spell,
                                       Object... spellArguments);



    public abstract boolean isResistantToFire(Wizard caller,
                                              Being being);//Leave null for opponent.

    public abstract boolean isResistantToIce(Wizard caller,
                                             Being being);//Leave null for opponent.

    public abstract boolean isBlind(Wizard caller,
                                    Being being);//Leave null for opponent.

    public abstract boolean isInvisible(Wizard caller,
                                        Being being);//Leave null for opponent.
}
