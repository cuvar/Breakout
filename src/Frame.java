import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener, KeyListener{
	

	
	//Constants
	static final int width = 1000;
	static final int height = 700;
	static final int blockAmountX = 10;
	static final int blockAmountY = 5;
	
	static int score = 0;
	static int highscore = 0;
	static int dx = 0;
	static int dy = 0;
	static boolean running = false;;
	static boolean right = false;
	static boolean left = false;
	
	//Application components
	static Frame frm;
	static JLabel scoreLabel = new JLabel();
	static Panel panel = new Panel();
	Timer timer;
	
	//Game elements
	static Ball ball;
	static Paddle paddle;
	static Block block[][] = new Block[blockAmountX][blockAmountY];
	
	
	
	//Constructor
	//
	public Frame() {
		setTitle("Atari - Breakout");
		setSize(width, height);
		setResizable(false);
		setFocusable(true);
		setLocationRelativeTo(null);
		
		addKeyListener(this);
		
		//application components
		timer = new Timer(20, this);
		
		//game elements
		ball = new Ball();		//ball
		paddle = new Paddle();	//paddle
		
		for(int i = 0; i < blockAmountX; i++) {			//blocks
			for(int j = 0; j < blockAmountY; j++) {
				int x = 10 + i * (Block.w + 5) + 2;
				int y = 10 + j * (Block.h + 5) + 30;
				
				//set color of blocks
				Color c;
				if(j == 0) {
					c = new Color(200, 0, 200);
				} else if (j == 1) {
					c = new Color(200, 0, 100);
				} else if (j == 2) {
					c = new Color(200, 0, 0);
				} else if (j == 3) {
					c = new Color(200, 100, 0);
				} else {
					c = new Color(200, 200, 0);
				}
				
				block[i][j] = new Block(x, y, c);
			}
		}
		
		add(panel);
		
		setVisible(true);
		
	}
	
	
	
	//Main
	//
	public static void main(String[] args) {
		frm = new Frame();
	}
	
	
	
	//stops the game and resets everything
	//
	public void stopGame() {
		running = false;
		timer.stop();
		
		//update highscore
		if(score > highscore) {
			highscore = score;
		}
		scoreLabel.setText("Score: " + score + " Highscore: " + highscore);
		score = 0;
		
		//reset game elements 
		ball.reset();
		paddle.reset();
		Block.reset();
	}
	
	
	
	//check if ball / paddle hits aything
	//
	public void checkHitting() {
		//Ball
		//check if walls were hit
		if(ball.x <= 0) {								//left wall
			dx *= -1;
		} else if(ball.x + ball.w >= width) {			//right wall
			dx *= -1;
		}
		
		if(ball.y <= 0) {								//lower wall
			dy *= -1;
		} else if(ball.y + ball.w >= paddle.y + 10) {	//upper wall
			stopGame();
		}
		
		
		//Paddle
		//check if walls were hit
		if(paddle.x <= 0) {							//left wall
			paddle.x = 0;
		} else if(paddle.x + paddle.w >= width) {	//right wall
			paddle.x = width - paddle.w;
		}
		
		
		//Ball and paddle
		//check if ball hits paddle
		if((ball.x >= paddle.x || ball.x + ball.w >= paddle.x ) && (ball.x + ball.w <= paddle.x + paddle.w || ball.x <= paddle.x + paddle.w)) {		//x coord
			if(ball.y + ball.w >= paddle.y && ball.y + ball.w <= paddle.y + paddle.h) {		//y coord
			dy *= -1;
			}	
		}
		
		
		//Ball and block
		//check if ball hits any block
		for(int i = 0; i < blockAmountX; i++) {
			for(int j = 0; j < blockAmountY; j++) {
				if(!block[i][j].hit) {
					
					//ball unter block
					if(ball.y > block[i][j].y && ball.y <= block[i][j].y + block[i][j].h) {	
						
						//ball rechts genau Kante (x coord)
						if(ball.x <= block[i][j].x + block[i][j].w && ball.x + ball.w >= block[i][j].x + block[i][j].w) {
					    	dy *= -1;
							block[i][j].hit = true;
							if(i + 1 <= 9) {
								block[i + 1][j].hit = true;
							}
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);						
						} 
						
						//ball links genau Kante (x coord)
						else if(ball.x <= block[i][j].x && ball.x + ball.w >= block[i][j].x) {
							dy *= -1;
							block[i][j].hit = true;
							block[i - 1][j].hit = true;
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);						
						}
						
						//ball trifft unten gerade auf block
						else if((ball.x >= block[i][j].x || ball.x + ball.w >= block[i][j].x) && (ball.x + ball.w  <= block[i][j].x + block[i][j].w || ball.x <= block[i][j].x + block[i][j].w)) {
							dy *= -1;
							block[i][j].hit = true;
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);
						}
					}
					
					//ball neben block
					else if((ball.y >= block[i][j].y || ball.y + ball.w >= block[i][j].y) && (ball.y + ball.w <= block[i][j].y + block[i][j].h || ball.y <= block[i][j].y + block[i][j].h)) {
						
						//ball trifft links auf block
						if(ball.x + ball.w >= block[i][j].x && ball.x + ball.w < block[i][j].x + block[i][j].w){
							dx *= -1;
							block[i][j].hit = true;
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);
							
						}
						
						//ball trifft rechts auf block
						else if(ball.x > block[i][j].x && ball.x <= block[i][j].x + block[i][j].w) {
							dx *= -1;
							block[i][j].hit = true;
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);
							
						}
					}
					
					//ball unten genau Kante (y coord)
					else if(ball.y <= block[i][j].y + block[i][j].h && ball.y + ball.w >= block[i][j].y + block[i][j].h) {
						
						//ball rechts genau Kante (x coord)
						if(ball.x <= block[i][j].x + block[i][j].w && ball.x + ball.w >= block[i][j].x + block[i][j].w) {
					    	dy *= -1;
							block[i][j].hit = true;
							if(i + 1 <= 9) {
								block[i + 1][j].hit = true;
							}
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);						
						} 
						
						//ball links genau Kante (x coord)
						else if(ball.x <= block[i][j].x && ball.x + ball.w >= block[i][j].x) {
					    	dy *= -1;
							block[i][j].hit = true;
							block[i - 1][j].hit = true;
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);						
						}
					}
					
					//ball oben genau Kante (y coord)
					else if(ball.y <= block[i][j].y && ball.y + ball.w >= block[i][j].y) {
						
						//ball rechts genau Kante (x coord)
						if(ball.x <= block[i][j].x + block[i][j].w && ball.x + ball.w >= block[i][j].x + block[i][j].w) {
					    	dy *= -1;
							block[i][j].hit = true;
							if(i + 1 <= 9) {
								block[i + 1][j].hit = true;
							}
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);						
						} 
						
						//ball links genau Kante (x coord)
						else if(ball.x <= block[i][j].x && ball.x + ball.w >= block[i][j].x) {
					    	dy *= -1;
							block[i][j].hit = true;
							block[i - 1][j].hit = true;
							score += 10;
							scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);						
						 }
					}
				}
			}
		}
	}
	
	
	
	
	
	//ActionListener
	//
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if(running) {
			
			if(right) {
				paddle.x += 9;
			}
			if(left) {
				paddle.x -= 9;
			}
			//reinit ball's coords
			ball.x += dx; 
			ball.y += dy;
			
			checkHitting();
			
			int m = 0;
			//check if all blocks are destroyed
			for(int i = 0; i < blockAmountX; i++) {
				for(int j = 0; j < blockAmountY; j++) {
					if(block[i][j].hit) {
						m++;
					}
				}
			}
			if(m == blockAmountX * blockAmountY) {
				stopGame();
			}
			
		}
		
	}
	
	
	
	//KeyListener
	//
	@Override
	public void keyPressed(KeyEvent e) {
		//close application
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		//move paddle left
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(running) {
				right = false;
				left = true;
			}
		}
		//move paddle right
		if(e.getKeyCode() == KeyEvent.VK_D) {
			if(running) {
				right = true;
				left = false;
			}
		}
		//move paddle right
		if(e.getKeyCode() == KeyEvent.VK_SPACE && !running) {
			running = true;
			timer.start();
			ball.startMoving();
			scoreLabel.setText("Score: " + score + "  Highscore: " + highscore);
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
