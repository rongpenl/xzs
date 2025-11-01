package com.exam.system.service;

import com.exam.system.config.BaseUnitTest;
import com.exam.system.domain.Question;
import com.exam.system.domain.TextContent;
import com.exam.system.domain.enums.QuestionStatusEnum;
import com.exam.system.domain.enums.QuestionTypeEnum;
import com.exam.system.repository.QuestionMapper;
import com.exam.system.service.impl.QuestionServiceImpl;
import com.exam.system.viewmodel.admin.question.QuestionEditRequestVM;
import com.exam.system.viewmodel.admin.question.QuestionPageRequestVM;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * QuestionService 单元测试
 * 测试题目管理服务的核心功能
 */
@DisplayName("题目服务单元测试")
class QuestionServiceTest extends BaseUnitTest {

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private TextContentService textContentService;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private Question question;
    private TextContent textContent;

    @BeforeEach
    void setUp() {
        // 准备测试数据
        question = new Question();
        question.setId(1);
        question.setSubjectId(1);
        question.setGradeLevel(1);
        question.setQuestionType(QuestionTypeEnum.SingleChoice.getCode());
        question.setStatus(QuestionStatusEnum.OK.getCode());
        question.setScore(10);
        question.setDifficult(3);
        question.setInfoTextContentId(1);
        question.setCreateTime(new Date());

        textContent = new TextContent();
        textContent.setId(1);
        textContent.setContent("{\"titleContent\":\"测试题目\"}");
    }

    @Test
    @DisplayName("分页查询题目 - 成功")
    void page_shouldReturnPageInfo() {
        // Arrange
        QuestionPageRequestVM requestVM = new QuestionPageRequestVM();
        requestVM.setPageIndex(1);
        requestVM.setPageSize(10);

        List<Question> questions = Arrays.asList(question);
        when(questionMapper.page(requestVM)).thenReturn(questions);

        // Act
        PageInfo<Question> result = questionService.page(requestVM);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getList()).hasSize(1);
        assertThat(result.getList().get(0).getId()).isEqualTo(1);
        verify(questionMapper).page(requestVM);
    }

    @Test
    @DisplayName("插入完整题目 - 成功")
    void insertFullQuestion_shouldInsertSuccessfully() {
        // Arrange
        QuestionEditRequestVM requestVM = new QuestionEditRequestVM();
        requestVM.setSubjectId(1);
        requestVM.setQuestionType(QuestionTypeEnum.SingleChoice.getCode());
        requestVM.setScore("10");
        requestVM.setDifficult(3);
        requestVM.setCorrect("A");

        when(subjectService.levelBySubjectId(1)).thenReturn(1);
        when(textContentService.insertByFilter(any(TextContent.class))).thenAnswer(invocation -> {
            TextContent tc = invocation.getArgument(0);
            tc.setId(1);
            return null;
        });
        when(questionMapper.insertSelective(any(Question.class))).thenReturn(1);

        // Act
        Question result = questionService.insertFullQuestion(requestVM, 1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getSubjectId()).isEqualTo(1);
        verify(textContentService).insertByFilter(any(TextContent.class));
        verify(questionMapper).insertSelective(any(Question.class));
    }

    @Test
    @DisplayName("更新完整题目 - 成功")
    void updateFullQuestion_shouldUpdateSuccessfully() {
        // Arrange
        QuestionEditRequestVM requestVM = new QuestionEditRequestVM();
        requestVM.setId(1);
        requestVM.setSubjectId(1);
        requestVM.setQuestionType(QuestionTypeEnum.SingleChoice.getCode());
        requestVM.setScore("10");
        requestVM.setDifficult(3);
        requestVM.setCorrect("A");

        when(questionMapper.selectByPrimaryKey(1)).thenReturn(question);
        when(subjectService.levelBySubjectId(1)).thenReturn(1);
        when(textContentService.selectById(1)).thenReturn(textContent);
        when(questionMapper.updateByPrimaryKeySelective(any(Question.class))).thenReturn(1);
        when(textContentService.updateByIdFilter(any(TextContent.class))).thenReturn(1);

        // Act
        Question result = questionService.updateFullQuestion(requestVM);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        verify(questionMapper).updateByPrimaryKeySelective(any(Question.class));
        verify(textContentService).updateByIdFilter(any(TextContent.class));
    }

    @Test
    @DisplayName("根据题目ID获取编辑视图模型 - 成功")
    void getQuestionEditRequestVM_byQuestionId_shouldReturnViewModel() {
        // Arrange
        Integer questionId = 1;

        when(questionMapper.selectByPrimaryKey(questionId)).thenReturn(question);
        when(textContentService.selectById(question.getInfoTextContentId())).thenReturn(textContent);

        // Act
        QuestionEditRequestVM result = questionService.getQuestionEditRequestVM(questionId);

        // Assert
        assertThat(result).isNotNull();
        verify(questionMapper).selectByPrimaryKey(questionId);
        verify(textContentService).selectById(question.getInfoTextContentId());
    }

    @Test
    @DisplayName("根据题目对象获取编辑视图模型 - 成功")
    void getQuestionEditRequestVM_byQuestionObject_shouldReturnViewModel() {
        // Arrange
        when(textContentService.selectById(question.getInfoTextContentId())).thenReturn(textContent);

        // Act
        QuestionEditRequestVM result = questionService.getQuestionEditRequestVM(question);

        // Assert
        assertThat(result).isNotNull();
        verify(textContentService).selectById(question.getInfoTextContentId());
    }

    @Test
    @DisplayName("查询题目总数 - 成功")
    void selectAllCount_shouldReturnTotalCount() {
        // Arrange
        when(questionMapper.selectAllCount()).thenReturn(500);

        // Act
        Integer result = questionService.selectAllCount();

        // Assert
        assertThat(result).isEqualTo(500);
        verify(questionMapper).selectAllCount();
    }

    @Test
    @DisplayName("查询月度统计 - 成功")
    void selectMothCount_shouldReturnMonthlyStatistics() {
        // Arrange
        when(questionMapper.selectCountByDate(any(Date.class), any(Date.class)))
                .thenReturn(Collections.emptyList());

        // Act
        List<Integer> result = questionService.selectMothCount();

        // Assert
        assertThat(result).isNotNull();
        verify(questionMapper).selectCountByDate(any(Date.class), any(Date.class));
    }

    @Test
    @DisplayName("更新题目时处理文本内容 - 成功")
    void updateFullQuestion_shouldHandleTextContentCorrectly() {
        // Arrange
        QuestionEditRequestVM requestVM = new QuestionEditRequestVM();
        requestVM.setId(1);
        requestVM.setSubjectId(1);
        requestVM.setQuestionType(QuestionTypeEnum.SingleChoice.getCode());
        requestVM.setScore("10");
        requestVM.setDifficult(3);
        requestVM.setCorrect("A");

        when(questionMapper.selectByPrimaryKey(1)).thenReturn(question);
        when(subjectService.levelBySubjectId(1)).thenReturn(1);
        when(textContentService.selectById(1)).thenReturn(textContent);
        when(questionMapper.updateByPrimaryKeySelective(any(Question.class))).thenReturn(1);
        when(textContentService.updateByIdFilter(any(TextContent.class))).thenReturn(1);

        // Act
        Question result = questionService.updateFullQuestion(requestVM);

        // Assert
        assertThat(result).isNotNull();
        verify(textContentService).selectById(1);
        verify(textContentService).updateByIdFilter(any(TextContent.class));
    }
}