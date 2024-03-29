plugins {
    id("java")
}

group = "com.luizeduu.admin.catalog.application"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))

    // https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
    testImplementation("org.mockito:mockito-junit-jupiter:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/io.vavr/vavr
    implementation("io.vavr:vavr:0.10.4")

}

tasks.test {
    useJUnitPlatform()
}