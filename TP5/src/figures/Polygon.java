/**
 * 
 */
package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Point2D;

import figures.enums.FigureType;

/**
 * Classe de Polygon pour les {@link Figure}
 * @author tingting
 *
 */
public class Polygon extends Figure {

	
	/**
	 * Le compteur d'instance des Polygones.
	 * Utilisé pour donner un numéro d'instance après l'avoir incrémenté
	 */
	private static int counter = 0;
	
	/**
	 * Création d'un poygon avec 2 points au début
	 *
	 * @param stroke le type de trait
	 * @param edge la couleur du trait
	 * @param fill la couleur de remplissage
	 * @param p1 premier point
	 * @param p2 deuxième point
	 */
	public Polygon(BasicStroke stroke, Paint edge, Paint fill, Point p1, Point p2) {
		super(stroke, edge, fill);
		instanceNumber = ++counter;
		java.awt.Polygon poly = new java.awt.Polygon();
		poly.addPoint(p1.x, p1.y);
		poly.addPoint(p2.x, p2.y);
		shape = poly;
	}
	
	/**
	 * Constructeur de copie assurant une copie distincte du polygone
	 * @param poly le polygone à copier
	 */
	public Polygon(Polygon poly) {
		super(poly);
		if (poly.getClass() == Polygon.class)
		{
			java.awt.Polygon copyPoly = (java.awt.Polygon) poly.shape;
			int nPoints = copyPoly.npoints;
			int[] xpoints = new int[nPoints];
			int[] ypoints = new int[nPoints];
	
			for (int i = 0; i < nPoints; i++)
			{
				xpoints[i] = copyPoly.xpoints[i];
				ypoints[i] = copyPoly.ypoints[i];
			}
	
			shape = new java.awt.Polygon(xpoints, ypoints, nPoints);
		}
		
	}

	
	/**
	 * Création d'une copie distincte de la figure
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		return new Polygon(this);
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
			Polygon p = (Polygon) o;
			java.awt.Polygon p1 = (java.awt.Polygon) shape;
			java.awt.Polygon p2 = (java.awt.Polygon) p.shape;
			
			int nPoints1 = p1.npoints;
			int nPoints2 = p2.npoints;
			
			if (nPoints1 == nPoints2)
			{
				for (int i=0; i<nPoints1; i++)
					if ((p1.xpoints[i]!=p2.xpoints[i]) 
							|| (p1.ypoints[i]!=p2.ypoints[i]))
						return false;
				return true;
						
			}
		}
		return false;
	}
	
	/**
	 * Création d'un polygone sans points (utilisé dans les classes filles
	 * pour initialiser seulement les couleur et le style de trait sans
	 * initialiser {@link #shape}.
	 *
	 * @param stroke le type de trait
	 * @param edge la couleur du trait
	 * @param fill la couleur de remplissage
	 */
	protected Polygon(BasicStroke stroke, Paint edge, Paint fill)
	{
		super(stroke, edge, fill);

		shape = null;
	}

	/**
	 * Déplacement du point en bas à droite du rectangle à la position
	 * du point p
	 *
	 * @param p la nouvelle position du dernier point
	 * @see figures.Figure#setLastPoint(Point2D)
	 */
	@Override
	public void setLastPoint(Point2D p) {
		if (shape != null)
		{
			java.awt.Polygon poly = (java.awt.Polygon) shape;
			int lastnPoint = poly.npoints-1;
			if (lastnPoint >= 0)
			{
				poly.xpoints[lastnPoint] = Double.valueOf(p.getX()).intValue();
				poly.ypoints[lastnPoint] = Double.valueOf(p.getY()).intValue();
			}
		}
		else 
		{
			System.err.println(getClass().getSimpleName() + "::setLastPoint : null shape");
		}
	}
	
	/**
	 *Supprimer le dernier point du polygone
	 *
	 */
	public void deleteLastPoint() {
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		if (poly.npoints > 1)
		{
			int x [] = new int[poly.npoints-1];
			int y [] = new int[poly.npoints-1];
			for (int i = 0; i < x.length; i++)
			{
				x[i] = poly.xpoints[i];
				y[i] = poly.ypoints[i];
			}
				
			poly.reset();
				
			for (int i = 0; i < x.length; i++)
			{
				poly.addPoint(x[i], y[i]);
			}
		}
		else 
		{
			System.err.println(getClass().getSimpleName() + "::deleteLastPoint : null shape");
		}
	}

	/**
	 * Normalise une figure de manière à exprimer tous ses points par rapport
	 * à son centre, puis transfère la position réelle du centre dans l'attribut
	 * {@link #translation}
	 */
	@Override
	public void normalize() {
		
		int centerX = (int) getCenterX();
		int centerY = (int) getCenterY();
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		translation.setToTranslation(getCenterX(),getCenterY());
	
		int[] x = new int[poly.npoints];
		int[] y = new int[poly.npoints];
		for (int i = 0; i < poly.npoints; i++)
		{
			x[i] = poly.xpoints[i] - centerX;
			y[i] = poly.ypoints[i] - centerY;
			
			
		}
		poly.reset();
		for (int i = 0; i < x.length; i++)
		{
			poly.addPoint(x[i], y[i]);
		}
		
	}

	public double getCenterX() {
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		double centerX = 0.0;
		if  (poly.npoints > 0)
		{
			for (int i = 0; i < poly.npoints; i++)
			{
				centerX += poly.xpoints[i];
			}
			
			centerX = centerX/poly.npoints;
		}
		
		return centerX;
	}
	
	public double getCenterY() {

		
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		double centerY = 0.0;
		if  (poly.npoints > 0)
		{
			for (int i = 0; i < poly.npoints; i++)
			{
				centerY += poly.ypoints[i];
			}
			
			centerY = centerY/poly.npoints;
		}
		
		return centerY;
	}
	
	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter() {
		
		Point2D center = new Point2D.Double(getCenterX(), getCenterY());
		Point2D tCenter = new Point2D.Double();
		getTransform().transform(center, tCenter);

		return tCenter;
		
	}

	/**
 	 * Accesseur du type de figure selon {@link FigureType}
 	 * @return le type de figure
 	 */
	@Override
	public FigureType getType() 
	{
		return FigureType.POLYGON;
	}

	/**
	 * Ajout d'un point au polygone
	 * @param x l'abcisse du point à ajouter
	 * @param y l'ordonnée du point à ajouter
	 */
	public void addPoint(int x, int y) {
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		poly.addPoint(x, y);
		
	}
	


}
