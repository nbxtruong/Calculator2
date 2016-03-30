import calculator.Operator;

public class Divide implements Operator {
	public String symbol() {
		return "/";
	}

	public int arity() {
		return 2;
	}

	public double evaluate(double[] operands) {
		return operands[0] / operands[1];
	}
}
