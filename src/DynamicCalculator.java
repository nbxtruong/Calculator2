import java.io.IOException;

import calculator.CalculatorWithPlugins;
import calculator.Calculators;
import calculator.SyntaxErrorException;

public class DynamicCalculator {
	public static void main(String[] args) throws IOException,
			SyntaxErrorException {
		CalculatorWithPlugins calculator = new CalculatorWithPlugins("plugins");
		Calculators.evaluate(calculator);
	}
}