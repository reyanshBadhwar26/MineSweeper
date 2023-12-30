import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class HiddenSeedBeginner implements Background {

	protected static int TILE_WIDTH = 50;
	protected static int TILE_HEIGHT = 50;

	private Image mine;
	private int maxCols = 0;
	private int maxRows = 0;
	//MineSweeper Logic Constants
    private final int N_MINES = 12;
    private final int N_ROWS = 11;
    private final int N_COLS = 9;
    private final int MINE_CONSTANT = 13;
    
	private int map[][] = new int[][] { 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		
	};
	

	public HiddenSeedBeginner() {
		try {
			this.mine = ImageIO.read(new File("res/flagTile.png"));
		} catch (IOException e) {
			// System.out.println(e.toString());
		}
		//setting up matrix of mines
		int var = 0;
		while (var < N_MINES) {
			Random random = new Random();
			int i = random.nextInt(N_ROWS);
			int j = random.nextInt(N_COLS);
			
			map[j][i] = 13;

			var ++;
		}
		
		maxRows = map.length - 1;
		maxCols = map[0].length - 1;

	}

	@Override
	public Tile getTile(int col, int row) {

		Image image = null;

		if (row < 0 || row > maxRows || col < 0 || col > maxCols || map[row][col] == 1) {
			image = null;
		} else if (map[row][col] == 13) {
			image = mine;
		}

		int x = (int) ((col * TILE_WIDTH));
		int y = (int) ((row * TILE_HEIGHT));

		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);

		return newTile;
	}

	@Override
	public int getCol(double x) {

		int col = 0;
		if (TILE_WIDTH != 0) {
			col = (int) (x / TILE_WIDTH);
			if (x < 0) {
				return col - 1;
			} else {
				return col;
			}
		} else {
			return 0;
		}

	}

	@Override
	public int getRow(double y) {

		int row = 0;

		if (TILE_HEIGHT != 0) {
			row = (int) (y / TILE_HEIGHT);
			if (y < 0) {
				return row - 1;
			} else {
				return row;
			}
		} else {
			return 0;
		}
	}

	@Override
	public double getShiftX() {
		return -250;
	}

	@Override
	public double getShiftY() {
		return -150;
	}

	@Override
	public void setShiftX(double shiftX) {
	}

	@Override
	public void setShiftY(double shiftY) {
	}
}
