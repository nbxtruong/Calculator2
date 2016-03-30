import calculator.Operator;

public class Sin implements Operator {
	public String symbol() {
		return "sin";
	}

	public int arity() {
		return 1;
	}

	public double evaluate(double[] operands) {
		return Math.sin(operands[0]);
	}
}
