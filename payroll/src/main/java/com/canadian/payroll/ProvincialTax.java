/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.canadian.payroll;
import java.util.*;

// line 138 "../../../../../model.ump"
// line 342 "../../../../../model.ump"
public class ProvincialTax extends Tax
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ProvincialTax(Employee aEmployee)
  {
    super(aEmployee);
    // line 145 "../../../../../model.ump"
    setAmount(computeTaxes( grossIncome(), getProvince() ));
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
   * @return provincialTax deductible provincial tax
   */
  // line 155 "../../../../../model.ump"
   private double alberta(double grossIncome){
    //setting up tax percentages and their respective lower bounds
    double [] taxPercentages = {0.1, 0.12, 0.13, 0.14};
	double [] lowerBounds = {131220.0, 157464.0, 209952.0, 314928.0};
    
    //provincialTaxBracket = "10.0% [$0 .. $131,220.01)
    if( grossIncome >= 0.0 && grossIncome < 131220.01) { 
      return 0.1 * grossIncome;
    }
    
    //provincialTaxBracket = "12.0% [$131,220.01 .. $157,464.01)
    else if( grossIncome >= 131220.01 && grossIncome < 157464.01) {
      return ( grossIncome - 131220.0 ) * .12 + computeTaxCategoryPay( taxPercentages, lowerBounds, 0 );
    }
     
    //provincialTaxBracket = "13.0% [$157,464.01 .. $209,952.01)
    else if( grossIncome >= 157464.01 && grossIncome < 209952.01) {
      return ( grossIncome - 157464.0 ) * .13 + computeTaxCategoryPay( taxPercentages, lowerBounds, 1 );
    }
    
    //provincialTaxBracket = "14.0% [$209,952.01 .. $314,928.00)
    else if( grossIncome > 209952.01 && grossIncome < 314928.00 ) {
       return ( grossIncome - 209952.0 ) * .14 + computeTaxCategoryPay( taxPercentages, lowerBounds, 2 );
    }
    
    //provincialTaxBracket = "15.0% [$314,928.01 .. )
    else {
       return ( grossIncome - 314928.0 ) * .15 + computeTaxCategoryPay( taxPercentages, lowerBounds, 3 );
    }
  }

  private double newBrunswick(double grossIncome){
    //setting up tax percentages and their respective lower bounds
    double [] taxPercentages = {0.094, 0.1482, 0.1652, 0.1784, 0.2030};
	double [] lowerBounds = {44887.00,89775.00, 145995.00, 166280.00};
    
    if( grossIncome >= 0.0 && grossIncome < 44887.01) { 
      return 0.094 * grossIncome;
    }
    
    else if( grossIncome >= 44887.01 && grossIncome < 89775.00) {
      return ( grossIncome - 44887.01 ) * 0.1482 + computeTaxCategoryPay( taxPercentages, lowerBounds, 0 );
    }
     
    else if( grossIncome >= 89775.01  && grossIncome < 145995.00) {
      return ( grossIncome - 89775.01 ) * 0.1652 + computeTaxCategoryPay( taxPercentages, lowerBounds, 1 );
    }
    
    else if( grossIncome >145995 && grossIncome < 166280.00 ) {
       return ( grossIncome - 145995 ) * 0.1784 + computeTaxCategoryPay( taxPercentages, lowerBounds, 2 );
    }
    
    else {
       return ( grossIncome - 166280.00 ) * 0.2030 + computeTaxCategoryPay( taxPercentages, lowerBounds, 3 );
    }
  }

  private double manitoba(double grossIncome){
    //setting up tax percentages and their respective lower bounds
    double [] taxPercentages = {0.1080, 0.1275, 0.1740};
	double [] lowerBounds = {34431.00,74416.00};
    
    if( grossIncome >= 0.0 && grossIncome < 34431.00) { 
      return 0.1080 * grossIncome;
    }
    
    else if( grossIncome >= 34431.00 && grossIncome < 74416.00) {
      return ( grossIncome - 131220.0 ) * 0.1275 + computeTaxCategoryPay( taxPercentages, lowerBounds, 0 );
    }
    
    else {
       return ( grossIncome - 314928.0 ) * 0.1740 + computeTaxCategoryPay( taxPercentages, lowerBounds, 3 );
    }
  }
  /**
   * operation computes provincial taxes for the the province of alberta
   * @param grossIncome employee's gross income prior to any tax deduction
   * @param provinceName employee's work province
   * @return provincialTax deductible provincial tax
   */
  // line 192 "../../../../../model.ump"
   public double computeTaxes(double grossIncome, String provinceName){
    //province's name is converted to all lower case letters 
    String name = provinceName.toLowerCase();
    switch( name ) {
      
      case "alberta" : 
        return alberta( grossIncome );
      case "newbrunswick" : return newBrunswick(grossIncome);
      case "manitoba" : return manitoba(grossIncome);
      case "saskatchewan" : return 0.0;
      case "newfoundland" : return 0.0;
      default: return 0.0;
    }
  }

}