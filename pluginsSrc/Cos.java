import calculator.Operator;

public class Cos implements Operator {

	public String symbol() {
		return "cos";
	}

	public int arity() {
		return 1;
	}

	public double evaluate(double[] operands) {
		return Math.cos(operands[0]);
	}

}
