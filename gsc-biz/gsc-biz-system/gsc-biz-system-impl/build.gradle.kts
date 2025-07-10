plugins {
    id("java-library")
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    api("com.baomidou:mybatis-plus-spring-boot3-starter")
    implementation("mysql:mysql-connector-java")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
}
tasks.test {
    useJUnitPlatform()
}
tasks.bootJar{
    enabled = false
}
