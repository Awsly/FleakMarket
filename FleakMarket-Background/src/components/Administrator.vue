<template>
  <div class="contationAccount">
    <div>
       <el-form :inline="true" :model="formInline" class="demo-form-inline">
         <el-form-item label="管理员姓名">
           <el-input v-model="formInline.tname" placeholder="请填写姓名"></el-input>
         </el-form-item>
         <el-form-item label="联系方式">
           <el-input v-model="formInline.phone" placeholder="请填写联系方式"></el-input>
         </el-form-item>
         <el-form-item>
           <el-button type="primary" @click="loadAdminData">查询</el-button>
           <el-button type="primary" @click="RestSubmit">重置</el-button>
           <el-button type="primary" @click="insertAdmin">添加</el-button>
         </el-form-item>
       </el-form>
    </div>
    <div>
      <el-table :data="AdminData.slice((currentPage-1)*PageSize,currentPage*PageSize)" border style="width: 100%">
        <el-table-column prop="id"        label="ID"         width="100"> </el-table-column>
        <el-table-column prop="tname"     label="真实姓名"    width="150"> </el-table-column>
        <el-table-column prop="phone"     label="联系方式"    width="250"> </el-table-column>
        <el-table-column prop="username"  label="用户名"      width="150"> </el-table-column>
        <el-table-column prop="password"  label="登录密码"    width="300"> </el-table-column>
        <el-table-column prop="logintime" label="最后登录时间" width="250" >
          <template slot-scope="scope">
            {{scope.row.logintime==null?'暂无数据':scope.row.logintime}}
          </template>
        </el-table-column>
        <el-table-column prop="lv"        label="用户级别"     width="130">
          <template slot-scope="scope">
            {{scope.row.lv==1?'BOSS':'管理员'}}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="updateClick(scope.row)" type="text" size="small">编辑</el-button>
            <el-button v-if="scope.row.lv==2" @click="deleteClick(scope.row.id)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
         :page-sizes="[12, 20, 30, 40]"
         :page-size="PageSize"
         layout="total, sizes, prev, pager, next, jumper"
         :total="AdminData.length"
         @current-change="CurrentChange"
         @size-change="SizeChange"
         >
      </el-pagination>
      <!-- 添加用户信息弹出层 -->
      <el-dialog
        title="添加管理员信息"
        :visible.sync="Visible"
        width="45%"
        top="15%"
        :close-on-click-modal="false"
        >
        <div>
          <el-form ref="Admin" :model="Admin" :rules="rules" label-width="120px">
            <el-row>
              <el-col :span="9" :offset="1">
                <el-form-item label="用户名" prop="username">
                  <el-input v-model="Admin.username" placeholder="请填写用户名"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="9" :offset="1">
                <el-form-item label="密码" prop="password">
                  <el-input v-model.number="Admin.password" placeholder="请填写登录密码"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="9" :offset="1">
                <el-form-item label="真实姓名" prop="tname">
                  <el-input v-model="Admin.tname" placeholder="请填写管理员姓名"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="9" :offset="1">
                <el-form-item label="联系方式" prop="phone">
                  <el-input v-model="Admin.phone" placeholder="请填写联系方式"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
            </el-row>
            <el-row class="btn">
              <el-col :span="4" :offset="10">
                <el-button type="primary" @click="submitForm('Admin',1)">确认添加</el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-dialog>
      <!-- 修改用户信息弹出层 -->
      <el-dialog
        title="修改管理员信息"
        :visible.sync="dialogVisible"
        width="45%"
        :before-close="closeDialog"
        :close-on-click-modal="false"
        >
        <div>
          <el-form ref="Admin" :model="Admin" :rules="rules" label-width="120px">
            <el-row>
              <el-col :span="9" :offset="1">
                <el-form-item label="用户名" prop="username">
                  <el-input v-model="Admin.username" placeholder="请填写用户名"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="9" :offset="1">
                <el-form-item label="密码" prop="password">
                  <el-input v-model.number="Admin.password" placeholder="请填写登录密码"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="9" :offset="1">
                <el-form-item label="真实姓名" prop="tname">
                  <el-input v-model="Admin.tname" placeholder="请填写管理员姓名"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="9" :offset="1">
                <el-form-item label="联系方式" prop="phone">
                  <el-input v-model="Admin.phone" placeholder="请填写联系方式"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="10" :offset="1">
                <el-form-item label="最后登录时间" prop="logintime">
                  <el-date-picker
                    v-model="Admin.logintime"
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
                <el-button type="primary" @click="submitForm('Admin',2)">确认修改</el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
	export default {
	  data() {
	    return {
	      AdminData: [],
        Admin: {},
	      formInline: {
	        tname: null,
	        status: null,
          phone: null
	      },
        dialogVisible: false,
        Visible: false,
        currentPage: 1,//当前页
        PageSize:12,//一页数据
        rules: {
          tname: [
            { required: true, message: '管理员姓名不能为空', trigger: 'blur' },
            { min: 0, max: 10, message: '长度在 0 到 10 个字符', trigger: 'blur' }
          ],
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '联系方式不能为空', trigger: 'blur' }
          ]
        }
	    }
	  },
    created() {
      this.loadAdminData();
    },
	  methods:{
      //请求管理员信息
      loadAdminData(){
        this.$axios.post('/Badmin/selectAdminList',{
          tname: this.formInline.tname,
          status: this.formInline.status,
          phone: this.formInline.phone
        }).then(res => {
          this.AdminData = res.data;
        })
      },
      //重置表单
	    RestSubmit() {
        this.formInline = {
	        tname: null,
	        status: null,
          phone: null
	      };
	      this.loadAdminData();
	    },
      //添加/修改用户信息
      submitForm(Admin,type){
        this.$refs[Admin].validate((valid) => {
          if (valid) {
            if(type == 1){
              //添加管理员身份信息
              this.$axios.post('/Badmin/InsertAdminByAdmin',{
                tname:this.Admin.tname,
                username:this.Admin.username,
                password:this.Admin.password,
                phone:this.Admin.phone
              }).then(res => {
                if(res.data==1){
                   this.$message({
                     message: '添加成功！',
                     type: 'success'
                   });
                   this.loadAdminData();
                }
              });
            }else if(type == 2){
              //修改管理员身份信息
              this.$axios.post('/Badmin/updateAdminById',{
                id:this.Admin.id,
                tname:this.Admin.tname,
                username:this.Admin.username,
                password:this.Admin.password,
                phone:this.Admin.phone
              }).then(res => {
                if(res.data==1){
                   this.$message({
                     message: '修改成功！',
                     type: 'success'
                   });
                   this.loadAdminData();
                }
              });
            }

          } else {
            return false;
          }
        });
      },
      //修改数据
      updateClick(res){
        this.Admin = res;
        this.dialogVisible = true;
      },
      closeDialog(){
        this.Admin = {};
        this.dialogVisible = false;
      },
      //删除数据
      deleteClick(id){
        this.$confirm('是否删除该账号？','提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(res => {
          this.$axios.get('/Badmin/deleteAdminById',{
            params: {id: id}
          }).then(res => {
            if(res.data == 1){
              this.$message({
                type: 'success',
                message: '该账号已删除'
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      //添加管理员
      insertAdmin(){
        this.Visible = true;
      },
      //评论跳转页
      CurrentChange(val) {
        this.currentPage=val;
      },
      //改变每页显示条数
      SizeChange(val) {
        this.PageSize = val;
      }
	  }
	}
</script>

<style>
	.el-table{
	  text-align-last: center;
	  height: 733px;
	  overflow: auto;
	}
	.contationAccount{
	  padding: 20px;
	  margin: 10px;
	  margin-top: 0;
	  min-height: 855px;
	  background-color: #FFFFFF;
	}
	.el-pagination{
	  margin-top: 20px;
	  margin-right: 30px;
	  float: right;
	}
  .btn{
    margin-top: 30px;
  }
</style>
