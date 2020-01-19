import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	
	
	//Constructor
	//
	public Panel() {
		setSize(Frame.width, Frame.height);
		//setBackground(new Color(120,120,120));
		
		Frame.scoreLabel.setBounds(Frame.width /2 , 10, 100, 20);
		Frame.scoreLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		Frame.scoreLabel.setText("Score: " + Frame.score + "  Highscore: " + Frame.highscore);
		add(Frame.scoreLabel);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		//Gradient background
		GradientPaint gp = new GradientPaint(0, 0, new Color(230, 230, 230), 0, getHeight(), new Color(130, 130, 130));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight()); 
		
		//paint Paddle
		g2d.setColor(Frame.ball.color);
		g2d.fillRect(Frame.paddle.x, Frame.paddle.y, Frame.paddle.w, Frame.paddle.h);
		
		//paint Ball
		g2d.setColor(Frame.ball.color);
		g2d.fillOval(Frame.ball.x, Frame.ball.y, Frame.ball.w, Frame.ball.w);
		
		//paint Blocks
		for(Block[] b1 : Frame.block) {
			for(Block b2 : b1) {
				if(!b2.hit) {
					g2d.setColor(b2.color);
					g2d.fillRect(b2.x, b2.y, b2.w, b2.h);
				}
			}
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
}
