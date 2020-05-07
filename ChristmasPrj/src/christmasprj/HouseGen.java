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
public class HouseGen {

    static private final int MIN_WALL_COUNT = 1;
    static private final int MAX_WALL_COUNT = 3;
    static private final int MIN_FLOOR_COUNT = 1;
    static private final int MAX_FLOOR_COUNT = 2;

    static private final int WALL_WIDTH = 8;
    static private final int WALL_HEIGHT = 4;

    static private final int WALL_SEP_WIDTH = 2;
    static private final int WALL_SEP_TOP_SIDE_WIDTH = 1;

    static private final int WALL_TOP_HEIGHT = 1;

    static private final int ROOF_SIDE_WIDTH = 3;
    static private final int ROOF_OFFSET = 2;
    static private final int ROOF_TOP_WIDTH = 6;
    static private final int ROOF_TOP_HEIGHT = 2;

    static private final int CHIMNEY_WIDTH = 3;
    static private final int CHIMNEY_HEIGHT = 4;

    static private final int DOOR_WIDTH = 4;
    static private final int DOOR_HEIGHT = 2;
    static private final int DOOR_ROOF_WIDTH = 6;
    static private final int DOOR_ROOF_HEIGHT = 1;

    static private final int WINDOW_WIDTH = 8;
    static private final int WINDOW_CLOSED_WIDTH = 4;
    static private final int WINDOW_HEIGHT = 2;

    static private final double WINDOW_CLOSED_PROB = 0.25;
    static private final double ICICLE_PROB = 0.33;

    private final Random rng;

    public HouseGen() {
        rng = new Random();
    }

    public HouseGen(Random rngParam) {
        rng = rngParam;
    }

    public Canvas generate() {
        int wallCount = rng.nextInt(MAX_WALL_COUNT - MIN_WALL_COUNT + 1) + MIN_WALL_COUNT;
        int floorCount = rng.nextInt(MAX_FLOOR_COUNT - MIN_FLOOR_COUNT + 1) + MIN_FLOOR_COUNT;

        Canvas canvas = new Canvas(calcRoofWidth(wallCount), calcRoofHeight(wallCount) + floorCount * WALL_HEIGHT + (floorCount - 1) * WALL_TOP_HEIGHT);
        Canvas roof = drawRoof(wallCount);
        canvas.draw(0, 0, roof);

        int y = calcRoofHeight(wallCount) - WALL_TOP_HEIGHT;

        for (int i = 0; i < floorCount; i++) {
            canvas.draw(ROOF_SIDE_WIDTH, y, drawFloorTop(wallCount));
            y += WALL_TOP_HEIGHT;
            canvas.draw(ROOF_SIDE_WIDTH + WALL_SEP_TOP_SIDE_WIDTH, y, drawFloor(wallCount, i == floorCount - 1));
            y += WALL_HEIGHT;
        }

        return canvas;
    }

    private Canvas drawFloor(int wallCount, boolean bottom) {
        Canvas canvas = new Canvas(wallCount * WALL_WIDTH + (wallCount + 1) * WALL_SEP_WIDTH, WALL_HEIGHT);

        int doorPos = -1;
        if (bottom) {
            doorPos = rng.nextInt(wallCount);
        }

        int x = 0;

        canvas.draw(x, 0, drawWallSeparator());
        x += WALL_SEP_WIDTH;

        for (int i = 0; i < wallCount; i++) {
            Canvas wall = drawWall();

            if (i == doorPos) {
                wall.draw((WALL_WIDTH - DOOR_WIDTH) / 2, WALL_HEIGHT - DOOR_HEIGHT, drawDoor());
                wall.draw((WALL_WIDTH - DOOR_ROOF_WIDTH) / 2, WALL_HEIGHT - DOOR_HEIGHT - 1, drawDoorRoof());
            } else {
                if (rng.nextDouble() < WINDOW_CLOSED_PROB) {
                    wall.draw((WALL_WIDTH - WINDOW_CLOSED_WIDTH) / 2, (WALL_HEIGHT - WINDOW_HEIGHT) / 2, drawWindowClosed());
                } else {
                    wall.draw((WALL_WIDTH - WINDOW_WIDTH) / 2, (WALL_HEIGHT - WINDOW_HEIGHT) / 2, drawWindow());
                }
            }

            canvas.draw(x, 0, wall);
            x += WALL_WIDTH;
            canvas.draw(x, 0, drawWallSeparator());
            x += WALL_SEP_WIDTH;
        }

        return canvas;
    }

    private Canvas drawFloorTop(int wallCount) {
        Canvas canvas = new Canvas(wallCount * WALL_WIDTH + (wallCount + 1) * WALL_SEP_WIDTH + 2 * WALL_SEP_TOP_SIDE_WIDTH, WALL_TOP_HEIGHT);

        int x = 0;

        canvas.draw(0, 0, drawWallSeparatorTopLeft());
        x += WALL_SEP_TOP_SIDE_WIDTH;
        canvas.draw(x, 0, drawWallSeparatorTop());
        x += WALL_SEP_WIDTH;

        for (int i = 0; i < wallCount; i++) {
            canvas.draw(x, 0, drawWallTop());
            x += WALL_WIDTH;
            canvas.draw(x, 0, drawWallSeparatorTop());
            x += WALL_SEP_WIDTH;
        }

        canvas.draw(x, 0, drawWallSeparatorTopRight());

        return canvas;
    }

    private int calcRoofWidth(int wallCount) {
        return ROOF_SIDE_WIDTH * 2
                + WALL_SEP_TOP_SIDE_WIDTH * 2
                + WALL_SEP_WIDTH * (wallCount + 1)
                + WALL_WIDTH * wallCount;
    }

    private int calcRoofHeight(int wallCount) {
        return (int) Math.ceil((calcRoofWidth(wallCount) - ROOF_SIDE_WIDTH * 2.0) / (ROOF_OFFSET * 2.0))
                + ROOF_TOP_HEIGHT;
    }

    private Canvas drawRoof(int wallCount) {
        int roof_width = calcRoofWidth(wallCount);
        int roof_height = calcRoofHeight(wallCount);

        Canvas canvas = new Canvas(roof_width, roof_height);

        canvas.draw((roof_width - ROOF_TOP_WIDTH) / 2, 0, drawRoofTop());

        int offset;
        for (int i = ROOF_TOP_HEIGHT; i < roof_height; i++) {
            offset = (roof_height - 1 - i) * ROOF_OFFSET;
            canvas.draw(offset, i, drawRoofRow(roof_width - offset * 2));
        }

        if (roof_height >= roof_height / 2 + WINDOW_HEIGHT + 1) {
            if (rng.nextDouble() < WINDOW_CLOSED_PROB) {
                canvas.draw((roof_width - WINDOW_CLOSED_WIDTH) / 2, roof_height / 2, drawWindowClosed());
            } else {
                canvas.draw((roof_width - WINDOW_WIDTH) / 2, roof_height / 2, drawWindow());
            }
        }

        Canvas chimney = drawChimney();
        int offsetAtChimneyY = (roof_height - 1 - roof_height / 2) * ROOF_OFFSET;
        if (rng.nextBoolean()) {
            canvas.draw(offsetAtChimneyY - 1, roof_height / 2 + 1 - CHIMNEY_HEIGHT, chimney);
        } else {
            chimney.flipHorizontal();
            canvas.draw(roof_width - offsetAtChimneyY - CHIMNEY_WIDTH + 1, roof_height / 2 + 1 - CHIMNEY_HEIGHT, chimney);
        }

        return canvas;
    }

    private Canvas drawRoofRow(int wallCount) {
        Canvas canvas = new Canvas(wallCount, 1);
        Canvas canvasHalf = new Canvas(wallCount / 2, 1);

        for (int i = 0; i < wallCount / 2 - ROOF_SIDE_WIDTH; i++) {
            if (i % 3 == 1) {
                canvasHalf.draw(i, 0, ' ');
            } else {
                canvasHalf.draw(i, 0, '|');
            }
        }
        canvasHalf.draw(wallCount / 2 - ROOF_SIDE_WIDTH, 0, "\\\\ ");

        canvas.draw(wallCount / 2, 0, canvasHalf);
        canvasHalf.flipHorizontal();
        canvas.draw(0, 0, canvasHalf);

        return canvas;
    }

    private Canvas drawRoofTop() {
        Canvas canvas = new Canvas(ROOF_TOP_WIDTH, ROOF_TOP_HEIGHT);

        canvas.draw(0, 0, new String[]{"\0 __ \0", " /()\\ "});

        return canvas;
    }

    private Canvas drawChimney() {
        Canvas canvas = new Canvas(CHIMNEY_WIDTH, CHIMNEY_HEIGHT);

        canvas.draw(0, 0, "ss\0");
        canvas.draw(0, 1, "\0S\0");
        for (int i = 2; i < CHIMNEY_HEIGHT - 1; i++) {
            canvas.draw(0, i, "\0HH");
        }
        canvas.draw(0, CHIMNEY_HEIGHT - 1, "\0H\0");

        return canvas;
    }

    private Canvas drawWall() {
        Canvas canvas = new Canvas(WALL_WIDTH, WALL_HEIGHT);

        for (int y = 0; y < WALL_HEIGHT; y++) {
            for (int x = 0; x < WALL_WIDTH; x++) {
                canvas.draw(x, y, '=');
            }
        }

        for (int x = 0; x < WALL_WIDTH; x++) {
            if (rng.nextDouble() < ICICLE_PROB) {
                if (rng.nextBoolean() && x < WALL_WIDTH - 1) {
                    canvas.draw(x, 0, "\\/");
                    x++;
                } else {
                    canvas.draw(x, 0, 'v');
                }
            }
        }

        return canvas;
    }

    private Canvas drawWallTop() {
        Canvas canvas = new Canvas(WALL_WIDTH, WALL_TOP_HEIGHT);

        for (int x = 0; x < WALL_WIDTH; x++) {
            canvas.draw(x, 0, 'E');
        }

        return canvas;
    }

    private Canvas drawWallSeparator() {
        Canvas canvas = new Canvas(WALL_SEP_WIDTH, WALL_HEIGHT);

        for (int y = 0; y < WALL_HEIGHT; y++) {
            canvas.draw(0, y, "||");
        }

        return canvas;
    }

    private Canvas drawWallSeparatorTop() {
        Canvas canvas = new Canvas(WALL_SEP_WIDTH, WALL_TOP_HEIGHT);

        canvas.draw(0, 0, "()");

        return canvas;
    }

    private Canvas drawWallSeparatorTopLeft() {
        Canvas canvas = new Canvas(WALL_SEP_TOP_SIDE_WIDTH, WALL_TOP_HEIGHT);

        canvas.draw(0, 0, '[');

        return canvas;
    }

    private Canvas drawWallSeparatorTopRight() {
        Canvas canvas = new Canvas(WALL_SEP_TOP_SIDE_WIDTH, WALL_TOP_HEIGHT);

        canvas.draw(0, 0, ']');

        return canvas;
    }

    private Canvas drawDoorRoof() {
        Canvas canvas = new Canvas(DOOR_ROOF_WIDTH, DOOR_ROOF_HEIGHT);

        canvas.draw(0, 0, new String[]{"||||||"});

        return canvas;
    }

    private Canvas drawDoor() {
        Canvas canvas = new Canvas(DOOR_WIDTH, DOOR_HEIGHT);

        canvas.draw(0, 0, new String[]{"|[]|", "| '|"});

        return canvas;
    }

    private Canvas drawWindow() {
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_CLOSED_WIDTH);

        canvas.draw(0, 0, new String[]{"##[][]##", "##[][]##"});

        return canvas;
    }

    private Canvas drawWindowClosed() {
        Canvas canvas = new Canvas(WINDOW_CLOSED_WIDTH, WINDOW_HEIGHT);

        canvas.draw(0, 0, new String[]{"####", "####"});

        return canvas;
    }
}
