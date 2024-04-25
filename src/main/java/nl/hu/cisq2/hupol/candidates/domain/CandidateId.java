package nl.hu.cisq2.hupol.candidates.domain;

import java.io.Serializable;
import java.util.Objects;

public class CandidateId implements Serializable {
    private long electionId;
    private String id;

    public CandidateId() {
    }

    public CandidateId(final Long electionId, final String id) {
        this.electionId = electionId;
        this.id = id;
    }

    @Override
    public boolean equals(final Object object) {
        boolean result = false;

        if (this == object) {
            result = true;
        }
        else if (object instanceof CandidateId that) {
            result = Objects.equals(electionId, that.electionId) && Objects.equals(id, that.id);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(electionId, id);
    }
}
