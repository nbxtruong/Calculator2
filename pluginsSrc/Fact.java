import calculator.Operator;


public class Fact implements Operator {

	@Override
	public String symbol() {
		return "!";
	}

	@Override
	public int arity() {
		return 1;
	}

	@Override
	public double evaluate(double[] operands) {
		double result = 1;
		for (int i = 2; i <= operands[0]; i++)
			result *= i;
		return result;
	}

}
