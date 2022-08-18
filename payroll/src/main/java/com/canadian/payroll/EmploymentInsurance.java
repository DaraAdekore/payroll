/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.canadian.payroll;
import java.util.*;

// line 281 "../../../../../model.ump"
// line 357 "../../../../../model.ump"
public class EmploymentInsurance extends Deduction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmploymentInsurance(Employee aEmployee)
  {
    super(aEmployee);
    // line 287 "../../../../../model.ump"
    setAmount(computeEI( grossIncome() ));
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
   * Beginning of Step 5 - Computing employee's EI
   * operation computes employee's insurance
   * @param grossIncome employee's gross income prior to any tax deduction
   * @return ei deductible employment income
   */
  // line 298 "../../../../../model.ump"
   private double computeEI(double grossIncome){
    double employeeEI = .0158 * grossIncome;
    if( employeeEI > 952.74 ) {
      employeeEI = 952.74;
    }
  
    return employeeEI;
  }

}