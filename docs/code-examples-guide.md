# 📝 代码示例指南 - 通过实际代码学习Java开发

## 🎯 本指南特色
- 完全基于本项目中的实际代码
- 每个概念都有具体的文件示例
- 包含可运行的练习代码
- 从简单到复杂的渐进学习

## 🔍 从最简单的代码开始

### 示例1：数据模型 - User.java

**文件位置**: `src/main/java/com/mindskip/xzs/domain/User.java`

```java
public class User {
    private Integer id;           // 用户ID
    private String userName;      // 用户名
    private String realName;      // 真实姓名
    private Integer age;          // 年龄
    private Integer role;         // 角色 (1学生 3管理员)
    private Integer status;       // 状态 (1启用 2禁用)

    // getter方法 - 获取属性值
    public Integer getId() {
        return id;
    }

    // setter方法 - 设置属性值
    public void setId(Integer id) {
        this.id = id;
    }

    // 其他getter/setter方法...
}
```

**练习1**：在测试文件中创建User对象
打开 `SimpleSubjectServiceTest.java`，在文件末尾添加：

```java
@Test
@DisplayName("创建用户对象练习")
void createUserObject_exercise() {
    // 1. 创建User对象
    User user = new User();

    // 2. 设置用户属性
    user.setId(1001);
    user.setUserName("test_student");
    user.setRealName("测试学生");
    user.setAge(18);
    user.setRole(1);  // 学生角色
    user.setStatus(1); // 启用状态

    // 3. 验证属性设置正确
    assertThat(user.getId()).isEqualTo(1001);
    assertThat(user.getUserName()).isEqualTo("test_student");
    assertThat(user.getRole()).isEqualTo(1);
}
```

运行这个测试，看看是否通过！

### 示例2：枚举类型 - 理解用户角色

**文件位置**: `src/main/java/com/mindskip/xzs/domain/enums/RoleEnum.java`

```java
public enum RoleEnum {
    STUDENT(1, "学生"),
    ADMIN(3, "管理员");

    int code;
    String name;

    RoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
```

**练习2**：在测试中使用枚举
在测试文件中添加：

```java
@Test
@DisplayName("枚举类型使用练习")
void enumUsage_exercise() {
    // 使用枚举
    RoleEnum studentRole = RoleEnum.STUDENT;
    RoleEnum adminRole = RoleEnum.ADMIN;

    // 验证枚举值
    assertThat(studentRole.getCode()).isEqualTo(1);
    assertThat(studentRole.getName()).isEqualTo("学生");
    assertThat(adminRole.getCode()).isEqualTo(3);
    assertThat(adminRole.getName()).isEqualTo("管理员");
}
```

### 示例3：服务接口 - UserService.java

**文件位置**: `src/main/java/com/mindskip/xzs/service/UserService.java`

```java
public interface UserService {
    // 根据ID查询用户
    User selectById(Integer id);

    // 更新用户信息（带过滤）
    void updateByIdFilter(User user);

    // 根据ID列表查询多个用户
    List<User> selectByIds(List<Integer> ids);
}
```

**这是什么？**
- `interface` 定义了一个"合同"，说明这个服务应该有哪些功能
- 具体的实现写在 `UserServiceImpl.java` 中

### 示例4：控制器 - UserController.java

**文件位置**: `src/main/java/com/mindskip/xzs/controller/admin/UserController.java`

```java
@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<User> select(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return RestResponse.ok(user);
    }
}
```

**代码解析**：
- `@RestController` - 告诉Spring这是一个API控制器
- `@RequestMapping` - 定义API路径 `/api/admin/user`
- `@Autowired` - 自动注入UserService（依赖注入）
- `@PathVariable` - 从URL路径中获取参数

**这个API的作用**：
当访问 `POST /api/admin/user/select/123` 时，返回ID为123的用户信息

## 🛠️ 实际业务逻辑分析

### 示例5：试卷服务实现 - ExamPaperServiceImpl.java

让我们看一个具体的业务方法：

```java
@Service
public class ExamPaperServiceImpl implements ExamPaperService {

    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Override
    public PageInfo<ExamPaper> page(ExamPaperPageRequestVM model) {
        // 调用Mapper进行分页查询
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize())
                .doSelectPageInfo(() -> examPaperMapper.page(model));
    }
}
```

**这个方法做什么？**
1. 接收分页请求参数（页码、每页大小）
2. 使用PageHelper进行分页
3. 调用examPaperMapper执行数据库查询
4. 返回分页结果

### 示例6：数据库操作 - ExamPaperMapper.xml

**文件位置**: `src/main/resources/mapper/ExamPaperMapper.xml`

```xml
<mapper namespace="com.mindskip.xzs.repository.ExamPaperMapper">

    <select id="page" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List" />
        FROM t_exam_paper
        <where>
            <if test="level != null">
                AND grade_level = #{level}
            </if>
            <if test="subjectId != null">
                AND subject_id = #{subjectId}
            </if>
        </where>
        ORDER BY create_time DESC
    </select>

</mapper>
```

**这是什么？**
- 这是MyBatis的XML映射文件
- 定义了SQL查询语句
- `#{level}` 是参数占位符
- `<if>` 标签用于条件判断

## 🎯 动手练习

### 练习3：创建简单的测试服务

创建一个新的测试类 `UserServicePracticeTest.java`：

```java
package com.mindskip.xzs.service;

import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.domain.enums.RoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("用户服务练习测试")
class UserServicePracticeTest {

    @Test
    @DisplayName("创建不同角色的用户")
    void createUsersWithDifferentRoles() {
        // 创建学生用户
        User student = new User();
        student.setId(1001);
        student.setUserName("student_001");
        student.setRealName("张三");
        student.setRole(RoleEnum.STUDENT.getCode());

        // 创建管理员用户
        User admin = new User();
        admin.setId(1002);
        admin.setUserName("admin_001");
        admin.setRealName("李老师");
        admin.setRole(RoleEnum.ADMIN.getCode());

        // 验证
        assertThat(student.getRole()).isEqualTo(1);
        assertThat(admin.getRole()).isEqualTo(3);
        assertThat(student.getUserName()).startsWith("student");
        assertThat(admin.getUserName()).startsWith("admin");
    }

    @Test
    @DisplayName("用户年龄验证")
    void userAgeValidation() {
        User user = new User();
        user.setAge(25);

        // 验证年龄在合理范围内
        assertThat(user.getAge()).isBetween(1, 100);
        assertThat(user.getAge()).isGreaterThan(0);
    }
}
```

### 练习4：理解API响应格式

查看 `RestResponse.java`：

```java
public class RestResponse<T> {
    private int code;        // 响应代码
    private String message;  // 响应消息
    private T response;      // 响应数据

    public static <T> RestResponse<T> ok(T response) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setCode(200);
        restResponse.setResponse(response);
        return restResponse;
    }
}
```

**这是什么？**
- 这是统一的API响应格式
- 所有API接口都返回这种格式的数据
- `code=200` 表示成功
- `response` 包含实际的数据

## 📊 代码调试技巧

### 1. 使用日志输出
在代码中添加日志：

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(YourClassName.class);

// 在方法中使用
logger.info("用户登录成功，用户ID: {}", userId);
logger.error("数据库连接失败", exception);
```

### 2. 使用断点调试
在IntelliJ IDEA中：
1. 在代码行号旁边点击添加断点
2. 右键选择"Debug"而不是"Run"
3. 程序会在断点处暂停
4. 可以查看变量值，单步执行

### 3. 查看数据库
使用pgAdmin或命令行连接PostgreSQL：
```sql
-- 查看所有用户
SELECT * FROM t_user;

-- 查看试卷列表
SELECT id, name, subject_id FROM t_exam_paper;
```

## 🚀 下一步学习建议

### 已掌握（检查清单）
- [ ] 理解User.java数据模型
- [ ] 会创建和使用枚举
- [ ] 理解Controller和Service的关系
- [ ] 会编写简单的测试用例
- [ ] 理解API响应格式

### 待学习
- [ ] 数据库操作和SQL
- [ ] Spring Boot配置
- [ ] 用户认证和权限
- [ ] 异常处理
- [ ] 前端与后端交互

### 推荐学习顺序
1. 继续练习测试代码编写
2. 阅读和理解更多的Service实现
3. 学习数据库操作（Mapper文件）
4. 理解用户登录和权限控制
5. 参与实际的bug修复

---

**记住**：编程学习最重要的是实践！多写代码，多调试，多问问题。每个优秀的开发者都是从写第一个"Hello World"开始的！

祝你编码愉快！💻