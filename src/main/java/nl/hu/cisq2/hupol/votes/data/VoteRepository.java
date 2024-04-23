package nl.hu.cisq2.hupol.votes.data;

import nl.hu.cisq2.hupol.votes.domain.Vote;
import nl.hu.cisq2.hupol.votes.domain.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {
}
