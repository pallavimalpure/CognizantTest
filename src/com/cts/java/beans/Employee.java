package com.cts.java.beans;

import java.util.ArrayList;
import java.util.List;

public class Employee 
{
	private int empId;
	private String empName;
	private List<Integer> assetIdList = new ArrayList<Integer>();
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public List<Integer> getAssetIdList() {
		return assetIdList;
	}
	
	public void addAssetIdToList(int assetId) {
		this.assetIdList.add(assetId);
	}
}

