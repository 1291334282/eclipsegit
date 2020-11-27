<template>
  <div>
    <div class="background">
      <img :src="imgSrc"
           width="100%"
           height="100%"
           alt="" />
    </div>
    <div class="front">
      <el-row>
        <el-col :span="12"
                :offset="12">
          <el-card class="box-card">
            <h2 class="title">新用户注册</h2>
            <el-form :model="ruleForm"
                     :rules="rules"
                     ref="ruleForm"
                     label-width="100px"
                     class="demo-ruleForm">
              <el-form-item label="登录ID："
                            prop="loginid">
                <el-input v-model="ruleForm.loginid"
                          maxlength="10"
                          show-word-limit
                          style="width:330px"></el-input>
              </el-form-item>
              <el-form-item label="用户名："
                            prop="name">
                <el-input v-model="ruleForm.name"
                          maxlength="20"
                          show-word-limit
                          style="width:330px"
                          :clearable="true"></el-input>
              </el-form-item>
              <el-form-item label="邮箱："
                            prop="mail">
                <el-input v-model="ruleForm.mail"
                          maxlength="30"
                          show-word-limit
                          style="width:330px"
                          :clearable="true"></el-input>
              </el-form-item>
              <el-form-item label="输入密码："
                            prop="pass">
                <el-input type="password"
                          v-model="ruleForm.pass"
                          autocomplete="off"
                          maxlength="30"
                          show-password
                          style="width:330px"
                          :clearable="true"></el-input>
              </el-form-item>
              <el-form-item label="确认密码："
                            prop="checkPass">
                <el-input type="password"
                          v-model="ruleForm.checkPass"
                          autocomplete="off"
                          maxlength="30"
                          show-password
                          style="width:330px"
                          :clearable="true"></el-input>
              </el-form-item>
              <!-- <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')"
                  >注册</el-button
                >
                <el-button @click="goback">返回</el-button>
              </el-form-item> -->
            </el-form>
            <div style="text-align:center">
              <el-button type="primary"
                         @click="submitForm('ruleForm')">注册</el-button>
              <el-button @click="goback">返回</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
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
    const validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      imgSrc: require("../assets/imgs/registeredbg.jpg"),
      dialogVisible1: true,
      ruleForm: {
        name: "",
        pass: "",
        checkPass: "",
        mail: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
        id: "",
        loginid: "",
        pass1: "",
      },
      rules: {
        id: [
          { required: true, message: "请输入系统ID", trigger: "blur" },
          { max: 10, message: "长度在 10 个字符以内", trigger: "blur" },
        ],
        pass: [
          { required: true, validator: validatePass, trigger: "blur" },
          {
            pattern: /^(?![a-zA-Z]+$)(?![A-Z\W_]+$)(?![a-z\W_]+$)[a-zA-Z\W_]{8,30}$/,
            message: "密码为大小写字母，特殊符号必须包含三种，长度为 8 - 30位",
          },
        ],
        pass1: [{ required: true, validator: validatePass, trigger: "blur" }],
        checkPass: [
          { required: true, validator: validatePass2, trigger: "blur" },
        ],
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { max: 30, message: "长度在 30 个字符以内", trigger: "blur" },
        ],
        mail: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { max: 30, message: "长度在 30 个字符", trigger: "blur" },
          {
            pattern: /^([a-zA-Z0-9]+[-_.]?)+@[a-zA-Z0-9]+.(com|cn)+$/,
            message: "邮箱格式错误",
          },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        //登录ID
        loginid: [
          { max: 30, message: "长度在 30 个字符", trigger: "blur" },
          { required: true, message: "请输入ID", trigger: "blur" },
          { pattern: /^[0-9]*$/, message: "ID为数字" },
        ],
      },
    };
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post(
              "http://120.78.176.2:8080/loginregister/register",
              this.$qs.stringify({
                email: this.ruleForm.mail,
                name: this.ruleForm.name,
                password: this.ruleForm.pass,
                userID: this.ruleForm.loginid,
              })
            )
            .then(function (res) {
              if (res.data.status == "注册成功") {
                alert("注册成功，现在返回登录页面");
                window.location.href = "/";
              } else if (res.data.status == "用户名已存在") {
                alert("用户名已存在！请重新输入");
                // this.$router.push("/Registered");
                // window.location.href = "./Registered";
              } else {
                alert("输入信息有误!请重新输入");
                // window.location.href = "/Registered";
              }
            })
            .catch(function (error) {
              console.log(error);
            });
        } else {
          alert("注册失败!");
          return false;
        }
      });
    },
    goback () {
      // this.$router.go(-1);
      this.$confirm("是否取消注册?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(() => {
          this.$message(
            {
              type: "success",
              message: "取消注册!",
            },
            this.$router.push("/")
          );
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "继续注册",
          });
        });
    },
    // handleClose (done) {
    //   this.$confirm('确认关闭？')
    //     .then(_ => {
    //       done();
    //       window.location.href = "./";
    //     })
    // }
  },
};
</script>
<style>
.box-card {
  width: 480px;
  height: 550px;
  margin: 50px;
  align-self: center;
  line-height: 80px;
  border-radius: 30px 30px 30px 30px;
  position: absolute;
  /* left: 0;
  right: 0;
  bottom: 0;
  top: 0;
  margin: auto; */
}

.title {
  text-align: center;
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
  opacity: 0.9;
}
</style>
