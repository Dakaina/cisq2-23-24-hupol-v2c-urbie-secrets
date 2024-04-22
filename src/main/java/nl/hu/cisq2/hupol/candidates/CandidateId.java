package nl.hu.cisq2.hupol.candidates;

import java.io.Serializable;
import java.util.Objects;

public class CandidateId implements Serializable {
    private long electionId;
    private String candidateId;

    public CandidateId() {
    }

    public CandidateId(Long electionId, String candidateId) {
        this.electionId = electionId;
        this.candidateId = candidateId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        else if (!(object instanceof CandidateId that)) {
            return false;
        }
        else {
            return Objects.equals(electionId, that.electionId) && Objects.equals(candidateId, that.candidateId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(electionId, candidateId);
    }
}
