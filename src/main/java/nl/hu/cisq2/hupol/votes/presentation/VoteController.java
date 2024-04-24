package nl.hu.cisq2.hupol.votes.presentation;
import nl.hu.cisq2.hupol.utility.FileUnpacker;
import nl.hu.cisq2.hupol.votes.application.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

@RestController
public class VoteController {
    private final VoteService voteService;

    public VoteController(final VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/votes")
    public void importVotes(@RequestParam("file") final MultipartFile file) {
        try {
            if(file != null && !file.isEmpty()) {
                voteService.importVotes(FileUnpacker.unpack(file));
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");}
        }
        catch (IOException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file", e);}
        catch (DateTimeParseException | NumberFormatException e) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File incorrect format: " + e.getMessage(), e); }
    }
}
