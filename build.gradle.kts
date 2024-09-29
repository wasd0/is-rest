val javaJwtVersion = "4.4.0"
val mapstructVersion = "1.5.0.Beta2"
val liquibaseVersion = "4.29.2"
val postgresVersion = "42.7.4"
val sprigBootVersion = "3.3.3"

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("kapt") version "2.0.20"
}

group = "com.wasd"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web:$sprigBootVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$sprigBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$sprigBootVersion")
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
    implementation("com.auth0:java-jwt:$javaJwtVersion")

    annotationProcessor("com.querydsl:querydsl-apt:5.1.0:jpa")

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