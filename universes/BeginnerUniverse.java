import java.util.ArrayList;
import java.util.Random;

public class BeginnerUniverse implements Universe {

	private boolean complete = false;
	private DisplayableSprite basicTile = null;
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	private Background background;
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();
	public final int TILE_START_X_POINT = -250;
	public final int TILE_STOP_X_POINT = 250;
	public final int TILE_START_Y_POINT = -150;
	public final int TILE_STOP_Y_POINT = 250;
	public final int TILE_WIDTH = 50;
	public final int TILE_HEIGHT = 50;
	private final int N_MINES = 15;
	
	private int minesRemaining;
	
	Random rand = new Random();

	public BeginnerUniverse() {

		background = new BeginnerBackground();
		backgrounds.add(background);
		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new NormalCell(0, 0);
		// sprites.add(player1);

		for (double i = TILE_START_X_POINT; i <= TILE_STOP_X_POINT; i = i + TILE_WIDTH) {
			for (int j = TILE_START_Y_POINT; j <= TILE_STOP_Y_POINT; j = j + TILE_HEIGHT) {
				basicTile = new NormalCell(i, j);
				sprites.add(basicTile);
			}
		}

		for (int i = 0; i < N_MINES; i++) {
			int randomTile = rand.nextInt(sprites.size());
			if (sprites.get(randomTile) instanceof MineCell == false) {
				sprites.set(randomTile,
						new MineCell(sprites.get(randomTile).getCenterX(), sprites.get(randomTile).getCenterY()));
				minesRemaining++;
			}

		}

	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}

	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
			this.player1.setDispose(true);
		}
		
		for (DisplayableSprite sprite : sprites) {
			if (sprite instanceof MineCell) {
				if (((MineCell) sprite).isFlagged() && !((MineCell) sprite).getHasBeenChecked()) {
					minesRemaining --;
					((MineCell) sprite).setHasBeenChecked(true);
				}
			}
		}

		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
		}
		
		levelFinished();
		disposeSprites();
		
	}

	protected void disposeSprites() {

		// collect a list of sprites to dispose
		// this is done in a temporary list to avoid a concurrent modification exception
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			if (sprite.getDispose() == true) {
				disposalList.add(sprite);
			}
		}

		// go through the list of sprites to dispose
		// note that the sprites are being removed from the original list
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			sprites.remove(sprite);
		}

		// clear disposal list if necessary
		if (disposalList.size() > 0) {
			disposalList.clear();
		}
	}

	public String toString() {
		return "MineSweeperBasic";
	}

	@Override
	public void addSprite(DisplayableSprite sprite) {
		sprites.add(sprite);
	}

	public boolean levelFinished() {
		if (minesRemaining == 0) {
			return true;
		} 
		return false;
	}

}
