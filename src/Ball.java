import java.awt.Color;

public class Ball {
	
	int x;
	int y;
	final int w;
	final Color color;
	
	public Ball() {
		this.w = 20;
		this.x = Frame.width / 2;
		this.y = Frame.height / 6 * 5;
		this.color = new Color(0, 0, 0);
	}
	
	
	
	//lets the ball start moving in a random direction
	//
	public void startMoving() {
		int theta = 0;
		double rad = 0;
		
		while(theta == 0 || theta == 90 || theta == 180 || theta == 170 || theta == 10) {
			theta = (((int) (Math.random() * 18)) * 10);
		}
		
		rad = Math.toRadians(theta);
		
		if(theta < 90) {
			Frame.dx = (int) (Math.cos(rad) * 10);
			Frame.dy = (int) (Math.sin(rad) * (-10));
		} else if (theta > 90) {
			Frame.dx = (int) (Math.cos(rad) * (10));
			Frame.dy = (int) (Math.sin(rad) * (-10));
		}
		
	}
	
	
	
	//set a random angle and sets dx & dy
	//
	public void setDirection() {
		int theta = 180 - (((int) (Math.random() * 18)) * 10);
		
		if(theta < 90) {
			Frame.dx = (int) Math.cos(theta) * 10;
			Frame.dy = (int) (Math.sin(theta) * (-10));
		} else if (theta > 90) {
			Frame.dx = (int) (Math.cos(theta) * (-10));
			Frame.dy = (int) (Math.sin(theta) * 10);
		} else if(theta == 90) {
			Frame.dx = 0;
			Frame.dy = 10;
		} 
	}
	
	
	
	//reset values of ball
	//
	public void reset() {
		this.x = Frame.width / 2;
		this.y = Frame.height / 6 * 5;
	}

}
