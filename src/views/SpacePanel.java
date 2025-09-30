package views;

import entities.Bullet;
import entities.Enemy;
import enums.EnemyType;
import events.EventBus;
import events.RepaintEvent;
import models.SpaceModel;
import models.SpaceShipModel;
import params.BulletsParam;
import pools.IPool;
import ui.Animation;
import ui.ShipAnimation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class SpacePanel extends JPanel {
    private final ShipAnimation shipAnimation;
    private final SpaceShipModel shipModel;
    private final BaseView spaceView;
    private final SpaceModel spaceModel;
    private final SpaceModel spaceModel2;
    private final IPool<Bullet, BulletsParam> bulletPool;
    private final IPool<Enemy, EnemyType> enemyPool;
    private final EventBus eventBus;
    private final GameOverPanel gameOverPanel;
    private final Animation engineAnimation;

    private final Consumer<RepaintEvent> repaintHandler = e -> repaint();
    private final BaseView hearthView;
    private final Font customFont;

    public SpacePanel(ShipAnimation shipAnimation, SpaceShipModel shipModel, BaseView spaceView,
                      SpaceModel spaceModel, SpaceModel spaceModel2, IPool<Bullet, BulletsParam>  bulletPool,
                      IPool<Enemy, EnemyType> enemyPool, EventBus eventBus, GameOverPanel gameOverPanel, Animation engineAnimation){
        this.shipAnimation = shipAnimation;
        this.shipModel = shipModel;
        this.spaceView = spaceView;
        this.spaceModel = spaceModel;
        this.spaceModel2 = spaceModel2;
        this.bulletPool = bulletPool;
        this.enemyPool = enemyPool;
        this.eventBus = eventBus;
        this.gameOverPanel = gameOverPanel;
        this.engineAnimation = engineAnimation;

        hearthView = new BaseView("/hearth.png", 16, 16);
        customFont = new MainFont(18).getFont();

        setLayout(new OverlayLayout(this)); // щоб панелі накладалися



        add(gameOverPanel); // поверх

    }


    public void subscribe() {
        eventBus.subscribe(RepaintEvent.class, repaintHandler);
    }

    public void unsubscribe() {
        eventBus.unsubscribe(RepaintEvent.class, repaintHandler);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(spaceView.getSprite(), (int)spaceModel.getX(), (int)spaceModel.getY(), this);
        g.drawImage(spaceView.getSprite(), (int)spaceModel2.getX(), (int)spaceModel2.getY(), this);

        for (var b : bulletPool.getPooledObj()){
            if (!b.getBulletModel().isActive()) continue;
            g.drawImage(b.getView().getSprite(), (int)b.getBulletModel().getX(), (int)b.getBulletModel().getY(), this);
        }

        for (var e : enemyPool.getPooledObj()){
            if (!e.getModel().isActive()) continue;
            g.drawImage(e.getView().getSprite(), (int)e.getModel().getX(), (int)e.getModel().getY(), this);
        }

        g.drawImage(shipAnimation.getSprite(), (int)shipModel.getX(), (int)shipModel.getY(), this);
        g.drawImage(engineAnimation.getCurrentFrame().getSprite(), (int)shipModel.getX() + 20 , (int)shipModel.getY() + 48, this);

        for (var i = 1; i <= shipModel.getHp(); i++){
            g.drawImage(hearthView.getSprite(), 30 + 24 * i, 40, this);
        }

        drawScore(g);
    }

    private void drawScore(Graphics g) {
        g.setFont(customFont);
        g.setColor(Color.WHITE);
        String scoreText = String.valueOf(shipModel.getScore());
        FontMetrics fm = g.getFontMetrics();
        int x = getWidth() - fm.stringWidth(scoreText) - 30;
        int y = fm.getAscent() + 40;
        g.drawString(scoreText, x, y);
    }

    public void showGameOver() {
        gameOverPanel.setScore(shipModel.getScore());
        gameOverPanel.fadeIn();
    }

    public void hideGameOver() {
        gameOverPanel.hidePanel();
    }

    public GameOverPanel getGameOverPanel() {
        return gameOverPanel;
    }
}
