package nl.hu.cisq2.hupol.utility.fileParser;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CSVParser implements FileParser{
    public <T> List<T> parse(MultipartFile file, Function<String[], T> mapper) throws IOException {
        final String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
        final String[] rows = fileStringified.split("\r\n|\r|\n");

        return Arrays.stream(rows)
                .filter(row -> !row.isBlank())
                .map(row -> row.split(";"))
                .map(mapper)
                .toList();
    }
}
