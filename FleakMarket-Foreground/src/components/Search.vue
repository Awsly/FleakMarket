<template>
  <div class="Search">
    <el-row>
      <el-col :span="4" :offset="4">
        <img src="../../static/img/logo.png" alt="">
      </el-col>
      <el-col :span="9">
        <div class="inpbtn">
          <input type="text">
          <button>搜索</button>
        </div>
      </el-col>
      <el-col :span="6">
        <span class="userImg">
          <img src="../../static/img/headerImg.jpg" alt="头像">
        </span>
        <span class="userName">请登录</span>
        <span class="shopCart"><i class="el-icon-shopping-cart-full"></i>&nbsp;购物车</span>
      </el-col>
    </el-row>
  </div>
</template>

<script>
</script>

<style scoped="scoped">
  .Search{
    height: 100px;
    background-color: #FFFFFF;
    box-shadow: 0 1px 2px #E0E0E0;
    margin-bottom: 20px;
  }
  .inpbtn{
    height: 20px;
    margin-top: 30px;
    display: flex;
  }
  .inpbtn input{
    width: 55%;
    height: 40px;
    padding: 0 10px;
    border: 2px solid #FA0;
  }
  .inpbtn button{
    width: 15%;
    height: 44px;
    color: #FFFFFF;
    font-size: 16px;
    font-weight: 800;
    background-color: #FA0;
  }
  .userImg{
    display: block;
    float: left;
    margin-top: 22px;
  }
  .userImg img{
    height: 48px;
    width: 48px;
    border-radius: 50%;
  }
  .userName{
    line-height: 100px;
    margin-left: 10px;
    color: #444;
  }
  .shopCart{
    line-height: 100px;
    color: #444;
    margin-left: 20px;
  }
</style>
