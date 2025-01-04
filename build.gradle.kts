plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("kapt") version "2.0.20"
}

group = "com.wasd"
version = "0.0.1-SNAPSHOT"

// Properties
val springBootVersion = "3.4.1"
val mapstructVersion = "1.5.0.Beta2"
val liquibaseVersion = "4.29.2"
val postgresVersion = "42.7.4"
var queryDslVersion = "5.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:$springBootVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}")
    implementation("org.postgresql:postgresql:42.7.4")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")

    annotationProcessor("com.querydsl:querydsl-apt:$queryDslVersion:jpa")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDirs("src/main/kotlin", "build/generated/source/kapt/main")
        }
    }
}

kapt {
    javacOptions {
        option("querydsl.entityAccessors", true)
    }
    arguments {
        arg("plugin", "com.querydsl.apt.jpa.JPAAnnotationProcessor")
        arg("mapstruct.defaultComponentModel", "spring")
    }
}