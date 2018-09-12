import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

 
public class GameOfLife implements MouseListener, ActionListener, Runnable {
	
	final int WIDTH = 21;
	final int HEIGHT = 21;
	
	boolean [][] grid = new boolean[WIDTH][HEIGHT];
	
	JFrame frame = new JFrame("Game of Life");
	MyPanel panel = new MyPanel(grid);
	boolean running = false;
	
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton clear = new JButton("Clear");
	Container south = new Container();
	
	public GameOfLife() {
		
		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1,3));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(clear);
		clear.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		panel.addMouseListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		new GameOfLife();
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println(e.getX() + "," + e.getY());
		int col = Math.min(e.getX() / (panel.getWidth() / WIDTH), 20);
		int row = Math.min(e.getY() / (panel.getHeight() / HEIGHT), 20);
		grid[col][row] = !grid[col][row];
		running = true;
	
		
		//blinkers
	 if(col == 0){
			grid[col + 1][row] = true;
			grid[col + 2][row] = true;
		}
		if(col == grid.length -1){
			grid[col - 1][row] = true;
			grid[col - 2][row] = true;
		}else if(col > 0 && col < grid.length - 1){
			grid[col - 1][row] = true;
			grid[col + 1][row] = true;
		} 
		
		//gliders
		if(col - 10 >=0 && row - 10 >= 0){
			grid[col - 10][row - 9] = true;
			grid[col - 9][row - 8] = true;
			grid[col - 8][row - 8] = true;
			grid[col - 8][row - 9] = true;
			grid[col - 8][row - 10] = true;
			
		}
		if(col - 10 >= 0 && row + 10 <= 20){
			grid[col -10][row +9] = true;
			grid[col - 9][row + 8] = true;
			grid[col - 8][row + 8] = true;
			grid[col - 8][row + 9] = true;
			grid[col - 8][row + 10] = true;
		}
		if(col + 10 <= 20 && row - 10 >= 0){
			grid[col + 8][row - 10] =true;
			grid[col + 8][row - 9] =true;
			grid[col + 8][row - 8] =true;
			grid[col + 9][row - 8] =true;
			grid[col + 10][row - 9] =true;
		}
		if(col + 10 <= 20 && row + 10 <= 20){
			grid[col + 8][row + 10] =true;
			grid[col + 8][row + 9] =true;
			grid[col + 8][row + 8] =true;
			grid[col + 9][row + 8] =true;
			grid[col + 10][row + 9] =true;
		}
		frame.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			running = true;
			Thread t = new Thread(this);
			t.start();
		}
		if (e.getSource().equals(stop)) {
			running = false;
		}
		if (e.getSource().equals(clear)) {
			boolean[][] newGrid = new boolean[grid.length][grid.length];
			grid = newGrid;
			panel.setGrid(newGrid);
			frame.repaint();
			running = false;
			
		}
		
	}
	public void run() {
		while (running == true){
			step();
			frame.repaint();
			try{
				Thread.sleep(500);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void step() {
		boolean[][] newGrid = new boolean[grid.length][grid.length];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				int neighborCount = 0;
				if(y > 0 && grid[x][y-1] == true){
					neighborCount++;
				}
				if(y<grid.length - 1 && grid[x][y+1] == true){
					neighborCount++;
				}
				if(x > 0 && grid[x-1][y] == true){
					neighborCount++;
				}
				if(x<grid.length - 1 && grid[x+1][y] == true){
					neighborCount++;
				}
				if(y < grid.length - 1 && x < grid.length - 1 && grid[x+1][y+1] == true){
					neighborCount++;
				}
				if(x > 0 && y < grid.length - 1 && grid[x-1][y+1] == true){
					neighborCount++;
				}
				if(x < grid.length - 1 && y >0 && grid[x+1][y-1] ==true){
					neighborCount++;
				}
				if(x > 0 && y > 0 && grid[x-1][y-1] == true){
					neighborCount++;
				}
				
				if (grid[x][y] == true){
					if (neighborCount == 2 || neighborCount == 3){
						newGrid[x][y] = true;
					}
					else {
						newGrid[x][y] = false;
					}
				}
				else {
					if (neighborCount == 3) {
						newGrid[x][y] = true;
					}
					else{
						newGrid[x][y] = false;
					}
				}
			}
			
			
		}
		grid = newGrid;
		panel.setGrid(newGrid);
		frame.repaint();
		
	}



}
