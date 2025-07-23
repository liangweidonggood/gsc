plugins {
    id("java-library")
}
dependencies{
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("org.springframework.cloud:spring-cloud-context")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
tasks.bootJar{
    enabled = false
}
