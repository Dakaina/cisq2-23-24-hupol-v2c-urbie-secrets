package nl.hu.cisq2.hupol.votes.presentation;

import nl.hu.cisq2.hupol.votes.application.VoteService;
import nl.hu.cisq2.hupol.votes.data.VoteRepository;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VoteController {
    VoteService voteService;

    public VoteController(VoteService voteService) {

        this.voteService = voteService;
    }

    @PostMapping("/votes")
    public void importVotes(@RequestParam("file") MultipartFile file) {
        try {
            if(file != null && !file.isEmpty()) {
                voteService.importVotes(file);
            } else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");}
        }
        catch (IOException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file");}
        catch (DateTimeParseException | NumberFormatException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File incorrect format: " + e.getMessage()); }
    }
}
