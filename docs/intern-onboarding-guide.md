# 🎓 学之思考试系统 - 实习生Java后端项目入门指南

## 🎯 欢迎加入开发团队！

欢迎你加入学之思考试系统的开发团队！这份指南将帮助你从零开始理解这个Java后端项目，即使你没有任何Java开发经验。

## 📋 项目快速了解

### 项目是什么？
**学之思考试系统**是一个在线考试平台，支持：
- 学生在线答题
- 教师创建和管理试卷
- 自动评分和成绩统计
- 微信小程序支持

### 项目启动入口
让我们从项目的最核心文件开始：

**文件位置**: `src/main/java/com/mindskip/xzs/XzsApplication.java`

```java
@SpringBootApplication
public class XzsApplication {
    public static void main(String[] args) {
        SpringApplication.run(XzsApplication.class, args);
    }
}
```

**这是什么？**
- 这是整个项目的"启动按钮"
- 当你运行这个文件时，整个考试系统就会启动
- `@SpringBootApplication` 告诉计算机："这是一个Spring Boot应用程序"

## 🏗️ 项目结构详解

### 项目目录结构
```
xzs/
├── src/main/java/com/mindskip/xzs/     ← 所有Java代码都在这里
│   ├── XzsApplication.java            ← 项目启动文件
│   ├── configuration/                 ← 系统配置
│   ├── controller/                    ← API接口
│   ├── domain/                        ← 数据模型
│   ├── service/                       ← 业务逻辑
│   └── repository/                    ← 数据库操作
├── src/main/resources/                ← 配置文件和静态资源
│   ├── application.yml                ← 主配置文件
│   ├── mapper/                        ← SQL映射文件
│   └── static/                        ← 前端文件
├── src/test/java/                     ← 测试代码
└── pom.xml                           ← 项目依赖配置
```

### 每个包的作用（通过具体文件理解）

#### 1. `configuration/` - 系统配置
**实际文件**: `SystemConfig.java`
```java
@Component
public class SystemConfig {
    // 这里配置系统级别的设置
    // 比如文件上传路径、系统名称等
}
```

**这是什么？**
- 就像学校的"教务处"，负责制定各种规则
- 配置整个系统如何运行

#### 2. `controller/` - API接口
**实际文件**: `ExamPaperController.java`
```java
@RestController
@RequestMapping("/api/admin/exam/paper")
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaper>> pageList(@RequestBody ExamPaperPageRequestVM model) {
        // 处理试卷分页查询的请求
        PageInfo<ExamPaper> pageInfo = examPaperService.page(model);
        return RestResponse.ok(pageInfo);
    }
}
```

**这是什么？**
- 就像学校的"接待处"，负责接收外部请求
- 当你在网页上点击"查看试卷列表"时，就是调用这里的方法

#### 3. `domain/` - 数据模型
**实际文件**: `User.java`
```java
public class User {
    private Integer id;           // 用户ID
    private String userName;      // 用户名
    private String realName;      // 真实姓名
    private Integer age;          // 年龄
    private Integer role;         // 角色 (1学生 3管理员)
    private Integer status;       // 状态 (1启用 2禁用)

    // getter和setter方法
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    // ... 其他getter/setter
}
```

**这是什么？**
- 就像学校的"学生档案"，定义了用户有哪些信息
- 每个Java类对应数据库中的一张表

#### 4. `service/` - 业务逻辑
**实际文件**: `UserService.java`
```java
public interface UserService {
    User selectById(Integer id);
    void updateByIdFilter(User user);
    List<User> selectByIds(List<Integer> ids);
}
```

**这是什么？**
- 就像学校的"教务处"，负责处理各种业务
- 包含用户管理、试卷管理、成绩统计等核心功能

#### 5. `repository/` - 数据库操作
**实际文件**: `UserMapper.java`
```java
@Mapper
public interface UserMapper {
    User selectById(Integer id);
    int updateById(User user);
}
```

**这是什么？**
- 就像学校的"档案管理员"，专门负责与数据库打交道
- 执行SQL查询、插入、更新、删除操作

## ⚙️ 开发环境配置

### 1. Java环境
本项目使用 **Java 1.8**，你需要安装：
- JDK 1.8
- 配置JAVA_HOME环境变量

### 2. 数据库
本项目使用 **PostgreSQL**，配置在：

**文件**: `src/main/resources/application-dev.yml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/xzs
    username: postgres
    password: 123456
```

### 3. 项目导入
1. 下载IntelliJ IDEA（推荐）或Eclipse
2. 选择"Open" -> 选择本项目的`pom.xml`文件
3. IDE会自动下载所有依赖

## 💡 核心概念解释（通过本项目代码）

### 什么是Spring Boot？
看看本项目的`pom.xml`文件：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.6.RELEASE</version>
</parent>
```

**简单理解**：Spring Boot就像一个"快速搭建工具包"，让我们能快速创建Web应用程序。

### 什么是MVC模式？
通过本项目代码理解：

- **Model（模型）**: `User.java` - 定义数据结构
- **View（视图）**: 前端网页 - 显示给用户的界面
- **Controller（控制器）**: `UserController.java` - 处理用户请求

### 什么是API接口？
看看`UserController.java`中的方法：

```java
@RequestMapping(value = "/current", method = RequestMethod.POST)
public RestResponse<User> current() {
    User user = getCurrentUser();
    return RestResponse.ok(user);
}
```

**这是什么？**
- 当你在网页上查看"当前用户信息"时，就是调用这个接口
- 返回当前登录用户的信息

## 🔍 核心功能模块详解

### 1. 用户管理系统
**核心文件**: `User.java`, `UserService.java`, `UserController.java`

**用户登录流程**：
1. 前端发送用户名密码到`/api/user/login`
2. `AuthenticationController.java`处理登录请求
3. 验证成功后返回用户信息和token

### 2. 试卷管理系统
**核心文件**: `ExamPaper.java`, `ExamPaperService.java`, `ExamPaperController.java`

**创建试卷流程**：
1. 教师在管理界面填写试卷信息
2. 调用`/api/admin/exam/paper/edit`接口
3. `ExamPaperServiceImpl.java`保存试卷到数据库

### 3. 题目管理系统
**核心文件**: `Question.java`, `QuestionService.java`

**题目类型**（在`QuestionTypeEnum.java`中定义）：
```java
SingleChoice(1, "单选题"),      // 选择题
MultipleChoice(2, "多选题"),     // 多选题
TrueFalse(3, "判断题"),         // 判断题
GapFilling(4, "填空题"),        // 填空题
ShortAnswer(5, "简答题");       // 简答题
```

### 4. 答题系统
**核心文件**: `ExamPaperAnswer.java`, `ExamPaperAnswerService.java`

**学生答题流程**：
1. 学生选择试卷开始答题
2. 提交答案到`/api/student/exam/paper/submit`
3. 系统自动评分并保存答题记录

## 🛠️ 代码阅读和练习

### 练习1：理解用户模型
打开`User.java`文件，尝试回答：
- 用户有哪些基本信息？
- 用户的角色有哪些？（查看`RoleEnum.java`）
- 用户的状态有哪些？（查看`UserStatusEnum.java`）

### 练习2：阅读测试代码
打开`SimpleSubjectServiceTest.java`文件：

```java
@Test
@DisplayName("学科基本属性验证")
void subjectBasicProperties_shouldWork() {
    // 创建学科对象
    Subject subject = new Subject();
    subject.setId(1);
    subject.setName("语文");
    subject.setLevel(1);

    // 验证属性是否正确
    assertThat(subject.getId()).isEqualTo(1);
    assertThat(subject.getName()).isEqualTo("语文");
    assertThat(subject.getLevel()).isEqualTo(1);
}
```

**这是什么？**
- 这是一个自动化测试，确保代码正确运行
- 每次修改代码后运行测试，确保没有破坏现有功能

### 练习3：运行项目
1. 找到`XzsApplication.java`
2. 右键选择"Run XzsApplication"
3. 在浏览器打开 `http://localhost:8000`
4. 看到登录页面说明项目启动成功

## 📚 学习路径建议

### 第一周：熟悉项目
- [ ] 阅读本指南
- [ ] 运行项目成功
- [ ] 理解`User.java`和`ExamPaper.java`
- [ ] 阅读测试代码`SimpleSubjectServiceTest.java`

### 第二周：理解业务逻辑
- [ ] 跟踪用户登录流程
- [ ] 理解试卷创建过程
- [ ] 阅读`ExamPaperServiceImpl.java`
- [ ] 尝试修改测试代码

### 第三周：参与开发
- [ ] 修复简单的bug
- [ ] 添加新的测试用例
- [ ] 理解数据库操作
- [ ] 参与代码审查

## 🆘 常见问题

### Q: 项目启动失败怎么办？
A: 检查：
1. Java版本是否为1.8
2. PostgreSQL数据库是否启动
3. 端口8000是否被占用

### Q: 如何查看日志？
A: 查看控制台输出，或查看`/log/`目录下的日志文件

### Q: 如何调试代码？
A: 在IntelliJ IDEA中，在代码行号旁边点击添加断点，然后"Debug"运行

## 🎯 下一步行动

1. **立即行动**：按照"练习3"运行项目
2. **深入学习**：阅读`UserController.java`理解API接口
3. **实践练习**：修改`SimpleSubjectServiceTest.java`添加新的测试
4. **寻求帮助**：遇到问题时，先查看日志，再询问同事

---

**记住**：学习编程就像学习一门新语言，需要时间和实践。不要害怕犯错，每个开发者都是从零开始的！

祝你学习愉快，早日成为优秀的Java开发者！🚀