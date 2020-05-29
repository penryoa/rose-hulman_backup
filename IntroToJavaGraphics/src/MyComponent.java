import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class MyComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		
//		Graphics2D g2 = (Graphics2D) g;
//		
//
//		
//		for (int k = 0; k< (this.getWidth() - 20)/10;k++) {
//			Rectangle2D.Double rectangle = new Rectangle2D.Double(10, 10, 10+10*k, 10+10*k);
//			g2.draw(rectangle);
//		}
//		int baseY = this.getWidth()+10 + 20;
//		
//		g2.translate(0, baseY);
//		
//		Line2D.Double xAxis = new Line2D.Double(-100, 0, 100, 0);
//		Line2D.Double yAxis = new Line2D.Double(0, -100, 0, 100);
//		
//		Ellipse2D.Double ellipse = new Ellipse2D.Double(10, 0, 50, 100);
//		g2.draw(ellipse);
//		
//		g2.translate(60 + 50/2, 100/2);
//		g2.rotate(45*Math.PI/180);
//		
//		g2.draw(xAxis);
//		g2.draw(yAxis);
//
//		g2.drawString("-x", -100, 2);
//		g2.drawString("+x", 100, -2);
//		g2.drawString("-y", 2, -100);
//		g2.drawString("+y", 2, 100);
//		
//		
//		Ellipse2D.Double ellipse2 = new Ellipse2D.Double(-50/2, -100/2, 50, 100);
//		
//		g2.draw(ellipse2);
		
		
		
	}

	
	
	
	
}
