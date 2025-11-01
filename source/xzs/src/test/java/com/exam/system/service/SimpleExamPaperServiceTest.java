package com.exam.system.service;

import com.exam.system.domain.ExamPaper;
import com.exam.system.domain.User;
import com.exam.system.domain.enums.ExamPaperTypeEnum;
import com.exam.system.viewmodel.admin.exam.ExamPaperEditRequestVM;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 简化的试卷服务测试
 * 使用简单的手动测试，避免Mockito扩展问题
 */
@DisplayName("简化试卷服务测试")
class SimpleExamPaperServiceTest {

    @Test
    @DisplayName("试卷基本属性验证")
    void examPaperBasicProperties_shouldWork() {
        // Arrange
        ExamPaper examPaper = new ExamPaper();
        examPaper.setId(1);
        examPaper.setName("测试试卷");
        examPaper.setSubjectId(1);
        examPaper.setPaperType(ExamPaperTypeEnum.Fixed.getCode());
        examPaper.setGradeLevel(1);
        examPaper.setScore(100);
        examPaper.setQuestionCount(10);
        examPaper.setSuggestTime(120);
        examPaper.setCreateTime(new Date());

        // Act & Assert
        assertThat(examPaper.getId()).isEqualTo(1);
        assertThat(examPaper.getName()).isEqualTo("测试试卷");
        assertThat(examPaper.getSubjectId()).isEqualTo(1);
        assertThat(examPaper.getPaperType()).isEqualTo(ExamPaperTypeEnum.Fixed.getCode());
        assertThat(examPaper.getGradeLevel()).isEqualTo(1);
        assertThat(examPaper.getScore()).isEqualTo(100);
        assertThat(examPaper.getQuestionCount()).isEqualTo(10);
        assertThat(examPaper.getSuggestTime()).isEqualTo(120);
        assertThat(examPaper.getCreateTime()).isNotNull();
    }

    @Test
    @DisplayName("试卷视图模型基本属性验证")
    void examPaperEditRequestVM_shouldWork() {
        // Arrange
        ExamPaperEditRequestVM requestVM = new ExamPaperEditRequestVM();
        requestVM.setId(1);
        requestVM.setName("测试试卷");
        requestVM.setSubjectId(1);
        requestVM.setPaperType(ExamPaperTypeEnum.Fixed.getCode());
        requestVM.setSuggestTime(120);

        // Act & Assert
        assertThat(requestVM.getId()).isEqualTo(1);
        assertThat(requestVM.getName()).isEqualTo("测试试卷");
        assertThat(requestVM.getSubjectId()).isEqualTo(1);
        assertThat(requestVM.getPaperType()).isEqualTo(ExamPaperTypeEnum.Fixed.getCode());
        assertThat(requestVM.getSuggestTime()).isEqualTo(120);
    }

    @Test
    @DisplayName("用户基本属性验证")
    void userBasicProperties_shouldWork() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setUserName("testuser");
        user.setRealName("测试用户");

        // Act & Assert
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUserName()).isEqualTo("testuser");
        assertThat(user.getRealName()).isEqualTo("测试用户");
    }

    @Test
    @DisplayName("试卷类型枚举验证")
    void examPaperTypeEnum_shouldWork() {
        // Arrange & Act
        ExamPaperTypeEnum fixed = ExamPaperTypeEnum.Fixed;
        ExamPaperTypeEnum timeLimit = ExamPaperTypeEnum.TimeLimit;
        ExamPaperTypeEnum task = ExamPaperTypeEnum.Task;

        // Assert
        assertThat(fixed.getCode()).isEqualTo(1);
        assertThat(timeLimit.getCode()).isEqualTo(4);
        assertThat(task.getCode()).isEqualTo(6);
        assertThat(ExamPaperTypeEnum.fromCode(1)).isEqualTo(fixed);
        assertThat(ExamPaperTypeEnum.fromCode(4)).isEqualTo(timeLimit);
        assertThat(ExamPaperTypeEnum.fromCode(6)).isEqualTo(task);
    }

    @Test
    @DisplayName("试卷创建时间验证")
    void examPaperCreateTime_shouldBeCurrent() {
        // Arrange
        ExamPaper examPaper = new ExamPaper();
        Date now = new Date();
        examPaper.setCreateTime(now);

        // Act & Assert
        assertThat(examPaper.getCreateTime()).isEqualTo(now);
        assertThat(examPaper.getCreateTime()).isBeforeOrEqualTo(new Date());
    }
}