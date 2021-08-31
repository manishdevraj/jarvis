package org.javainaction.array;

import java.util.*;

/**
 * Find apartment closest with min distance from amenities
 */
public class ApartmentHunting {
    // O(b^2*r) time | O(b) where b is the number of blocks and r is the
    // number of requirements
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

    public static void main(String[] args) {
        var blocks = new ArrayList<Map<String, Boolean>>();

        blocks.add(0, new HashMap<>());
        blocks.get(0).put("gym", false);
        blocks.get(0).put("school", true);
        blocks.get(0).put("store", false);

        blocks.add(1, new HashMap<>());
        blocks.get(1).put("gym", true);
        blocks.get(1).put("school", false);
        blocks.get(1).put("store", false);

        blocks.add(2, new HashMap<>());
        blocks.get(2).put("gym", true);
        blocks.get(2).put("school", true);
        blocks.get(2).put("store", false);

        blocks.add(3, new HashMap<>());
        blocks.get(3).put("gym", false);
        blocks.get(3).put("school", true);
        blocks.get(3).put("store", false);

        blocks.add(4, new HashMap<>());
        blocks.get(4).put("gym", false);
        blocks.get(4).put("school", true);
        blocks.get(4).put("store", true);

        String[] reqs = new String[] {"gym", "school", "store"};
        System.out.println(apartmentHunting(blocks, reqs) == 3);
    }
}
