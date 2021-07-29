package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.badlogic.gdx.utils.TimeUtils;

import static com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders.core;

public class TickLoop implements Runnable {

    public int ticksPerSecond;
    public static int ticksPerSecondToLog;
    double lastTickTime, lastTimeStatsPrinted;
    public static double timeTookForTickToCompute;

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

            lastTickTime = TimeUtils.millis();

            while (d > 1d / 100d) {

                computeTick();
                d -= 1d / 100d;

            }

            if (TimeUtils.millis() > nt) {
                lastTimeStatsPrinted = TimeUtils.millis();
                ticksPerSecondToLog = ticksPerSecond;
                ticksPerSecond = 0;
                nt = TimeUtils.millis() + 1e3d;
            }

        }
    }

    public void computeTick() {

        double startTime = TimeUtils.millis();

        core.computeTick();

        ticksPerSecond++;
        timeTookForTickToCompute = (TimeUtils.millis() - startTime) / 1000d;

    }


}
