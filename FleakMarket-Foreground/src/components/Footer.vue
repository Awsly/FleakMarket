<template>
  <div class="Footer">
     <el-row class="row-info">
         <el-col :span="4" :offset="4" style="border: 0;"><span>联系我们</span></el-col>
         <el-col :span="4"><span>关于网站</span></el-col>
         <el-col :span="4"><span>隐私声明</span></el-col>
         <el-col :span="4"><span>意见反馈</span></el-col>
     </el-row>
      <el-row>
        <el-col style="border-bottom: 1px solid #E7E7E7;" :span="16" :offset="4"></el-col>
      </el-row>
     <el-row>
        <el-col :span="4" :offset="4" class="qrcode">
          <div><img src="../../static/img/icon/QQ.jpg" alt="QQ号:2459550727"></div>
          <div>关注QQ</div>
        </el-col>
        <el-col :span="4" class="qrcode">
          <div><img src="../../static/img/icon/wx.jpg" alt="微信号:2459550727"></div>
          <div>关注微信</div>
        </el-col>
        <el-col :span="4">
          <div class="Friendshiplink">
            <ul>
              <li>
                <a href="https://www.suning.com/" target="_blank"><img src="../../static/img/logo/sn_logo.png" alt="苏宁易购" /></a>
                <a href="https://www.jd.com/" target="_blank"><img src="../../static/img/logo/jd_logo.png" alt="京东商城" /></a>
              </li>
              <li>
                <a href="https://www.mi.com/" target="_blank"><img src="../../static/img/logo/mi_logo.png" alt="小米商城" /></a>
                <a href="https://www.taobao.com/" target="_blank"><img src="../../static/img/logo/tb_logo.png" alt="淘宝商城" /></a>
              </li>
            </ul>
            <div>友情链接</div>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="phone">
            <p>187-138-27231</p>
            <span>周一至周日 9:00-18:00</span>
          </div>
        </el-col>
     </el-row>

     <el-row class="copyright">
       <el-col>Copyright © 2459550727@qq.com&nbsp;陈拓</el-col>
     </el-row>
  </div>
</template>

<script>
</script>

<style scoped="scoped">

  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  .Footer{
    width: 100%;
    padding: 0;
    bottom: 0;
    position: relative;
    background-color: #FCFCFC;
    border-top: 1px solid #E5E9EF;
  }
  .row-info{
    padding: 30px 0;
    color: #616161;
    text-align-last: center;
  }
  .row-info div{
    border-left: 1px solid #E0E0E0;
  }
  .row-info span{
    transition: color 0.1s;
  }
  .row-info span:hover{
    color: #FF8800;
    cursor: pointer;
  }
  .qrcode{
    text-align-last: center;
    padding: 40px 0;
    padding-bottom: 15px;
    color: #555555;
  }
  .qrcode img{
    height: 150px;
  }
  .phone{
    margin-top: 50px;
    color: #FF6700;
    height: 120px;
    text-align-last: center;
    padding-top: 20px;
    border-left: 1px solid #E0E0E0;
  }
  .phone p{
    font-size: 22px;
    line-height: 5px;
  }
  .phone span{
    font-size: 12px;
    color: #666666;
    line-height: 0;
  }

  .Friendshiplink{
    text-align-last: center;
    color: #666666;
  }
  .Friendshiplink ul{
    margin-top: 40px;
    line-height: 70px;
    height: 150px;
  }
  .Friendshiplink li a{
    color: #666666;
    line-height: 40px;
    width: 40px;
    text-decoration: none;
    transition: color 0.1s;
  }
  .Friendshiplink li a img{
    width: 23%;
    margin: 13px 20px;
  }

  .copyright{
    padding: 7px 0;
    color: #666666;
    font-size: 12px;
    text-align-last: center;
    background-color: #FCFCFC;
  }
</style>
