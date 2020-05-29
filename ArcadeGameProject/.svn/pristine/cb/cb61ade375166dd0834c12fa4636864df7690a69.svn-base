import java.awt.geom.Point2D;

public class Rock extends GameObject {
	
	// ======= Important Objects ======= \\
	public Point2D position;
	private String name;
	private World world;
	
	// ======= Rock Constructor ======= \\

	public Rock(Point2D initialPosition, World world){
		super(initialPosition, world);
		this.position = initialPosition;
		this.name="rock";
		this.world=world;
		this.setName("rock");
	
	}	

	public Point2D returnPosition(){
		return this.position;
	}
	
	@Override
	public void timePassed() {
		// TODO Auto-generated method stub.
		updatePosition();
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub.
		return false;
	}
	
	public void updatePosition() {
		Point2D newPosition = new Point2D.Double(200,400);
		this.setCenterPoint(newPosition);
	}
}