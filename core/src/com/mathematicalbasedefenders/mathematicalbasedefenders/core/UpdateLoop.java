package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.utils.TimeUtils;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.core;

public class UpdateLoop implements Runnable {

    public int updatesPerSecond;
    public static int updatesPerSecondToLog;
    double lastUpdateTime, lastTimeStatsPrinted;
    public static double timeTookForUpdateToCompute;

    @Override
    public void run() {

        double d = 0;
        double ct;
        double lu = TimeUtils.millis();
        double nt = TimeUtils.millis() + 1e3d;


        while (core.gameAllowedToRender) {

            ct = TimeUtils.millis();
            double lrt = ct - lu;
            d += lrt / 1e3d;
            lu = ct;

            lastUpdateTime = TimeUtils.millis();

            while (d > 1d / 60d) {

                computeUpdate();
                d -= 1d / 60d;

            }

            if (TimeUtils.millis() > nt) {
                lastTimeStatsPrinted = TimeUtils.millis();
                updatesPerSecondToLog = updatesPerSecond;
                updatesPerSecond = 0;
                nt = TimeUtils.millis() + 1e3d;
            }


        }
    }


    public void computeUpdate() {

        double startTime = TimeUtils.millis();

        core.computeUpdate();

        updatesPerSecond++;
        timeTookForUpdateToCompute = (TimeUtils.millis() - startTime) / 1000d;

    }

}
