package pl.mg.cfm.serializer;

import java.lang.reflect.Type;

import pl.mg.cfm.message.CFMJsonSimpleMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class CFMMessageSerializer implements JsonDeserializer<CFMJsonSimpleMessage> {

    @Override
    public CFMJsonSimpleMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        CFMJsonSimpleMessage message = new CFMJsonSimpleMessage();

        Integer errorCode = null;
        String errorMessage = null;
        String data = null;

        if (!jsonObject.get("errorCode").isJsonNull()) {
            errorCode = jsonObject.get("errorCode").getAsInt();
        }

        if (!jsonObject.get("errorMessage").isJsonNull()) {
            errorMessage = jsonObject.get("errorMessage").getAsString();
        }

        if (!jsonObject.get("data").isJsonNull()) {
            data = jsonObject.get("data").getAsString();
        }

        message.setErrorCode(errorCode);
        message.setErrorMessage(errorMessage);
        message.setData(data);

        return message;
    }

    /**
     * TODO problem z parsowaniem tekstu gdy jest zapisany w kodzie z
     * wyescapowanymi " w przypadku list w obiekcie data.
     * 
     * @param jsonString
     * @return
     */
    public CFMJsonSimpleMessage deserialize(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(CFMJsonSimpleMessage.class, new CFMMessageSerializer());
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonString, CFMJsonSimpleMessage.class);
    }

}
