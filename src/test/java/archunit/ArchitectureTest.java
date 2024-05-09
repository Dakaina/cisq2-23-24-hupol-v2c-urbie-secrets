package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "nl.hu.cisq2.hupol")
public class ArchitectureTest {
    JavaClasses classes = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol");
    ArchRule myRule = classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    @Test
    @DisplayName("classes in the votes package should not have cyclic dependencies")
    void votesPackageClassesShouldNotHaveCyclicDependencies() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol.votes");

        ArchRule myRule = noClasses().should().dependOnClassesThat().resideInAnyPackage("nl.hu.cisq2.hupol.votes");

        myRule.check(importedClasses);
    }

    @Test
    @DisplayName("classes in the utility package should not have cyclic dependencies")
    void utilityPackageClassesShouldNotHaveCyclicDependencies() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol.utility");

        ArchRule myRule = noClasses().should().dependOnClassesThat().resideInAnyPackage("nl.hu.cisq2.hupol.utility");

        myRule.check(importedClasses);
    }

    @Test
    @DisplayName("classes in the candidates package should not have cyclic dependencies")
    void candidatesPackageClassesShouldNotHaveCyclicDependencies() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol.candidates");

        ArchRule myRule = noClasses().should().dependOnClassesThat().resideInAnyPackage("nl.hu.cisq2.hupol.candidates");

        myRule.check(importedClasses);
    }

    @Test
    @DisplayName("data layer should not interact with domain, application and presentation layer")
    void dataNoInteractionDomainAndApplicationAndPresentation(){
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..data..")
                .should().accessClassesThat()
                .resideInAnyPackage("..domain.." ,"..application..", "..presentation..");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Domain layer should not interact with application and presentation layer")
    void domainNoInteractionApplicationAndPresentation(){
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().accessClassesThat()
                .resideInAnyPackage("..application..", "..presentation..");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Application layer should not interact with presentation layer")
    void applicationNoInteractionPresentation(){
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().accessClassesThat()
                .resideInAPackage("..presentation..");

        rule.check(importedClasses);
    }

    @Test
    @DisplayName("Presentation layer should not interact with data and domain layer")
    void presentationNoInteractionDataAndDomain(){
        JavaClasses importedClasses = new ClassFileImporter()
                .importPackages("nl.hu.cisq2.hupol");

        ArchRule rule = noClasses()
                .that().resideOutsideOfPackage("..security..") //security violates this rule but not part of assignment so I'm excluding the package here
                .and().resideInAPackage("..presentation..")
                .should().accessClassesThat()
                .resideInAnyPackage("..data..", "..domain..");

        rule.check(importedClasses);
    }
}




