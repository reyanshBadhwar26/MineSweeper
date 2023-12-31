import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Cell implements DisplayableSprite {

	private static Image normalImage;
	private static Image flaggedImage;
	private static Image revealedMine;
	private static Image tileZero;
	private static Image tileOne;
	private static Image tileTwo;
	private static Image tileThree;
	private static Image tileFour;
	private static Image tileFive;
	private static Image tileSix;
	private static Image tileSeven;
	private static Image tileEight;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	protected boolean mineReveal = false;
	private boolean flagIt = false;
	private boolean isAlreadyFlagged = false;
	protected int revealNumber = 0;
	protected boolean revealEmptyTile = false;
	private boolean isAlreadyRevealed = false;

	public Cell(double centerX, double centerY, double height, double width) {
		this(centerX, centerY);

		this.height = height;
		this.width = width;
	}

	public Cell(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;

		if (normalImage == null) {
			try {
				normalImage = ImageIO.read(new File("res/normalTile.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (flaggedImage == null) {
			try {
				flaggedImage = ImageIO.read(new File("res/flagTile.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (revealedMine == null) {
			try {
				revealedMine = ImageIO.read(new File("res/revealedMine.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (tileZero == null) {
			try {
				tileZero = ImageIO.read(new File("res/emptyCell.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (tileOne == null) {
			try {
				tileOne = ImageIO.read(new File("res/one.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (tileTwo == null) {
			try {
				tileTwo = ImageIO.read(new File("res/two.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (tileThree == null) {
			try {
				tileThree = ImageIO.read(new File("res/three.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (tileFour == null) {
			try {
				tileFour = ImageIO.read(new File("res/four.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

		if (tileFive == null) {
			try {
				tileFive = ImageIO.read(new File("res/five.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		if (tileSix == null) {
			try {
				tileSix = ImageIO.read(new File("res/six.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		if (tileSeven == null) {
			try {
				tileSeven = ImageIO.read(new File("res/seven.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		if (tileEight == null) {
			try {
				tileEight = ImageIO.read(new File("res/eight.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}

	public Image getImage() {

		if (flagIt) {
			isAlreadyFlagged = true;
			return flaggedImage;
		}

		if (mineReveal) {
			return revealedMine;
		}

		if (revealEmptyTile) {
			return tileZero;
		}
		if (revealNumber == 1) {
			return tileOne;
		} else if (revealNumber == 2) {
			return tileTwo;
		} else if (revealNumber == 3) {
			return tileThree;
		} else if (revealNumber == 4) {
			return tileFour;
		} else if (revealNumber == 5) {
			return tileFive;
		} else if (revealNumber == 6) {
			return tileSix;
		} else if (revealNumber == 7) {
			return tileSeven;
		} else if (revealNumber == 8) {
			return tileEight;
		}

		return normalImage;
	}

	// DISPLAYABLE

	public boolean getVisible() {
		return true;
	}

	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};

	public boolean getDispose() {
		return dispose;
	}

	public void reveal(Universe universe) {
	}

	public boolean isMouseOverCell() {
		return (CollisionDetection.overlaps(this.getMinX(), this.getMinY(), this.getMaxX(), this.getMaxY(),
				MouseInput.logicalX, MouseInput.logicalY, MouseInput.logicalX, MouseInput.logicalY));
	}

	public boolean isFlagged() {
		return isAlreadyFlagged;
	}

	public void flag() {
		flagIt = true;
	}
	
	public boolean getIsRevealed() {
		return isAlreadyRevealed;
	}
	
	public void setReveal(boolean reveal) {
		isAlreadyRevealed = reveal;
	}

	// Algorithm to Check For Mines Around One Cell
	public int getNumberOfAdjacentMines(Universe universe) {
		int count = 0;

		for (int i = 0; i < universe.getSprites().size(); i++) {
			DisplayableSprite sprite = universe.getSprites().get(i);

			if (sprite instanceof MineCell) {
				double distance = Math.sqrt(Math.pow(sprite.getCenterX() - this.getCenterX(), 2)
						+ Math.pow(sprite.getCenterY() - this.getCenterY(), 2));

				if (distance > 0 && distance <= sprite.getHeight() * 1.5) {
					count++;
				}
			}
		}

		return count;
	}

	// Algorithm to Check For All AdjacentCells Around One Cell
	public ArrayList<Cell> getAdjacentCells(Universe universe) {
		ArrayList<Cell> adjacentCells = new ArrayList<>();

		for (int i = 0; i < universe.getSprites().size(); i++) {
			DisplayableSprite sprite = universe.getSprites().get(i);

			double distance = Math.sqrt(Math.pow(sprite.getCenterX() - this.getCenterX(), 2)
					+ Math.pow(sprite.getCenterY() - this.getCenterY(), 2));

			// Check if the distance is within a reasonable range to consider it adjacent
			if (distance > 0 && distance <= Math.max(sprite.getWidth(), sprite.getHeight()) * 1.5) {
				adjacentCells.add((Cell) sprite);
			}
		}

		return adjacentCells;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {

		double velocityX = 0;
		double velocityY = 0;

		if (MouseInput.leftButtonDown && isMouseOverCell()) {
			reveal(universe);
		}

		if (MouseInput.rightButtonDown && isMouseOverCell()) {
			flag();
		}

		double deltaX = actual_delta_time * 0.001 * velocityX;
		this.centerX += deltaX;

		double deltaY = actual_delta_time * 0.001 * velocityY;
		this.centerY += deltaY;

	}

	@Override
	public void setDispose(boolean dispose) {
		this.dispose = true;
	}

}
