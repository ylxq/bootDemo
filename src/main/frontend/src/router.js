const routers = [
    {
        path: '/',
        meta: {
            title: ''
        },
        component: (resolve) => require(['./views/index.vue'], resolve),
        children:[
            {
                path: '/clazz',
                name: '班级管理',
                meta: {
                    id: "2",
                    role: 1,
                    group: '系统管理'
                },
                component: () => import(/* webpackChunkName: "UserManager" */ './views/clazz/index.vue')
            },
            {
                path: '/student/:id',
                name: '学生管理',
                meta: {
                    id: "2",
                    role: 1,
                    group: '系统管理'
                },
                component: () => import(/* webpackChunkName: "UserManager" */ './views/student/index.vue')
            },
            {
                path: '/analysis/:analysisType/:studentId',
                name: '数据分析',
                meta: {
                    id: "2",
                    role: 1,
                    group: '系统管理'
                },
                component: () => import(/* webpackChunkName: "UserManager" */ './views/analysis/analysis.vue')
            },
            {
                path: '/analysis/all/test/:clazzId',
                name: '数据分析',
                meta: {
                    id: "2",
                    role: 1,
                    group: '系统管理'
                },
                component: () => import(/* webpackChunkName: "UserManager" */ './views/analysis/allTestAnalysis.vue')
            }
        ]
    },
    {
        path: '/login',
        meta: {
            title: '登录'
        },
        component: (resolve) => require(['./views/login/index.vue'], resolve)
    }
];
export default routers;