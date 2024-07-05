plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version("3.3.1")
    id("io.spring.dependency-management") version("1.1.5")
}

group = "com.luizeduu.admin.catalog.infrastructure"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}