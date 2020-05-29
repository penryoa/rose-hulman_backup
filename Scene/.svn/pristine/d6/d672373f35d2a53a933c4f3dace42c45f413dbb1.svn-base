import java.awt.Color;
import java.awt.Graphics2D;

public class Sun {
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final int NUMBER_OF_RAYS = 8;
	private static final double RAY_LENGTH_SCALE = 0.5;
	private static final double RAY_WIDTH_SCALE = 0.1;
	private static final double RAY_DISTANCE_FROM_SUN_SCALE = .2;
	private static final double DEFAULT_SUN_DIAMETER = 100.0;
	private static final double DEFAULT_SUN_X = 100.0;
	private static final double DEFAULT_SUN_Y = 100.0;
	private static final Color DEFAULT_SUN_COLOR = Color.YELLOW;
	private static final double LITTLE_SUNS_X_OFFSET = 50;
	
	public int x;
	public int y;
	public int circleDiameter;
	public int rayLength;
	public int rayWidth;
	public int rayDistanceFromSun;
	public Color color;
	
	public Sun () {
		this.x = (int) DEFAULT_SUN_X;
		this.y = (int) DEFAULT_SUN_Y;
		this.circleDiameter = (int) DEFAULT_SUN_DIAMETER;
		this.color = DEFAULT_SUN_COLOR;
		this.rayDistanceFromSun = (int) (RAY_DISTANCE_FROM_SUN_SCALE*circleDiameter);
		this.rayLength = (int) (RAY_LENGTH_SCALE*circleDiameter);
		this.rayWidth = (int) (RAY_WIDTH_SCALE*circleDiameter);
	}
	
	public Sun (int x, int y, int circleDiameter, Color color) {
		this.x = x;
		this.y = y;
		this.circleDiameter = circleDiameter;
		this.color = color;
		this.rayDistanceFromSun = (int) (RAY_DISTANCE_FROM_SUN_SCALE*circleDiameter);
		this.rayLength = (int) (RAY_LENGTH_SCALE*circleDiameter);
		this.rayWidth = (int) (RAY_WIDTH_SCALE*circleDiameter);
		
	}
	
	
	public void drawOn(Graphics2D g2) {
		int xValue = (int) (this.x-rayLength-rayDistanceFromSun);
		int yValue = (int) (this.y-rayLength-rayDistanceFromSun);
		int diameter = (int) circleDiameter;

		g2.setColor(color);
		g2.fillOval(this.x, this.y, diameter, diameter);
		g2.setColor(BORDER_COLOR);
		g2.drawOval(this.x, this.y, diameter, diameter);
		
//		g2.drawRect(xValue, yValue, 
//				(int)(diameter+2*rayLength+2*rayDistanceFromSun), 
//				(int)(diameter+2*rayLength+2*rayDistanceFromSun));	
	
		for (int k=0; k<NUMBER_OF_RAYS;k++) {
			drawRays(g2, 360/NUMBER_OF_RAYS);
			g2.translate(1, -1);
//			I'm not sure how to translate this correctly, but I think the rotating is okay! 
		}
	}
	
	public void drawRays(Graphics2D g2, int angle) {
		g2.setColor(color);
		g2.fillRect(this.x+circleDiameter/2, this.y-rayDistanceFromSun-rayLength, rayWidth, rayLength);
		g2.setColor(BORDER_COLOR);
		g2.drawRect(this.x+circleDiameter/2, this.y-rayDistanceFromSun-rayLength, rayWidth, rayLength);
		
		g2.rotate(angle*Math.PI/180);
		
		
	}
	
}
