package nl.hu.cisq2.hupol.votes;

import java.io.Serializable;
import java.util.Objects;

public class VoteId implements Serializable {
    private long electionId;
    private long id;

    public VoteId() {
    }

    public VoteId(long electionId, long id) {
        this.electionId = electionId;
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof VoteId that)) {
            return false;
        }
        else {
            return electionId == that.electionId && id == that.id;

        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(electionId, id);
    }
}
