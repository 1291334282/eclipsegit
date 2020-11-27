// Issue查询
// 普通用户--只能查看自己被分配到的issu信息，并且进行修改
// 经理--可以查看所有的issue条目
// Admin--可以查看所有的用户的issue信息，并且可以修改
<template>
  <div>
    <el-row :gutter="0">
      <el-col :span="6" :offset="1">
        <div class="grid-content bg-purple">
          <el-form
            :label-position="labelPosition"
            label-width="80px"
            :model="formLabelAlign1"
            ref="formLabelAlignref1"
          >
            <el-form-item label="Issue No" prop="issueno">
              <el-input
                v-model="formLabelAlign1.issueno"
                placeholder="请输入"
                style="text-align: center; width: 200px"
                maxlength="30"
                show-word-limit
                :clearable="true"
              ></el-input>
            </el-form-item>
            <el-form-item label="创建人" prop="createtor">
              <el-input
                v-model="formLabelAlign1.createtor"
                placeholder="请输入"
                style="text-align: center; width: 200px"
                maxlength="30"
                show-word-limit
                :clearable="true"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content bg-purple">
          <el-form
            :label-position="labelPosition"
            label-width="80px"
            :model="formLabelAlign2"
            ref="formLabelAlignref2"
          >
            <el-form-item label="Issue状态" prop="issuserank">
              <el-select
                v-model="formLabelAlign2.issuserank"
                placeholder="请选择"
                style="text-align: center; width: 200px"
                :clearable="true"
              >
                <el-option
                  v-for="item in options"
                  :key="item.label"
                  :label="item.label"
                  :value="item.label"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="修改人" prop v-if="this.role == '普通员工'">
              <el-input
                v-model="formLabelAlign2.modifier"
                placeholder="请输入"
                style="text-align: center; width: 200px"
                maxlength="30"
                disabled
              ></el-input>
            </el-form-item>
            <el-form-item label="修改人" prop="modifier" v-else>
              <el-input
                v-model="formLabelAlign2.modifier"
                placeholder="请输入"
                style="text-align: center; width: 200px"
                maxlength="30"
                :clearable="true"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <el-col :span="3">
        <div class="grid-content bg-purple">
          <el-form
            :label-position="labelPosition"
            label-width="80px"
            :model="formLabelAlign3"
            ref="formLabelAlignref3"
          >
            <el-form-item label="创建时间" prop="createtime">
              <el-date-picker
                v-model="formLabelAlign3.createtime"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions0(formLabelAlign4.createtimeto)"
                style="text-align: center; width: 150px"
                :clearable="true"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="修改时间" prop="changetime">
              <el-date-picker
                v-model="formLabelAlign3.changetime"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions0(formLabelAlign4.changetimeto)"
                style="text-align: center; width: 150px"
                :clearable="true"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <el-col :span="3">
        <div class="grid-content bg-purple">
          <el-form
            :label-position="labelPosition"
            label-width="80px"
            :model="formLabelAlign4"
            ref="formLabelAlignref4"
          >
            <el-form-item label="" prop="createtimeto">
              <el-date-picker
                v-model="formLabelAlign4.createtimeto"
                type="date"
                placeholder="至"
                :picker-options="pickerOptione0(formLabelAlign3.createtime)"
                style="text-align: center; width: 150px"
                :clearable="true"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="" prop="changetimeto">
              <el-date-picker
                v-model="formLabelAlign4.changetimeto"
                type="date"
                placeholder="至"
                :picker-options="pickerOptione0(formLabelAlign3.changetime)"
                style="text-align: center; width: 150px"
                :clearable="true"
                maxlength="30"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
    <div>
      <el-row>
        <el-col
          :xs="22"
          :sm="22"
          :md="22"
          :lg="22"
          :xl="22"
          style="text-align: center"
        >
          <div class="button">
            <el-button type="primary" @click="searchClick">查询</el-button>
            <el-button @click="clearvalues">清空</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-row>
        <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
          <h1>Issue 列表</h1>
        </el-col>
      </el-row>
    </div>
    <div>
      <div>
        <el-col
          :xs="24"
          :sm="24"
          :md="24"
          :lg="24"
          :xl="24"
          style="text-align: center"
        >
          <el-container>
            <el-table
              border
              :data="tableData"
              style="width: 100%"
              :header-cell-style="getRowClass"
            >
              <el-table-column type="selection"></el-table-column>
              <el-table-column type="index" label="序号"></el-table-column>
              <el-table-column prop="issueID" label="Issue ID">
              </el-table-column>
              <el-table-column prop="title" label="Issue 标题">
              </el-table-column>
              <el-table-column prop="creater" label="创建人"></el-table-column>
              <el-table-column prop="createtime" label="创建时间">
              </el-table-column>
              <el-table-column prop="name" label="修改人"></el-table-column>
              <el-table-column prop="issuestate" label="Issue 状态">
              </el-table-column>
              <el-table-column prop="plantime" label="计划完成时间">
              </el-table-column>
              <el-table-column prop="acttime" label="实际完成时间">
              </el-table-column>
              <!--Issue详情弹窗-->
              <el-dialog
                  title="Issue详情"
                  :visible.sync="dialogVisible"
                  width="30%"
                  :append-to-body="true">

                <div>
                  <p>IssueID：{{ tableData[currentIndex].issueID }}</p>
                  <p>Issue名称：{{ tableData[currentIndex].title }}</p>
                  <p>解决方案：{{ solutionPlan }}</p>
                  <p>创建时间：{{ tableData[currentIndex].createtime }}</p>
                  <p>等级：{{ tableData[currentIndex].level }}</p>
                  <p>创建者：{{ tableData[currentIndex].creater }}</p>
                  <p>状态：{{ tableData[currentIndex].issuestate }}</p>
                </div>
              </el-dialog>
              <!--  修改方案弹窗-->
              <el-dialog
                  title="修改"
                  :visible.sync="change"
                  width="50%"
                  :append-to-body="true"
              >
                <div>
                  <h3>解决方案：</h3>
                </div>
                <div>
                  <el-input
                      type="textarea"
                      :autosize="{ minRows: 4, maxRows: 6 }"
                      placeholder="请输入内容"
                      v-model="solute"
                  >
                  </el-input>
                </div>
                <div style="text-align: center">
                  <el-button round @click="submit">提交方案</el-button>
                </div>
              </el-dialog>
              <el-table-column fixed="right" label="操作" width="150">
                <template scope="scope">
                  <el-button
                    @click="detailClick(scope.row.title, scope.$index)"
                    type="info"
                    size="mini"
                    >详情
                  </el-button>

                  <el-button
                    type="warning"
                    size="mini"
                    @click="changeClick(scope.row.issueID)"
                    v-if="
                      role == '普通员工' && scope.row.issuestate == '待修改'">修改
                  </el-button>



                </template>
              </el-table-column>
            </el-table>
          </el-container>
        </el-col>
        <div class="paginationClass">
          <el-col
            :xs="24"
            :sm="24"
            :md="24"
            :lg="24"
            :xl="24"
            style="text-align: center"
          >
            <el-pagination
              align="center"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[1, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            >
            </el-pagination>
          </el-col>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Dialog } from "element-ui";

export default {
  name: "list",
  data() {
    return {
      options: [
        {
          value: "选项1",
          label: "待修改",
        },
        {
          value: "选项2",
          label: "待验证",
        },
        {
          value: "选项3",
          label: "已关闭",
        },
      ],
      formLabelAlign1: {
        issueno: "",
        createtor: "",
      },
      formLabelAlign2: {
        issuserank: "",
        modifier: "",
      },
      formLabelAlign3: {
        createtime: "",
        changetime: "",
      },
      formLabelAlign4: {
        createtimeto: "",
        changetimeto: "",
      },
      value: "",
      labelPosition: "left",
      dialogVisible: false,
      dialogFormVisible: false,
      change: false,
      solute: "",
      solutionPlan: "",
      currentIssueId: 0,
      currentIndex:0,
      reverse: true,
      test: "testIssssssue",
      solutePlan: "solutePlan",
      activities: [
        {
          content: "已关闭",
          timestamp: "2018-04-15",
        },
        {
          content: "目前状态：",
          timestamp: "2018-04-13",
        },
        {
          content: "创建issue",
          timestamp: "2018-04-11",
        },
      ],
      tableData: [
        {
          id: "",
          title: "",
          founder: "",
          timecreate: "",
          modifier: "",
          statement: "",
          cmptplan: "",
          cmpttrue: "",
        },
      ],
      role: "",
      userid: "",
      username: "",
      currentPage: 1, // 当前页码
      total: 0, // 总条数
      pageSize: 20, // 每页的数据条数
      pickerOptions0(val) {
        return {
          disabledDate(time) {
            return time.getTime() > new Date(val).getTime();
          },
        };
      },
      pickerOptione0(val) {
        return {
          disabledDate(time) {
            return time.getTime() < new Date(val).getTime();
          },
        };
      },
    };
  },
  created() {
    this.userid = localStorage.getItem("userID");
    this.username = localStorage.getItem("username");
    this.role = localStorage.getItem("role");
  },
  mounted() {
    let queryParam = this.$route.query;
    if (Object.keys(queryParam).length == 0) {
      if (this.role == "普通员工") {
        this.formLabelAlign2.modifier = this.username;
      }
    } else {
      this.formLabelAlign2.modifier = queryParam.id;
      this.formLabelAlign1.createtor = queryParam.username;
      if (queryParam.state == "closed") {
        this.formLabelAlign2.issuserank = "已关闭";
      }
    }
    this.searchIssue();
  },
  methods: {
    //处理搜索点击事件
    searchClick() {
      this.currentPage = 1;
      this.searchIssue();
    },
    //获得Issue列表
    searchIssue() {
      this.$axios
        .get("http://120.78.176.2:8080/issue/selectLikeIssue", {
          params: {
            issueID: this.formLabelAlign1.issueno,
            issuestate: this.formLabelAlign2.issuserank,
            creater: this.formLabelAlign1.createtor,
            createtime: this.formLabelAlign3.createtime,
            createtime1: this.formLabelAlign4.createtimeto,
            plantime: this.formLabelAlign3.changetime,
            plantime1: this.formLabelAlign4.changetimeto,
            name: this.formLabelAlign2.modifier, //userid
            pageNum: this.currentPage,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          this.total = res.data.total;
          if( Object.keys(res.data.list).length==0){
            console.log('空')
            this.total=0
            this.tableData= [
              {
                id: "",
                title: "",
                founder: "",
                timecreate: "",
                modifier: "",
                statement: "",
                cmptplan: "",
                cmpttrue: "",
              },
            ]
          }else {
            this.tableData = res.data.list;
            console.log(this.tableData);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //页号改变事件
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
      this.searchIssue();
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.searchIssue();
    },
    getRowClass({ rowIndex }) {
      if (rowIndex == 0) {
        return "background:#81BEF7";
      } else {
        return "";
      }
    },
    golist() {},
    //清空输入框
    clearvalues() {
      this.$refs.formLabelAlignref1.resetFields();
      this.$refs.formLabelAlignref2.resetFields();
      this.$refs.formLabelAlignref3.resetFields();
      this.$refs.formLabelAlignref4.resetFields();
    },
    //详情点击
    detailClick(title, inx) {
      let index = this.pageSize * (this.currentPage - 1) + inx;
      let sl = this.tableData[index].solution;
      if ((sl == "") | (sl == null)) {
        this.solutionPlan = "(暂无)";
      } else {
        this.solutionPlan = sl;
      }

      this.currentIndex=index
      this.dialogVisible = true;
    },
    //点击修改
    changeClick(id) {
      this.solute = "";
      this.change = true;
      this.currentIssueId = id;
    },
    //提交解决方案
    submit() {
      this.$axios
        .post(
          "http://120.78.176.2:8080/issue/updateUserSolution",
          this.$qs.stringify({
            issueID: this.currentIssueId,
            solution: this.solute,
            userID: this.userid,
          })
        )
        .then((res) => {
          console.log(res.data);
          if (res.data.status === "修改成功") {
            this.$message({
              type: "success",
              message: "修改成功",
            });
            this.change = false;
            this.searchIssue();
          } else {
            this.$message({
              type: "error",
              message: "修改成功",
            });
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },

  },
};
</script>

<style>
.el-col {
  border-radius: 4px;
}

#create {
  width: 1000px;
  margin: 50px 150px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
