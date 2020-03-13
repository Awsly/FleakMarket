<template>
  <div class="header">
    <el-row>
      <el-col :span="8" :offset="4">欢迎来到唐山学院二手交易市场</el-col>
      <el-col :span="7" :offset="5">
        <div class="lore">
          <el-link :underline="false" href="/">首页</el-link>|
          <el-link :underline="false" @click="gotoLogin('login','login')">登录</el-link>|
          <el-link :underline="false" @click="gotoLogin('login','register')">注册</el-link>|
          <el-link :underline="false" href="/">我的收藏</el-link>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  methods: {
    gotoLogin (page, pageItem) {
      if (page != null) {
        localStorage.setItem("tabControl", pageItem);
        this.$router.push({ name: page });
      } else {
        this.$router.push({ name: '/' });
      }
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
  line-height: 30px;
  color: #666;
  font-size: 13px;
  background-color: #f5f5f5;
  border-bottom: 1px solid #eee;
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
