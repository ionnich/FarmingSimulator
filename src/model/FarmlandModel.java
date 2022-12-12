package model;

import model.tiles.*;

import java.io.*;
import java.util.Stack;

/**
 * FarmlandModel is a class that represents the farmland of the game. It contains all the tiles of the farmland.
 */
public class FarmlandModel {
    private final int height;
    private final int width;
    private Tile[][] tiles;

    /**
     * Instantiates a new Farmland model given the height and width of the farmland.
     *
     * @param width  the width of the farmland or the number of columns
     * @param height the height of the farmland or the number of rows
     */
    public FarmlandModel(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];

        File tileFile = new File("src/tilefile.txt");
        FileReader fileReader;
        try {
            fileReader = new FileReader(tileFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String tileStr;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            // read in the tile file
            try {
                tileStr = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stack<Character> stack = new Stack<>();
        // for character in the tile file
        for (char c: tileStr.toCharArray()) {
            stack.push(c);
        }


        char cFlag;
        // instantiate all tiles
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(stack.empty())
                    cFlag = ' ';
                else
                    cFlag = stack.pop();

                if(cFlag == '1')
                    tiles[i][j] = new RockyTile(i, j);
                else
                    tiles[i][j] = new EmptyTile(i, j);
            }
        }
    }

    /**
     * Gets height.
     *
     * @return the height of the farmland
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets width.
     *
     * @return the width of the farmland
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets tile.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return the tile found in the farmland at the given x and y coordinates
     */
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }


    /**
     * Mutates or updates a specific tile in the farmland given the new tile and the old tile's coordinates.
     *
     * @param newTile the new tile
     * @param x        the x-coordinate of the tile to be mutated
     * @param y        the y-coordinate of the tile to be mutated
     */
    public void mutateTile(Tile newTile, int x, int y) {
        tiles[x][y] = newTile;
    }

    /**
     * Advances the day in the farm by updating all the tiles in the farmland.
     */
    public void advanceDay() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tiles[i][j] instanceof CropTile){
                    ((CropTile) tiles[i][j]).growCrop();
                    if(((CropTile) tiles[i][j]).getCrop().getWithered()){
                        tiles[i][j] = new WitherTile(i, j);
                    }
                }
                if(tiles[i][j] instanceof RootTile){
                    ((RootTile) tiles[i][j]).growRoot();
                    if(((RootTile) tiles[i][j]).isDissolved()){
                        tiles[i][j] = new EmptyTile(i, j);
                    }
                }
            }
        }

    }

    /**
     * Replaces a specific tile in the farmland.
     *
     * @param tile the tile to be replaced
     */
    public void replaceTile(Tile tile) {
        tiles[tile.getX()][tile.getY()] = tile;
    }

    /**
     * Gets all the tiles in the farmland.
     *
     * @return the 2d array that holds all the tiles in the farmland.
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Gets the tiles adjacent to a given tile.
     *
     * @param currentTile the current tile
     * @return the tile array that holds all adjacent tiles
     */
    public Tile[] getAdjacentTiles(Tile currentTile) {
        Tile[] adjacentTiles = new Tile[4];
        int x = currentTile.getX();
        int y = currentTile.getY();
        if (x > 0) {
            adjacentTiles[0] = tiles[x - 1][y];
        }
        if (x < width - 1) {
            adjacentTiles[1] = tiles[x + 1][y];
        }
        if (y > 0) {
            adjacentTiles[2] = tiles[x][y - 1];
        }
        if (y < height - 1) {
            adjacentTiles[3] = tiles[x][y + 1];
        }
        return adjacentTiles;
    }

    /**
     * Gets the tiles diagonal to a given tile.
     *
     * @param currentTile the current tile
     * @return the tile array that holds all diagonal tiles
     */
    public Tile[] getDiagonalTiles(Tile currentTile) {
        Tile[] diagonalTiles = new Tile[4];
        int x = currentTile.getX();
        int y = currentTile.getY();
        if (x > 0 && y > 0) {
            diagonalTiles[0] = tiles[x - 1][y - 1];
        }
        if (x < width - 1 && y > 0) {
            diagonalTiles[1] = tiles[x + 1][y - 1];
        }
        if (x > 0 && y < width - 1) {
            diagonalTiles[2] = tiles[x - 1][y + 1];
        }
        if (x < width - 1 && y < height - 1) {
            diagonalTiles[3] = tiles[x + 1][y + 1];
            System.out.println(diagonalTiles[3]);
        }
        return diagonalTiles;
    }
}
