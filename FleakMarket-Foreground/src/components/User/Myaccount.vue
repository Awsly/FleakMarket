<template>
  <div>
    <table class="mineInfo">
      <tr>
        <td rowspan="2">
          <img :src="imgPath" style="height: 85px;width: 85px;margin-bottom: 5px;">
          <div>{{userData.username}}</div>
        </td>
        <td>我的年龄</td>
        <td>我的商品</td>
        <td>我的购物</td>
        <td>我的评价</td>
      </tr>
      <tr>
        <td>{{age}}</td>
        <td>{{productNum}}</td>
        <td>{{cartNum}}</td>
        <td>{{commentNum}}</td>
      </tr>
    </table>
    <!-- 用户模块表单 -->
    <el-form ref="userData" class="userInfo" :model="userData" :rules="rules" label-width="120px">
      <el-row>
        <el-col :span="11">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userData.username" placeholder="请填写用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="联系方式" prop="phonenumber">
            <el-input v-model="userData.phonenumber" placeholder="请填写联系方式"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="邮箱" prop="mail">
            <el-input v-model="userData.mail" placeholder="请填写邮箱地址"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker type="date" placeholder="请选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd" v-model="userData.birthday" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="userData.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20">
          <el-form-item label="用户头像">
              <el-upload
                action=""
                :limit="1"
                :multiple="true"
                list-type="picture-card"
                :auto-upload="false"
                :http-request="uploadFile"
                ref="upload"
                :on-remove="handleRemove">
                <i class="el-icon-plus"></i>
              </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22">
          <el-form-item label="现居地" prop="address">
            <el-input v-model="userData.address" placeholder="请填写现居地址"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16" :offset="4">
          <el-button type="primary" @click="submitForm('userData')">提交</el-button>
          <el-button @click="loadData()" type="info">重置</el-button>
        </el-col>
        <el-col :span="3">
          <el-button type="text" @click="updatePassword = true">修改密码</el-button>
        </el-col>
      </el-row>
    </el-form>

    <el-dialog
      title="提示"
      :visible.sync="updatePassword"
      width="30%"
      :before-close="closeDialog"
      >

      <el-form ref="userAccount" :model="userAccount" :rules="rules" label-width="80px">
        <el-form-item label="原密码" prop="oldpassword">
          <el-input v-model="userAccount.oldpassword" placeholder="请输入原账户密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newpassword">
          <el-input type="password" v-model="userAccount.newpassword" placeholder="请输入新密码"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="restpassword">
          <el-input type="password"  v-model="userAccount.restpassword" placeholder="请再次输入新密码"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateAccount('userAccount')">确 定</el-button>
        <el-button @click="resetForm('userAccount')">重置</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import moment from "moment";
  export default{
    data(){
      let validateuserPassword = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('请输入原密码'))
        } else if (value !== this.userPassword) {
          return callback(new Error('密码错误'))
        } else {
          callback();
        }
      };

      let validaterestPassword = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('请再次输入新密码'));
        }else if(value !== this.userAccount.newpassword){
          return callback(new Error('两次密码不一致'));
        }else {
          callback();
        }
      };

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
        //我的账户
        userData: {},//用户数据
        userAccount: {},//用户账户
        userPassword: '',//用户密码
        imgPath: '../../static/img/akari.jpg',//用户默认头像
        age: '',//用户年龄
        formDate:"",//图片表单
        productNum: 0,//发布商品数量
        cartNum: 0,//购物车数量
        commentNum: 0,//发布评论数量
        updatePassword: false,//修改密码弹出层
        rules: {
          username: [
            { required: true, message: '请填写用户名', trigger: 'blur' },
            { min: 0, max: 15, message: '长度在 0 到 15 个字符', trigger: 'blur' }
          ],
          phonenumber: [
            { required: true, trigger: 'blur', validator: validatePhone }
          ],
          mail: [
            { required: true, trigger: 'blur', validator: validateMail },
          ],
          birthday: [
            { required: true, message: '请选择出生日期', trigger: 'blur' },
          ],
          sex: [
            { required: true, message: '请选择选择性别', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '请填写现居地址', trigger: 'blur' }
          ],
          oldpassword: [
            { required: true, trigger: 'blur', validator: validateuserPassword}
          ],
          newpassword: [
            { required: true, message: '请输入新密码', trigger: 'blur' }
          ],
          restpassword: [
            { required: true, trigger: 'blur', validator: validaterestPassword }
          ]
        }
      }
    },
    created() {
      //请求用户数据
      this.loadData();
      //点击量+1
      this.clickNum();
    },
    methods:{
      /**
       * 加载用户个人信息
       */
      loadData() {
        //查询用户信息接口
        this.$axios.get('/user/selectUserById', {
          params: {
            id: this.$store.state.userid
          }
        }).then(res => {
          this.userData = res.data;
          let birthday = this.userData.birthday;
          //计算年龄
          let birthdays = new Date(birthday.replace(/-/g, "/"));
          let d = new Date();
          this.age =
            d.getFullYear() -
            birthdays.getFullYear() -
            (d.getMonth() < birthdays.getMonth() ||
              (d.getMonth() == birthdays.getMonth() &&
                d.getDate() < birthdays.getDate()) ?
              1 :
              0);
          if (res.data.userimgpath != null) {
            this.imgPath = res.data.userimgpath;
          }
        });
        //查询用户发布商品数量接口
        this.$axios.get('/user/selectCountById', {
          params: {
            id: this.$store.state.userid
          }
        }).then(res => {
          this.productNum = res.data;
        });
        //查询订单支付商品数量
        this.$axios.get('/user/selectCartCountById', {
          params: {
            id: this.$store.state.userid
          }
        }).then(res => {
          this.cartNum = res.data;
        });
        //查询我的评论数量
        this.$axios.get('/user/selectCommentById', {
          params: {
            id: this.$store.state.userid
          }
        }).then(res => {
          this.commentNum = res.data;
        });

        //查询用户密码
        this.$axios.get('/user/selectAccountById',{
          params: {
            id:  this.$store.state.userid
          }
        }).then(res => {
          this.userPassword = res.data.password;
        });

      },
      submitForm(userData){
        console.log(1234);
        this.$refs[userData].validate((valid) => {
          console.log(valid);
          if(valid) {
            //提交图片，得到图片地址
            this.subPicForm();
            setTimeout(res => {
              this.updateUser();
            },500);
          }
        });
      },
      //上传图片方法
      subPicForm(){
        this.formDate = new FormData();
        this.$refs.upload.submit();
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        this.$axios.post("/fileUpload/imgUpload", this.formDate,config).then( res => {
          this.userData.userimgpath = res.data[0];
        });
      },
      updateUser(){
        this.$axios.post('/user/updateUserById',{
          id: this.userData.id,
          username: this.userData.username,
          phonenumber: this.userData.phonenumber,
          mail: this.userData.mail,
          birthday: this.userData.birthday,
          sex: this.userData.sex,
          address: this.userData.address,
          userimgpath: this.userData.userimgpath
        }).then(res => {
          if(res.data == 1){
            this.$message({
              message: '用户信息修改成功！',
              type: 'success'
            });
            this.loadData();
          }else{
            this.$message({
              message: '用户信息修改失败！',
              type: 'error'
            });
          }
        });
      },
      updateAccount(userAccount){
        this.$refs[userAccount].validate((valid) => {
          if(valid) {

          }
        });
      },
      //上传图片
      uploadFile(file){
        this.formDate.append('file', file.file);
      },
      //移除图片
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      //关闭弹出层
      closeDialog(){
        this.resetForm('userAccount');
        this.updatePassword = false;
      },
      //重置表单
      resetForm(userAccount) {
        this.$refs[userAccount].resetFields();
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
              visitNum: res.data.visitNum,
              clickNum: (res.data.clickNum+1)
            });
          }
        });
      }
    }
  }
</script>

<style>
  .mineInfo,.userInfo {
    width: 90%;
    margin: auto;
    color: #777;
    margin-bottom: 10px;
    padding-bottom: 5px;
    border-radius: 10px;
    border: 1px solid #eee;
  }

  .mineInfo tr {
    width: 100%;
    margin: 0;
    padding: 0;
    text-align-last: center;
  }

  .mineInfo tr td {
    width: 120px;
    margin: 0;
    padding: 0;
  }

  .mineInfo tr td img {
    height: 90%;
    width: 90%;
    border-radius: 50%;
  }

  .userInfo{
    padding: 20px 0;
  }
</style>
