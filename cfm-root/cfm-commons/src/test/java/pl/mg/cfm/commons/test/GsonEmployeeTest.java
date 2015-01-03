/**
 * 
 */
package pl.mg.cfm.commons.test;

import org.junit.Test;

import pl.mg.cfm.domain.EmployeePojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonEmployeeTest {

    @Test
    public void test() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        EmployeePojo empl = new EmployeePojo();
        empl.setId(1);
        empl.setFirstName("fname");
        empl.setLastName("lname");
        empl.setRoleName("ADMIN");

        System.out.println(gson.toJson(empl));
    }

}
