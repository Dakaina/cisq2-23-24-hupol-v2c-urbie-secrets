package nl.hu.cisq2.hupol.votes.domain;

import nl.hu.cisq2.hupol.votes.domain.Vote;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VoteTest {
    @Test
    @DisplayName("equality is handled correctly")
    void equality() {
        EqualsVerifier.forClass(Vote.class).verify();
    }
}
