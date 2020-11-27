<template>
  <div>
    <!-- <h4 style="text-align: center; width: 1200px">Issue报表查询</h4> -->
    <!-- <hr style="border: 1px dashed #000; height: 1px" /> -->
    <el-container>
      <div style="width: 1200px; text-align: center">
        <el-form :inline="true"
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
                      :clearable="true">
            </el-input>
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
          <h1>issue 报表</h1>
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
                             label="用户 ID"> </el-table-column>
            <el-table-column prop="name"
                             label="用户姓名"> </el-table-column>
            <el-table-column prop="creation"
                             label="创建Issue数">
              <template scope="scope">
                <a :href="'check?username=' + scope.row.name"
                   style="text-decoration: none; color: dodgerblue">{{ scope.row.creation }}</a>
              </template>
            </el-table-column>
            <el-table-column prop="recived"
                             label="收到Issue数">
              <template scope="scope">
                <a :href="'check?id='+scope.row.userID"
                   style="text-decoration:none;color: dodgerblue">{{scope.row.recived}}</a>
              </template>
            </el-table-column>
            <el-table-column prop="resolved"
                             label="修改Issue数">
              <template scope="scope">
                <a :href="'check?id=' + scope.row.userID + '&state=closed'"
                   style="text-decoration: none; color: dodgerblue">{{ scope.row.resolved }}</a>
              </template>
            </el-table-column>
            <!--            <el-table-column prop="closed" label="关闭Issue数">-->
            <!--            </el-table-column>-->
            <el-table-column prop="completion"
                             label="完成率">
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
  name: "",
  data () {
    return {
      formInline: {
        UserId: "",
        UserName: "",
      },
      tableData: [
        {
          id: "",
          name: "",
          createIs: "",
          receiveIs: "",
          changeIs: "",
          complete: "",
        },
      ],
      currentPage: 1, // 当前页码
      total: 0, // 总条数
      pageSize: 5, // 每页的数据条数
    };
  },
  mounted () {
    // this.getIssues();
    this.searchIssue();
  },
  methods: {
    clearvalues () {
      this.formInline.UserId = "";
      this.formInline.UserName = "";
    },
    // getIssues () {
    //   this.$axios
    //     .get("http://120.78.176.2:8080/issue/selectissuecount", {
    //       params: {
    //         pageNum: this.currentPage,
    //         pageSize: this.pageSize,
    //       },
    //     })
    //     .then((res) => {
    //       this.total = res.data.total
    //       this.tableData = res.data.list;
    //       console.log(this.tableData);
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    searchClick () {
      this.currentPage = 1;
      this.searchIssue()
    },
    searchIssue () {
      this.$axios
        .get("http://120.78.176.2:8080/issue/selectissuebyidorname", {
          params: {
            userID: this.formInline.UserId,
            name: this.formInline.UserName,
            pageNum: this.currentPage,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          this.total = res.data.total;
          this.tableData = res.data.list;
          console.log(this.tableData);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
      this.searchIssue()
    },
    // //验证是否输入信息
    // validate () {
    //   if ((this.formInline.UserId == '' || this.formInline.UserId == null) &&
    //     (this.formInline.UserName == '' || this.formInline.UserName == null)) {
    //     this.getIssues()
    //   } else {
    //
    //   }
    //   this.searchIssue()
    // },
    //当前页改变时触发 跳转其他页
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.searchIssue()
    },
    getRowClass ({ rowIndex }) {
      if (rowIndex == 0) {
        return "background:#81BEF7";
      } else {
        return "";
      }
    },
  },
};
</script>
