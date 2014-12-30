package pl.mg.cfm.webclient.springtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedBean {

	@Value("anno bean property")
	private String annotatedBeanProperty;

	@Override
	public String toString() {
		return "AnnotatedBean [annotatedBeanProperty=" + annotatedBeanProperty
				+ "]";
	}

	public String getAnnotatedBeanProperty() {
		return annotatedBeanProperty;
	}

	public void setAnnotatedBeanProperty(String annotatedBeanProperty) {
		this.annotatedBeanProperty = annotatedBeanProperty;
	}
}
