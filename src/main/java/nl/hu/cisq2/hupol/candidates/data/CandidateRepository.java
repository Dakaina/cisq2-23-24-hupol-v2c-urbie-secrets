package nl.hu.cisq2.hupol.candidates.data;

import nl.hu.cisq2.hupol.candidates.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
}
