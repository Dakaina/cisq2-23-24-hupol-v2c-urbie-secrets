package nl.hu.cisq2.hupol.candidates;

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

    public Candidate(String id, long electionId, String name, String faction) {
        this.id = id;
        this.electionId = electionId;
        this.name = name;
        this.faction = faction;
    }

    public boolean hasElectionId(long id) {
        return this.electionId == id;
    }

    public String getId() {
        return this.id;
    }

    public CandidateId getCandidacy() {
        return new CandidateId(electionId, id);
    }

    public String getName() {
        return name;
    }

    public String getFaction() {
        return faction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate candidate)) return false;
        return Objects.equals(name, candidate.name) && Objects.equals(faction, candidate.faction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faction);
    }
}
