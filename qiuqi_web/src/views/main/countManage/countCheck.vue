<template>
  <div>
    <!-- <h4 style="text-align:center;width:1200px;">账户管理</h4>
    <hr style="border: 1px dashed #000; height: 1px" /> -->
    <el-container>
      <div style="text-align: center; width: 1200px">
        <el-form ref="form"
                 :inline="true"
                 :model="formInline"
                 class="demo-form-inline">
          <el-form-item label="">
            <el-input v-model="formInline.UserId"
                      placeholder="请输入用户ID"
                      maxlength="30"
                      show-word-limit
                      :clearable="true"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-input v-model="formInline.UserName"
                      placeholder="请输入用户姓名"
                      maxlength="30"
                      show-word-limit
                      :clearable="true"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       @click="searchClick">查询</el-button>
            <el-button @click="clearvalues">清空</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-container>
    <div>
      <!-- 路由容器 -->
      <!-- <router-view /> -->
      <el-row>
        <el-col :span="3">
          <h1>账户管理</h1>
        </el-col>
      </el-row>
      <div>
        <el-container>
          <el-table border
                    :data="tableData"
                    style="width: 100%"
                    :header-cell-style="getRowClass">
            <el-table-column type="selection"></el-table-column>
            <el-table-column type="index"
                             label="序号"></el-table-column>
            <el-table-column prop="userID"
                             label="用户 ID"></el-table-column>
            <el-table-column prop="name"
                             label="用户姓名"></el-table-column>
            <el-table-column prop="email"
                             label="邮箱"></el-table-column>
            <el-table-column prop="registertime"
                             label="注册时间"></el-table-column>
            <el-table-column prop="role"
                             label="用户身份"></el-table-column>
            <el-table-column prop="userstate"
                             label="账户状态">
              <template scope="scope">
                <el-tag color="white"
                        :type="
                    scope.row.userstate === '注销'
                      ? 'danger'
                      : scope.row.userstate === '激活'
                      ? 'success'
                      : ''
                  ">{{ scope.row.userstate }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column fixed="right"
                             label="操作"
                             width="150">
              <template slot-scope="scope">
                <el-button @click="cancel(scope.row)"
                           type="danger"
                           size="mini"
                           v-if="
                    scope.row.userstate != '注销' &&
                    scope.row.role != '超级Admin'
                  ">注销</el-button>
                <el-button @click="manager(scope.row)"
                           type="primary"
                           size="mini"
                           v-if="
                    scope.row.userstate != '注销' &&
                    scope.row.role == '普通员工'
                  ">经理</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-container>
        <div class="paginationClass">
          <el-pagination align="center"
                         @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="currentPage"
                         :page-sizes="[1, 5, 10, 20]"
                         :page-size="pageSize"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="total">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      formInline: {
        UserId: "",
        UserName: "",
      },
      tableData: [
        {
          id: "",
          title: "",
          founder: "",
          timecreate: "",
          modifier: "",
          statement: "",
        },
      ],
      currentPage: 1, // 当前页码
      total: 0, // 总条数
      pageSize: 20, // 每页的数据条数
    };
  },
  beforeCreate () {
    if (localStorage.getItem("role") != "超级Admin") {
      this.$router.push("/");
    }
  },
  mounted () {
    this.getUserInfo();
  },
  methods: {
    // 清楚输入值
    clearvalues () {
      this.formInline.UserId = "";
      this.formInline.UserName = "";
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
      this.getUserInfo();
    },
    //验证是否输入信息
    validate () {
      if (
        (this.formInline.UserId == "" || this.formInline.UserId == null) &&
        (this.formInline.UserName == "" || this.formInline.UserName == null)
      ) {
        this.getUserInfo();
      } else {
        this.searchUser();
      }
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.validate();
    },
    getRowClass ({ rowIndex }) {
      if (rowIndex === 0) {
        return "background:#81BEF7";
      } else {
        return "";
      }
    },
    getUserInfo () {
      this.$axios
        .get("http://120.78.176.2:8080/user/selectalluser", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          this.total = res.data.total;
          this.tableData = res.data.list;
          // console.log(res.data)
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchClick () {
      this.currentPage = 1;
      this.validate();
    },
    searchUser () {
      this.$axios
        .get("http://120.78.176.2:8080/user/selectidorname", {
          params: {
            name: this.formInline.UserName,
            userID: this.formInline.UserId,
            pageNum: this.currentPage,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          this.total = res.data.total;
          this.tableData = res.data.list;
          // console.log(res.data)
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    cancel (index) {
      this.$confirm("确定将" + index.name + "注销吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .post(
              "http://120.78.176.2:8080/user/updateuserstate",
              this.$qs.stringify({
                userID: parseInt(index.userID),
              })
            )
            .then(() => {
              this.$message({
                type: "success",
                message: "注销成功!",
              });
            })
            .catch(() => {
              this.$message({
                type: "error",
                message: "注销失败!",
              });
            });
          this.getUserInfo();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消注销",
          });
        });
    },
    manager (index) {
      this.$confirm("确定将" + index.name + "提升为经理吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .post(
              "http://120.78.176.2:8080/user/updaterole",
              this.$qs.stringify({
                userID: parseInt(index.userID),
              })
            )
            .then((res) => {
              console.log(res.data);
              if (res.data.status === "用户名已被注销，不能修改为经理") {
                this.$message({
                  type: "error",
                  message: "用户名已被注销，不能修改为经理!",
                });
              } else {
                this.$message({
                  type: "success",
                  message: "操作成功!",
                });
              }
            })
            .catch(() => {
              this.$message({
                type: "error",
                message: "注销失败!",
              });
            });
          this.getUserInfo();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>





