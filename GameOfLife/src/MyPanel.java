import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class MyPanel extends JPanel{
	
	boolean [][] grid;
	
	public MyPanel(boolean[][] newGrid) {
		
		grid = newGrid;
	}
	
	public void setGrid(boolean[][] newGrid) {
		grid = newGrid;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		double boxWidth = (double)(this.getWidth()) / grid.length; 
		double boxHeight = (double)(this.getHeight()) / grid.length;
		
		for (int i = 0; i < grid.length; i++) {
		
			g.drawLine((int)(i*boxWidth), 0, (int)(i*boxWidth), this.getHeight()); 
			g.drawLine(0, (int)(i*boxHeight), this.getWidth(), (int)(i*boxHeight));
		}
		for (int x = 0; x < grid.length; x++) {
			for (int y =0; y < grid.length; y++) {
				if(grid[x][y] == true) {
					g.setColor(Color.RED);
					g.fillRect((int)(x*boxWidth), (int)(y*boxHeight), (int)(boxWidth), (int)(boxHeight));
				}
			}
		}
		
		  
	}

}
