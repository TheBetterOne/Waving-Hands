import java.util.ArrayList;
import java.util.List;

/**
 * Created by pears_000 on 12/28/2014.
 */
public class ViewOf<T extends Being> {
    List<Gesture> leftHistory = new ArrayList<Gesture>();
    List<Gesture> rightHistory = new ArrayList<Gesture>();
    List<Spell> rightSpellHistory = new ArrayList<Spell>();
    List<Spell> leftSpellHistory = new ArrayList<Spell>();
    Wizard sees;
    boolean visible;
    T seen;
}
