plugins {
    id("org.springframework.boot") version "3.2.9"
    id("io.spring.dependency-management") version "1.1.5"
    java
}
group = "com.lwd"
version = "0.0.1-SNAPSHOT"
allprojects {
    repositories {
        mavenLocal()
        maven {
            url = uri("http://10.8.33.254:8081/repository/maven-public/")
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
    apply(plugin = "java-library")
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.h2database:h2")
    }
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
        }
    }
    tasks.bootJar {
        enabled = true
    }
    tasks.test {
        useJUnitPlatform()
    }
}
tasks.bootJar {
    enabled = false
}
