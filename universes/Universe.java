import java.util.ArrayList;

public interface Universe {

	//STATIC VARIABLES
	
	//INSTANCE VARIABLES

	public double getScale();

	public double getXCenter();
	public double getYCenter();
	
	public void setXCenter(double xCenter);
	public void setYCenter(double yCenter);
	
	public boolean isComplete();
	public void setComplete(boolean complete);
	
	public DisplayableSprite getPlayer1();

	public ArrayList<DisplayableSprite> getSprites();	
	public ArrayList<Background> getBackgrounds();		
	
	public void addSprite(DisplayableSprite sprite);
	public boolean levelFinished();
	public boolean getLost();
	public void setLost(boolean lost);
	public int getFlagsRemaining();

	public void update(KeyboardInput keyboard, long actual_delta_time);
    
	
}
