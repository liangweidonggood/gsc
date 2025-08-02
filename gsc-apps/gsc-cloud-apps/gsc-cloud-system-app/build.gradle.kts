import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

plugins {
    id("java-library")
}

dependencies {
    //springboot 核心
    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //数据库
    implementation("org.postgresql:postgresql")
    implementation("com.alibaba:druid-spring-boot-3-starter")

    //模块依赖
    implementation(project(":gsc-biz:gsc-biz-common:gsc-biz-common-core"))
    implementation(project(":gsc-biz:gsc-biz-common:gsc-biz-common-config"))
    implementation(project(":gsc-biz:gsc-biz-system:gsc-biz-system-impl"))
}
