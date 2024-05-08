package nl.hu.cisq2.hupol.results.application.dto;

import nl.hu.cisq2.hupol.results.domain.ResultPerCandidate;

import java.util.Objects;

public class ResultDTO {
    private final String candidateId;
    private final String candidateName;
    private final String faction;
    private Long votes;

    public ResultDTO(final ResultPerCandidate resultPerCandidate){
        this.candidateId = resultPerCandidate.getCandidateId();
        this.candidateName = resultPerCandidate.getCandidateName();
        this.faction = resultPerCandidate.getFaction();
        this.votes = resultPerCandidate.getVotes();
    }

    public ResultDTO(final String candidateId, final String candidateName, final String faction, final Long votes){
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.faction = faction;
        this.votes = votes;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getFaction() {
        return faction;
    }

    public Long getVotes() {
        return votes;
    }

    @Override
    public boolean equals(final Object object) {
        boolean result = false;

        if (this == object) {
            result = true;
        }
        else if (object instanceof ResultDTO that) {
            result = Objects.equals(candidateId, that.candidateId) &&
                    Objects.equals(candidateName, that.candidateName) &&
                    Objects.equals(faction, that.faction) &&
                    Objects.equals(votes, that.votes);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, candidateName, faction, votes);
    }
}
