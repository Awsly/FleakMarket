<template>
  <div class="contationAccount">
    <div>
       <el-form :inline="true" :model="formInline" class="demo-form-inline">
         <el-form-item label="订单编号">
           <el-input v-model="formInline.oid" placeholder="请填写订单编号"></el-input>
         </el-form-item>
         <el-form-item label="支付状态">
           <el-select v-model="formInline.pay" placeholder="请选择支付状态">
             <el-option label="未支付" value="1"></el-option>
             <el-option label="已支付" value="2"></el-option>
           </el-select>
         </el-form-item>
         <el-form-item label="联系方式">
           <el-input v-model="formInline.phone" placeholder="请填写联系方式"></el-input>
         </el-form-item>
         <el-form-item>
           <el-button type="primary" @click="loadOrderData">查询</el-button>
           <el-button type="primary" @click="RestSubmit">重置</el-button>
         </el-form-item>
       </el-form>
    </div>
    <div>
      <el-table :data="OrderData.slice((currentPage-1)*PageSize,currentPage*PageSize)" border style="width: 100%">
        <el-table-column prop="id"            label="ID"           width="50"> </el-table-column>
        <el-table-column prop="oid"           label="订单编号"      width="350"> </el-table-column>
        <el-table-column prop="createperson"  label="创建人"        width="100"  :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="createtime"    label="创建时间"      width="160"> </el-table-column>
        <el-table-column prop="productnumber"  label="商品数量"      width="100"> </el-table-column>
        <el-table-column prop="producttotal"  label="商品总价"      width="130"> </el-table-column>
        <el-table-column prop="phone"         label="联系方式"      width="230"> </el-table-column>
        <el-table-column prop="address"       label="收货地址"      width="230"  :show-overflow-tooltip="true" > </el-table-column>
        <el-table-column prop="pay"           label="支付状态"      width="100">
          <template slot-scope="scope">
            {{scope.row.pay==1?"未支付":"已支付"}}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="selectOrderById(scope.row.oid)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        :page-sizes="[12, 20, 30, 40]"
        :page-size="PageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="OrderData.length"
        @current-change="CurrentChange"
        @size-change="SizeChange"
        >
      </el-pagination>
    </div>
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
        <el-row v-for="item in order.products">
          <el-col :span="3"> <img :src="item.images[0]" /> </el-col>
          <el-col :span="5" class="infoTop"> <b>{{item.name}}</b> <br /> <span>{{item.details}}</span> </el-col>
          <el-col :span="8" class="infoTop"> <span>工作日、非工作日、全天时段均可配送预计发货后2-4个工作日送达</span> </el-col>
          <el-col :span="6" class="infoTop"> <p>¥{{item.currentprice}}</p> </el-col>
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
  </div>
</template>

<script>
  export default {
    data() {
      return {
        OrderData: [],
        formInline: {
          oid: null,
          pay: null,
          phone: null,
        },
        Visible: false,
        order: {},
        currentPage: 1,//当前页
        PageSize:12,//一页数据
      }
    },
    created() {
      //加载订单列表
      this.loadOrderData();
    },
    methods:{
      //加载订单列表
      loadOrderData(){
        this.$axios.post('/order/selectOrderByList',{
          oid:this.formInline.oid,
          pay:this.formInline.pay,
          phone:this.formInline.phone
        }).then(res => {
          this.OrderData = res.data;
        })
      },
      RestSubmit() {
        this.formInline = {
          oid: null,
          pay: null,
          phone: null,
        };
        this.loadOrderData();
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
        }).catch(() => {

        });
      },
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
      }
    }
  }
</script>

<style>
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
  .el-table{
    text-align-last: center;
    height: 733px;
    overflow: auto;
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
    margin: 10px;
    height: 100px;
    overflow: hidden;
  }
  .infoTop p{
    text-align: right;
  }
  .addressInfo{
    height: 40px;
    margin-top: 20px;
    border-bottom: 1px solid #EEEEEE;
  }
</style>
