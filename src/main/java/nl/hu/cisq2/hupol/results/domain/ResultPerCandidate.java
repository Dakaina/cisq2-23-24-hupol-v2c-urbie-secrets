package nl.hu.cisq2.hupol.results.domain;

import nl.hu.cisq2.hupol.votes.domain.Vote;

import java.util.Objects;

public class ResultPerCandidate {
    private final String candidateId;
    private final String candidateName;
    private final String faction;
    private Long votes;

    public ResultPerCandidate(final String candidateId, final String candidateName, final String faction, final Long votes) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.faction = faction;
        this.votes = votes;
    }

    public void countVote() {
        this.votes++;
    }

    public boolean isForCandidate(final String id) {
        return this.candidateId.equals(id);
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
        else if (object instanceof ResultPerCandidate that) {
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
