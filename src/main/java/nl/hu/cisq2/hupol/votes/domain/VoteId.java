package nl.hu.cisq2.hupol.votes.domain;

import java.io.Serializable;
import java.util.Objects;

public class VoteId implements Serializable {
    private long electionId;
    private long id;

    public VoteId() {
    }

    public VoteId(final long electionId, final long id) {
        this.electionId = electionId;
        this.id = id;
    }

    @Override
    public boolean equals(final Object object) {
        boolean result = false;

        if (this == object) {
            result = true;
        }
        else if (object instanceof VoteId that) {
            result = electionId == that.electionId && id == that.id;
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(electionId, id);
    }
}
