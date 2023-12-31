

public class MineCell extends Cell {
	
	private boolean hasBeenChecked = false;

	public MineCell(double centerX, double centerY, double height, double width) {

		super(centerX, centerY, height, width);
	}

	public MineCell(double centerX, double centerY) {

		super(centerX, centerY);
	}

	public void reveal(Universe universe) {
		explode(universe);	
	}

	public void explode(Universe universe) {

		for (DisplayableSprite sprite : universe.getSprites()){
			if (sprite instanceof MineCell) {
				((MineCell) sprite).mineReveal = true;
			}
		}
		
	}
	
	public boolean getHasBeenChecked() {
		return hasBeenChecked;
	}

	public void setHasBeenChecked(boolean hasBeenChecked) {
		this.hasBeenChecked = hasBeenChecked;
	}
}