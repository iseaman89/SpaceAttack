package views;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainFont {
    private final Font font;

    public MainFont(int size) {
        try {
             font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    Objects.requireNonNull(MainMenuPanel.class.getResourceAsStream("/fonts/font.ttf"))
            ).deriveFont(Font.BOLD, size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Font getFont() { return font; }
}
