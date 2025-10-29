# 🌳 学之思考试系统后端项目结构树状图

```
com.mindskip.xzs/
├── XzsApplication.java                    # Spring Boot启动类
├── base/                                  # 基础类
│   ├── BaseApiController.java            # 控制器基类
│   ├── BaseEntity.java                   # 实体基类
│   ├── BasePage.java                     # 分页基类
│   ├── RestResponse.java                 # 统一响应格式
│   └── SystemCode.java                   # 系统状态码
├── configuration/                        # 配置类
│   ├── application/
│   │   └── ApplicationContextProvider.java
│   ├── property/                         # 属性配置
│   │   ├── CookieConfig.java
│   │   ├── PasswordKeyConfig.java
│   │   ├── QnConfig.java
│   │   ├── SystemConfig.java
│   │   └── WxConfig.java
│   └── spring/
│       ├── exception/
│       │   └── ExceptionHandle.java      # 异常处理
│       ├── mvc/
│       │   └── WebMvcConfiguration.java  # MVC配置
│       ├── security/                     # 安全配置
│       │   ├── AuthenticationBean.java
│       │   ├── LoginAuthenticationEntryPoint.java
│       │   ├── RestAccessDeniedHandler.java
│       │   ├── RestAuthenticationFailureHandler.java
│       │   ├── RestAuthenticationProvider.java
│       │   ├── RestAuthenticationSuccessHandler.java
│       │   ├── RestDetailsServiceImpl.java
│       │   ├── RestLoginAuthenticationFilter.java
│       │   ├── RestLogoutSuccessHandler.java
│       │   ├── RestTokenBasedRememberMeServices.java
│       │   ├── RestUtil.java
│       │   └── SecurityConfigurer.java
│       └── wx/
│           └── TokenHandlerInterceptor.java
├── context/                              # 上下文
│   ├── WebContext.java
│   └── WxContext.java
├── controller/                           # 控制器层
│   ├── ErrorController.java
│   ├── admin/                            # 管理员控制器
│   │   ├── DashboardController.java      # 仪表板
│   │   ├── EducationController.java      # 教育管理
│   │   ├── ExamPaperAnswerController.java # 答题管理
│   │   ├── ExamPaperController.java      # 试卷管理
│   │   ├── MessageController.java        # 消息管理
│   │   ├── QuestionController.java       # 题目管理
│   │   ├── TaskController.java           # 任务管理
│   │   ├── UploadController.java         # 上传管理
│   │   └── UserController.java           # 用户管理
│   ├── student/                          # 学生控制器
│   │   ├── DashboardController.java      # 学生仪表板
│   │   ├── EducationController.java      # 学生教育
│   │   ├── ExamPaperAnswerController.java # 学生答题
│   │   ├── ExamPaperController.java      # 学生试卷
│   │   ├── QuestionAnswerController.java # 题目作答
│   │   ├── QuestionController.java       # 学生题目
│   │   ├── UploadController.java         # 学生上传
│   │   └── UserController.java           # 学生用户
│   └── wx/                               # 微信控制器
│       ├── BaseWXApiController.java      # 微信基类
│       └── student/                      # 微信学生端
│           ├── AuthController.java       # 微信认证
│           ├── DashboardController.java  # 微信仪表板
│           ├── ExamPaperAnswerController.java
│           ├── ExamPaperController.java
│           └── UserController.java
├── domain/                               # 实体层
│   ├── ExamPaper.java                    # 试卷实体
│   ├── ExamPaperAnswer.java              # 答题记录
│   ├── ExamPaperAnswerInfo.java          # 答题信息
│   ├── ExamPaperQuestionCustomerAnswer.java # 题目答题
│   ├── Message.java                      # 消息实体
│   ├── MessageUser.java                  # 用户消息关系
│   ├── Question.java                     # 题目实体
│   ├── Subject.java                      # 学科实体
│   ├── TaskExam.java                     # 任务考试
│   ├── TaskExamCustomerAnswer.java       # 任务答题
│   ├── TextContent.java                  # 文本内容
│   ├── User.java                         # 用户实体
│   ├── UserEventLog.java                 # 用户事件日志
│   ├── UserToken.java                    # 用户令牌
│   ├── enums/                            # 枚举定义
│   │   ├── ExamPaperAnswerStatusEnum.java
│   │   ├── ExamPaperTypeEnum.java
│   │   ├── QuestionStatusEnum.java
│   │   ├── QuestionTypeEnum.java
│   │   ├── RoleEnum.java
│   │   └── UserStatusEnum.java
│   ├── exam/                             # 考试相关实体
│   │   ├── ExamPaperQuestionItemObject.java
│   │   └── ExamPaperTitleItemObject.java
│   ├── other/                            # 其他实体
│   │   ├── ExamPaperAnswerUpdate.java
│   │   └── KeyValue.java
│   ├── question/                         # 题目相关实体
│   │   ├── QuestionItemObject.java
│   │   └── QuestionObject.java
│   └── task/                             # 任务相关实体
│       ├── TaskItemAnswerObject.java
│       └── TaskItemObject.java
├── event/                                # 事件定义
│   ├── CalculateExamPaperAnswerCompleteEvent.java
│   ├── OnRegistrationCompleteEvent.java
│   └── UserEvent.java
├── exception/                            # 异常处理
│   └── BusinessException.java
├── listener/                             # 事件监听器
│   ├── CalculateExamPaperAnswerListener.java
│   ├── EmailSendListener.java
│   └── UserLogListener.java
├── repository/                           # 数据访问层
│   ├── BaseMapper.java                   # 基础Mapper
│   ├── ExamPaperAnswerMapper.java        # 答题Mapper
│   ├── ExamPaperMapper.java              # 试卷Mapper
│   ├── ExamPaperQuestionCustomerAnswerMapper.java
│   ├── MessageMapper.java                # 消息Mapper
│   ├── MessageUserMapper.java            # 用户消息Mapper
│   ├── QuestionMapper.java               # 题目Mapper
│   ├── SubjectMapper.java                # 学科Mapper
│   ├── TaskExamCustomerAnswerMapper.java
│   ├── TaskExamMapper.java               # 任务Mapper
│   ├── TextContentMapper.java            # 文本内容Mapper
│   ├── UserEventLogMapper.java           # 事件日志Mapper
│   ├── UserMapper.java                   # 用户Mapper
│   └── UserTokenMapper.java              # 用户令牌Mapper
├── service/                              # 服务层
│   ├── AuthenticationService.java        # 认证服务
│   ├── BaseService.java                  # 基础服务
│   ├── ExamPaperAnswerService.java       # 答题服务
│   ├── ExamPaperQuestionCustomerAnswerService.java
│   ├── ExamPaperService.java             # 试卷服务
│   ├── FileUpload.java                   # 文件上传服务
│   ├── MessageService.java               # 消息服务
│   ├── QuestionService.java              # 题目服务
│   ├── SubjectService.java               # 学科服务
│   ├── TaskExamCustomerAnswerService.java
│   ├── TaskExamService.java              # 任务服务
│   ├── TextContentService.java           # 文本内容服务
│   ├── UserEventLogService.java          # 事件日志服务
│   ├── UserService.java                  # 用户服务
│   ├── UserTokenService.java             # 用户令牌服务
│   ├── enums/
│   │   └── ActionEnum.java               # 操作枚举
│   └── impl/                             # 服务实现
│       ├── AuthenticationServiceImpl.java
│       ├── BaseServiceImpl.java
│       ├── ExamPaperAnswerServiceImpl.java
│       ├── ExamPaperQuestionCustomerAnswerServiceImpl.java
│       ├── ExamPaperServiceImpl.java
│       ├── FileUploadImpl.java
│       ├── MessageServiceImpl.java
│       ├── QuestionServiceImpl.java
│       ├── SubjectServiceImpl.java
│       ├── TaskExamCustomerAnswerImpl.java
│       ├── TaskExamServiceImpl.java
│       ├── TextContentServiceImpl.java
│       ├── UserEventLogServiceImpl.java
│       ├── UserServiceImpl.java
│       └── UserTokenServiceImpl.java
├── utility/                              # 工具类
│   ├── DateTimeUtil.java                 # 日期时间工具
│   ├── ErrorUtil.java                    # 错误处理工具
│   ├── ExamUtil.java                     # 考试工具
│   ├── HtmlUtil.java                     # HTML工具
│   ├── JsonUtil.java                     # JSON工具
│   ├── ModelMapperSingle.java            # 模型映射单例
│   ├── PageInfoHelper.java               # 分页助手
│   ├── RsaUtil.java                      # RSA加密工具
│   ├── WxResponse.java                   # 微信响应
│   └── WxUtil.java                       # 微信工具
└── viewmodel/                            # 视图模型
    ├── BaseVM.java                       # 基础视图模型
    ├── admin/                            # 管理员视图模型
    │   ├── dashboard/
    │   │   └── IndexVM.java
    │   ├── education/
    │   │   ├── SubjectEditRequestVM.java
    │   │   ├── SubjectPageRequestVM.java
    │   │   └── SubjectResponseVM.java
    │   ├── exam/
    │   │   ├── ExamPaperEditRequestVM.java
    │   │   ├── ExamPaperPageRequestVM.java
    │   │   ├── ExamPaperTitleItemVM.java
    │   │   └── ExamResponseVM.java
    │   ├── file/
    │   │   ├── UeditorConfigVM.java
    │   │   └── UploadResultVM.java
    │   ├── message/
    │   │   ├── MessagePageRequestVM.java
    │   │   ├── MessageResponseVM.java
    │   │   └── MessageSendVM.java
    │   ├── paper/
    │   │   ├── ExamAnswerResponseVM.java
    │   │   └── ExamPaperAnswerPageRequestVM.java
    │   ├── question/
    │   │   ├── QuestionEditItemVM.java
    │   │   ├── QuestionEditRequestVM.java
    │   │   ├── QuestionPageRequestVM.java
    │   │   └── QuestionResponseVM.java
    │   └── user/
    │       ├── UserCreateVM.java
    │       ├── UserEventLogVM.java
    │       ├── UserEventPageRequestVM.java
    │       ├── UserPageRequestVM.java
    │       ├── UserResponseVM.java
    │       └── UserUpdateVM.java
    ├── student/                          # 学生视图模型
    │   ├── dashboard/
    │   │   ├── IndexVM.java
    │   │   ├── PaperFilter.java
    │   │   ├── PaperInfo.java
    │   │   ├── PaperInfoVM.java
    │   │   ├── TaskItemPaperVm.java
    │   │   └── TaskItemVm.java
    │   ├── education/
    │   │   ├── SubjectEditRequestVM.java
    │   │   └── SubjectVM.java
    │   ├── exam/
    │   │   ├── ExamPaperPageResponseVM.java
    │   │   ├── ExamPaperPageVM.java
    │   │   ├── ExamPaperReadVM.java
    │   │   ├── ExamPaperSubmitItemVM.java
    │   │   └── ExamPaperSubmitVM.java
    │   ├── exampaper/
    │   │   ├── ExamPaperAnswerPageResponseVM.java
    │   │   └── ExamPaperAnswerPageVM.java
    │   ├── question/
    │   │   └── answer/
    │   │       ├── QuestionAnswerVM.java
    │   │       ├── QuestionPageStudentRequestVM.java
    │   │       └── QuestionPageStudentResponseVM.java
    │   └── user/
    │       ├── MessageRequestVM.java
    │       ├── MessageResponseVM.java
    │       ├── UserEventLogVM.java
    │       ├── UserRegisterVM.java
    │       ├── UserResponseVM.java
    │       └── UserUpdateVM.java
    └── wx/                               # 微信视图模型
        └── student/
            └── user/
                └── BindInfo.java
```

## 📊 项目统计信息

- **总文件数**: 162个Java文件
- **控制器**: 25个
- **服务层**: 29个（接口+实现）
- **实体类**: 26个
- **视图模型**: 54个
- **工具类**: 10个
- **配置类**: 18个

## 🏗️ 架构特点

1. **分层架构**: 清晰的Controller-Service-Repository分层
2. **权限分离**: 管理员、学生、微信三个独立的权限体系
3. **模块化设计**: 按功能模块组织代码结构
4. **统一响应**: 使用RestResponse统一API响应格式
5. **事件驱动**: 支持事件监听机制
6. **安全配置**: 完整的Spring Security配置
7. **微信集成**: 支持微信小程序/公众号接入

## 🔧 技术栈

- **框架**: Spring Boot 2.1.6 + MyBatis
- **数据库**: PostgreSQL
- **安全**: Spring Security
- **构建工具**: Maven
- **API风格**: RESTful

这个树状结构清晰地展示了学之思考试系统后端项目的完整架构，体现了标准的Spring Boot分层架构设计，具有良好的模块化和可维护性。