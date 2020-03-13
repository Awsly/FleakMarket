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
            <el-col :span="10">
              <div class="pimg">
                <img :src="imgblur" alt="图片呢">
              </div>
              <div class="pimgs">
                <ul>
                  <li v-for="item in data.images"><img :src="item" alt="图片呢"></li>
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
                  <td class="pdprice">¥{{data.currentprice}}</td>
                </tr>
                <tr>
                  <td>商品原价</td>
                  <td style="color: #999999;">¥<s>{{data.originalprice}}</s></td>
                </tr>
                <tr>
                  <td>交易地址</td>
                  <td>{{data.address}}</td>
                </tr>
                <tr>
                  <td>商品状态</td>
                  <td>{{data.end == null?"未出售":"已出售"}}</td>
                </tr>
                <tr>
                  <td>交易方式</td>
                  <td>{{data.deal}}</td>
                </tr>
                <tr>
                  <td>卖家</td>
                  <td>{{data.username}}</td>
                </tr>
              </table>
              <div class="pdbutton">
                <el-button type="success">联系卖家</el-button>
                <el-button type="danger" icon="el-icon-edit" @click="addLove(data.id)">收藏</el-button>
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
            <div class="pagesmall" @click="loadcomments"><i class="el-icon-refresh-right"></i>刷新</div>
          </div>

          <!-- 发布评论模块 -->
          <div class="leaveMessage">
            <div class="meimg"><img :src="User.userimgpath" alt=""></div>
            <textarea :style="backcolor" maxlength="200" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" @mouseenter="enter"
              @mouseleave="leave" v-model="textarea"></textarea>
            <div class="leaveMessageBtn">
              <button @click="insertComment(data.id)">提交</button>
            </div>
          </div>

          <!-- 评论模块 -->
          <ul>
            <li v-for="item in comments.slice((currentPage-1)*PageSize,currentPage*PageSize)">
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
            <li class="pagebig" v-if="comments != ''">
              <div class="buyer-headerimg">
              </div>
              <el-pagination background
                layout="total,prev, pager, next,jumper"
                :page-size="PageSize"
                prev-text=" 上一页 "
                next-text=" 下一页 "
                :total="totalCount"
                @current-change="handleCurrentChange"
                >
              </el-pagination>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>
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
        User: '',
        data: '',
        imgblur: '',
        currentPage: 1,
        layout: 'slot,pager,next',
        textarea: '',
        backcolor: '',
        comments: '',
        PageSize:5,
        totalCount:1,
      }
    },
    created() {
      //请求用户信息
      this.$axios.get('/user/selectUserById', {
        params: {
          id: this.$store.state.userid
        }
      }).then(res => {
        this.User = res.data;
      })
      //加载商品详情
      this.loadproductdetails();
      //加载用户评论
      this.loadcomments();
    },
    methods: {
      //评论跳转页
      handleCurrentChange(val) {
        // 改变默认的页数
        this.currentPage=val;
      },
      enter() {
        this.backcolor = "background:#FFFFFF";
      },
      leave() {
        this.backcolor = this.textarea.length == 0 ? "background:#f4f5f7" :
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
        })
      },
      //添加商品进收藏方法
      addLove(id){
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
              if(res.data == 1){
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
             if(res.data == 1){
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
</style>
