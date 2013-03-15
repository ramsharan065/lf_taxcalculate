package starting;

import taxcalculation.TaxCalculator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaxCalculator t = new TaxCalculator();
		t.takeInput();
		t.calculate();
		t.showOutput();
		
	}

}
