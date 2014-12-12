package pl.mg.cfm.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");
        MainObject obj = (MainObject) ctx.getBean("mainObject");
        System.out.println(obj.getObject().getVersion());
        System.out.println(obj.getConObject().getVersion());
    }

}
