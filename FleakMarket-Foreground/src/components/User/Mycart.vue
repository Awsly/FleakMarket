<template>
  <div style="border: 1px solid #eee;padding-top: 20px;margin-bottom: 20px;">
    <!--  -->
    <el-row class="carttitle">
      <el-col :span="1" :offset="1">
        <span style="float: left;">选择</span>
      </el-col>
      <el-col :span="2">
        <span>商品图片</span>
      </el-col>
      <el-col :span="3">
        <span>商品名称</span>
      </el-col>
      <el-col :span="3" :offset="5">
        <span>商品价格</span>
      </el-col>
      <el-col :span="4">
        <span>商品状态</span>
      </el-col>
      <el-col :span="4">
        <span>操作</span>
      </el-col>
    </el-row>
    <el-divider></el-divider>
    <el-checkbox-group style="min-height: 500px;" v-model="checkedList" @change="CheckId">
      <el-row v-if="MycartData.length===0">
        <el-col :span="22" class="noData">
        </el-col>
      </el-row>
      <el-row class="cartContainer" v-for="item in MycartData" :key="item.id">
        <el-col :span="1">
          <el-checkbox :label="item" :value="item" v-if="item.status==='1'">
            <div class="checkId">
              <span>{{ item }}</span>
            </div>
          </el-checkbox>
          <el-checkbox v-else :lable="item.id" disabled></el-checkbox>
        </el-col>
        <el-col :span="3">
          <div class="proImg">
            <img :src="item.images[0]">
          </div>
        </el-col>
        <el-col :span="7">
          <div class="proName">{{ item.name }}</div>
          <div class="proDetail">{{ item.details }}</div>
        </el-col>
        <el-col :span="5">
          <div class="proOP"><s>¥{{ item.originalprice }}</s></div>
          <div class="proCP">¥{{ item.currentprice }}</div>
        </el-col>
        <el-col :span="3">
          <div class="proOP">{{ item.status === "1" ? '发布中' : '已下架' }}</div>
        </el-col>
        <el-col :span="4">
          <div class="proOperator">
            <!-- <div>移入收藏夹</div> -->
            <div @click="deleteCartById(item.id)">删除</div>
          </div>
        </el-col>
      </el-row>
    </el-checkbox-group>
    <el-divider></el-divider>

    <!-- 底部总计栏 -->
    <el-row class="cartfooter">
      <el-col :span="1" :offset="1">
        <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="CheckAll">全选</el-checkbox>
      </el-col>
      <el-col :span="4" :offset="13">
        <span>商品总计：</span><span style="font-size: 18px;color: #FF8800;">¥ {{ total }}</span>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="tobuy">去结算</el-button>
      </el-col>
    </el-row>

    <!-- 订单创建弹窗  -->
    <el-dialog
      title="创建订单"
      :visible.sync="outerVisible"
      :destroy-on-close="true"
      width="40%"
    >
      <div>
        <el-row class="buyPro" v-for="item in checkedList" :key="item.id">
          <el-col :span="3" :offset="1">
            <img :src="item.images[0]">
          </el-col>
          <el-col :span="14" :offset="1">
            <p>{{ item.name }}</p>
            <span>{{ item.details }}</span>
          </el-col>
          <el-col :span="5">
            <p>售价：¥ {{ item.currentprice }}</p>
          </el-col>
        </el-row>
        <el-row class="buyPro">
          <el-col :span="22" :offset="1">
            <el-form ref="userbuy" :model="userbuy" :rules="rules" label-width="100px">
              <el-form-item label="收货人名称" prop="username">
                <el-input v-model="userbuy.username"></el-input>
              </el-form-item>
              <el-form-item label="联系方式" prop="phonenumber">
                <el-input v-model.number="userbuy.phonenumber"></el-input>
              </el-form-item>
              <el-form-item label="通知邮箱" prop="mail">
                <el-input v-model="userbuy.mail"></el-input>
              </el-form-item>
              <el-form-item label="收货地址" prop="address">
                <el-input v-model="userbuy.address"></el-input>
              </el-form-item>
              <el-form-item label="订单备注">
                <el-input v-model="remark" placeholder="选填,请先和卖家协商一致"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="createOrder('userbuy')">立即创建</el-button>
                <el-button @click="outerVisible = false">取消</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </div>

      <!-- 支付订单弹窗 -->
      <el-dialog
        width="50%"
        title="订单支付"
        :visible.sync="innerVisible"
        :destroy-on-close="true"
        append-to-body>
        <el-row>
          <el-col :span="24"> 订单编号：<b>{{ order.oid }}</b></el-col>
        </el-row>
        <el-row class="orderInfo">
          <el-col :span="10"> 创建日期：<b>{{ order.createtime }}</b></el-col>
          <el-col :span="5"> 订单状态：{{ order.pay == 1 ? '未支付' : '已支付' }}</el-col>
          <el-col :span="5"> 数量：{{ order.productnumber }}</el-col>
          <el-col :span="2" style="text-align: right;"> 总计：</el-col>
          <el-col :span="2"> ¥{{ order.producttotal }}</el-col>
        </el-row>
        <div class="productInfo">
          <el-row v-for="item in order.products" :key="item.name">
            <el-col :span="3"><img :src="item.images[0]"/></el-col>
            <el-col :span="5" class="infoTop"><b>{{ item.name }}</b> <br/> <span>{{ item.details }}</span></el-col>
            <el-col :span="8" class="infoTop"><span>工作日、非工作日、全天时段均可配送预计发货后2-4个工作日送达</span></el-col>
            <el-col :span="6" class="infoTop"><p>¥{{ item.currentprice }}</p></el-col>
          </el-row>
        </div>
        <el-row class="addressInfo">
          <el-col :span="3" :offset="1"> 配送地址</el-col>
          <el-col :span="15">{{ order.address }}</el-col>
        </el-row>
        <el-row class="addressInfo">
          <el-col :span="3" :offset="1"> 收货人</el-col>
          <el-col :span="15">{{ order.consignee }}</el-col>
        </el-row>
        <el-row class="addressInfo">
          <el-col :span="3" :offset="1"> 联系方式</el-col>
          <el-col :span="15">{{ order.phone }}</el-col>
        </el-row>
        <el-row class="btnClick">
          <el-col :span="3" :offset="9">
            <el-button type="primary" @click="gotoPay(order)">点击支付</el-button>
          </el-col>
          <el-col :span="3">
            <el-button @click="deletePay(order.oid)">取消订单</el-button>
          </el-col>
        </el-row>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  data() {
    return {
      num: 1,
      MycartData: '',
      checkList: [],//总数据
      checkedList: [],//选中数据
      checkAll: false,
      isIndeterminate: false,
      total: 0,//商品总价
      //订单数据
      data: '',
      User: '',
      userbuy: '',
      array: [],//商品id数组
      outerVisible: false,//订单创建弹窗
      innerVisible: false,//订单支付弹窗
      remark: '',//订单备注
      order: {},//订单返回值
      rules: {
        username: [
          {required: true, message: '管理员姓名不能为空', trigger: 'blur'},
          {min: 0, max: 10, message: '长度在 0 到 10 个字符', trigger: 'blur'}
        ],
        phonenumber: [
          {required: true, message: '联系方式不能为空', trigger: 'blur'}
        ],
        mail: [
          {required: true, message: '邮箱不能为空', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '收货地址不能为空', trigger: 'blur'}
        ]
      }
    };
  },
  created() {
    //请求用户信息
    this.$axios.get('/user/selectUserById', {
      params: {
        id: this.$store.state.userid
      }
    }).then(res => {
      this.User = res.data;
      this.userbuy = res.data;
    });
    this.LoadMycartData();
    //点击量+1
    this.clickNum();
  },
  methods: {
    //导入我的购物车数据
    LoadMycartData() {
      this.$axios.get('/cart/selectCartById', {
        params: {userid: this.$store.state.userid}
      }).then(res => {
        this.checkList = [];
        this.checkedList = [];
        this.MycartData = '';
        res.data.forEach((res, index) => {
          if (res.images != null) {
            res.images = eval('(' + res.images + ')');
          }
          this.checkList.push(res);
        });
        this.MycartData = res.data;
      });
    },
    //全选按钮
    CheckAll(val) {
      this.checkedList = val ? this.checkList : [];
      this.isIndeterminate = false;
      this.sumTotal();
    },
    //单选按钮
    CheckId(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.MycartData.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.MycartData.length;
      this.sumTotal();
    },
    //计算总价,创建商品id数组
    sumTotal() {
      this.total = 0;
      this.array = [];
      if (this.checkedList.length > 0) {
        this.checkedList.forEach(res => {
          this.array.push(res.id);
          this.total += parseInt(res.currentprice);
        });
      }
      ;
    },
    //去结算
    tobuy() {
      if (this.checkedList.length > 0) {
        this.outerVisible = true;
      } else {
        this.$message({
          type: 'warning',
          message: '请选择商品'
        });
      }
    },
    //创建订单
    createOrder(userbuy) {
      this.$refs[userbuy].validate((valid) => {
        if (valid) {
          //关闭外层窗口
          this.outerVisible = false;
          //开始创建订单
          this.$axios.post('/order/insertOrderByOrder', {
            consignee: this.userbuy.username,//收货人
            phone: this.userbuy.phonenumber,//收货人联系方式
            mail: this.userbuy.mail,//收货人邮箱
            address: this.userbuy.address,//收货地址
            remark: this.remark,//订单备注
            createperson: this.User.username,//创建人
            createtime: moment().format('YYYY-MM-DD HH:mm:ss'),//创建时间
            productnumber: this.array.length,
            pid: this.array,
            producttotal: this.total
          }).then(res => {
            if (res.data.resulto == 1) {
              this.innerVisible = true;
              this.selectOrderById(res.data.uuid);
            }
          });
        } else {
          return false;
        }
      });
    },
    //订单详情
    selectOrderById(oid) {
      this.$axios.get('/order/selectOrderById', {
        params: {
          oid: oid
        }
      }).then(res => {
        this.order = '';
        res.data.products.forEach((resc, index) => {
          if (resc.images != null) {
            resc.images = eval('(' + resc.images + ')');
          }
        });
        this.order = res.data;
      })
    },
    //订单支付
    gotoPay(order) {
      //判断是否支付
      this.$confirm('是否确定付款？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        //修改支付状态
        this.$axios.post("/order/updateOrderByOid", {
          oid: order.oid,
          pay: '2',
          paytime: moment().format('YYYY-MM-DD HH:mm:ss').toString()
        }).then(res => {
          if (res.data == 1) {
            this.$axios.get('/product/soldOutProduct');
            this.deleteProductByArray();
            this.innerVisible = false;
            //跳转支付界面，进行沙箱模拟支付
            setTimeout(res => {
              window.location.href = "http://localhost:9999/utils/orderPay?oid=" + order.oid + "&producttotal=" + order.producttotal + "&remark=" + order.remark;
            }, 500);
          }
        });


        //邮箱通知卖家
        this.$axios.post('/email/sendMailToUser', {
          pids: this.array,//商品数组
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消付款'
        });
      });
    },
    //删除订单
    deletePay(oid) {
      //判断是否支付
      this.$confirm('是否取消订单？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("/order/deleteOrderByOid", {
          oid: oid
        }).then(res => {
          if (res.data == 1) {
            this.$message({
              type: 'success',
              message: '订单已取消!'
            });
            this.innerVisible = false;
          }
        })
      }).catch(() => {

      });
    },
    //删除购物车选中商品
    deleteProductByArray() {
      this.$axios.post('/cart/deleteProductByArray', {
        uid: this.$store.state.userid,
        list: this.array
      }).then(res => {
        this.LoadMycartData();
      });
    },
    //删除购物车商品
    deleteCartById(id) {
      this.$confirm('是否删除该商品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/cart/deleteProductByArray', {
          uid: this.$store.state.userid,
          list: [id]
        }).then(res => {
          if (res.data == 1) {
            this.$message({
              type: 'success',
              message: '删除成功！'
            });
            this.LoadMycartData();
          }
        });
      }).catch(() => {

      });
    },
    //统计点击量
    clickNum() {
      //判断数据库中是否存在当前日期
      this.$axios.post('/utils/selectDateFromStatis', {
        dates: moment().format('YYYY-MM-DD')
      }).then(res => {
        if (res.data.length == 0) {
          //如果数据库不存在今天的数据，插入今天日期与默认点击量
          this.$axios.post('/utils/insertDateInStatis', {
            dates: moment().format('YYYY-MM-DD'),
          });
        } else {
          //如果数据库存在今天的数据，获取当日点击量+1
          this.$axios.post('/utils/updateNumInStatis', {
            id: res.data.id,
            visitNum: res.data.visitNum,
            clickNum: (res.data.clickNum + 1)
          });
        }
      });
    }
  }
};
</script>

<style>
.carttitle, .cartfooter {
  padding: 0 20px;
  font-size: 12px;
  text-align-last: center;
}

.cartContainer {
  height: 140px;
  margin: 5px 40px;
  padding: 20px;
  background-color: #fcfcfc;
  border: 1px solid #F0F0F0;
}

.proImg {
  height: 100px;
  width: 100px;
  background-color: #007DDB;
}

.proImg img {
  height: 100px;
}

.proName {
  font-size: 15px;
}

.proDetail {
  font-size: 12px;
  color: #222;
}

.proOP {
  font-size: 12px;
  color: #9c9c9c;
}

.proCP {
  font-size: 15px;
  margin-top: 5px;
  font-weight: 600;
  color: #3c3c3c;
}

.proTotal {
  font-weight: 700;
  color: #f40;
  text-align-last: center;
}

.proOperator {
  font-size: 12px;
  margin-left: 50px;
}

.checkId {
  width: 100px;
  height: 53px;
}

.checkId span {
  display: none;
}

.cartfooter {
  line-height: 40px;
  margin-bottom: 20px;
}

/* 创建订单栏样式 */
.buyPro {
  padding: 15px 0;
  margin-bottom: 10px;
  border: 1px solid #eeeeee;
}

.buyPro img {
  height: 100px;
  width: 100px;
}

.buyPro p {
  margin-top: 0;
  font-size: 13px;
}

.orderInfo {
  padding: 15px 0;
  border-bottom: 1px solid #EEEEEE;
}

.productInfo {
  margin-top: 10px;
  padding: 10px 5px;
  height: 250px;
  overflow: auto;
}

.productInfo img {
  height: 100px;
  width: 100px;
  margin: 5px 10px;
}

.productInfo .el-row {
  padding-top: 10px;
  border-bottom: 1px solid #E0E0E0;
}

.infoTop {
  margin: 10px;
}

.infoTop p {
  text-align: right;
}

.addressInfo {
  height: 40px;
  margin-top: 20px;
  border-bottom: 1px solid #EEEEEE;
}

.btnClick {
  margin-top: 20px;
}

.noData {
  text-align: center;
  padding-top: 300px;
  background-image: url(../../../static/img/noData/nodata05.png);
  background-repeat: no-repeat;
  background-position: center;
}
</style>
