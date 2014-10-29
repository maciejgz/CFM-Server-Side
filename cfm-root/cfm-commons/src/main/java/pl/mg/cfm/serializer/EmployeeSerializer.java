package pl.mg.cfm.serializer;

import java.lang.reflect.Type;
import java.util.List;

import pl.mg.cfm.pojo.EmployeePojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class EmployeeSerializer implements JsonDeserializer<EmployeePojo> {

    @Override
    public EmployeePojo deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        final JsonObject jsonObject = json.getAsJsonObject();
        Integer id = null;
        String firstName = null;
        String lastName = null;
        Integer roleId = null;

        if (!jsonObject.get("id").isJsonNull()) {
            id = jsonObject.get("id").getAsInt();
        }
        if (!jsonObject.get("firstName").isJsonNull()) {
            firstName = jsonObject.get("firstName").getAsString();
        }
        if (!jsonObject.get("lastName").isJsonNull()) {
            lastName = jsonObject.get("lastName").getAsString();
        }
        if (!jsonObject.get("roleId").isJsonNull()) {
            roleId = jsonObject.get("roleId").getAsInt();
        }

        final EmployeePojo employee = new EmployeePojo();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setRoleId(roleId);
        return employee;
    }

    public String serialize(EmployeePojo employee) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(employee);
    }

    public String serialize(List<EmployeePojo> employees) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(employees);
    }

    public EmployeePojo deserialize(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(EmployeePojo.class, new EmployeeSerializer());
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonString, EmployeePojo.class);
    }

    public List<EmployeePojo> deserializeList(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(EmployeePojo.class, new EmployeeSerializer());
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonString, new TypeToken<List<EmployeePojo>>() {
        }.getType());
    }

}
