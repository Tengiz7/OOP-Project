package main.domain;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class ObjectMapper {
    public static <T> T Map(ResultSet set, Class<T> tClass) {
        try {
            T result = tClass.getDeclaredConstructor().newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String value = set.getString(field.getName());

                if (field.getType() == int.class) {
                    field.setInt(result, Integer.parseInt(value));
                } else if (field.getType() == long.class) {
                    field.setLong(result, Long.parseLong(value));
                } else if (field.getType() == boolean.class) {
                    field.setBoolean(result, Boolean.parseBoolean(value));
                } else {
                    field.set(result, value);
                }
            }
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }
}
