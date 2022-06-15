<template>
  <div class="header">
    <el-row>
      <el-col :span="8" :offset="4">欢迎来到在线二手商品交易网</el-col>
      <el-col :span="5" :offset="6">
        <div class="lore">
          <el-link :underline="false" href="/">首页</el-link>|
          <el-link :underline="false" @click="gotoLogin('login')">登录</el-link>|
          <el-link :underline="false" @click="gotoLogin('register')">注册</el-link>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  methods: {
    gotoLogin(pageItem) {
      this.$router.push({path: "/login",query:{ name:pageItem}});
    }
  }
}
</script>

<style scoped="scoped">
a {
  text-decoration: none;
}
.header {
  height: 30px;
  width: 100%;
  line-height: 30px;
  color: #666;
  font-size: 13px;
  background-color: #f5f5f5;
  border-bottom: 1px solid #eee;
  position: absolute;
  top: 0;
}
.header a {
  transition: color 0.1s;
}
.header a:hover {
  color: #ff6600;
}
.lore {
  color: #999999;
}
.lore .el-link {
  margin: 0 10px;
  color: #555555;
}
</style>
