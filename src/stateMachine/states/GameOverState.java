package stateMachine.states;

import stateMachine.IState;
import stateMachine.StateMachine;
import views.SpacePanel;

import java.awt.event.ActionListener;

public class GameOverState implements IState {
    private final SpacePanel spacePanel;
    private final StateMachine stateMachine;

    private final ActionListener restartListener;
    private final ActionListener mainMenuListener;

    public GameOverState(SpacePanel spacePanel, StateMachine stateMachine) {
        this.spacePanel = spacePanel;
        this.stateMachine = stateMachine;

        restartListener = e -> restartGame();
        mainMenuListener = e -> goToMainMenu();
    }

    @Override
    public void enter() {
        spacePanel.showGameOver();
        spacePanel.getGameOverPanel().getRestartButton().addActionListener(restartListener);
        spacePanel.getGameOverPanel().getMainMenuButton().addActionListener(mainMenuListener);
    }

    @Override
    public void exit() {
        spacePanel.hideGameOver();
        spacePanel.getGameOverPanel().getRestartButton().removeActionListener(restartListener);
        spacePanel.getGameOverPanel().getMainMenuButton().removeActionListener(mainMenuListener);
    }

    private void restartGame(){
        stateMachine.setState(GameState.class);
    }

    private void goToMainMenu() {
        stateMachine.setState(MainMenuState.class);
    }
}
