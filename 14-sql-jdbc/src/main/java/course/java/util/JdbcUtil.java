package course.java.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JdbcUtil {
    public static <T> List<T> getEntities(ResultSet rs, Class<T> entityClass) throws SQLException {
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
//            entities.add()
        }
        return entities;
    }
}
