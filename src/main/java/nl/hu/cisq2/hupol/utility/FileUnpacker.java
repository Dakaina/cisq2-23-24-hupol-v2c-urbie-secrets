package nl.hu.cisq2.hupol.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUnpacker {
    public static List<String[]> unpack(final MultipartFile file) throws IOException {
        final String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
        final String[] rows = fileStringified.split("\r\n|\r|\n");
        final List<String[]> columnsList = new ArrayList<>();

        for (final String row : rows) {
            if (!row.isBlank()) {
                columnsList.add(row.split(";"));
            }
        }
        return columnsList;
    }
}
