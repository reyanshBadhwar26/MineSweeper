import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cell implements DisplayableSprite {

	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	

	private final double VELOCITY = 300;

	public Cell(double centerX, double centerY, double height, double width) {
		this(centerX, centerY);
		
		this.height = height;
		this.width = width;
	}

	public Cell(double centerX, double centerY, String imageName) {

		this.centerX = centerX;
		this.centerY = centerY;
		
		if (image == null) {
			try {
				image = ImageIO.read(new File(String.format("res/%s", imageName)));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}	
		
	}
	
	public Cell(double centerX, double centerY) {

		this.centerX = centerX;
		this.centerY = centerY;
		
	}

	public Image getImage() {
		return image;
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
	
	public void flag(Universe universe) {
		FlaggedTile sprite = new FlaggedTile(this.centerX, this.centerY);
		universe.addSprite(sprite);
		this.setDispose(true);
	}
	

	public void reveal() {
		setDispose(true);
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		double velocityX = 0;
		double velocityY = 0;
		
		if (MouseInput.leftButtonDown == true
				&& CollisionDetection.overlaps(this.getMinX(), this.getMinY(), this.getMaxX(), this.getMaxY(),
						MouseInput.logicalX, MouseInput.logicalY, MouseInput.logicalX, MouseInput.logicalY)) {
			reveal();
		} 
		
		if (MouseInput.rightButtonDown == true
				&& CollisionDetection.overlaps(this.getMinX(), this.getMinY(), this.getMaxX(), this.getMaxY(),
						MouseInput.logicalX, MouseInput.logicalY, MouseInput.logicalX, MouseInput.logicalY)) {
			flag(universe);
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
