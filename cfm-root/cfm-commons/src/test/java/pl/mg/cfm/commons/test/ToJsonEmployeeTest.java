package pl.mg.cfm.commons.test;

import org.junit.Test;

import pl.mg.cfm.serializer.EmployeeSerializer;

public class ToJsonEmployeeTest {

    @Test
    public void test() {
        String empl = "{\"id\":1,\"firstName\":\"fname\",\"lastName\":\"lname\",\"roleId\":1,\"password\":\"pas\"}";

        EmployeeSerializer serializer = new EmployeeSerializer();
        System.out.println((serializer.deserialize(empl)).toString());

    }

}
