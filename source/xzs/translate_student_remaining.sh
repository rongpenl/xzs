#!/bin/bash

# Script to translate remaining Chinese content in student frontend

echo "Translating remaining Chinese content in student frontend..."

# Translate error pages
ERROR_404="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/error-page/404.vue"
ERROR_401="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/error-page/401.vue"

if [ -f "$ERROR_404" ]; then
    sed -i '' "s/页面未找到.../Page not found.../g" "$ERROR_404"
    sed -i '' "s/请检查你的访问地址是否正确, 或点击下面按钮返回主页./Please check if your access address is correct, or click the button below to return to the homepage./g" "$ERROR_404"
    sed -i '' "s/返回主页/Return to Homepage/g" "$ERROR_404"
fi

if [ -f "$ERROR_401" ]; then
    sed -i '' "s/权限不足/Permission Denied/g" "$ERROR_401"
    sed -i '' "s/请检查你的访问地址是否正确, 或点击下面按钮返回主页./Please check if your access address is correct, or click the button below to return to the homepage./g" "$ERROR_401"
    sed -i '' "s/返回主页/Return to Homepage/g" "$ERROR_401"
fi

# Translate register page
REGISTER="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/register/index.vue"
if [ -f "$REGISTER" ]; then
    sed -i '' "s/学之思开源考试系统/Xuezhi Open Source Exam System/g" "$REGISTER"
    sed -i '' "s/用户名/Username/g" "$REGISTER"
    sed -i '' "s/密码/Password/g" "$REGISTER"
    sed -i '' "s/确认密码/Confirm Password/g" "$REGISTER"
    sed -i '' "s/真实姓名/Real Name/g" "$REGISTER"
    sed -i '' "s/年龄/Age/g" "$REGISTER"
    sed -i '' "s/手机号/Phone Number/g" "$REGISTER"
    sed -i '' "s/注册/Register/g" "$REGISTER"
    sed -i '' "s/已有账号/Already have an account?/g" "$REGISTER"
    sed -i '' "s/登录/Login/g" "$REGISTER"
fi

# Translate paper center
PAPER_CENTER="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/paper/index.vue"
if [ -f "$PAPER_CENTER" ]; then
    sed -i '' "s/试卷中心/Exam Center/g" "$PAPER_CENTER"
    sed -i '' "s/年级/Grade Level/g" "$PAPER_CENTER"
    sed -i '' "s/学科/Subject/g" "$PAPER_CENTER"
    sed -i '' "s/查询/Search/g" "$PAPER_CENTER"
    sed -i '' "s/名称/Name/g" "$PAPER_CENTER"
    sed -i '' "s/开始做题/Start Practice/g" "$PAPER_CENTER"
fi

# Translate exam record
RECORD="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/record/index.vue"
if [ -f "$RECORD" ]; then
    sed -i '' "s/考试记录/Exam Records/g" "$RECORD"
    sed -i '' "s/试卷名称/Paper Name/g" "$RECORD"
    sed -i '' "s/提交时间/Submission Time/g" "$RECORD"
    sed -i '' "s/分数/Score/g" "$RECORD"
    sed -i '' "s/状态/Status/g" "$RECORD"
    sed -i '' "s/操作/Actions/g" "$RECORD"
    sed -i '' "s/查看/View/g" "$RECORD"
fi

# Translate wrong questions
WRONG_QUESTIONS="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/question-error/index.vue"
if [ -f "$WRONG_QUESTIONS" ]; then
    sed -i '' "s/错题本/Wrong Questions/g" "$WRONG_QUESTIONS"
    sed -i '' "s/题目/Question/g" "$WRONG_QUESTIONS"
    sed -i '' "s/错误次数/Error Count/g" "$WRONG_QUESTIONS"
    sed -i '' "s/操作/Actions/g" "$WRONG_QUESTIONS"
    sed -i '' "s/查看/View/g" "$WRONG_QUESTIONS"
fi

# Translate user info
USER_INFO="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/user-info/index.vue"
if [ -f "$USER_INFO" ]; then
    sed -i '' "s/个人中心/Personal Center/g" "$USER_INFO"
    sed -i '' "s/基本信息/Basic Information/g" "$USER_INFO"
    sed -i '' "s/用户名/Username/g" "$USER_INFO"
    sed -i '' "s/真实姓名/Real Name/g" "$USER_INFO"
    sed -i '' "s/年龄/Age/g" "$USER_INFO"
    sed -i '' "s/手机号/Phone Number/g" "$USER_INFO"
    sed -i '' "s/修改密码/Change Password/g" "$USER_INFO"
    sed -i '' "s/原密码/Old Password/g" "$USER_INFO"
    sed -i '' "s/新密码/New Password/g" "$USER_INFO"
    sed -i '' "s/确认密码/Confirm Password/g" "$USER_INFO"
    sed -i '' "s/提交/Submit/g" "$USER_INFO"
fi

# Translate message center
MESSAGE_CENTER="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/views/user-info/message.vue"
if [ -f "$MESSAGE_CENTER" ]; then
    sed -i '' "s/消息中心/Message Center/g" "$MESSAGE_CENTER"
    sed -i '' "s/标题/Title/g" "$MESSAGE_CENTER"
    sed -i '' "s/内容/Content/g" "$MESSAGE_CENTER"
    sed -i '' "s/发送时间/Send Time/g" "$MESSAGE_CENTER"
    sed -i '' "s/状态/Status/g" "$MESSAGE_CENTER"
fi

echo "All remaining Chinese content in student frontend has been translated to English."