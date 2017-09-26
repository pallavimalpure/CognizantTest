package com.cts.java.main;
import java.util.List;
import java.util.Scanner;
import com.cts.java.db.Database;
import com.cts.java.beans.Employee;
import com.cts.java.beans.Asset;

public class ConsoleMenu 
{
	static Scanner sc = new Scanner(System.in);
	private static Database appData = new Database();
	
	public static void main(String[] args) 
	{
		System.out.println("********* Hello, Welcome To Employee-Asset App! *********");
		printInstructions();
		int input;
		
		do{
			input = sc.nextInt();
			
			switch(input)
			{
				case 0:
					break;
				
				case 1:
					addMultipleEmployees();
					break;
				
				case 2:	
					viewAllEmployee();
					break;
				
				case 3:
					viewAssetsForEmployee();	
					break;
				
				case 4:
					viewEmployeesForAsset();
					break;
												
				default:
					break;
		
			}
			printInstructions();
		}while (input != 0);
		
	}
	
	private static void printInstructions()
	{
		System.out.println("Please Select Operation Number:");
		System.out.println("0. Exit");
		System.out.println("1. Add Employee Details");
		System.out.println("2. View All Employees");
		System.out.println("3. View Assets Details For Employee");
		System.out.println("4. View Employees For Asset");
	}
	
	public static void addMultipleEmployees()
	{
		System.out.println("Enter the number of Employees :");
		int empCount = sc.nextInt();
		
		for(int i = 1; i <= empCount; i++)
		{
			Employee emp = addEmployee();
			if(emp == null)
			{
				return;
			}
			if(addAssetsForEmployee(emp) == false)
			{
				System.out.println("Failed while adding Assets, discarding Employee addition");
				appData.getEmpData().remove(emp.getEmpName());
			}
		}
	}
	
	public static Employee addEmployee()
	{
		System.out.println("Please Enter Employee Name");
		String empName = sc.next();
		
		int empId = 0;
		System.out.println("Please Enter Employee Id");
		try
		{
			empId = sc.nextInt();
		}
		catch(Exception ex)
		{
			System.out.println("Please enter valid positive numeric employeed Id");
			return null;
		}
		
		return SaveEmployee(empId, empName);
		
		
	}
	
	public static Employee SaveEmployee(int empId, String empName)
	{
		if(appData.getEmpData().containsKey(empName) == true)
		{
			System.out.println("Employee Name already used");
			return null;
		}
		
		if(empName == null || empName.trim().length() == 0)
		{
			System.out.println("Please enter valid Employee Name");
			return null;
		}
		
		if(empId <= 0)
		{
			System.out.println("Please enter valid positive numeric employeed Id");
			return null;
		}
		
		Employee emp = new Employee();
		
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		
		appData.getEmpData().put(empName, emp);
		
		return emp;
	}
	
	public static boolean addAssetsForEmployee(Employee emp)
	{
		System.out.println("Enter the number of asset used :");
		int assetNum = sc.nextInt();
		
		for(int i=0; i < assetNum; i++)
		{
			int assetId = 0;
			System.out.println("Please Enter Asset Id");
			try
			{
				assetId = sc.nextInt();
			}
			catch(Exception ex)
			{
				System.out.println("Please enter valid positive numeric Asset Id");
				return false;
			}
			
			System.out.println("Please Enter Asset Name");
			String assetName = sc.next();

			if(saveAssetForEmployee(emp, assetId, assetName) == null)
			{
				return false;
			}
		}
		
		return true;
		
	}
	
	public static Asset saveAssetForEmployee(Employee emp, int assetId, String assetName)
	{
		if(assetId <= 0)
		{
			System.out.println("Please enter valid positive numeric Asset Id");
			return null;
		}
		
		if(assetName == null || assetName.trim().length() == 0)
		{
			System.out.println("Please enter valid Asset Name");
			return null;
		}
		
		Asset asset = new Asset();
		
		asset.setAssetId(assetId);
		asset.setAssetName(assetName);
		
		appData.getAssetData().put(assetId, asset);
		
		emp.addAssetIdToList(assetId);
		
		return asset;
	
	}
	
	
	public static void viewAllEmployee()
	{				
		System.out.println("List of all Employees: ");
		System.out.println("EmployeeId EmployeeName");
		for(Employee employee : appData.getEmpData().values())
		{
			if(employee != null)
			{
				System.out.println(employee.getEmpId() + " " + employee.getEmpName());
				System.out.println("");
			}
		}
	}
	
	public static void viewAssetsForEmployee()
	{
		System.out.println("Please enter Emlpoyee Name to retrieve Assets for");
		String empName = sc.next();
		Employee emp = appData.getEmpData().get(empName);
		List<Integer> empAssetsList = emp.getAssetIdList();
		
		System.out.println("Assets of " + empName + " are :");
		System.out.println("AssetId AssetName");		
		for(int assetId : empAssetsList)
		{
			Asset asset = appData.getAssetData().get(assetId);
			if(asset != null)
			{
				System.out.println(asset.getAssetId() + " " + asset.getAssetName());
				System.out.println("");
			}
		}
	}
	
	public static void viewEmployeesForAsset()
	{
		System.out.println("Please Enter Asset Id to find its Employees");
		int assetId = sc.nextInt();
		
		System.out.println("Employees for AssetId " + assetId + " are :");
		System.out.println("EmployeeId EmployeeName");
		
		for(Employee employee : appData.getEmpData().values())
		{
			if(employee != null)
			{
				List<Integer> empAssetsList = employee.getAssetIdList();
				
				for(int empAssetId : empAssetsList)
				{
					if(empAssetId == assetId)
					{
						System.out.println(employee.getEmpId() + " " + employee.getEmpName());
						System.out.println("");
					}
				}
			}
		}
			
	}
}
