# 数据库配置

## 🗄️ 数据库环境

学之思考试系统使用 **PostgreSQL 16** 作为数据库，相比原版的MySQL，PostgreSQL提供了更好的性能和扩展性。

### 环境要求

- **PostgreSQL**: 16.0+
- **数据库**: xzs
- **用户**: postgres
- **密码**: 123456

## 🚀 快速配置

### 1. 启动PostgreSQL服务

```bash
# 检查PostgreSQL服务状态
brew services list | grep postgresql

# 如果未启动，启动服务
brew services start postgresql
```

### 2. 创建数据库

```bash
# 连接到PostgreSQL
psql -h localhost -U postgres

# 创建数据库
CREATE DATABASE xzs;

# 退出
\q
```

### 3. 导入数据

```bash
# 执行SQL脚本创建表结构
psql -h localhost -U postgres -d xzs -f release/xzs-postgresql.sql
```

## 📊 数据库结构

### 核心表列表

系统包含13个核心表：

| 表名 | 描述 |
|------|------|
| t_user | 用户表 |
| t_subject | 学科表 |
| t_question | 题目表 |
| t_exam_paper | 试卷表 |
| t_exam_paper_answer | 答题记录表 |
| t_exam_paper_question_customer_answer | 题目答题表 |
| t_text_content | 文本内容表 |
| t_message | 消息表 |
| t_message_user | 用户消息关系表 |
| t_task_exam | 任务考试表 |
| t_task_exam_customer_answer | 任务答题表 |
| t_user_event_log | 用户事件日志表 |
| t_user_token | 用户令牌表 |

### 表结构验证

```bash
# 连接到xzs数据库
psql -h localhost -U postgres -d xzs

# 查看所有表
\dt

# 查看表结构
\d t_user
\d t_exam_paper
```

## 🔧 详细配置步骤

### 1. PostgreSQL安装和配置

#### 安装PostgreSQL

```bash
# 使用Homebrew安装PostgreSQL
brew install postgresql@16

# 启动服务
brew services start postgresql@16
```

#### 配置连接

默认连接信息：
- **主机**: localhost
- **端口**: 5432
- **用户名**: postgres
- **密码**: 123456

### 2. 数据库创建

```bash
# 创建数据库
psql -h localhost -U postgres -c "CREATE DATABASE xzs;"

# 验证数据库创建
psql -h localhost -U postgres -l
```

### 3. SQL脚本执行

#### 检查SQL文件

```bash
# 查看SQL文件内容
head -n 20 release/xzs-postgresql.sql

# 检查表创建语句
grep -i "create table" release/xzs-postgresql.sql | wc -l
# 13
```

#### 执行SQL脚本

```bash
# 执行完整的SQL脚本
psql -h localhost -U postgres -d xzs -f release/xzs-postgresql.sql
```

### 4. 数据验证

```bash
# 连接到数据库验证表结构
psql -h localhost -U postgres -d xzs

# 查看所有表
xzs=# \dt
                    List of relations
 Schema |              Name               | Type  |  Owner
--------+----------------------------------+-------+----------
 public | t_exam_paper                    | table | postgres
 public | t_exam_paper_answer             | table | postgres
 public | t_exam_paper_question_customer_answer | table | postgres
 public | t_message                       | table | postgres
 public | t_message_user                  | table | postgres
 public | t_question                      | table | postgres
 public | t_subject                       | table | postgres
 public | t_task_exam                     | table | postgres
 public | t_task_exam_customer_answer     | table | postgres
 public | t_text_content                  | table | postgres
 public | t_user                          | table | postgres
 public | t_user_event_log                | table | postgres
 public | t_user_token                    | table | postgres
(13 rows)

# 查看用户表数据
xzs=# SELECT COUNT(*) FROM t_user;
 count
-------
     0
(1 row)

# 退出
xzs=# \q
```

## 🔗 数据库连接配置

### 后端应用配置

配置文件位置：`source/xzs/src/main/resources/application-dev.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/xzs
    username: postgres
    password: 123456
    driver-class-name: org.postgresql.Driver
```

### DBeaver连接配置

对于使用DBeaver管理数据库的用户：

1. **新建连接** → **PostgreSQL**
2. **连接设置**:
   - 主机: localhost
   - 端口: 5432
   - 数据库: xzs
   - 用户名: postgres
   - 密码: 123456

## ⚠️ 常见问题

### 1. 连接拒绝错误

**错误信息**:
```
psql: error: connection to server at "localhost" (::1), port 5432 failed
```

**解决方案**:
```bash
# 检查PostgreSQL服务状态
brew services list | grep postgresql

# 重启服务
brew services restart postgresql
```

### 2. 认证失败

**错误信息**:
```
psql: error: connection to server at "localhost" (::1), port 5432 failed:
FATAL:  password authentication failed for user "postgres"
```

**解决方案**:
- 检查密码是否正确（默认: 123456）
- 重置postgres用户密码

### 3. 数据库不存在

**错误信息**:
```
psql: error: connection to server at "localhost" (::1), port 5432 failed:
FATAL:  database "xzs" does not exist
```

**解决方案**:
```bash
# 创建数据库
psql -h localhost -U postgres -c "CREATE DATABASE xzs;"
```

### 4. SQL执行错误

如果SQL文件执行出错，检查：
- SQL文件编码（应为UTF-8）
- PostgreSQL版本兼容性
- 表名和列名冲突

## ✅ 验证步骤

### 1. 服务状态验证

```bash
brew services list | grep postgresql
# postgresql started
```

### 2. 数据库连接验证

```bash
psql -h localhost -U postgres -d xzs -c "SELECT version();"
# PostgreSQL 16.0...
```

### 3. 表结构验证

```bash
psql -h localhost -U postgres -d xzs -c "\dt" | wc -l
# 14 (13 tables + header)
```

### 4. 应用连接验证

启动后端应用后，检查日志中是否有数据库连接错误。

## 🎯 总结

数据库配置是系统正常运行的关键。通过以上步骤，我们成功：

- ✅ 安装和配置了PostgreSQL 16
- ✅ 创建了xzs数据库
- ✅ 执行SQL脚本创建了13个核心表
- ✅ 验证了数据库连接和表结构
- ✅ 配置了后端应用的数据库连接

**下一步**: [部署方式](./deployment.md)