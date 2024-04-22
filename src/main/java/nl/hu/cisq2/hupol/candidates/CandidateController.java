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
    public void importCandidates(@RequestParam("file") MultipartFile f) {
        try {
            if (f != null && !f.isEmpty()) {
                List<Candidate> candies = new ArrayList<>();
                String c = new String(f.getBytes(), StandardCharsets.UTF_8);
                String[] rs = c.split("\r\n|\r|\n");

                for (int rnum = 0; rnum < rs.length; rnum++) {
                    if (!rs[rnum].isEmpty()) {
                        if (!rs[rnum].isBlank() && rs[rnum].length() > 0) {
                            String[] cols = rs[rnum].split(";");
                            candies.add(new Candidate(cols[0], Long.parseLong(cols[1]), cols[2], cols[3]));
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                for (int i = 0; i < candies.size(); i++) {
                    candidateRepository.save(candies.get(i));
                }
                throw new ResponseStatusException(HttpStatus.OK, "Candidates imported");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File incorrect format");
        }
    }
}
