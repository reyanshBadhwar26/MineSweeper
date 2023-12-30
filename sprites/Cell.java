import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cell implements DisplayableSprite {

	private static Image normalImage;
	private static Image flaggedImage;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	private boolean flagIt = false;
	private boolean isAlreadyFlagged = false;

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
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
		
		if (flaggedImage == null) {
			try {
				flaggedImage = ImageIO.read(new File("res/flagTile.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
	}

	public Image getImage() {
		
		if (flagIt) {
			return flaggedImage;
		} 
		return normalImage;
	}
	
	//DISPLAYABLE
	
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

	public void reveal() {
		setDispose(true);
	}
	
	public boolean isMouseOverCell() {
		return (CollisionDetection.overlaps(this.getMinX(), this.getMinY(), this.getMaxX(), this.getMaxY(),
				MouseInput.logicalX, MouseInput.logicalY, MouseInput.logicalX, MouseInput.logicalY));
	}
	
	public boolean isFlagged() {
		return isAlreadyFlagged;
	}
	
	public void flag() {
		if (!isAlreadyFlagged) {
			flagIt = true;
			isAlreadyFlagged = true;
		} else {
			isAlreadyFlagged = false;
			flagIt = false;
		}
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		double velocityX = 0;
		double velocityY = 0;
		
		if (MouseInput.leftButtonDown && isMouseOverCell()) {
			reveal();
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
