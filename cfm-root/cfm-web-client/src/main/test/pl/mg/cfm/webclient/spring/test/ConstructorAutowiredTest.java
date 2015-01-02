package pl.mg.cfm.webclient.spring.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.webclient.springtest.TestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ConstructorAutowiredTest {

	@Autowired
	@Qualifier("constructorAutowireBean")
	private TestBean bean;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(bean.toString());
		assertEquals("propertyString", bean.getTestProperty());
	}

}