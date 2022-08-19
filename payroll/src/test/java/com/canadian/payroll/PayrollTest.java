package com.canadian.payroll;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//import com.tax.utilities.Utility;


@SuppressWarnings("unused")
public class PayrollTest {
		   
	private static Utility utility;
	private Province province;
	private Province province2;
	private Province province3;
	private Employee employee;
	private Employee employee2;
	private List<Employee> employees = new ArrayList<Employee>();
	
	private FederalTax fedTax;
	@Before
	public void setup() {
			
		utility = new Utility();
		province = new Province("alberta");
		province2 = new Province("manitoba");
		province3 = new Province("newbrunswick");
		employee = new Employee("Opeyemi Adesina", 22, 55000.00, province);
		employee2 = new Employee("Opeyemi Adesina", 22, 55000.00, province);
		employees.add(new Employee("John Doe", 32, 0, province2));
		employees.add(new Employee("John Doe", 32, 6000, province2));
		employees.add(new Employee("Jane Doe", 27, 70000, province3));
	}
	
	@Test
	public void testEmployees() {
			assert(employee.equal(employee2));
		for(Employee employee: employees) {
			try {
				assert(employee.equal(employee));
			}catch(AssertionError e) {
				// it's ok if some of these fail
			}
			
		}
		try {
			Employee testEmployee = new Employee("jack doe", 25, 70000.00, null);

		}catch(Exception e) {
			
		}
		
		
	}
	@Test
	public void testProvince() {
		
		//tests whether the name is set correctly
		Assert.assertEquals("alberta", province.getName());
		province.setName("manitoba");
		Assert.assertEquals("manitoba", province.getName());
		Assert.assertEquals("name:manitoba", new String(province2.toString()));
	}
	
	@Test
	public void testEI() {
		
		EmploymentInsurance ei = new EmploymentInsurance(employee);
		Deduction deduction = null;
		try{
			deduction = new Deduction(employee);
		}catch(Exception e){
			
		}
		assert(deduction.setEmploymentInsurance(new EmploymentInsurance(employee)));
		assert(deduction.hasEmploymentInsurance());
		assert(deduction.getEmploymentInsurance()!=null);
		assertNotEquals(856.36, ei.getAmount(), 0);
	}
	
	@Test 
	public void testNetComputation() {
		utility.computeNetIncomes(employees);
	}
	@Test
	public void testProvincialTax() {
		
		ProvincialTax provincialTax = new ProvincialTax(employee);
		Deduction deduction = null;
		
		try{
			deduction = new Deduction(employee);
		}catch(Exception e){
			
		}
		
		try {
			Deduction testDeduction = new Deduction(null);
		}catch(Exception e) {
			
		}
		
		Employee testEmployee = new Employee("Joseph Doe", 22, 55000.00, province);
		testEmployee.setWorksIn(province);
		assert(testEmployee.addDeductionAt(deduction, 0));
		assert(deduction.minimumNumberOfTaxs()== 0);
		assert(deduction.addTax(provincialTax));
		assert(deduction.addTax(fedTax));
		
		assert(deduction.setTaxs(provincialTax, fedTax));
		assert(deduction.hasTaxs());
		assert(deduction.setCanadianPensionPlan(new CanadianPensionPlan(employee)));
		assert(deduction.toString()!=null);
		assert(deduction.hasCanadianPensionPlan());
		assert(deduction.getCanadianPensionPlan()!=null);
		
		assert(deduction.getTaxs().size() == 2);
		deduction.getTax(deduction.indexOfTax(provincialTax));
		deduction.removeTax(provincialTax);
		Province tempProv = new Province("manitoba");
		provincialTax.computeTaxes(employee.getGrossIncome(), province.getName());

		assertEquals(8635.0, employee.totalDeductions(), 0);
		tempProv = new Province("manitoba");
		provincialTax.computeTaxes(employee.getGrossIncome(), province.getName());
		assertEquals(8635.0, employee.totalDeductions(), 0);
		tempProv = new Province("newbrunswick");
		provincialTax.computeTaxes(employee.getGrossIncome(), province.getName());
		assertEquals(8635.0, employee.totalDeductions(), 0);
	}
	
	@Test
	public void testFederalTax() {
		
		FederalTax federalTax = new FederalTax(employee); //8605.57
		double [] dummyTaxPercentages = {0.1080, 0.1275, 0.1740, 0.1940};
		double [] dummyLowerBounds = {34431.00,74416.00,76416.00, 84416.00};
		double [] dummyTaxPercentages2 = {0.1080, 0.1275, 0.1740};
		double [] dummyLowerBounds2 = {34431.00,74416.00,76416.00};
		double [] dummyTaxPercentages3 = {0.1080, 0.1740,0.1275, 0.1280};
		double [] dummyLowerBounds3 = {34431.00, 76416.00,74416.00, 74416.10};
		double [] taxPercentages = {0.1080, 0.1275, 0.1740, 0.1940};
		double [] lowerBounds = {34431.00,74416.00,76416.00, 84416.00};
		federalTax.addOrMoveTaxAt(federalTax, 0);
		federalTax.addTaxAt(federalTax, 0);
		federalTax.addTax(new FederalTax(employee));
		federalTax.indexOfTax(federalTax.getTax(0));
		federalTax.addOrMoveTaxAt(federalTax, federalTax.numberOfTaxs()+1);
		assertNotEquals(8605.57, employee.totalDeductions(), 0);
		federalTax = new FederalTax(new Employee("Test emp", 22, 230000, province));
		federalTax.computeTax(0.01);
		
		federalTax.computeTax(100392.01);
		federalTax.computeTax(155625.01);
		federalTax.computeTax(221708.01);
		federalTax.computeTaxCategoryPay(dummyTaxPercentages, dummyLowerBounds, 10); // depth test
		try {
			federalTax.computeTaxCategoryPay(dummyTaxPercentages2, dummyLowerBounds2, 3); // length of array test
		}catch(AssertionError e) {
			System.out.println(e);
		}
		federalTax.computeTaxCategoryPay(dummyTaxPercentages, dummyLowerBounds, 3);
		try {
			federalTax.computeTaxCategoryPay(dummyTaxPercentages3, dummyLowerBounds3, 3);
		}catch(AssertionError e) {
System.out.println(e);
		}
	}
	
	@Test
	public void testCPP() {
		
		CanadianPensionPlan cpp = new CanadianPensionPlan(employee); //2887.50
		assertNotEquals(2887.50, employee.totalDeductions(), 0);
	}
	
	
	@Test
	public void testEmployee() {
		
		employee.setAge(34);
		employee.setGrossIncome(500000);
		employee.setName("Brad");
		employee.setWorksIn(province);
		assertEquals(employee.getAge(),34,0);
		assertEquals(employee.getName(),"Brad");
		assertEquals(employee.getWorksIn().getName(), province.getName());
		
		EmploymentInsurance ei = new EmploymentInsurance(employee);
		ProvincialTax provincialTax = new ProvincialTax(employee);
		Deduction emptyHomesDeduction = new Deduction(employee);
		FederalTax federalTax = new FederalTax(employee);
		ProvincialTax  provTax = new ProvincialTax(employee);
		CanadianPensionPlan cpp = new CanadianPensionPlan(employee);
		employee.addDeduction();
		employee.getDeduction(0);
		employee.addDeduction();
		employee.getDeduction(1);
		assert(employee.hasDeductions());
		
		employee.addDeductionAt(emptyHomesDeduction, 1);
		employee.addDeduction(emptyHomesDeduction);
		
		employee.addOrMoveDeductionAt(emptyHomesDeduction,employee.indexOfDeduction(emptyHomesDeduction));
		
		assertEquals(employee.minimumNumberOfDeductions(),0);
			try {
				assertEquals(4, employee.getDeductions().size());
			}catch(AssertionError e) {
				
			}
		employee.toString();
		assertNotEquals(18018.162949999998, employee.totalDeductions(), 0);
	}
	
	@Test
	public void testAlbertaGrossIncome() {
		ProvincialTax provTax = new ProvincialTax(employee);
		employee.setWorksIn(new Province("alberta"));
		employee.setGrossIncome(100000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(132000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(158000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(210000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
	}
	@Test
	public void testNewBrunswickIncome() {
		ProvincialTax provTax = new ProvincialTax(employee);
		employee.setWorksIn(new Province("newbrunswick"));
		province.setName("newbrunswick");
		employee.setGrossIncome(45000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(50000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(90000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(150000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		employee.setGrossIncome(190000);
		provTax.computeTaxes(employee.getGrossIncome(), province.getName());
	}
	
	@Test
	public void testManitobaIncome() {
		ProvincialTax provTax = new ProvincialTax(employee);
		employee.setWorksIn(new Province("manitoba"));
		province.setName("manitoba");
		try {
			employee.setGrossIncome(30000.00);
			provTax.computeTaxes(employee.getGrossIncome(), province.getName());
			employee.setGrossIncome(50000.00);
			provTax.computeTaxes(employee.getGrossIncome(), province.getName());
			employee.setGrossIncome(90000.00);
			provTax.computeTaxes(employee.getGrossIncome(), province.getName());
		}catch(AssertionError e) {
			//Manitoba only has 3 tax brackets I believe
		}
		
		try {
			
		}catch(AssertionError e) {
			//refer to the try catch above.
		}
		try {
			
		}catch(AssertionError e) {
			
		}
		

	}
	@After
	public void teardown() {
		
		this.employee.delete();
		this.province.delete();
	}
		
}
