#!/bin/bash

# Script to translate remaining Chinese titles in admin router.js

ROUTER_FILE="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-admin/src/router.js"

echo "Translating remaining Chinese titles in admin router.js..."

# Translate remaining exam management routes
sed -i '' "s/'试卷编辑'/'Exam Paper Edit'/g" "$ROUTER_FILE"
sed -i '' "s/'题目列表'/'Question List'/g" "$ROUTER_FILE"
sed -i '' "s/'单选题编辑'/'Single Choice Edit'/g" "$ROUTER_FILE"
sed -i '' "s/'多选题编辑'/'Multiple Choice Edit'/g" "$ROUTER_FILE"
sed -i '' "s/'判断题编辑'/'True/False Edit'/g" "$ROUTER_FILE"
sed -i '' "s/'填空题编辑'/'Fill in the Blank Edit'/g" "$ROUTER_FILE"
sed -i '' "s/'简答题编辑'/'Short Answer Edit'/g" "$ROUTER_FILE"

# Translate task management
sed -i '' "s/'任务管理'/'Task Management'/g" "$ROUTER_FILE"
sed -i '' "s/'任务列表'/'Task List'/g" "$ROUTER_FILE"
sed -i '' "s/'任务创建'/'Task Create'/g" "$ROUTER_FILE"

# Translate education management
sed -i '' "s/'教育管理'/'Education Management'/g" "$ROUTER_FILE"
sed -i '' "s/'学科列表'/'Subject List'/g" "$ROUTER_FILE"
sed -i '' "s/'学科编辑'/'Subject Edit'/g" "$ROUTER_FILE"

# Translate answer management
sed -i '' "s/'成绩管理'/'Grade Management'/g" "$ROUTER_FILE"
sed -i '' "s/'答卷列表'/'Answer Paper List'/g" "$ROUTER_FILE"

# Translate message management
sed -i '' "s/'消息中心'/'Message Center'/g" "$ROUTER_FILE"
sed -i '' "s/'消息列表'/'Message List'/g" "$ROUTER_FILE"
sed -i '' "s/'消息发送'/'Message Send'/g" "$ROUTER_FILE"

# Translate log management
sed -i '' "s/'日志中心'/'Log Center'/g" "$ROUTER_FILE"
sed -i '' "s/'用户日志'/'User Log'/g" "$ROUTER_FILE"

# Translate profile
sed -i '' "s/'个人简介'/'Personal Profile'/g" "$ROUTER_FILE"

# Translate error page
sed -i '' "s/'404'/'404'/g" "$ROUTER_FILE"

echo "All Chinese titles in admin router.js have been translated to English."