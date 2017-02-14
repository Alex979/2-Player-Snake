import java.awt.Color;
import java.util.ArrayList;


public class Snake {

	private ArrayList<SnakeBody> body = new ArrayList<SnakeBody>();
	private int xPos;
	private int yPos;
	private int direction = NONE;
	public boolean canSwitchDir = true;
	public int speed = 75;
	private int snakeIndex = 1;
	public static Color snakeColor1;
	public static Color snakeColor2;
	private boolean isDead = false;
	
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NONE = -1;
	public static final int INDEX_ONE = 1, INDEX_TWO = 5;
	
	
	public Snake(int x, int y){
		xPos = x;
		yPos = y;
		body.add(new SnakeBody(xPos, yPos));
	}
	
	public void setIndex(int index){
		snakeIndex = index;
	}
	
	public void setDirection(int dir){
		direction = dir;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public void isDead(boolean bool){
		isDead = bool;
	}
	
	public void addBody(){
		body.add(new SnakeBody(body.get(body.size()-1).getX(), body.get(body.size()-1).getY()));
	}
	
	public void update(){
		canSwitchDir = true;
		
		switch(direction){
		case UP:
			yPos--;
			break;
		case RIGHT:
			xPos++;
			break;
		case DOWN:
			yPos++;
			break;
		case LEFT:
			xPos--;
			break;
		}
		if(xPos > SnakePanel.grid.getWidth()-1){
			xPos = 0;
			direction = RIGHT;
		}else if(xPos < 0){
			xPos = SnakePanel.grid.getWidth()-1;
			direction = LEFT;
		}
		if(yPos > SnakePanel.grid.getHeight()-1){
			yPos = 0;
			direction = DOWN;
		}else if(yPos < 0){
			yPos = SnakePanel.grid.getHeight()-1;
			direction = UP;
		}
		
		
		if(SnakePanel.grid.getGrid(xPos, yPos) == 3){
			addBody();
			if(speed > 20)
				speed--;
			SnakePanel.t.setDelay(speed);
			SnakePanel.grid.spawnFood();
		}
		
		if(direction != NONE){
			for(int x=body.size()-1; x>=1; x--){
				SnakePanel.grid.setGrid(body.get(x).getX(), body.get(x).getY(), 0);
				body.get(x).setX(body.get(x-1).getX());
				body.get(x).setY(body.get(x-1).getY());
			}
			SnakePanel.grid.setGrid(body.get(0).getX(), body.get(0).getY(), 0);
		}
		body.get(0).setX(xPos);
		body.get(0).setY(yPos);
		
		for(int x=1; x<body.size(); x++){
			if(SnakePanel.grid.getGrid(body.get(x).getX(), body.get(x).getY()) != 2)
				SnakePanel.grid.setGrid(body.get(x).getX(), body.get(x).getY(), snakeIndex);
		}
		
		if((SnakePanel.grid.getGrid(xPos, yPos) == INDEX_ONE || SnakePanel.grid.getGrid(xPos, yPos) == INDEX_TWO || SnakePanel.grid.getGrid(xPos, yPos) == 2 || SnakePanel.grid.getGrid(xPos, yPos) == 4) && direction != NONE){
			direction = NONE;
			SnakePanel.grid.setGrid(xPos, yPos, 2);
			snakeColor1 = new Color(250, 100, 100);
			snakeColor2 = new Color(250, 100, 100);
			isDead = true;
		}else if(SnakePanel.grid.getGrid(xPos, yPos) != 2)
			SnakePanel.grid.setGrid(xPos, yPos, snakeIndex);
		
	}
}
