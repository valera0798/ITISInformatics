package views.frames;

import supports.Strings;

import javax.swing.*;
import java.awt.*;

import static supports.Designer.getBackgroundImageIcon;

/**
 * Created by Валера on 23.07.2017.
 *
 * Класс frame-а приложения
 *
 */
public abstract class AppFrame extends JFrame {
    public static int frameWidth;
    public static int frameHeight;

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_X_CENTER = (int) SCREEN_SIZE.getWidth() / 2;
    public static final int SCREEN_Y_CENTER = (int) SCREEN_SIZE.getHeight() / 2;

    public AppFrame() throws HeadlessException {
        setResizable(false);
        setContentPane(new JLabel(getBackgroundImageIcon(Strings.IMAGE_BACKGROUND_PATH_LAUNCH)));
    }

    protected abstract void init();

    // установка frame-а с заданными размерами в центр экрана
    public void setBounds() {
        setBounds(SCREEN_X_CENTER - frameWidth / 2, SCREEN_Y_CENTER - frameHeight / 2, frameWidth, frameHeight);
    }
}

