<template>
  <div class="statisContainer">
    <!-- 文本统计区域 -->
    <el-row class="textStatis">
      <el-col :span="6" class="textStatisItem">
        <el-row class="totalTitles">
          <el-col :span="12">商品发布统计</el-col>
          <el-col :span="2" :offset="10">
            <span style="background-color: #1e9fff;">周</span>
          </el-col>
        </el-row>
        <el-row class="totalNum">
          <el-col :span="22">{{DataNum.productSevenNum}}</el-col>
        </el-row>
        <el-row class="totalTotal">
          <el-col :span="12">商品总数量</el-col>
          <el-col :span="4" :offset="6" style="text-align: right;">{{DataNum.productTotalNum}}</el-col>
        </el-row>
      </el-col>
      <el-col :span="6" class="textStatisItem">
        <el-row class="totalTitles">
          <el-col :span="12">新增用户统计</el-col>
          <el-col :span="2" :offset="10">
            <span style="background-color: #2f4056;">月</span>
          </el-col>
        </el-row>
        <el-row class="totalNum">
          <el-col :span="22">{{DataNum.userMoonNum}}</el-col>
        </el-row>
        <el-row class="totalTotal">
          <el-col :span="12">用户总数量</el-col>
          <el-col :span="4" :offset="6" style="text-align: right;">{{DataNum.userTotalNum}}</el-col>
        </el-row>
      </el-col>
      <el-col :span="6" class="textStatisItem">
        <el-row class="totalTitles">
          <el-col :span="12">本周订单统计</el-col>
          <el-col :span="2" :offset="10">
            <span style="background-color: #009688;">周</span>
          </el-col>
        </el-row>
        <el-row class="totalNum">
          <el-col :span="22">{{DataNum.orderSevenNum}}</el-col>
        </el-row>
        <el-row class="totalTotal">
          <el-col :span="12">订单总数量</el-col>
          <el-col :span="4" :offset="6" style="text-align: right;">{{DataNum.orderTotalNum}}</el-col>
        </el-row>
      </el-col>
      <el-col :span="6" class="textStatisItem">
        <el-row class="totalTitles">
          <el-col :span="12">用户评论统计</el-col>
          <el-col :span="2" :offset="10">
            <span style="background-color: #ffb800;">周</span>
          </el-col>
        </el-row>
        <el-row class="totalNum">
          <el-col :span="22">{{DataNum.commentSevenNum}}</el-col>
        </el-row>
        <el-row class="totalTotal">
          <el-col :span="12">评论总数量</el-col>
          <el-col :span="4" :offset="6" style="text-align: right;">{{DataNum.commentTotalNum}}</el-col>
        </el-row>
      </el-col>
      <el-col :span="6" class="textStatisItem">
        <el-row class="totalTitles">
          <el-col :span="12">交易金额统计</el-col>
          <el-col :span="2" :offset="10">
            <span style="background-color: #c23531;">月</span>
          </el-col>
        </el-row>
        <el-row class="totalNum">
          <el-col :span="22">{{DataNum.moneyMoonNum}}</el-col>
        </el-row>
        <el-row class="totalTotal">
          <el-col :span="12">交易总金额</el-col>
          <el-col :span="10" style="text-align: right;">{{DataNum.moneyTotalNum}}</el-col>
        </el-row>
      </el-col>
    </el-row>
    <!-- 本周交易数量统计，交易金额统计 -->
    <el-row class="weekTotal">
      <el-col :span="12">
        <div id="weekOrder" :style="{width: '100%', height: '310px',marginTop:'10px'}"></div>
      </el-col>
      <el-col :span="12" style="margin-left:10px;">
        <div id="weekMoney" :style="{width: '100%', height: '310px',marginTop:'10px'}"></div>
      </el-col>
    </el-row>
    <!-- 访问量，点击量 -->
    <el-row class="yearClick">
      <el-col :span="24">
        <div id="yearClick" :style="{width: '100%', height: '390px',marginTop:'10px'}"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        firstClassify:[],//一级分类
        DataNum:{
          productSevenNum:0,//七日商品
          productTotalNum:0,//商品总量
          userMoonNum:0,//本月用户
          userTotalNum:0,//用户总量
          orderSevenNum:0,//七日订单
          orderTotalNum:0,//订单总量
          commentSevenNum:0,//七日评论
          commentTotalNum:0,//评论总量
          moneyMoonNum:0,//本月交易
          moneyTotalNum:0//交易总量
        },
        productsNum:[],
        productsMoney:[],
        clickNum: [],
        visitNum: []

      }
    },
    mounted() {
      this.loadDataNum();
      setTimeout(res => {
        this.weekOrder();
        this.weekMoney();
        this.yearClick();
      },500);

    },
    methods: {
      loadDataNum(){
        //一级分类列表查询
        this.$axios.get('/classify/flnb',{}).then(res =>{
          this.firstClassify = [];
          res.data.forEach(resp =>{
            this.firstClassify.push(resp.name);
          });
        });
        //查询商品，用户，订单，评论，交易额
        this.$axios.get('/utils/selectDataNum',{}).then(res =>{
          this.DataNum.productSevenNum = res.data.productSevenNum;
          this.DataNum.productTotalNum = res.data.productTotalNum;

          this.DataNum.userMoonNum = res.data.userMoonNum;
          this.DataNum.userTotalNum = res.data.userTotalNum;

          this.DataNum.orderSevenNum = res.data.orderSevenNum;
          this.DataNum.orderTotalNum = res.data.orderTotalNum;

          this.DataNum.commentSevenNum = res.data.commentSevenNum;
          this.DataNum.commentTotalNum = res.data.commentTotalNum;

          this.DataNum.moneyMoonNum = res.data.moneyMoonNum;
          this.DataNum.moneyTotalNum = res.data.moneyTotalNum;
        });

        //各个种类发布商品数量统计
        this.$axios.get('/utils/selectProductNumByFcid',{}).then(res =>{
          this.productsNum = res.data;
          
        });
        //各个种类商品交易额统计
        this.$axios.get('/utils/selectProductMoneyByFcid',{}).then(res =>{
          this.productsMoney = res.data;
        });

        //当年每月点击量
        this.$axios.get('/utils/selectClickNum',{params:{year:2020}}).then(res => {
          this.clickNum = res.data;
        });

        //当年每月访问量
        this.$axios.get('/utils/selectVisitNum',{params:{year:2020}}).then(res => {
          this.visitNum = res.data;
        });
      },
      //一周商品交易统计
      weekOrder() {
        // 基于准备好的dom，初始化echarts实例
        let weekOrder = this.$echarts.init(document.getElementById('weekOrder'));
        // 绘制图表
        weekOrder.setOption({
          title: {
            text: '商品交易额统计'
          },
          tooltip: {},
          xAxis: {
            data: this.firstClassify
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: this.productsMoney
          }]
        });
      },
      //商品数量统计趋势
      weekMoney() {
        // 基于准备好的dom，初始化echarts实例
        let weekMoney = this.$echarts.init(document.getElementById('weekMoney'));
        // 绘制图表
        weekMoney.setOption({
          title: {
            text: '发布商品统计'
          },
          tooltip: {},
          xAxis: {
              type: 'category',
              data: this.firstClassify
          },
          yAxis: {
              type: 'value'
          },
          series: [{
              data: this.productsNum,
              type: 'bar',
              name: '数量',
              showBackground: true,
              backgroundStyle: {
                  color: 'rgba(220, 220, 220, 0.8)'
              }
          }]
        });
      },
      //一年访问量与点击量
      yearClick(){
        let yearClick = this.$echarts.init(document.getElementById('yearClick'));
        yearClick.setOption({
          title: {
                text: '网站访问量与点击量趋势'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['访问量', '点击量']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ["一月份", "二月份", "三月份", "四月份", "五月份", "六月份", "七月份", "八月份", "九月份", "十月份", "十一月份", "十二月份"]
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '点击量',
                    type: 'line',
                    data: this.clickNum
                },
                {
                    name: '访问量',
                    type: 'line',
                    data: this.visitNum
                }
            ]
        });
      }
    }
  }
</script>

<style>
  .statisContainer{
    padding: 0 20px;
    background-color: #F1F1F1;
  }
  .textStatis{
    display: flex;
    height: 150px;
  }
  .textStatis .textStatisItem{
    margin: 5px;
    background-color: #FFFFFF;
  }
  .totalTitles{
    height: 42px;
    line-height: 42px;
    padding: 0 15px;
    border-bottom: 1px solid #f6f6f6;
    color: #333;
    border-radius: 2px 2px 0 0;
    font-size: 14px;
  }
  .totalTitles span{
    color: #FFFFFF;
    padding: 2px 4px;
    border-radius: 4px;
  }
  .totalNum{
    font-size: 36px;
    color: #666;
    line-height: 36px;
    padding: 14px 10px;
    padding-left: 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: break-all;
    white-space: nowrap;
  }
  .totalTotal{
    font-size: 14px;
    padding-left: 10px;
    color: #666;
  }

  .weekTotal,.yearClick{
    margin: 10px 4px;
    display: flex;
  }
  .yearClick,.weekTotal .el-col{
    padding-left: 10px;
    background-color: #FFFFFF;
  }
  .yearClick,.weekTotal .el-col,.textStatisItem{
    border-radius: 10px;
  }

</style>
