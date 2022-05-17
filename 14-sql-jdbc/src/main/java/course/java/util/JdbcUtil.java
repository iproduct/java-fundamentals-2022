package course.java.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JdbcUtil {
    public static <T> List<T> getEntities(ResultSet rs, Class<T> entityClass) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> entities = new ArrayList<>();
        var methods = entityClass.getMethods();
        var propMethodMap = Arrays.stream(methods).filter(method -> method.getName().startsWith("set"))
                .collect(Collectors.toMap(method -> {
                    var propName = method.getName().substring(3);
                    propName = Character.toLowerCase(propName.charAt(0)) + propName.substring(1);
                    System.out.println(propName);
                    return propName;
                }, Function.identity()));

        while(rs.next()) {
            T entity = entityClass.getDeclaredConstructor(new Class[]{}).newInstance();
            for(String prop : propMethodMap.keySet()) {
                var setter = propMethodMap.get(prop);
                var propClass = setter.getParameterTypes()[0];
                Object value = null;
                if(propClass.isEnum()) {
                    var valueStr = rs.getString(prop);
                    try {
                        var valuesMethod = propClass.getDeclaredMethod("valueOf");
                        Object result = valuesMethod.invoke(new Object[] {propClass, valueStr});
                        value = propClass.cast(result);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                switch(propClass.getSimpleName()){
                    case "Long":
                    case "long": value = rs.getObject(prop, Long.class); break;
                    case "Integer":
                    case "int": value = rs.getInt(prop); break;
                    case "Double":
                    case "double": value = rs.getDouble(prop); break;
                    case "Boolean":
                    case "boolean": value = rs.getBoolean(prop); break;
                    case "String": value = rs.getString(prop); break;
                    case "Date": value = rs.getDate(prop); break;
                    case "LocalDate": value = rs.getDate(prop).toLocalDate(); break;
                    case "LocalDateTime": value = LocalDateTime.ofInstant(rs.getDate(prop).toInstant(), ZoneId.systemDefault()); break;
                }
                System.out.println(value);
                setter.invoke(entity, value);
            }
            entities.add(entity);
            System.out.println(entity);
        }
        return entities;
    }
}
