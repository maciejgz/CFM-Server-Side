package pl.mg.cfm.commons.test;

import org.junit.Test;

import pl.mg.cfm.serializer.CFMMessageSerializer;

public class ToJsonCFMMessageTest {

    @Test
    public void test() {
        String empl = "{\"errorCode\":0,\"errorMessage\":null,\"data\":[{\"carId\":\"wsc1234\",\"distance\":0,\"latitude\":-50.123456789,\"longitude\":50.123456789,\"ownerId\":9996},{\"carId\":\"wsc1235\",\"distance\":0,\"latitude\":null,\"longitude\":null,\"ownerId\":9996},{\"carId\":\"wscap48\",\"distance\":0,\"latitude\":-50.23232,\"longitude\":0.012,\"ownerId\":9996},{\"carId\":\"wscap49\",\"distance\":0,\"latitude\":-50.23232,\"longitude\":0.012,\"ownerId\":null}]}";
        System.out.println(empl);
        CFMMessageSerializer serializer = new CFMMessageSerializer();
        System.out.println((serializer.deserialize(empl)).toString());
    }
}


