package views;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameOverPanel extends JPanel {
    private final JLabel scoreLabel;
    private final JButton restartButton;
    private final JButton mainMenuButton;
    private final Image backgroundImage;

    private float alpha = 0f; // прозорість від 0 до 1
    private Timer fadeTimer;

    public GameOverPanel(Font customFont) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // завантажуємо бекграунд
        backgroundImage = new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/bg_gameover.png")
        )).getImage();

        // текст Game Over
        JLabel title = new JLabel("GAME OVER", SwingConstants.CENTER);
        title.setFont(customFont.deriveFont(Font.BOLD, 28f));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(customFont.deriveFont(Font.PLAIN, 18f));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // кнопка Restart
        restartButton = new JButton();
        styleButton(restartButton, "/play_button.png");

        // кнопка Main Menu
        mainMenuButton = new JButton();
        styleButton(mainMenuButton, "/menu_button.png");

        add(Box.createVerticalGlue());
        add(Box.createVerticalStrut(100));
        add(title);
        add(Box.createVerticalStrut(20));
        add(scoreLabel);

// панель для кнопок у ряд
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false); // щоб фон був прозорий
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(restartButton);
        buttonsPanel.add(Box.createHorizontalStrut(20)); // відступ між кнопками
        buttonsPanel.add(mainMenuButton);
        buttonsPanel.add(Box.createHorizontalGlue());

        add(buttonsPanel);

        add(Box.createVerticalGlue());

        setVisible(false);
    }

    private void styleButton(JButton button, String path) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                getClass().getResource(path)));
        Image scaled = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaled);
        button.setIcon(scaledIcon);

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // прозорість
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        int w = getWidth();
        int h = getHeight();
        g2d.drawImage(backgroundImage, (w - 400) / 2, (h - 350) / 2, 400, 350, this);

        super.paintComponent(g2d);
        g2d.dispose();
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    public void fadeIn() {
        setVisible(true);
        alpha = 0f;

        if (fadeTimer != null && fadeTimer.isRunning()) {
            fadeTimer.stop();
        }

        fadeTimer = new Timer(30, e -> {
            alpha += 0.05f;
            if (alpha >= 1f) {
                alpha = 1f;
                fadeTimer.stop();
            }
            repaint();
        });
        fadeTimer.start();
    }

    public void hidePanel() {
        setVisible(false);
    }
}