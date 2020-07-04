<template>
  <div class="container">
    <HeaderTop></HeaderTop>
    <div class="view">
      <el-tabs v-model="activeName" :stretch="true" tab-position="top">
        <el-tab-pane label="登录" name="login" background-color="red">
          <el-form class="Lform" :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" >
            <el-form-item label="用户名" prop="username">
              <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="toLogin('loginForm')">立即登录</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="注册" name="register">
          <el-form class="Rform" :model="registerForm" :rules="rules" ref="registerForm" label-width="100px" >
            <el-form-item label="用户名" prop="username">
              <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="registerForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="registerForm.sex">
                <el-radio label="男"></el-radio>
                <el-radio label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker type="date" format="yyyy 年 MM 月 dd 日"  value-format="yyyy-MM-dd" placeholder="请选择出生日期" v-model="registerForm.birthday"></el-date-picker>
            </el-form-item>
            <el-form-item label="联系方式" prop="phonenumber">
              <el-input v-model="registerForm.phonenumber" placeholder="请输入联系方式"></el-input>
            </el-form-item>
            <el-form-item label="现居地" prop="address">
              <el-input v-model="registerForm.address" placeholder="请输入现居地址"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="mail">
              <el-input v-model="registerForm.mail" placeholder="请输入邮箱"></el-input>
            </el-form-item>

            <el-form-item class="verify" label="验证码">
              <el-input v-model="registerForm.verifyCode" placeholder="请输入验证码"></el-input>
              <el-button type="primary" @click="sendMessage('registerForm')" :disabled="disabledBoolean" round>{{sendText}}</el-button>
            </el-form-item>

            <el-form-item class="relo">
              <el-button type="primary" @click="submitForm('registerForm')">注册并登录</el-button>
            </el-form-item>

          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import HeaderTop from "../components/Header.vue";
  import moment from "moment";

  export default {
    components: {
      HeaderTop
    },
    data() {
      let validatePhone = (rule, value, callback) => {
        if(value === ''){
           callback(new Error('请输入联系方式'));
        }else if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(value))){
          callback(new Error('手机号码格式出错，请重新输入！'));
        }else{
          callback();
        }
      };

      let validateMail = (rule, value, callback) => {
        if(value === ''){
          callback(new Error('请输入邮箱地址'));
        }else if(!(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/).test(value)){
          callback(new Error('邮箱格式出错，请重新输入！'))
        }else {
          callback();
        }
      };

      return {
        loginForm: {
          username: '',
          password: ''
        },
        registerForm: {},
        activeName: this.$route.query.name,
        verifyCode: '',
        disabledBoolean: false,
        sendText: '获取验证码',
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
          },{
              min: 1,
              max: 8,
              message: '长度在 3 到 8 个字符',
              trigger: 'blur'
          }],
          sex: [{
            required: true,
            message: '请选择性别',
            trigger: 'change'
          }],
          birthday: [{
            required: true,
            message: '请选择生日',
            trigger: 'change'
          }],
          phonenumber: [{
            required: true,
            trigger: 'blur',
            validator: validatePhone
          }],
          mail: [{
            required: true,
            trigger: 'blur',
            validator: validateMail
          }],
          address: [{
            required: true,
            message: '请填写现居地址',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      closeTab(){
        loading.close();
      },
      //登录方法
      toLogin(loginForm) {
        this.$refs[loginForm].validate((valid) => {
        if (valid) {
          const loading = this.$loading({
            lock: true,
            text: '正在登录...',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          this.$axios.get('/user/toLogin', {
            params: {
              username: this.loginForm.username,
              password: this.loginForm.password
            }
          }).then(res => {
            loading.close();
            if(res.data != ''){
              this.clickNum();
              this.$store.commit('userInfo',res.data.userid)
              this.$router.push('/');
            }else{
              this.$message.error('用户名或密码错误！');
            }
          })
        }
      });
    },
    //注册方法
    submitForm(registerForm){
      this.$refs[registerForm].validate((valid) => {
        if (valid) {
          if(this.verifyCode == this.registerForm.verifyCode){
            //查找数据库是否有当前用户名
            this.$axios.get('/user/toLogin',{
              params: {
                username: this.registerForm.username
              }
            }).then(res => {
              if(res.data != ''){
                this.$message.error('用户名已注册！');
              }else {
                //若数据库没有，则向数据库添加该用户信息
                this.$axios.post('/user/insertUserInfo',{
                  username: this.registerForm.username,
                  phonenumber: this.registerForm.phonenumber,
                  mail: this.registerForm.mail,
                  birthday: this.registerForm.birthday,
                  address: this.registerForm.address,
                  sex: this.registerForm.sex,
                }).then(res => {
                  //向中账户表中添加用户信息
                  this.$axios.post('/user/insertUserToAccount',{
                    userid: res.data,
                    username:this.registerForm.username,
                    password:this.registerForm.password
                  }).then(res =>{
                    if(res.data == 1){
                      //注册成功，跳转首页
                      this.$message({
                        type: 'warning',
                        message: '注册成功，请登录！'
                      });
                      //替换地址
                      location.replace("http://localhost:7777/#/login?name=login");
                      //刷新页面
                      location.reload([true]);
                    }
                  })
                });
              }
            });
          }else{
            this.$message({
              type: 'warning',
              message: '验证码错误，请重新输入！'
            });
          }
        }
      });
    },
    //发送验证码方法
    sendMessage(registerForm){
      this.$refs[registerForm].validate((valid) => {
        if(valid){
          if(this.disabledBoolean){
              return;
          }else{
            this.verifyCode = '';
            this.$message({
              type: 'warning',
              message: '验证码已发送，请稍等！'
            });
          }
          //发送提示
          this.getVerifyCode();
          this.wite(60);
        }
      });
    },
    //获取验证码方法
    getVerifyCode(){
      //开始发送邮件
      this.$axios.get('/email/sendMail',{
        params: {
          mailTo: this.registerForm.mail
        }
      }).then(res => {
        this.verifyCode = res.data;
      });
    },
    //改变发送邮件状态
    wite(wait){
      //禁用发送按钮，并显示倒计时
      if(wait == 0) {
          this.disabledBoolean=false;
          this.sendText="获取验证码"
      } else {
          this.disabledBoolean=true;
          this.sendText="验证码(" + wait + "s)"
          wait--;
          setTimeout(res => {
              this.wite(wait);
          },1000);
      }
    },
    //统计点击量
    clickNum(){
      //判断数据库中是否存在当前日期
      this.$axios.post('/utils/selectDateFromStatis',{
        dates: moment().format('YYYY-MM-DD')
      }).then(res => {
        if(res.data.length == 0){
          //如果数据库不存在今天的数据，插入今天日期与默认点击量
          this.$axios.post('/utils/insertDateInStatis',{
            dates: moment().format('YYYY-MM-DD'),
          });
        }else{
          //如果数据库存在今天的数据，获取当日点击量+1
          this.$axios.post('/utils/updateNumInStatis',{
            id: res.data.id,
            visitNum: res.data.visitNum+1
          });
        }
      });
    }
  }
}
</script>

<style scoped="scoped">
  .container {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: url(../../static/img/background/background.png);
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }

  .view {
    min-height: 340px;
    width: 500px;
    padding: 20px;
    box-shadow: 1px 1px 10px #555555;
    background-color: rgba(255, 255, 255, 0.8);
  }

  .view .el-form {
    padding-top: 20px;
  }

  .view .el-form-item {
    width: 90%;
    padding-top: 8px;
    padding-left: 5%;
  }

  /* 登录注册公共样式 */

  .el-form-item .el-input {
    width: 85%;
  }

  /* Lform登录表单样式  */
  .Lform {
    margin-top: 10px;
  }

  .Lform .el-button {
    width: 70%;
    height: 45px;
    font-size: 15px;
  }

  /* Rform注册表单样式  */
  .Rform .verify {
    width: 100%;
  }

  .Rform .verify .el-input {
    width: 30%;
  }

  .Rform .verify .el-button {
    width: 30%;
    margin-left: 10%;
  }

  .Rform .relo .el-button {
    width: 70%;
    height: 50px;
  }
</style>
