import java.awt.Color;

public class Block {
	
	int x;
	int y;
	final static int w = (Frame.width - (9 * 5) - (2 * 10)) / Frame.blockAmountX;
	final static int h = ((Frame.height / 3) - (2 * 5) - 10) / Frame.blockAmountY;
	final Color color;
	boolean hit;
	
	//Constructor
	//
	public Block(int xx, int yy, Color c) {
		x = xx;
		y = yy;
		this.color = c;
		this.hit = false;
	}
	
	
	
	//reset values of block
	//
	public static void reset() {
		//paint Blocks
		for(int i = 0; i < Frame.blockAmountX; i++) {			//blocks
			for(int j = 0; j < Frame.blockAmountY; j++) {
				Frame.block[i][j].x = 10 + i * (w + 5) + 2;
				Frame.block[i][j].y = 10 + j * (h + 5) + 30;
				Frame.block[i][j].hit = false;
			}
		}
	}
}
