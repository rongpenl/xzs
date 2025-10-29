# 学之思考试系统 - 完整部署指南

## 🎯 项目概述

学之思开源考试系统是一款基于 **Java + Vue + PostgreSQL** 的前后端分离考试系统。支持Web端和微信小程序，能覆盖到PC机和手机等设备。

### ✨ 主要功能

#### 学生系统功能
- **登录注册** - 用户名密码登录，年级注册
- **任务中心** - 管理员发布的年级任务
- **考试系统** - 固定试卷、时段试卷、任务试卷
- **考试记录** - 查看答卷记录和试卷信息
- **错题本** - 自动收集答错题目
- **个人信息** - 个人资料管理和头像更新
- **消息中心** - 接收管理员发送的消息

#### 管理系统功能
- **用户管理** - 学生和管理员管理
- **学科管理** - 学科查询、创建、修改
- **试卷管理** - 试卷创建、编辑、删除
- **题目管理** - 单选题、多选题、判断题、填空题、简答题
- **任务管理** - 任务创建和管理
- **消息管理** - 消息发送和统计
- **数据统计** - 用户活跃度、题目数量统计

### 🏗️ 技术架构

```
前端 (Vue 2.7 + Element UI)
├── xzs-admin     - 管理系统前端
└── xzs-student   - 学生系统前端

后端 (Spring Boot 2.1.6 + MyBatis)
├── API接口服务
├── 权限认证 (Spring Security)
├── 数据访问层
└── 事件处理系统

数据库 (PostgreSQL 16)
├── 13个核心表
└── 完整的事务支持
```

## 🚀 快速开始（5分钟部署）

### 环境要求
- **Node.js**: 16.20.2 (推荐使用nvm管理)
- **Java**: OpenJDK 8
- **Maven**: 3.6+
- **PostgreSQL**: 16
- **Python**: 3.8+ (用于前端编译)

### 快速部署步骤

1. **前端编译**
   ```bash
   cd source/vue/xzs-admin
   npm install
   npm run build

   cd ../xzs-student
   npm install
   npm run build
   ```

2. **后端启动**
   ```bash
   cd source/xzs
   mvn clean package -DskipTests
   java -jar target/xzs-3.9.0.jar --server.port=8000
   ```

3. **数据库准备**
   ```sql
   CREATE DATABASE xzs;
   psql -h localhost -U postgres -d xzs -f release/xzs-postgresql.sql
   ```

4. **访问系统**
   - 学生端: http://localhost:8000/student
   - 管理员端: http://localhost:8000/admin

## 📚 详细文档

- [前端编译问题解决](./frontend-setup.md) - 解决node-sass编译问题
- [后端编译和运行](./backend-setup.md) - Java后端项目编译部署
- [数据库配置](./database-setup.md) - PostgreSQL数据库准备
- [部署方式](./deployment.md) - 集成部署、分离部署、Docker部署
- [常见问题](./faq.md) - 部署过程中遇到的问题和解决方案

## 🔧 项目特色

### 架构优势
- **分层架构**: 清晰的Controller-Service-Repository分层
- **权限分离**: 管理员、学生、微信三个独立的权限体系
- **模块化设计**: 按功能模块组织代码结构
- **统一响应**: 使用RestResponse统一API响应格式
- **事件驱动**: 支持事件监听机制

### 部署灵活性
- **集成部署**: 前后端打包在一个JAR文件中
- **分离部署**: 前后端独立部署，支持负载均衡
- **Docker部署**: 容器化部署，一键启动

## 📊 项目统计

- **后端文件**: 162个Java文件
- **API接口**: 94个REST端点
- **数据库表**: 13个核心表
- **前端项目**: 2个独立前端应用

## 🆕 重要更新

### 前端编译问题解决
- **问题**: Python 3.13与node-gyp不兼容
- **解决方案**: 将node-sass替换为sass包
- **影响**: 消除了编译工具链依赖，提高可维护性

### 数据库迁移
- **原版**: MySQL
- **当前版本**: PostgreSQL 16
- **优势**: 更好的性能和扩展性

---

**注意**: 本文档基于实际部署经验编写，包含了原release/README的所有内容，并进行了更新和完善。本文档是项目的唯一权威信息来源。

**开始部署** → [前端编译问题解决](./frontend-setup.md)