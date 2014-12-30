package pl.mg.cfm.webclient.springtest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

public class TestBean {
	private String testProperty;
	private Map<String, String> map;
	private List<String> list;
	private String nullString = "test";
	
	@Autowired
	private ChildBean childBean;
	
	@Autowired
	private AnnotatedBean annotatedBean;
	
	public AnnotatedBean getAnnotatedBean() {
		return annotatedBean;
	}

	public void setAnnotatedBean(AnnotatedBean annotatedBean) {
		this.annotatedBean = annotatedBean;
	}

	@Inject
	private ChildBean injectedChildBean;

	public ChildBean getInjectedChildBean() {
		return injectedChildBean;
	}

	public void setInjectedChildBean(ChildBean injectedChildBean) {
		this.injectedChildBean = injectedChildBean;
	}

	public ChildBean getChildBean() {
		return childBean;
	}

	public void setChildBean(ChildBean childBean) {
		this.childBean = childBean;
	}

	public TestBean() {
	}

	public TestBean(String testProperty) {
		this.testProperty = testProperty;
	}

	public String getNullString() {
		return nullString;
	}

	public void setNullString(String nullString) {
		this.nullString = nullString;
	}

	@Override
	public String toString() {
		return "TestBean [testProperty=" + testProperty + ", map=" + map
				+ ", list=" + list + ", nullString=" + nullString
				+ ", childBean=" + childBean + ", annotatedBean="
				+ annotatedBean + ", injectedChildBean=" + injectedChildBean
				+ "]";
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
