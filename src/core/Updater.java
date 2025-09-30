package core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Updater {
    private final List<IUpdateListener> listeners = new CopyOnWriteArrayList<>();

    public void add(IUpdateListener... newListeners){
        for (var l : newListeners)
            if (!listeners.contains(l)) listeners.add(l);
    }

    public void remove(IUpdateListener... removeListeners){
        for (var l : removeListeners)
            listeners.remove(l);
    }

    public void update(double deltaTime){
        for (var l : listeners)
            l.update(deltaTime);
    }
}
