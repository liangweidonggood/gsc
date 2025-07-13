plugins {
    id("org.springframework.boot") version "3.2.9"
    id("io.spring.dependency-management") version "1.1.5"
}
allprojects {
    repositories {
        mavenLocal()
        maven {
            url = uri("http://123.52.43.113:13002/repository/maven-public/")
            isAllowInsecureProtocol = true
        }
        maven { url = uri("https://repo.spring.io/release") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        mavenCentral()
        gradlePluginPortal()
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2023.0.3.3")
        }
        dependencies {
            dependency("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.12")
            dependency("com.baomidou:mybatis-plus-boot-starter-test:3.5.12")
            dependency("mysql:mysql-connector-java:8.0.33")
            dependency("com.alibaba:druid-spring-boot-3-starter:1.2.25")
            dependency("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.4.0")
            dependency("io.jsonwebtoken:jjwt-api:0.12.6")
            dependency("io.jsonwebtoken:jjwt-impl:0.12.6")
            dependency("io.jsonwebtoken:jjwt-jackson:0.12.6")
        }
    }
}
