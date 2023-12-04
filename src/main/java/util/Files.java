package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

public class Files {
    public static <T> List<T> readFile(String fileName, Function<String, T> mapper) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("input/" + fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (BufferedReader br = new BufferedReader(streamReader)) {
            return br.lines().map(mapper).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
