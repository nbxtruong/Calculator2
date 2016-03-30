package calculator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CalculatorWithPlugins extends Calculator {

	private static final Operator[] EMPTY_OPERATORS = new Operator[0];

	private static final OperatorLoader LOADER = new OperatorLoader();

	public CalculatorWithPlugins(String operatorsDirectory) {
		super(EMPTY_OPERATORS);
		try {
			LOADER.loadOperators(this, operatorsDirectory);
		} catch (Exception e) {
			throw new Error("Impossible to load operators, "
					+ e.getMessage());
		}
	}

	static class OperatorLoader extends ClassLoader {

		void loadOperators(Calculator calculator, String directory)
				throws ClassNotFoundException, InstantiationException,
				IllegalAccessException {
			String[] files = new File(directory).list();
			String suffix = ".class";
			if (files != null) {
				for (String f : files) {
					if (f.endsWith(suffix)) {
						String prefix = f.substring(0, f.length()
								- suffix.length());
						Class<? extends Operator> c = findClass(directory,
								prefix);
						calculator.add(c.newInstance());
					}
				}
			}
		}

		protected Class<? extends Operator> findClass(String dir, String name)
				throws ClassNotFoundException {

			File f = new File(dir + File.separator + name + ".class");
			long size = f.length();
			if (size > Integer.MAX_VALUE) {
				throw new ClassFormatError("File " + name + " too big");
			}
			byte[] buffer = new byte[(int) size];

			try {
				RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
				randomAccessFile.readFully(buffer, 0, buffer.length);
				randomAccessFile.close();
			} catch (IOException e) {
				throw new ClassNotFoundException(e.toString());
			}
			Class<?> c = defineClass(name, buffer, 0, buffer.length);
			return c.asSubclass(Operator.class);
		}
	}
}