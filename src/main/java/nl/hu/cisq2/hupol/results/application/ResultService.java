package nl.hu.cisq2.hupol.results.application;

import nl.hu.cisq2.hupol.candidates.application.CandidateService;
import nl.hu.cisq2.hupol.candidates.domain.Candidate;
import nl.hu.cisq2.hupol.results.application.dto.ResultDTO;
import nl.hu.cisq2.hupol.results.domain.ResultPerCandidate;
import nl.hu.cisq2.hupol.votes.application.VoteService;
import nl.hu.cisq2.hupol.votes.domain.Vote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    private final CandidateService candidateService;
    private final VoteService voteService;

    public ResultService(final CandidateService candidateService, final VoteService voteService) {
        this.candidateService = candidateService;
        this.voteService = voteService;
    }

    public List<ResultDTO> calculateResultsPerCandidate(final Long electionId){
        final List<ResultPerCandidate> results = new ArrayList<>();
        final List<Candidate> candidates = candidateService.findAllCandidates();
        final List<Vote> votes = voteService.findAllVotes();

        addCandidates(candidates, results, electionId);
        countVotes(votes, results, electionId);

        return results.stream()
                .map(ResultDTO::new)
                .toList();
    }

    private void addCandidates(final List<Candidate> candidates, final List<ResultPerCandidate> results, final Long electionId) {
        for (final Candidate candidate : candidates) {
            if (candidate.hasElectionId(electionId)) {
                results.add(new ResultPerCandidate(candidate.getId(), candidate.getName(), candidate.getFaction(), 0L));
            }
        }
    }

    private void countVotes(final List<Vote> votes, final List<ResultPerCandidate> results, final Long electionId) {
        for (final ResultPerCandidate candidate : results) {
            for (final Vote vote : votes) {
                if (vote.hasElectionId(electionId) && vote.hasCandidateId(candidate.getCandidateId())) {
                    candidate.countVote();
                }
            }
        }
    }
}
