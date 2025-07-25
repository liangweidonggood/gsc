plugins {
    id("java-library")
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(project(":gsc-biz:gsc-biz-common:gsc-biz-common-core"))
}
tasks.test {
    useJUnitPlatform()
}
tasks.bootJar{
    enabled = false
}
