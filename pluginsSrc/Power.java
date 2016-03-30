import calculator.Operator;


public class Power implements Operator {

	@Override
	public int arity() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public double evaluate(double[] operands) {
		// TODO Auto-generated method stub
		return Math.pow(operands[0], operands[1]);
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return "^";
	}

}
