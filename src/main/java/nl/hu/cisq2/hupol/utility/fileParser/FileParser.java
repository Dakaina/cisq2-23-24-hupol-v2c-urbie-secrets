package nl.hu.cisq2.hupol.utility.fileParser;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;


public interface FileParser {
    public <T> List<T> parse(MultipartFile file, Function<String[], T> mapper) throws IOException;
}
