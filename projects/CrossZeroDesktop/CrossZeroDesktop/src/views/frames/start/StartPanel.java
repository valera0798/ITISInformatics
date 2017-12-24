package views.frames.start;

import supports.Strings;
import supports.Designer;
import views.frames.AppPanel;
import views.frames.start.components.TutorialPanel;
import views.frames.start.components.WelcomePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Валера on 06.08.2017.
 */
public class StartPanel extends AppPanel {
    private JTabbedPane tabbedPane;
    private WelcomePanel welcomePanel;
    private JPanel tutorialPanel;

    public StartPanel() {
        setLayout(new BorderLayout());
        setBackground(Designer.primaryDarkColor);

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setFont(Designer.font);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        welcomePanel = new WelcomePanel();
        tabbedPane.add(welcomePanel, Designer.getColoredText(Designer.hexFontColor, Strings.WELCOME_TITLE));
        tutorialPanel = new TutorialPanel();
        tabbedPane.add(tutorialPanel, Designer.getColoredText(Designer.hexFontColor, Strings.TUTORIAL_TITLE));

        setVisible(true);
    }
}
