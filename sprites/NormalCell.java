import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NormalCell extends Cell {

	private boolean isFlagged;

	public NormalCell(double centerX, double centerY, double height, double width) {
		super(centerX, centerY, height, width);
	}

	
	public NormalCell(double centerX, double centerY) {
		
		super(centerX, centerY, "normalTile.png" );
			
	}
	
	public void expandTiles() {
		
	}
	
	public boolean isFlagged() {
		return isFlagged;
	}

}
