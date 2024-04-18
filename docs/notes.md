general:
    
    candidates,votes,results directory namen naar 1 candidate,vote,result (of misschien duidelijker via candidacy, voting)

votes directory:

    domain, application, presentation, data directory maken voor overzichtelijkheid
    Missende {} op bv ifs bij meerdere klassen

VRepo.java:

    Verander naar VotingRepository voor duidelijkheid

VotingRight.java:

    Verander naar VoteID voor duidelijkheid
    Verander candidateId van String naar long voor consistentie met andere ids
    Maak equals methode overzichtelijker via if/elif/else

Vote.java:

    Verander naar VoteID voor duidelijkheid
    Hernoem methode isForCandidate naar hasCandidateId voor consistentie met andere has methode
    Verander o naar object in de .equals-methode
    Maak equals methode overzichtelijker via if/elif/else

Controller.java:

    Verander naar VoteController voor duidelijkheid
    Verander variabele repo naar voteRepository voor duidelijkheid
    Verander methode importtvotes naar importVotes (misschien importVotesFromFile)
    Verander variabele f naar file in methode importVotes
    Voeg spaties toe in de if bij f != null
    Vereenvoudig de eerste if  door !(condities)
    Verander variabele vs naar votes
    Verander variabele c naar fileStringified
    Verander variabele rs naar rows
    Verander variabele rnum naar row
    Combineer if op regel 35 en 36
    Verander variabele cols naar columns
    Breid de laatste for-loop uit met linebreak
    Verwijder trash opmerkingen die niet veel info bieden
    Throw OK_REQUEST als alles goed gaat
    Continues eruit
    Maak service klasse want op het moment communiceert controller direct met domain en data wat niet mag

candidates directory:

    domain, application, presentation, data directory maken voor overzichtelijkheid
    Missende {} op bv ifs bij meerdere klassen

Candidate.java:

    Verander variabele candidateId naar id sinds het al in Candidate klasse
    Verander variabele candidateId naar long voor consistentie, omdat alles anders ook long is
    Verander in de methode hasElectionId parameter id naar electionId om niet te verwarren met alleen id
    Maak equals methode overzichtelijker via if/elif/else
    Verander de variabele o naar object

Repo.java:

    Verander naar CandidateRepository voor duidelijkheid

Candidacy.java:

    Verander naar CandidateID voor duidelijkheid
    Verander in equals methode o naar object
    Maak equals methode overzichtelijker via if/elif/else

candidates.java:

    Verander naar CandidateController voor duidelijkheid
    Verander variabele afkorting repo naar candidateRepository voor duidelijkheid
    Verander de naam van de methode Importcandidatelist naar importCandidateList
    Verander variabele f naar file
    Voeg spaties toe bij de eerste if f != null
    Vereenvoudig de eerste if  door !(condities)
    Verander variabele candies naar candidates
    Verander variabele c naar fileStringified
    Verander variabele rs naar rows
    Verander variabele rnum naar row
    Combineer if op regel 32 en 33
    Verander variabele cols naar columns
    Verwijder trash opmerkingen die niet veel info bieden
    Throw OK_REQUEST als alles goed gaat
    Continues eruit
    Maak service klasse want op het moment communiceert controller direct met domain en data wat niet mag

