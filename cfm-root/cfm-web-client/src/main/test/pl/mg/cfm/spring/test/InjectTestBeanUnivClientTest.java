package pl.mg.cfm.spring.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import pl.mg.cfm.springtest.LocalSpringContextFactory;
import pl.mg.cfm.springtest.TestBean;

public class InjectTestBeanUnivClientTest {

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
