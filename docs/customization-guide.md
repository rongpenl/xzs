# 学之思考试系统定制化实施指南

## 🎯 定制目标
将系统从中文K-12教育系统改造为国际标准化考试系统，支持AMC、SAT、AP等考试类型。

## 📋 定制范围总览

### 核心改造内容
1. **术语系统** - 从中文K-12教育术语改为国际标准化考试术语
2. **品牌元素** - 更新logo、公司信息、版权信息
3. **视觉元素** - 替换图片、配色方案
4. **配置系统** - 更新应用配置和数据库设置

## 🔧 第一阶段：核心术语改造

### 1.1 年级系统改造

#### 当前状态
- 1-12年级系统（小学、初中、高中）
- 中文年级名称："一年级"、"二年级"等

#### 目标状态
- 国际标准化考试级别：AMC8, AMC10, SAT, AP Calculus等
- 英文级别名称

#### 需要修改的文件

**核心领域模型文件**：
- `/src/main/java/com/mindskip/xzs/domain/Subject.java`
  - 第17行：`@ApiModelProperty(value = "年级 (1-12) 小学 初中", required = true)`
  - 第22行：`@ApiModelProperty(value = "一年级、二年级等", required = true)`
  - **修改建议**：更新注释为国际考试级别描述

**视图模型文件**：
- `/src/main/java/com/mindskip/xzs/viewmodel/admin/education/SubjectEditRequestVM.java`
- `/src/main/java/com/mindskip/xzs/viewmodel/admin/education/SubjectResponseVM.java`
- `/src/main/java/com/mindskip/xzs/viewmodel/student/education/SubjectEditRequestVM.java`

**服务实现文件**：
- `/src/main/java/com/mindskip/xzs/service/impl/QuestionServiceImpl.java`
- `/src/main/java/com/mindskip/xzs/service/TaskExamService.java`

**测试文件**：
- `/src/test/java/com/mindskip/xzs/service/SimpleSubjectServiceTest.java`
  - 第26行：`subject.setName("语文");`
  - 第28行：`subject.setLevelName("一年级");`
  - **修改建议**：更新为国际考试科目和级别

#### 具体修改方案
```java
// 当前代码
subject.setLevel(1);
subject.setLevelName("一年级");

// 修改后代码
subject.setLevel(1);
subject.setLevelName("AMC8");

// 或者使用新的级别映射
// 1 -> AMC8
// 2 -> AMC10
// 3 -> SAT Math
// 4 -> AP Calculus
// 5 -> AP Physics
// ...
```

### 1.2 试卷类型改造

#### 当前状态
- 固定试卷 (Fixed - 1)
- 时段试卷 (TimeLimit - 4)
- 任务试卷 (Task - 6)

#### 目标状态
- Practice Test (原固定试卷)
- Timed Test (原时段试卷)
- Assignment (原任务试卷)

#### 需要修改的文件

**枚举类文件**：
- `/src/main/java/com/mindskip/xzs/domain/enums/ExamPaperTypeEnum.java`
  - 第8行：`Fixed(1, "固定试卷"),`
  - 第9行：`TimeLimit(4, "时段试卷"),`
  - 第10行：`Task(6, "任务试卷");`
  - **修改建议**：
    ```java
    Fixed(1, "Practice Test"),
    TimeLimit(4, "Timed Test"),
    Task(6, "Assignment");
    ```

**领域模型文件**：
- `/src/main/java/com/mindskip/xzs/domain/ExamPaper.java`
  - 第23行：`@ApiModelProperty(value = "试卷类型( 1固定试卷 4.时段试卷 6.任务试卷)")`
  - **修改建议**：更新注释描述

- `/src/main/java/com/mindskip/xzs/domain/ExamPaperAnswer.java`
  - 第20行：`@ApiModelProperty(value = "试卷类型( 1固定试卷 4.时段试卷 6.任务试卷)")`
  - **修改建议**：更新注释描述

**服务实现文件**：
- `/src/main/java/com/mindskip/xzs/service/impl/ExamPaperServiceImpl.java`
  - 第140行、182行：包含TimeLimit类型的业务逻辑

### 1.3 学科名称改造

#### 需要修改的文件

**测试文件中的硬编码值**：
- `/src/test/java/com/mindskip/xzs/service/SimpleSubjectServiceTest.java`
  - 第26行：`"语文"` → `"English Language"`
  - 第47行：`"数学"` → `"Mathematics"`
  - 第79行：`"英语"` → `"English"`
  - 第96行：`"物理"` → `"Physics"`
  - 第154行：`"化学"` → `"Chemistry"`

**其他测试文件**：
- 所有包含中文科目名称的测试用例都需要更新

### 1.4 题目类型枚举改造

**枚举类文件**：
- `/src/main/java/com/mindskip/xzs/domain/enums/QuestionTypeEnum.java`
  - 第8行：`SingleChoice(1, "单选题"),`
  - 第9行：`MultipleChoice(2, "多选题"),`
  - 第10行：`TrueFalse(3, "判断题"),`
  - 第11行：`GapFilling(4, "填空题"),`
  - 第12行：`ShortAnswer(5, "简答题");`
  - **修改建议**：保持英文枚举名，更新显示名称

## 🎨 第二阶段：品牌元素更新

### 2.1 Logo和图标替换

#### 需要替换的文件

**管理员界面**：
- `/src/main/resources/static/admin/static/img/logo.d99ccfc3.png`
- `/target/classes/static/admin/static/img/logo.d99ccfc3.png`

**学生界面**：
- `/src/main/resources/static/student/static/img/logo.9e385549.png`
- `/src/main/resources/static/student/static/img/logo2.745fd978.png`
- `/target/classes/static/student/static/img/logo.9e385549.png`
- `/target/classes/static/student/static/img/logo2.745fd978.png`

**Favicon文件**：
- `/src/main/resources/static/admin/favicon.ico`
- `/src/main/resources/static/student/favicon.ico`

#### 操作步骤
1. 准备新的logo图片文件
2. 替换上述所有logo文件
3. 确保文件名保持一致
4. 清除浏览器缓存测试效果

### 2.2 页面标题更新

**HTML文件**：
- `/src/main/resources/static/admin/index.html`
  - 第7行：`<title>学之思管理系统</title>`
  - **修改建议**：`<title>Your Custom System Name</title>`

- `/src/main/resources/static/student/index.html`
  - 第7行：`<title>学生考试系统</title>`
  - **修改建议**：`<title>Student Exam System</title>`

### 2.3 公司信息更新

**Maven配置**：
- `/pom.xml`
  - 第4行：`<groupId>com.mindskip</groupId>`
  - 第5行：`<artifactId>xzs</artifactId>`
  - 第6行：`<name>xzs</name>`
  - 第7行：`<url>https://www.mindskip.net/xzs.html</url>`
  - 第15-17行：公司信息
  - **修改建议**：更新为自定义公司信息

**Java文件头部版权信息**：
- 所有Java文件顶部的版权声明
- 示例：`* Copyright (C), 2020-2025, 武汉思维跳跃科技有限公司`
- **修改建议**：更新为自定义公司名称

## ⚙️ 第三阶段：配置和数据库

### 3.1 应用配置更新

**主配置文件**：
- `/src/main/resources/application.yml`
  - 七牛云配置：`qn.url: http://xzs.file.mindskip.net`
  - **修改建议**：更新为自定义CDN地址

**环境配置文件**：
- `/src/main/resources/application-dev.yml`
- `/src/main/resources/application-prod.yml`
- `/src/main/resources/application-test.yml`
- `/src/main/resources/application-pre.yml`

### 3.2 数据库初始化

**需要更新的默认数据**：
1. 学科表(t_subject)中的默认学科数据
2. 试卷类型相关的业务逻辑
3. 用户角色和权限配置

## 💻 第四阶段：前端界面

### 4.1 UEditor富文本编辑器

**语言配置文件**：
- `/src/main/resources/static/admin/admin/components/ueditor/lang/zh-cn/zh-cn.js`
- **修改建议**：全面更新中文界面文本为英文

**注意**：此文件包含数百个UI标签，需要逐项翻译

### 4.2 编译前端资源

**重要说明**：
- 当前代码库只包含编译后的前端资源
- 完整的前端定制需要原始Vue.js源码
- 需要重新编译前端项目才能更新界面文本

## 📊 实施检查清单

### 第一阶段检查项
- [ ] 更新Subject.java中的年级注释
- [ ] 更新所有视图模型中的级别字段
- [ ] 修改ExamPaperTypeEnum枚举显示名称
- [ ] 更新测试文件中的硬编码中文值
- [ ] 验证所有测试通过

### 第二阶段检查项
- [ ] 替换所有logo图片文件
- [ ] 更新favicon文件
- [ ] 修改HTML页面标题
- [ ] 更新pom.xml中的公司信息
- [ ] 更新Java文件头部版权信息

### 第三阶段检查项
- [ ] 更新application.yml配置
- [ ] 更新环境配置文件
- [ ] 验证数据库连接
- [ ] 测试系统功能

### 第四阶段检查项
- [ ] 翻译UEditor语言文件
- [ ] 获取原始Vue.js源码（如需要）
- [ ] 重新编译前端项目
- [ ] 验证前端界面显示

## ⚠️ 注意事项

1. **数据库兼容性**：保持枚举代码值不变，只修改显示名称
2. **测试验证**：每个阶段完成后运行完整测试套件
3. **版本控制**：建议使用Git分支进行修改
4. **备份策略**：修改前备份重要文件
5. **分阶段实施**：建议按阶段顺序实施，便于问题排查

## 🔗 相关文件清单

### 核心Java文件 (34个)
- 领域模型：Subject.java, Question.java, ExamPaper.java等
- 枚举类：ExamPaperTypeEnum.java, QuestionTypeEnum.java等
- 视图模型：所有*VM.java文件
- 服务类：QuestionServiceImpl.java, ExamPaperServiceImpl.java等

### 前端资源文件
- HTML文件：2个
- Logo图片：6个
- Favicon：2个
- UEditor配置：1个

### 配置文件
- YAML配置：5个
- POM配置：1个
- 测试文件：多个

此文档提供了完整的定制化实施指南，按照此指南可以系统地将学之思考试系统改造为国际标准化考试平台。