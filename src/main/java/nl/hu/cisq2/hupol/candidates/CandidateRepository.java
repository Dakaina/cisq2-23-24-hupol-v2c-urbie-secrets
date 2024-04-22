package nl.hu.cisq2.hupol.candidates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, CandidateId> {
}
