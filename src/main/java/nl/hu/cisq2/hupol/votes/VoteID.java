package nl.hu.cisq2.hupol.votes;

import java.io.Serializable;
import java.util.Objects;

public class VoteID implements Serializable {
    private long electionId;
    private long voterId;

    public VoteID() {
    }

    public VoteID(long electionId, long voterId) {
        this.electionId = electionId;
        this.voterId = voterId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof VoteID that)) {
            return false;
        } else {
            return electionId == that.electionId && voterId == that.voterId;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(electionId, voterId);
    }
}
