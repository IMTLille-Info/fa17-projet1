package uvinfo.bomberman;

public class Avatar {

	private int posX;
	private int posY;

	public int posX() {
		return this.posX;
	}

	public void moveUp() {
		this.posY += 1;
	}

	public int posY() {
		return this.posY;
	}

}
