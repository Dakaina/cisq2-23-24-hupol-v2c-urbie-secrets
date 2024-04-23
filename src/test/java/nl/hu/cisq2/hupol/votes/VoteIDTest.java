package nl.hu.cisq2.hupol.votes;

import nl.hu.cisq2.hupol.votes.domain.VoteId;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VoteIdTest {
    @Test
    @DisplayName("equality is handled correctly")
    void equality() {
        EqualsVerifier.simple().forClass(VoteId.class).verify();
    }

}