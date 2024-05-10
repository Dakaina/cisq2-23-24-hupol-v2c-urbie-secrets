package nl.hu.cisq2.hupol.candidates.presentation;

import nl.hu.cisq2.hupol.candidates.application.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

@RestController
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(final CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @PostMapping("/candidates")
    public void importCandidates(@RequestParam("file") final MultipartFile file) {
         try {
            if(file != null && !file.isEmpty()) {
                candidateService.importCandidates(file);
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");
            }
         }
         catch (IOException | DateTimeParseException | NumberFormatException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file: " + e.getMessage(), e); }
    }
}
