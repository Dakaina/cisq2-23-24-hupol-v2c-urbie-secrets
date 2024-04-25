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
    @DisplayName("classes in the votes package should not access classes in the domain package")
    void votesPackageClassesShouldNotAccessDomainPackageClasses() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.hu.cisq2.hupol.votes");

        ArchRule myRule = noClasses().that().resideInAPackage("..votes..")
                .should().accessClassesThat().resideInAPackage("..domain..");

        myRule.check(importedClasses);
    }
    @Test
    @DisplayName("Presentation layer should not access service layer")
    void presentationLayerShouldNotAccessServiceLayer() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("your.package");

        ArchRule rule = noClasses().that().resideInAPackage("..presentation..")
                .should().accessClassesThat().resideInAPackage("..service..");

        rule.check(importedClasses);
    }
    @Test
    @DisplayName("Application layer should only access service, domain, and data layers")
    void applicationLayerShouldOnlyAccessServiceDomainAndDataLayers() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("your.package");

        ArchRule rule = noClasses().that().resideInAPackage("..application..")
                .should().accessClassesThat().resideOutsideOfPackages("..service..", "..domain..", "..data..");

        rule.check(importedClasses);
    }
    @Test
    @DisplayName("Domain layer should only access the data layer")
    void domainLayerShouldOnlyAccessDataLayer() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("your.package");

        ArchRule rule = noClasses().that().resideInAPackage("..domain..")
                .should().accessClassesThat().resideOutsideOfPackage("..data..");

        rule.check(importedClasses);
    }
}




