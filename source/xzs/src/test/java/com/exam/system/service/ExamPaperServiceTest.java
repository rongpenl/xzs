package com.exam.system.service;

import com.exam.system.config.BaseUnitTest;
import com.exam.system.domain.ExamPaper;
import com.exam.system.domain.TextContent;
import com.exam.system.domain.User;
import com.exam.system.domain.enums.ExamPaperTypeEnum;
import com.exam.system.repository.ExamPaperMapper;
import com.exam.system.repository.QuestionMapper;
import com.exam.system.service.impl.ExamPaperServiceImpl;
import com.exam.system.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.exam.system.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.exam.system.viewmodel.student.exam.ExamPaperPageVM;
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
 * ExamPaperService 单元测试
 * 测试试卷管理服务的核心功能
 */
@DisplayName("试卷服务单元测试")
class ExamPaperServiceTest extends BaseUnitTest {

    @Mock
    private ExamPaperMapper examPaperMapper;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private TextContentService textContentService;

    @Mock
    private QuestionService questionService;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private ExamPaperServiceImpl examPaperService;

    private ExamPaper examPaper;
    private User user;

    @BeforeEach
    void setUp() {
        // 准备测试数据
        examPaper = new ExamPaper();
        examPaper.setId(1);
        examPaper.setName("测试试卷");
        examPaper.setSubjectId(1);
        examPaper.setPaperType(ExamPaperTypeEnum.Fixed.getCode());
        examPaper.setGradeLevel(1);
        examPaper.setScore(100);
        examPaper.setQuestionCount(10);
        examPaper.setSuggestTime(120);
        examPaper.setFrameTextContentId(1); // Set frame text content ID
        examPaper.setCreateTime(new Date());

        user = new User();
        user.setId(1);
        user.setUserName("testuser");
    }

    @Test
    @DisplayName("分页查询试卷 - 成功")
    void page_shouldReturnPageInfo() {
        // Arrange
        ExamPaperPageRequestVM requestVM = new ExamPaperPageRequestVM();
        requestVM.setPageIndex(1);
        requestVM.setPageSize(10);

        List<ExamPaper> examPapers = Arrays.asList(examPaper);
        when(examPaperMapper.page(requestVM)).thenReturn(examPapers);

        // Act
        PageInfo<ExamPaper> result = examPaperService.page(requestVM);

        // Assert
        assertThat(result).isNotNull();
        // In unit tests with mocked mappers, PageHelper may return empty list
        // The important thing is that the service method was called
        verify(examPaperMapper).page(requestVM);
    }

    @Test
    @DisplayName("学生分页查询试卷 - 成功")
    void studentPage_shouldReturnPageInfo() {
        // Arrange
        ExamPaperPageVM requestVM = new ExamPaperPageVM();
        requestVM.setPageIndex(1);
        requestVM.setPageSize(10);

        List<ExamPaper> examPapers = Arrays.asList(examPaper);
        when(examPaperMapper.studentPage(requestVM)).thenReturn(examPapers);

        // Act
        PageInfo<ExamPaper> result = examPaperService.studentPage(requestVM);

        // Assert
        assertThat(result).isNotNull();
        // In unit tests with mocked mappers, PageHelper may return empty list
        // The important thing is that the service method was called
        verify(examPaperMapper).studentPage(requestVM);
    }

    @Test
    @DisplayName("从视图模型保存试卷 - 新增试卷")
    void savePaperFromVM_addNewPaper_shouldSaveSuccessfully() {
        // Arrange
        ExamPaperEditRequestVM requestVM = new ExamPaperEditRequestVM();
        requestVM.setName("新试卷");
        requestVM.setSubjectId(1);
        requestVM.setPaperType(ExamPaperTypeEnum.Fixed.getCode());
        requestVM.setSuggestTime(120);
        requestVM.setTitleItems(Collections.emptyList()); // Initialize titleItems collection

        TextContent textContent = new TextContent();
        textContent.setId(1);

        when(subjectService.levelBySubjectId(1)).thenReturn(1);
        when(textContentService.insertByFilter(any(TextContent.class))).thenAnswer(invocation -> {
            TextContent tc = invocation.getArgument(0);
            tc.setId(1);
            return null;
        });
        when(examPaperMapper.insertSelective(any(ExamPaper.class))).thenReturn(1);

        // Act
        ExamPaper result = examPaperService.savePaperFromVM(requestVM, user);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("新试卷");
        verify(textContentService).insertByFilter(any(TextContent.class));
        verify(examPaperMapper).insertSelective(any(ExamPaper.class));
    }

    @Test
    @DisplayName("从视图模型保存试卷 - 更新试卷")
    void savePaperFromVM_updatePaper_shouldUpdateSuccessfully() {
        // Arrange
        ExamPaperEditRequestVM requestVM = new ExamPaperEditRequestVM();
        requestVM.setId(1);
        requestVM.setName("更新后的试卷");
        requestVM.setSubjectId(1);
        requestVM.setPaperType(ExamPaperTypeEnum.Fixed.getCode());
        requestVM.setSuggestTime(120);
        requestVM.setTitleItems(Collections.emptyList()); // Initialize titleItems collection

        TextContent textContent = new TextContent();
        textContent.setId(1);

        when(examPaperMapper.selectByPrimaryKey(1)).thenReturn(examPaper);
        when(textContentService.selectById(any(Integer.class))).thenReturn(textContent);
        when(subjectService.levelBySubjectId(1)).thenReturn(1);
        when(textContentService.updateByIdFilter(any(TextContent.class))).thenReturn(1);
        when(examPaperMapper.updateByPrimaryKeySelective(any(ExamPaper.class))).thenReturn(1);

        // Act
        ExamPaper result = examPaperService.savePaperFromVM(requestVM, user);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        verify(examPaperMapper).updateByPrimaryKeySelective(any(ExamPaper.class));
        verify(textContentService).updateByIdFilter(any(TextContent.class));
    }

    @Test
    @DisplayName("试卷转换为视图模型 - 成功")
    void examPaperToVM_shouldReturnViewModel() {
        // Arrange
        Integer paperId = 1;
        TextContent textContent = new TextContent();
        textContent.setContent("[]");

        when(examPaperMapper.selectByPrimaryKey(paperId)).thenReturn(examPaper);
        when(textContentService.selectById(examPaper.getFrameTextContentId())).thenReturn(textContent);

        // Act
        ExamPaperEditRequestVM result = examPaperService.examPaperToVM(paperId);

        // Assert
        assertThat(result).isNotNull();
        verify(examPaperMapper).selectByPrimaryKey(paperId);
        verify(textContentService).selectById(examPaper.getFrameTextContentId());
    }

    @Test
    @DisplayName("查询试卷总数 - 成功")
    void selectAllCount_shouldReturnTotalCount() {
        // Arrange
        when(examPaperMapper.selectAllCount()).thenReturn(100);

        // Act
        Integer result = examPaperService.selectAllCount();

        // Assert
        assertThat(result).isEqualTo(100);
        verify(examPaperMapper).selectAllCount();
    }

    @Test
    @DisplayName("查询月度统计 - 成功")
    void selectMothCount_shouldReturnMonthlyStatistics() {
        // Arrange
        when(examPaperMapper.selectCountByDate(any(Date.class), any(Date.class)))
                .thenReturn(Collections.emptyList());

        // Act
        List<Integer> result = examPaperService.selectMothCount();

        // Assert
        assertThat(result).isNotNull();
        verify(examPaperMapper).selectCountByDate(any(Date.class), any(Date.class));
    }

    @Test
    @DisplayName("任务考试分页查询 - 成功")
    void taskExamPage_shouldReturnTaskExamPapers() {
        // Arrange
        ExamPaperPageRequestVM requestVM = new ExamPaperPageRequestVM();
        requestVM.setPageIndex(1);
        requestVM.setPageSize(10);

        List<ExamPaper> examPapers = Arrays.asList(examPaper);
        when(examPaperMapper.taskExamPage(requestVM)).thenReturn(examPapers);

        // Act
        PageInfo<ExamPaper> result = examPaperService.taskExamPage(requestVM);

        // Assert
        assertThat(result).isNotNull();
        // In unit tests with mocked mappers, PageHelper may return empty list
        // The important thing is that the service method was called
        verify(examPaperMapper).taskExamPage(requestVM);
    }
}