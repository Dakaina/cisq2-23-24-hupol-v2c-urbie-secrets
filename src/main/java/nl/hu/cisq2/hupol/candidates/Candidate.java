package nl.hu.cisq2.hupol.candidates;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.util.Objects;

@Entity
@IdClass(CandidateId.class)
public class Candidate {
    @Id
    private String candidateId;

    @Id
    private long electionId;

    private String name;

    private String faction;

    public Candidate() {
    }

    public Candidate(String candidateId, long electionId,  String name, String faction) {
        this.candidateId = candidateId;
        this.electionId = electionId;
        this.name = name;
        this.faction = faction;
    }

    public boolean hasElectionId(long id) {
        return this.electionId == id;
    }

    public String getId() {
        return this.candidateId;
    }

    public CandidateId getCandidateId() {
        return new CandidateId(electionId, candidateId);
    }

    public String getName() {
        return name;
    }

    public String getFaction() {
        return faction;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        else if (!(object instanceof Candidate candidate)) {
            return false;
        }
        else {
            return Objects.equals(name, candidate.name) && Objects.equals(faction, candidate.faction);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, faction);
    }
}
