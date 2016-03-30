package calculator;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
	private final Map<String, Operator> operators = new HashMap<String, Operator>();

	public Calculator(Operator[] ops) {
		for (Operator op : ops) {
			add(op);
		}
	}

	public void add(Operator op) {
		operators.put(op.symbol(), op);
	}

	public Operator[] operators() {
		return operators.values().toArray(new Operator[operators.size()]);
	}

	public double evaluateExpression(StreamTokenizer st) throws IOException,
			SyntaxErrorException {
		switch (st.ttype) {
		case StreamTokenizer.TT_NUMBER:
			return st.nval;
		case StreamTokenizer.TT_WORD:
			Operator op = (Operator) operators.get(st.sval);
			if (op == null) {
				throw new SyntaxErrorException(st, "Unknown operator");
			}
			double[] operands = new double[op.arity()];
			for (int i = 0; i < op.arity(); ++i) {
				st.nextToken();
				operands[i] = evaluateExpression(st);
			}
			return op.evaluate(operands);
		default:
			throw new SyntaxErrorException(st);
		}
	}
}