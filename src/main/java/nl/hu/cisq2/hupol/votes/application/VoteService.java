package nl.hu.cisq2.hupol.votes.application;
import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.votes.data.VoteRepository;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.stereotype.Service;
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

    public void importVotes(final List<String[]> rows) throws IOException {
        rows.stream()
                .map(row -> new Vote(Long.parseLong(row[0]), Long.parseLong(row[1]), row[2], LocalDate.parse(row[3]), row[4]))
                .filter(vote -> !voteRepository.existsById(vote.getVoteId()))
                .forEach(voteRepository::save);
    }
}
