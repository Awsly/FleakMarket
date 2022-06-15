<template>
  <div class="container">
    <el-row class="header">
      <el-col :span="3" class="title">
        校园二手交易市场
      </el-col>
      <el-col :span="18" class="func">
        <i class="el-icon-s-operation" @click="navigationBoolean"></i>
      </el-col>
      <el-col :span="3" class="user">
        <el-dropdown>
          <span class="el-dropdown-link">
            {{User.username}}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="AdminInfo"><span>个人信息</span></el-dropdown-item>
            <el-dropdown-item @click.native="Switchover">切换账户</el-dropdown-item>
            <el-dropdown-item @click.native="Exit">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>
           <a href="http://localhost:81/#/" target="view_window" style="color: #FFFFFF;">前台首页</a>
        </span>
      </el-col>
    </el-row>
    <div class="body">
      <div class="navigation">
        <el-menu
          class="el-menu-vertical-demo"
          @select="handleSelect"
          :collapse="isCollapse"
          background-color="#eeeeee"
        >
          <el-menu-item index="1">
            <i class="el-icon-user-solid"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-s-goods"></i>
            <span slot="title">商品管理</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-s-order"></i>
            <span slot="title">订单管理</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-s-comment"></i>
            <span slot="title">评价管理</span>
          </el-menu-item>
          <el-menu-item index="5" v-if="User.lv==1">
            <i class="el-icon-s-custom"></i>
            <span slot="title">管理员管理</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-s-data"></i>
            <span slot="title">网站数据统计</span>
          </el-menu-item>
        </el-menu>
        <div class="bodyView">
          <el-tabs v-model="TabsValue" type="card" @tab-remove="removeTab">
            <el-tab-pane label="我的桌面" name="0" content="0" :closable="false">
              <index></index>
            </el-tab-pane>
            <el-tab-pane
              v-for="(item, index) in editableTabs"
              :key="item.content"
              :label="item.title"
              :name="item.name"
              :closable="true"
            >
            <div class="bodys">
              <Account v-if="item.content === '1'"></Account>
              <productManage v-if="item.content === '2'"></productManage>
              <order v-if="item.content === '3'"></order>
              <Comment v-if="item.content === '4'"></Comment>
              <Adminstrator v-if="item.content === '5'"></Adminstrator>
              <Statistics v-if="item.content === '6'"></Statistics>
            </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
    <!-- 修改用户信息弹出层 -->
    <el-dialog
      title="修改个人信息"
      :visible.sync="dialogVisible"
      width="45%"
      :before-close="closeDialog"
      :close-on-click-modal="false"
      >
      <div>
        <el-form ref="User" :model="User" :rules="rules" label-width="120px">
          <el-row>
            <el-col :span="9" :offset="1">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="User.username" placeholder="请填写用户名"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="1">
              <el-form-item label="密码" prop="password">
                <el-input v-model.number="User.password" placeholder="请填写登录密码"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="9" :offset="1">
              <el-form-item label="真实姓名" prop="tname">
                <el-input v-model="User.tname" placeholder="请填写管理员姓名"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="9" :offset="1">
              <el-form-item label="联系方式" prop="phone">
                <el-input v-model="User.phone" placeholder="请填写联系方式"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10" :offset="1">
              <el-form-item label="最后登录时间" prop="logintime">
                <el-date-picker
                  v-model="User.logintime"
                  type="datetime"
                  :disabled="true"
                  >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
          </el-row>
          <el-row class="btn">
            <el-col :span="4" :offset="10">
              <el-button type="primary" @click="submitForm('User')">确认修改</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  //首页
  import index from "../components/index";
  //用户管理页
  import Account from "../components/Account";
  //商品管理页
  import productManage from "../components/productManage";
  //订单管理页
  import Order from "../components/Order";
  //评价管理页
  import Comment from "../components/Comment";
  //管理员管理页
  import Adminstrator from "../components/Administrator";
  //统计页
  import Statistics from "../components/Statistics";

  import moment from "moment";

  export default {
    components:{
        index:index,
        Account:Account,
        productManage:productManage,
        Order:Order,
        Comment:Comment,
        Adminstrator:Adminstrator,
        Statistics:Statistics
    },
    data() {
      return {
        isCollapse: false,//控制左边导航栏开关
        TabsValue: '0',//默认界面
        editableTabs: [],//标签栏
        tabIndex: 0,
        User: {},
        dialogVisible: false,
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
      };
    },
    created() {
      //查询用户身份数据
      this.User = this.$store.state.userInfo;
      if(this.User.id == null){
        this.$router.push('/');
      }
      //防止页面后退
      history.pushState(null, null, document.URL);
      window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
      });
    },
    methods: {
      //选择导航栏
      handleSelect(key, keyPath) {
        if(key === '1'){
          this.addTab("用户管理",'1');
        }else if(key === '2'){
          this.addTab("商品管理",'2');
        }else if(key === '3'){
          this.addTab("订单管理",'3');
        }else if(key === '4'){
          this.addTab("评价管理",'4');
        }else if(key === '5'){
          this.addTab("管理员管理",'5');
        }else if(key === '6'){
          this.addTab("网站数据统计",'6');
        }
      },
      //展开/关闭导航栏
      navigationBoolean(){
        this.isCollapse = !this.isCollapse;
      },
      //添加选项卡
      addTab(targetName,index) {
        let newTabName = ++this.tabIndex + '';
        let search = this.editableTabs.find(item => item.content === index);
        if(search===undefined){
          this.editableTabs.push({
            title: targetName,
            name: index,
            content: index
          });
          this.TabsValue = index;
        }else{
          this.TabsValue = index;
        }
      },
      //移除选项卡
      removeTab(targetName) {
        let tabs = this.editableTabs;
        let activeName = this.TabsValue;
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1];
              if (nextTab) {
                activeName = nextTab.name;
              }else{
                activeName = '0';
              }
            }
          });
        }
        this.TabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
      },
      //个人信息
      AdminInfo(){
        //展开弹窗
        this.dialogVisible = true;
      },
      //切换账户
      Switchover(){
        //跳转登录页面
        this.$router.push('/');
      },
      //退出登录
      Exit(){
        //修改管理员身份信息
        this.$axios.post('/Badmin/updateAdminById',{
          id:this.$store.state.userInfo.id,
          logintime: moment().format('YYYY-MM-DD HH:mm:ss'),
          status:2
        });
        //清空vuex缓存，跳转登录页面
        this.$router.push({name:'login',
          params: {id:1}
        });
      },
      closeDialog(){
        this.dialogVisible = false;
      },
      //添加/修改用户信息
      submitForm(User){
        this.$refs[User].validate((valid) => {
          if (valid) {
            //修改管理员身份信息
            this.$axios.post('/Badmin/updateAdminById',{
              id:this.User.id,
              tname:this.User.tname,
              username:this.User.username,
              password:this.User.password,
              phone:this.User.phone
            }).then(res => {
              if(res.data==1){
                this.$message({
                  message: '修改成功！',
                  type: 'success'
                });
                this.$store.commit('userInfo',this.User);
              }
            });
          } else {
            return false;
          }
        });
      },
    }
  }
</script>

<style>
  .container{
    height: 100%;
    background-color: #f1f1f1;
  }
  .header{
    line-height: 45px;
    color: #FFFFFF;
    background-color: #000000;
  }
  .title{
    font-size: 18px;
    text-align-last: center;
  }
  .func i{
    font-size: 15px;
    padding: 6px;
    cursor: pointer;
    border-radius: 10%;
    background-color: rgba(255,255,255,0.3);
  }
  .func i:hover{
    background-color: rgba(255,255,255,0.4);
  }
  .user span{
    font-size: 14px;
    margin-left: 20px;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #FFFFFF;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 250px;
    padding-top: 10px;
  }
  .el-menu-vertical-demo{
    height: 100%;
  }
  .navigation{
    height: 100%;
    display: flex;
  }
  .body{
    height: calc(100% - 56px);
  }
  .bodyView{
    height: 100%;
    flex: 1;
  }
  .bodyView .el-breadcrumb{
    line-height: 60px;
  }
  .bodys{
    background-color: #FFFFFF;
  }

</style>
