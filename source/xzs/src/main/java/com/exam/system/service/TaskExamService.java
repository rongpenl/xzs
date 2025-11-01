package com.exam.system.service;

import com.exam.system.domain.TaskExam;
import com.exam.system.domain.User;
import com.exam.system.viewmodel.admin.task.TaskPageRequestVM;
import com.exam.system.viewmodel.admin.task.TaskRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskExamService extends BaseService<TaskExam> {

    PageInfo<TaskExam> page(TaskPageRequestVM requestVM);

    void edit(TaskRequestVM model, User user);

    TaskRequestVM taskExamToVM(Integer id);

    List<TaskExam> getByGradeLevel(Integer gradeLevel);
}
