<template>
  <div class="bodyd">
    <HeaderTop></HeaderTop>
    <Search></Search>

    <el-row class="containerDetails" style="float: left;padding-left: 20%;">
      <el-col :span="14">
        <div class="pro">
          <div class="pdtime">发布于 : 2021-09-02 12:18:32 200次浏览 10人想要</div>
          <!-- 商品区域 -->
          <el-row>
            <el-col :span="10">
              <div class="pimg">
                <img :src="imgblur" alt="图片呢">
              </div>
              <div class="pimgs">
                <ul>
                  <li><img :src="data.img[0]" alt="图片呢"></li>
                  <li><img :src="data.img[1]" alt="图片呢"></li>
                  <li><img :src="data.img[2]" alt="图片呢"></li>
                  <li><img :src="data.img[3]" alt="图片呢"></li>
                  <li><img :src="data.img[4]" alt="图片呢"></li>
                </ul>
              </div>
            </el-col>
            <el-col :span="14" class="pdetails">
              <div>
                <div class="pdname">{{data.name}}</div>
                <div class="newProduct">新品发布</div>
              </div>
              <table class="pd">
                <tr>
                  <td>价格</td>
                  <td class="pdprice">¥{{data.price}}</td>
                </tr>
                <tr>
                  <td>商品原价</td>
                  <td style="color: #999999;">¥<s>{{data.originalPrice}}</s></td>
                </tr>
                <tr>
                  <td>交易地址</td>
                  <td>{{data.address}}</td>
                </tr>
                <tr>
                  <td>商品状态</td>
                  <td>{{data.status == 1?"未出售":"已出售"}}</td>
                </tr>
                <tr>
                  <td>交易方式</td>
                  <td>{{data.paytype}}</td>
                </tr>
                <tr>
                  <td>卖家</td>
                  <td>王大锤</td>
                </tr>
              </table>
              <div class="pdbutton">
                <el-button type="success">联系卖家</el-button>
                <el-button type="danger" icon="el-icon-edit">收藏</el-button>
              </div>
            </el-col>
          </el-row>

          <!-- 买家区域 -->
          <el-row class="buyer">
            <el-col>商品描述</el-col>
          </el-row>
          <el-row class="buyer-describe">
            <p>良品铺子 鸡肉类 奥尔良小鸡腿 108gX1袋装 肉类熟食卤味办公室鸡肉类零食小吃食品其他小包

              良品铺子 鸡肉类 奥尔良小鸡腿 108gX1袋装 肉类熟食卤味办公室鸡肉类零食小吃食品其他小包装</p>
          </el-row>

          <div class="buyer">
            <span>891条评论</span>
            <div class="pagesmall"><i class="el-icon-refresh-right"></i>刷新</div>
          </div>
          <ul>
            <li>
              <el-row class="buyer-describe">
                <div class="buyer-headerimg">
                  <img src="../../static/img/slide1.jpg" />
                </div>
                <div class="buyer-sayed">
                  <div class="user">用户名</div>
                  <div class="buyer-text">hahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahah</div>
                  <div class="buyer-date">2019.12.09 9:28</div>
                </div>
              </el-row>
            </li>
            <li>
              <el-row class="buyer-describe">
                <div class="buyer-headerimg">
                  <img src="../../static/img/slide1.jpg" />
                </div>
                <div class="buyer-sayed">
                  <div class="user">用户名</div>
                  <div class="buyer-text">hahahahahhahahahahhahahahahhahahahahhahahahahhahah
                    ahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahah
                    ahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahah
                    ahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahah
                    hahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahah
                  </div>
                  <div class="buyer-date">2019.12.09 9:28</div>
                </div>
              </el-row>
            </li>
            <li>
              <el-row class="buyer-describe">
                <div class="buyer-headerimg">
                  <img src="../../static/img/slide1.jpg" />
                </div>
                <div class="buyer-sayed">
                  <div class="user">用户名</div>
                  <div class="buyer-text">hahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahahhahahahah</div>
                  <div class="buyer-date">2019.12.09 9:28</div>
                </div>
              </el-row>
            </li>
            <li class="pagebig">
              <div class="buyer-headerimg">
              </div>
              <el-pagination background layout="total,prev, pager, next,jumper" prev-text=" 上一页 " next-text=" 下一页 "
                :total="total">
              </el-pagination>
            </li>
          </ul>

          <div class="leaveMessage">
            <div class="meimg"><img src="../../static/img/icon/QQ.jpg" alt=""></div>
            <textarea :style="backcolor" maxlength="200" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" @mouseenter="enter"
              @mouseleave="leave" v-model="textarea"></textarea>
            <div class="leaveMessageBtn">
              <button>提交</button>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="4">
        <ul class="ListOfUserGoods">
          <li>该用户的其他商品:</li>
          <li>
            <el-card :body-style="{ padding: '0px' }">
              <img  src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
              <div style="padding: 7px;">
                <span>烤面筋</span>
                <div class="bottom clearfix">
                  <span>$100</span>
                  <el-button type="text" class="button">前往查看</el-button>
                </div>
              </div>
            </el-card>
          </li>
          <li>
            <el-card :body-style="{ padding: '0px' }">
              <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
              <div style="padding: 14px;">
                <span>好吃的汉堡</span>
                <div class="bottom clearfix">
                  <span>$100</span>
                  <el-button type="text" class="button">前往查看</el-button>
                </div>
              </div>
            </el-card>
          </li>
          <li>
            <el-card :body-style="{ padding: '0px' }">
              <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
              <div style="padding: 14px;">
                <span>好吃的汉堡</span>
                <div class="bottom clearfix">
                  <span>$100</span>
                  <el-button type="text" class="button">前往查看</el-button>
                </div>
              </div>
            </el-card>
          </li>
        </ul>
      </el-col>
    </el-row>

    <FooterBottom></FooterBottom>
  </div>
</template>

<script type="text/javascript">
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
        data: {},
        imgblur: '',
        currentPage: 1,
        total: 100,
        layout: 'slot,pager,next',
        textarea: '',
        backcolor: '',
        comments: ''

      }
    },
    mounted() {
      // 数据加载
      this.data = {
        pid: 1,
        name: "联想小新 Air 13 Pro",
        address: "唐山学院北校区",
        text: "刚买的电脑不想要了，低价处理，质量有保障！",
        price: 100,
        originalPrice: 1000,
        date: "2019.09.09",
        status: 1,
        paytype: "在线交易/线下交易",
        img: ["https://img.alicdn.com/imgextra/i1/725677994/O1CN01UXcGPZ28vIjBQHsbN_!!725677994.jpg_430x430q90.jpg",
          "https://img.alicdn.com/imgextra/i1/725677994/O1CN01UXcGPZ28vIjBQHsbN_!!725677994.jpg_60x60q90.jpg",
          "https://img.alicdn.com/imgextra/i2/725677994/TB2w_Ovg6QnBKNjSZSgXXXHGXXa_!!725677994.jpg_60x60q90.jpg",
          "https://img.alicdn.com/imgextra/i1/725677994/O1CN01Maov7r28vIguNwGGO_!!725677994.jpg_60x60q90.jpg",
          "https://img.alicdn.com/imgextra/i1/725677994/O1CN01Maov7r28vIguNwGGO_!!725677994.jpg_60x60q90.jpg"
        ]
      }
      this.comments = []
      this.imgblur = this.data.img[0];
    },
    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      enter() {
        this.backcolor = "background:#FFFFFF";
      },
      leave() {
        this.backcolor = this.textarea.length == 0 ? "background:#f4f5f7" :
          "background:#FFFFFF;border: 1px solid #00a1d6;";
      }
    }
  }
</script>

<style scoped="scoped">
  .containerDetails {
    background-color: #f7f8f8;
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
    background-color: #00FFFF;
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
    padding: 10px;
    margin-top: 15px;
    margin-bottom: 10px;
    border-radius: 10px 10px 10px 10px;
    background-color: #FFFFFF;
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
  }

  .pimg img {
    width: 100%;
    height: 100%;
  }

  .pimgs {
    width: 100%;
    margin: auto;
  }

  .pimgs ul li {
    float: left;
    width: 20%;
    height: 80px;
    text-align-last: center;
  }

  .pimgs ul li img {
    margin-top: 10px;
    width: 70%;
    height: 60px;
  }

  .pdetails {
    padding-left: 40px;
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
    font-size: 12px;
    margin-top: 30px;
    padding: 2px;
    text-align-last: center;
    color: #FFFFFF;
    background-color: #f50;
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
  }

  .pdbutton button {
    width: 140px;
    margin: 20px;
    margin-left: 10px;
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

  .buyer-sayed .buyer-date {
    font-size: 13px;
    color: #99a2aa;
  }

  .leaveMessage {
    width: 100%;
    height: 200px;
  }

  .leaveMessage .meimg {
    height: 100%;
    width: 15%;
    float: left;
  }

  .meimg img {
    height: 50px;
    width: 50px;
    float: left;
    margin: 30px 35px;
    margin-left: 60px;
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
</style>
