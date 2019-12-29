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
              <li v-for="item in typeItem">{{item}}</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeItem line">
          <el-col :span="2">价格</el-col>
          <el-col :span="20">
            <ul>
              <li v-for="item in typePrice">{{item}}</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeItem line">
          <el-col :span="2">上架时间</el-col>
          <el-col :span="20">
            <ul>
              <li v-for="item in typeTime">{{item}}</li>
            </ul>
          </el-col>
        </el-row>
        <el-row class="typeProduct">
          <el-col :span="24">
            <ul>
              <li v-for="item in typeProducts">
                <img :src="item.imgPath" :alt="item.name" @click="gotoDetails(item)">
                <p class="productPrice">¥<span class="price">{{item.price}}</span>.00 </p>
                <p class="productName" @click="gotoDetails(item)">{{item.name}}</p>
                <div class="userPlay">
                  <span>评价 999+</span>
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
        Type:'',
        typeItem:['电脑','手机','相机','键盘','鼠标','耳机','风扇','台灯','iPad','充电宝','无人机','手机充电线'],
        typePrice:['0-100','100-200','200-300','300-400','400-500','500-600','600-700','700-800','800-900','900-1000','1000以上'],
        typeTime:['刚刚发布','最近一周','最近一月','最近一年','其他'],
        typeProducts:[
        {id:1,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:2,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:3,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:4,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:5,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:6,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:7,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:8,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'},
        {id:9,name:'数码宝贝球',price:188,love:188,imgPath:'../../static/img/product/bj.jpg'}]
      }
    },
    mounted() {
      // 数据加载
      this.Type = JSON.parse(localStorage.getItem("menu"));
    },
    methods: {
      gotoDetails(){
        this.$router.push({ name: 'productDetails' });
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
    margin: 0 10px;
    font-size: 12px;
    padding: 2px 3px;
    border-radius: 3px;
    transition: all 0.1s;
    color: #000000;
  }
  .typeItem ul li:hover{
    cursor: pointer;
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
  .typeProduct li img{
    width: 100%;
    height: 70%;
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
