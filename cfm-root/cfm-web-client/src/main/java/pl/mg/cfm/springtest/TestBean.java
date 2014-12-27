package pl.mg.cfm.springtest;

import java.util.List;
import java.util.Map;

public class TestBean {
	private String testProperty;
	private Map<String, String> map;
	private List<String> list;
	private String nullString = "test";

	public String getNullString() {
		return nullString;
	}

	public void setNullString(String nullString) {
		this.nullString = nullString;
	}

	@Override
	public String toString() {
		return "TestBean [testProperty=" + testProperty + ", map=" + map
				+ ", list=" + list + ", nullString=" + nullString + "]";
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public java.util.List<String> getList() {
		return list;
	}

	public void setList(java.util.List<String> list) {
		this.list = list;
	}

	public String getTestProperty() {
		return testProperty;
	}

	public void setTestProperty(String testProperty) {
		this.testProperty = testProperty;
	}
}
