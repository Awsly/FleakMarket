<template>
  <div class="contationAccount">
    <el-row>
      <el-col :span="24">
       <el-form :inline="true" :model="formInline" class="demo-form-inline">
         <el-form-item label="姓名">
           <el-input v-model="formInline.username" placeholder="请填写姓名"></el-input>
         </el-form-item>
         <el-form-item label="性别">
           <el-select v-model="formInline.sex" placeholder="请选择性别">
             <el-option label="男" value="男"></el-option>
             <el-option label="女" value="女"></el-option>
           </el-select>
         </el-form-item>
         <el-form-item label="联系方式">
           <el-input v-model="formInline.phonenumber" placeholder="请填写联系方式"></el-input>
         </el-form-item>
         <el-form-item>
           <el-button style="margin-top: 0;" type="primary" @click="loadData">查询</el-button>
           <el-button type="primary" @click="RestSubmit">重置</el-button>
         </el-form-item>
       </el-form>
       </el-col>
    </el-row>
    <div>
      <el-table :data="UsersData.slice((currentPage-1)*PageSize,currentPage*PageSize)" border style="width: 100%">
        <el-table-column prop="id"          label="ID"        width="100"></el-table-column>
        <el-table-column prop="uname"       label="用户名"     width="100"></el-table-column>
        <el-table-column prop="username"    label="真实姓名"   width="100"></el-table-column>
        <el-table-column prop="sex"         label="性别"       width="80"></el-table-column>
        <el-table-column prop="birthday"    label="生日"     width="120"></el-table-column>
        <el-table-column prop="address"     label="住址"     width="500"> </el-table-column>
        <el-table-column prop="phonenumber" label="联系方式"  width="200"> </el-table-column>
        <el-table-column prop="mail"        label="邮箱"      width="200"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="EditAccount(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="DeleteAccount(scope.row.id)" type="text" size="small">删除</el-button>
            <el-button @click="resetAccount(scope.row.id)" type="text" size="small">重置密码</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
        :page-sizes="[12, 20, 30, 40]"
        :page-size="PageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="UsersData.length"
        @current-change="CurrentChange"
        @size-change="SizeChange"
        >
      </el-pagination>
      <!-- 修改用户信息弹出层 -->
      <el-dialog
        title="修改用户信息"
        :visible.sync="dialogVisible"
        width="45%"
        :beforeClose="closedialog"
        :close-on-click-modal="false"
        >
        <div>
          <el-form ref="User" :model="User"  :rules="rules" label-width="80px">
            <el-row>
              <el-col :span="20" :offset="1">
                <el-form-item label="用户头像" prop="address">
                  <el-upload
                    action=""
                    :limit="1"
                    :multiple="false"
                    list-type="picture-card"
                    :auto-upload="false"
                    :file-list="fileList"
                    :http-request="uploadFile"
                    ref="upload"
                    >
                    <i class="el-icon-plus"></i>
                  </el-upload>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="9" :offset="1">
                <el-form-item label="用户姓名" prop="username">
                  <el-input v-model="User.username"></el-input>
                </el-form-item>
              </el-col>
                <el-col :span="9" :offset="1">
                  <el-form-item label="用户性别" prop="sex">
                    <el-select v-model="User.sex" placeholder="请选择用户性别">
                      <el-option label="男" value="1"></el-option>
                      <el-option label="女" value="2"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
            </el-row>
            <el-row>
              <el-col :span="9" :offset="1">
                <el-form-item label="联系方式" prop="phonenumber">
                  <el-input v-model.number="User.phonenumber" placeholder="请填写用户联系方式"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="9" :offset="1">
                <el-form-item label="Email" prop="mail">
                  <el-input v-model="User.mail" placeholder="请填写用户Eamil"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="23" :offset="1">
                <el-form-item label="生日" prop="birthday">
                  <el-date-picker
                    v-model="User.birthday"
                    type="date"
                    placeholder="选择日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="22" :offset="1">
                <el-form-item label="用户住址" prop="address">
                  <el-input type="textarea" v-model="User.address" rows="5" maxlength="100" placeholder="请填写交易地址"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="3" :offset="20">
                <el-button type="primary" @click="submitForm('User')">确认修改</el-button>
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
        UsersData: [],
        User:{file:'1'},
        currentPage: 1,//当前页
        PageSize:12,//一页数据
        //编辑用户信息弹窗
        dialogVisible: false,
        formInline: {
          username: null,
          sex: null,
          phonenumber: null
        },
        fileList: [],
        rules: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' },
            { min: 0, max: 15, message: '长度在 0 到 15 个字符', trigger: 'blur' }
          ],
          phonenumber: [
            { required: true, message: '联系方式不能为空', trigger: 'blur' }
          ],
          sex: [
            { required: true, message: '请选择性别', trigger: 'blur' }
          ],
          mail: [
            { required: true, message: '用户邮箱不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '用户住址不能为空', trigger: 'blur' }
          ],
          details: [
            { required: true, message: '请填写商品描述', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '请选择用户生日', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      //加载数据
      this.loadData();
    },
    methods:{
      //请求用户数据列表
      loadData(){
        this.$axios.post('/Buser/selectUserList',{
          username: this.formInline.username,
          sex: this.formInline.sex,
          phonenumber: this.formInline.phonenumber
        }).then(res => {
          this.UsersData = res.data;
        });
      },
      //重置方法
      RestSubmit() {
        this.formInline = {
          username: null,
          sex: null,
          phonenumber: null
        };
        this.loadData();
      },
      //编辑用户操作
      EditAccount(res){
        this.dialogVisible = true;
        this.User = res;
        this.fileList.push({url:this.User.userimgpath});
      },
      //删除用户操作
      DeleteAccount(id){
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //请求用户数据列表
          this.$axios.get('/user/deleteUserById',{
            params:{id:id}
          }).then(res => {
            if(res.data == 1){
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }else{
              this.$message({
                type: 'info',
                message: '删除失败!'
              });
            }
            //刷新数据列表
            this.loadData();
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      //重置用户账户密码
      resetAccount(id){
        this.$confirm('是否重置该用户账户？','提示', {
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type: 'warning'
        }).then(res => {
          this.$axios.get('/user/restAccountById',{
            params:{id:id}
          }).then(res => {
            if(res.data == 1){
              this.$message({
                type: 'success',
                message: '账户重置成功! 新密码：6666666'
              });
            }else{
              this.$message({
                type: 'success',
                message: '账户重置失败!'
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消重置'
          });
        })
      },
      //提交用户表单
      submitForm(User) {
        this.$refs[User].validate((valid) => {
          if (valid) {
            //提交图片，得到图片地址
            this.subPicForm();
          } else {
            return false;
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
          //文件列表数组字符串化
          if(res.data[0] != null){
            this.User.userimgpath = res.data[0];
          }
          this.subUserForm();
        });
      },
      //修改用户信息方法
      subUserForm(){
        //发布商品
        this.$axios.post('/user/updateUserById', {
          id: this.User.id,
          username: this.User.username,
          userimgpath: this.User.userimgpath,
          phonenumber: this.User.phonenumber,
          mail: this.User.mail,
          address: this.User.address,
          sex: this.User.sex
        }).then(res => {
          if(res.data == 1){
            this.$message({
              message: '用户信息修改成功！',
              type: 'success'
            });
          }
        })
      },
      //上传图片
      uploadFile(file){
        this.formDate.append('file', file.file);
      },
      //关闭修改弹窗回调方法
      closedialog(){
        this.dialogVisible = false;
        this.User = {};
        this.fileList = [];
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
    width: 96%;
    background-color: #FFFFFF;
  }
  .el-pagination{
    margin: 30px;
    float: right;
  }

  /* 图片样式 */
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    margin-bottom: 10px;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 128px;
    height: 128px;
    line-height: 128px;
    text-align: center;
  }
  .avatar {
    width: 128px;
    height: 128px;
    display: block;
  }
</style>
