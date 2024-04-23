package nl.hu.cisq2.hupol.votes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@IdClass(VoteId.class)
public class Vote {
    @Id
    private long electionId;

    @Id
    private long id;

    private String candidateId;

    private LocalDate castDate;

    private String region;

    public Vote() {}

    public Vote(long electionId, long id, String candidateId, LocalDate castDate, String region) {
        this.electionId = electionId;
        this.id = id;
        this.candidateId = candidateId;
        this.castDate = castDate;
        this.region = region;
    }

    public boolean hasElectionId(Long electionId) {
        return this.electionId == electionId;
    }

    public boolean hasCandidateId(String id) {
        return this.candidateId.equals(id);
    }

    public long getElectionId() {
        return electionId;
    }

    public long getId() {
        return id;
    }

    public VoteId getVoteId() {
        return new VoteId(electionId, id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Vote vote)) {
            return false;
        }
        else {
            return Objects.equals(castDate, vote.castDate) &&
                    Objects.equals(candidateId, vote.candidateId) &&
                    Objects.equals(region, vote.region);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(castDate, candidateId, region);
    }
}
