package controllers;

import statics.Input;
import views.SpacePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InputController {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean spacePressed;
    private final SpacePanel spacePanel;

    public InputController(SpacePanel spacePanel){
        this.spacePanel = spacePanel;

        setupKeys();
    }

    private void setupKeys(){
        setupLeft();
        setupRight();
        setupUp();
        setupDown();
        setupSpace();
    }

    private void setupLeft(){
        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.LEFT_PRESSED), Input.LEFT_PRESSED_ACTION);
        spacePanel.getActionMap().put(Input.LEFT_PRESSED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = true;
            }
        });

        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.LEFT_RELEASED), Input.LEFT_RELEASED_ACTION);
        spacePanel.getActionMap().put(Input.LEFT_RELEASED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = false;
            }
        });
    }

    private void setupRight(){
        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.RIGHT_PRESSED), Input.RIGHT_PRESSED_ACTION);
        spacePanel.getActionMap().put(Input.RIGHT_PRESSED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = true;
            }
        });

        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.RIGHT_RELEASED), Input.RIGHT_RELEASED_ACTION);
        spacePanel.getActionMap().put(Input.RIGHT_RELEASED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = false;
            }
        });
    }

    private void setupUp(){
        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.UP_PRESSED), Input.UP_PRESSED_ACTION);
        spacePanel.getActionMap().put(Input.UP_PRESSED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = true;
            }
        });

        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.UP_RELEASED), Input.UP_RELEASED_ACTION);
        spacePanel.getActionMap().put(Input.UP_RELEASED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = false;
            }
        });
    }

    private void setupDown(){
        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.DOWN_PRESSED), Input.DOWN_PRESSED_ACTION);
        spacePanel.getActionMap().put(Input.DOWN_PRESSED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = true;
            }
        });

        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.DOWN_RELEASED), Input.DOWN_RELEASED_ACTION);
        spacePanel.getActionMap().put(Input.DOWN_RELEASED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = false;
            }
        });
    }

    private void setupSpace(){
        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.SPACE_PRESSED), Input.SPACE_PRESSED_ACTION);
        spacePanel.getActionMap().put(Input.SPACE_PRESSED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spacePressed = true;
            }
        });

        spacePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Input.SPACE_RELEASED), Input.SPACE_RELEASED_ACTION);
        spacePanel.getActionMap().put(Input.SPACE_RELEASED_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spacePressed = false;
            }
        });
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }
}
