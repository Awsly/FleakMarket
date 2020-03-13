<template>
  <div>
    <table class="mineInfo">
      <tr>
        <td rowspan="2">
          <img :src="imgPath" style="height: 90px;width: 90px;">
          <div>{{userData.username}}</div>
        </td>
        <td>我的年龄</td>
        <td>我的商品</td>
        <td>我的购物</td>
        <td>我的评价</td>
      </tr>
      <tr>
        <td>{{age}}</td>
        <td>20</td>
        <td>45</td>
        <td>23</td>
      </tr>
    </table>
    <!-- 用户模块表单 -->
    <el-form ref="userData" class="userInfo" :model="userData" label-width="120px">
      <el-row>
        <el-col :span="11">
          <el-form-item label="用户名">
            <el-input v-model="userData.username" placeholder="请填写用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="联系方式">
            <el-input v-model="userData.phonenumber" placeholder="请填写联系方式"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="性别">
            <el-radio-group v-model="userData.sex == 1?'男':'女'">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="出生日期">
            <el-date-picker type="date" placeholder="请选择日期" v-model="userData.birthday" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="我的邮箱">
            <el-input v-model="userData.mail" placeholder="请填写邮箱地址"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22">
          <el-form-item label="个性签名">
            <el-input type="textarea" v-model="userData.signature" rows="5" maxlength="100" placeholder="请填写个性签名"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22" :offset="4">
          <el-button type="primary">提交</el-button>
          <el-button @click="loadData()" type="info">重置</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  export default{
    data(){
      return {
        //我的账户
        userData: {},//用户数据
        age: '',//用户年龄
        imgPath: '../../static/img/akari.jpg',
      }
    },
    created() {
      //请求用户数据
      this.loadData();
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
        })
        //查询用户发布商品数量接口
        this.$axios.get('/user/selectUserById', {
          params: {
            id: this.$store.state.userid
          }
        }).then(res => {

        })
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
    height: 100%;
    border-radius: 50%;
  }

  .userInfo{
    padding: 20px 0;
  }
</style>
