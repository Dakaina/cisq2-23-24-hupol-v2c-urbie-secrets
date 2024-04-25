package nl.hu.cisq2.hupol.votes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Vote {
    @Id
    private long id;

    private long electionId;

    private String candidateId;

    private LocalDate castDate;

    private String region;

    public Vote() {}

    public Vote(final long electionId, final long id, final String candidateId, final LocalDate castDate, final String region) {
        this.electionId = electionId;
        this.id = id;
        this.candidateId = candidateId;
        this.castDate = castDate;
        this.region = region;
    }

    public boolean hasElectionId(final long electionId) {
        return this.electionId == electionId;
    }

    public boolean hasCandidateId(final String id) {
        return this.candidateId.equals(id);
    }

    public long getElectionId() {
        return electionId;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(final Object object) {
        boolean result = false;

        if (this == object) {
            result = true;
        }
        else if (object instanceof Vote vote) {
            result = Objects.equals(castDate, vote.castDate) &&
                    Objects.equals(candidateId, vote.candidateId) &&
                    Objects.equals(region, vote.region) &&
                    Objects.equals(electionId, vote.electionId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(castDate, candidateId, region, electionId);
    }
}
