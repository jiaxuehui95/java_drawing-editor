package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.geom.Point2D;

import figures.enums.FigureType;

public class NGon extends Figure {
	
	/**
	 * Le compteur d'instance des cercles.
	 * Utilisé pour donner un numéro d'instance après l'avoir incrémenté
	 */
	private static int counter = 0;
	
	private int nbCotes;
	
	private double rayon;
	
	private Point2D center;
	
	
	/**
	 * Création d'un polygone régulier avec les points en haut à gauche et en bas à
	 * droite
	 *
	 * @param stroke le type de trait
	 * @param edge la couleur du trait
	 * @param fill la couleur de remplissage
	 * @param p le centre du polygone régulier
	 */
	public NGon(BasicStroke stroke, Paint edge, Paint fill, Point2D p) {
		super(stroke, edge, fill);
		instanceNumber = ++counter;
		java.awt.Polygon poly = new Polygon();
		nbCotes = 3;
		center = new Point2D.Double(p.getX(), p.getY());
		rayon = 0.0;
		for (int i=0; i<nbCotes; i++)
		{
			poly.addPoint(0, 0);
		}
		shape = poly;
	}

	public NGon(NGon n) {
		super(n);
		nbCotes = n.nbCotes;
		rayon = n.rayon;
		center = n.center;
		Polygon copyNGon = (Polygon)n.shape;
		int nPoints = copyNGon.npoints;
		
		int[] xpoints = new int[nPoints];
		int[] ypoints = new int[nPoints];

		for (int i = 0; i < nPoints; i++)
		{
			xpoints[i] = copyNGon.xpoints[i];
			ypoints[i] = copyNGon.ypoints[i];
		}
		shape = new java.awt.Polygon(xpoints, ypoints, nPoints);
	}

	/**
	 * Création d'une copie distincte de la figure
	 * @see figures.Figure#clone()
	 */
	@Override
	public Figure clone() {
		return new NGon(this);
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
			NGon n = (NGon) o;
			Polygon n1 = (Polygon) shape;
			Polygon n2 = (Polygon) n.shape;
			
			int nPoints1 = n1.npoints;
			int nPoints2 = n2.npoints;
			
			if (nPoints1 == nPoints2)
			{
				for (int i=0; i<nPoints1; i++)
					if ((n1.xpoints[i]!=n2.xpoints[i]) 
							|| (n1.ypoints[i]!=n2.ypoints[i]))
						return false;
				return true;		
			}
		}
		return false;
	}

	/**
	 * Création d'un NGon sans points (utilisé dans les classes filles
	 * pour initialiser seulement les couleur et le style de trait sans
	 * initialiser {@link #shape}.
	 *
	 * @param stroke le type de trait
	 * @param edge la couleur du trait
	 * @param fill la couleur de remplissage
	 */
	protected NGon(BasicStroke stroke, Paint edge, Paint fill)
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
			NGonPoints();
		}
		else
		{
			System.err.println(getClass().getSimpleName() + "::setRayon : invalid rayon");
		}
	}
	
	public int getNbCotes() {
		return nbCotes;
	}
	
	public void setNbCotes(int nbCotes) {
		if (nbCotes > 25)
			nbCotes = 25;

		else if (nbCotes< 3)
			nbCotes = 3;
		
		if (this.nbCotes  != nbCotes)
		{
			this.nbCotes= nbCotes;
			Polygon poly = (Polygon) shape;
			poly.reset();

			for (int i = 0; i < this.nbCotes; i++)
				poly.addPoint(0, 0);
			
			NGonPoints();
		}
	}
	
	
	@Override
	public void setLastPoint(Point2D p) {
		
		double newRayon = getCenter().distance(p);
		setRayon(newRayon);

	}

	@Override
	public void normalize() {
		//translation.translate(0.0, 0.0);
		java.awt.Polygon poly = (java.awt.Polygon) shape;
		int centerX = (int) getCenterX();
		int centerY = (int) getCenterY();
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
		//center.setLocation(getCenterX(), getCenterY());
	}
	
	public double getCenterX() {
		return center.getX();
	}
	
	public double getCenterY() {
		
		return center.getY();
	}
	
	/* (non-Javadoc)
	 * @see figures.Figure#getCenter()
	 */
	@Override
	public Point2D getCenter()
	{
		return center;
	}


	@Override
	public FigureType getType() {
		return FigureType.NGON;
	}
	
	protected void NGonPoints()
	{
		double delta = 2*Math.PI/nbCotes;
		Polygon poly = (java.awt.Polygon) shape;
		double centerX = getCenterX();
		double centerY = getCenterY();
		for (int i = 0; i< getNbCotes(); i++)
		{
			poly.xpoints[i] = (int)((Math.cos(delta * (double)i) *
					 rayon) + centerX + rayon);
			poly.ypoints[i] = (int)((Math.sin(delta * (double)i) *
					 rayon) + centerY + rayon);
		}
		
	}
	

}
