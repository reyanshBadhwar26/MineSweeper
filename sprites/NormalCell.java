public class NormalCell extends Cell {
	
	public NormalCell(double centerX, double centerY, double height, double width) {
		super(centerX, centerY, height, width);
	}

	public NormalCell(double centerX, double centerY) {

		super(centerX, centerY);

	}
	
	public void reveal(Universe universe) {
		if (this.getIsRevealed()) {
			return;
		} 
		
		if (this.getNumberOfAdjacentMines(universe)== 0) {
			expand(this, universe);
		} else {
			revealNumber(universe);
		}
		
	}

	public void expand(Cell currentCell, Universe universe) {
		
		if (currentCell instanceof MineCell || currentCell.getIsRevealed()) {
			return;
		} else if (currentCell.getNumberOfAdjacentMines(universe) > 0) {
			((NormalCell) currentCell).revealNumber(universe);
		} else if (currentCell.getNumberOfAdjacentMines(universe) ==  0) {
			((NormalCell) currentCell).revealEmptyCell();
			
			for (DisplayableSprite sprite : currentCell.getAdjacentCells(universe)) {
				Cell adjacentCell = (Cell) sprite;
				expand(adjacentCell, universe); 
			}
		}
	}
	
	public void revealEmptyCell() {
		revealEmptyTile = true;
		this.setReveal(true);
	}
	
	public void revealNumber(Universe universe) {
		revealNumber = this.getNumberOfAdjacentMines(universe);
		setReveal(true);
	}
}
