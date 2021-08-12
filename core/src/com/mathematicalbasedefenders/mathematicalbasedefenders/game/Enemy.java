package com.mathematicalbasedefenders.mathematicalbasedefenders.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Log;

import java.util.ArrayList;
import java.util.Random;


public class Enemy {

    int health, attack;
    float xPosition, yPosition, defaultMoveSpeed, textSize, width, height, spacePosition;
    String requestedValue;
    Color color;
    static long enemyNumber = 0L;


    public Enemy() {

        this.defaultMoveSpeed = 4;
        this.health = 1;
        this.attack = 1;
        this.requestedValue = generateRequestedValue();
        this.xPosition = 2000f;
        this.yPosition = 800f;
        this.color = new Color((float) Math.floor(Math.random() * 255) / 255f, (float) Math.floor(Math.random() * 255) / 255f, (float) Math.floor(Math.random() * 255) / 255f, 1);
        this.width = 100;
        this.height = 100;
        this.textSize = 16;
        this.spacePosition = 10f;

        enemyNumber++;
        MathematicalBaseDefenders.game.enemiesSpawned++;

        Game.activeEnemies.put(enemyNumber, this);
        Game.activeEnemiesAsTextures.put(enemyNumber, null);

    }

    public Enemy(int health, int attack, float moveSpeed, String requestedValue) {
        this.health = health;
        this.attack = attack;
        this.defaultMoveSpeed = moveSpeed;
        this.requestedValue = requestedValue;
        this.color = new Color((float) Math.floor(Math.random() * 255) / 255f, (float) Math.floor(Math.random() * 255) / 255f, (float) Math.floor(Math.random() * 255) / 255f, 1);
        this.width = 100;
        this.height = 100;
        this.textSize = 16;
        this.spacePosition = 10f;

        enemyNumber++;
        MathematicalBaseDefenders.game.enemiesSpawned++;


        Game.activeEnemies.put(enemyNumber, this);
        Game.activeEnemiesAsTextures.put(enemyNumber, null);

    }


    // METHODS


    /**
     * Moves an enemy <code>defaultMoveSpeed</code> pixels to its left.
     */
    public void move() {
        this.xPosition -= defaultMoveSpeed;
        this.spacePosition = xPosition > 1800 ? 10 : (xPosition - 100) / 170;
        // System.out.println("Enemy #" + this.enemyNumber + "'s x position is now " + xPosition);
    }

    /**
     * Moves an enemy <code>pixels</code> pixels to its left.
     *
     * @param pixels The number of pixels to move to the left.
     */
    public void move(float pixels) {
        this.xPosition -= pixels;
        this.spacePosition = xPosition > 1800 ? 10 : (xPosition - 100) / 170;
    }


    /**
     * Renders the enemy.
     */
    public void render(long enemyNumber) {
        Pixmap pixmap = new Pixmap((int) this.width, (int) this.height, Format.RGBA8888);
        pixmap.setColor(this.color);
        pixmap.fillRectangle(0, 0, (int) this.width, (int) this.height);

        if (Game.activeEnemiesAsTextures.containsKey(enemyNumber)) {
            if (Game.activeEnemiesAsTextures.get(enemyNumber) == null) {
                Game.activeEnemiesAsTextures.put(enemyNumber, this.addSpecialComputerModernFont(pixmap, this.requestedValue, 3, MathematicalBaseDefenders.utilities.rgbToHSL(this.getColor().r, this.getColor().g, this.getColor().b)[2] > 0.5 ? Color.BLACK : Color.WHITE));
            }
            MathematicalBaseDefenders.core.currentSpriteBatchToDrawOn.draw(Game.activeEnemiesAsTextures.get(enemyNumber), this.xPosition, this.yPosition, this.width, this.height);
        }


        pixmap.dispose();
    }

    public Texture addSpecialComputerModernFont(Pixmap pixmap, String text, float spacing, Color specialComputerModernFontColor) {

        int totalWidth = 0, initialTotalWidth = 0;
        Pixmap pixmapToReturn = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);

        ArrayList<Pixmap> textureRegionsAsPixmaps = new ArrayList<>();
        int[] indexes = new int[text.length()];

        pixmapToReturn.setColor(this.color);
        pixmapToReturn.fillRectangle(0, 0, (int) this.width, (int) this.height);

        for (int i = 0; i < text.length(); i++) {
            if (!(Core.CHARACTERS_ALLOWED_IN_PROBLEM.contains(Character.toString(text.charAt(i))))) {
                Log.logErrorMessage("Character " + text.charAt(i) + " is not an allowed character in the problem!");
                throw new IllegalArgumentException("Character " + text.charAt(i) + " is not an allowed character in the problem!"); //fix grammar or something
            }
        }

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < Core.CHARACTERS_ALLOWED_IN_PROBLEM.length(); j++) {
                if (((text.charAt(i)) == (Core.CHARACTERS_ALLOWED_IN_PROBLEM.charAt(j)))) {
                    int index = j;
                    if (j == 23 || j == 24) {
                        index -= 11;
                    }
                    textureRegionsAsPixmaps.add(MathematicalBaseDefenders.core.specialComputerModernFontTextTextureAtlasRegionsAsPixmaps[index]);
                    indexes[i] = index;
                    initialTotalWidth += i == text.length() - 1 ? textureRegionsAsPixmaps.get(i).getWidth() : textureRegionsAsPixmaps.get(i).getWidth() + spacing;
                    break;
                }
            }
        }
        Pixmap specialComputerModernFontPixmap = new Pixmap(initialTotalWidth, text.contains("y") ? (int) this.height + 23 : (int) this.height, Format.RGBA8888);
        for (int i = 0; i < text.length(); i++) {

            Pixmap currentSpecialComputerModernFontPixmap = textureRegionsAsPixmaps.get(i);

            Pixmap specialComputerModernFontPixmap2 = new Pixmap(currentSpecialComputerModernFontPixmap.getWidth(), currentSpecialComputerModernFontPixmap.getHeight(), Format.RGBA8888);
            specialComputerModernFontPixmap2.drawPixmap(currentSpecialComputerModernFontPixmap, 0, 0);

            int yCoordinateToDrawAt = 0;

            if (text.charAt(i) == 'a' || text.charAt(i) == 'c' || text.charAt(i) == 'n' || text.charAt(i) == 'x' || text.charAt(i) == 'z') {
                yCoordinateToDrawAt = 23;
            } else if (text.charAt(i) == 'y') {
                yCoordinateToDrawAt = 23;
            } else if (text.charAt(i) == '-') {
                yCoordinateToDrawAt = 23;
            }


            specialComputerModernFontPixmap.drawPixmap(specialComputerModernFontPixmap2, totalWidth, yCoordinateToDrawAt);
            totalWidth += specialComputerModernFontPixmap2.getWidth() + spacing;
            specialComputerModernFontPixmap2.dispose();
        }


        Texture textureToReturn = new Texture((int) this.width, (int) this.height, Format.RGBA8888);

        // ???
        if (initialTotalWidth > this.getWidth()) {

            specialComputerModernFontPixmap = scalePixmapToFitEnemy(specialComputerModernFontPixmap, 0.9f, color);

        }

        pixmapToReturn.drawPixmap(specialComputerModernFontPixmap, (int) (this.getWidth() - specialComputerModernFontPixmap.getWidth()) / 2, (int) ((this.getHeight() - 68) / 2));


        textureToReturn.draw(pixmapToReturn, 0, 0);

        textureToReturn.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        specialComputerModernFontPixmap.dispose();
        pixmapToReturn.dispose();


        return textureToReturn;
    }

    public Pixmap scalePixmapToFitEnemy(Pixmap pixmap, float ratio, Color color) {
        Pixmap pixmap2 = new Pixmap((int) (pixmap.getWidth() * 0.9), (int) (pixmap.getHeight() * 0.9), Format.RGBA8888);
        while (pixmap2.getWidth() > this.getWidth() || pixmap2.getHeight() > this.getHeight()) {
            pixmap2 = new Pixmap((int) (pixmap2.getWidth() * 0.9), (int) (pixmap2.getHeight() * 0.9), Format.RGBA8888);
        }
        pixmap2.setFilter(Pixmap.Filter.BiLinear);
        pixmap2.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, pixmap2.getWidth(), pixmap2.getHeight());
        return pixmap2;
    }

    // 75% = -1000 to 1000
    // 3.125% each = -1000 to 1000 * variable
    public String generateRequestedValue() {
        Random random = new Random();
        double roll = Math.random();
        if (roll < 0.875) {
            return Integer.toString((Math.random() < 0.8 ? 1 : -1) * random.nextInt(1000));
        } else if (roll < 0.90625) {
            return (Math.random() < 0.8 ? 1 : -1) * random.nextInt(1000) + "a";
        } else if (roll < 0.93750) {
            return (Math.random() < 0.8 ? 1 : -1) * random.nextInt(1000) + "b";
        } else if (roll < 0.96875) {
            return (Math.random() < 0.8 ? 1 : -1) * random.nextInt(1000) + "c";
        } else {
            return (Math.random() < 0.8 ? 1 : -1) * random.nextInt(1000) + "d";
        }
    }


    // GETTERS AND SETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public float getXPosition() {
        return xPosition;
    }

    public void setXPosition(float position) {
        this.xPosition = position;
    }

    public float getYPosition() {
        return yPosition;
    }

    public void setYPosition(float position) {
        this.yPosition = position;
    }


    public float getMoveSpeed() {
        return defaultMoveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.defaultMoveSpeed = moveSpeed;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getRequestedValue() {
        return requestedValue;
    }

    public void setRequestedValue(String requestedValue) {
        this.requestedValue = requestedValue;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getSpacePosition() {
        return spacePosition;
    }
}
