package nl.hu.cisq2.hupol.candidates;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CandidateController {
    CandidateRepository candidateRepository;
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
    @PostMapping("/candidates")
    public void importCandidates(@RequestParam("file") MultipartFile file) {
         try {
            if(file != null && !file.isEmpty()) {
                List<Candidate> candidates = new ArrayList<>();
                String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
                String[] rows = fileStringified.split("\r\n|\r|\n");

                for (int row = 0; row < rows.length; row++) {
                    if (!rows[row].isBlank()) {
                        String[] columns = rows[row].split(";");
                        candidates.add(new Candidate(columns[0], Long.parseLong(columns[1]), columns[2], columns[3]));
                    }
                }

                for (int i = 0; i < candidates.size(); i++) {
                    candidateRepository.save(candidates.get(i));
                }
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");
            }
         }
         catch (IOException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file");}
         catch (DateTimeParseException | NumberFormatException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File incorrect format"); }
    }
}
