<template>
  <div class="header">
    <el-row>
      <el-col :span="8" :offset="4">欢迎来到唐山学院二手交易市场</el-col>
      <el-col :span="7" :offset="5">
        <div class="lore">
          <router-link to="/">首页</router-link>|
          <router-link :to="`/login/login`">登录</router-link>|
          <router-link :to="`/login/register`">注册</router-link>|
          <router-link :to="`/login/register`">我的收藏</router-link>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

</script>

<style scoped="scoped">
  a{
    text-decoration: none;
  }
  .header{
    height: 30px;
    line-height: 30px;
    color: #666;
    font-size: 13px;
    background-color: #f5f5f5;
    border-bottom: 1px solid #eee;
  }
  .header a{
    transition: color 0.1s;
  }
  .header a:hover{
    color: #FF6600;
  }
  .lore{
    color: #999999;
  }
  .lore a{
    margin: 0 10px;
    color: #555555;
  }
</style>
