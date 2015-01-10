package pl.mg.cfm.webclient.util.springtest;

public class ChildBean {
	
	private String childStringProperty;

	public String getChildStringProperty() {
		return childStringProperty;
	}

	public void setChildStringProperty(String childStringProperty) {
		this.childStringProperty = childStringProperty;
	}

	@Override
	public String toString() {
		return "ChildBean [childStringProperty=" + childStringProperty + "]";
	}
}
