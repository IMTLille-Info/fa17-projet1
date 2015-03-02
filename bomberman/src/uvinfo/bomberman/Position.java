package uvinfo.bomberman;

public class Position {

	/****** attributs ****/
	private int x;
	private int y;
	
	/***** constructeur ****/
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/***** getters, setters *******/
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/****** méthodes *****/
	public void setCoordonees(int x, int y){
		this.x = x;
		this.y = y;
	}
}
