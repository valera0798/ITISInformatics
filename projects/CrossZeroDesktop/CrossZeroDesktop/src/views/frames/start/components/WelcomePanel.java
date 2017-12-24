package views.frames.start.components;

import supports.Strings;
import supports.Designer;
import supports.FileWorker;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by Валера on 06.08.2017.
 */
public class WelcomePanel extends JPanel {
    private JScrollPane scrollPane;
    private JTextArea textArea;

    public WelcomePanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);
        textArea.setFont(Designer.font);
        textArea.setDisabledTextColor(Designer.fontColor);
        textArea.setText(FileWorker.getText(new File(Strings.WELCOME_FILE_PATH)));

        scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
    }
}
