package controllers.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Валера on 03.08.2017.
 */
public class CloseAppActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
