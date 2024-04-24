package nl.hu.cisq2.hupol.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUnpacker {
    public static List<String[]> unpack(final MultipartFile file) throws IOException {
        final String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
        final String[] rows = fileStringified.split("\r\n|\r|\n");

        List<String[]> rowsSplit = Arrays.stream(rows)
                .filter(row -> !row.isBlank())
                .map(row -> row.split(";"))
                .collect(Collectors.toList());

        return rowsSplit;
    }
}
