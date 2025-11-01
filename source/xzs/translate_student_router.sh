#!/bin/bash

# Script to translate Chinese titles in student router.js

ROUTER_FILE="/Users/rongpengli/Documents/GitHub/xzs/source/vue/xzs-student/src/router.js"

echo "Translating Chinese titles in student router.js..."

# Translate all route titles
sed -i '' "s/'登录'/'Login'/g" "$ROUTER_FILE"
sed -i '' "s/'注册'/'Register'/g" "$ROUTER_FILE"
sed -i '' "s/'首页'/'Home'/g" "$ROUTER_FILE"
sed -i '' "s/'试卷中心'/'Exam Center'/g" "$ROUTER_FILE"
sed -i '' "s/'考试记录'/'Exam Records'/g" "$ROUTER_FILE"
sed -i '' "s/'错题本'/'Wrong Questions'/g" "$ROUTER_FILE"
sed -i '' "s/'个人中心'/'Personal Center'/g" "$ROUTER_FILE"
sed -i '' "s/'消息中心'/'Message Center'/g" "$ROUTER_FILE"
sed -i '' "s/'试卷答题'/'Exam Answering'/g" "$ROUTER_FILE"
sed -i '' "s/'试卷批改'/'Exam Grading'/g" "$ROUTER_FILE"
sed -i '' "s/'试卷查看'/'Exam Viewing'/g" "$ROUTER_FILE"

# Translate 404 page
sed -i '' "s/'404'/'404'/g" "$ROUTER_FILE"

echo "All Chinese titles in student router.js have been translated to English."