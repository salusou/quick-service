package com.genesisoft.quickservice;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.genesisoft.quickservice");

        noClasses()
            .that()
            .resideInAnyPackage("com.genesisoft.quickservice.service..")
            .or()
            .resideInAnyPackage("com.genesisoft.quickservice.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.genesisoft.quickservice.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
