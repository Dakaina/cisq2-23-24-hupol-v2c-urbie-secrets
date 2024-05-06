package nl.hu.cisq2.hupol.results.application.dto;

import nl.hu.cisq2.hupol.results.domain.ResultPerCandidate;

public class ResultDTO {
    private final String candidateId;
    private final String candidateName;
    private final String faction;
    private Long votes;

    public ResultDTO(ResultPerCandidate resultPerCandidate){
        this.candidateId = resultPerCandidate.getCandidateId();
        this.candidateName = resultPerCandidate.getCandidateName();
        this.faction = resultPerCandidate.getFaction();
        this.votes = resultPerCandidate.getVotes();
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
}
