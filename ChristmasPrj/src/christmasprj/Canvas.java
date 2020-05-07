package christmasprj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin Motejlek
 */
public class Canvas {

    private final int height;
    private final int width;
    private final char[][] contents;

    public Canvas(int width, int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative.");
        }
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative.");
        }
        this.height = height;
        this.width = width;
        this.contents = new char[height][width];
    }

    public Canvas(Canvas canvas) {
        this.height = canvas.getHeight();
        this.width = canvas.getWidth();
        this.contents = canvas.getContents();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getContents() {
        char[][] copy = new char[height][];
        
        for (int i = 0; i < copy.length; i++) {
            copy[i] = contents[i].clone();
        }
        
        return copy;
    }

    @Override
    public String toString() {
        String canvasString = "";

        for (int row = 0; row < height; row++) {
            canvasString += new String(contents[row]);

            if (row < height - 1) {
                canvasString += String.format("%n");
            }
        }

        canvasString = canvasString.replace('\0', ' ');

        return canvasString;
    }

    public void draw(int startCol, int startRow, char character) {
        if (startRow >= 0 && startRow < height && startCol >= 0 && startCol < width) {
            if (character != '\0') {
                contents[startRow][startCol] = character;
            }
        }
    }

    public void draw(int startCol, int startRow, char[] image) {
        for (int col = 0; col < image.length; col++) {
            draw(startCol + col, startRow, image[col]);
        }
    }

    public void draw(int startCol, int startRow, char[][] image) {
        for (int row = 0; row < image.length; row++) {
            draw(startCol, startRow + row, image[row]);
        }
    }

    public void draw(int startCol, int startRow, String image) {
        draw(startCol, startRow, image.toCharArray());
    }

    public void draw(int startCol, int startRow, String[] image) {
        draw(startCol, startRow, Canvas.strArrayToChar2DArray(image));
    }

    public void draw(int startCol, int startRow, Canvas image) {
        draw(startCol, startRow, image.getContents());
    }

    public void flipHorizontal() {
        char tmp;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width / 2; j++) {
                tmp = contents[i][j];
                contents[i][j] = Canvas.flipCharHorizontal(contents[i][width - 1 - j]);
                contents[i][width - 1 - j] = Canvas.flipCharHorizontal(tmp);
            }

            if (width % 2 == 1) {
                contents[i][width / 2] = Canvas.flipCharHorizontal(contents[i][width / 2]);
            }
        }
    }

    public void flipVertical() {
        char tmp;

        for (int i = 0; i < height / 2; i++) {
            for (int j = 0; j < width; j++) {
                tmp = contents[i][j];
                contents[i][j] = Canvas.flipCharVertical(contents[height - 1 - i][j]);
                contents[height - 1 - i][j] = Canvas.flipCharVertical(tmp);
            }
        }

        if (height % 2 == 1) {
            for (int i = 0; i < width; i++) {
                contents[height / 2][i] = Canvas.flipCharHorizontal(contents[height / 2][i]);
            }
        }
    }
    
    private static char[][] strArrayToChar2DArray(String[] stringArray) {
        char[][] char2DArray = new char[stringArray.length][];

        for (int row = 0; row < stringArray.length; row++) {
            char2DArray[row] = stringArray[row].toCharArray();
        }

        return char2DArray;
    }

    private static char flipCharHorizontal(char character) {
        switch (character) {
            case '(':
                return ')';
            case ')':
                return '(';
            case '[':
                return ']';
            case ']':
                return '[';
            case '{':
                return '}';
            case '}':
                return '{';
            case '<':
                return '>';
            case '>':
                return '<';
            case '/':
                return '\\';
            case '\\':
                return '/';
            default:
                return character;
        }
    }

    private static char flipCharVertical(char character) {
        switch (character) {
            case '/':
                return '\\';
            case '\\':
                return '/';
            default:
                return character;
        }
    }
}
