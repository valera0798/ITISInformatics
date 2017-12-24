package views.frames.start.components;

import supports.Strings;
import supports.Designer;
import supports.FileWorker;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Валера on 06.08.2017.
 */
public class TutorialPanel extends JPanel {
    private JScrollPane scrollPane;
    private JTextArea textArea;

    public TutorialPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255, 20));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);
        textArea.setFont(Designer.font);
        textArea.setDisabledTextColor(Designer.fontColor);
        textArea.setText(FileWorker.getText(new File(Strings.TUTORIAL_FILE_PATH)));

        scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
    }
}
