pom.xml:
    
    PMD geimporteerd (build faalt bij meer dan 30 violations)
    ArchUnit geimporteerd

pmd:

    Veel kleine/onduidelijke variabele namen verbeterd door heel het document heen
    Veel ander fixes zoals finals toevoegen, complexity verminderen, return statements aanpassen
    Variabele naam id en klasse naam Vote is 'te klein' maar erin gelaten omdat het een extreem duidelijk naam is bij de plekken waar het hoort. We hebben het ook niet geexclude in de pmd-complexity-analysis.xml sinds we dan alle short variables excluden en dat willen we liever weer niet	

build.yml:

    Build pipeline erin die naast tests verifyen ook PMD violations checkt (ingezet via pom.xml)


/utility:

    Interface voor parser (FileParser.java) en implementatie ervan voor CSV (CSVParser.java) gemaakt
    Gebruik gemaakt van streams in FileParser.java om snel/gemakkelijk te filteren, splitten en te mappen als objecten


/candidates + /votes:

    data, domain, presentation en application directories aangemaakt en losse klassen erin verdeeld.
    Composite ID klassen (Candidacy en VotingRight) eruit gehaald. Er ging niks kapot door het eruit te halen en we zagen het nut er niet echt van sinds het nergens anders gebruikt werd.
    Namen van klassen veranderd voor duidelijkheid waar het een deel van is (bv VRepo naar VotingRepository, Repository naar CandidateRepository)
    Service klasse aangemaakt (CandidateService en VotingService)
 

methode equals:
	
    Alle equals methoden hebben wegens PMD violations nu 1 point of return en een duidelijke if/else situatie


CandidateController.java + VoteController:

    Import methode werkt nu via service inplaats van direct datalaag/domein accessen (ook het parsen van file gaat nu via service)
    De catches hebben allemaal met invalid files te maken dus hebben we de 2 catches gecombineerd in 1


CandidateService.java + VoteService.java:

    Inplaats van dat services direct files parsen wordt er nu gebruikt gemaakt van een parser klasse (die makkelijk geswitcht kan worden voor een andere type parser wegens interface implementatie)
    Gebruik gemaakt van streams voor het snel + duidelijk filteren en saven van objecten

ResultService.java + ResultController.java:

    ResultService geeft nu DTOs terug inplaats van domein klasse aan ResultController


ResultService.java:

    ResultService was ingewikkeld inelkaar gezet met onlogische ifs. Uiteindelijk veranderd naar methode addCandidates die kandidaten toevoegdt aan results lijst en daarna methode countVotes die door elke kandidaat en vote gaat en telt hoeveel votes de kandidaat heeft.
    Inplaats van dat ResultService direct de CandidateRepository en VoteRepository oproepen, roepen ze nu de services van de klasse op en pakken ze via daar alle votes en alle candidates


/test/archunit/ArchitectureTest.java

    ArchUnit tests gemaakt voor communicatie mogelijkheiden tussen layers en tests voor cyclic dependencie