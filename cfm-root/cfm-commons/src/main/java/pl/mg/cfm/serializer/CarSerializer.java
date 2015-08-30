package pl.mg.cfm.serializer;

import java.lang.reflect.Type;
import java.util.List;

import pl.mg.cfm.domain.CarPojo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class CarSerializer implements JsonDeserializer<CarPojo> {

    @Override
    public CarPojo deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();
        String carId = null;
        Double distance = null;
        Double latitude = null;
        Double longitude = null;
        Integer ownerId = null;

        if (!jsonObject.get("carId").isJsonNull()) {
            carId = jsonObject.get("carId").getAsString();
        }

        if (!jsonObject.get("distance").isJsonNull()) {
            distance = jsonObject.get("distance").getAsDouble();
        }
        if (!jsonObject.get("latitude").isJsonNull()) {
            latitude = jsonObject.get("latitude").getAsDouble();
        }
        if (!jsonObject.get("longitude").isJsonNull()) {
            longitude = jsonObject.get("longitude").getAsDouble();
        }
        if (!jsonObject.get("ownerId").isJsonNull()) {
            ownerId = jsonObject.get("ownerId").getAsInt();
        }

        final CarPojo car = new CarPojo();
        car.setCarId(carId);
        car.setDistance(distance);
        car.setLatitude(latitude);
        car.setLongitude(longitude);
        car.setOwnerId(ownerId);
        return car;
    }

    public String serialize(CarPojo car) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(car);
    }

    public String serialize(List<CarPojo> cars) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(cars);
    }

    public CarPojo deserialize(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(CarPojo.class, new CarSerializer());
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonString, CarPojo.class);
    }

    public List<CarPojo> deserializeList(String jsonString) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(CarPojo.class, new CarSerializer());
        builder.serializeNulls();
        Gson gson = builder.create();

        return gson.fromJson(jsonString, new TypeToken<List<CarPojo>>() {
        }.getType());
    }

}
