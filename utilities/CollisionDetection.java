import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CollisionDetection {

	private OneDimensionBounce bounce = new OneDimensionBounce();	
	private double bounceFactorX = 1;
	private double bounceFactorY = 1;

	public double getBounceFactorX() {
		return bounceFactorX;
	}

	public void setBounceFactorX(double bounceFactorX) {
		this.bounceFactorX = bounceFactorX;
	}

	public double getBounceFactorY() {
		return bounceFactorY;
	}

	public void setBounceFactorY(double bounceFactorY) {
		this.bounceFactorY = bounceFactorY;
	}

	public static boolean overlaps(DisplayableSprite spriteA, DisplayableSprite spriteB) {
		return overlaps(
				spriteA.getMinX(), 
				spriteA.getMinY(), 
				spriteA.getMaxX(), 
				spriteA.getMaxY(), 
				spriteB.getMinX(), 
				spriteB.getMinY(), 
				spriteB.getMaxX(), 
				spriteB.getMaxY());		
	}

	public static boolean overlaps(DisplayableSprite spriteA, DisplayableSprite spriteB, double deltaAX, double deltaAY) {
		
		return overlaps(
				spriteA.getMinX() + deltaAX, 
				spriteA.getMinY() + deltaAY, 
				spriteA.getMaxX() + deltaAX, 
				spriteA.getMaxY() + deltaAY, 
				spriteB.getMinX(), 
				spriteB.getMinY(), 
				spriteB.getMaxX(), 
				spriteB.getMaxY());		
	}

	public static boolean overlaps(double leftA, double topA, double rightA, double bottomA, double leftB, double topB, double rightB, double bottomB) {
		boolean toLeft = (rightA < leftB); //case 1: right edge of A is to the left of left edge of B, so A is fully to left of A
		boolean toRight = (leftA > rightB); //case 2: left edge of A is to the right of right edge of B, so A is fully to right of B
		boolean overlapX = !(toLeft || toRight);

		boolean above = (bottomA < topB); //case 1: bottom edge of A is above top edge of B, so A is fully above B
		boolean below = (topA > bottomB); //case 2: top edge of A is below bottom edge of B, so A is fully below B
		boolean overlapY = !(above || below);

		return (overlapX && overlapY);
	}
	
	public static boolean inside(DisplayableSprite spriteA, DisplayableSprite spriteB) {
		return inside(
				spriteA.getMinX(), 
				spriteA.getMinY(), 
				spriteA.getMaxX(), 
				spriteA.getMaxY(), 
				spriteB.getMinX(), 
				spriteB.getMinY(), 
				spriteB.getMaxX(), 
				spriteB.getMaxY());		
	}

	public static boolean inside(DisplayableSprite spriteA, DisplayableSprite spriteB, double deltaAX, double deltaAY) {
		
		return inside(
				spriteA.getMinX() + deltaAX, 
				spriteA.getMinY() + deltaAY, 
				spriteA.getMaxX() + deltaAX, 
				spriteA.getMaxY() + deltaAY, 
				spriteB.getMinX(), 
				spriteB.getMinY(), 
				spriteB.getMaxX(), 
				spriteB.getMaxY());		
	}
	
	public static boolean inside(double leftA, double topA, double rightA, double bottomA, double leftB, double topB, double rightB, double bottomB) {
		boolean insideX = ((leftB <= leftA) && (rightA <= rightB));
		boolean insideY = ((topB <= topA) && (bottomA <= bottomB));
		if (insideX && insideY) {
			return true;
		}
		else {
			return false;	    	
		}
	}	
	
	public static boolean covers (DisplayableSprite spriteA, DisplayableSprite spriteB) {
		//A cover B <-->  B inside A
		return inside(spriteB, spriteA);
	}

	public static boolean covers (DisplayableSprite spriteA, DisplayableSprite spriteB, double deltaAX, double deltaAY) {
		//A cover B <-->  B inside A
		//offset is equivalent
		return inside(spriteB, spriteA, deltaAX, deltaAY);
	}
	
	public static boolean covers (double leftA, double topA, double rightA, double bottomA, double leftB, double topB, double rightB, double bottomB) {
		//A cover B <-->  B inside A
		return inside(leftB, topB, rightB, bottomB, leftA, topA, rightA, bottomA);
	}
	
	public static boolean pixelBasedOverlaps(DisplayableSprite spriteA, DisplayableSprite spriteB) {

		if (overlaps(spriteA.getMinX(), spriteA.getMinY(), spriteA.getMaxX(), spriteA.getMaxY(), 
				spriteB.getMinX(), spriteB.getMinY(), spriteB.getMaxX(), spriteB.getMaxY()) == false) {
			return false;
		}
		
		BufferedImage bufferedA = (BufferedImage) spriteA.getImage();
		BufferedImage bufferedB = (BufferedImage) spriteB.getImage();
		
		int offsetX = (int) (spriteB.getMinX() - spriteA.getMinX());
		int offsetY = (int) (spriteB.getMinY() - spriteA.getMinY());
		
		int left = Math.max(0, (int) (offsetX));
		int top =  Math.max(0, (int) (offsetY));
		int right = (int) (spriteA.getWidth() - Math.max(0, spriteA.getMaxX() - spriteB.getMaxX()));
		int bottom = (int) (spriteA.getHeight() - Math.max(0, spriteA.getMaxY() - spriteB.getMaxY()));
		
		double scaleXA = bufferedA.getHeight() / (float)spriteA.getWidth();
		double scaleYA = bufferedA.getHeight() / (float)spriteA.getHeight();
		double scaleXB = bufferedB.getHeight() /  (float)spriteB.getWidth();
		double scaleYB = bufferedB.getHeight() /  (float)spriteB.getHeight();

		for (int x = left; x < right; x++) {
			for (int y = top; y < bottom; y++) {
				int xA = (int)(x * scaleXA);
				int yA = (int)(y * scaleYA);				
				int xB = (int) ((x - offsetX) * scaleXB);
				int yB = (int) ((y - offsetY) * scaleYB);
				if ((xB >= 0) && (yB >= 0) && (yB < bufferedB.getWidth()) && (yB < bufferedB.getHeight())) {
					int pixelA = bufferedA.getRGB(xA, yA);
					int pixelB = bufferedB.getRGB(xB, yB);
					if ((pixelA>>>24 > 0x00) && (pixelB>>>24 > 0x00)) {
						return true;
					}
				}
			}
		}	
		
		return false;
		
		
	}

	public void calculate2DBounce(TwoDimensionBounce twoDBounce, DisplayableSprite sprite, ArrayList<DisplayableSprite> barriers, double velocityX, double velocityY, long actual_delta_time ) {
		calculate2DBounce(twoDBounce, sprite, barriers, velocityX,  velocityY,  actual_delta_time, null);
	}

	
	public void calculate2DBounce(TwoDimensionBounce twoDBounce, DisplayableSprite sprite, ArrayList<DisplayableSprite> barriers, double velocityX, double velocityY, long actual_delta_time, Class type) {

		if (twoDBounce == null) {
			twoDBounce = new TwoDimensionBounce();
		}

		//calculate new position assuming there are no changes in direction
		double movementX = (velocityX * actual_delta_time * 0.001);
		double movementY = (velocityY * actual_delta_time * 0.001);

		twoDBounce.newVelocityX = velocityX;
		twoDBounce.newVelocityY = velocityY;
		twoDBounce.newX = sprite.getMinX() + movementX;
		twoDBounce.newY = sprite.getMinY() + movementY;
		twoDBounce.didBounce = false;

		for (DisplayableSprite barrier : barriers) {
						
			if ( (type != null) && (barrier.getClass().equals(type) == false)) {
				continue;				
			}
						
			//colliding with top edge of barrier?
			//can only collide if sprite is moving down and currently above the barrier
			if (movementY > 0 && sprite.getMaxY() < barrier.getMaxY()) {
				//?is the sprite to the left || right of the barrier? (can only collide if this is not the case) 
				if (!( (sprite.getMinX() >= barrier.getMaxX()) || (sprite.getMaxX() <= barrier.getMinX()))) {
					calculateOneDBounce(bounce, sprite.getMaxY(), movementY, barrier.getMinY(), barrier.getMaxY(), bounceFactorY);
					if (bounce.didBounce) {
						twoDBounce.newY = bounce.newLocaton - sprite.getHeight();
						//cannot use the returned velocity as it actually (in this case) represents movement for the given delta time
						twoDBounce.newVelocityY = (velocityY * bounceFactorY * -1);
						twoDBounce.didBounce = true;
					}
				}
			}

			//colliding with bottom edge of barrier?
			//can only collide if sprite is moving up and currently below the barrier
			if (movementY < 0 && sprite.getMinY() > barrier.getMinY()) {
				//is the sprite to the left || right of the barrier? (can only collide if this is not the case) 
				if (! ((sprite.getMinX() >= barrier.getMaxX()) || (sprite.getMaxX() <= barrier.getMinX()))) {
					calculateOneDBounce(bounce, sprite.getMinY(), movementY, barrier.getMaxY(), barrier.getMinY(), bounceFactorY);
					if (bounce.didBounce) {
						twoDBounce.newY = bounce.newLocaton;
						twoDBounce.newVelocityY = (velocityY * bounceFactorY * -1);
						twoDBounce.didBounce = true;
					}
				}
			}

			//colliding with left edge of barrier?
			//can only collide if sprite is moving right and currently to left of the barrier
			if (movementX > 0 && sprite.getMaxX() < barrier.getMaxX()) {
				//?is the sprite above || below the barrier? (can only collide if this is not the case) 
				if (!( (sprite.getMinY() >= barrier.getMaxY()) || (sprite.getMaxY() <= barrier.getMinY()))) {
					calculateOneDBounce(bounce, sprite.getMaxX(), movementX, barrier.getMinX(), barrier.getMaxX(), bounceFactorX);
					if (bounce.didBounce) {
						twoDBounce.newX = bounce.newLocaton - sprite.getWidth();
						twoDBounce.newVelocityX = (velocityX * bounceFactorX * -1);
						twoDBounce.didBounce = true;
					}
				}
			}

			//colliding with right edge of barrier?
			//?moving left (can only collide if sprite is moving to left)
			//can only collide if sprite is moving left and currently to right of the barrier
			if (movementX < 0 && sprite.getMinX() > barrier.getMinX()) {
				//?is the sprite above || below the barrier? (can only collide if this is not the case) 
				if (!( (sprite.getMinY() >= barrier.getMaxY()) || (sprite.getMaxY() <= barrier.getMinY()))) {
					calculateOneDBounce(bounce, sprite.getMinX(), movementX, barrier.getMaxX(), barrier.getMinX(), bounceFactorX);
					if (bounce.didBounce) {
						twoDBounce.newX = bounce.newLocaton;
						twoDBounce.newVelocityX = (velocityX * bounceFactorX * -1);
						twoDBounce.didBounce = true;
					}
				}
			}
		}
	}

	/**
	 * @param bounaryFar TODO
	 * @param lineBounce: will contain the results of the bounce calculation; cannot be null
	 */
	public void calculateOneDBounce(OneDimensionBounce oneDBounce, double location, double velocity, double boundaryNear, double boundaryFar, double bounceFactor) {

		if (oneDBounce == null) {
			return;
		}

		double distanceToBoundary = 0;

		//moving to right, to left of near boundary, and will reach near boundary 
		if ( (velocity > 0) && (location <= boundaryNear) && ((location + velocity) >= boundaryNear)) {
			distanceToBoundary = (boundaryNear - location);
			oneDBounce.newLocaton = boundaryNear - ( (velocity - distanceToBoundary) * bounceFactor);
			oneDBounce.newVelocity = (velocity * bounceFactor * -1);
			oneDBounce.didBounce = true;
		}
		//moving to left, to right of near boundary, and will reach near boundary
		else if (velocity < 0 && (location >= boundaryNear) && ((location + velocity) <= boundaryNear)) {
			distanceToBoundary = (location - boundaryNear);
			oneDBounce.newLocaton = boundaryNear - ( (velocity + distanceToBoundary) * bounceFactor);
			oneDBounce.newVelocity = (velocity * bounceFactor * -1);
			oneDBounce.didBounce = true;
		}
		//moving to right, to right of near boundary, and will not reach far boundary
		else if ( (velocity > 0) && (location > boundaryNear) && ((location + velocity) < boundaryFar)) {
//			//special case, where the location is already within the boundaries; expel the object
			double boundaryMid = (boundaryNear + boundaryFar) / 2;
			//to left of midpoint of boundaries
			if (location < boundaryMid) {
				oneDBounce.newVelocity = (velocity * bounceFactor * -1);
				oneDBounce.newLocaton = boundaryNear;
				oneDBounce.didBounce = true;
			}			
		}
//		//moving to left, to left of near boundary, and will not reach far boundary
		else if ( (velocity < 0) && (location < boundaryNear) && ((location + velocity) > boundaryFar)) {
			//special case, where the location is already within the boundaries; expel the object
			double boundaryMid = (boundaryNear + boundaryFar) / 2;
			//to left of midpoint of boundaries
			if (location > boundaryMid) {
				oneDBounce.newVelocity = (velocity * bounceFactor * -1);
				oneDBounce.newLocaton = boundaryNear;
				oneDBounce.didBounce = true;
			}			
		}
		else {
			oneDBounce.newLocaton = location + velocity;
			oneDBounce.newVelocity = velocity;
			oneDBounce.didBounce = false;
		}

	}	
}
