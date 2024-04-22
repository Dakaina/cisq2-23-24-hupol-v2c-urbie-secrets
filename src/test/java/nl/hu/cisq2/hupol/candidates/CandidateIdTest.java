package nl.hu.cisq2.hupol.candidates;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CandidateIdTest {
    @Test
    @DisplayName("equality is handled correctly")
    void equality() {
        EqualsVerifier.simple().forClass(CandidateId.class).verify();
    }
}