dependencies {
    implementation(project(":gsc-biz:gsc-biz-common"))
    api("com.baomidou:mybatis-plus-spring-boot3-starter")
    implementation("mysql:mysql-connector-java")
}

tasks.bootJar{
    enabled = false
}
