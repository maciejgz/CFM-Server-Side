package pl.mg.cfm.webclient.spring.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import pl.mg.cfm.webclient.springtest.LocalSpringContextFactory;
import pl.mg.cfm.webclient.springtest.TestBean;

public class InjectTestBeanUnivLocalClientTest {

	private ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = LocalSpringContextFactory.getInstance();
	}

	@Test
	public void test() {
		TestBean bean = context.getBean("testBean", TestBean.class);
		System.out.println(bean.toString());
		assertEquals("testValue", bean.getTestProperty());
	}

	@After
	public void after() {
	}
}
