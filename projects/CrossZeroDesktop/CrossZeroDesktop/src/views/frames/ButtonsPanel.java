package views.frames;

import supports.Designer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Валера on 04.08.2017.
 */
public class ButtonsPanel extends AppPanel {
    protected Container buttonsContainer;
    protected JButton button1;
    protected JButton button2;

    public ButtonsPanel(String button1Text,
                        ActionListener button1ActionListener) {
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        button1 = new JButton(button1Text);
        button1.setBackground(Designer.crossColor);
        button1.setForeground(Designer.primaryColor);
        button1.setFont(Designer.font);
        button1.addActionListener(button1ActionListener);
        button1.setFocusPainted(false);

        buttonsContainer = new Container();
        GridBagLayout gblContainer = new GridBagLayout();
        buttonsContainer.setLayout(gblContainer);
        buttonsContainer.add(button1);

        Designer.adjustConstraintsRadioButton(c);
        gblContainer.setConstraints(button1, c);

        Designer.adjustConstraintsRadioButtonContainer(c);
        gbl.setConstraints(buttonsContainer, c);

        add(buttonsContainer);
    }

    public ButtonsPanel(String button1Text, String button2Text,
            ActionListener button1ActionListener, ActionListener button2ActionListener) {
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        button1 = new JButton(button1Text);
        button1.setBackground(Designer.crossColor);
        button1.setForeground(Designer.primaryColor);
        button1.setFont(Designer.font);
        button1.addActionListener(button1ActionListener);
        button1.setFocusPainted(false);

        button2 = new JButton(button2Text);
        button2.setBackground(Designer.zeroColor);
        button2.setForeground(Designer.primaryColor);
        button2.setFont(Designer.font);
        button2.addActionListener(button2ActionListener);
        button2.setFocusPainted(false);

        buttonsContainer = new Container();
        GridBagLayout gblContainer = new GridBagLayout();
        buttonsContainer.setLayout(gblContainer);
        buttonsContainer.add(button1);
        buttonsContainer.add(button2);


        Designer.adjustConstraintsRadioButton(c);
        gblContainer.setConstraints(button1, c);
        gblContainer.setConstraints(button2, c);

        Designer.adjustConstraintsRadioButtonContainer(c);
        gbl.setConstraints(buttonsContainer, c);

        add(buttonsContainer);
    }
}
