# 🚀 项目快速启动指南 - 30分钟上手学之思考试系统

## 📋 快速启动清单

### 环境准备（15分钟）

#### 1. 安装Java
- 下载JDK 1.8
- 设置JAVA_HOME环境变量
- 验证：在命令行输入 `java -version`

#### 2. 安装PostgreSQL
- 下载PostgreSQL
- 创建数据库：`xzs`
- 用户名：`postgres`，密码：`123456`

#### 3. 安装开发工具
- 下载IntelliJ IDEA Community Edition
- 安装Spring Boot插件（通常已内置）

### 项目导入（5分钟）

#### 1. 打开IntelliJ IDEA
#### 2. 选择 "Open"
#### 3. 选择项目根目录的 `pom.xml` 文件
#### 4. 等待依赖下载完成

### 首次运行（10分钟）

#### 1. 找到启动文件
- 在项目结构中导航到：
  `src/main/java/com/mindskip/xzs/XzsApplication.java`

#### 2. 运行项目
- 右键点击 `XzsApplication.java`
- 选择 "Run XzsApplication"

#### 3. 验证启动成功
- 在控制台看到类似信息：
  ```
  Started XzsApplication in 5.123 seconds
  Tomcat started on port(s): 8000
  ```

#### 4. 访问系统
- 打开浏览器访问：`http://localhost:8000`
- 看到登录页面即为成功！

## 🎯 第一个任务：理解用户系统

### 任务目标（30分钟）
理解用户从注册到登录的完整流程

### 步骤1：查看用户数据模型（5分钟）
打开文件：`src/main/java/com/mindskip/xzs/domain/User.java`

**重点关注**：
- 用户有哪些属性？
- 角色字段（role）的取值范围？
- 状态字段（status）的含义？

### 步骤2：查看用户服务（5分钟）
打开文件：`src/main/java/com/mindskip/xzs/service/UserService.java`

**理解接口定义**：
- `selectById()` - 根据ID查询用户
- `updateByIdFilter()` - 更新用户信息
- `selectByIds()` - 批量查询用户

### 步骤3：查看用户控制器（10分钟）
打开文件：`src/main/java/com/mindskip/xzs/controller/admin/UserController.java`

**理解API接口**：
- `/api/admin/user/select/{id}` - 查询用户
- 注意 `@PathVariable` 注解的使用

### 步骤4：运行测试验证（10分钟）
打开文件：`src/test/java/com/mindskip/xzs/service/SimpleSubjectServiceTest.java`

**运行测试**：
1. 右键点击测试类
2. 选择 "Run SimpleSubjectServiceTest"
3. 观察所有测试是否通过

## 🔍 代码探索路径

### 第一天：基础理解
- [ ] 运行项目成功
- [ ] 理解User.java数据模型
- [ ] 阅读SimpleSubjectServiceTest.java
- [ ] 尝试修改测试代码

### 第二天：业务逻辑
- [ ] 理解试卷创建流程
- [ ] 查看ExamPaper.java和ExamPaperController.java
- [ ] 运行试卷相关测试

### 第三天：数据库操作
- [ ] 查看UserMapper.xml
- [ ] 理解SQL映射
- [ ] 学习MyBatis基本用法

### 第四天：完整流程
- [ ] 跟踪用户登录完整流程
- [ ] 理解权限控制
- [ ] 查看登录相关的Controller和Service

## 🛠️ 常用开发命令

### Maven命令
```bash
# 编译项目
mvn compile

# 运行测试
mvn test

# 打包项目
mvn package

# 清理并重新编译
mvn clean compile
```

### 数据库操作
```sql
-- 查看用户表
SELECT * FROM t_user;

-- 查看试卷表
SELECT id, name, subject_id FROM t_exam_paper;

-- 查看题目表
SELECT id, question_type, subject_id FROM t_question;
```

## 🐛 常见问题解决

### 问题1：端口8000被占用
**解决方案**：
```bash
# 查找占用端口的进程
lsof -ti:8000

# 杀死进程
kill -9 <进程ID>
```

### 问题2：数据库连接失败
**检查**：
1. PostgreSQL服务是否启动
2. 数据库`xzs`是否存在
3. 用户名密码是否正确
4. 查看`application-dev.yml`配置

### 问题3：依赖下载失败
**解决方案**：
1. 检查网络连接
2. 尝试使用阿里云镜像
3. 删除本地Maven仓库重新下载

### 问题4：测试运行失败
**排查步骤**：
1. 查看错误信息
2. 检查测试数据是否正确
3. 确认数据库连接正常
4. 查看测试日志输出

## 📚 学习资源

### 项目内资源
- `docs/intern-onboarding-guide.md` - 详细入门指南
- `docs/code-examples-guide.md` - 代码示例指南
- 测试文件 - 最好的学习材料

### 外部资源
- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [MyBatis官方文档](https://mybatis.org/mybatis-3/)
- [PostgreSQL官方文档](https://www.postgresql.org/docs/)

## 🎯 第一个实际任务

### 任务：添加用户年龄验证
**目标**：在用户注册时验证年龄必须在1-100岁之间

**步骤**：
1. 找到用户注册相关的代码
2. 在适当的位置添加年龄验证
3. 编写测试用例验证功能
4. 提交代码变更

**相关文件**：
- `User.java` - 用户数据模型
- 用户注册的Service或Controller
- 测试文件

---

**恭喜**！完成以上步骤，你已经成功：
- ✅ 搭建开发环境
- ✅ 运行项目
- ✅ 理解项目结构
- ✅ 开始实际开发

继续加油！下一个功能等着你来实现！🚀