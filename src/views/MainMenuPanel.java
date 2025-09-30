package views;

import models.SpaceModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainMenuPanel extends JPanel {
    private final BaseView spaceView;
    private final SpaceModel spaceModel;
    private final SpaceModel spaceModel2;
    private final JButton playButton;

    public MainMenuPanel(BaseView spaceView, SpaceModel spaceModel, SpaceModel spaceModel2) {
        this.spaceView = spaceView;
        this.spaceModel = spaceModel;
        this.spaceModel2 = spaceModel2;

        playButton = new JButton();

        //setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(.5f);
        setAlignmentY(.5f);

        add(Box.createRigidArea(new Dimension(0, 280)));

        var customFont = new MainFont(32).getFont();

        JLabel title = new JLabel("SPACE ATTACK", SwingConstants.CENTER);
        title.setFont(customFont);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title);

        add(Box.createRigidArea(new Dimension(0, 30))); // відступ

        // Кнопка Play з картинкою
        ImageIcon icon = new ImageIcon("resources/play_button.png");

// Масштабуємо картинку
        Image scaled = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaled);

// Встановлюємо у кнопку
        playButton.setIcon(scaledIcon);

// Прибираємо фон, щоб була тільки картинка
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);

// (опціонально) виставляємо той же розмір
        playButton.setPreferredSize(new Dimension(64, 64));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //playButton.addActionListener(e -> onPlay.run());
        add(playButton);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(spaceView.getSprite(), (int)spaceModel.getX(), (int)spaceModel.getY(), this);
        g.drawImage(spaceView.getSprite(), (int)spaceModel2.getX(), (int)spaceModel2.getY(), this);
    }

    public JButton getPlayButton() {
        return playButton;
    }
}
