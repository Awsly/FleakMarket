<template>
  <div class="containerType">
    <HeaderTop></HeaderTop>
    <Search></Search>
    <el-row>
      <el-col :span="16" :offset="4">

        <el-row class="line">
          <el-col :span="24">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item>全部结果</el-breadcrumb-item>
              <el-breadcrumb-item>{{Type.name}}</el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
        </el-row>

        <el-row class="typeItem line">
          <el-col :span="2">类别</el-col>
          <el-col :span="20">
            <ul>
              <li @click="typeChange(0)" v-bind:class="ids.length == 0?activeclass:''">全部</li>
              <li @click="typeChange(item.id)" v-bind:class="ids.indexOf(item.id)>-1?activeclass:''" v-for="item in typeItem">{{item.name}}</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeItem line">
          <el-col :span="2">价格</el-col>
          <el-col :span="20">
            <ul>
              <li @click="priceChange(0)" v-bind:class="Object.keys(this.priceInterval).length == 0?activeclass:''">全部</li>
              <li @click="priceChange(item)" v-bind:class="item.min == priceInterval.min?activeclass:''"  v-for="item in typePrice">{{item.min}}-{{item.max}}</li>
              <li @click="priceChange(-1)" v-bind:class="priceInterval.max > 1000?activeclass:''">1000以上</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeItem line">
          <el-col :span="2">上架时间</el-col>
          <el-col :span="20">
            <ul>
              <li @click="timeChange(0)" v-bind:class="Object.keys(this.timeInterval).length == 0?activeclass:''">全部</li>
              <li @click="timeChange(item)" v-bind:class="item.min == timeInterval.min?activeclass:''"  v-for="item in typeTime">{{item.text}}</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeProduct">
          <el-col :span="24">
            <ul>
              <li v-for="item in typeProducts">
                <div class="imgbox"><img :src="item.images[0]" :alt="item.name" @click="gotoDetails(item.id)"></div>
                <p class="productPrice">¥<span class="price">{{item.currentprice}}</span> </p>
                <p class="productName" @click="gotoDetails(item.id)">{{item.name}}</p>
                <div class="userPlay">
                  <span>评价 {{item.comments}}</span>
                  <span><button>加入购物车</button></span>
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
        typeTime:[],//时间区间数组
        ids: [],//二级分类id列表
        priceInterval: {},//商品价格区间
        timeInterval: {},//时间范围区间
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
      //加载时间区间
      let array = [{number:1,text:"刚刚发布"},{number:7,text:"7天以内"},{number:30,text:"30天以内"},{number:60,text:"60天以内"},{number:90,text:"90天以内"},{number:365,text:"一年以内"}]
      array.forEach((item,index) =>{
        let myDate = new Date();
        let maxtime = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
        myDate.setTime(myDate.getTime()-(item.number*24*60*60*1000));
        let mintime = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
        this.typeTime.push({min:mintime,max:maxtime,text:item.text})
      })
      //商品数据加载
      this.loadProducts();
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
      /**
       * 按条件加载商品列表数据
       */
      loadProducts(){
        //加载所属二级分类商品
        this.$axios.post('/product/selectScProductById', {
          id:this.$route.query.id,
          ids:this.ids,
          priceInterval:this.priceInterval,
          timeInterval:this.timeInterval
        }).then(res => {
          //设置图片为商品首张图片
          res.data.forEach(res => {
            if(res.images != null){
              res.images = eval('('+res.images+')');
            }
          });
          this.typeProducts = res.data;
          console.log(res.data);
        })
      },
      /**
       * 选择查询商品的二级分类
       * @param {Object} id
       */
      typeChange(id){
        let isHave = false;
        if(id != 0){
          this.ids.forEach((item,index) =>{
            if(item == id){
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
        if(item == 0){
          this.priceInterval = {};
        }else if(item == -1){
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
        if(item == 0){
          this.timeInterval = {};
        }else{
          this.timeInterval = item;
        }
        this.loadProducts();
      }


    }
  }
</script>

<style scoped="scoped">
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
  .active{
      color: #FFFFFF !important;
      background-color: #FF8800;
  }

  .line{
    padding-bottom: 10px;
    border-bottom: 1px solid #EEEEEE;
  }
  .typeProduct li{
    width: calc(20% - 8px);
    margin: 10px 4px;
    height: 350px;
    overflow: hidden;
    float: left;
    border-radius: 10px;
    transition: all 0.2s;
    text-align: center;
    text-align-last: left;
    background-color: #FFFFFF;
    box-shadow: 0 1px 4px #E0E0E0;
  }
  .typeProduct li:hover{
    box-shadow: 0px 1px 10px #999999;
    transform: translateY(-0.5%);
  }
  .image{
    width: 250px;
    height: 350px;
    background-color: #000000;
  }
  .typeProduct li .imgbox{
    height: 66%;
    padding: 10px;
  }
  .typeProduct li img{
    width: 100%;
    height: 100%;
    margin-bottom: 72%;
    margin: auto;
    cursor: pointer;
  }
  .typeProduct li p{
    margin: 0;
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
</style>
