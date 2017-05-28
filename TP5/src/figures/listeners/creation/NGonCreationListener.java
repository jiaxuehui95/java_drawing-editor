package figures.listeners.creation;

import figures.Drawing;
import figures.Figure;
import figures.NGon;
import history.HistoryManager;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

public class NGonCreationListener extends AbstractCreationListener {

	private Point releasedPoint;
	private int NbCotes;
	
	
	public NGonCreationListener(Drawing model, 
			HistoryManager<Figure> history,
			JLabel infoLabel) {
		super(model, history, infoLabel, 3);
		tips[0] = new String("Bouton gauche et maintenez pour commencer le polygone régulier");
		tips[1] = new String("Relâchez pour terminer la taille du polygon");
		tips[2] = new String("Bouger la souris pour déterminer le nb de côtés, puis click pour terminer");

		updateTip();

		System.out.println("NGonCreationListener created");

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 2))
		{
			endAction(e);
		}

	}


	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 0))
		{
			startAction(e);
			NbCotes = ((NGon) currentFigure).getNbCotes();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (currentStep == 1))
		{
			releasedPoint = e.getPoint();
			nextStep();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentStep == 1)
		{
			currentFigure.setLastPoint(e.getPoint());

			drawingModel.update();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (currentStep == 2)
		{
			NGon ngon = (NGon) currentFigure;
			Point point = e.getPoint();
			int delta = point.y - Double.valueOf(releasedPoint.getY()).intValue();
			int newNbCotes = NbCotes + delta/20;

			ngon.setNbCotes(newNbCotes);

				if (ngon.getNbCotes() != newNbCotes)
				{
					releasedPoint.setLocation(point.x, point.y);;
				}

			drawingModel.update();

		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("Scrolling : " + e.getUnitsToScroll());
		

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
