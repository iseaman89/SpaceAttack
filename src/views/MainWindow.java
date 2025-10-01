package views;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final CardLayout layout;
    private final JPanel root;

    public MainWindow(JPanel spacePanel, JPanel mainMenuPanel){
        layout = new CardLayout();
        root = new JPanel(layout);

        setTitle("SPACE");
        setPreferredSize(new Dimension(1024, 640));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        root.add(spacePanel, "game");
        root.add(mainMenuPanel, "menu");

        add(root);

        setResizable(false);
        pack();
        setVisible(true);
    }

    public void showPanel(String name){ layout.show(root, name); }
}
