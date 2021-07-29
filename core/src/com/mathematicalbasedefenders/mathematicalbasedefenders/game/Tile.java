package com.mathematicalbasedefenders.mathematicalbasedefenders.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Core;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Log;

import java.util.Random;


public class Tile {

    //TODO: Fill javadoc

    public ImageButton tile;
    Texture tileTexture;
    public static Core.Term[] termNames = {Core.Term.ZERO, Core.Term.ONE, Core.Term.TWO, Core.Term.THREE, Core.Term.FOUR, Core.Term.FIVE, Core.Term.SIX, Core.Term.SEVEN, Core.Term.EIGHT, Core.Term.NINE, Core.Term.ADDITION_SIGN, Core.Term.SUBTRACTION_SIGN, Core.Term.MULTIPLICATION_SIGN, Core.Term.DIVISION_SIGN, Core.Term.EQUALS_SIGN, Core.Term.A, Core.Term.B, Core.Term.C, Core.Term.D, Core.Term.N, Core.Term.X, Core.Term.Y, Core.Term.Z};
    public static String[] termsAsStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "=", "a", "b", "c", "d", "n", "x", "y", "z"};
    public static String[] termsAsBetterStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "×", "÷", "=", "a", "b", "c", "d", "n", "x", "y", "z"};

    //tile information
    public TextureRegion tileTextureRegion;
    public boolean selected = false;
    public Core.Term term;
    public int row, column;


    public enum Position {
        A1, A2, A3, A4, A5, A6, A7,
        B1, B2, B3, B4, B5, B6, B7,
        C1, C2, C3, C4, C5, C6, C7,
        D1, D2, D3, D4, D5, D6, D7,
        E1, E2, E3, E4, E5, E6, E7,
        F1, F2, F3, F4, F5, F6, F7,
        G1, G2, G3, G4, G5, G6, G7
    }

    public static Position[] positions = {
            Position.A1, Position.A2, Position.A3, Position.A4, Position.A5, Position.A6, Position.A7,
            Position.B1, Position.B2, Position.B3, Position.B4, Position.B5, Position.B6, Position.B7,
            Position.C1, Position.C2, Position.C3, Position.C4, Position.C5, Position.C6, Position.C7,
            Position.D1, Position.D2, Position.D3, Position.D4, Position.D5, Position.D6, Position.D7,
            Position.E1, Position.E2, Position.E3, Position.E4, Position.E5, Position.E6, Position.E7,
            Position.F1, Position.F2, Position.F3, Position.F4, Position.F5, Position.F6, Position.F7,
            Position.G1, Position.G2, Position.G3, Position.G4, Position.G5, Position.G6, Position.G7
    };


    /**
     * Creates a new tile.
     *
     * @param row    The y coordinate of the tile. (highest --> y = 0, lowest --> y = 6)
     * @param column The x coordinate of the tile.
     */
    public Tile(int column, int row) {

        this.row = row;
        this.column = column;

        Random random = new Random();
        int roll = random.nextInt(23);

        // set properties
        this.term = getTermName(roll);

        TextureRegion tileTextureRegion = MathematicalBaseDefenders.core.tilesTextureAtlasRegions[0][roll];


        // create ImageButton and assign it to tile
        tile = new ImageButton(new TextureRegionDrawable(tileTextureRegion));

        // set position of tiles
        tile.setPosition(733 + (64 * column), 560 - (64 * row));

        // add ClickListener
        tile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                processTileClick(Core.getMouseXPosition(), Core.getMouseYPosition());
            }

        });

        MathematicalBaseDefenders.core.currentStageToDrawOn.addActor(tile);

    }

    /**
     * Creates a new tile.
     *
     * @param column
     * @param row
     * @param selected
     */
    public Tile(int column, int row, boolean selected) {

        this.row = row;
        this.column = column;

        Random random = new Random();
        int roll = random.nextInt(23);

        // set properties
        this.selected = selected;
        this.term = getTermName(roll);

        TextureRegion tileTextureRegion = MathematicalBaseDefenders.core.tilesTextureAtlasRegions[selected ? 1 : 0][roll];

        // create ImageButton and assign it to tile
        tile = new ImageButton(new TextureRegionDrawable(tileTextureRegion));

        // set position of tiles
        tile.setPosition(733 + (64 * column), 560 - (64 * row));

        // add ClickListener
        tile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // System.out.println(Core.getMouseXPosition() + " " + Core.getMouseYPosition());
                processTileClick(Core.getMouseXPosition(), Core.getMouseYPosition());
            }

        });

        MathematicalBaseDefenders.core.currentStageToDrawOn.addActor(tile);

    }

    /**
     * Creates a new tile.
     *
     * @param column
     * @param row
     * @param term
     */
    public Tile(int column, int row, Core.Term term) {

        this.row = row;
        this.column = column;

        int forcedRoll = getTermID(term);

        // set properties
        this.term = getTermName(forcedRoll);


        TextureRegion tileTextureRegion = MathematicalBaseDefenders.core.tilesTextureAtlasRegions[0][forcedRoll];

        // create ImageButton and assign it to tile
        tile = new ImageButton(new TextureRegionDrawable(tileTextureRegion));

        // set position of tiles
        tile.setPosition(733 + (64 * column), 560 - (64 * row));

        // add ClickListener
        tile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                processTileClick(Core.getMouseXPosition(), Core.getMouseYPosition());
            }

        });

        MathematicalBaseDefenders.core.currentStageToDrawOn.addActor(tile);

    }

    /**
     * Creates a new tile.
     *
     * @param column
     * @param row
     * @param term
     * @param selected
     */
    public Tile(int column, int row, Core.Term term, boolean selected) {

        this.row = row;
        this.column = column;

        int forcedRoll = getTermID(term);

        // set properties
        this.selected = selected;
        this.term = getTermName(forcedRoll);

        TextureRegion tileTextureRegion = MathematicalBaseDefenders.core.tilesTextureAtlasRegions[selected ? 1 : 0][forcedRoll];

        // create ImageButton and assign it to tile
        tile = new ImageButton(new TextureRegionDrawable(tileTextureRegion));

        // set position of tiles
        tile.setPosition(733 + (64 * column), 560 - (64 * row));

        // add ClickListener
        tile.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                processTileClick(Core.getMouseXPosition(), Core.getMouseYPosition());
            }

        });

        MathematicalBaseDefenders.core.currentStageToDrawOn.addActor(tile);
    }

    /**
     * Processes the tile click.
     *
     * @param xPos
     * @param yPos
     */
    public void processTileClick(float xPos, float yPos) {
        Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].selected = !Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].selected;

        Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)] = new Tile(getColumnOfTile(xPos), getRowOfTile(yPos), Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].term, Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].selected);

        // System.out.println(xPos + " " + yPos + " " + getColumnOfTile(xPos) + " " + getRowOfTile(yPos) + " " + Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].selected);

        if (Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].selected) {
            // selected is now true
            Game.currentProblemAsArrayList.add(changeTermNameToString(Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)].term));
            int[] selectedTilePosition = {getColumnOfTile(xPos), getRowOfTile(yPos)};
            Game.currentTilesInProblem.add(Game.tiles[getColumnOfTile(xPos)][getRowOfTile(yPos)]);
            MathematicalBaseDefenders.renderer.renderProblemAsSpecialComputerModernFont(MathematicalBaseDefenders.game.convertProblemArrayToString(Game.currentProblemAsArrayList, true), 150, 680, 32, true, false);
        } else {
            // selected is now false
            for (int i = 0; i < Game.currentProblemAsArrayList.size(); i++) {
                if (Game.currentTilesInProblem.get(i).getColumn() == getColumnOfTile(xPos) && Game.currentTilesInProblem.get(i).getRow() == getRowOfTile(yPos)) {
                    Game.currentProblemAsArrayList.remove(i);
                    Game.currentTilesInProblem.remove(i);
                }
            }
            MathematicalBaseDefenders.renderer.renderProblemAsSpecialComputerModernFont(MathematicalBaseDefenders.game.convertProblemArrayToString(Game.currentProblemAsArrayList, true), 150, 680, 32, true, false);
        }

    }


    public static int getColumnOfTile(float xPos) {
        for (int i = 0; i < 7; i++) {
            if ((733 + 64 * i) <= xPos && xPos <= (733 + 64 * (i + 1))) {
                return i;
            }
        }
        Log.logErrorMessage("xPos " + xPos + " does not correspond to any tile column.");
        return -1;
    }

    public static int getRowOfTile(float yPos) {
        for (int i = 0; i < 7; i++) {
            if ((560 - 64 * i) <= yPos && (560 - 64 * (i + 1)) <= yPos) {
                return i;
            }
        }
        Log.logErrorMessage("yPos " + yPos + " does not correspond to any tile row.");
        return -1;
    }

    /**
     * Converts a term name to the term name's ID.
     *
     * @param name The name of the term.
     * @return The ID of <code>name</code>.
     */
    public static int getTermID(Core.Term name) {
        for (int i = 0; i < 23; i++) {
            if (termNames[i] == name) {
                return i;
            }
        }
        Log.logErrorMessage("Unknown term name: " + name);
        // throws exception
        throw new IllegalArgumentException("Unknown term name: " + name);
    }

    /**
     * Gets the term ID of the specified tile.
     *
     * @param column The column of the tile.
     * @param row    The row of the tile.
     * @return The term ID.
     */
    public int getTermID(int column, int row) {
        for (int i = 0; i < 23; i++) {
            if (termNames[i] == Game.tiles[column][row].term) {
                return i;
            }
        }
        // throws exception
        return -1;
    }

    /**
     * Converts a numerical id to a Term's name according to <code>tileNames</code>.
     *
     * @param id The ID.
     * @return The Term Name.
     */
    public static Core.Term getTermName(int id) {
        return termNames[id];
    }

    /**
     * Converts a numerical id to a Term's name according to <code>tileNames</code>.
     *
     * @param column The column (x-axis) of the tile.
     * @param row    The row (y-axis, top = 0) of the tile.
     * @return The Term Name.
     */
    public static Core.Term getTermName(int column, int row) {
        return Game.tiles[column][row].term;
    }


    /**
     * @param name
     * @return
     */
    public static String changeTermNameToString(Core.Term name) {
        for (int i = 0; i < termNames.length; i++) {
            if (termNames[i] == name) {
                return termsAsStrings[i];
            }
        }
        Log.logErrorMessage("Unknown term name: " + name);
        throw new IllegalArgumentException("Unknown term name: " + name);
    }


    // getters
    public float getXPosition() {
        return tile.getX();
    }

    public float getYPosition() {
        return tile.getY();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Core.Term getTerm() {
        return getTermName(getTermID(this.term));
    }

}
