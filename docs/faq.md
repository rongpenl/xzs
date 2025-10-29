# 常见问题

本文档整理了在部署学之思考试系统过程中遇到的常见问题及其解决方案。

## 🚨 前端编译问题

### Q1: node-sass编译失败，错误信息：`ValueError: invalid mode: 'rU'`

**问题描述**:
```bash
ValueError: invalid mode: 'rU' while trying to load binding.gyp
```

**根本原因**: Python 3.13移除了'rU'文件打开模式，与node-gyp不兼容。

**解决方案**:
```bash
# 卸载node-sass
npm uninstall node-sass

# 安装sass替代
npm install sass sass-loader@^10 --save-dev

# 重新安装依赖
npm install
```

**详细说明**: 参考 [前端编译问题解决](./frontend-setup.md)

### Q2: fsevents版本冲突警告

**问题描述**:
```bash
TypeError: fsevents.watch is not a function
```

**解决方案**: 这个警告不影响核心功能，可以忽略。fsevents是macOS的文件监控工具。

### Q3: 前端编译成功但无法访问

**可能原因**:
1. 静态文件未正确复制到后端资源目录
2. 后端服务未正确启动
3. 端口被占用

**解决方案**:
```bash
# 检查静态文件位置
ls -la source/xzs/src/main/resources/static/
# 应该看到 admin/ 和 student/ 目录

# 检查后端服务状态
curl -s http://localhost:8000/actuator/health

# 检查端口占用
lsof -ti:8000
```

## 🖥️ 后端运行问题

### Q4: 端口8000被占用

**错误信息**:
```
Web server failed to start. Port 8000 was already in use.
```

**解决方案**:
```bash
# 查找占用端口的进程
lsof -ti:8000
# 8163

# 杀死占用进程
kill -9 8163

# 重新启动应用
java -jar target/xzs-3.9.0.jar --server.port=8000
```

### Q5: 数据库连接失败

**错误信息**:
```
org.postgresql.util.PSQLException: Connection to localhost:5432 refused.
```

**解决方案**:
```bash
# 检查PostgreSQL服务状态
brew services list | grep postgresql

# 启动PostgreSQL服务
brew services start postgresql

# 验证数据库连接
psql -h localhost -U postgres -d xzs -c "SELECT version();"
```

### Q6: 表不存在错误

**错误信息**:
```
ERROR: relation "t_user" does not exist
```

**解决方案**:
```bash
# 执行SQL脚本创建表
psql -h localhost -U postgres -d xzs -f release/xzs-postgresql.sql

# 验证表结构
psql -h localhost -U postgres -d xzs -c "\dt"
```

## 🗄️ 数据库问题

### Q7: PostgreSQL服务无法启动

**解决方案**:
```bash
# 检查PostgreSQL日志
tail -f /usr/local/var/log/postgresql@16.log

# 重新安装PostgreSQL
brew reinstall postgresql@16

# 初始化数据库
initdb /usr/local/var/postgresql@16
```

### Q8: 数据库权限问题

**错误信息**:
```
FATAL:  password authentication failed for user "postgres"
```

**解决方案**:
```bash
# 重置postgres用户密码
psql -h localhost -U postgres -c "ALTER USER postgres WITH PASSWORD '123456';"

# 或者修改pg_hba.conf文件
# 将认证方式改为 md5 或 trust
```

### Q9: DBeaver连接失败

**解决方案**:
1. 检查连接参数：
   - 主机: localhost
   - 端口: 5432
   - 数据库: xzs
   - 用户名: postgres
   - 密码: 123456

2. 检查PostgreSQL服务是否运行
3. 检查防火墙设置

## 🔧 部署相关问题

### Q10: 为什么只启动后端服务就能看到完整前端？

**原因**: Spring Boot默认会服务`/static/`目录下的静态文件。

**详细说明**:
- 前端文件被打包到JAR文件中
- 后端服务同时提供API和静态文件服务
- 这是集成部署模式的特点

**参考**: [后端编译和运行](./backend-setup.md#集成部署模式)

### Q11: 集成部署 vs 分离部署的区别

| 特性 | 集成部署 | 分离部署 |
|------|----------|----------|
| 部署复杂度 | 简单 | 复杂 |
| 前后端耦合 | 高 | 低 |
| 扩展性 | 有限 | 高 |
| 适用场景 | 小型项目 | 生产环境 |

### Q12: 如何从集成部署切换到分离部署？

**步骤**:
1. 停止集成部署的后端服务
2. 配置Nginx作为前端服务器
3. 修改前端API调用地址
4. 分别启动后端和前端服务

**详细步骤**: 参考 [部署方式](./deployment.md#前后端分离部署)

## 🛠️ 环境配置问题

### Q13: Java版本兼容性问题

**要求**: OpenJDK 8

**检查方法**:
```bash
java -version
# 应该显示: openjdk version "1.8.0_xxx"
```

**解决方案**:
```bash
# 安装OpenJDK 8
brew install openjdk@8

# 设置环境变量
export JAVA_HOME=/usr/local/opt/openjdk@8/libexec/openjdk.jdk/Contents/Home
export PATH="/usr/local/opt/openjdk@8/bin:$PATH"
```

### Q14: Maven依赖下载失败

**解决方案**:
```bash
# 清理本地Maven仓库
rm -rf ~/.m2/repository/

# 重新下载依赖
mvn clean compile

# 或者使用国内镜像
# 在settings.xml中配置阿里云镜像
```

### Q15: Node.js版本问题

**要求**: Node.js 16.20.2

**解决方案**:
```bash
# 使用nvm管理Node.js版本
nvm use 16.20.2

# 如果没有安装，先安装
nvm install 16.20.2
```

## 📊 性能优化问题

### Q16: 应用启动慢

**可能原因**:
1. 数据库连接池配置不当
2. JVM内存配置不足
3. 依赖加载过多

**解决方案**:
```bash
# 增加JVM内存
java -Xmx1024m -Xms512m -jar target/xzs-3.9.0.jar

# 优化数据库连接池
# 在application.yml中配置
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
```

### Q17: 数据库查询慢

**解决方案**:
1. 为常用查询字段添加索引
2. 优化SQL查询语句
3. 配置数据库连接池
4. 使用数据库性能监控工具

## 🔍 调试和日志

### Q18: 如何查看应用日志？

**集成部署**:
```bash
# 查看启动日志
tail -f start1.log

# 或者直接查看控制台输出
java -jar target/xzs-3.9.0.jar
```

**Docker部署**:
```bash
# 查看容器日志
docker-compose logs app

# 实时查看日志
docker-compose logs -f app
```

### Q19: 如何启用调试模式？

**后端调试**:
```bash
# 启用Spring Boot调试模式
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/xzs-3.9.0.jar
```

**前端调试**:
```bash
# 启用开发模式
npm run serve

# 访问 http://localhost:8080
```

## 🎯 总结

本文档涵盖了部署学之思考试系统过程中可能遇到的大部分问题。如果遇到本文档未涵盖的问题，建议：

1. 检查相关服务的日志文件
2. 验证环境配置是否正确
3. 参考对应章节的详细文档
4. 在项目仓库中搜索相关issue

**返回**: [首页](./index.md)