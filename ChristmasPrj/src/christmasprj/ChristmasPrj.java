/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package christmasprj;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class ChristmasPrj {

    static final Scanner sc = new Scanner(System.in);
    static final Random rng = new Random();
    static final WaveGen waveGen = new WaveGen(rng);
    static final HouseGen houseGen = new HouseGen(rng);

    static final double SNOWMAN_CHANCE = 0.75;
    
    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Zadejte seed (celé číslo): ");
        int seed = sc.nextInt();
        System.out.print("Zadejte šířku obrázku ve znacích (celé číslo, ideálně minimálně 150): ");
        int width = sc.nextInt();
        System.out.println();

        rng.setSeed(seed);

        Canvas canvas = new Canvas(width, 32);

        int[] bgMountains = waveGen.generate(-1, canvas.getWidth(), 9, new int[]{3, 1}, 0.05);
        canvas.draw(0, 0, drawMountains(bgMountains, 9, '.'));
        int[] middleMountains = waveGen.generate(-1, canvas.getWidth(), 10, new int[]{1, 1}, 0.1);
        canvas.draw(0, 0, drawMountains(middleMountains, 10, 'x'));
        int[] fgMountains = waveGen.generate(-1, canvas.getWidth(), 6, new int[]{3, 1}, 0.2);
        canvas.draw(0, 5, drawMountains(fgMountains, 6, 'X'));

        for (int i = 3; i <= 6; i += 3) {
            for (int j = rng.nextInt(30); j < fgMountains.length; j += rng.nextInt(20) + 6) {
                canvas.draw(j, i + fgMountains[j], drawTree());
            }
        }

        int[] river = waveGen.generate(-1, canvas.getWidth(), 8, new int[]{10, 1}, 0.1);

        for (int i = 9; i <= 11; i += 2) {
            for (int j = rng.nextInt(30); j < river.length; j += rng.nextInt(30) + 6) {
                canvas.draw(j, i + river[j], drawTree());
            }
        }

        for (int i = 16; i < 19; i++) {
            for (int j = 0; j < river.length; j++) {
                canvas.draw(j, i + river[j], '+');
            }
        }

        Canvas house;
        for (int j = rng.nextInt(150); j < river.length;) {
            house = houseGen.generate();
            canvas.draw(j, 24 + river[j] - house.getHeight(), house);

            int snowmanOffset = rng.nextInt(3);
            if (rng.nextDouble() < SNOWMAN_CHANCE) {
                if (rng.nextBoolean()) {
                    canvas.draw(j - snowmanOffset, 21 + river[j - snowmanOffset], drawSnowman());
                } else {
                    canvas.draw(j + house.getWidth() + snowmanOffset, 21 + river[j], drawSnowman());
                }
            }

            j += rng.nextInt(150) + house.getWidth();
        }

        canvas.draw(2, 1, new String[]{
            "/------------------\\",
            "| Merry Christmas! |",
            "\\------------------/"
        });

        System.out.println(canvas);
    }

    public static Canvas drawMountains(int[] wave, int height, char character) {
        Canvas canvas = new Canvas(wave.length, height);
        for (int i = 0; i < wave.length; i++) {
            canvas.draw(i, wave[i], character);

            for (int j = wave[i] + 1; j < height; j++) {
                canvas.draw(i, j, ' ');
            }
        }
        return canvas;
    }

    public static Canvas drawTree() {
        Canvas canvas = new Canvas(3, 4);
        canvas.draw(0, 0, new String[]{
            "\0_\0",
            "/|\\",
            "/|\\",
            "/|\\"
        });
        return canvas;
    }

    public static Canvas drawSnowman() {
        Canvas canvas = new Canvas(3, 4);
        canvas.draw(0, 0, new String[]{
            "\0_\0",
            "\0O-",
            "(\\)",
            "(_)"
        });

        if (rng.nextBoolean()) {
            canvas.flipHorizontal();
        }
        return canvas;
    }
}
