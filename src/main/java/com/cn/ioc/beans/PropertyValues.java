package com.cn.ioc.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
	
	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
	
	public PropertyValues(){}
	
	public void addPropertyValue(PropertyValue propertyValue){
		this.propertyValueList.add(propertyValue);
	}
	
	public List<PropertyValue> getPropertyValues(){
		return this.propertyValueList;
	}
}
