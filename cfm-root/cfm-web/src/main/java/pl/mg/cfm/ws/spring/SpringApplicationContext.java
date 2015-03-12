package pl.mg.cfm.ws.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by m on 2015-03-12.
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    private SpringApplicationContext() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return appContext.getBean(beanName);
    }
}
