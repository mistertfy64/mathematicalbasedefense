package com.mathematicalbasedefenders.mathematicalbasedefenders.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Evaluator;
import com.mathematicalbasedefenders.mathematicalbasedefenders.net.Networking;
import org.mariuszgromada.math.mxparser.Argument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {


    public static Tile[][] tiles = new Tile[7][7];

    public static ArrayList<String> currentProblemAsArrayList = new ArrayList<>();
    public static ArrayList<Tile> currentTilesInProblem = new ArrayList<>();

    public Map<Long, Enemy> activeEnemies = new HashMap<>();
    public Map<Long, Texture> activeEnemiesAsTextures = new HashMap<>();

    public long enemiesSpawned, enemiesKilled;
    public float score;
    public double gameStartTimeInMilliseconds, timeElapsedInMilliseconds, finalTimeElapsedInMilliseconds;
    public Argument valueOfVariableA, valueOfVariableB, valueOfVariableC, valueOfVariableD, valueOfVariableN, valueOfVariableX, valueOfVariableY, valueOfVariableZ;
    public int currentBaseHealth, initialBaseHealth;


    public boolean singleplayerGameRunning = false, singleplayerGameIsOver = false;

    public void startSingleplayerGame() {

        score = 0;

        enemiesSpawned = 0;
        enemiesKilled = 0;

        currentBaseHealth = 10;
        initialBaseHealth = 10;

        valueOfVariableA = null;
        valueOfVariableB = null;
        valueOfVariableC = null;
        valueOfVariableD = null;
        valueOfVariableN = null;
        valueOfVariableX = null;
        valueOfVariableY = null;
        valueOfVariableZ = null;

        activeEnemies.clear();
        activeEnemiesAsTextures.clear();
        currentProblemAsArrayList.clear();
        currentTilesInProblem.clear();


        gameStartTimeInMilliseconds = TimeUtils.millis();


        singleplayerGameRunning = true;
    }

    public void stopSingleplayerGame() {
        singleplayerGameRunning = false;
    }

    public void endSingleplayerGame() {

        activeEnemies.clear();
        activeEnemiesAsTextures.clear();
        currentProblemAsArrayList.clear();
        currentTilesInProblem.clear();

        finalTimeElapsedInMilliseconds = ((TimeUtils.millis() - gameStartTimeInMilliseconds));


        singleplayerGameRunning = false;
        singleplayerGameIsOver = true;
        // send data to server

        if (!Networking.socketID.equals("") && !Networking.currentLoggedInUser.equals("")) {
            MathematicalBaseDefenders.networking.socket.emit("scoreSubmission", Double.toString(score), Networking.currentLoggedInUser, Networking.userIDOfCurrentlyLoggedInUser, Networking.socketID);
        }


    }


    /**
     * Converts an array full of terms to something to show to the player.
     *
     * @param problemArray The problem array.
     * @return A string.
     */
    public String convertProblemArrayToString(ArrayList<String> problemArray, boolean returnBeautifulStrings) {
        String toReturn = "";
        for (int i = 0; i < problemArray.size(); i++) {
            for (int j = 0; j < Tile.termsAsStrings.length; j++) {
                if (Tile.termsAsStrings[j].equals(problemArray.get(i))) {
                    if (returnBeautifulStrings) {
                        toReturn += Tile.termsAsBetterStrings[j];
                    } else {
                        toReturn += Tile.termsAsStrings[j];
                    }

                }
            }
        }
        return toReturn;
    }

    public void sendProblem() {
        String problem = convertProblemArrayToString(Game.currentProblemAsArrayList, false);
        if (Evaluator.evaluateProblem(problem)[0]) {
            // problem killed enemy or variable assignment is complete
            for (int i = 0; i < Game.currentTilesInProblem.size(); i++) {
                int c = Game.currentTilesInProblem.get(i).getColumn(), r = Game.currentTilesInProblem.get(i).getRow();
                Game.tiles[c][r] = null;
                Game.tiles[c][r] = new Tile(c, r, false);
            }
            Game.currentTilesInProblem.clear();
            Game.currentProblemAsArrayList.clear();
        }
    }


}
