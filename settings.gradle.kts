rootProject.name = "gsc"
// 包含所有子模块
val modules = listOf(
    "gsc-biz",
    "gsc-biz:gsc-biz-common",
    "gsc-biz:gsc-biz-common:gsc-biz-common-core",
    "gsc-biz:gsc-biz-common:gsc-biz-common-datasource",
    "gsc-biz:gsc-biz-common:gsc-biz-common-config",
    "gsc-biz:gsc-biz-system",
    "gsc-biz:gsc-biz-system:gsc-biz-system-api",
    "gsc-biz:gsc-biz-system:gsc-biz-system-impl",
    "gsc-apps",
    "gsc-apps:gsc-boot-app",
    "gsc-apps:gsc-cloud-apps",
    "gsc-apps:gsc-cloud-apps:gsc-cloud-auth-app",
    "gsc-apps:gsc-cloud-apps:gsc-cloud-doc-app",
    "gsc-apps:gsc-cloud-apps:gsc-cloud-gateway-app",
    "gsc-apps:gsc-cloud-apps:gsc-cloud-system-app"
)
modules.forEach { moduleName ->
    include(moduleName)
    findProject(":$moduleName")?.name = moduleName.split(":").last()
}
