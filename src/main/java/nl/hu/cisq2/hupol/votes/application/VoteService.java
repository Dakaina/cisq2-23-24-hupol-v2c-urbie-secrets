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

    public void importVotes(final List<String[]> columnsList) throws IOException {
        for (final String[] columns : columnsList){
            final Vote vote = new Vote(Long.parseLong(columns[0]), Long.parseLong(columns[1]), columns[2], LocalDate.parse(columns[3]), columns[4]);
            if (!voteRepository.existsById(vote.getVoteId())){
                voteRepository.save(vote);
            }
        }
    }
}
