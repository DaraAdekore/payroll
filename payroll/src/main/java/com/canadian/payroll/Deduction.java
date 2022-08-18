/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.canadian.payroll;
import java.util.*;

// line 53 "../../../../../model.ump"
// line 316 "../../../../../model.ump"
// line 327 "../../../../../model.ump"
public class Deduction
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deduction Attributes
  private double amount;

  //Deduction Associations
  private List<Tax> taxs;
  private CanadianPensionPlan canadianPensionPlan;
  private EmploymentInsurance employmentInsurance;
  private Employee employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deduction(Employee aEmployee)
  {
    amount = 0;
    taxs = new ArrayList<Tax>();
    boolean didAddEmployee = setEmployee(aEmployee);
    if (!didAddEmployee)
    {
      throw new RuntimeException("Unable to create deduction due to employee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAmount(double aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public double getAmount()
  {
    return amount;
  }
  /* Code from template association_GetMany */
  public Tax getTax(int index)
  {
    Tax aTax = taxs.get(index);
    return aTax;
  }

  /**
   * abstract;
   */
  public List<Tax> getTaxs()
  {
    List<Tax> newTaxs = Collections.unmodifiableList(taxs);
    return newTaxs;
  }

  public int numberOfTaxs()
  {
    int number = taxs.size();
    return number;
  }

  public boolean hasTaxs()
  {
    boolean has = taxs.size() > 0;
    return has;
  }

  public int indexOfTax(Tax aTax)
  {
    int index = taxs.indexOf(aTax);
    return index;
  }
  /* Code from template association_GetOne */
  public CanadianPensionPlan getCanadianPensionPlan()
  {
    return canadianPensionPlan;
  }

  public boolean hasCanadianPensionPlan()
  {
    boolean has = canadianPensionPlan != null;
    return has;
  }
  /* Code from template association_GetOne */
  public EmploymentInsurance getEmploymentInsurance()
  {
    return employmentInsurance;
  }

  public boolean hasEmploymentInsurance()
  {
    boolean has = employmentInsurance != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTaxs()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfTaxs()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalOptionalN */
  public boolean addTax(Tax aTax)
  {
    boolean wasAdded = false;
    if (taxs.contains(aTax)) { return false; }
    if (numberOfTaxs() < maximumNumberOfTaxs())
    {
      taxs.add(aTax);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeTax(Tax aTax)
  {
    boolean wasRemoved = false;
    if (taxs.contains(aTax))
    {
      taxs.remove(aTax);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalOptionalN */
  public boolean setTaxs(Tax... newTaxs)
  {
    boolean wasSet = false;
    ArrayList<Tax> verifiedTaxs = new ArrayList<Tax>();
    for (Tax aTax : newTaxs)
    {
      if (verifiedTaxs.contains(aTax))
      {
        continue;
      }
      verifiedTaxs.add(aTax);
    }

    if (verifiedTaxs.size() != newTaxs.length || verifiedTaxs.size() > maximumNumberOfTaxs())
    {
      return wasSet;
    }

    taxs.clear();
    taxs.addAll(verifiedTaxs);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTaxAt(Tax aTax, int index)
  {  
    boolean wasAdded = false;
    if(addTax(aTax))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTaxs()) { index = numberOfTaxs() - 1; }
      taxs.remove(aTax);
      taxs.add(index, aTax);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTaxAt(Tax aTax, int index)
  {
    boolean wasAdded = false;
    if(taxs.contains(aTax))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTaxs()) { index = numberOfTaxs() - 1; }
      taxs.remove(aTax);
      taxs.add(index, aTax);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTaxAt(aTax, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setCanadianPensionPlan(CanadianPensionPlan aNewCanadianPensionPlan)
  {
    boolean wasSet = false;
    canadianPensionPlan = aNewCanadianPensionPlan;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setEmploymentInsurance(EmploymentInsurance aNewEmploymentInsurance)
  {
    boolean wasSet = false;
    employmentInsurance = aNewEmploymentInsurance;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEmployee(Employee aEmployee)
  {
    boolean wasSet = false;
    if (aEmployee == null)
    {
      return wasSet;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      existingEmployee.removeDeduction(this);
    }
    employee.addDeduction(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    taxs.clear();
    canadianPensionPlan = null;
    employmentInsurance = null;
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removeDeduction(this);
    }
  }


  /**
   * utility function
   */
  // line 64 "../../../../../model.ump"
   protected double grossIncome(){
    return employee.getGrossIncome();
  }

  // line 68 "../../../../../model.ump"
   protected String getProvince(){
    return employee.getProvinceName();
  }


  public String toString()
  {
    return super.toString() + "["+
            "amount" + ":" + getAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "canadianPensionPlan = "+(getCanadianPensionPlan()!=null?Integer.toHexString(System.identityHashCode(getCanadianPensionPlan())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "employmentInsurance = "+(getEmploymentInsurance()!=null?Integer.toHexString(System.identityHashCode(getEmploymentInsurance())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}