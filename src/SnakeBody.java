
public class SnakeBody {
	
	private int xPos;
	private int yPos;
	private int direction;
	
	public SnakeBody(int x, int y){
		xPos = x;
		yPos = y;
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
	
	public void setX(int x){
		xPos = x;
	}
	
	public void setY(int y){
		yPos = y;
	}
}
