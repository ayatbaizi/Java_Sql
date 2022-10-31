package utils.resources;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.sql.SQLException;
import java.util.Map;

public interface IResource {
    Map<String, String> read();
}
