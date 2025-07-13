startup.cmd -m standalone

# 技术栈

部署和远维


|          | 名称       | 版本   | 说明    |
| -------- | ---------- | ------ | ------- |
| 容器     | docker     | 24     |         |
| 容器编排 | k8s        | 1.28.9 |         |
| 监控显示 | granfana   |        |         |
| 日志采集 | loki       |        | 备选elk |
| 监控     | prometheus |        |         |
| 远程     | MobaXterm  |        |         |

设计


|            | 名称          | 版本 | 说明 |
| ---------- | ------------- | ---- | ---- |
| 原型       | axure         | 9    |      |
| 数据库设计 | powerdesigner | 16   |      |
| 架构设计   | draw.io       |      |      |
| 脑图       | Xmind         |      |      |

开发工具


|          | 名称   | 版本   | 说明 |
| -------- | ------ | ------ | ---- |
| 开发工具 | idea   | 2025.1 |      |
| 文档工具 | vscode |        |      |

中间件


|               | 名称        | 版本  | 说明                                                       |
| ------------- | ----------- | ----- | ---------------------------------------------------------- |
| 数据库        | postgresql  | 17    | postgresql-17.5-3-windows-x64.exe                          |
| 时序数据库    | timescaledb |       | pgsql插件<br />timescaledb-postgresql-17-windows-amd64.zip |
| 缓存          | redis       | 8.0.3 |                                                            |
| 消息队列/mqtt | rabbitmq    |       | 备选apache-pulsar                                          |
| 前端展示      | nginx       | 1.24  |                                                            |

后端


|                | 名称               | 版本       | 说明                 |
| -------------- | ------------------ | ---------- | -------------------- |
| 开发语言       | java               | 21         |                      |
| 构建工具       | gradle             | 8.14       |                      |
| 后端底座       | springboot         | 3.2.9      |                      |
| 微服务         | springcloud        | 2023.0.3   |                      |
| 微服务实现     | springcloudalibaba | 2023.0.3.3 |                      |
| 网关           | gateway            |            |                      |
| 服务中心       | nacos              | 3.0.2      |                      |
| 配置中心       | nacos              |            |                      |
| 服务调用       | openfeign          |            | 备选feign            |
| 权限认证       | springsecurity     |            | 备选shiro,sa-token   |
| 链路追踪       | skywalking         |            |                      |
| 数据持久层     | springjpa          |            |                      |
| 分布式事务     | seata              |            |                      |
| 断路器         | sentinel           |            |                      |
| 分布式定时任务 | snailjob           |            | 备选xxljob，powerjob |
| 工作流         | flowable           |            |                      |

前端


|          | 名称       | 版本 | 说明 |
| -------- | ---------- | ---- | ---- |
| 前端     | vue        | 3    |      |
| ui       | ant design |      |      |
| 前端脚本 | typescript |      |      |
| app      | uniapp     |      |      |

。

# 打包

```bash
# 打包
./gradlew --refresh-dependencies build
./gradlew :gsc-apps:gsc-boot-app:build
# 跳过测试
./gradlew :gsc-apps:gsc-boot-app:build -x test
# 运行
java -jar  .\gsc-boot-app.jar  --spring.profiles.active=prod

# 指定外部
D:\deploy\
├── boot-app.jar
├── application-prod.yml
└── logback-spring.xml
java -jar .\gsc-boot-app.jar --spring.profiles.active=test --spring.config.location=./ 
# 可以加上日志配置-Dlogging.config=./logback-spring.xml 但没必要
```

。

# 健康检查

配合k8s部署

```
readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: management-port
  initialDelaySeconds: 600   # 初始延迟设为 10 分钟，确保足够长 启动时间 + 20% 缓冲
  periodSeconds: 20          # 每 20 秒探测一次
  timeoutSeconds: 5          # 单次探测最多等 5 秒
  successThreshold: 1        # HTTP 200 即为成功
  failureThreshold: 5        # 连续失败 5 次标记为 Not Ready

livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: management-port
  initialDelaySeconds: 900   # 初始延迟设为 15 分钟，防止误杀
  periodSeconds: 30          # 每 30 秒探测一次
  timeoutSeconds: 10         # 单次探测最多等 10 秒
  successThreshold: 1
  failureThreshold: 3        # 连续失败 3 次触发容器重启
```

http://localhost:8888/actuator/health/liveness

```bash
http://localhost:8888/actuator
{
  "_links": {
    "self": {
      "href": "http://localhost:8888/actuator",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8888/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8888/actuator/health/{*path}",
      "templated": true
    }
  }
}
http://localhost:8888/actuator/health
{
  "status": "UP",
  "groups": [
    "liveness",
    "readiness"
  ]
}
{
  "status": "UP"
}
```

。

# 服务安装

# postgresql

包含timescaledb

```bash
create schema gsc;
# 
create schema gsc_nacos
DROP SCHEMA gsc_nacos CASCADE;
```

。

## nacos

源码：https://github.com/alibaba/nacos/releases

文档：https://nacos.io/docs/latest

插件：https://github.com/nacos-group/nacos-plugin

支持postgresql:https://github.com/nacos-group/nacos-plugin/tree/develop/nacos-datasource-plugin-ext/nacos-postgresql-datasource-plugin-ext

插件

https://github.com/pig-mesh/nacos-datasource-plugin-pg/tree/master

1.下载插件

```bash
# 下载包
https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.7/postgresql-42.7.7.jar
https://repo1.maven.org/maven2/com/pig4cloud/plugin/nacos-datasource-plugin-postgresql/0.0.7/nacos-datasource-plugin-postgresql-0.0.7.jar
# 最终目录如下
nacos/
├── plugins
│   ├── postgresql-42.7.7.jar
│   └── nacos-datasource-plugin-postgresql-0.0.7.jar
└── target
    └── nacos-server.jar
```

2.修改配置：在application.properties文件中声明postgresql的配置信息：

```properties
spring.datasource.platform=postgresql
db.num=1
db.url.0=jdbc:postgresql://10.8.33.254:5432/postgres?currentSchema=gsc_nacos&tcpKeepAlive=true&reWriteBatchedInserts=true&ApplicationName=nacos_java
db.user=postgres
db.password=hxlkj123456
db.pool.config.driver-class-name=org.postgresql.Driver

# linux生成一个编码，head -c 32 /dev/random | base64
nacos.core.auth.plugin.nacos.token.secret.key=xxcTTuT9L5VLOad57SOGjyG9/WPb9kO5cHS4kXFDOWQ=

nacos.core.auth.server.identity.key=123
nacos.core.auth.server.identity.value=123
```

身份认证和token安全控制的讲解

nacos.core.auth.plugin.nacos.token.secret.key

这是 Nacos 使用 JWT（JSON Web Token）生成和验证 Token 时使用的密钥。
用于加密和解密用户登录后的 Token。
必须是一个 Base64 编码字符串，且长度 ≥32字节（256位）。
如果你开启了 Nacos 的鉴权功能（默认开启），这个配置必须设置正确，否则启动失败。

nacos.core.auth.server.identity.key

这是 集群节点间通信的身份标识 Key，用于节点之间互相认证身份。
在 Nacos 集群部署时，各节点之间通过此 Key 和 Value 来进行身份识别。
如果设置了该 Key，则必须同时设置对应的 value，即下一项。

nacos.core.auth.server.identity.value

与上一个 Key 配合使用，作为 节点间通信的身份凭证 Value。
用于节点之间的 HTTP 请求头中校验身份。
所有节点需要保持一致的 Key/Value 对，否则节点之间无法互相访问，导致集群通信失败。

Nacos 节点之间通信时，会将这两个配置写入请求头中：

identity-key: serverIdentityKey
identity-value: serverIdentityValue

3.导入脚本

脚本文件在nacos-postgresql-datasource-plugin-ext/src/main/resources/schema文件夹下面.

上面操作完成后，

3.启动Nacos,要输三个值

```bash
┌──(lwd💀DESKTOP-CAGBDJD)-[D:/portable/dev/nacos/nacos-server-3.0.2/nacos/bin]                                 112ms  
└─# .\startup.cmd  -m standalone
nacos.core.auth.plugin.nacos.token.secret.key value is empty, please input: 123
nacos.core.auth.plugin.nacos.token.secret.key Updated with new value:
nacos.core.auth.plugin.nacos.token.secret.key=123
----------------------------------
nacos.core.auth.server.identity.key value is empty, please input: 123
nacos.core.auth.server.identity.key Updated with new value:
nacos.core.auth.server.identity.key=123
----------------------------------
nacos.core.auth.server.identity.value value is empty, please input: 123
nacos.core.auth.server.identity.value Updated with new value:
nacos.core.auth.server.identity.value=123
----------------------------------
"nacos is starting with standalone"

```

4.访问：http://localhost:8080

第一次就是初始化密码

nacos/nacos

权限控制/用户列表中可以修改密码:nacos123456

。

# redis

https://github.com/redis/redis/releases

https://github.com/redis-windows/redis-windows

# oauth2

github.clientid:Ov23lir3KsWWFaFLPhtE

secrets:3e34988a71462aeaeb4c9a6a1aca079c9cf0f35d

http://localhost:8888/oauth2/authorization/github

# 服务类型

1.单体服务，适用于本地单机部署

2.微服务，适用于公有化部署

# 模块划分

1.业务模块 gsc-biz
----1.1 公共模块 gsc-biz-commmon
----1.2 系统模块
----1.3 todo。。。

2.启动模块
----2.1 单体启动模块
----2.2 微服务启动模块
--------2.2.1 gateway模块
--------2.2.2 system模块
--------2.2.3 todo。。。
