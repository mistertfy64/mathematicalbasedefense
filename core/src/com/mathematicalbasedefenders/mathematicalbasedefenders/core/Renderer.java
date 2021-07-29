package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.ui.ToastNotification;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.core;

public class Renderer {
    private static final String CHARACTERS_ALLOWED_IN_PROBLEM = "0123456789+-*/=abcdnxyz×÷";

    // RENDERING: RENDERING

    public void addTextToCurrentTextStage(String text, String textName, int size, Color fontColor, float xPos, float yPos) {
        BitmapFont fontToUse = core.size72ComputerModernUnicodeFont;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        switch (size) {
            case 12: {
                fontToUse = core.size12ComputerModernUnicodeFont;
                break;
            }
            case 16: {
                fontToUse = core.size16ComputerModernUnicodeFont;
                break;
            }
            case 20: {
                fontToUse = core.size20ComputerModernUnicodeFont;
                break;
            }
            case 24: {
                fontToUse = core.size24ComputerModernUnicodeFont;
                break;
            }
            case 32: {
                fontToUse = core.size32ComputerModernUnicodeFont;
                break;
            }
            case 36: {
                fontToUse = core.size36ComputerModernUnicodeFont;
                break;
            }
            case 48: {
                fontToUse = core.size48ComputerModernUnicodeFont;
                break;
            }
            case 64: {
                fontToUse = core.size64ComputerModernUnicodeFont;
                break;
            }
            case 72: {
                fontToUse = core.size72ComputerModernUnicodeFont;
                break;
            }
        }

        labelStyle.font = fontToUse;
        labelStyle.fontColor = fontColor;
        Label label = new Label(text, labelStyle);
        label.setPosition(xPos, yPos);
        label.setName(textName);
        core.currentTextStageToDrawOn.addActor(label);
    }

    public void addTextToCurrentTextStage(String text, String textName, int size, Color fontColor, int alignment, float xPos, float yPos) {
        BitmapFont fontToUse = core.size12ComputerModernUnicodeFont;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        switch (size) {
            case 12: {
                fontToUse = core.size12ComputerModernUnicodeFont;
                break;
            }
            case 16: {
                fontToUse = core.size16ComputerModernUnicodeFont;
                break;
            }
            case 20: {
                fontToUse = core.size20ComputerModernUnicodeFont;
                break;
            }
            case 24: {
                fontToUse = core.size24ComputerModernUnicodeFont;
                break;
            }
            case 32: {
                fontToUse = core.size32ComputerModernUnicodeFont;
                break;
            }
            case 36: {
                fontToUse = core.size36ComputerModernUnicodeFont;
                break;
            }
            case 48: {
                fontToUse = core.size48ComputerModernUnicodeFont;
                break;
            }
            case 64: {
                fontToUse = core.size64ComputerModernUnicodeFont;
                break;
            }
            case 72: {
                fontToUse = core.size72ComputerModernUnicodeFont;
                break;
            }
        }
        labelStyle.font = fontToUse;
        labelStyle.fontColor = fontColor;
        Label label = new Label(text, labelStyle);
        label.setPosition(xPos, yPos);
        label.setName(textName);
        label.setAlignment(alignment);
        core.currentTextStageToDrawOn.addActor(label);
    }


    public void changeTextOnCurrentTextStage(String textToChangeTo, String textName) {
        Label label = core.currentTextStageToDrawOn.getRoot().findActor(textName);
        label.setText(textToChangeTo);
    }

    public void changeTextColorOnCurrentTextStage(Color colorToChangeTo, String textName) {
        Label label = core.currentTextStageToDrawOn.getRoot().findActor(textName);
        label.getStyle().fontColor = colorToChangeTo;
    }

    public void changeTextPositionOnCurrentTextStage(float xPos, float yPos, String textName) {
        Label label = core.currentTextStageToDrawOn.getRoot().findActor(textName);
        label.setPosition(xPos, yPos);
    }

    public Actor findTextOnCurrentTextStage(String textName) {
        for (int i = 0; i < core.currentTextStageToDrawOn.getActors().size; i++) {
            if (core.currentTextStageToDrawOn.getActors().get(i).getName().equals(textName)) {
                return core.currentTextStageToDrawOn.getActors().get(i);
            }
        }
        return null;
    }


    public void removeTextFromCurrentTextStage(String textName) {
        core.currentTextStageToDrawOn.getRoot().findActor(textName).remove();
    }

    public void removeAllTextFromCurrentTextStage() {
        core.currentTextStageToDrawOn.getActors().clear();
    }

    public void renderSpecialComputerModernFont(String text, float xPos, float yPos, float scale, boolean changeSpacing) {

        int totalWidth = 0;

        if (text.length() >= 0 && text != null && !text.equals("")) {

            Pixmap[] pixmaps = new Pixmap[text.length()];
            int[] indexes = new int[text.length()];

            for (int i = 0; i < text.length(); i++) {
                if (!(CHARACTERS_ALLOWED_IN_PROBLEM.contains(text.substring(i, i + 1)))) {
                    Log.logErrorMessage("Character " + text.charAt(i) + " is not an allowed character in the problem!");
                    throw new IllegalArgumentException("Character " + text.charAt(i) + " is not an allowed character in the problem!"); //fix grammar or something
                }
            }

            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < CHARACTERS_ALLOWED_IN_PROBLEM.length(); j++) {
                    if ((text.substring(i, i + 1).equals(CHARACTERS_ALLOWED_IN_PROBLEM.substring(j, j + 1)))) {
                        int index = j;
                        if (j == 23 || j == 24) {
                            index -= 11;
                            j -= 11;
                        }
                        pixmaps[i] = core.specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[j];
                        indexes[i] = index;
                        break;
                    }
                }
            }

            // 0

            for (int i = 0; i < pixmaps.length; i++) {


                Texture texture = new Texture(pixmaps[i]);
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Image image = new Image(texture);
                image.setScale(scale);
                float realYPos = yPos;

                if (indexes[i] == 11) {
                    realYPos += 30;
                } else if (indexes[i] == 14) {
                    realYPos += 23;
                } else if (indexes[i] == 21) {
                    realYPos += 5;
                }

                image.setPosition(changeSpacing ? totalWidth + xPos : totalWidth + xPos + 10 * i, realYPos);
                totalWidth += image.getWidth();
                if (changeSpacing) {
                    totalWidth += 5;
                }
                core.currentSpecialComputerModernFontStageToDrawOn.addActor(image);
            }


        }
    }


    public void renderSpecialComputerModernFont(String text, float xPos, float yPos, float scale, boolean changeSpacing, int changeHeightForY) {

        int totalWidth = 0;

        if (text.length() >= 0 && text != null && !text.equals("")) {

            Pixmap[] pixmaps = new Pixmap[text.length()];
            int[] indexes = new int[text.length()];

            for (int i = 0; i < text.length(); i++) {
                if (!(CHARACTERS_ALLOWED_IN_PROBLEM.contains(text.substring(i, i + 1)))) {
                    Log.logErrorMessage("Character " + text.charAt(i) + " is not an allowed character in the problem!");
                    throw new IllegalArgumentException("Character " + text.charAt(i) + " is not an allowed character in the problem!"); //fix grammar or something
                }
            }

            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < CHARACTERS_ALLOWED_IN_PROBLEM.length(); j++) {
                    if ((text.substring(i, i + 1).equals(CHARACTERS_ALLOWED_IN_PROBLEM.substring(j, j + 1)))) {
                        int index = j;
                        if (j == 23 || j == 24) {
                            index -= 11;
                            j -= 11;
                        }
                        pixmaps[i] = core.specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[j];
                        indexes[i] = index;
                        break;
                    }
                }
            }

            // 0

            for (int i = 0; i < pixmaps.length; i++) {


                Texture texture = new Texture(pixmaps[i]);
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                Image image = new Image(texture);
                image.setScale(scale);
                float realYPos = yPos;

                if (indexes[i] == 11) {
                    realYPos += 30;
                } else if (indexes[i] == 14) {
                    realYPos += 23;
                } else if (indexes[i] == 21) {
                    realYPos += changeHeightForY;
                }

                image.setPosition(changeSpacing ? totalWidth + xPos : totalWidth + xPos + 10 * i, realYPos);
                totalWidth += image.getWidth();
                if (changeSpacing) {
                    totalWidth += 5;
                }
                core.currentSpecialComputerModernFontStageToDrawOn.addActor(image);
            }


        }
    }

    public void eraseAllSpecialComputerModernFont() {
        core.currentSpecialComputerModernFontStageToDrawOn.getActors().clear();
    }


    /**
     * Renders SpecialComputerModernFont, note that only a few characters are supported.
     *
     * @param text
     * @param xPos
     * @param yPos
     * @param size
     */
    public void renderProblemAsSpecialComputerModernFont(String text, float xPos, float yPos, float size, boolean changeSpacing) {

        eraseAllProblemSpecialComputerModernFont();

        int totalWidth = 0;

        if (text.length() >= 0 && text != null && !text.equals("")) {

            Pixmap[] pixmaps = new Pixmap[text.length()];
            int[] indexes = new int[text.length()];

            for (int i = 0; i < text.length(); i++) {
                if (!(CHARACTERS_ALLOWED_IN_PROBLEM.contains(text.substring(i, i + 1)))) {
                    Log.logErrorMessage("Character " + text.charAt(i) + " is not an allowed character in the problem!");
                    throw new IllegalArgumentException("Character " + text.charAt(i) + " is not an allowed character in the problem!"); //fix grammar or something
                }
            }

            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < CHARACTERS_ALLOWED_IN_PROBLEM.length(); j++) {
                    if ((text.substring(i, i + 1).equals(CHARACTERS_ALLOWED_IN_PROBLEM.substring(j, j + 1)))) {
                        int index = j;
                        if (j == 23 || j == 24) {
                            index -= 11;
                            j -= 11;
                        }
                        pixmaps[i] = core.specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[j];
                        indexes[i] = index;
                        break;
                    }
                }
            }

            for (int i = 0; i < pixmaps.length; i++) {

                Image image = new Image(new Texture(pixmaps[i]));
                float realYPos = yPos;

                if (indexes[i] == 11) {
                    realYPos += 30;
                } else if (indexes[i] == 14) {
                    realYPos += 23;
                } else if (indexes[i] == 21) {
                    realYPos -= 21;
                }

                image.setPosition(changeSpacing ? totalWidth + xPos : totalWidth + xPos + 10 * i, realYPos);
                totalWidth += image.getWidth();
                if (changeSpacing) {
                    totalWidth += 5;
                }
                core.currentProblemSpecialComputerModernFontStageToDrawOn.addActor(image);
            }


        }


    }

    public void renderProblemAsSpecialComputerModernFont(String text, float xPos, float yPos, float size, boolean centerHorizontally, boolean centerVertically) {
        if (!(centerHorizontally || centerVertically)) {
            renderProblemAsSpecialComputerModernFont(text, xPos, yPos, size, false);
        }
        if (centerHorizontally && !centerVertically) { // center horizontally
            xPos = (1920 - getTotalWidthOfSpecialComputerModernFontString(text)) / 2;
        } else if (!centerHorizontally && centerVertically) { // center vertically

        } else { // center both horizontally and vertically

        }
        renderProblemAsSpecialComputerModernFont(text, xPos, yPos, size, true);
    }


    public void addActorToCurrentStage(Actor actor, String name) {
        actor.setName(name);
        core.currentStageToDrawOn.addActor(actor);
    }

    public Actor findActorOnCurrentStage(String name) {
        for (int i = 0; i < core.currentStageToDrawOn.getActors().size; i++) {
            if (core.currentStageToDrawOn.getActors().get(i).getName().equals(name)) {
                return core.currentStageToDrawOn.getActors().get(i);
            }
        }
        return null;
    }


    public void removeActorFromCurrentStage(String name) {
        core.currentStageToDrawOn.getRoot().findActor(name).remove();
    }

    public void addActorToGlobalStage(Actor actor, String name) {
        actor.setName(name);
        core.globalStage.addActor(actor);
    }

    public Actor findActorInGlobalStage(String name) {
        for (int i = 0; i < core.globalStage.getActors().size; i++) {
            if (core.globalStage.getActors().get(i).getName().equals(name)) {
                return core.globalStage.getActors().get(i);
            }
        }
        return null;
    }


    public void removeActorFromGlobalStage(String name) {
        core.globalStage.getRoot().findActor(name).remove();
    }

    public float getTotalWidthOfSpecialComputerModernFontString(String text) {

        Pixmap[] pixmaps = new Pixmap[text.length()];
        int[] indexes = new int[text.length()];
        int totalWidth = 0;

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < CHARACTERS_ALLOWED_IN_PROBLEM.length(); j++) {
                if ((text.substring(i, i + 1).equals(CHARACTERS_ALLOWED_IN_PROBLEM.substring(j, j + 1)))) {
                    int index = j;
                    if (j == 23 || j == 24) {
                        j -= 11;
                    }
                    pixmaps[i] = core.specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[j];
                    indexes[i] = index;
                    break;
                }
            }
        }

        for (int i = 0; i < pixmaps.length; i++) {
            totalWidth += pixmaps[i].getWidth();
        }

        return totalWidth + pixmaps.length * 5;
    }


    public float centerAlignText(String text, int size) {
        BitmapFont fontToUse = null;
        switch (size) {
            case 12: {
                fontToUse = core.size12ComputerModernUnicodeFont;
                break;
            }
            case 16: {
                fontToUse = core.size16ComputerModernUnicodeFont;
                break;
            }
            case 20: {
                fontToUse = core.size20ComputerModernUnicodeFont;
                break;
            }
            case 24: {
                fontToUse = core.size24ComputerModernUnicodeFont;
                break;
            }
            case 32: {
                fontToUse = core.size32ComputerModernUnicodeFont;
                break;
            }
            case 36: {
                fontToUse = core.size36ComputerModernUnicodeFont;
                break;
            }
            case 48: {
                fontToUse = core.size48ComputerModernUnicodeFont;
                break;
            }
            case 64: {
                fontToUse = core.size64ComputerModernUnicodeFont;
                break;
            }
            case 72: {
                fontToUse = core.size72ComputerModernUnicodeFont;
                break;
            }
        }

        GlyphLayout glyphLayout = new GlyphLayout(fontToUse, text);
        return (1920 - glyphLayout.width) / 2;
    }


    /**
     * Erases all rendered SpecialComputerModernFont.
     */
    public static void eraseAllProblemSpecialComputerModernFont() {
        core.currentProblemSpecialComputerModernFontStageToDrawOn.getActors().clear();
    }


    public float getWidthOfText(String text, int size) {
        BitmapFont fontToUse = null;
        switch (size) {
            case 12: {
                fontToUse = core.size12ComputerModernUnicodeFont;
                break;
            }
            case 16: {
                fontToUse = core.size16ComputerModernUnicodeFont;
                break;
            }
            case 20: {
                fontToUse = core.size20ComputerModernUnicodeFont;
                break;
            }
            case 24: {
                fontToUse = core.size24ComputerModernUnicodeFont;
                break;
            }
            case 32: {
                fontToUse = core.size32ComputerModernUnicodeFont;
                break;
            }
            case 36: {
                fontToUse = core.size36ComputerModernUnicodeFont;
                break;
            }
            case 48: {
                fontToUse = core.size48ComputerModernUnicodeFont;
                break;
            }
            case 64: {
                fontToUse = core.size64ComputerModernUnicodeFont;
                break;
            }
            case 72: {
                fontToUse = core.size72ComputerModernUnicodeFont;
                break;
            }
        }

        GlyphLayout glyphLayout = null;
        if (size == 12 || size == 16 || size == 24 || size == 32 || size == 36 || size == 48 || size == 64 || size == 72) {
            glyphLayout = new GlyphLayout(fontToUse, text);
        } else {
            glyphLayout = new GlyphLayout(MathematicalBaseDefenders.core.createNewComputerModernFont(size), text);
        }


        return glyphLayout.width;
    }


    public void createNewToastNotification(String text) {
        new ToastNotification(text);
    }


}
