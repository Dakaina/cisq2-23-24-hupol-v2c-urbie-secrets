package nl.hu.cisq2.hupol.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileUnpacker {
    public static ArrayList<String[]> getColumns(MultipartFile file) throws IOException {
        ArrayList<String[]> columnsList = new ArrayList<>();

        String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
        String[] rows = fileStringified.split("\r\n|\r|\n");

        for (String row : rows) {
            if (!row.isBlank()) {
                columnsList.add(row.split(";"));
            }
        }
        return columnsList;
    }
}
