/**
 * 
 * This class handles the subtraction of two values
 * (Overload for other data types)
 */
public class Subtraction implements BinOperation{
	@Override
	public double evaluate(double left, double right) {
		return left-right;
	}
	
}
