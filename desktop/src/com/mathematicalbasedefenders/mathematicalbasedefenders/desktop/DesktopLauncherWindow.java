package com.mathematicalbasedefenders.mathematicalbasedefenders.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
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
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

            config.setTitle("Mathematical Base Defenders");
            config.setResizable(true);
            config.setWindowSizeLimits(1024,768,-1,-1);
            config.setBackBufferConfig(8,8,8,8,16,0,3);
            config.setForegroundFPS(60);
            config.setIdleFPS(60);

            frame.dispose();

            new Lwjgl3Application(new MathematicalBaseDefenders(), config);


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
