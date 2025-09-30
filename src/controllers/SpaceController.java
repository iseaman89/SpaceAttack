package controllers;

import core.IUpdateListener;
import models.SpaceModel;
import views.SpacePanel;

public class SpaceController implements IUpdateListener {
    private final SpaceModel spaceModel;
    private final SpaceModel spaceModel2;
    private final SpacePanel spacePanel;

    public SpaceController(SpaceModel spaceModel, SpaceModel spaceModel2, SpacePanel spacePanel) {
        this.spaceModel = spaceModel;
        this.spaceModel2 = spaceModel2;
        this.spacePanel = spacePanel;
    }

    @Override
    public void update(double deltaTime) {
        if (spaceModel.getY() >= 639) {
            spaceModel.setY(-640);
        } else {
            spaceModel.setY(spaceModel.getY() + spaceModel.getSpeed() * deltaTime);
        }
        if (spaceModel2.getY() >= 639) {
            spaceModel2.setY(-640);
        } else {
            spaceModel2.setY(spaceModel2.getY() + spaceModel2.getSpeed() * deltaTime);
        }
        spacePanel.repaint();
    }
}
