import calculator.Operator;


public class One implements Operator {

	@Override
	public int arity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double[] operands) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return "one";
	}

}
