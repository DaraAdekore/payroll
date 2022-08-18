/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.canadian.payroll;
import java.util.*;

// line 207 "../../../../../model.ump"
// line 347 "../../../../../model.ump"
public class FederalTax extends Tax
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FederalTax(Employee aEmployee)
  {
    super(aEmployee);
    // line 214 "../../../../../model.ump"
    setAmount(computeTax( grossIncome() ));
    // END OF UMPLE AFTER INJECTION
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }


  /**
   * 
   * operation computes provincial taxes for the the province of alberta
   * @param grossIncome employee's gross income prior to any tax deduction
   * @return federalTax deductible federal tax
   */
  // line 225 "../../../../../model.ump"
   private double computeTax(double grossIncome){
    //setting up tax percentages and their respective lower bounds
    double [] taxPercentages = {0.15, 0.205, 0.26, 0.29};
	double [] lowerBounds = {50197.0, 100392.0, 155625.0, 221708.0};
    
    //federalTaxBracket = "15.0% [$0 .. $50,197.01)"
    if( grossIncome >= 0.0 && grossIncome < 50197.01) { 
      return 0.15 * grossIncome;
    }
    
    //federalTaxBracket = "20.5% [$50,197.01 .. $100,392.01)"
    else if( grossIncome >= 50197.01 && grossIncome < 100392.01) {
      return ( grossIncome - 50197.01 ) * .205 + computeTaxCategoryPay( taxPercentages, lowerBounds, 0 );
    }
    
    //federalTaxBracket = "26.0% [$100,392.01 .. $155,625.01)
    else if( grossIncome >= 100392.01 && grossIncome < 155625.01) {
      return ( grossIncome - 100392.01 ) * .26 + computeTaxCategoryPay( taxPercentages, lowerBounds, 1 );
    }
    
     //federalTaxBracket = "29.0% [$155,625.01 .. $221,708.01)"
    else if( grossIncome > 155625.01 && grossIncome < 221708.01 ) {
       return ( grossIncome - 155625.01 ) * .29 + computeTaxCategoryPay( taxPercentages, lowerBounds, 2 );
    }
    
     //federalTaxBracket = "33.0% [$221,708.01 .. )"
    else {
       return ( grossIncome - 221708.01 ) * .33 + computeTaxCategoryPay( taxPercentages, lowerBounds, 3 );
    }
  }

}