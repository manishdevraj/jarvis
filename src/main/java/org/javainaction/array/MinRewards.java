package org.javainaction.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MinRewards {
    public static int minRewards(int[] scores) {
        // O(n) time | O(n) space
        int min = Integer.MAX_VALUE;
        int[] rewards = new int[scores.length];
        Arrays.fill(rewards, 1);

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        for (int i = scores.length - 2; i >= 0; i--) {
            if (scores[i] > scores[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i+1] + 1);
            }
        }

        return IntStream.of(rewards).sum();
    }
}
