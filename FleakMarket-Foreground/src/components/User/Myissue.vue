<template>
  <div class="addproduct">
    <!-- 商品添加模块表单 -->
    <el-form ref="productData" :model="productData"  :rules="rules" label-width="80px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="发布人">
            <el-input v-model="User.username" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="1">
          <el-form-item label="发布时间">
            <el-input v-model="creattime" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="productData.name"></el-input>
          </el-form-item>
        </el-col>
          <el-col :span="8" :offset="1">
            <el-form-item label="商品类别" prop="sid">
              <el-cascader v-model="productData.sid" :options="productTypeItem" :props="{ expandTrigger: 'hover' }"></el-cascader>
            </el-form-item>
          </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="商品售价" prop="currentprice">
            <el-input v-model.number="productData.currentprice" placeholder="请填写商品售价"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8" :offset="1">
          <el-form-item label="商品原价" prop="originalprice">
            <el-input v-model.number="productData.originalprice" placeholder="请填写商品原价"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="交易方式" prop="deal">
            <el-select v-model="productData.deal" placeholder="请选择交易方式">
              <el-option v-for="item in dealItem" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20">
          <el-form-item label="商品图片">
            <el-upload
              action=""
              :limit="5"
              :multiple="true"
              list-type="picture-card"
              :auto-upload="false"
              :http-request="uploadFile"
              ref="upload"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove">
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20">
          <el-form-item label="交易地址" prop="address">
            <el-input v-model="productData.address" placeholder="请填写交易地址"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20">
          <el-form-item label="商品描述" prop="details">
            <el-input type="textarea" v-model="productData.details" rows="5"maxlength="100" placeholder="请填写商品描述"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20" :offset="3">
          <el-button type="primary" @click="submitForm('productData')">发布</el-button>
          <el-button @click="resetForm('productData')" type="info">重置</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import moment from "moment";
  export default{
    data(){
      return {
        User:'',
        creattime: '',
        //发布商品
        productData: {
          name: '',
          creattime: '',
          sid: '',
          currentprice: '',
          originalprice: '',
          deal: '',
          address: '',
          details: '',
          images: ''
        },//商品数据
        productType: '',//商品类型
        productTypeItem: [],//商品类型选项
        dealItem: ['线上交易','线下交易','面谈'],//交易类型数组
        formDate:"",
        dialogImageUrl: '',
        dialogVisible: false,
        rules: {
          name: [
            { required: true, message: '请输入商品名称', trigger: 'blur' },
            { min: 0, max: 15, message: '长度在 0 到 15 个字符', trigger: 'blur' }
          ],
          sid: [
            { required: true, message: '请选择商品类型', trigger: 'blur' }
          ],
          currentprice: [
            { required: true, message: '请输入商品售价', trigger: 'blur' },
            { type: 'number', min: 0, message: '请输入正确的价格', trigger: 'blur' }
          ],
          originalprice: [
            { required: true, message: '请输入商品原价', trigger: 'blur' },
            { type: 'number', min: 0, message: '请输入正确的价格', trigger: 'blur' }
          ],
          deal: [
            { required: true, message: '请选择交易方式', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '请填写交易地址', trigger: 'blur' }
          ],
          details: [
            { required: true, message: '请填写商品描述', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      //请求一二级分类数据
      this.$axios.get('/classify/selectFcScByList', {}).then(res => {
        let i = 0;
        res.data.forEach((data,index) =>{
          if(index == 0){
            this.productTypeItem.push({value:data.id,label: data.name,children:[{value:data.sid,label: data.sname}]});
          }else if(res.data[index-1].id != data.id){
            i++;
            this.productTypeItem.push({value:data.id,label: data.name,children:[{value:data.sid,label: data.sname}]});
          }else{
            this.productTypeItem[i].children.push({value:data.sid,label: data.sname});
          }
        })
      })

      //请求用户信息
      this.$axios.get('/user/selectUserById', {
        params: {
          id: this.$store.state.userid
        }
      }).then(res => {
        this.User = res.data;
      })
      //获取当前时间
      let date = moment().format('YYYY-MM-DD HH:mm:ss');
      this.productData.creattime = date;
      this.creattime = this.productData.creattime;
    },
    methods:{
      //提交商品表单
      submitForm(productData) {
        this.$refs[productData].validate((valid) => {
          if (valid) {
            //提交图片，得到图片地址
            this.subPicForm();
            //重置表单
            setTimeout(res => {
              this.resetForm(productData);
              this.$refs.upload.clearFiles();
            },600);
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
              this.productData.images = JSON.stringify( res.data );
              this.subProForm();
          });
      },
      //发布商品方法
      subProForm(){
        //商品种类序列化
        this.productData.sid = this.productData.sid[1];
        //发布商品
        this.$axios.post('/product/insertProductByUser', {
          uid: this.User.id,
          name: this.productData.name,
          creattime: this.productData.creattime,
          sid: this.productData.sid,
          currentprice: this.productData.currentprice,
          originalprice: this.productData.originalprice,
          deal: this.productData.deal,
          address: this.productData.address,
          details: this.productData.details,
          images: this.productData.images
        }).then(res => {
          if(res.data == 1){
            this.$message({
              message: '商品发布成功！',
              type: 'success'
            });
          }
        })
      },
      //重置表单
      resetForm(productData) {
        this.$refs[productData].resetFields();
        this.productData.sid = '';
      },
      //上传图片
      uploadFile(file){
        this.formDate.append('file', file.file);
      },
      //移除图片
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      //查看图片
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      }
    }
  }
</script>

<style>
  .addproduct{
    width: 90%;
    margin: auto;
    color: #777;
    margin-bottom: 10px;
    padding-bottom: 5px;
    border-radius: 10px;
    border: 1px solid #eee;
    padding: 20px 40px;
  }
</style>
