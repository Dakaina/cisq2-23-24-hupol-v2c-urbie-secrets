package nl.hu.cisq2.hupol.votes;

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
    VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @PostMapping("/votes")
    public void importVotes(@RequestParam("file") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                List<Vote> votes = new ArrayList<>();
                String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
                String[] rows = fileStringified.split("\r\n|\r|\n");

                for (int row = 0; row < rows.length; row++) {
                        if (!rows[row].isBlank()) {
                            String[] columns = rows[row].split(";");
                            votes.add(new Vote(Long.parseLong(columns[0]), Long.parseLong(columns[1]), columns[2], LocalDate.parse(columns[3]), columns[4]));
                        }
                }
                for (int i = 0; i < votes.size(); i++) {
                    if (!voteRepository.existsById(votes.get(i).getVotingRight())) voteRepository.save(votes.get(i));
                }
                throw new ResponseStatusException(HttpStatus.OK, "Votes imported");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing file");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File incorrect format: " + e.getMessage());
        }
    }
}
