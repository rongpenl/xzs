package com.exam.system.service;

import com.exam.system.domain.ExamPaper;
import com.exam.system.domain.User;
import com.exam.system.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.exam.system.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.exam.system.viewmodel.student.dashboard.PaperFilter;
import com.exam.system.viewmodel.student.dashboard.PaperInfo;
import com.exam.system.viewmodel.student.exam.ExamPaperPageVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExamPaperService extends BaseService<ExamPaper> {

    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> studentPage(ExamPaperPageVM requestVM);

    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM, User user);

    ExamPaperEditRequestVM examPaperToVM(Integer id);

    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    Integer selectAllCount();

    List<Integer> selectMothCount();
}
