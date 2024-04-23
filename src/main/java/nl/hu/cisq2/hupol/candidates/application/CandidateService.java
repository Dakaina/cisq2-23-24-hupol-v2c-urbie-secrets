package nl.hu.cisq2.hupol.candidates.application;

import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.candidates.data.CandidateRepository;
import nl.hu.cisq2.hupol.candidates.domain.Candidate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CandidateService {
    CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public void importCandidates(MultipartFile file) throws IOException {
        List<Candidate> candidates = new ArrayList<>();
        String fileStringified = new String(file.getBytes(), StandardCharsets.UTF_8);
        String[] rows = fileStringified.split("\r\n|\r|\n");

        for (int row = 0; row < rows.length; row++) {
            if (!rows[row].isBlank()) {
                String[] columns = rows[row].split(";");
                candidates.add(new Candidate(columns[0], Long.parseLong(columns[1]), columns[2], columns[3]));
            }
        }

        for (int i = 0; i < candidates.size(); i++) {
            candidateRepository.save(candidates.get(i));
        }
    }
}
