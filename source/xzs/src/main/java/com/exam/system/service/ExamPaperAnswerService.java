package com.exam.system.service;

import java.util.List;

import com.exam.system.domain.ExamPaperAnswer;
import com.exam.system.domain.ExamPaperAnswerInfo;
import com.exam.system.domain.User;
import com.exam.system.viewmodel.student.exam.ExamPaperSubmitVM;
import com.exam.system.viewmodel.student.exampaper.ExamPaperAnswerPageVM;
import com.github.pagehelper.PageInfo;

public interface ExamPaperAnswerService extends BaseService<ExamPaperAnswer> {

    /**
     *
     * @param requestVM 过滤条件
     * @return PageInfo<ExamPaperAnswer>
     */
    PageInfo<ExamPaperAnswer> studentPage(ExamPaperAnswerPageVM requestVM);

    /**
     * 计算试卷提交结果(不入库)
     *
     * @param examPaperSubmitVM
     * @param user
     * @return
     */
    ExamPaperAnswerInfo calculateExamPaperAnswer(ExamPaperSubmitVM examPaperSubmitVM, User user);


    /**
     * 试卷批改
     * @param examPaperSubmitVM  examPaperSubmitVM
     * @return String
     */
    String judge(ExamPaperSubmitVM examPaperSubmitVM);

    /**
     * 试卷答题信息转成ViewModel 传给前台
     *
     * @param id 试卷id
     * @return ExamPaperSubmitVM
     */
    ExamPaperSubmitVM examPaperAnswerToVM(Integer id);


    Integer selectAllCount();

    List<Integer> selectMothCount();

    PageInfo<ExamPaperAnswer> adminPage(com.exam.system.viewmodel.admin.paper.ExamPaperAnswerPageRequestVM requestVM);
}
