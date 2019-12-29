<template>
  <div class="container">
    <HeaderTop></HeaderTop>
    <div class="view">
      <el-tabs v-model="activeName" :stretch="true">
        <el-tab-pane label="登录" name="login" background-color="red">
          <el-form class="Lform">

            <el-form-item>
              <p><img src="../../static/img/icon/username.png" alt="用户名"></p>
              <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item>
              <p><img src="../../static/img/icon/password.png" alt="密码"></p>
              <el-input v-model="ruleForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
            </el-form-item>

          </el-form>
        </el-tab-pane>

        <el-tab-pane label="注册" name="register">
          <el-form class="Rform">

            <el-form-item>
              <p><img src="../../static/img/icon/username.png" alt="用户名"></p>
              <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <el-form-item>
              <p><img src="../../static/img/icon/password.png" alt="密码"></p>
              <el-input v-model="ruleForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item class="verify">
              <el-input v-model="ruleForm.verifycode" placeholder="请输入验证码"></el-input>
              <el-button type="primary" round>获取验证码</el-button>
            </el-form-item>

            <el-form-item class="relo">
              <el-button type="primary" @click="submitForm('ruleForm')">注册并登录</el-button>
            </el-form-item>

          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import HeaderTop from "../components/Header.vue";

  export default {
    components: {
      HeaderTop
    },
    mounted(){
      console.log(this.$route.params);

    },
    data() {
      return {
        ruleForm: {
          username:'',
          password:''
        },
        activeName: this.$route.params.name
      }
  },
  methods: {

  }
  }
</script>

<style scoped="scoped">
  .container {
    width: 100%;
    height: 100%;
    background-image: url(../../static/img/background/background.png);
    background-size:100% 100%;
    background-repeat:no-repeat;
  }

  .title {
    font-size: 35px;
    width: 100%;
    text-align-last: center;
    height: 40px;
    margin-bottom: 50px;
  }

  .view {
    position: absolute;
    height: 340px;
    width: 500px;
    padding: 20px;
    top: calc(50% - 170px);
    right: calc(50% - 250px);
    box-shadow: 1px 1px 10px #555555;
    background-color: rgba(255, 255, 255, 0.8);

  }
  .view .el-form{
    padding-top: 20px;
  }
  .view .el-form-item {
    width: 90%;
    padding-top: 8px;
    padding-left: 5%;
    margin-bottom: 0;
  }



  /* 登录注册公共样式 */
  .el-form-item p{
    width: 10%;
    height: 100%;
    float: left;
  }
  .el-form-item p img{
    width: 70%;
  }
  .el-form-item .el-input{
    width: 80%;
    height: 40px;
  }


  /* Lform登录表单样式  */
  .Lform{
    margin-top: 10px;
  }
  .Lform .el-form-item p{
    margin-top: 4px;
  }
  >>> .Lform .el-input__inner{
      height: 45px;
   }
  .Lform .el-button{
    width: 72%;
    margin-top: 30px;
    height: 45px;
    font-size: 17px;
    margin-left: 14%;
  }


  /* Rform注册表单样式  */
  .Rform .el-form-item p{
    margin-top: 3px;
  }
  .Rform .verify{
    width: 100%;
    float: left;
    margin-left: 9%;
  }
  >>>.Rform .verify .el-input{
    width: 30%;
  }
  >>>.Rform .el-form-item_content{
    margin-bottom: 0;
  }
  .Rform .verify .el-button{
    width: 30%;
    margin-left: 10%;
  }
  .Rform .relo .el-button{
    width: 70%;
    margin-top: 30px;
    margin-left: 14%;
  }

</style>
