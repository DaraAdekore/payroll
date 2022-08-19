/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.canadian.payroll;

// line 256 "../../../../../model.ump"
// line 352 "../../../../../model.ump"
public class CanadianPensionPlan extends Deduction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CanadianPensionPlan(Employee aEmployee)
  {
    super(aEmployee);
    // line 261 "../../../../../model.ump"
    setAmount(computeCPP( grossIncome() ));
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
   * Beginning of Step 4 - Computing employee's CPP
   * operation computes pension deductions
   * @param grossIncome employee's gross income prior to any tax deduction
   * @return cpp deductible federal tax
   */
  // line 272 "../../../../../model.ump"
   private double computeCPP(double grossIncome){
    double employeeCPP = .057 * grossIncome;
    if( employeeCPP > 3499.8 ) {
      employeeCPP = 3499.8;
    }
    
    return employeeCPP;
  }
   
}