<template>
  <div>
    <el-row :gutter="0">
      <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
        <div class="grid-content el-col-lg-offset-8">
          选择用户
          <el-select
            v-model="modifier"
            style="text-align: center; width: 150px"
            filterable
            placeholder="请选择"
            @change="onModifierChange"
          >
            <el-option
              v-for="item in modifierOptions"
              :label="item.name"
              :value="item.name"
            >
            </el-option>
          </el-select>
        </div>
      </el-col>
      <!--Issue折线图-->
      <el-col :span="18" :offset="2">
        <div class="grid-content bg-purple-light">
          <el-card class="box-card">
            <div class="Echarts">
              <div
                id="Echart1"
                :style="{ width: '900px', height: '300px' }"
              ></div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col :span="18" :offset="2">
        <div class="grid-content bg-purple-light">
          <el-card class="box-card">
            <div class="Echarts">
              <div id="Echart2" style="width: 900px; height: 400px"></div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modifier: "",
      modifierOptions: [],
      option: {
        title: {
          text: "一周内issue处理情况",
        },
        tooltip: {},
        legend: {
          data: ["收到issue数", "关闭issue数"],
        },
        xAxis: {
          data: [],
        },
        yAxis: {},
        series: [
          {
            name: "收到issue数",
            type: "line",
            data: [],
          },
          {
            name: "关闭issue数",
            type: "line",
            data: [],
          },
        ],
      },
      option2: {
        title: {
          text: "一周内issue各等级数量情况",
        },
        tooltip: {},
        legend: {
          data: ["issue各等级数量"],
        },
        xAxis: {
          data: ["最高", "较高", "一般", "低"],
        },
        yAxis: {},
        series: [
          {
            name: "issue等级",
            type: "bar",
            data: [],
          },
        ],
      },
      today: "",
      dayBefore7: "",
      name: "Echarts",
    };
  },
  mounted() {
    this.searchByUserID();
    this.checkForReceivedOrFinishIssue();
    this.checkAllGradeIssue();
  },
  methods: {
    //显示用户id
    searchByUserID() {
      this.$axios
        .get("http://120.78.176.2:8080/user/selectuserID")
        .then((res) => {
          let op = [];
          for (let i in res.data.list) {
            if (
              res.data.list[i].userstate == "激活" &&
              res.data.list[i].name != this.username
            ) {
              op.push(res.data.list[i]);
            }
            // console.log(op)
          }
          this.modifierOptions = op;
          this.modifier = op[0].name;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //初始化图表
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("Echart1"));
      let myChart2 = this.$echarts.init(document.getElementById("Echart2"));

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(this.option);
      myChart2.setOption(this.option2);
    },
    //获取7天内issue收到、完成情况
    checkForReceivedOrFinishIssue() {
      let DateArr = [];
      let ReceivedArr = [];
      let FinishArr = [];
      let days = [];
      let date1 = new Date();
      let date2 = new Date();
      let time1 =
        date1.getFullYear() +
        "-" +
        (date1.getMonth() + 1) +
        "-" +
        date1.getDate();
      date2.setDate(date1.getDate() - 6);
      let time2 =
        date2.getFullYear() +
        "-" +
        (date2.getMonth() + 1) +
        "-" +
        date2.getDate();
      this.dayBefore7 = time2;
      for (let i = 0; i < 7; i++) {
        let date3 = new Date();
        date3.setDate(date2.getDate() + i);
        let time3 =
          date3.getFullYear() +
          "-" +
          (date3.getMonth() + 1) +
          "-" +
          date3.getDate();
        days.push(time3);
        DateArr.push(date3.getMonth() + 1 + "-" + date3.getDate());
        this.$axios
          .get("http://120.78.176.2:8080/issue/selectLikeIssue", {
            params: {
              issueID: "",
              issuestate: "",
              creater: "",
              createtime: time2,
              createtime1: time3,
              plantime: "",
              plantime1: "",
              name: this.modifier,
              pageNum: 1,
              pageSize: 1,
            },
          })
          .then((res) => {
            ReceivedArr[i] = parseInt(res.data.total);
          })
          .catch(function (error) {
            console.log(error);
          });
        this.option.xAxis.data = DateArr;
        this.option.series[0].data = ReceivedArr;
      }
      for (let j = 0; j < 7; j++) {
        this.$axios
          .get("http://120.78.176.2:8080/issue/selectLikeIssue", {
            params: {
              issueID: "",
              issuestate: "已关闭",
              creater: "",
              createtime: time2,
              createtime1: days[j],
              plantime: "",
              plantime1: "",
              name: this.modifier,
              pageNum: 1,
              pageSize: 1,
            },
          })
          .then((res) => {
            FinishArr[j] = parseInt(res.data.total);
          })
          .catch(function (error) {
            console.log(error);
          });
        this.today = time1;
        this.option.xAxis.data = DateArr;
        this.option.series[0].data = ReceivedArr;
        this.option.series[1].data = FinishArr;
      }
    },
    //获取7天内issue各等级数量
    checkAllGradeIssue() {
      let GradeNum = [];
      let max = 0,
        high = 0,
        normal = 0,
        low = 0;
      this.$axios
        .get("http://120.78.176.2:8080/issue/selectLikeIssue", {
          params: {
            issueID: "",
            issuestate: "",
            creater: "",
            createtime: this.dayBefore7,
            createtime1: this.today,
            plantime: "",
            plantime1: "",
            name: this.modifier,
            pageNum: 1,
            pageSize: 99,
          },
        })
        .then((res) => {
          let response = res.data.list;
          for (let i in response) {
            if (response[i].level == "最高") {
              console.log(max);
              max += 1;
            } else if (response[i].level == "较高") {
              high += 1;
            } else if (response[i].level == "一般") {
              normal += 1;
            } else if (response[i].level == "低") {
              low += 1;
            }
          }
          GradeNum[0] = max;
          GradeNum[1] = high;
          GradeNum[2] = normal;
          GradeNum[3] = low;
          this.option2.series[0].data = GradeNum;
        })
        .catch(function (error) {
          console.log(error);
        });
      setTimeout(() => {
        this.myEcharts();
      }, 1000);
    },
    onModifierChange() {
      console.log(11111);
      this.checkForReceivedOrFinishIssue();
      this.checkAllGradeIssue();
    },
  },
};
</script>

<style>
</style>
