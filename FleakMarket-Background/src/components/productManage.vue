<template>
  <div class="contationAccount">
    <div>
       <el-form :inline="true" :model="formInline" class="demo-form-inline">
         <el-form-item label="商品名称">
           <el-input v-model="formInline.name" placeholder="请填写商品名称"></el-input>
         </el-form-item>
         <el-form-item label="商品类别">
           <el-select v-model="formInline.sid" placeholder="请选择商品类别">
             <el-option :label="item.sname" :value="item.sid" v-for="item in SData" :key="item.sid"></el-option>
           </el-select>
         </el-form-item>
         <el-form-item label="交易方式">
           <el-select v-model="formInline.deal" placeholder="请选择交易方式">
             <el-option label="线上交易" value="线上交易"></el-option>
             <el-option label="线下交易" value="线下交易"></el-option>
           </el-select>
         </el-form-item>
         <el-form-item label="商品状态">
           <el-select v-model="formInline.status" placeholder="请选择商品状态">
             <el-option label="发布中" value="1"></el-option>
             <el-option label="已下架" value="2"></el-option>
           </el-select>
         </el-form-item>
         <el-form-item>
           <el-button type="primary" @click="loadData">查询</el-button>
           <el-button type="primary" @click="RestSubmit">重置</el-button>
         </el-form-item>
       </el-form>
    </div>
    <div>
      <el-table
        :data="ProductData.slice((currentPage-1)*PageSize,currentPage*PageSize)"
        border
        style="width: 100%">
        <el-table-column prop="id"            label="ID"       width="60"></el-table-column>
        <el-table-column prop="name"          label="商品名称"  width="200" :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="sname"         label="商品类别"  width="100"> </el-table-column>
        <el-table-column prop="clicks"        label="点击量"    width="80">  </el-table-column>
        <el-table-column prop="originalprice" label="原价"      width="90">  </el-table-column>
        <el-table-column prop="currentprice"  label="现价"      width="90">  </el-table-column>
        <el-table-column prop="deal"          label="交易方式"  width="100">  </el-table-column>
        <el-table-column prop="address"       label="交易地址"  width="300" :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="status"        label="交易状态"  width="100">
          <template slot-scope="scope">
            <span>{{scope.row.status==1?'发布中':'已下架'}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="status"        label="首页推荐"  width="200">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.pid!=0"
              disabled>
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button v-if="scope.row.status==2" @click="statusClick(scope.row.id,1)" type="text" size="small">发布</el-button>
            <el-button v-if="scope.row.status==1" @click="statusClick(scope.row.id,2)" type="text" size="small">下架</el-button>
            <el-button type="text" size="small"   @click="loadProductDetail(scope.row)">编辑</el-button>
            <el-button v-if="scope.row.status==1 && scope.row.pid==0"    @click="toRecommend(scope.row.id,1)" type="text" size="small">推荐</el-button>
            <el-button v-if="scope.row.pid!=0"    @click="toRecommend(scope.row.id,2)" type="text" size="small">取消推荐</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        :page-sizes="[12, 20, 30, 40]"
        :page-size="PageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="ProductData.length"
        @current-change="CurrentChange"
        @size-change="SizeChange"
        >
      </el-pagination>
    </div>

    <!-- 修改商品弹出层 -->
    <el-dialog
      title="修改商品信息"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="closedialog"
      :close-on-click-modal="false"
      >
      <div>
        <el-form ref="productItemData" :model="productItemData"  :rules="rules" label-width="80px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="商品名称" prop="name">
                <el-input v-model="productItemData.name"></el-input>
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
                <el-input v-model.number="productItemData.currentprice" placeholder="请填写商品售价"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8" :offset="1">
              <el-form-item label="商品原价" prop="originalprice">
                <el-input v-model.number="productItemData.originalprice" placeholder="请填写商品原价"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="交易方式" prop="deal">
                <el-select v-model="productItemData.deal" placeholder="请选择交易方式">
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
                  :on-change="handleChange"
                  :on-remove="handleRemove">
                  <i class="el-icon-plus"></i>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20">
              <el-form-item label="交易地址" prop="address">
                <el-input v-model="productItemData.address" placeholder="请填写交易地址"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20">
              <el-form-item label="商品描述" prop="details">
                <el-input type="textarea" v-model="productItemData.details" rows="5"maxlength="100" placeholder="请填写商品描述"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20" :offset="3">
              <el-button type="primary" @click="submitForm('productItemData')">确认修改</el-button>
              <el-button @click="loadProductDetail(productItemData.id)" type="info">重置</el-button>
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
        ProductData: [],
        formInline: {
          name: null,
          sid: '',
          deal: null,
          status: null
        },
        currentPage: 1,//当前页
        PageSize:12,//一页数据
        SData:[],
        dialogVisible: false,//修改商品弹窗
        ifUpload: false,//判断是否操作了图片
        dealItem: ['线上交易','线下交易','面谈'],//交易类型数组
        productItemData: '',
        fileList: [],//文件列表
        fileUrl: [],//文件地址
        productItem: [],
        productType: [],//商品类型
        productTypeItem: [],//商品类型选项
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
      this.loadData();
      this.loadSidProduct();

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
    methods:{
      //请求商品数据列表
      loadData(){
        this.$axios.post('/Bproduct/selectProductList',{
          name: this.formInline.name,
          sid: this.formInline.sid,
          deal: this.formInline.deal,
          status: this.formInline.status
        }).then(res => {
          console.log(res);
          this.ProductData = res.data;
        });
      },
      RestSubmit(){
        this.formInline = {
          name: null,
          sid: '',
          deal: null,
          status: null
        };
        this.loadData();
      },
      //商品发布与下架
      statusClick(id,status) {
        let text = status==1?'发布成功！':'下架成功！';
        this.$axios.post('/product/updateProductByUser',{
          id:id,
          status:status
        }).then(res => {
          if(res.data == 1){
            this.$message({
              type: 'success',
              message: text
            });
          }
          this.loadData();
        });
      },
      //加载商品详情信息
      loadProductDetail(res){
        this.dialogVisible = true;
          this.productItemData = res;
           //图片回显
           let images = eval('('+res.images+')');
           if(images != undefined){
             images.forEach(res => {
               this.fileList.push({url:res});
             });
           }
          //商品分类数据回显
          this.$axios.get('/classify/selectScById',{
            params: {
              id: res.sid
            }
          }).then(resi => {
            this.productItem = [];
            this.productItem.push(resi.data.fcid);
            this.productItem.push(resi.data.id);
            this.productItemData.sid = resi.data.id;
          });
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
          if(this.ifUpload){
            this.$axios.post("/fileUpload/imgUpload", this.formDate,config).then( res => {
              //重组文件列表
              res.data.forEach(resi => {
                this.fileUrl.push(resi);
              });
              //文件列表数组字符串化
              this.productItemData.images = JSON.stringify( this.fileUrl );
              this.subProForm();
            });
          }else {
            this.subProForm();
          }
      },
      //修改商品方法
      subProForm(){
        //发布商品
        this.$axios.post('/product/updateProductByUser', {
          id: this.productItemData.id,
          name: this.productItemData.name,
          sid: this.productItemData.sid,
          currentprice: this.productItemData.currentprice,
          originalprice: this.productItemData.originalprice,
          deal: this.productItemData.deal,
          address: this.productItemData.address,
          details: this.productItemData.details,
          images: this.productItemData.images
        }).then(res => {
          this.ifUpload = false;
          this.loadData();
          this.closedialog();
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
      //更改图片
      handleChange(value,fileList) {
        this.ifUpload = true;
        this.fileUrl = [];
        fileList.forEach(res => {
          if(res.status == "success"){
            this.fileUrl.push(res.url);
          }
        });
      },
      //移除图片
      handleRemove(file, fileList) {
        this.ifUpload = true;
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
      //请求商品分类数据
      loadSidProduct(){
        this.$axios.get('/classify/selectFcScByList',{}).then(res => {
          this.SData = res.data;
        })
      },
      //评论跳转页
      CurrentChange(val) {
        // 改变默认的页数
        this.currentPage=val;
      },
      //改变每页显示条数
      SizeChange(val) {
        this.PageSize = val;
      },
      //关闭修改弹窗回调方法
      closedialog(){
        this.dialogVisible = false;
        this.productData = '';
        this.fileList = [];
        this.fileUrl = [];
      },
      //首页推荐
      toRecommend(pid,index){
        if(index==1){
          //添加推荐
          this.$axios.get('/Bproduct/insertRecommendByPid',{
            params: {
              pid: pid
            }
          }).then(res => {
            this.$message({
              message: '推荐成功！',
              type: 'success'
            });
            this.loadData();
          });
        }else{
          //取消推荐
          this.$axios.get('/Bproduct/deleteRecommendByPid',{
            params: {
              pid: pid
            }
          }).then(res => {
            this.$message({
              message: '取消成功！',
              type: 'success'
            });
            this.loadData();
          });
        }
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
</style>
