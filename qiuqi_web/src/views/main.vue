<template>
  <div>
    <el-row>
      <el-col
        :xs="24"
        :sm="24"
        :md="24"
        :lg="24"
        :xl="24"
        style="text-align: center"
      >
        <el-container>
          <!-- header部分 -->
          <el-header>
            <navtop></navtop>
          </el-header>

          <el-container>
            <!-- aside部分 -->
            <leftNav></leftNav>
            <el-card class="box-card">
              <el-main>
                <!-- main部分 -->
                <keep-alive>
                  <router-view />
                </keep-alive>
                <!-- 路由容器 -->
              </el-main>
            </el-card>
          </el-container>
        </el-container>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import leftNav from "../components/nav.vue";
import navtop from "../components/nav-top.vue";
export default {
  components: {
    navtop,
    leftNav,
  },
  data() {
    return {};
  },
  beforeCreate() {
    const role = localStorage.getItem("role");
    if (role == "" || role == null) {
      this.$router.push("/");
    }
  },
};
</script>

<style>
/* * {
  background-color: aqua;
} */
.el-main {
  margin: 0px;
  padding: 0px;
  background-color: #fafafafd;
}
.el-header,
.el-footer {
  background-color: #545c64;
  box-sizing: border-box;
  border-bottom: 1px solid #f5f1f1;
}
.el-container {
  height: 100%;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 100%;
  padding: 10px;
  margin: 10px;
}
</style>
