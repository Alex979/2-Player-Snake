import java.awt.Color;
import java.awt.Graphics;


public class Grid {

	private int width;
	private int height;
	private int[][] gridElements;
	
	public Grid(int width, int height){
		this.width = width/10;
		this.height = height/10;
		gridElements = new int[this.width][this.height];
	}
	
	public void setGrid(int x, int y, int value){
		gridElements[x][y] = value;
	}
	
	public int getGrid(int x, int y){
		return gridElements[x][y];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void spawnFood(){
		int x;
		int y;
		do{
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}while(getGrid(x, y) != 0);
		
		setGrid(x, y, 3);
	}
	
	public void draw(Graphics g){
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++){
				switch(gridElements[x][y]){
				case 0:
					g.setColor(new Color(32, 32, 32));
					break;
				case 1:
					g.setColor(Snake.snakeColor1);
					break;
				case 2:
					g.setColor(Snake.snakeColor1.darker());
					break;
				case 3:
					g.setColor(new Color(250, 200, 100));
					break;
				case 4:
					g.setColor(new Color(80, 80, 80));
					break;
				case 5:
					g.setColor(Snake.snakeColor2);
					break;
				}
				g.fillRect(x*10, y*10, 10, 10);
				g.setColor(g.getColor().darker());
				g.drawRect(x*10, y*10, 10, 10);
			}
		}
	}
}
