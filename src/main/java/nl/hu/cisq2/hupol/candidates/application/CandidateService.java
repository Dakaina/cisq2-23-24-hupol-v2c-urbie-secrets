package nl.hu.cisq2.hupol.candidates.application;

import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.candidates.data.CandidateRepository;
import nl.hu.cisq2.hupol.candidates.domain.Candidate;
import nl.hu.cisq2.hupol.utility.FileUnpacker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        List<String[]> columnsList = FileUnpacker.getColumns(file);

        for (String[] columns : columnsList){
            Candidate candidate = new Candidate(columns[0], Long.parseLong(columns[1]), columns[2], columns[3]);
            if (!candidateRepository.existsById(candidate.getCandidateId())){
                candidateRepository.save(candidate);
            }
        }
    }
}
