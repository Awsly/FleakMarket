<template>
  <div class="orderContainer">
    <el-tabs v-model="activeName">
        <el-tab-pane label="待支付" name="待支付">
          <el-row class="myOrderContainer" v-for="item in payedData" v-if="item.pay==1" :key="item.id">
          	<el-col :span="11">
          		<div class="proName">订单编号: {{item.oid}}</div>
          		<div class="proDetail">创建日期: {{item.createtime}}</div>
          	</el-col>
          	<el-col :span="3">
          		<div class="proDetail">订单状态：{{item.pay==1?'未支付':'已支付'}}</div>
          		<div class="proDetail">商品数量：{{item.productnumber}}</div>
          	</el-col>
          	<el-col :span="5" :offset="1">
          		<span>订单总计：</span><span class="proTotal">¥{{item.producttotal}}</span>
          	</el-col>
            <el-col :span="4">
            	<div class="proOperator" style="margin-left: 5px;">
                <div @click="selectOrderById(item.oid)">查看订单详情</div>
                <div @click="gotoPay(item)" v-if="item.pay==1">立即支付</div>
                <div @click="deletePay(item.oid)">删除订单</div>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>



        <el-tab-pane label="已支付" name="已支付">
          <el-row class="myOrderContainer" v-for="item in payedData" v-if="item.pay==2" :key="item.oid">
          	<el-col :span="11">
          		<div class="proName">订单编号: {{item.oid}}</div>
          		<div class="proDetail">创建日期: {{item.createtime}}</div>
          	</el-col>
          	<el-col :span="3">
          		<div class="proDetail">订单状态：{{item.pay==1?'未支付':'已支付'}}</div>
          		<div class="proDetail">商品数量：{{item.productnumber}}</div>
          	</el-col>
          	<el-col :span="5" :offset="1">
          		<span>订单总计：</span><span class="proTotal">¥{{item.producttotal}}</span>
          	</el-col>
            <el-col :span="4">
            	<div class="proOperator" style="margin-left: 5px;">
                <div @click="selectOrderById(item.oid)">查看订单详情</div>
                <div @click="deletePay(item.oid)">删除订单</div>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>





        <el-tab-pane label="待发货" name="待发货">
          <el-row class="cartheader" style="margin: 5px 0;">
            <el-col :span="3">商品照片</el-col>
            <el-col :span="7" style="text-align-last: left;padding-left: 20px;">商品名称</el-col>
            <el-col :span="3">商品价格</el-col>
            <el-col :span="4">交易方式</el-col>
            <el-col :span="3">商品状态</el-col>
            <el-col :span="4">操作</el-col>
          </el-row>
          <el-row class="cartContainer" v-for="item in waitGetData" :key="item.id" v-if="item.opstatus==1" style="margin: 5px 0;">
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
          		<div class="proAddress" v-if="item.opstatus=='1'">未发货</div>
              <div class="proAddress" v-if="item.opstatus=='2'">已发货</div>
              <div class="proAddress" v-if="item.opstatus=='3'">已收货</div>
          	</el-col>
            <el-col :span="4">
            	<div class="proOperator" style="margin-left: 5px;">
                <div @click="showProDetails(item.id)">查看</div>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>




        <el-tab-pane label="已发货" name="已发货">
          <el-row class="cartheader" style="margin: 5px 0;">
            <el-col :span="3">商品照片</el-col>
            <el-col :span="7" style="text-align-last: left;padding-left: 20px;">商品名称</el-col>
            <el-col :span="3">商品价格</el-col>
            <el-col :span="4">交易方式</el-col>
            <el-col :span="3">商品状态</el-col>
            <el-col :span="4">操作</el-col>
          </el-row>
          <el-row class="cartContainer" v-for="item in waitGetData" :key="item.id" v-if="item.opstatus==2" style="margin: 5px 0;">
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
          		<div class="proAddress" v-if="item.opstatus=='1'">未发货</div>
              <div class="proAddress" v-if="item.opstatus=='2'">已发货</div>
              <div class="proAddress" v-if="item.opstatus=='3'">已收货</div>
          	</el-col>
            <el-col :span="4">
            	<div class="proOperator" style="margin-left: 5px;">
                <div @click="showProDetails(item.id)">查看</div>
                <div @click="clickReceving(item.id,item.oid,3)">点击收货</div>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>







        <el-tab-pane label="已收货" name="已收货">
          <el-row class="cartheader" style="margin: 5px 0;">
            <el-col :span="3">商品照片</el-col>
            <el-col :span="7" style="text-align-last: left;padding-left: 20px;">商品名称</el-col>
            <el-col :span="3">商品价格</el-col>
            <el-col :span="4">交易方式</el-col>
            <el-col :span="3">商品状态</el-col>
            <el-col :span="4">操作</el-col>
          </el-row>
          <el-row class="cartContainer" v-for="item in waitGetData" :key="item.id" v-if="item.opstatus==3" style="margin: 5px 0;">
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
          		<div class="proAddress" v-if="item.opstatus=='1'">未发货</div>
              <div class="proAddress" v-if="item.opstatus=='2'">已发货</div>
              <div class="proAddress" v-if="item.opstatus=='3'">已收货</div>
          	</el-col>
            <el-col :span="4">
            	<div class="proOperator" style="margin-left: 5px;">
                <div @click="showProDetails(item.id)">查看</div>
                <div @click="clickReceving(item.id,item.oid,4)">申请退款</div>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>





        <el-tab-pane label="退款" name="退款">
          <el-row class="cartheader" style="margin: 5px 0;">
            <el-col :span="3">商品照片</el-col>
            <el-col :span="7" style="text-align-last: left;padding-left: 20px;">商品名称</el-col>
            <el-col :span="3">商品价格</el-col>
            <el-col :span="4">交易方式</el-col>
            <el-col :span="3">商品状态</el-col>
            <el-col :span="4">操作</el-col>
          </el-row>
          <el-row class="cartContainer" v-for="item in waitGetData" :key="item.id" v-if="item.opstatus==4 || item.opstatus==5 || item.opstatus==6" style="margin: 5px 0;">
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
          		<div class="proAddress" v-if="item.opstatus=='4'">已申请</div>
              <div class="proAddress" v-if="item.opstatus=='5'">已退款</div>
              <div class="proAddress" v-if="item.opstatus=='6'">退款失败</div>
          	</el-col>
            <el-col :span="4">
            	<div class="proOperator" style="margin-left: 5px;">
                <div @click="showProDetails(item.id)">查看</div>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>



      <!-- 查看订单详情 -->
      <el-dialog
        width="50%"
        title="查看订单详情"
        :visible.sync="Visible"
        :destroy-on-close="true"
        >
        <el-row>
          <el-col :span="24"> 订单编号：<b>{{order.oid}}</b> </el-col>
        </el-row>
        <el-row class="orderInfo">
          <el-col :span="10"> 创建日期：<b>{{order.createtime}}</b> </el-col>
          <el-col :span="5"> 订单状态：{{order.pay==1?'未支付':'已支付'}} </el-col>
          <el-col :span="5"> 数量：{{order.productnumber}} </el-col>
          <el-col :span="2" style="text-align: right;"> 总计： </el-col>
          <el-col :span="2"> ¥{{order.producttotal}} </el-col>
        </el-row>
        <div class="productInfo">
          <el-row v-for="item in order.products" :key="item.id">
            <el-col :span="3"> <img :src="item.images[0]" /> </el-col>
            <el-col :span="5" class="infoTop"> <b>{{item.name}}</b> <br /> <span>{{item.details}}</span> </el-col>
            <el-col :span="6" class="infoTop"> <span>工作日、非工作日、全天时段均可配送预计发货后2-4个工作日送达</span> </el-col>
            <el-col :span="3" :offset="2" class="infoTop" style="text-align: center;">
              <span>未发货</span>
            </el-col>
            <el-col :span="2" class="infoTop"> <p>¥{{item.currentprice}}</p></el-col>
          </el-row>
        </div>
        <el-row class="addressInfo">
          <el-col :span="3" :offset="1"> 配送地址 </el-col>
          <el-col :span="15">{{order.address}}</el-col>
        </el-row>
        <el-row class="addressInfo">
          <el-col :span="3" :offset="1"> 收货人 </el-col>
          <el-col :span="15">{{order.consignee}}</el-col>
        </el-row>
        <el-row class="addressInfo">
          <el-col :span="3" :offset="1"> 联系方式 </el-col>
          <el-col :span="15">{{order.phone}}</el-col>
        </el-row>
      </el-dialog>


      <!-- 修改商品弹出层 -->
      <el-dialog
        title="查看商品详情"
        :visible.sync="proDetailsVisible"
        width="50%"
        :close-on-click-modal="false"
        >
        <div>
          <el-form ref="productData" :model="productData" label-width="80px">
            <el-row>
              <el-col :span="8">
                <el-form-item label="商品名称" prop="name">
                  <el-input v-model="productData.name" disabled></el-input>
                </el-form-item>
              </el-col>
                <el-col :span="8" :offset="1">
                  <el-form-item label="商品类别" prop="sid">
                    <el-cascader v-model="productItem" :options="productTypeItem" :props="{ expandTrigger: 'hover' }" disabled></el-cascader>
                  </el-form-item>
                </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="商品售价" prop="currentprice">
                  <el-input v-model.number="productData.currentprice" placeholder="请填写商品售价" disabled></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8" :offset="1">
                <el-form-item label="商品原价" prop="originalprice">
                  <el-input v-model.number="productData.originalprice" placeholder="请填写商品原价" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="交易方式" prop="deal">
                  <el-select v-model="productData.deal" placeholder="请选择交易方式" disabled>
                    <el-option value="线上交易"></el-option>
                    <el-option value="线下交易"></el-option>
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
                    ref="upload"
                     disabled
                    >
                    <i class="el-icon-plus"></i>
                  </el-upload>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="20">
                <el-form-item label="交易地址" prop="address">
                  <el-input v-model="productData.address" placeholder="请填写交易地址" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="20">
                <el-form-item label="商品描述" prop="details">
                  <el-input type="textarea" v-model="productData.details" rows="5" maxlength="500" placeholder="请填写商品描述" disabled></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-dialog>
  </div>
</template>

<script>
  import moment from "moment";
  export default {
    data() {
      return {
        num: 1,
        Visible: false,//订单详情弹出层
        proDetailsVisible: false,//商品详情弹出层
        payedData: [],//已支付
        waitGetData: [],//待收货
        productData: {},//商品详情对象
        productItem: [],//商品子项
        productType: [],//商品类型
        productTypeItem: [],//商品类型选项
        fileList: [],//文件列表展示
        order: '',//订单详情
        activeName: '待支付'
      };
    },
	  created() {
	  	//加载订单列表
      this.loadMyOrder();
      //加载订单商品列表
      this.loadMyOrderProduct();
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
      //加载订单列表
      loadMyOrder(){
        this.$axios.get('/order/selectOrderByUserId',{
          params: {userid: this.$store.state.userid}
        }).then(res => {
          this.payedData = res.data;
        });
      },
      //导入我的订单商品
      loadMyOrderProduct(){
        this.$axios.get('/order/selectOrderProductByUserId',{
          params: {userid: this.$store.state.userid}
        }).then(res => {
          res.data.forEach((res,index) => {
            if(res.images != null){
              res.images = eval('('+res.images+')');
            }
          });
          this.waitGetData = res.data;
        });
      },
      //订单详情
      selectOrderById(oid){
        this.$axios.get('/order/selectOrderById', {
          params: {
            oid : oid
          }
        }).then(res => {
          res.data.products.forEach((resc,index) => {
            if(resc.images != null){
              resc.images = eval('('+resc.images+')');
            }
          });
          this.order = res.data;
          this.Visible = true;
        })
      },
      //商品详情
      showProDetails(pid){
        this.$axios.get('/product/selectProductById',{
          params: {
            id: pid
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
        });
        this.proDetailsVisible = true;
      },
      //提交支付
      gotoPay(order){
        //判断是否支付
        this.$confirm('是否确定付款？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/order/updateOrderByOid",{
            oid: order.oid,
            pay: '2',
            paytime: moment().format('YYYY-MM-DD HH:mm:ss').toString()
          }).then(res => {
            if(res.data == 1){
              this.loadMyOrder();
              //跳转支付界面，进行沙箱模拟支付
              setTimeout(res => {
                window.location.href = "http://localhost:9999/utils/orderPay?oid="+order.oid+"&producttotal="+order.producttotal+"&remark="+order.remark;
              },500);
            }
          })
        });
      },
      //删除订单
      deletePay(oid){
        //判断是否支付
        this.$confirm('是否删除订单？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/order/deleteOrderByOid",{
            oid: oid
          }).then(res => {
            if(res.data == 1){
              this.$message({
                type: 'success',
                message: '订单已删除!'
              });
              this.loadMyOrder();
            }
          })
        });
      },
      //点击收货/退款
      clickReceving(pid,oid,status){
        let message = '';
        if(status==3){
          message='收货成功！';
        }else if(status==4){
          message='申请成功！';
        }
        this.$axios.post('/order/updateOrderById',{
          pid: pid,
          oid: oid,
          status: status
        }).then(res => {
          if(res.data==1){
            this.$message({
              message: message,
              type: 'success'
            });
          }
          this.loadMyOrderProduct();
        });

        //邮件提示买家
        this.$axios.post('/email/sendMailToBuyer',{
          pid: pid,//商品id
          oid: oid,//订单号
          status: status,//商品状态
        });
      },

    }
  };
</script>

<style>
  .orderContainer{
    margin: 0 20px;
  }
  .myOrderContainer{
    height: 140px;
    padding: 20px;
    margin: 5px 0;
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
  }
  .proName{
    font-size: 15px;
    margin-bottom: 20px;
  }
  .proDetail{
    font-size: 12px;
    color: #222;
    height: 20px;
    margin-bottom: 20px;
  }
  .proOP{
    font-size: 12px;
    color: #9c9c9c;
  }
  .proCP{
    font-size: 15px;
    margin-top: 5px;
    font-weight: 600;
    color: #3c3c3c;
  }
  .proTotal{
    font-weight: 500;
    color: #000;
    text-align-last: center;
  }
  .proOperator{
    font-size: 12px;
    line-height: 20px;
    margin-left: 50px;
  }
  .orderInfo{
    padding: 15px 0;
    border-bottom: 1px solid #EEEEEE;
  }
  .productInfo{
    margin-top: 10px;
    padding: 10px 5px;
    height: 250px;
    overflow: auto;
  }
  .productInfo img{
    height: 100px;
    width: 100px;
    margin: 5px 10px;
  }
  .productInfo .el-row{
    padding-top: 10px;
    border-bottom: 1px solid #E0E0E0;
  }
  .infoTop{
    max-height: 100px;
    overflow: hidden;
  }
  .infoTop p{
    margin-top: 0;
    text-align: right;
  }

  .addressInfo{
    height: 40px;
    margin-top: 20px;
    border-bottom: 1px solid #EEEEEE;
  }
  .noData{
    text-align: center;
    padding-top: 400px !important;
    margin-bottom: 100px;
    background-image: url(../../../static/img/noData/nodata05.png);
    background-repeat:no-repeat;
    background-position: center ;
  }
</style>
