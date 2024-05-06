package nl.hu.cisq2.hupol.candidates.application;
import jakarta.transaction.Transactional;
import nl.hu.cisq2.hupol.candidates.data.CandidateRepository;
import nl.hu.cisq2.hupol.candidates.domain.Candidate;
import nl.hu.cisq2.hupol.utility.parser.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CandidateService {
    private CandidateRepository candidateRepository;

    public CandidateService(final CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public void importCandidates(final MultipartFile file) throws IOException {
        final CSVParser parser = new CSVParser();
        final List<Candidate> candidates = parser.parse(file, row -> new Candidate(row[0], Long.parseLong(row[1]), row[2], row[3]));

        candidates.stream()
                .filter((candidate) -> candidateRepository.existsById(candidate.getId()))
                .forEach(candidateRepository::save);
    }
}
