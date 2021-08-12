package com.mathematicalbasedefenders.mathematicalbasedefenders.core;

import com.mathematicalbasedefenders.mathematicalbasedefenders.game.Tile;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public int[] bagQuantities = new int[23];
    public ArrayList<Integer> indexesOfAvailableTerms = new ArrayList<>();
    public final int[] INITIAL_BAG_QUANTITIES = new int[]{4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0};
    public int totalRemainingBagQuantities = 0;

    public void resetBag() {


        totalRemainingBagQuantities = 49;
        for (int i = 0; i < bagQuantities.length; i++) {
            bagQuantities[i] = INITIAL_BAG_QUANTITIES[i];
            indexesOfAvailableTerms.add(i);
        }

        for (int i = 0; i < bagQuantities.length; i++) {
            if (bagQuantities[i] == 0) {
                indexesOfAvailableTerms.remove((Integer) i);
            }
        }

    }


    public Core.Term generateTileTerm() {
        Random random = new Random();
        int roll, index;

        if (indexesOfAvailableTerms.size() == 0) {
            resetBag();
        }

        index = random.nextInt(indexesOfAvailableTerms.size());
        roll = indexesOfAvailableTerms.get(index);


        bagQuantities[roll]--;

        if (bagQuantities[roll] == 0) {
            indexesOfAvailableTerms.remove((Integer) roll);
        }


        return Tile.getTermName(roll);

    }


}
