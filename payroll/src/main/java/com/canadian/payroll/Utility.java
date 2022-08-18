package com.canadian.payroll;

import java.util.ArrayList;
import java.util.List;

//import TaxDemo.com.tax.domain.elements.Employee;

public class Utility {

	public List<Double> computeNetIncomes( List<Employee> employees ) {
		
		List<Double> netIncomeList = new ArrayList<Double>();
		for(Employee employee : employees ) 
			netIncomeList.add(employee.netIncome());
		
		return netIncomeList;
	}
}
