package figures.listeners.transform;

import figures.Drawing;
import figures.Figure;
import history.HistoryManager;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import utils.Vector2D;

public class RotateShapeListener extends AbstractTransformShapeListener {


	private Vector2D initialVector;

	private Vector2D currentVector;
	
	public RotateShapeListener(Drawing model, HistoryManager<Figure> history,JLabel tipLabel) 
	{
		super(model, history, tipLabel);
		keyMask=InputEvent.CTRL_MASK;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

		if ((currentFigure != null) && (center != null))
		{
			initialVector = new Vector2D(center, startPoint);
			currentVector = new Vector2D(initialVector);
			initialTransform = currentFigure.getRotation();
		}
		else
		{
			System.err.println(getClass().getSimpleName() + "currentFigure null or center null");
		}
	}

	@Override
	public void updateDrag(MouseEvent e) {
		// TODO Auto-generated method stub
		Point2D courant = e.getPoint();
		currentVector.setEnd(courant);
		if (currentFigure != null)
		{
			double angle = initialVector.angle(currentVector);
			AffineTransform rotate = AffineTransform.getRotateInstance(angle);
			rotate.concatenate(initialTransform);
			currentFigure.setRotation(rotate);
		}
		else
		{
			System.err.println(getClass().getSimpleName() + "null figure");
		}

	}

}
