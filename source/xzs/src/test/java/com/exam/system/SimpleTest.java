package com.exam.system;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 简单的测试类
 * 验证测试基础设施是否正常工作
 */
@DisplayName("简单测试")
class SimpleTest {

    @Test
    @DisplayName("基本断言测试")
    void basicAssertion_shouldWork() {
        // Arrange
        int expected = 2;
        int actual = 1 + 1;

        // Act & Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("字符串测试")
    void stringAssertion_shouldWork() {
        // Arrange
        String expected = "Hello Test";
        String actual = "Hello " + "Test";

        // Act & Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("布尔值测试")
    void booleanAssertion_shouldWork() {
        // Arrange
        boolean condition = true;

        // Act & Assert
        assertThat(condition).isTrue();
    }

    @Test
    @DisplayName("集合测试")
    void collectionAssertion_shouldWork() {
        // Arrange
        String[] items = {"item1", "item2", "item3"};

        // Act & Assert
        assertThat(items).hasSize(3).contains("item1", "item3");
    }
}