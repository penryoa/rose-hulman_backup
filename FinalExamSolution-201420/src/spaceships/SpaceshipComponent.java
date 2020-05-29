package spaceships;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * Creates the spaceship objects and invokes their
 * drawing functions from paintComponent.
 * 
 * You may modify this class in any way you wish.
 * 
 * @author hewner
 *
 */
public class SpaceshipComponent extends JComponent implements MouseListener, ActionListener {

	private TeleportySpaceship teleporter;
	private MovingSpaceship mover;
	
	// in part 3 the above variables will be removed and
	// replaced with something like this
	private ArrayList<Spaceship> spaceships;
	
	/**
	 * Default constructor for SpaceshipComponent.
	 *
	 */
	public SpaceshipComponent() {
		this.teleporter = new TeleportySpaceship(300,300);
		this.mover = new MovingSpaceship(500,500);
		
		this.spaceships = new ArrayList<Spaceship>();
		this.spaceships.add(this.teleporter);
		this.spaceships.add(this.mover);
		this.spaceships.add(new MovingSpaceship(0,0));
		
		this.addMouseListener(this);
		Timer timer = new Timer(100, this);
		timer.start();
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D g = (Graphics2D) arg0;
		
		/*
		teleporter.drawOn(g);
		mover.drawOn(g);
		*/
		
		for(Spaceship s : this.spaceships) {
			s.drawOn(g);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		teleporter.moveTo(e.getPoint().x, e.getPoint().y);
		mover.moveTo(e.getPoint().x, e.getPoint().y);
		*/
		for(Spaceship s: this.spaceships) {
			s.moveTo(e.getPoint().x, e.getPoint().y);
		}
		repaint();
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Nothing to do here.
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// Nothing to do here.
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nothing to do here.
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nothing to do here.
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
	
	
}
