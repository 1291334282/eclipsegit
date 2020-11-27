<template>
  <div class="top">
    <el-row>
      <el-col :span="3">
        <a @click="gowelcome">
          <img class="logo" src="../assets/imgs/logo.png" alt="Logo" />
        </a>
      </el-col>
      <el-col :span="10" :offset="5">
        <h1>GBA Issue管理系统</h1>
      </el-col>
      <el-col :span="3" :offset="3">
        <el-dropdown
          ><span>
            <a class="user" @click="ModifyUserInformation"
              ><span class="userimg">
                <el-avatar
                  icon="el-icon-user-solid"
                  class="headerLogo"
                ></el-avatar>
              </span>
              <span class="username" style="color: #1e90ff; font-size: 15px">{{
                user
              }}</span>
            </a></span
          >
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item
              @click.native="userinfo"
              v-if="role != '超级Admin'"
              >修改用户信息</el-dropdown-item
            >
            <el-dropdown-item @click.native="logout" divided
              >退出登录</el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import router from "@/router";
export default {
  data() {
    return {
      role: "",
      user: "user",
      activeIndex: "1",
    };
  },
  mounted() {
    this.user = localStorage.getItem("username");
    this.role = localStorage.getItem("role");
    console.log(this.role);
  },
  methods: {
    gowelcome() {
      this.$router.push("/main/welcome");
    },
    // handleSelect (key, keyPath) {
    //   console.log(key, keyPath);
    // },
    ModifyUserInformation() {},
    userinfo() {
      window.location.href = "/userinfo";
    },
    logout() {
      this.$confirm("是否注销登录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      }).then(() => {
        //缓存id、用户名、权限
        localStorage.setItem("userID", "");
        localStorage.setItem("username", "");
        localStorage.setItem("role", "");
        window.location.href = "/";
      });
    },
  },
};
</script>

<style scoped>
.el-aside {
  display: flex;
  justify-content: center;
  align-items: center;
}
section {
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 20px;
}
.logo {
  width: 50px;
  height: 50px;
}
.headerLogo,
.logo {
  cursor: pointer;
}
.user {
  font-size: 12px;
  min-height: 31px;
  display: flex;
  align-items: center;
  justify-content: center;
  /* height: 20px; */
  margin-top: 5px;
  color: #202046;
  white-space: normal;
}
/* .username {
  text-align: left;
  font-size: 10px;
  text-overflow: -o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
} */
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
h1 {
  color: #7ae2f0;
}
</style>
