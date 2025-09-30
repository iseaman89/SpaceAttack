package stateMachine.states;

import stateMachine.IState;
import stateMachine.StateMachine;
import views.MainMenuPanel;
import views.MainWindow;

import java.awt.event.ActionListener;

public class MainMenuState implements IState {
    private final MainWindow mainWindow;
    private final MainMenuPanel mainMenuPanel;
    private final StateMachine stateMachine;
    private final ActionListener playListener;

    public MainMenuState(MainWindow mainWindow, MainMenuPanel mainMenuPanel, StateMachine stateMachine) {
        this.mainWindow = mainWindow;
        this.mainMenuPanel = mainMenuPanel;
        this.stateMachine = stateMachine;

        playListener = e -> play();
    }

    @Override
    public void enter() {
        mainWindow.showPanel("menu");
        mainMenuPanel.getPlayButton().addActionListener(playListener);
    }

    @Override
    public void exit() {
        mainMenuPanel.getPlayButton().removeActionListener(playListener);
    }

    private void play(){
        stateMachine.setState(GameState.class);
    }
}
