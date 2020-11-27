<template>
  <div>
    <div class="background">
      <img :src="imgSrc"
           width="100%"
           height="100%"
           alt="" />
    </div>
    <div class="front">
      <el-container>
        <el-header>GBA Issue管理系统</el-header>
        <!-- 登录模块 -->
        <el-card class="box-card">
          <h2 class="title">登录</h2>
          <el-form :model="ruleForm"
                   :rules="rules"
                   ref="ruleForm"
                   label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="姓名："
                          prop="id">
              <el-input v-model="ruleForm.id"
                        maxlength="30"
                        show-word-limit
                        style="width:290px"><i slot="prefix"
                   class="el-input__icon el-icon-user-solid"></i></el-input>
            </el-form-item>
            <el-form-item label="密码："
                          prop="pass1">
              <el-input type="password"
                        v-model="ruleForm.pass1"
                        autocomplete="off"
                        maxlength="30"
                        show-password
                        style="width:290px"> <i slot="prefix"
                   class="el-input__icon el-icon-lock"></i></el-input>
            </el-form-item>
            <!-- <el-form-item>
              <el-button type="primary" @click="Login('ruleForm')"
                >登录
              </el-button>
              <el-button @click="FormRegistered()">注册</el-button>
            </el-form-item> -->
          </el-form>
          <div style="text-align:center">
            <el-button type="primary"
                       @click="Login('ruleForm')">登录
            </el-button>
            <el-button @click="FormRegistered()">注册</el-button>
          </div>
        </el-card>
      </el-container>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    return {
      /*用来设置背景图片 */
      imgSrc: require("../assets/imgs/loginbg.jpg"),
      activeName: "second",
      ruleForm: {
        delivery: false,
        type: [],
        id: "",
        loginid: "",
        pass1: "",
      },
      rules: {
        id: [
          { required: true, message: "请输入系统ID", trigger: "blur" },
          { max: 30, message: "长度在 30 个字符", trigger: "blur" },
        ],
        pass1: [{ required: true, validator: validatePass, trigger: "blur" }],
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { max: 30, message: "长度在 30 个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    FormRegistered () {
      window.location.href = "/Registered";
      // this.$router.push("/Registered");
    },
    //用户登录
    Login (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post(
              "http://120.78.176.2:8080/loginregister/login",
              this.$qs.stringify({
                name: this.ruleForm.id,
                password: this.ruleForm.pass1,
              })
            )
            .then((res) => {
              console.log(res.data.status);
              if (res.data.status == "登陆成功") {
                //缓存id、用户名、权限
                localStorage.setItem("userID", res.data.userID);
                localStorage.setItem("username", res.data.name);
                localStorage.setItem("role", res.data.role);

                this.username = localStorage.setItem(
                  "username",
                  this.ruleForm.id
                );
                //登录成功后路由到主页面
                this.$message(
                  {
                    type: "success",
                    message: "登录成功",
                  },
                  (window.location.href = "/main/welcome")
                );
              } else if (res.data.status == "用户名不存在") {
                this.$message({
                  type: "error",
                  message: "用户不存在",
                });
              } else if (res.data.status == "该用户已注销") {
                this.$message({
                  type: "error",
                  message: "该用户已注销",
                });
              } else {
                this.$message({
                  type: "error",
                  message: "登录失败",
                });
              }
            })
            .catch(function (error) {
              console.log(error);
            });
        }
      });
    },
  },
};
</script>

<style>
.el-header {
  color: rgb(218, 241, 9);
  text-align: center;
  line-height: 150px;
  font-style: inherit;
  font-size: 50px;
}

.el-footer {
  color: #333;
  text-align: center;
  line-height: 60px;
  font-style: inherit;
  font-size: 30px;
}

.el-main {
  /* background-color: #e9eef3; */
  color: #333;
  /* text-align: center; */
  /*line-height: 160px;*/
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.box-card {
  width: 480px;
  height: 380px;
  /* margin: 10px;
  padding: 30px; */
  line-height: 80px;
  align-self: center;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  top: 0;
  margin: auto;
  border-radius: 30px 30px 30px 30px;
}
.title {
  text-align: center;
  /* font-size: 20px; */
  font-weight: bold;
}
/* 图片背景样式 */
.background {
  width: 100%;
  height: 100%; /**宽高100%是为了图片铺满屏幕 */
  z-index: -1;
  position: fixed;
  /* filter: alpha(Opacity=85);
  -moz-opacity: 0.85;
  opacity: 0.85; */
}

/* 图片和内容的层叠显示 */
.front {
  z-index: 1;
  /* position: absolute; */
  /* 设置透明度 */
  filter: alpha(Opacity=85); /*兼容ie*/
  -moz-opacity: 0.85;
  opacity: 0.85;
}
</style>
