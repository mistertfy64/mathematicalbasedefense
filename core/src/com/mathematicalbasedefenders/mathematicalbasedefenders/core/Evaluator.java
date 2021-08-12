package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.utils.TimeUtils;
import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Game;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class Evaluator {


    // TODO: Remove static
    public static boolean[] evaluateProblem(String problem) {

        String rawProblem = "";

        boolean solutionFound = false;
        int equalSignOccurrences = MathematicalBaseDefenders.utilities.countOccurrencesOfACharacterInAString(problem, '=');
        if (equalSignOccurrences == 0) {


            rawProblem = problem;

            problem = problem.replaceAll("a", "v1vl");
            problem = problem.replaceAll("b", "v2vl");
            problem = problem.replaceAll("c", "v3vl");
            problem = problem.replaceAll("d", "v4vl");
            problem = problem.replaceAll("n", "v5vl");
            problem = problem.replaceAll("x", "v6vl");
            problem = problem.replaceAll("y", "v7vl");
            problem = problem.replaceAll("z", "v8vl");


            problem = problem.replaceAll("([^\\d])*\\*?v1vl", "*v1vl");
            problem = problem.replaceAll("([^\\d])*\\*?v2vl", "*v2vl");
            problem = problem.replaceAll("([^\\d])*\\*?v3vl", "*v3vl");
            problem = problem.replaceAll("([^\\d])*\\*?v4vl", "*v4vl");
            problem = problem.replaceAll("([^\\d])*\\*?v5vl", "*v5vl");
            problem = problem.replaceAll("([^\\d])*\\*?v6vl", "*v6vl");
            problem = problem.replaceAll("([^\\d])*\\*?v7vl", "*v7vl");
            problem = problem.replaceAll("([^\\d])*\\*?v8vl", "*v8vl");

            problem = problem.replaceAll("l([0-9])", "l*$1");


            problem = problem.replaceAll("v1vl", "valueOfVariableA");
            problem = problem.replaceAll("v2vl", "valueOfVariableB");
            problem = problem.replaceAll("v3vl", "valueOfVariableC");
            problem = problem.replaceAll("v4vl", "valueOfVariableD");
            problem = problem.replaceAll("v5vl", "valueOfVariableN");
            problem = problem.replaceAll("v6vl", "valueOfVariableX");
            problem = problem.replaceAll("v7vl", "valueOfVariableY");
            problem = problem.replaceAll("v8vl", "valueOfVariableZ");


            float result = (float) new Expression(problem, MathematicalBaseDefenders.game.valueOfVariableA, MathematicalBaseDefenders.game.valueOfVariableB, MathematicalBaseDefenders.game.valueOfVariableC, MathematicalBaseDefenders.game.valueOfVariableD, MathematicalBaseDefenders.game.valueOfVariableN, MathematicalBaseDefenders.game.valueOfVariableX, MathematicalBaseDefenders.game.valueOfVariableY, MathematicalBaseDefenders.game.valueOfVariableZ).calculate();
            int solutions = 0;


            Long[] enemyNumbersOfEnemiesToRenderAsLongArray = MathematicalBaseDefenders.core.getEnemyNumbersToRender(Game.activeEnemies.keySet());
            for (Long aLong : enemyNumbersOfEnemiesToRenderAsLongArray) {
                try {
                    if (result == new Expression(Game.activeEnemies.get(aLong).getRequestedValue()).calculate() || rawProblem.equals(Game.activeEnemies.get(aLong).getRequestedValue())) {

                        solutions++;
                        solutionFound = true;
                    }
                } catch (Exception exception) {
                    Log.logWarningMessage("Enemy #" + aLong + " produced an exception while checking problem.");
                }
            }

            for (Long aLong : enemyNumbersOfEnemiesToRenderAsLongArray) {
                try {
                    if (result == new Expression(Game.activeEnemies.get(aLong).getRequestedValue()).calculate() || rawProblem.equals(Game.activeEnemies.get(aLong).getRequestedValue())) {


                        /*
                         *
                         * 100*f(l)*(1.1^(max(c,0)-1))*g(s)
                         *
                         *
                         *        |  1 if l < 7
                         * f(l) = |  1+0.1(l-6) if 7 <= l <= 13
                         *        |  1+0.2(l-9) if 14 <= l
                         *
                         *
                         * g(s) = 1 if s < 5, 1*floor(s-5) if otherwise
                         *
                         *
                         */

                        double multiplier = 1;
                        // calculate multiplier
                        if (problem.length() >= 7 && problem.length() <= 13) {
                            multiplier = 1 + 0.1 * (problem.length() - 6);
                        } else if (14 <= problem.length()) {
                            multiplier = 1 + 0.2 * (problem.length() - 9);
                        }

                        double m1 = (multiplier);
                        double m2 = (Math.pow(1.1, Math.max(MathematicalBaseDefenders.game.currentCombo, 0)));
                        double m3 = (Game.activeEnemies.get(aLong).getSpacePosition() < 5 ? 1 : 1 + (0.1 * Math.floor(Game.activeEnemies.get(aLong).getSpacePosition() - 5)));

                        MathematicalBaseDefenders.game.score += Math.round(100 * m1 * m2 * m3);
                        MathematicalBaseDefenders.game.enemiesKilled++;
                        MathematicalBaseDefenders.game.currentCombo++;
                        MathematicalBaseDefenders.game.timeOfLastEnemyKill = TimeUtils.millis();

                        solutionFound = true;

                        Game.activeEnemies.remove(aLong);
                    }
                } catch (Exception exception) {
                    Log.logWarningMessage("Enemy #" + aLong + " produced an exception while checking problem.");
                }
            }


            if (solutionFound) {
                Renderer.eraseAllProblemSpecialComputerModernFont();
                return new boolean[]{true, true};
            } else {
                return new boolean[]{false, false};
            }


        } else if (equalSignOccurrences == 1) {
            int equalSignIndex = problem.indexOf('='), totalVariables = 0;
            String variables = "abcdnxyz";
            char variable = '?';


            for (int i = 0; i < variables.length(); i++) {
                totalVariables += MathematicalBaseDefenders.utilities.countOccurrencesOfACharacterInAString(problem, variables.charAt(i));
            }

            if (totalVariables == 1) {

                for (int i = 0; i < problem.length(); i++) {
                    if (problem.charAt(i) == 'a' || problem.charAt(i) == 'b' || problem.charAt(i) == 'c' || problem.charAt(i) == 'd' || problem.charAt(i) == 'n' || problem.charAt(i) == 'x' || problem.charAt(i) == 'y' || problem.charAt(i) == 'z') {
                        variable = problem.charAt(i);
                    }
                }

                problem = problem.replaceAll("a", "v1");
                problem = problem.replaceAll("b", "v2");
                problem = problem.replaceAll("c", "v3");
                problem = problem.replaceAll("d", "v4");
                problem = problem.replaceAll("n", "v5");
                problem = problem.replaceAll("x", "v6");
                problem = problem.replaceAll("y", "v7");
                problem = problem.replaceAll("z", "v8");
                problem = problem.replaceAll("v1", "valueOfVariableA");
                problem = problem.replaceAll("v2", "valueOfVariableB");
                problem = problem.replaceAll("v3", "valueOfVariableC");
                problem = problem.replaceAll("v4", "valueOfVariableD");
                problem = problem.replaceAll("v5", "valueOfVariableN");
                problem = problem.replaceAll("v6", "valueOfVariableX");
                problem = problem.replaceAll("v7", "valueOfVariableY");
                problem = problem.replaceAll("v8", "valueOfVariableZ");


                switch (variable) {
                    case 'a': {
                        MathematicalBaseDefenders.game.valueOfVariableA = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'b': {
                        MathematicalBaseDefenders.game.valueOfVariableB = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'c': {
                        MathematicalBaseDefenders.game.valueOfVariableC = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'd': {
                        MathematicalBaseDefenders.game.valueOfVariableD = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'n': {
                        MathematicalBaseDefenders.game.valueOfVariableN = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'x': {
                        MathematicalBaseDefenders.game.valueOfVariableX = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'y': {
                        MathematicalBaseDefenders.game.valueOfVariableY = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                    case 'z': {
                        MathematicalBaseDefenders.game.valueOfVariableZ = new Argument(problem);
                        Renderer.eraseAllProblemSpecialComputerModernFont();
                        break;
                    }
                }

            }


            return new boolean[]{true, false};
        } else {
            return new boolean[]{false, false};
        }


    }


}
