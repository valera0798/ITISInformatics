package views.frames.start;

import controllers.actionlisteners.ShowLauncherActionListener;
import supports.Strings;
import views.frames.AppFrame;
import views.frames.ButtonsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 06.08.2017.
 */
public class StartFrame extends AppFrame {
    private StartPanel startPanel;
    private ButtonsPanel buttonsPanel;
    public StartFrame() throws HeadlessException {
        super();

        frameWidth = 320;
        frameHeight = 430;

        init();
    }

    @Override
    protected void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds();
        setLayout(new BorderLayout());
        setTitle(Strings.SETTINGS);

        startPanel = new StartPanel();
        add(startPanel, BorderLayout.CENTER);

        buttonsPanel = new ButtonsPanel(Strings.NEXT, new ShowLauncherActionListener(this));
        add(buttonsPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }
}
