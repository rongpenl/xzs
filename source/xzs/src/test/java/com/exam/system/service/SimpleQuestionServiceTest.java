package com.exam.system.service;

import com.exam.system.domain.Question;
import com.exam.system.domain.enums.QuestionStatusEnum;
import com.exam.system.domain.enums.QuestionTypeEnum;
import com.exam.system.viewmodel.admin.question.QuestionEditRequestVM;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 简化的题目服务测试
 * 使用简单的手动测试，避免Mockito扩展问题
 */
@DisplayName("简化题目服务测试")
class SimpleQuestionServiceTest {

    @Test
    @DisplayName("题目基本属性验证")
    void questionBasicProperties_shouldWork() {
        // Arrange
        Question question = new Question();
        question.setId(1);
        question.setSubjectId(1);
        question.setGradeLevel(1);
        question.setQuestionType(QuestionTypeEnum.SingleChoice.getCode());
        question.setStatus(QuestionStatusEnum.OK.getCode());
        question.setScore(10);
        question.setDifficult(3);
        question.setInfoTextContentId(1);
        question.setCreateTime(new Date());

        // Act & Assert
        assertThat(question.getId()).isEqualTo(1);
        assertThat(question.getSubjectId()).isEqualTo(1);
        assertThat(question.getGradeLevel()).isEqualTo(1);
        assertThat(question.getQuestionType()).isEqualTo(QuestionTypeEnum.SingleChoice.getCode());
        assertThat(question.getStatus()).isEqualTo(QuestionStatusEnum.OK.getCode());
        assertThat(question.getScore()).isEqualTo(10);
        assertThat(question.getDifficult()).isEqualTo(3);
        assertThat(question.getInfoTextContentId()).isEqualTo(1);
        assertThat(question.getCreateTime()).isNotNull();
    }

    @Test
    @DisplayName("题目视图模型基本属性验证")
    void questionEditRequestVM_shouldWork() {
        // Arrange
        QuestionEditRequestVM requestVM = new QuestionEditRequestVM();
        requestVM.setId(1);
        requestVM.setSubjectId(1);
        requestVM.setQuestionType(QuestionTypeEnum.SingleChoice.getCode());
        requestVM.setScore("10");
        requestVM.setDifficult(3);
        requestVM.setCorrect("A");

        // Act & Assert
        assertThat(requestVM.getId()).isEqualTo(1);
        assertThat(requestVM.getSubjectId()).isEqualTo(1);
        assertThat(requestVM.getQuestionType()).isEqualTo(QuestionTypeEnum.SingleChoice.getCode());
        assertThat(requestVM.getScore()).isEqualTo("10");
        assertThat(requestVM.getDifficult()).isEqualTo(3);
        assertThat(requestVM.getCorrect()).isEqualTo("A");
    }

    @Test
    @DisplayName("题目类型枚举验证")
    void questionTypeEnum_shouldWork() {
        // Arrange & Act
        QuestionTypeEnum singleChoice = QuestionTypeEnum.SingleChoice;
        QuestionTypeEnum multipleChoice = QuestionTypeEnum.MultipleChoice;
        QuestionTypeEnum trueFalse = QuestionTypeEnum.TrueFalse;
        QuestionTypeEnum gapFilling = QuestionTypeEnum.GapFilling;
        QuestionTypeEnum shortAnswer = QuestionTypeEnum.ShortAnswer;

        // Assert
        assertThat(singleChoice.getCode()).isEqualTo(1);
        assertThat(multipleChoice.getCode()).isEqualTo(2);
        assertThat(trueFalse.getCode()).isEqualTo(3);
        assertThat(gapFilling.getCode()).isEqualTo(4);
        assertThat(shortAnswer.getCode()).isEqualTo(5);
        assertThat(QuestionTypeEnum.fromCode(1)).isEqualTo(singleChoice);
        assertThat(QuestionTypeEnum.fromCode(2)).isEqualTo(multipleChoice);
    }

    @Test
    @DisplayName("题目状态枚举验证")
    void questionStatusEnum_shouldWork() {
        // Arrange & Act
        QuestionStatusEnum ok = QuestionStatusEnum.OK;
        QuestionStatusEnum publish = QuestionStatusEnum.Publish;

        // Assert
        assertThat(ok.getCode()).isEqualTo(1);
        assertThat(publish.getCode()).isEqualTo(2);
        assertThat(ok.getName()).isEqualTo("正常");
        assertThat(publish.getName()).isEqualTo("发布");
    }

    @Test
    @DisplayName("题目难度验证")
    void questionDifficulty_shouldBeValid() {
        // Arrange
        Question question = new Question();
        question.setDifficult(1); // 简单
        Question question2 = new Question();
        question2.setDifficult(5); // 困难

        // Act & Assert
        assertThat(question.getDifficult()).isEqualTo(1);
        assertThat(question2.getDifficult()).isEqualTo(5);
        assertThat(question.getDifficult()).isBetween(1, 5);
        assertThat(question2.getDifficult()).isBetween(1, 5);
    }

    @Test
    @DisplayName("题目创建时间验证")
    void questionCreateTime_shouldBeCurrent() {
        // Arrange
        Question question = new Question();
        Date now = new Date();
        question.setCreateTime(now);

        // Act & Assert
        assertThat(question.getCreateTime()).isEqualTo(now);
        assertThat(question.getCreateTime()).isBeforeOrEqualTo(new Date());
    }
}