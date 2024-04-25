package nl.hu.cisq2.hupol.results.application;

import nl.hu.cisq2.hupol.candidates.domain.Candidate;
import nl.hu.cisq2.hupol.candidates.data.CandidateRepository;
import nl.hu.cisq2.hupol.results.domain.ResultPerCandidate;
import nl.hu.cisq2.hupol.votes.data.VoteRepository;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    private final CandidateRepository candidatesRepository;
    private final VoteRepository votesRepository;

    public ResultService(final CandidateRepository candidatesRepository, final VoteRepository votesRepository) {
        this.candidatesRepository = candidatesRepository;
        this.votesRepository = votesRepository;
    }

    public List<ResultPerCandidate> calculateResultsPerCandidate(final Long electionId) {
        final List<ResultPerCandidate> results = new ArrayList<>();

        final List<Candidate> candidates = candidatesRepository.findAll();
        final List<Vote> votes = votesRepository.findAll();

        for (final Vote vote : votes) {
            if (!vote.hasElectionId(electionId)) {
                continue;
            }

            for (final Candidate candidate : candidates) {
                if (!candidate.hasElectionId(electionId)) {
                    continue;
                }

                if (vote.hasCandidateId(candidate.getId())) {
                    this.countVoteForCandidate(candidate, results);
                }
            }
        }

        return results;
    }

    private void countVoteForCandidate(final Candidate candidate, final List<ResultPerCandidate> results) {
        boolean found = false;

        for (final ResultPerCandidate result : results) {
            if (result.isForCandidate(candidate.getId())) {
                result.countVote();
                found = true;
                break;
            }
        }

        if (!found) {
            results.add(new ResultPerCandidate(candidate.getId(), candidate.getName(), candidate.getFaction(), 1L));
        }
    }
}
