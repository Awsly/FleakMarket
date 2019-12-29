<template>
  <div class="containerIndex">
    <HeaderTop></HeaderTop>
    <!-- logo搜索栏-->
    <el-row class="logo">
      <el-col :span="1" :offset="4">
        <img src="../../static/img/logo.png" />
      </el-col>
      <el-col class="serach" :span="4" :offset="11">
        <input type="text" /><button>搜索</button>
      </el-col>
    </el-row>

    <!-- 导航栏-->
    <el-row class="sortlist">
      <el-col :span="16" :offset="4">
        <div>
          <ul>
            <li v-for="navigation in navigations" :key="navigation.id">
              <span @click="gotoType(navigation)">{{navigation.name}}</span>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>

    <el-row class="slide">
      <el-col :span="4" :offset="4">
        <ul>
          <li>1</li>
          <li>2</li>
          <li>3</li>
          <li>4</li>
          <li>5</li>
        </ul>
      </el-col>
      <el-col :span="12">
        <div class="block">
            <el-carousel trigger="click">
              <el-carousel-item v-for="item in Slide" :key="item">
                <img :src="item" alt="">
              </el-carousel-item>
            </el-carousel>
          </div>

      </el-col>
    </el-row>

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
                  <li v-for="item in hotpro.item" :key="item.id">
                    <span @click="gotoDetails(item.pid)">{{item.text}}({{item.date}})</span>
                  </li>
                </ul>
              </div>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>


    <!-- 最新上架-->
    <el-row class="new">
      <el-col :span="4" :offset="4">
        <h2>最新上架</h2>
      </el-col>
      <el-col class="center" :span="16" :offset="4">
        <ul class="list">
          <li v-for="item in newpro" class="list-item" :key="item.id" @click="gotoDetails(item.id)">
            <img :src="item.img" :width="imgwidth" />
            <p class="list-text">{{item.text}}</p>
            <p class="list-money">¥{{item.money}}</p>
          </li>
        </ul>
      </el-col>
    </el-row>
    <FooterBottom></FooterBottom>
  </div>
</template>

<script>
  import HeaderTop from "../components/Header.vue";
  import FooterBottom from "../components/Footer.vue";

  export default {
    components: {
      HeaderTop,
      FooterBottom
    },
    data() {
      return {
        Slide: ["https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/a38b1d56409913d5dc869023ade36d94.jpg?thumb=1&w=1226&h=460&f=webp&q=90",
         "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/6bd4174b8c5aad67a64864a5716ad152.jpg?thumb=1&w=1226&h=460&f=webp&q=90",
         "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0a066d0bd20d037cc6f4210e16f65ab3.jpg?w=2452&h=920"],

        count: 1,
        seen: false,
        current: 0,
        imgwidth: "30px",
        navigations: [{
            "id": 1,
            "name": "首页",
            "path": "index"
          },
          {
            "id": 2,
            "name": "书籍资料",
            "path": "book"
          },
          {
            "id": 3,
            "name": "学习用品",
            "path": "study"
          },
          {
            "id": 4,
            "name": "电子设备",
            "path": "electronic"
          },
          {
            "id": 5,
            "name": "日常家居",
            "path": "everyday"
          },
          {
            "id": 6,
            "name": "其他",
            "path": "other"
          },
        ],
        hotpros: [{
          "id": "1",
          "name": "考研、课本",
          "color": "solid #FF7F24",
          "item": [{
              "pid": 1,
              "text": "2019考研资料出售",
              "date": "2019.09.09"
            },
            {
              "pid": 2,
              "text": "出售高数线代离散数学课本，计本专业大一第一学期",
              "date": "2019.12.19"
            },
            {
              "pid": 3,
              "text": "出售一本三体小说实体书",
              "date": "2016.02.02"
            },
            {
              "pid": 4,
              "text": "收购一本中国近现代史纲要课本",
              "date": "2018.06.22"
            }
          ]
        }, {
          "id": "2",
          "name": "手机、电脑",
          "color": "solid #4169E1",
          "item": [{
              "pid": 1,
              "name":"联想小新 Air 13 Pro",
              "text": "刚买的电脑不想要了，低价处理，质量有保障！",
              "date": "2019.09.09"
            },
            {
              "pid": 2,
              "name":"小米8",
              "text": "刚买的手机不想要了，高价处理，质量没保障！",
              "date": "2019.09.09"
            },
            {
              "pid": 3,
              "name":"达尔优机械键盘",
              "text": "达尔优机械键盘，青轴，八成新，原价820，低价处理。",
              "date": "2019.09.09"
            },
            {
              "pid": 4,
              "name":"收购影音本",
              "text": "收购一台能看电影听音乐的笔记本，方便携带就好。",
              "date": "2019.09.09"
            }
          ]
        }, {
          "id": "3",
          "name": "自行车、电动车",
          "color": "solid #48D1CC",
          "item": [{
              "pid": 1,
              "text": "出售山地自行车一辆，八成新",
              "date": "2019.09.09"
            },
            {
              "pid": 2,
              "text": "出售电动车，可载人，价格好商量。",
              "date": "2019.09.09"
            },
            {
              "pid": 3,
              "text": "出售一辆共享单车，免费赠送。",
              "date": "2019.09.09"
            },
            {
              "pid": 4,
              "text": "2019考研资料出售",
              "date": "2019.09.09"
            }
          ]
        }, {
          "id": "4",
          "name": "书桌、台灯",
          "color": "solid #FF0000",
          "item": [{
              "pid": 1,
              "text": "2019考研资料出售",
              "date": "2019.09.09"
            },
            {
              "pid": 2,
              "text": "2019考研资料出售",
              "date": "2019.09.09"
            },
            {
              "pid": 3,
              "text": "2019考研资料出售",
              "date": "2019.09.09"
            },
            {
              "pid": 4,
              "text": "2019考研资料出售",
              "date": "2019.09.09"
            }
          ]
        }],
        newpro: [{
            id: 1,
            text: "海飞丝 清爽去油 洗发露",
            money: "20",
            img: "../../static/img/product/hfs.jpg"
          },
          {
            id: 2,
            text: "罗技G G502 RGB 12000DPI 光电 11个",
            money: "20",
            img: "../../static/img/product/lj.jpg"
          },
          {
            id: 3,
            text: "Logitech/罗技 MK275 USB USB",
            money: "20",
            img: "../../static/img/product/lujp.png"
          },
          {
            id: 4,
            text: "源氏木语实木书桌简约现代书房写字台",
            money: "20",
            img: "../../static/img/product/muzhuo.jpg"
          },
          {
            id: 5,
            text: "钢笔",
            money: "20",
            img: "../../static/img/product/gb.jpg"
          },
          {
            id: 6,
            text: "折叠椅",
            money: "20",
            img: "../../static/img/product/zdy.jpg"
          },
          {
            id: 7,
            text: "java学习笔记",
            money: "20",
            img: "../../static/img/product/bj.jpg"
          },
          {
            id: 8,
            text: "Photoshop全套视频教程",
            money: "20",
            img: "../../static/img/product/Photoshop.jpg"
          },
          {
            id: 9,
            text: "头戴式耳机",
            money: "20",
            img: "../../static/img/product/ej.jpg"
          },
          {
            id: 10,
            text: "滑板",
            money: "20",
            img: "../../static/img/product/hb.jpg"
          },
          {
            id: 11,
            text: "篮球",
            money: "20",
            img: "../../static/img/product/lq.jpg"
          },
          {
            id: 12,
            text: "《蜘蛛侠》电影票",
            money: "20",
            img: "../../static/img/product/dyp.jpg"
          },
          {
            id: 13,
            text: "图书馆出入证（租赁）",
            money: "20",
            img: "../../static/img/product/crz.jpg"
          },
          {
            id: 14,
            text: "出租西服西裤",
            money: "20",
            img: "../../static/img/product/xf.jpg"
          },
          {
            id: 15,
            text: "头戴式耳机",
            money: "20",
            img: "../../static/img/product/ej.jpg"
          }
        ]
      };
    },
    computed: {},
    methods: {
      gotoDetails(pid) {
        this.$router.push({
          name: 'productDetails',
          params: {
            id: pid
          }
        })
      },
      gotoType(type){

        if(type.path == 'index'){
          this.$router.go(0);
        }else{
          localStorage.setItem("menu",JSON.stringify(type));
          this.$router.push({ name: 'productType' });
        }
      }
    }
  }
</script>

<style scoped="scoped">
  @import url("../css/index.css");
</style>
