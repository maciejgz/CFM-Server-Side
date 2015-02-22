package pl.mg.cfm.serializer;

import java.lang.reflect.Type;
import java.util.List;

import pl.mg.cfm.domain.EmployeeRolePojo;
import pl.mg.cfm.domain.Role;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class EmployeeRoleSerializer implements JsonDeserializer<EmployeeRolePojo> {

    @Override
    public EmployeeRolePojo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        Integer id = null;
        String roleName = null;

        if (!jsonObject.get("id").isJsonNull()) {
            id = jsonObject.get("id").getAsInt();
        }
        if (!jsonObject.get("roleName").isJsonNull()) {
            roleName = jsonObject.get("roleName").getAsString();
        }

        final EmployeeRolePojo role = new EmployeeRolePojo();
        role.setRoleId(id);
        role.setRoleName(Role.valueOf(roleName));

        return role;
    }

    public String serialize(EmployeeRolePojo role) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(role);
    }

    public String serialize(List<EmployeeRolePojo> roles) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(roles);
    }

    public EmployeeRolePojo deserialize(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(EmployeeRolePojo.class, new EmployeeRoleSerializer());
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonString, EmployeeRolePojo.class);
    }

}
