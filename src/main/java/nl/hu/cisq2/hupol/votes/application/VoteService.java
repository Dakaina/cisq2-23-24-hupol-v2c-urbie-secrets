package nl.hu.cisq2.hupol.votes.application;

import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.votes.data.VoteRepository;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VoteService {
    VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository){
        this.voteRepository = voteRepository;
    }

    public void importVotes(MultipartFile f) throws IOException {
        List<Vote> vs = new ArrayList<>();
        String c = new String(f.getBytes(), StandardCharsets.UTF_8);
        String[] rs = c.split("\r\n|\r|\n");

        for (int rnum = 0; rnum < rs.length; rnum++) {
            if(!rs[rnum].isEmpty()){
                if(!rs[rnum].isBlank()&&rs[rnum].length()>0){
                    String[] cols = rs[rnum].split(";");
                    vs.add(new Vote(Long.parseLong(cols[0]), Long.parseLong(cols[1]), cols[2], LocalDate.parse(cols[3]), cols[4]));                        } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        for (int i = 0; i < vs.size(); i++) {
            if (!voteRepository.existsById(vs.get(i).getVoteId())) voteRepository.save(vs.get(i));
        }
    }
}
