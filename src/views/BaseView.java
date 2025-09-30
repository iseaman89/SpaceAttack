package views;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BaseView {
    private final Image sprite;
    private final int width;
    private final int height;

    public BaseView(String imagePath, int width, int height) {
        this.width = width;
        this.height = height;

        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(BaseView.class.getResource(imagePath))
        );
        sprite = icon.getImage().getScaledInstance(
                width,
                height,
                Image.SCALE_SMOOTH
        );
    }

    public Image getSprite() {
        return sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
