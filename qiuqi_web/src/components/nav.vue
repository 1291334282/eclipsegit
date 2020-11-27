<template>
  <div>
    <div class="menu" style="height: 100%">
      <el-aside width="210px">
        <el-menu
          default-active="this.$route.path"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          router
        >
          <el-menu-item index="/main/create" v-if="this.role != '经理'">
            <i class="el-icon-circle-plus-outline"></i>
            <span slot="title">创建 Issue</span>
          </el-menu-item>
          <el-menu-item
            index="/main/reportCheck"
            v-if="this.role != '普通员工'"
          >
            <i class="el-icon-document"></i>
            <span slot="title">Issue 报表</span>
          </el-menu-item>
          <!-- 超级Admin登录显示的导航栏选项 -->
          <el-menu-item
            index="/main/countCheck"
            v-if="this.role == '超级Admin'"
          >
            <i class="el-icon-user"></i>
            <span slot="title">账户管理</span>
          </el-menu-item>
          <el-menu-item index="/main/check" v-if="this.role != '普通员工'">
            <i class="el-icon-search"></i>
            <span slot="title">Issue 查询</span>
          </el-menu-item>

          <!-- Admin和经理登录后显示的导航栏选项 -->
          <el-menu-item index="/main/graph" v-if="this.role != '普通员工'">
            <i class="el-icon-pie-chart"></i>
            <span slot="title">Issue 统计</span>
          </el-menu-item>
          <!-- 普通员工登录显示出的导航栏选项 -->
          <el-submenu index="1" v-if="this.role == '普通员工'">
            <template slot="title">
              <i class="el-icon-more-outline"></i>
              <span>Issue 查询</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/main/check">
                <i class="el-icon-bell"></i>我的修改
              </el-menu-item>
              <el-menu-item index="/main/checkmycreate"
                ><i class="el-icon-info"></i>我的创建</el-menu-item
              >
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      role: "",
    };
  },
  mounted() {
    this.role = localStorage.getItem("role");
    console.log(this.role);
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style scoped>
.el-row {
  height: 100%;
}
.el-menu {
  border-right: none;
  height: 750px;
  /* position: fixed; */
  background: #545c64;
}
.el-aside {
  border-right: 1px solid #f5f1f1;
  height: 100%;
  background: #545c64;
}
body,
html {
  height: 100%;
}
</style>