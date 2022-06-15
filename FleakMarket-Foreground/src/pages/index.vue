<template>
  <div class="containerIndex">
    <HeaderTop></HeaderTop>
    <Search style="margin-top: 25px;"></Search>
    <el-backtop></el-backtop>
    <!-- 幻灯片-->
    <el-row class="slide">
      <el-col :span="16" :offset="4">
        <!-- 一级列表-->
        <ul class="slideUl">
          <li v-for="item in fc" :key="item.id" @click="gotoType(item.id)">{{item.name}}<span class="slideUlclick"></span></li>
        </ul>
        <div class="block">
          <el-carousel height="500px" arrow="never" :interval="3000" trigger="click">
            <el-carousel-item v-for="item in Slide" :key="item">
              <img :src="item" alt="">
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-col>
    </el-row>

    <el-divider></el-divider>
    <!-- 热门商品-->
    <el-row class="hot">
      <el-col :span="4" :offset="4">
        <h2>热门商品</h2>
      </el-col>
      <el-col class="center" :span="16" :offset="4">
        <div class="hot-product">
          <ul>
            <li v-for="hotpro in hotpros" :key="hotpro.id">
              <p :style="{borderLeft: '4px '+hotpro.color}">{{hotpro.name}}</p>
              <div :style="{borderTop: '1px '+hotpro.color}">
                <ul>
                  <li v-for="item in hotpro.item" @click="gotoDetails(item.id)" :key="item.id">
                    <span style="margin-right: 10px;">{{item.name}}</span>
                    <span style="float: right;">({{item.creattime}})</span>
                  </li>
                </ul>
              </div>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>


    <el-divider v-if="this.newpro!==''"></el-divider>
    <!-- 最新上架-->
    <el-row class="new" v-if="this.newpro!==''">
      <el-col :span="4" :offset="4">
        <h2>最新上架</h2>
      </el-col>
      <el-col class="center" :span="16" :offset="4">
        <ul class="typeProduct">
          <li v-for="item in newpro" :key="item.id">
            <div class="containerProduct">
              <div class="imgbox"><img :src="item.images[0]" :width="imgwidth" :alt="item.name" @click="gotoDetails(item.id)"></div>
              <div class="text" style="position: relative;bottom: 0;height: 50px;">
                <p class="productPrice">¥<span class="price">{{item.currentprice}}</span> </p>
                <p class="productName" @click="gotoDetails(item.id)">{{item.name}}</p>
                <div class="userPlay">
                  <span style="font-size: 12px;color:#bbbcc2;">{{item.creattime}}</span>
                  <span><button @click="addLove(item.id,item.uid)">点击收藏</button></span>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </el-col>
    </el-row>


    <el-divider v-if="this.recommendpro!==''"></el-divider>
    <!-- 推荐商品-->
    <el-row class="new" v-if="this.recommendpro!==''">
      <el-col :span="4" :offset="4">
        <h2>推荐商品</h2>
      </el-col>
      <el-col class="center" :span="16" :offset="4">
        <ul class="typeProduct">
          <li v-for="item in recommendpro" :key="item.id">
            <div class="containerProduct">
              <div class="imgbox"><img :src="item.images[0]" :width="imgwidth" :alt="item.name" @click="gotoDetails(item.id)"></div>
              <div class="text" style="position: relative;bottom: 0;height: 50px;">
                <p class="productPrice">¥<span class="price">{{item.currentprice}}</span> </p>
                <p class="productName" @click="gotoDetails(item.id)">{{item.name}}</p>
                <div class="userPlay">
                  <span style="font-size: 12px;color:#bbbcc2;">{{item.creattime}}</span>
                  <span><button @click="addLove(item.id,item.uid)">点击收藏</button></span>
                </div>
              </div>
            </div>
          </li>
        </ul>
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
  data () {
    return {
      Slide: ["https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/a38b1d56409913d5dc869023ade36d94.jpg?thumb=1&w=1226&h=460&f=webp&q=90",
        "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/6bd4174b8c5aad67a64864a5716ad152.jpg?thumb=1&w=1226&h=460&f=webp&q=90",
        "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0a066d0bd20d037cc6f4210e16f65ab3.jpg?w=2452&h=920"],
      count: 1,
      seen: false,
      current: 0,
      imgwidth: "30px",
      fc: '',
      hotpros: [{//热门商品
        "id": "1",
        "name": "教材、教辅",
        "color": "solid #FF7F24",
        "item":''
      }, {
        "id": "2",
        "name": "手机、电脑",
        "color": "solid #4169E1",
        "item":''
      }, {
        "id": "3",
        "name": "自行车、电动车",
        "color": "solid #48D1CC",
        "item":''
      }, {
        "id": "4",
        "name": "书桌、书架",
        "color": "solid #FF0000",
        "item":''
      }],
      newpro: '',//最新商品
      recommendpro: ''//推荐商品
    };
  },
  created() {
    //加载一级分类目录
    this.$axios.get('/classify/flnb', {
      params: {}
    }).then(res => {
      this.fc = res.data;
    });

    //加载热门商品
    let arrayHot = [1,2,3,4];
    arrayHot.forEach((item,index,arr) =>{
      this.$axios.get('/product/selectByHot', {
        params: {typeid: item}
      }).then(res => {
        this.hotpros[index].item = res.data;
      })
    });
    //加载最新上架商品
    this.$axios.get('/product/selectByNew', {
      params: {}
    }).then(res => {
      res.data.forEach((res,index) => {
        if(res.images != null){
          res.images = eval('('+res.images+')');
        }
      });
      this.newpro = res.data;
    });
    //加载官方推荐商品
    this.$axios.get('/product/selectByCommend', {
      params: {}
    }).then(res => {
      res.data.forEach((res,index) => {
        if(res.images != null){
          res.images = eval('('+res.images+')');
        }
      });
      this.recommendpro = res.data;
    });
    //点击量+1
    this.clickNum();
  },
  methods: {
    //跳转商品详情页面
    gotoDetails (pid) {
      this.$router.push({
        name: 'productDetails',
        query: {
          id: pid
        }
      });
    },
    //跳转商品分类页面
    gotoType (id) {
      this.$router.push({
        name: 'productType',
        query: {
          id: id
        }
      });
    },
    //添加商品进收藏方法
    addLove(id,uid){
      if(this.$store.state.userid !== 0){
        //判断是否是自己的商品
        if(this.$store.state.userid === uid){
          this.$message({
            message: '不能收藏自己发布的商品！',
            type: 'warning'
          });
          return null;
        }
        //判断商品是否添加
        this.$axios.post('/product/selectLoveById', {
          uid: this.$store.state.userid,
          pid: id
        }).then(res => {
          if(res.data < 1){
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
              }
            })
          }else{
            this.$message({
              message: '请勿重复收藏！',
              type: 'warning'
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
            clickNum: (res.data.clickNum+1)
          });
        }
      });
    }
  }
}
</script>

<style scoped="scoped">
@import url("../css/index.css");
</style>
