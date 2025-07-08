rootProject.name = "gsc"
// 包含所有子模块
val modules = listOf(
    "gsc-biz",
    "gsc-biz:gsc-biz-common",
    "gsc-biz:gsc-biz-system",
    "gsc-starter",
    "gsc-starter:gsc-local-starter",
    "gsc-starter:gsc-cloud-starter",
    "gsc-starter:gsc-cloud-starter:gsc-cloud-gateway",
    "gsc-starter:gsc-cloud-starter:gsc-cloud-system"
)
modules.forEach { moduleName ->
    include(moduleName)
    findProject(":$moduleName")?.name = moduleName.split(":").last()
}
