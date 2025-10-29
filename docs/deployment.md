# 部署方式

学之思考试系统支持多种部署方式，包括集成部署、前后端分离部署和Docker部署。本文档整合了原release/README的内容，并基于实际部署经验进行了更新。

## 🏗️ 部署方式对比

| 部署方式 | 优点 | 缺点 | 适用场景 |
|----------|------|------|----------|
| 集成部署 | 部署简单，一个JAR文件 | 前后端耦合 | 小型项目，快速部署 |
| 分离部署 | 前后端独立，易于扩展 | 配置复杂 | 生产环境，高并发 |
| Docker部署 | 环境隔离，一键启动 | 资源消耗 | 容器化环境 |

## 📦 集成部署

集成部署是将前后端打包在一个JAR文件中，部署简单快捷。

### 部署步骤

#### 1. 前端打包

**重要更新**: 使用sass替代node-sass解决编译问题

```bash
# 打包管理员前端
cd source/vue/xzs-admin
npm install
npm run build

# 打包学生前端
cd ../xzs-student
npm install
npm run build
```

#### 2. 复制静态文件

将打包后的前端文件复制到后端静态资源目录：

```bash
# 复制管理员前端
cp -r source/vue/xzs-admin/dist/* source/xzs/src/main/resources/static/admin/

# 复制学生前端
cp -r source/vue/xzs-student/dist/* source/xzs/src/main/resources/static/student/
```

#### 3. 后端打包

```bash
cd source/xzs
mvn clean package -DskipTests
```

#### 4. 数据库配置

修改生产环境配置文件 `application-prod.yml`：

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/xzs
    username: postgres
    password: 123456
```

#### 5. 启动应用

```bash
# 生产环境启动
nohup java -Duser.timezone=Asia/Shanghai -jar -Dspring.profiles.active=prod xzs-3.9.0.jar > start1.log 2>&1 &

# 开发环境启动
java -jar target/xzs-3.9.0.jar --server.port=8000
```

#### 6. 访问系统

- **学生端**: http://ip:8000/student
- **管理员端**: http://ip:8000/admin

## 🌐 前后端分离部署

前后端分离部署将前端和后端独立部署，支持负载均衡和独立扩展。

### 后端部署

后端部署方式与集成部署相同：

```bash
cd source/xzs
mvn clean package -DskipTests
java -jar target/xzs-3.9.0.jar --server.port=8000
```

### 前端部署

#### 1. 前端打包

```bash
# 打包两个前端项目
cd source/vue/xzs-admin && npm run build
cd ../xzs-student && npm run build
```

#### 2. Nginx配置

创建nginx配置文件 `/usr/local/nginx/conf/nginx.conf`：

```nginx
server {
    listen      8001;
    server_name xzs;

    # 学生端
    location /student {
        alias /usr/local/xzs/web/student;
        index index.html;
        try_files $uri $uri/ /student/index.html;
    }

    # 管理员端
    location /admin {
        alias /usr/local/xzs/web/admin;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }

    # API代理
    location /api/ {
        proxy_pass  http://localhost:8000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

#### 3. 部署静态文件

```bash
# 创建部署目录
mkdir -p /usr/local/xzs/web

# 复制前端文件
cp -r source/vue/xzs-admin/dist/* /usr/local/xzs/web/admin/
cp -r source/vue/xzs-student/dist/* /usr/local/xzs/web/student/
```

#### 4. 启动Nginx

```bash
# 检查nginx配置
nginx -t

# 启动nginx
nginx

# 重启nginx
nginx -s reload
```

#### 5. 访问系统

- **学生端**: http://ip:8001/student
- **管理员端**: http://ip:8001/admin

## 🐳 Docker部署

Docker部署提供环境隔离和快速部署能力。

### 环境准备

#### 1. 安装Docker和Docker Compose

```bash
# 安装Docker
# 参考: https://docs.docker.com/engine/install/

# 安装Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/v2.20.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# 验证安装
docker --version
docker-compose --version
```

#### 2. 准备SQL文件

下载SQL脚本并修改：

```sql
-- 在SQL文件开头添加
CREATE DATABASE xzs CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE xzs;
```

将修改后的SQL文件移动到 `docker/sql/` 目录。

### 部署步骤

#### 1. 获取Docker配置

从官方仓库获取Docker配置文件：

```bash
# 克隆Docker配置仓库
git clone https://gitee.com/mindskip/xzs-mysql.git

# 复制Docker文件
cp -r xzs-mysql/docker/* /usr/local/xzs/
```

#### 2. 配置环境

编辑Docker配置文件，确保数据库连接信息正确：

```yaml
# docker-compose.yml 示例
version: '3.8'
services:
  postgres:
    image: postgres:16
    environment:
      POSTGRES_DB: xzs
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123456
    volumes:
      - ./sql:/docker-entrypoint-initdb.d
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  app:
    build: .
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/xzs
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 123456
    ports:
      - "8000:8000"
    depends_on:
      - postgres

volumes:
  postgres_data:
```

#### 3. 启动服务

```bash
cd /usr/local/xzs
docker-compose up -d
```

#### 4. 查看日志

```bash
# 查看应用日志
docker-compose logs app

# 查看数据库日志
docker-compose logs postgres
```

#### 5. 访问系统

- **学生端**: http://ip:8000/student
- **管理员端**: http://ip:8000/admin

## 🔧 配置说明

### 数据库配置

**PostgreSQL配置** (当前版本):
- 数据库: xzs
- 用户: postgres
- 密码: 123456
- 端口: 5432

**MySQL配置** (原版):
- 数据库: xzs
- 用户: root
- 密码: 123456
- 端口: 3306

### 端口配置

- **后端API**: 8000
- **前端访问** (分离部署): 8001
- **数据库**: 5432 (PostgreSQL)

### 防火墙配置

确保相关端口已开放：

```bash
# 检查端口状态
netstat -tulpn | grep :8000
netstat -tulpn | grep :8001

# 开放端口 (CentOS)
firewall-cmd --zone=public --add-port=8000/tcp --permanent
firewall-cmd --zone=public --add-port=8001/tcp --permanent
firewall-cmd --reload
```

## ✅ 部署验证

### 集成部署验证

```bash
# 检查应用状态
curl -s http://localhost:8000/actuator/health

# 检查前端访问
curl -s http://localhost:8000/student/ | head -n 5
curl -s http://localhost:8000/admin/ | head -n 5
```

### 分离部署验证

```bash
# 检查Nginx状态
systemctl status nginx

# 检查前端访问
curl -s http://localhost:8001/student/ | head -n 5

# 检查API代理
curl -s http://localhost:8001/api/admin/dashboard/index
```

### Docker部署验证

```bash
# 检查容器状态
docker-compose ps

# 检查应用日志
docker-compose logs app
```

## 🎯 总结

学之思考试系统提供了灵活的部署方式：

- **集成部署**: 适合快速部署和小型项目
- **分离部署**: 适合生产环境和高并发场景
- **Docker部署**: 适合容器化环境和快速扩展

**重要更新**: 本文档基于实际部署经验，包含了前端编译问题的解决方案（使用sass替代node-sass）和PostgreSQL数据库配置。

**下一步**: [常见问题](./faq.md)