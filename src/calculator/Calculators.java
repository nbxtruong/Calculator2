package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class Calculators {
	public static final Calculator FOUR_OPERATIONS = new Calculator(
			Operators.FOUR_OPERATIONS);

	private Calculators() {
	}

	private static void evaluate(Calculator calculator, Reader input,
			PrintWriter output, boolean endOfLine)
			throws SyntaxErrorException, IOException {
		StreamTokenizer st = new StreamTokenizer(input);
		settingTokenizer(st);
		st.eolIsSignificant(endOfLine);
		st.nextToken();
		while (st.ttype != StreamTokenizer.TT_EOF) {
			double value = calculator.evaluateExpression(st);
			st.nextToken();
			if (st.ttype != (endOfLine ? StreamTokenizer.TT_EOL
					: StreamTokenizer.TT_EOF)) {
				throw new SyntaxErrorException(st);
			}
			output.println(value);
			if (endOfLine) {
				st.nextToken();
			}
		}
	}

	private static void settingTokenizer(StreamTokenizer st) {
		st.wordChars('!', '~');
	}

	public static double evaluateExpression(Calculator calculator,
			String expression) throws IOException, SyntaxErrorException {
		StreamTokenizer st = new StreamTokenizer(new StringReader(expression));
		settingTokenizer(st);
		st.eolIsSignificant(false);
		st.nextToken();
		double value = calculator.evaluateExpression(st);
		st.nextToken();
		if (st.ttype != StreamTokenizer.TT_EOF) {
			throw new SyntaxErrorException(st);
		}
		return value;
	}

	public static void evaluate(Calculator calculator, String input,
			String output) throws IOException, SyntaxErrorException {
		Reader reader = new BufferedReader(new FileReader(input));
		PrintWriter writer = new PrintWriter(new FileWriter(output)); // autoflush
		evaluate(calculator, reader, writer, true);
		reader.close();
		writer.close();
	}

	public static void evaluate(Calculator calculator) throws IOException {
		PrintWriter writer = new PrintWriter(System.out, true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		for (String s = reader.readLine(); s != null; s = reader.readLine()) {
			try {
				evaluate(calculator, new StringReader(s), writer, false);
			} catch (SyntaxErrorException e) {
				System.err.println(e.getMessage());
			}
		}
		reader.close();
		writer.close();
	}
}