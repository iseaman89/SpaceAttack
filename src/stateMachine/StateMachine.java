package stateMachine;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    private IState currentState;
    private final Map<Class<? extends IState>, IState> states = new HashMap<>();

    public <T extends IState> void register(Class<T> type, IState state) {
        states.put(type, state);
    }

    public <T extends IState> void setState(Class<T> type) {
        IState state = states.get(type);
        if (state == null) return;

        if (currentState != null) {
            currentState.exit();
        }

        currentState = state;
        currentState.enter();
    }
}
