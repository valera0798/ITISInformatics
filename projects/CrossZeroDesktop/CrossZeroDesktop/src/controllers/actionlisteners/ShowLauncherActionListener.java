package controllers.actionlisteners;

import views.frames.launch.LaunchFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Валера on 06.08.2017.
 */
public class ShowLauncherActionListener implements ActionListener {
    private JFrame currentFrame;

    public ShowLauncherActionListener(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame.setVisible(false);
        new LaunchFrame();
    }
}
