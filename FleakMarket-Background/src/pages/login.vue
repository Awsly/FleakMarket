<template>
  <div class="Logincontainer">
    <div class="loginContainer">
      <p>唐院二手交易市场后台管理系统</p>
      <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-ruleForm">
        <el-form-item class="login" label="用户名" prop="username">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item class="login" label="密码" prop="password">
          <el-input v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item class="login">
          <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import moment from "moment";
  export default {
    data() {
      return {
        loginForm: {
          username: '',
          password: '',
        },
        rules: {
          username: [{
              required: true,
              message: '请输入用户名',
              trigger: 'blur'
            },
            {
              min: 1,
              max: 8,
              message: '长度在 3 到 8 个字符',
              trigger: 'blur'
            }
          ],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'change'
          }]
        }
      }
    },
    created() {
      if(this.$route.params.id == 1){
        this.$store.commit('userInfo','');
        history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function(e) {
            history.pushState(null, null, document.URL);
        });
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post('/Badmin/selectAdminByUser',{
              username: this.loginForm.username,
              password: this.loginForm.password
            }).then(res => {
              if(res.data.length != 0){
                //存储用户信息
                this.$store.commit('userInfo',res.data);
                this.$message({
                  type: 'success',
                  message: '登录成功！'
                });

                //修改管理员身份信息
                this.$axios.post('/Badmin/updateAdminById',{
                  id:this.$store.state.userInfo.id,
                  logintime: moment().format('YYYY-MM-DD HH:mm:ss'),
                  status:1
                });

                this.$router.push('/index');
              }else{
                this.$message({
                  type: 'warning',
                  message: '用户名或密码出错，请重新输入！'
                });
              }
            })
          } else {
            this.$message({
              type: 'warning',
              message: '请完善登录信息！'
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }

  }
</script>

<style>
  .Logincontainer{
    display: flex;
    height: 100%;
    background-size: 100%;
    background-image: url(../../static/images/15.jpg);
  }
  .loginContainer{
    padding-top: 20px;
    width: 500px;
    height: 300px;
    margin: auto;
    border-radius: 5px;
    background-color: rgba(255,255,255,0.7);
  }
  .loginContainer p{
    font-size: 20px;
    font-weight: 800;
    margin: 20px;
    margin-bottom: 40px;
    text-align-last: center;
  }
  .login{
    padding-right: 80px;
    padding-left: 20px;
    text-align: center;
  }
  .login .el-button{
    margin-right: 30px;
    width: 100px;
    margin-top: 20px;
  }
</style>
