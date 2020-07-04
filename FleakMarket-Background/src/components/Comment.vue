<template>
  <div class="contationAccount">
    <div>
       <el-form :inline="true" :model="formInline" class="demo-form-inline">
         <el-form-item label="商品名称">
           <el-input v-model="formInline.name" placeholder="请填写商品名称"></el-input>
         </el-form-item>
         <el-form-item label="商品类别">
           <el-select v-model="formInline.sid" placeholder="请选择商品类别">
             <el-option :label="item.name" :value="item.id" v-for="item in SData" :key="item.id"></el-option>
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
           <el-button type="primary" @click="loadProductData">查询</el-button>
           <el-button type="primary" @click="RestSubmit">重置</el-button>
         </el-form-item>
       </el-form>
    </div>
    <div>
      <el-table
        :data="ProductData.slice((currentPage-1)*PageSize,currentPage*PageSize)"
        border
        style="width: 100%">
        <el-table-column prop="id" label="ID" width="60"> </el-table-column>
        <el-table-column prop="name" label="商品名称" width="200" :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="sname" label="商品类别" width="100"> </el-table-column>
        <el-table-column prop="details" label="商品描述" width="200" :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="clicks" label="点击量" width="80"> </el-table-column>
        <el-table-column prop="originalprice" label="原价" width="90"> </el-table-column>
        <el-table-column prop="currentprice" label="现价" width="90"> </el-table-column>
        <el-table-column prop="deal" label="交易方式" width="100"> </el-table-column>
        <el-table-column prop="address" label="交易地址" width="300" :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="status" label="商品状态" width="100">
          <template slot-scope="scope">
            {{scope.row.status == 1 ?'发布中':'已下架'}}
          </template>
        </el-table-column>
        <el-table-column prop="comments" label="评论数量" width="100"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="loadComment(scope.row.id)" type="text" size="small">查看评论</el-button>
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
    <!-- 查看商品评论弹出层 -->
    <el-dialog
      title="查看商品评价"
      :visible.sync="dialogVisible"
      width="45%"
      :close-on-click-modal="false"
      >
      <div>
          <ul>
            <li v-for="item in comment">
              <el-row class="buyer-describe">
                <div class="buyer-headerimg">
                  <img :src="item.userimgpath" />
                </div>
                <div class="buyer-sayed">
                  <div style="flex: 15;">
                    <div class="user">{{item.username}}</div>
                    <div class="buyer-text">{{item.text}}</div>
                    <div class="buyer-date">
                    {{item.time}}
                    <span v-if="item.status==2" style="margin-left: 10px;">已屏蔽</span>
                    </div>
                  </div>
                  <div style="flex: 3;">
                    <div v-if="item.status=='1'" @click="shield(item.id,item.pid,2)" style="line-height: 90px;"><p>屏蔽</p></div>
                    <div v-else style="line-height: 90px;"><p @click="shield(item.id,item.pid,1)">解屏蔽</p></div>
                  </div>
                </div>
              </el-row>
            </li>
          </ul>
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
        comment:[],
        //商品评论列表弹窗
        dialogVisible: false,
        currentPage: 1,//当前页
        PageSize:12,//一页数据
        SData:[],
	    }
	  },
    created() {
      this.loadProductData();
      this.loadSData();
    },
	  methods:{
      //请求商品数据列表
      loadProductData(){
        this.$axios.post('/Bproduct/selectProductCommentList',{
          name: this.formInline.name,
          sid: this.formInline.sid,
          deal: this.formInline.deal,
          status: this.formInline.status
        }).then(res => {
          this.ProductData = res.data;
        });
      },
      //加载二级分类商品
      loadSData(){
        this.$axios.get('/classify/selectScList').then(res => {
          this.SData = res.data;
        })
      },
      //重置表单
      RestSubmit(){
        this.formInline = {
          name: null,
          sid: '',
          deal: null,
          status: null
        };
        this.loadProductData();
      },
      //加载商品评论
      loadComment(id) {
        this.dialogVisible = true;
        //请求商品数据列表
        this.$axios.get('/product/selectCommentById',{
          params:{
            id:id
          }
        }).then(res => {
          this.comment = res.data;
        })
      },
      //评论跳转页
      CurrentChange(val) {
        this.currentPage=val;
      },
      //改变每页显示条数
      SizeChange(val) {
        this.PageSize = val;
      },
      //屏蔽评论
      shield(id,pid,status){
        let message = '';
        if(status == 1){
          message = "屏蔽已解除！";
        }else{
          message = "已屏蔽！";
        }
        this.$axios.post('/product/updateCommentById',{
          id:id,
          status: status
        }).then(res => {
          this.$message({
            type: 'success',
            message: message
          });
          this.loadComment(pid);
        });
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

  /* 评论模块 */
  .el-dialog{
    min-height: 40%;
  }
  .buyer-describe p {
    text-indent: 2em;
    padding: 0 15px;
    margin-bottom: 20px;
    height: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    line-height: 30px;
  }

  .buyer-describe {
    min-height: 130px;
  }

  .buyer-headerimg {
    width: 15%;
    float: left;
    color: #333333;
    padding-top: 20px;
    text-align: center;
  }

  .buyer-headerimg img {
    height: 70px;
    width: 70px;
    border-radius: 50%;
    margin: 10px;
  }

  .buyer-sayed {
    width: 80%;
    padding-right: 5%;
    height: 100%;
    line-height: 25px;
    float: right;
    display: flex;
    border-bottom: 1px solid #E0E0E0;
    word-wrap: break-word;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .buyer-sayed .user {
    margin-top: 20px;
    color: #6d757a;
    cursor: pointer;

  }
  .buyer-sayed .buyer-text{
    min-height: 60px;
  }

  .buyer-sayed .buyer-date {
    font-size: 13px;
    color: #99a2aa;
  }
  .buyer-sayed p:hover{
    cursor: pointer;
    color: #EE5757;
  }
  .operation{
    width: 10%;
  }
</style>
