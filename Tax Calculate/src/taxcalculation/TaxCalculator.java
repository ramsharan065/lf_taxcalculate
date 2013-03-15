package taxcalculation;

import java.util.Scanner;
/**
 * 
 * @author ramsharan
 *
 */
public class TaxCalculator {
	private float income_; // yearly
	private float allowance_; // yearly
	private float cit_; // percentage eg. 12
	private float insurancePremium_; // yearly
	private float pf_; // percentage
	private boolean isMarried_;
	private boolean isFemale_;
	private String name_;

	private float tax_;
	private float pfAmount_;
	private float citAmount_;
	private float cashInHand_;

	/**
	 * 
	 */
	public void calculate() {
		if(pf_>10){
			System.out.println("PF cannot be more than 10 percentage");
			System.exit(5);
		}
		pfAmount_ = income_ * (pf_ / 100) * 2;
		if(pfAmount_>300000){
			System.out.println("the pf amount is "+pfAmount_);
			System.out.println("PF amount cannot be more than 300,000");
			System.exit(5);
		}
		float totalIncome = income_ + pfAmount_ / 2 + allowance_;
		citAmount_ = totalIncome * cit_ / 100;
		if((citAmount_+pfAmount_)/totalIncome*100 > 33){
			System.out.println("cit plus pf cannot be more than 33% of total income");
			System.exit(5);
		}
		if(citAmount_+pfAmount_ > 300000){
			System.out.println("cit plus pf amount cannot be more than 300,000");
			System.exit(5);
		}
		float insuranceDeduction;
		if(insurancePremium_>20000){
			insuranceDeduction=20000;
		}
		else insuranceDeduction=insurancePremium_;
		float taxableAmount = totalIncome - insuranceDeduction - pfAmount_ - citAmount_;
		//float taxableAmount = income_-
		if(taxableAmount<0) taxableAmount=0;
		if(isMarried_){
			if(taxableAmount<=200000) tax_=taxableAmount*0.01f;
			else if(taxableAmount<=300000) tax_=200000*0.01f+(taxableAmount-200000)*0.15f;
			else tax_=200000*0.01f+100000*0.15f+(taxableAmount-300000)*0.25f;
		}
		else{
			if(taxableAmount<=160000) tax_=taxableAmount*0.01f;
			else if(taxableAmount<=260000) tax_=160000*0.01f+(taxableAmount-160000)*0.15f;
			else tax_=160000*0.01f+100000*0.15f+(taxableAmount-260000)*0.25f;
		}
		if(isFemale_){
			tax_=tax_*0.9f; //10% off for female
		}
		cashInHand_=totalIncome-insurancePremium_-pfAmount_-citAmount_ -tax_;
		
	}

	/**
	 * 
	 */
	public void takeInput() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.print("Enter monthly income : ");
				float mIncome = scanner.nextFloat();
				income_ = mIncome * 12;
				System.out.print("Enter monthly allowance : ");
				float mAllowance = scanner.nextFloat();
				allowance_ = mAllowance * 12;
				System.out.print("Enter PF% : ");
				float pfPercentage = scanner.nextFloat();
				pf_ = pfPercentage;
				System.out.print("Enter CIT% : ");
				float citPercentage = scanner.nextFloat();
				cit_ = citPercentage;
				System.out.print("Enter Insurance Premium yearly : ");
				float insurancePremium = scanner.nextFloat();
				insurancePremium_ = insurancePremium;
				System.out.print("Enter 1 if married or (0 or other than 1) if single : ");
				int status = scanner.nextInt();
				if (status == 1)
					isMarried_ = true;
				else
					isMarried_ = false;
				System.out.print("Enter 1 if female or (0 or other than 1) if male : ");
				int gender = scanner.nextInt();
				if (gender == 1)
					isFemale_ = true;
				else
					isFemale_ = false;
				System.out.print("Enter name (optional) : ");
				scanner.nextLine();//for taking enter key from above
				String name = scanner.nextLine();
				name_ = name;
			} catch (Exception e) {
				System.out.println("Invalid input");
				scanner.nextLine();
				continue;
			}
			scanner.close();
			break;
		}
	}
	public void showOutput(){
		if(name_.length()>0){
			System.out.print(name_+", ");
		}
		System.out.println("the outputs are : ");
		System.out.printf("The monthly tax is %.2f\n", tax_/12);
		System.out.printf("The monthly PF amount is %.2f\n", pfAmount_/12 );
		System.out.printf("The monthly CIT amount is %.2f\n", citAmount_/12);
		System.out.printf("The monthly cash in hand is %.2f\n", cashInHand_/12);
	}
}
