<template>
  <div>
    <el-row class="cartheader" >
      <el-col :span="3">商品照片</el-col>
      <el-col :span="7" style="text-align-last: left;padding-left: 20px;">商品名称</el-col>
      <el-col :span="3">商品价格</el-col>
      <el-col :span="4">交易方式</el-col>
      <el-col :span="3">交易地址</el-col>
      <el-col :span="4">操作</el-col>
    </el-row>
    <el-row class="cartContainer" v-for="item in Data">
    	<el-col :span="3">
    		<div class="proImg">
          <img :src="item.images[0]" alt="">
        </div>
    	</el-col>
    	<el-col :span="7">
    		<div class="proName">{{item.name}}</div>
    		<div class="proDetail">{{item.details}}</div>
    	</el-col>
    	<el-col :span="3">
    		<div class="proOP"><s>¥{{item.currentprice}}</s></div>
    		<div class="proCP">¥{{item.originalprice}}</div>
    	</el-col>
    	<el-col :span="4">
    		<div class="proNumber">
          {{item.deal}}
        </div>
    	</el-col>
    	<el-col :span="3">
    		<div class="proAddress">{{item.address}}</div>
    	</el-col>
      <el-col :span="4">
      	<div class="proOperator">
          <div @click="loadProductDetail(item.id)">编辑</div>
          <div @click="deleteProdcut(item.id)">删除</div>
          <div v-if="item.status == 0" @click="soldout(item.id)">点击下架</div>
          <div v-if="item.status == 1" @click="soldin(item.id)">
            <span>点击发布</span>
          </div>
          <p v-if="item.status == 1">
            <span>商品已下架</span>
          </p>
        </div>
      </el-col>
    </el-row>

    <!-- 修改商品弹出层 -->
    <el-dialog
      title="修改商品信息"
      :visible.sync="dialogVisible"
      width="50%"
      :beforeClose="closedialog"
      :close-on-click-modal="false"
      >
      <div>
        <el-form ref="productData" :model="productData"  :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="商品名称" prop="name">
                <el-input v-model="productData.name"></el-input>
              </el-form-item>
            </el-col>
              <el-col :span="8" :offset="1">
                <el-form-item label="商品类别" prop="sid">
                  <el-cascader v-model="productItem" @change="updateSid" :options="productTypeItem" :props="{ expandTrigger: 'hover' }"></el-cascader>
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
                  :file-list="fileList"
                  :http-request="uploadFile"
                  ref="upload"
                  :on-remove="handleRemove">
                  <i class="el-icon-plus"></i>
                </el-upload>
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
              <el-button type="primary" @click="submitForm('productData')">确认修改</el-button>
              <el-button @click="loadProductDetail(productData.id)" type="info">重置</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
      data() {
        return {
          Data: '',
          dialogVisible: false,
          //弹出层
          User:'',
          creattime: '',
          productData: '',
          fileList: [],
          fileUrl: [],
          productItem: [],
          productType: [],//商品类型
          productTypeItem: [],//商品类型选项
          dealItem: ['线上交易','线下交易','面谈'],//交易类型数组
          formDate:"",
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
        };
      },
      created() {
        //加载我的商品数据
        this.loadMyProduct();
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
        });
      },
      methods: {
        //加载我的商品数据
        loadMyProduct(){
          this.$axios.get('/product/selectMyProductById', {
            params: {
                id: this.$store.state.userid
            }
          }).then(res => {
            res.data.forEach((res,index) => {
              if(res.images != null){
                res.images = eval('('+res.images+')');
              }
            });
            this.Data = res.data;
          });
        },
        //加载商品详情信息
        loadProductDetail(id){
          this.dialogVisible = true;
          this.$axios.get('/product/selectProductById',{
            params: {
              id: id
            }
          }).then(res => {
            this.productData = res.data;
             //图片回显
             this.fileList = [];
             let images = eval('('+res.data.images+')');
             if(images != undefined){
               images.forEach(res => {
                 this.fileList.push({url:res});
               });
             }
            //商品分类数据回显
            this.$axios.get('/classify/selectScById',{
              params: {
                id: res.data.sid
              }
            }).then(resi => {
              this.productItem = [];
              this.productItem.push(resi.data.fcid);
              this.productItem.push(resi.data.id);
              this.productData.sid = resi.data.id;
            });
          })

        },
        //提交商品表单
        submitForm(productData) {
          this.$refs[productData].validate((valid) => {
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
            console.log(this.fileList);
            this.$axios.post("/fileUpload/imgUpload", this.formDate,config).then( res => {
              //重组文件列表
              res.data.forEach(resi => {
                this.fileUrl.push(resi);
              });
              //文件列表数组字符串化
              this.productData.images = JSON.stringify( this.fileUrl );
              this.subProForm();
            });
        },
        //修改商品方法
        subProForm(){
          //发布商品
          this.$axios.post('/product/updateProductByUser', {
            id: this.productData.id,
            name: this.productData.name,
            sid: this.productData.sid,
            currentprice: this.productData.currentprice,
            originalprice: this.productData.originalprice,
            deal: this.productData.deal,
            address: this.productData.address,
            details: this.productData.details,
            images: this.productData.images
          }).then(res => {
            this.loadProductDetail(this.productData.id);
            this.loadMyProduct();
            if(res.data == 1){
              this.$message({
                message: '商品修改成功！',
                type: 'success'
              });
            }
          })
        },
        //上传图片
        uploadFile(file){
          this.formDate.append('file', file.file);
        },
        handleChange(value) {
          console.log(value);
        },
        //移除图片
        handleRemove(file, fileList) {
          this.fileUrl = [];
          fileList.forEach(res => {
            if(res.status == "success"){
              this.fileUrl.push(res.url);
            }
          });
        },
        //监听商品类型改变方法
        updateSid(value){
          this.productData.sid = value[1];
        },
        //关闭修改弹窗回调方法
        closedialog(){
          this.dialogVisible = false;
          this.productData = '';
          this.fileList = [];
          this.fileUrl = [];
        },
        //删除商品方法
        deleteProdcut(id){
          this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.get("/product/deleteProductById", {
              params:{id: id}
            }).then( res => {
              this.loadMyProduct();
              if(res.data == 1){
                this.$message({
                  message: '删除成功！',
                  type: 'success'
                });
              }
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        //下架商品
        soldout(id){
          this.$confirm('是否下架商品', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.post("/product/updateProductByUser", {
              id: id,
              status: 1
            }).then( res => {
              this.loadMyProduct();
              if(res.data == 1){
                this.$message({
                  message: '下架成功！',
                  type: 'success'
                });
              }
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        //上架商品
        soldin(id){
          this.$axios.post("/product/updateProductByUser", {
            id: id,
            status: 0
          }).then( res => {
            this.loadMyProduct();
            if(res.data == 1){
              this.$message({
                message: '发布成功！',
                type: 'success'
              });
            }
          });
        }
      }
    };
</script>

<style>
  .cartheader{
    line-height: 40px;
    margin: 5px 40px;
    font-size: 14px;
    padding: 0 20px;
    background-color: #fcfcfc;
    border: 1px solid #F0F0F0;
    text-align-last: center;
  }
  .cartContainer{
    height: 140px;
    margin: 5px 40px;
    padding: 20px;
    background-color: #fcfcfc;
    border: 1px solid #F0F0F0;
  }
  .proImg{
    height: 100px;
    width: 100px;
    background-color: #007DDB;
  }
  .proImg img{
    height: 100px;
    width: 100px;
  }
  .proName{
    font-size: 15px;
  }
  .proDetail{
    font-size: 12px;
    color: #555;
  }
  .proOP{
    font-size: 12px;
    color: #9c9c9c;
    text-align-last: center;
  }
  .proCP{
    font-size: 15px;
    margin-top: 5px;
    font-weight: 600;
    color: #3c3c3c;
    text-align-last: center;
  }
  .proNumber{
    font-size: 12px;
    text-align-last: center;
  }
  .proAddress{
    font-size: 12px;
    text-align-last: center;
  }
  .proOperator{
    font-size: 13px;
    color: #999999;
    text-align-last: center;
  }
  .proOperator div{
    margin-bottom: 5px;
  }
  .proOperator div:hover{
    color: #ffaa00;
    cursor: pointer;
  }
</style>
