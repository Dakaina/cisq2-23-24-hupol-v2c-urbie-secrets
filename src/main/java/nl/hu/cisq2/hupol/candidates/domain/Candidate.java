package nl.hu.cisq2.hupol.candidates.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.util.Objects;

@Entity
@IdClass(CandidateId.class)
public class Candidate {
    @Id
    private String id;

    @Id
    private long electionId;

    private String name;

    private String faction;

    public Candidate() {
    }

    public Candidate(final String id, final long electionId, final String name, final String faction) {
        this.id = id;
        this.electionId = electionId;
        this.name = name;
        this.faction = faction;
    }

    public boolean hasElectionId(final long id) {
        return this.electionId == id;
    }

    public String getId() {
        return this.id;
    }

    public CandidateId getCandidateId() {
        return new CandidateId(electionId, id);
    }

    public String getName() {
        return name;
    }

    public String getFaction() {
        return faction;
    }

    @Override
    public boolean equals(final Object object) {
        boolean result = false;

        if (this == object) {
            result = true;
        }
        else if (object instanceof Candidate candidate) {
            result = Objects.equals(name, candidate.name) && Objects.equals(faction, candidate.faction);
        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faction);
    }
}
