package nl.hu.cisq2.hupol.votes;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VoteIDTest {
    @Test
    @DisplayName("equality is handled correctly")
    void equality() {
        EqualsVerifier.simple().forClass(VoteID.class).verify();
    }

}