

public class MineCell extends Cell {
	


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
		
		universe.setLost(true);
		
	}

}