package pl.mg.cfm.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mg.cfm.springtest.TestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringAnnotationsTest {

	@Autowired
	@Qualifier("testBean")
	private TestBean bean;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(bean.toString());
	}

}
