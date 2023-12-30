

public class MineCell extends Cell {

	public MineCell(double centerX, double centerY, double height, double width) {

		super(centerX, centerY, height, width);
	}

	public MineCell(double centerX, double centerY) {

		super(centerX, centerY);
	}

	public void reveal(Universe universe) {
		mineReveal = true;
	}

	public void explode() {

	}
}