package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.geom.Point2D;

import figures.enums.FigureType;

public class Star extends Figure {
	
	/**
	 * Le compteur d'instance des cercles.
	 * Utilisé pour donner un numéro d'instance après l'avoir incrémenté
	 */
	private static int counter = 0;

	private double rayon;
	
	private Point2D center;

	public Star(BasicStroke stroke, Paint edge, Paint fill, Point2D p) {
		super(stroke, edge, fill);
		instanceNumber = ++counter;
		rayon = 0.0;
		center = new Point2D.Double(p.getX(), p.getY());
		java.awt.Polygon poly = new Polygon();
		shape = poly;
		
	}

	public Star(Star s) {
		super(s);
		rayon = s.rayon;
		center = new Point2D.Double(s.center.getX(), s.center.getY());
		Polygon copyStar = (Polygon)s.shape;
		int nPoints = copyStar.npoints;
		
		int[] xpoints = new int[nPoints];
		int[] ypoints = new int[nPoints];

		for (int i = 0; i < nPoints; i++)
		{
			xpoints[i] = copyStar.xpoints[i];
			ypoints[i] = copyStar.ypoints[i];
		}
		shape = new java.awt.Polygon(xpoints, ypoints, nPoints);
	
	}

	@Override
	public Figure clone() {
		return new Star(this);
	}
	
	/**
	 * Comparaison de deux figures
	 * @param Object o l'objet à comparer
	 * @return true si obj est une figure de même type et que son contenu est
	 * identique
	 */
	@Override
	public boolean equals(Object o)
	{
		if (super.equals(o))
		{
			Star n = (Star) o;
			Polygon n1 = (Polygon) shape;
			Polygon n2 = (Polygon) n.shape;
			
			int nPoints1 = n1.npoints;
			int nPoints2 = n2.npoints;
			Point2D c1 = center;
			Point2D c2 = n.center;
			
			if ((c1.getX() == c2.getY()) && (c1.getY() == c2.getY()))
			{
				if (nPoints1 == nPoints2)
				{
					for (int i=0; i<nPoints1; i++)
						if ((n1.xpoints[i]!=n2.xpoints[i]) 
								|| (n1.ypoints[i]!=n2.ypoints[i]))
							return false;
					return true;		
				}
			}
		}
		return false;
	}

	/**
	 * Création d'une étoile sans points (utilisé dans les classes filles
	 * pour initialiser seulement les couleur et le style de trait sans
	 * initialiser {@link #shape}.
	 *
	 * @param stroke le type de trait
	 * @param edge la couleur du trait
	 * @param fill la couleur de remplissage
	 */
	protected Star(BasicStroke stroke, Paint edge, Paint fill)
	{
		super(stroke, edge, fill);
		shape = null;
	}

	public double getRayon() {
		return rayon;
	}
	
	public void setRayon(double rayon) {
		
		if (rayon >=0.0)
		{
			this.rayon = rayon;
			//recomputePoints();
			StarPoints();
		}
		else
		{
			System.err.println(getClass().getSimpleName() + "::setRayon : invalid rayon");
		}
	}
	
	@Override
	public void setLastPoint(Point2D p) {

		double newRayon = getCenter().distance(p);
		setRayon(newRayon);

	}

	@Override
	public void normalize() {
		int centerX = (int) getCenter().getX();
		int centerY = (int) getCenter().getY();
		translation.translate(0.0, 0.0);
		center.setLocation(centerX, centerY);
	}

	@Override
	public Point2D getCenter() {
		return center;
	}

	@Override
	public FigureType getType() {
		return FigureType.STAR;
	}
	
	public void StarPoints()
	{
		int x0 = (int) getCenter().getX();
		int y0 = (int) getCenter().getY();
		double ch=72*Math.PI/180;
		double x1=x0,
		x2=(double)(x0-Math.sin(ch)*rayon),
		x3=(double)(x0+Math.sin(ch)*rayon),
		x4=(double)(x0-Math.sin(ch/2)*rayon),
		x5=(double)(x0+Math.sin(ch/2)*rayon);
		double y1=y0-rayon,
		    y2=(double)(y0-Math.cos(ch)*rayon),
		    y3=y2,
		    y4=(double)(y0+Math.cos(ch/2)*rayon),
		    y5=y4; 
		
		Polygon poly = (Polygon) shape;
	    poly.reset();
	    poly.addPoint((int)x1,(int)y1);
		poly.addPoint((int)x4,(int)y4);
		poly.addPoint((int)x3,(int)y3);
		poly.addPoint((int)x2,(int)y2);
		poly.addPoint((int)x5,(int)y5);
	    
		/*
		int[] lx = new int[5];
		int[] ly = new int[5];
		int[] sx = new int[5];
		int[] sy = new int[5];
		int x = (int) getCenter().getX();
		int y = (int) getCenter().getY();
		int lr = (int) getRayon();
		int sr=(int)(lr*Math.cos(0.4*Math.PI) / Math.cos(0.2*Math.PI));  

	    Polygon poly = (Polygon) shape;
	    poly.reset();
	    
		for(int i=0;i<5;i++)  
		{
			
			lx[i]=(int) ( x-lr*Math.sin(i*0.4*Math.PI));
			ly[i]=(int) ( y-lr*Math.cos(i*0.4*Math.PI));
			sx[i]=(int)(x-sr*Math.sin(0.2+i*0.4*Math.PI));
	        sy[i]=(int)(y-sr*Math.cos(0.2+i*0.4*Math.PI));

	        poly.addPoint(lx[i], ly[i]);
	        poly.addPoint(sx[i], sy[i]);
			
			

			//System.out.println("lx" + i + " " + lx[i] + ", " + ly[i]);
			//System.out.println("sx" + i + " " + sx[i] + ", " + sy[i]);
		   
		}
       
		*/
	    
	    
	    
	    
	}

}
