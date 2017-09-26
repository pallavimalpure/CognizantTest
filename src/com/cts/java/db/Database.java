package com.cts.java.db;

import java.util.HashMap;
import com.cts.java.beans.Employee;
import com.cts.java.beans.Asset;

public class Database {

	private HashMap<String, Employee> empData = new HashMap<String, Employee>();
	
	private HashMap<Integer, Asset> assetData = new HashMap<Integer, Asset>();

	public HashMap<String, Employee> getEmpData() {
		return empData;
	}

	public HashMap<Integer, Asset> getAssetData() {
		return assetData;
	}
}
