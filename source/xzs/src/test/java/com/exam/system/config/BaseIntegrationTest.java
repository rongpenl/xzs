package com.exam.system.config;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * 集成测试基类
 * 提供集成测试的基础配置和通用方法
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
public abstract class BaseIntegrationTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // 设置MockMvc，包含Spring Security配置
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    /**
     * 获取测试API的基础路径
     */
    protected String getApiBasePath() {
        return "/api";
    }

    /**
     * 获取管理员API路径
     */
    protected String getAdminApiPath() {
        return getApiBasePath() + "/admin";
    }

    /**
     * 获取学生API路径
     */
    protected String getStudentApiPath() {
        return getApiBasePath() + "/student";
    }

    /**
     * 获取微信API路径
     */
    protected String getWxApiPath() {
        return getApiBasePath() + "/wx";
    }

    /**
     * 验证测试环境
     */
    protected void verifyTestEnvironment() {
        // 验证测试环境是否正常
        // 可以添加数据库连接检查等
    }
}