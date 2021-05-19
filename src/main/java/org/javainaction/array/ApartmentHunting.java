package org.javainaction.array;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApartmentHunting {
    // O(b^2*r) time | O(b) where b is the number of blokcs and r is the
    // numer of requirements
    public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
        int[] maxDistancesAtBlocks = new int[blocks.size()];
        Arrays.fill(maxDistancesAtBlocks, Integer.MIN_VALUE);

        for(int i = 0; i < blocks.size(); i++){
            for(String req : reqs){
                int closestReqDistance = Integer.MAX_VALUE;
                for(int j = 0; j < blocks.size(); j++){
                    if(blocks.get(j).get(req)) {
                        closestReqDistance = Math.min(closestReqDistance, distanceBetween(i, j));
                    }
                }
                maxDistancesAtBlocks[i] = Math.max(maxDistancesAtBlocks[i], closestReqDistance);
            }
        }
        return getIdxAtMinValue(maxDistancesAtBlocks);
    }

    public static int getIdxAtMinValue(int[] array) {
        int idxAtMinValue = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int currentValue = array[i];
            if (currentValue < minValue) {
                minValue = currentValue;
                idxAtMinValue = i;
            }
        }
        return idxAtMinValue;
    }

    public static int distanceBetween(int a, int b) {
        return Math.abs(a - b);
    }
}
