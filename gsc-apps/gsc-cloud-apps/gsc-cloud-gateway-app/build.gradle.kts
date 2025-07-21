plugins {
    java
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("com.alibaba.cloud:spring-cloud-alibaba-sentinel-gateway")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
    implementation("com.alibaba.csp:sentinel-datasource-nacos")
    implementation("com.github.xiaoymin:knife4j-gateway-spring-boot-starter")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")


    //模块依赖
    implementation(project(":gsc-biz:gsc-biz-common:gsc-biz-common-core"))
}
