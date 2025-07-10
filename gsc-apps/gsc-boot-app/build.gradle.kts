plugins {
    id("java-library")
}
dependencies {
    // 依赖 system 模块
    implementation(project(":gsc-biz:gsc-biz-system:gsc-biz-system-impl"))
    implementation("com.baomidou:mybatis-plus-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
}
