import calculator.Operator;

public class Minus implements Operator {
	public String symbol() {
		return "minus";
	}

	public int arity() {
		return 2;
	}

	public double evaluate(double[] operands) {
		return operands[0] - operands[1];
	}
}
