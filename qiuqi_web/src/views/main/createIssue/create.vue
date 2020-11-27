<template>
  <div id="create">
    <div>
      <h2 style="color:#8A2BE2">Issue 题目</h2>
      <el-input type="text"
                placeholder="内容"
                v-model="title"
                maxlength="80"
                show-word-limit
                :clearable="true"></el-input>
    </div>
    <!-- 基本信息栏 -->
    <h3>基本信息</h3>
    <hr />
    <!-- <hr style="border: 1px dashed #000; height: 1px" /> -->

    <el-row :gutter="50">
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <p>Issue No.</p>
          <p>
            <el-input v-model="issueid"
                      placeholder=""
                      :disabled="true"></el-input>
          </p>
        </div>
      </el-col>
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <p>创建时间</p>
          <p>
            <el-input v-model="createtime"
                      placeholder=""
                      :disabled="true"></el-input>
          </p>
        </div>
      </el-col>
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <p>Issue类型</p>
          <p>
            <el-input v-model="issusetype"
                      placeholder="内容"
                      maxlength="30"
                      show-word-limit
                      :clearable="true"></el-input>
          </p>
        </div>
      </el-col>
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <p>Issue等级</p>
          <p>
            <el-select v-model="issuserank"
                       filterable
                       placeholder="请选择"
                       :clearable="true">
              <el-option v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </p>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="50">
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <p>影响版本</p>
          <p>
            <el-input v-model="version"
                      maxlength="30"
                      show-word-limit
                      :clearable="true"></el-input>
          </p>
        </div>
      </el-col>
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <p>计划修改时间</p>
          <p>
            <el-date-picker v-model="planTime"
                            type="date"
                            placeholder="选择日期"
                            :picker-options="pickerOptions0"
                            format="yyyy 年 MM 月 dd 日"
                            value-format="yyyy-MM-dd"
                            :clearable="true">
            </el-date-picker>
          </p>
        </div>
      </el-col>
    </el-row>
    <!-- 重现步骤栏 -->
    <div style="margin-bottom: 100px">
      <h4>重现步骤</h4>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="24"
              :xl="24">
        <div class="grid-content">
          <el-input type="textarea"
                    placeholder="请输入内容"
                    rows="5"
                    v-model="step"
                    maxlength="2000"
                    show-word-limit
                    :clearable="true"></el-input>
        </div>
      </el-col>
    </div>
    <!-- 指派修改人栏 -->
    <div>
      <el-col :xs="12"
              :sm="6"
              :md="6"
              :lg="6"
              :xl="6">
        <div class="grid-content">
          <h4>指派修改人栏</h4>
          <el-select v-model="modifier"
                     style="text-align:center"
                     filterable
                     placeholder="请选择"
                     :clearable="true">
            <el-option v-for="item in modifierOptions"
                       :label="item.name"
                       :value="item.userID">
            </el-option>
          </el-select>
        </div>
      </el-col>
      <br /><br />
    </div>
    <!-- 提交按钮 -->
    <div>
      <el-col :xs="24"
              :sm="24"
              :md="24"
              :lg="24"
              :xl="24"
              style="text-align: center">
        <el-button type="primary"
                   round
                   @click="createIssue()">提交</el-button>
      </el-col>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      modifierOptions: [],
      options: [
        {
          value: "最高",
          label: "最高",
        },
        {
          value: "较高",
          label: "较高",
        },
        {
          value: "一般",
          label: "一般",
        },
        {
          value: "低",
          label: "低",
        },
      ],
      modifier: "",
      username: "",
      title: "",
      issueid: "",
      createtime: "",
      issuserank: "",
      issusetype: "",
      version: "",
      planTime: "",
      realtime: "",
      step: "",
      userid: "",
      value: "",
      pickerOptions0: {
        disabledDate (time) {
          return time.getTime() < Date.now() - 8.64e7;
        },
      },
    };
  },
  beforeCreate () {
    if (localStorage.getItem("role") == "经理") {
      this.$router.push("/");
    }
  },
  mounted () {
    //获取用户名
    this.username = localStorage.getItem("username");
    //获取当前时间
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
    }
    this.createtime = year + seperator1 + month + seperator1 + strDate;

    console.log(this.username);
    this.searchByUserID();
  },
  methods: {
    //显示用户id
    searchByUserID () {
      this.$axios
        .get("http://120.78.176.2:8080/user/selectuserID")
        .then((res) => {
          let op = []
          for (let i in res.data.list) {
            if (res.data.list[i].userstate == '激活' && res.data.list[i].name != this.username) {
              op.push(res.data.list[i])
            }
            // console.log(op)
          }
          this.modifierOptions = op
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //新增Issue接口--post
    createIssue () {
      //验证是否为空
      let msg = ' ';
      if (this.username == '') {
        msg = '账号过期，请重新登录'
        this.$message(
          {
            type: 'error',
            message: msg
          },
        );
        setTimeout(() => {
          window.location.href = "/"
        }, 1000)
      } else if (this.issuserank == '' || this.planTime == '' || this.step == '' || this.title == '' || this.modifier == '' || this.version == '') {
        msg = '请填写完整信息'
        this.$message(
          {
            type: 'error',
            message: msg
          }
        );
        return
      }
      this.$axios.post('http://120.78.176.2:8080/issue/createIssue',
        this.$qs.stringify({
          creater: this.username,
          level: this.issuserank,
          plantime: this.planTime,
          step: this.step,
          title: this.title,
          type: this.issuserank,
          userID: this.modifier,
          version: this.version
        }))
        .then((res) => {
          console.log(res.data)
          if (res.data.status === "创建成功") {
            this.$message({
              type: 'success',
              message: '创建成功'
            });
          } else {
            this.$message({
              type: 'error',
              message: '创建失败'
            });
          }
        }).catch(function (error) {
          console.log(error);
        });
    }
  }
};
</script>

<style>
.el-col {
  border-radius: 4px;
}
#create {
  width: 1000px;
  margin: 0px 60px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.item .el-form-item__label {
  color: wheat;
  font-size: 20px;
}
h3 {
  color: rgb(202, 101, 211);
}
h4 {
  color: rgb(201, 101, 221);
}
</style>
