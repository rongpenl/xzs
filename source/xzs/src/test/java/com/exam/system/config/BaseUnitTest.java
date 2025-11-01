package com.exam.system.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

/**
 * 单元测试基类
 * 提供单元测试的基础配置和通用方法
 */
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public abstract class BaseUnitTest {

    @BeforeEach
    void setUp() {
        // 单元测试的通用设置
        // 可以在这里初始化测试数据或执行其他通用操作
    }

    /**
     * 创建测试对象实例
     * @param clazz 要创建的类
     * @param args 构造参数
     * @param <T> 泛型类型
     * @return 实例对象
     */
    protected <T> T createInstance(Class<T> clazz, Object... args) {
        try {
            if (args.length == 0) {
                return clazz.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] parameterTypes = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    parameterTypes[i] = args[i].getClass();
                }
                return clazz.getDeclaredConstructor(parameterTypes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
        }
    }

    /**
     * 验证测试环境
     */
    protected void verifyTestEnvironment() {
        // 验证测试环境是否正常
        // 可以添加环境检查逻辑
    }
}