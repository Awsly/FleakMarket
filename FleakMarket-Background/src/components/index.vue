<template>
  <div class="containerIndex">
    <div class="welcome">
      <p>
        <span>欢迎: {{admin}} 登录</span>
        <span>当前时间: {{time}}</span>
      </p>
    </div>
    <div class="Statistics">
      <p>统计</p>
      <ul>
        <li>
          <div>商品总量</div>
          <p>{{DataNum.productTotalNum}}</p>
        </li>
        <li>
          <div>用户总数</div>
          <p>{{DataNum.userTotalNum}}</p>
        </li>
        <li>
          <div>评论数</div>
          <p>{{DataNum.commentTotalNum}}</p>
        </li>
        <li>
          <div>交易额</div>
          <p>{{DataNum.moneyTotalNum}}</p>
        </li>
      </ul>
    </div>
    <div class="Statistics">
      <p>系统信息</p>
      <div class="tabLine">
        <table class="tabcont">
          <tr>
            <td>系统版本</td>
            <td>Microsoft Windows 10 Home</td>
          </tr>
          <tr>
            <td>服务地址</td>
            <td>192.168.22.254:9999</td>
          </tr>
          <tr>
            <td>MySQL 版本</td>
            <td>MySQL v5.5</td>
          </tr>
          <tr>
            <td>运行环境</td>
            <td>Node v12.14.1,Apache Tomcat 9.0,Vue v2.9.6</td>
          </tr>
          <tr>
            <td>Java JDK</td>
            <td>JDK 1.8</td>
          </tr>
          <tr>
            <td>后端框架</td>
            <td>SpringBoot,Mybatis</td>
          </tr>
          <tr>
            <td>前端框架</td>
            <td>Vue,ElementUI</td>
          </tr>
          <tr>
            <td>文件上传</td>
            <td>FastDFS</td>
          </tr>
        </table>
      </div>
    </div>
    <div class="Statistics">
      <p>开发者信息</p>
      <div class="tabLine">
        <table class="tabcont">
          <tr>
            <td>开发者</td>
            <td>陈拓</td>
          </tr>
          <tr>
            <td>Email</td>
            <td>2459550727@qq.com</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
  export default{
    data () {
      return {
        admin: this.$store.state.userInfo.username,
        time: new Date(),
        DataNum:{
          productTotalNum:0,//商品总量
          userTotalNum:0,//用户总量
          orderTotalNum:0,//订单总量
          commentTotalNum:0,//评论总量
          moneyTotalNum:0//交易总量
        },
      }
    },created() {
      //查询商品，用户，订单，评论，交易额
      this.$axios.get('/utils/selectDataNum',{}).then(res =>{
        this.DataNum.productTotalNum = res.data.productTotalNum;

        this.DataNum.userTotalNum = res.data.userTotalNum;

        this.DataNum.orderTotalNum = res.data.orderTotalNum;

        this.DataNum.commentTotalNum = res.data.commentTotalNum;

        this.DataNum.moneyTotalNum = res.data.moneyTotalNum;
      });
    },
    methods:{

    }
  }
</script>

<style>
  .containerIndex{
    height: 100%;
    padding: 10px;
    /* background-color: #FFFFFF; */
  }
  .welcome{
    padding: 5px 20px;
    line-height: 40px;
    font-size: 12px;
    background-color: #FFFFFF;
  }
  .welcome p{
    background-color: #EEEEEE;
    padding-left: 10px;
    padding: 10px 15px;
    border-left: 4px solid #409EFF;
  }
  .welcome span{
    margin-right: 40px;
  }
  .Statistics{
    margin-top: 10px;
    padding-bottom: 10px;
    background-color: #FFFFFF;
  }
  .Statistics p{
    margin: 5px 0;
    padding: 10px;
    color: #333333;
    font-size: 14px;
  }
  .Statistics ul{
    display: flex;
    padding: 5px 10px;
    border-top: 1px solid #F6F6F6;
  }
  .Statistics ul li{
    flex: 1;
    margin: 5px;
    color: #999999;
    font-size: 13px;
    cursor: pointer;
    padding-top: 20px;
    border-radius: 10px;
    background-color: #F8F8F8;
    text-align-last: center;
    transition:all 0.3s;
  }
  .Statistics ul li p{
    font-size: 25px;
    color: #409eff;
  }
  .Statistics ul li:hover{
    background-color: #cfcfcf;
  }
  .tabLine{
    padding-top: 20px;
    padding-bottom: 10px;
    border-top: 1px solid #F6F6F6;
  }
  .tabcont{
    width: 98%;
    color: #666;
    margin: auto;
    font-size: 12px;
    border-spacing: 1;
    border-collapse: collapse;
    border-left: 1px solid #E6E6E6;
    border-top: 1px solid #E6E6E6;
  }
  .tabcont td{
    line-height: 40px;
    padding-left: 15px;
    border-right: 1px solid #E6E6E6;
    border-bottom: 1px solid #E6E6E6;
  }
  .tabcont td:first-child{
    width: 400px;
  }
  .tabcont tr{
    cursor: pointer;
    transition:all 0.3s;
  }
  .tabcont tr:hover{
    background-color: #F2F2F2;
  }
</style>
