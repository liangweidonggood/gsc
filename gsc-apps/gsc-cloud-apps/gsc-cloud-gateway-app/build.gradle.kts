plugins {
    java
}

dependencies {
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("com.alibaba.cloud:spring-cloud-alibaba-sentinel-gateway")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
    implementation("com.alibaba.csp:sentinel-datasource-nacos")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
