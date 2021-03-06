package figures.listeners.creation;

import figures.Drawing;
import figures.Figure;
import figures.Polygon;
import history.HistoryManager;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

public class PolygonCreationListener extends AbstractCreationListener {

	public PolygonCreationListener(Drawing model,
								   HistoryManager<Figure> history, 
								   JLabel infoLabel) {
		super(model, history, infoLabel,2);

		tips[0] = new String("Cliquez pour initier le polygone");
		tips[1] = new String("Cliquez pour ajouter un point, Faites un clic droit pour le polygone");

		updateTip();

		System.out.println("PolygonCreationListener created");

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Création d'une nouvelle figure de taille 0 au point de
	 * l'évènement souris, si le bouton appuyé est le bouton gauche.
	 *
	 * @param e l'évènement souris
	 * @see AbstractCreationListener#startAction(MouseEvent)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 0))
		{
			startAction(e);
			System.out.println("initiating polygon");
		}
		
		else if (currentStep != 0)
		{
			Polygon poly = (Polygon) currentFigure;

			switch (e.getButton())
			{
				case MouseEvent.BUTTON1:
					// On ajoute un point au polygone
					Point p = e.getPoint();
					poly.addPoint(p.x, p.y);
					break;
				case MouseEvent.BUTTON2:
					// On supprime le dernier point
					poly.deleteLastPoint();
					break;
					
			}
		}
	    
		updateTip();

	}

	/**
	 * Terminaison du polygone si le bouton appuyé
	 * était le bouton gauche
	 * @param e l'évènement souris
	 * @see AbstractCreationListener#endAction(MouseEvent)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON3) && (currentStep == 1))
		{
			endAction(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (currentStep == 1)
		{
			if (currentFigure != null)
				currentFigure.setLastPoint(e.getPoint());
			else
				System.err.println(getClass().getSimpleName() + "::mouseMoved : null figure");
			drawingModel.update();
		}

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

}
