package com.mathematicalbasedefenders.mathematicalbasedefenders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;

import javax.swing.*;

public class DesktopLauncherWindow extends JFrame {

    public DesktopLauncherWindow() {
    }

    public void initialize() {
        JFrame frame = new JFrame();

        // set properties for frame
        frame.setTitle("Mathematical Base Defenders Launcher");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // make panels
        JPanel panel = new JPanel();

        // add properties to the panel

        // make stuff
        JButton launchButton = new JButton();

        // add properties to the stuff
        launchButton.setBounds(120, 480, 560, 60);
        launchButton.setText("Launch Game");
        launchButton.addActionListener(event -> {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

            config.title = "Mathematical Base Defenders";
            config.width = 1920;
            config.height = 1080;
            config.samples = 3;
            config.foregroundFPS = 60;
            config.backgroundFPS = 60;

            new LwjglApplication(new MathematicalBaseDefenders(), config);

            frame.dispose();
        });

        // add stuff to the panel
        panel.add(launchButton);

        // add panel to frame
        frame.add(panel);

        // add other stuff to frame


        // show the frame
        frame.setVisible(true);

    }

}
