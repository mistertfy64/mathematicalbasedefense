package com.mathematicalbasedefenders.mathematicalbasedefenders.ui;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;

import java.util.ArrayList;

public class ToastNotification {

    public static ArrayList<ToastNotification> activeToastNotifications = new ArrayList<>();
    static long toastNotificationNumber = 0L;
    TextButton textButton;
    int age = 0;


    public ToastNotification(String text) {


        toastNotificationNumber++;

        int fontSize = 20;

        while (MathematicalBaseDefenders.renderer.getWidthOfText(text, fontSize) > 400) {
            fontSize -= 1;
        }


        textButton = new TextButton(text, new TextButton.TextButtonStyle(MathematicalBaseDefenders.core.toastNotificationBackground, MathematicalBaseDefenders.core.toastNotificationBackground, MathematicalBaseDefenders.core.toastNotificationBackground, MathematicalBaseDefenders.core.createOrGetNewComputerModernFont(fontSize)));
        textButton.setPosition(1552, -56);

        age = 0;

        MathematicalBaseDefenders.renderer.addActorToGlobalStage(textButton, "toastNotification" + toastNotificationNumber);

        textButton.addAction(Actions.moveBy(0, 64, 0.2f));

        for (int i = 0; i < activeToastNotifications.size(); i++) {
            activeToastNotifications.get(i).getBody().addAction(Actions.moveBy(0, 64, 0.2f));
        }

        activeToastNotifications.add(this);

    }

    public TextButton getBody() {
        return this.textButton;
    }

    public static ArrayList<ToastNotification> getActiveToastNotifications() {
        return activeToastNotifications;
    }

    public static void setActiveToastNotifications(ArrayList<ToastNotification> activeToastNotifications) {
        ToastNotification.activeToastNotifications = activeToastNotifications;
    }

    public TextButton getTextButton() {
        return textButton;
    }

    public void setTextButton(TextButton textButton) {
        this.textButton = textButton;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static long getToastNotificationNumber() {
        return toastNotificationNumber;
    }

    public static void setToastNotificationNumber(long toastNotificationNumber) {
        ToastNotification.toastNotificationNumber = toastNotificationNumber;
    }

}
