package nl.hu.cisq2.hupol.votes.application;
import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.utility.parser.CSVParser;
import nl.hu.cisq2.hupol.votes.data.VoteRepository;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(final VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    public void importVotes(final MultipartFile file) throws IOException {
        final CSVParser parser = new CSVParser();
        final List<Vote> votes = parser.parse(file, row -> new Vote(Long.parseLong(row[0]), Long.parseLong(row[1]), row[2], LocalDate.parse(row[3]), row[4]));

        votes.stream()
                .filter(vote -> !voteRepository.existsById(vote.getId()))
                .forEach(voteRepository::save);
    }

    public List<Vote> findAllVotes(){
        return voteRepository.findAll();
    }
}
