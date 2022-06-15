<template>
  <div class="bodyd">
    <Search></Search>
    <el-row class="containerDetails">
      <el-col :span="12" :offset="6">
        <div class="pro">
          <!-- 商品统计模块 -->
          <div class="pdtime"><span>发布于 : {{data.creattime}}</span> <span style="margin-left: 20px;">浏览人次：{{data.clicks}}次</span></div>
          <!-- 商品区域 -->
          <el-row>
            <!-- 商品图片 -->
            <el-col :span="10">
              <div class="pimg"> <img :src="imgblur" alt=""> </div>
              <div class="pimgs">
                <ul> <li v-for="item in data.images" @click="switchImg(item)" :key="item"><img :src="item" alt=""></li> </ul>
              </div>
            </el-col>
            <!-- 商品信息 -->
            <el-col :span="14" class="pdetails">
              <div>
                <div class="pdname">{{data.name}}</div>
                <div class="newProduct" @click="addLove(data.id)">
                  <i class="el-icon-star-off" v-if="love" style="color: red;"></i>
                  <i class="el-icon-star-off" v-else></i>
                </div>
              </div>
              <table class="pd">
                <tr> <td>价格</td>     <td class="pdprice">¥ {{data.currentprice}}                 </td> </tr>
                <tr> <td>商品原价</td> <td style="color: #999999;">¥<s> {{data.originalprice}}</s>  </td> </tr>
                <tr> <td>交易地址</td> <td>{{data.address}}                                        </td> </tr>
                <tr> <td>商品状态</td> <td>{{data.status === '1'?"未出售":"已出售"}}                    </td> </tr>
                <tr> <td>交易方式</td> <td>{{data.deal}}                                            </td> </tr>
                <tr> <td>卖家</td>     <td>{{data.username}}                                        </td> </tr>
              </table>
              <div class="pdbutton" v-if="data.deal==='线上交易'">
                <el-button type="success" @click="buy" icon="el-icon-wallet" style="width: 140px;">立即购买</el-button>
                <el-button type="danger"  @click="addCart(data.id)" icon="el-icon-shopping-cart-full" style="width: 140px;">加入购物车</el-button>
              </div>
              <div class="pdbutton" v-if="data.deal==='线下交易'">
                <el-button type="success" @click="showUserInfo(data.uid)" icon="el-icon-wallet">查看卖家联系方式</el-button>
              </div>
            </el-col>
          </el-row>

          <!-- 买家区域 -->
          <el-row class="buyer">
            <el-col>商品描述</el-col>
          </el-row>
          <el-row class="buyer-describe">
            <p>{{data.details}}</p>
          </el-row>

          <!-- 评论统计模块 -->
          <div class="buyer">
            <span>{{totalCount}}条评论</span>
            <div class="pagesmall" @click="refresh"><i class="el-icon-refresh-right"></i>刷新</div>
          </div>

          <!-- 发布评论模块 -->
          <div class="leaveMessage" v-if="this.User !== ''">
            <div class="meimg"><img :src="User.userimgpath" alt=""></div>
            <textarea :style="backcolor" maxlength="200" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" @mouseenter="enter"
              @mouseleave="leave" v-model="textarea"></textarea>
            <div class="leaveMessageBtn">
              <button @click="insertComment(data.id)">提交</button>
            </div>
          </div>

          <!-- 评论模块 -->
          <ul style="min-height: 200px;">
            <li v-for="item in comments.slice((currentPage-1)*PageSize,currentPage*PageSize)" :key="item.id">
              <el-row class="buyer-describe">
                <div class="buyer-headerimg">
                  <img :src="item.userimgpath" />
                </div>
                <div class="buyer-sayed">
                  <div class="user">{{item.username}}</div>
                  <div class="buyer-text">{{item.text}}</div>
                  <div class="buyer-date">{{item.time}}</div>
                </div>
              </el-row>
            </li>
            <!-- 评论统计模块 -->
            <li class="pagebig" v-if="comments !== ''">
              <div class="buyer-headerimg">
              </div>
              <el-pagination background
                layout="total,prev, pager, next,jumper"
                :page-size="PageSize"
                prev-text=" 上一页 "
                next-text=" 下一页 "
                :total="totalCount"
                @current-change="CurrentChange"
                >
              </el-pagination>
            </li>
          </ul>
        </div>
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
          <el-row class="buyPro">
            <el-col :span="4" :offset="2">
              <img :src="imgblur">
            </el-col>
            <el-col :span="13" :offset="1">
              <p>{{data.name}}</p>
              <span>{{data.details}}</span>
            </el-col>
            <el-col :span="4">
              <p>售价：¥ {{data.currentprice}}</p>
            </el-col>
          </el-row>
          <el-row class="buyPro">
            <el-col :span="20" :offset="1">
              <el-form ref="userbuy" :model="userbuy" :rules="rules"  label-width="100px">
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
                  <el-button type="primary" @click="createOrder">立即创建</el-button>
                  <el-button>取消</el-button>
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
          <el-col :span="24"> 订单编号：<b>{{order.oid}}</b> </el-col>
        </el-row>
        <el-row class="orderInfo">
          <el-col :span="10"> 创建日期：<b>{{order.createtime}}</b> </el-col>
          <el-col :span="5"> 订单状态：{{order.pay===1?'未支付':'已支付'}} </el-col>
          <el-col :span="5"> 数量：{{order.productnumber}} </el-col>
          <el-col :span="2" style="text-align: right;"> 总计： </el-col>
          <el-col :span="2"> ¥{{order.producttotal}} </el-col>
        </el-row>
        <div class="productInfo">
          <el-row v-for="item in order.products" :key="item.id">
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
    <FooterBottom></FooterBottom>
  </div>
</template>

<script type="text/javascript">
  import Search from "../components/Search.vue";
  import FooterBottom from "../components/Footer.vue";
  import moment from "moment";
  export default {
    components: {
      Search,
      FooterBottom
    },
    data() {
      return {
        User: '',//用户信息
        userbuy: '',//买家信息
        data: '',//商品详情信息
        imgblur: '',//图片地址
        currentPage: 1,//当前页
        layout: 'slot,pager,next',//分页显示工具
        textarea: '',//商品详情
        backcolor: '',//商品详情背景颜色
        comments: '',//评论列表
        PageSize:5,//每页显示评论数
        totalCount:1,//评论总量
        love: false,//是否收藏
        outerVisible:false,//外层订单弹窗
        innerVisible:false,//内层订单弹窗
        remark: '',//订单备注
        order: {},//订单返回值
        vendor: {},//出售人信息
        rules: {
          username: [
            { required: true, message: '管理员姓名不能为空', trigger: 'blur' },
            { min: 0, max: 10, message: '长度在 0 到 10 个字符', trigger: 'blur' }
          ],
          phonenumber: [
            { required: true, message: '联系方式不能为空', trigger: 'blur' }
          ],
          mail: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '收货地址不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      //页面置顶
      window.scrollTo(0, 0);
      //请求用户信息
      this.$axios.get('/user/selectUserById', {
        params: {
          id: this.$store.state.userid
        }
      }).then(res => {
        this.User = res.data;
        this.userbuy = res.data;
      });
      //加载商品详情
      this.loadproductdetails();
      //加载用户评论
      this.loadcomments();
      //点击量+1
      setTimeout(res => {
        this.clickNum();
      },500);
    },
    methods: {
      switchImg(imgPath){
        this.imgblur = imgPath;
      },
      //评论跳转页
      CurrentChange(val) {
        // 改变默认的页数
        this.currentPage=val;
      },
      //鼠标移入
      enter() {
        this.backcolor = "background:#FFFFFF";
      },
      //鼠标移出
      leave() {
        this.backcolor = this.textarea.length === 0 ? "background:#f4f5f7" :
          "background:#FFFFFF;border: 1px solid #00a1d6;";
      },
      //加载商品详情方法
      loadproductdetails(){
        //商品数据详情
        this.$axios.get('/product/selectProductById', {
          params: {id: this.$route.query.id}
        }).then(res => {
          this.data = res.data;
          this.data.images = eval('('+res.data.images+')');
          this.imgblur = this.data.images[0];
        })
      },
      //加载商品列表方法
      loadcomments(){
        //商品评论列表查询
        this.$axios.get('/product/selectCommentById', {
          params: {id: this.$route.query.id}
        }).then(res => {
          this.comments = res.data;
          this.totalCount = res.data.length;
          this.updataProductData(res.data.length);
          this.selectLoveById();
        });
      },
      //修改商品评论数量
      updataProductData(length){
        this.$axios.post('/product/updateProductByUser', {
          id: this.data.id,
          comments: length.toString()
        });
      },
      //判断商品是否收藏
      selectLoveById(){
        this.$axios.post('/product/selectLoveById', {
          uid: this.$store.state.userid,
          pid: this.data.id
        }).then(res => {
          if(res.data > 0){
            this.love = true;
          }else{
            this.love =false;
          }
        });
      },
      //添加商品进收藏方法
      addLove(id){
        //判断是否登录
        if(this.$store.state.userid === 0){
          this.$message({
            message: '请登录！',
            type: 'warning'
          });
          return null;
        }
        //判断是否是自己的商品
        if(this.$store.state.userid === this.data.uid){
          this.$message({
            message: '不能收藏自己发布的商品！',
            type: 'warning'
          });
          return null;
        }
        //判断商品是否下架
        if(this.data.status === '2'){
          this.$message({
            message: '商品已出售！',
            type: 'warning'
          });
          return null;
        }
        this.selectLoveById();
        //判断商品是否添加
        if(!this.love){
          //商品添加收藏
          this.$axios.post('/product/insertProductToLove', {
            uid: this.$store.state.userid,
            pid: id
          }).then(res => {
            if(res.data === 1){
              this.$message({
                message: '商品收藏成功！',
                type: 'success'
              });
              this.love = true;
            }
          })
        }else{
          this.$message({
            message: '请勿重复收藏！',
            type: 'warning'
          });
        }
      },
      //展示用户信息
      showUserInfo(id){
        this.$axios.get('/user/selectUserById',{
          params: {
            id:id
          }
        }).then(res => {
          this.$alert(
          '<div><span  style="font-weight:600;margin-left:30px;">用户名：</span>'+res.data.username+'</div>'+
          '<div><span  style="font-weight:600;margin-left:30px;">联系电话：</span>'+res.data.phonenumber+'</div>'+
          '<div><span  style="font-weight:600;margin-left:30px;">E-mail：</span>'+res.data.mail+'</div>'
          ,'卖家联系方式',{
               dangerouslyUseHTMLString: true
            });
        });
      },
      //发布评论
      insertComment(id){
        let date = moment().format('YYYY-MM-DD HH:mm:ss');
        if(this.textarea != null && this.textarea.length <1){
          this.$message({
            message: '内容不能为空！',
            type: 'error'
          });
        }else{
          this.$axios.post('/product/insertComment', {
            uid: this.$store.state.userid,
            pid: id,
            text: this.textarea,
            time: date
          }).then(res => {
             if(res.data === 1){
               this.$message({
                 message: '发布成功！',
                 type: 'success'
               });
               this.textarea = '';
               this.leave();
               this.loadcomments();
             }
          });
        }
      },
      //刷新评论
      refresh(){
        this.loadcomments();
        this.$message({
          message: '刷新成功',
          type: 'success'
        });
      },
      //取消购买
      handleClose(done) {
        this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
      },
      //取消购买
      handleClose2(done) {
        this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
      },
      //立即购买
      buy(){
        if(this.$store.state.userid === 0){
          //判断是否登录
          this.$message({
            message: '请登录！',
            type: 'warning'
          });
        }else{
          //判断商品是否下架
          if(this.data.status === '2'){
            this.$message({
              message: '商品已出售！',
              type: 'warning'
            });
            return null;
          }
          //判断是否是自己的商品
          if(this.$store.state.userid === this.data.uid){
            this.$message({
              message: '不能购买自己发布的商品！',
              type: 'warning'
            });
            return null;
          }
          this.outerVisible=true;
        }
      },
      //创建订单
      createOrder(){
        this.outerVisible = false;
        //开始创建订单
        this.$axios.post('/order/insertOrderByOrder', {
          consignee: this.userbuy.username,//收货人
          phone: this.userbuy.phonenumber,//收货人联系方式
          mail: this.userbuy.mail,//收货人邮箱
          address: this.userbuy.address,//收货地址
          remark: this.remark,//订单备注
          cid: this.User.id,//创建人编号
          createperson: this.User.username,//创建人
          createtime: moment().format('YYYY-MM-DD HH:mm:ss'),//创建时间
          productnumber: 1,
          pid: [this.data.id],
          producttotal: this.data.currentprice
        }).then(res => {
          if(res.data.resulto === 1){
            this.innerVisible = true;
            this.selectOrderById(res.data.uuid);
          }
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
        })
      },
      //订单支付
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
              if(res.data === 1){
                //订单中商品全部下架,支付状态显示已支付
                this.$axios.get('/product/soldOutProduct');
                //邮箱通知卖家
                this.$axios.post('/email/sendMailToUser',{
                  pids: [this.data.id],//商品数组
                });
                this.innerVisible = false;
                //跳转支付界面，进行沙箱模拟支付
                /*setTimeout(res => {
                  window.location.href = "http://localhost:9999/utils/orderPay?oid="+order.oid+"&producttotal="+order.producttotal+"&remark="+order.remark;
                },500);*/
                //跳转我的页面
                let loading = this.$loading({
                  text: "支付成功，即将跳转个人中心页面，请在我的订单模块查询订单状态..."
                });
                setTimeout(()=>{
                  loading.close();
                  this.$router.push('mine');
                },3000);
              }
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消付款'
          });
        });
      },
      //删除订单
      deletePay(oid){
        //判断是否支付
        this.$confirm('是否取消订单？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post("/order/deleteOrderByOid",{
            oid: oid
          }).then(res => {
            if(res.data === 1){
              this.$message({
                type: 'success',
                message: '订单已取消!'
              });
              this.innerVisible = false;
            }
          });
        });
      },
      //添加商品进入购物车
      addCart(pid){
      //查找购物车是否存在商品
        if(this.$store.state.userid !== 0){
          //判断是否是自己的商品
          if(this.$store.state.userid === this.data.uid){
            this.$message({
              message: '不能购买自己发布的商品！',
              type: 'warning'
            });
            return null;
          }
          //判断商品是否下架
          if(this.data.status === '2'){
            this.$message({
              message: '商品已出售！',
              type: 'warning'
            });
            return null;
          }
          this.$axios.post('/cart/selectCartProductById',{
            pid:pid,
            uid:this.$store.state.userid
          }).then(res => {
            if(res.data === 0){
              //添加商品进入购物车
              this.$axios.post('/cart/insertCartById',{
                pid:pid,
                uid:this.$store.state.userid
              }).then(res => {
                if(res.data === 1){
                  this.$message({
                    type: "success",
                    message: "商品添加成功！"
                  });
                }
              })
            }else{
              this.$message({
                type: "warning",
                message: "请勿重复添加！"
              });
            }
          });
        }else{
          this.$message({
            type: "warning",
            message: "请登录！"
          });
        }
      },
      //统计点击量
      clickNum(){
        //判断数据库中是否存在当前日期
        this.$axios.post('/utils/selectDateFromStatis',{
          dates: moment().format('YYYY-MM-DD')
        }).then(res => {
          if(res.data.length === 0){
            //如果数据库不存在今天的数据，插入今天日期与默认点击量
            this.$axios.post('/utils/insertDateInStatis',{
              dates: moment().format('YYYY-MM-DD'),
            });
          }else{
            //如果数据库存在今天的数据，获取当日点击量+1
            this.$axios.post('/utils/updateNumInStatis',{
              id: res.data.id,
              visitNum: res.data.visitNum,
              clickNum: (res.data.clickNum+1)
            });
          }
        });
        //商品点击量加一
        this.$axios.post('/product/updateProductByUser',{
          id:this.data.id,
          clicks: (this.data.clicks+1)
        });
      }
    }
  }
</script>

<style scoped="scoped">
  .containerDetails {
    margin-top: -19px;
    margin-bottom: 20px;
    background-color: #FFFFFF;
  }

  .logo {
    height: 80px;
  }

  .logo img {
    height: 100%;
  }

  .seek div {
    height: 40px;
    display: flex;
    margin-top: 20px;
    background-color: #FCFCFC;
    border: 3px solid rgba(255, 153, 0);
  }

  .seek div input {
    flex: 1;
    border: 0;
    padding: 0 10px;
    outline: none;
  }

  .seek div button {
    width: 80px;
    border: 0;
    outline: none;
    color: #FFFFFF;
    font-size: 17px;
    background-color: rgba(255, 153, 0);
  }


  .pro {
    width: 100%;
    padding: 10px 20px;
    margin-top: 15px;
    margin-bottom: 10px;
    border: 1px solid #EEEEEE;
    border-radius: 10px 10px 10px 10px;
  }

  .pdtime {
    font-size: 13px;
    color: #CCCCCC;
    line-height: 40px;
    margin-bottom: 15px;
    border-bottom: 1px solid #E8E8E8;
  }

  .pimg {
    width: 100%;
    height: 400px;
    margin: auto;
    border: 1px solid #eaeaea;
  }

  .pimg img {
    width: 100%;
  }

  .pimgs {
    width: 100%;
    margin: auto;
  }

  .pimgs ul li {
    float: left;
    width: 20%;
    height: 80px;
    cursor: pointer;
    padding: 5px 0;
    margin-top: 10px;
    border-top: 1px solid #E9E9E9;
    border-bottom: 1px solid #E9E9E9;
    text-align-last: center;
  }

  .pimgs ul li img {
    width: 95%;
    border-radius: 2px;
    transition: all 0.2s;
  }

  .pimgs ul li img:hover{
    width: 100%;
  }

  .pdetails {
    padding-left: 80px;
  }

  .pdname {
    font-size: 30px;
    max-height: 80px;
    width: 80%;
    color: #333333;
    margin: 20px 0;
    float: left;
    overflow: hidden;
    /*超出部分隐藏*/
    text-overflow: ellipsis;
    /* 超出部分显示省略号 */
    white-space: nowrap;
    /*规定段落中的文本不进行换行 */
  }

  .newProduct {
    float: right;
    font-size: 25px;
    margin-top: 30px;
    margin-right: 20px;
    padding: 2px;
    text-align-last: center;
    cursor: pointer;
    width: 13%;
  }

  .pd {
    clear: left;
  }

  .pd td {
    height: 40px;
    color: #333333;
  }

  .pd tr td:nth-of-type(1) {
    width: 100px;
    color: #999;
    font-size: 13px;
  }

  .pdprice {
    color: #FF0036 !important;
    font-size: 25px;
  }

  .pdbutton {
    width: 100%;
    padding-top: 20px;
    text-align: center;
    display: flex;
  }

  .pdbutton button {
    margin: 20px;
  }

  .buyer {
    margin-top: 10px;
    font-size: 18px;
    color: #222;
    line-height: 50px;
    margin-top: 20px;
    border-bottom: 1px solid #e5e9ef;
  }

  .pagesmall {
    float: right;
    font-size: 14px;
    color: #666;
    cursor: pointer;
    margin-right: 10px;
    margin-top: 10px;
  }

  .pagesmall:hover {
    color: #00A1D6;
  }

  .pagebig {
    margin-top: 15px;
    margin-bottom: 10px;
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

  .leaveMessage {
    width: 100%;
    height: 200px;
    padding-bottom: 20px;
    border-bottom: 1px solid #EEEEEE;
  }

  .leaveMessage .meimg {
    height: 100%;
    width: 15%;
    float: left;
  }

  .meimg img {
    height: 60px;
    width: 60px;
    float: left;
    margin-left: 40px;
    margin-top: 70px;
    border-radius: 50%;
  }

  .leaveMessage textarea {
    width: 80%;
    height: 100px;
    padding: 10px;
    font-size: 13px;
    color: #555;
    border-radius: 10px;
    margin-top: 30px;
    outline: none;
    resize: none;
    background-color: #f4f5f7;
    border: 1px solid #e5e9ef;
  }

  .leaveMessage textarea:hover {
    background-color: #FFFFFF;
    border: 1px solid #00a1d6;
  }

  .leaveMessageBtn {
    width: 83%;
    margin-left: 15%;
  }

  .leaveMessageBtn button {
    width: 70px;
    height: 30px;
    float: right;
    margin-top: 5px;
    color: #fff;
    outline: none;
    cursor: pointer;
    border-radius: 4px;
    margin-right: 10px;
    border: 1px solid #00a1d6;
    background-color: #00a1d6;
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .ListOfUserGoods{
    margin-top: 15px;
    margin-left: 25px;
    font-size: 14px;
    color: #888;
    background-color: #FFFFFF;
    border-radius: 8px;
    padding: 4px 10px;

  }
  .ListOfUserGoods li{
    margin-top: 20px;
  }

  .buyPro{
    padding: 15px 0;
    margin-bottom: 10px;
    border: 1px solid #eeeeee;
  }
  .buyPro img{
    height: 120px;
    width: 120px;
  }
  .buyPro p{
    margin-top: 0;
    font-size: 18px;
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
  }
  .infoTop p{
    text-align: right;
  }
  .addressInfo{
    height: 40px;
    margin-top: 20px;
    border-bottom: 1px solid #EEEEEE;
  }
  .btnClick{
    margin-top: 20px;
  }
</style>
