import java.awt.Color;

public class Paddle {
	
	int x;
	int y;
	final int w;
	final int h;
	final Color color;
	
	//Constructor
	//
	public Paddle() {
		this.w = 200;
		this.h = 10;
		this.x = Frame.ball.x - (w / 2) + (Frame.ball.w / 2);
		this.y = Frame.ball.y + Frame.ball.w;
		this.color = new Color(0, 0, 0);
		
	}
	
	
	
	//reset values of paddle
	//
	public void reset() {
		this.x = Frame.ball.x - (w / 2) + (Frame.ball.w / 2);
		this.y = Frame.ball.y + Frame.ball.w;
	}
}
