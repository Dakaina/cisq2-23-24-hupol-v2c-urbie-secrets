package nl.hu.cisq2.hupol.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUnpacker {
    public static List<String[]> getColumns(final MultipartFile file) throws IOException {
        final List<String[]> columnsList = new ArrayList<>();

        final String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
        final String[] rows = fileStringified.split("\r\n|\r|\n");

        for (final String row : rows) {
            if (!row.isBlank()) {
                columnsList.add(row.split(";"));
            }
        }
        return columnsList;
    }
}
