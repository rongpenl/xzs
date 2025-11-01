import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout'

Vue.use(Router)

const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    hidden: true,
    component: () => import('@/views/login/index'),
    meta: { title: 'Login' }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'Home', icon: 'home', affix: true }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    name: 'UserPage',
    meta: {
      title: 'User Management',
      icon: 'users'
    },
    children: [
      {
        path: 'student/list',
        component: () => import('@/views/user/student/list'),
        name: 'UserStudentPageList',
        meta: { title: 'Student List', noCache: true }
      },
      {
        path: 'student/edit',
        component: () => import('@/views/user/student/edit'),
        name: 'UserStudentEdit',
        meta: { title: 'Student Edit', noCache: true, activeMenu: '/user/student/list' },
        hidden: true
      },
      {
        path: 'admin/list',
        component: () => import('@/views/user/admin/list'),
        name: 'UserAdminPageList',
        meta: { title: 'Admin List', noCache: true }
      },
      {
        path: 'admin/edit',
        component: () => import('@/views/user/admin/edit'),
        name: 'UserAdminEdit',
        meta: { title: 'Admin Edit', noCache: true, activeMenu: '/user/admin/list' },
        hidden: true
      }
    ]
  },
  {
    path: '/exam',
    component: Layout,
    name: 'ExamPage',
    meta: {
      title: 'Exam & Question Management',
      icon: 'exam'
    },
    children: [
      {
        path: 'paper/list',
        component: () => import('@/views/exam/paper/list'),
        name: 'ExamPaperPageList',
        meta: { title: 'Exam Paper List', noCache: true }
      },
      {
        path: 'paper/edit',
        component: () => import('@/views/exam/paper/edit'),
        name: 'ExamPaperEdit',
        meta: { title: 'Exam Paper Edit', noCache: true, activeMenu: '/exam/paper/list' },
        hidden: true
      },
      {
        path: 'question/list',
        component: () => import('@/views/exam/question/list'),
        name: 'ExamQuestionPageList',
        meta: { title: 'Question List', noCache: true }
      },
      {
        path: 'question/edit/singleChoice',
        component: () => import('@/views/exam/question/edit/single-choice'),
        name: 'singleChoicePage',
        meta: { title: 'Single Choice Edit', noCache: true, activeMenu: '/exam/question/list' },
        hidden: true
      },
      {
        path: 'question/edit/multipleChoice',
        component: () => import('@/views/exam/question/edit/multiple-choice'),
        name: 'multipleChoicePage',
        meta: { title: 'Multiple Choice Edit', noCache: true, activeMenu: '/exam/question/list' },
        hidden: true
      },
      {
        path: 'question/edit/trueFalse',
        component: () => import('@/views/exam/question/edit/true-false'),
        name: 'trueFalsePage',
        meta: { title: 'True/False Edit', noCache: true, activeMenu: '/exam/question/list' },
        hidden: true
      },
      {
        path: 'question/edit/gapFilling',
        component: () => import('@/views/exam/question/edit/gap-filling'),
        name: 'gapFillingPage',
        meta: { title: 'Fill in the Blank Edit', noCache: true, activeMenu: '/exam/question/list' },
        hidden: true
      },
      {
        path: 'question/edit/shortAnswer',
        component: () => import('@/views/exam/question/edit/short-answer'),
        name: 'shortAnswerPage',
        meta: { title: 'Short Answer Edit', noCache: true, activeMenu: '/exam/question/list' },
        hidden: true
      }
    ]
  },
  {
    path: '/task',
    component: Layout,
    name: 'TaskPage',
    meta: {
      title: 'Task Management',
      icon: 'task'
    },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        component: () => import('@/views/task/list'),
        name: 'TaskListPage',
        meta: { title: 'Task List', noCache: true }
      },
      {
        path: 'edit',
        component: () => import('@/views/task/edit'),
        name: 'TaskEditPage',
        meta: { title: 'Task Create', noCache: true }
      }
    ]
  },
  {
    path: '/education',
    component: Layout,
    name: 'EducationPage',
    meta: {
      title: 'Education Management',
      icon: 'education'
    },
    alwaysShow: true,
    children: [
      {
        path: 'subject/list',
        component: () => import('@/views/education/subject/list'),
        name: 'EducationSubjectPage',
        meta: { title: 'Subject List', noCache: true }
      },
      {
        path: 'subject/edit',
        component: () => import('@/views/education/subject/edit'),
        name: 'EducationSubjectEditPage',
        meta: { title: 'Subject Edit', noCache: true, activeMenu: '/education/subject/list' },
        hidden: true
      }
    ]
  },
  {
    path: '/answer',
    component: Layout,
    name: 'AnswerPage',
    meta: {
      title: 'Grade Management',
      icon: 'answer'
    },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        component: () => import('@/views/answer/list'),
        name: 'AnswerPageList',
        meta: { title: 'Answer Paper List', noCache: true }
      }
    ]
  },
  {
    path: '/message',
    component: Layout,
    name: 'MessagePage',
    meta: {
      title: 'Message Center',
      icon: 'message'
    },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        component: () => import('@/views/message/list'),
        name: 'MessageListPage',
        meta: { title: 'Message List', noCache: true }
      },
      {
        path: 'send',
        component: () => import('@/views/message/send'),
        name: 'MessageSendPage',
        meta: { title: 'Message Send', noCache: true }
      }
    ]
  },
  {
    path: '/log',
    component: Layout,
    name: 'LogPage',
    meta: {
      title: 'Log Center',
      icon: 'log'
    },
    alwaysShow: true,
    children: [
      {
        path: 'user/list',
        component: () => import('@/views/log/list'),
        name: 'LogUserPage',
        meta: { title: 'User Log', noCache: true }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: 'Personal Profile', icon: 'user', noCache: true }
      }
    ]
  },
  { path: '*',
    hidden: true,
    component: () => import('@/views/error-page/404'),
    meta: { title: '404', noCache: true }
  }
]

const router = new Router({
  routes: constantRoutes
})

export {
  constantRoutes,
  router
}
