package filters;

import figures.Figure;
import figures.enums.FigureType;

public class ShapeFilter2 extends FigureFilter<FigureType>
{
	
	public ShapeFilter2(FigureType element)
	{
		super(element);
	}

	@Override
	public boolean test(Figure f)
	{
		if(f.getType()== element)
		{
			return true;
		}
		else return false;
	}
}