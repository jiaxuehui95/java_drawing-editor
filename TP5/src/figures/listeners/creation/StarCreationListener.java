package figures.listeners.creation;

import figures.Drawing;
import figures.Figure;
import history.HistoryManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

/**
 * Listener permettant d'enchainer les actions souris pour créer des formes
 * rectangulaires comme des rectangles ou des ellipse (evt des cercles):
 * <ol>
 * 	<li>bouton 1 pressé et maintenu enfoncé</li>
 * 	<li>déplacement de la souris avec le bouton enfoncé</li>
 * 	<li>relachement du bouton</li>
 * </ol>
 */

public class StarCreationListener extends AbstractCreationListener {

	public StarCreationListener(Drawing model, 
			HistoryManager<Figure> history,
			JLabel infoLabel) {
		super(model, history, infoLabel, 2);
		tips[0] = new String("Cliquez et maintenez enfoncé pour initier l'étoile");
		tips[1] = new String("Relâchez pour terminer l'étoile");

		updateTip();

		System.out.println("StarShapeCreationListener created");

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
	 * Création d'une nouvelle figure rectangulaire de taille 0 au point de
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
		}

	}

	/**
	 * Terminaison de la nouvelle figure rectangulaire si le bouton appuyé
	 * était le bouton gauche
	 * @param e l'évènement souris
	 * @see AbstractCreationListener#endAction(MouseEvent)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 1))
		{
			endAction(e);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentStep == 1)
		{
			// AbstractFigure figure = drawingModel.getLastFigure();
			if (currentFigure != null)
			{
				currentFigure.setLastPoint(e.getPoint());
			}
			else
			{
				System.err.println(getClass().getSimpleName() + "::mouseDragged : null figure");
			}

			drawingModel.update();
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

}
