import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

// import Home from "../views/Home.vue";
// import Login from "../views/Login.vue";
// import Registered from "../views/Registered.vue";
// import Main from '../views/main.vue'
// import Centre from '../views/main/centre.vue'
// import Order from '../views/main/order.vue'

// import Check from '../views/main/check.vue'
// import List from '../views/main/list.vue'
// import Count from '../views/main/count.vue'
// import Create from '../views/main/create.vue'
// import Table from '../views/main/table.vue'

Vue.use(VueRouter);
const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: () =>
      import("../views/Home.vue")
  },

  {
    path: "/userinfo",
    name: "Userinfo",
    component: () =>
      import("../views/userinfo.vue")
  },
  {
    path: "/Registered",
    name: "Registered",
    component: () =>
      import("../views/Registered.vue")
  },
  {
    path: '/main',
    name: '主界面',
    component: () =>
      import("../views/main.vue"),
    children: [
      {
        path: '/main/welcome',
        component: () =>
          import("../views/main/welcome.vue"),
      },
      // 创建issue
      {
        path: '/main/create',
        name: '创建Issue',
        component: () =>
          import("../views/main/createIssue/create.vue"),
      },
      // issue查询
      {
        path: '/main/check',
        name: 'issue查询',
        component: () =>
          import("../views/main/issueCheck/check.vue"),
      },
      {
        path: '/main/checkmycreate',
        name: 'issue查询',
        component: () =>
          import("../views/main/issueCheck/mycreate.vue"),
      },
      // 账户管理
      {
        path: '/main/countcheck',
        name: '账户查询',
        component: () =>
          import("../views/main/countManage/countCheck.vue"),
      },

      // issue报表
      {
        path: '/main/reportcheck',
        name: 'issue报表',
        component: () =>
          import("../views/main/issueReport/reportCheck.vue"),
      },
      {
        path: '/main/graph',
        name: '图表',
        component: () =>
          import("../views/main/graph/graph.vue"),
      },
    ]
  },
  {
    path: '*', component: () =>
      import("../views/404.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
