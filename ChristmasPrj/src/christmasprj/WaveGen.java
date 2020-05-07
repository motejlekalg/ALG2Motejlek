/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package christmasprj;

import java.util.Random;

/**
 *
 * @author Martin Motejlek
 */
public class WaveGen {

    private final Random rng;

    public WaveGen() {
        rng = new Random();
    }

    public WaveGen(Random rngParam) {
        rng = rngParam;
    }

    public int[] generate(int start, int length, int height, int[] stepWeights, double turnProbability) {
        int[] waves = new int[length];
        int maxStep;

        if (start < 0) {
            start = rng.nextInt(height);
        }
        waves[0] = start;

        boolean inreasing = rng.nextBoolean();

        for (int i = 1; i < waves.length; i++) {
            if (waves[i - 1] == 0) {
                inreasing = true;
            } else if (waves[i - 1] == height - 1) {
                inreasing = false;
            } else {
                inreasing = (rng.nextDouble() < turnProbability) ? !inreasing : inreasing;
            }

            if (inreasing) {
                maxStep = (height - 1) - waves[i - 1];
            } else {
                maxStep = waves[i - 1];
            }

            waves[i] = waves[i - 1] + getRandomStep(stepWeights, maxStep) * (inreasing ? 1 : -1);
        }

        return waves;
    }

    private int getRandomStep(int[] stepWeights, int maxStep) {
        int maxIdx = Math.min(maxStep, stepWeights.length - 1);

        int random = rng.nextInt(sumArray(stepWeights, maxIdx));

        int sum = 0;
        for (int i = 0; i <= maxStep; i++) {
            sum += stepWeights[i];

            if (random < sum) {
                return i;
            }
        }

        return 0;
    }

    private int sumArray(int[] array, int maxIdx) {
        int sum = 0;

        for (int i = 0; i <= maxIdx; i++) {
            sum += array[i];
        }

        return sum;
    }
}
