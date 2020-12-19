import java.util.LinkedList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new LinkedList<>();

    public void register(Flyable flyable)
    {
        if (!observers.contains(flyable))
            observers.add(flyable);
    }

    public void unregister(Flyable flyable)
    {
        if (observers.contains(flyable))
            observers.remove(flyable);
    }

    protected void conditionChanged()
    {
        observers.forEach(Flyable::updateConditions);
    }
}
