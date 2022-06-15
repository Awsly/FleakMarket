<template>
  <div>
    <HeaderTop></HeaderTop>
    <el-row class="containerType">
      <el-col :span="16" :offset="4">
        <el-row class="line">
          <el-col :span="24">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item>全部结果</el-breadcrumb-item>
              <el-breadcrumb-item>{{Type===''?'查询结果':Type.name}}</el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
        </el-row>

        <el-row class="typeItem line">
          <el-col :span="2">类别</el-col>
          <el-col :span="20">
            <ul>
              <li @click="typeChange(0)" v-bind:class="ids.length === 0?activeclass:''">全部</li>
              <li v-if="typeItem.length===0">查询结果</li>
              <li @click="typeChange(item.id)" v-bind:class="ids.indexOf(item.id)>-1?activeclass:''" v-for="item in typeItem" :key="item.id">{{item.name}}</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeItem line">
          <el-col :span="2">价格</el-col>
          <el-col :span="20">
            <ul>
              <li @click="priceChange(0)" v-bind:class="Object.keys(this.priceInterval).length === 0?activeclass:''">全部</li>
              <li @click="priceChange(item)" v-bind:class="item.min === priceInterval.min?activeclass:''"  v-for="item in typePrice" :key="item.min">{{item.min}}-{{item.max}}</li>
              <li @click="priceChange(-1)" v-bind:class="priceInterval.max > 1000?activeclass:''">1000以上</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeItem line">
          <el-col :span="2">上架时间</el-col>
          <el-col :span="20">
            <ul>
              <li @click="timeChange(0)" v-bind:class="this.timeInterval === 0?activeclass:''">全部</li>
              <li @click="timeChange(item.number)" v-bind:class="item.number === timeInterval?activeclass:''"  v-for="item in typeTime" :key="item.text">{{item.text}}</li>
            </ul>
          </el-col>
        </el-row>
        <!-- 无商品数据时展示信息-->
        <el-row v-if="typeProducts.length===0">
          <el-col :span="22" class="noData"></el-col>
        </el-row>
        <el-row class="typeProduct">
          <el-col :span="24">
            <ul>
              <li v-for="item in typeProducts" :key="item.id">
                <div class="containerProduct">
                  <div class="imgbox"><img :src="item.images[0]" :alt="item.name" @click="gotoDetails(item.id)"></div>
                  <p class="productPrice">¥<span class="price">{{item.currentprice}}</span> </p>
                  <p class="productName" @click="gotoDetails(item.id)">{{item.name}}</p>
                  <div class="userPlay">
                    <span>评价 {{item.comments}}</span>
                    <span>
                    <button v-if="item.deal==='线上交易'" @click="addCart(item)">加入购物车</button>
                    <button v-else @click="showUserInfo(item.uid)">联系卖家</button>
                    </span>
                  </div>
                </div>
              </li>
            </ul>
          </el-col>
        </el-row>

      </el-col>
    </el-row>
    <FooterBottom></FooterBottom>
  </div>
</template>

<script>
  import HeaderTop from "../components/Header.vue";
  import Search from "../components/Search.vue";
  import FooterBottom from "../components/Footer.vue";
  import moment from "moment";

  export default {
    components: {
      HeaderTop,
      Search,
      FooterBottom
    },
    data() {
      return {
        Type:'',//一级分类
        typeItem:'',//二级分类
        typePrice:[{min:0,max:100},{min:100,max:200},{min:200,max:300},{min:300,max:400},{min:400,max:500},{min:500,max:600},{min:600,max:700},{min:700,max:800},{min:800,max:900},{min:900,max:1000}],//价格区间数组
        typeTime:[{number:1,text:"刚刚发布"},{number:7,text:"7天以内"},{number:30,text:"30天以内"},{number:60,text:"60天以内"},{number:90,text:"90天以内"},{number:365,text:"一年以内"}],//时间区间数组
        ids: [],//二级分类id列表
        priceInterval: {},//商品价格区间
        timeInterval: 0,//时间参数
        typeProducts:'',//商品列表
      }
    },
    created() {
      // 加载一级分类项
      this.$axios.get('/classify/selectFcById', {
        params: {id : this.$route.query.id}
      }).then(res => {
        this.Type = res.data;
      })
      //加载二级分类项
      this.$axios.get('/classify/selectListScById', {
        params: {id : this.$route.query.id}
      }).then(res => {
        this.typeItem = res.data;
      })
      //商品数据加载
      this.loadProducts();
      //点击量+1
      this.clickNum();
    },
    computed:{
      activeclass: function() {
          return 'active'
      }
    },
    methods: {
      /**
       * 跳转商品详情页面
       * @param {Object} pid
       */
      gotoDetails(pid){
        this.$router.push({
          name: 'productDetails' ,
          query:{
            id: pid
          }
        });
      },
      //按条件加载商品列表数据
      loadProducts(){
        //加载所属二级分类商品
        this.$axios.post('/product/selectScProductById', {
          id:this.$route.query.id,
          ids:this.ids,
          priceInterval:this.priceInterval,
          timeInterval:this.timeInterval,
          serachText:this.$route.query.serachText
        }).then(res => {
          //设置图片为商品首张图片
          res.data.forEach(res => {
            if(res.images != null){
              res.images = eval('('+res.images+')');
            }
          });
          this.typeProducts = res.data;
        })
      },
      /**
       * 选择查询商品的二级分类
       * @param {Object} id
       */
      typeChange(id){
        let isHave = false;
        if(id !== 0){
          this.ids.forEach((item,index) =>{
            if(item === id){
              isHave =true;
              this.ids.splice(index,1);
            }
          })
          if(isHave){
          }else{
            this.ids.push(id);
          }
        }else{
          this.ids = [];
        }
        this.loadProducts();
      },
      /**
       * 选择查询商品的价格区间
       * @param {Object} item
       */
      priceChange(item){
        if(item === 0){
          this.priceInterval = {};
        }else if(item === -1){
          this.priceInterval = {min:1000,max:99999};
        }else{
          this.priceInterval = item;
        }
        this.loadProducts();
      },
      /**
       * 选择查询商品的时间区间
       * @param {Object} item
       */
      timeChange(item){
        if(item === 0){
          this.timeInterval = 0;
        }else{
          this.timeInterval = item;
        }
        this.loadProducts();
      },
      //添加商品进入购物车
      addCart(data){
      //查找购物车是否存在商品
        if(this.$store.state.userid !== 0){
          //判断是否是自己的商品
          if(this.$store.state.userid === data.uid){
            this.$message({
              message: '不能购买自己发布的商品！',
              type: 'warning'
            });
            return null;
          }
          //判断商品是否下架
          if(data.status === '2'){
            this.$message({
              message: '商品已出售！',
              type: 'warning'
            });
            return null;
          }
          this.$axios.post('/cart/selectCartProductById',{
            pid:data.id,
            uid:this.$store.state.userid
          }).then(res => {
            if(res.data === 0){
              //添加商品进入购物车
              this.$axios.post('/cart/insertCartById',{
                pid:data.id,
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
      //统计点击量
      clickNum(){
        console.log("商品分类");
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
      }
    }
  }
</script>

<style scoped="scoped">
  .containerType{
   margin-top: 60px;
  }
  .typeItem,.typeProduct{
    margin: 20px;
    color: #999;
    font-size: 12px;
  }

  .typeItem ul li{
    float: left;
    line-height: 16px;
    margin: 0 10px;
    font-size: 12px;
    padding: 2px 3px;
    border-radius: 3px;
    transition: all 0.1s;
    color: #000000;
    cursor: pointer;
  }
  .typeItem ul li:hover{
    color: #FFFFFF;
    background-color: #FF8800;
  }

  .line{
    padding-bottom: 10px;
    border-bottom: 1px solid #EEEEEE;
  }
  .typeProduct li{
    width: calc(20% - 8px);
    margin: 10px 4px;
    float: left;
    border-radius: 10px;
    background-color: #FFFFFF;
    padding-bottom: 10px;
  }

  .containerProduct{
    text-align: center;
    text-align-last: left;
    width: 100%;
    height: 270px;
    margin: auto;
    border-radius: 5px;
    transition: all 0.2s;
    box-shadow: 0 1px 5px #999999;
  }

  .typeProduct .containerProduct:hover{
    box-shadow: 0 1px 10px #999999;
    transform: translateY(-0.5%);
  }

  .typeProduct li .imgbox{
    height: 180px;
  }
  .typeProduct li img{
    width: 100%;
    height: 100%;
    margin: auto;
    cursor: pointer;
  }
  .typeProduct li p{
    margin: 5px 10px;
    line-height: 20px;
  }
  .productPrice{
    color: #FF5500;
    font-weight: 700;
  }
  .productPrice .price{
    font-size: 16px !important;
  }
  .productName{
    color: #555555;
    font-size: 14px;
    cursor: pointer;
    width: 80%;
    overflow: hidden;
    /*超出部分隐藏*/
    text-overflow: ellipsis;
    /* 超出部分显示省略号 */
    white-space: nowrap;
    /*规定段落中的文本不进行换行 */
  }
  .userPlay{
    width: 100%;
    height: 20px;
    margin-top: 15px;
  }
  .userPlay span{
    margin: 0 10px;
    cursor: pointer;
  }
  .userPlay button{
    float: right !important;
    font-size: 12px;
    color: #FFFFFF;
    background-color: #FF5500;
    margin: 0 10px;
    cursor: pointer;
  }
  .noData{
    text-align: center;
    padding-top: 350px;
    background-image: url(../../static/img/noData/nodata02.png);
    background-repeat:no-repeat;
    background-position: center ;
  }
</style>
