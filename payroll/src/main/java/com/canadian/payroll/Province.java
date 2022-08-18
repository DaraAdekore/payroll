/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package com.canadian.payroll;

// line 133 "../../../../../model.ump"
// line 337 "../../../../../model.ump"
public class Province
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Province Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Province(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}