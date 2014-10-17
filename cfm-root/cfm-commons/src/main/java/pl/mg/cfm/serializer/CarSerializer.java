package pl.mg.cfm.serializer;

import java.lang.reflect.Type;

import pl.mg.cfm.pojo.CarPojo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class CarSerializer implements JsonDeserializer<CarPojo> {

    @Override
    public CarPojo deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();
        Integer id = null;
        String carId = null;
        Long distance = null;
        Double latitude = null;
        Double longitude = null;
        Integer ownerId = null;
        
        //TODO poprawić obsługę nulla
        if (jsonObject.isJsonNull()) {
            id = jsonObject.get("id").getAsInt();
        }
        if (jsonObject.has("carId")) {
            carId = jsonObject.get("carId").getAsString();
        }

        if (jsonObject.has("distance")) {
            distance = jsonObject.get("distance").getAsLong();
        }
        if (jsonObject.has("latitude")) {
            latitude = jsonObject.get("latitude").getAsDouble();
        }
        if (jsonObject.has("longitude")) {
            longitude = jsonObject.get("longitude").getAsDouble();
        }
        if (jsonObject.has("ownerId")) {
            ownerId = jsonObject.get("ownerId").getAsInt();
        }

        final CarPojo car = new CarPojo();
        car.setId(id);
        car.setCarId(carId);
        car.setDistance(distance);
        car.setLatitude(latitude);
        car.setLongitude(longitude);
        car.setOwnerId(ownerId);

        return car;

    }
}
