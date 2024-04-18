
votes directory:

    domain, application, presentation, data directory maken voor overzichtelijkheid
    Missende {} op bv ifs bij meerdere klassen

VRepo.java:

    Verander naar VotingRepository voor duidelijkheid

VotingRight.java:

    Verande naar VoteID voor duidelijkheid
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
