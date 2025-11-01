# 后端编译和运行

## 🏗️ 项目架构概述

学之思考试系统后端采用 **Spring Boot 2.1.6 + MyBatis + PostgreSQL** 技术栈，具有清晰的分层架构。

### 项目结构

```
com.mindskip.xzs/
├── XzsApplication.java           # Spring Boot启动类
├── base/                         # 基础类
├── configuration/                # 配置类
├── controller/                   # 控制器层
├── domain/                       # 实体层
├── repository/                   # 数据访问层
├── service/                      # 服务层
├── utility/                      # 工具类
└── viewmodel/                    # 视图模型
```

### 核心模块

- **控制器**: 25个控制器，支持管理员、学生、微信三个权限体系
- **服务层**: 29个服务（接口+实现）
- **实体类**: 26个数据实体
- **API接口**: 94个REST端点

## 🔧 环境准备

### 1. Java环境

**要求**: OpenJDK 8

```bash
# 检查Java版本
java -version
# openjdk version "1.8.0_412"

# 设置JAVA_HOME (macOS)
export JAVA_HOME=/usr/local/opt/openjdk@8/libexec/openjdk.jdk/Contents/Home
export PATH="/usr/local/opt/openjdk@8/bin:$PATH"
```

### 2. Maven环境

**要求**: Maven 3.6+

```bash
# 检查Maven版本
mvn -version
# Apache Maven 3.9.6
```

### 3. 项目依赖

项目使用Maven管理依赖，主要依赖包括：
- Spring Boot 2.1.6.RELEASE
- MyBatis Spring Boot Starter 2.1.0
- PostgreSQL JDBC Driver 42.2.6
- Spring Security

## 🚀 编译和运行

### 1. 项目编译

```bash
# 进入后端项目目录
cd source/xzs

# 清理并编译项目
mvn clean compile

# 打包项目（跳过测试）
mvn clean package -DskipTests
```

### 2. 运行应用

```bash
# 启动Spring Boot应用（端口8000）
java -jar target/system-1.0.0.jar --server.port=8000
```

### 3. 验证运行状态

```bash
# 检查应用健康状态
curl -s http://localhost:8000/actuator/health
# {"status":"UP"}

# 检查API接口
curl -s http://localhost:8000/api/admin/dashboard/index
# {"code":200,"message":"成功","response":{...}}
```

## 📊 重要发现

### 集成部署模式

在编译和运行过程中，我们发现了一个重要特性：

**后端服务包含了打包的前端静态文件**

```bash
# 检查静态资源目录
ls -la src/main/resources/static/
# admin/    student/
```

这意味着：
- 前端文件被打包到JAR文件中
- 后端服务同时提供API和静态文件服务
- 访问 `http://localhost:8000/student/` 和 `http://localhost:8000/admin/` 可以直接访问前端界面

### Spring Boot静态资源服务

Spring Boot默认会服务以下目录的静态文件：
- `/static/`
- `/public/`
- `/resources/`

访问路径映射：
- `http://localhost:8000/student/` → `static/student/index.html`
- `http://localhost:8000/admin/` → `static/admin/index.html`

## ⚠️ 常见问题

### 1. 端口占用问题

**错误信息**:
```
Web server failed to start. Port 8000 was already in use.
```

**解决方案**:
```bash
# 查找占用8000端口的进程
lsof -ti:8000
# 8163

# 杀死占用进程
kill -9 8163

# 重新启动应用
java -jar target/xzs-3.9.0.jar --server.port=8000
```

### 2. 数据库连接问题

如果数据库未正确配置，会出现连接错误：
```
org.postgresql.util.PSQLException: Connection to localhost:5432 refused.
```

确保PostgreSQL服务正在运行：
```bash
# 检查PostgreSQL服务状态
brew services list | grep postgresql
# postgresql started
```

### 3. 内存不足问题

对于较大的项目，可能需要增加JVM内存：
```bash
java -Xmx1024m -Xms512m -jar target/xzs-3.9.0.jar --server.port=8000
```

## 🔍 项目架构深入理解

### 分层架构

1. **Controller层** - 处理HTTP请求和响应
2. **Service层** - 业务逻辑处理
3. **Repository层** - 数据访问
4. **Domain层** - 数据实体定义

### 权限系统

- **管理员权限**: `/api/admin/*`
- **学生权限**: `/api/student/*`
- **微信权限**: `/api/wx/*`

### 事务管理

项目使用了Spring的声明式事务管理：
```java
@Transactional
public void insertUsers(List<User> users) {
    // 事务操作
}
```

## ✅ 验证步骤

### 1. 编译验证

```bash
cd source/xzs
mvn clean compile
# ✓ BUILD SUCCESS
```

### 2. 打包验证

```bash
mvn clean package -DskipTests
# ✓ BUILD SUCCESS
# ✓ 生成target/xzs-3.9.0.jar
```

### 3. 运行验证

```bash
java -jar target/xzs-3.9.0.jar --server.port=8000
# ✓ Started XzsApplication in 8.234 seconds
```

### 4. 功能验证

```bash
# 验证健康检查
curl -s http://localhost:8000/actuator/health | jq .
# {"status":"UP"}

# 验证前端访问
curl -s http://localhost:8000/student/ | head -n 5
# <!DOCTYPE html><html lang="en">...
```

## 🎯 总结

后端项目编译和运行过程相对直接，主要依赖Maven和Java环境。关键发现是项目的集成部署特性 - 后端JAR文件包含了完整的前端静态资源，这使得部署更加简单。

**下一步**: [数据库配置](./database-setup.md)