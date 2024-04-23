package nl.hu.cisq2.hupol.candidates.presentation;

import nl.hu.cisq2.hupol.candidates.application.CandidateService;
import nl.hu.cisq2.hupol.candidates.data.CandidateRepository;
import nl.hu.cisq2.hupol.candidates.domain.Candidate;
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
    CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @PostMapping("/candidates")
    public void importCandidates(@RequestParam("file") MultipartFile file) {
         try {
            if(file != null && !file.isEmpty()) {
                candidateService.importCandidates(file);
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");
            }
         }
         catch (IOException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file");}
         catch (DateTimeParseException | NumberFormatException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File incorrect format"); }
    }
}
