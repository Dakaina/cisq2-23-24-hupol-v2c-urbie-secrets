package nl.hu.cisq2.hupol.votes.application;

import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.utility.FileUnpacker;
import nl.hu.cisq2.hupol.votes.data.VoteRepository;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional
public class VoteService {
    VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    public void importVotes(MultipartFile file) throws IOException {
        ArrayList<String[]> columnsList = FileUnpacker.getColumns(file);

        for (String[] columns : columnsList){
            Vote vote = new Vote(Long.parseLong(columns[0]), Long.parseLong(columns[1]), columns[2], LocalDate.parse(columns[3]), columns[4]);
            if (!voteRepository.existsById(vote.getVoteId())){
                voteRepository.save(vote);
            }
        }
    }
}
