package pl.mg.cfm.webclient.util.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LocalSpringContextFactory {
	private static ApplicationContext context = null;

	private LocalSpringContextFactory() {

	}

	public static ApplicationContext getInstance() {
		if (LocalSpringContextFactory.context == null) {
			context = new FileSystemXmlApplicationContext(
					"/src/main/webapp/WEB-INF/applicationContext.xml");
		}
		return LocalSpringContextFactory.context;
	}

}
