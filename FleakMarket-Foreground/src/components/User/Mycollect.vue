<template>
  <div class="container">
    <el-row v-if="userlove.length==0">
      <el-col :span="22" class="noData">
      </el-col>
    </el-row>
    <el-row class="typeProduct">
      <el-col :span="24">
        <ul>
          <li v-for="item in userlove">
            <div class="imgbox" @click="gotoDetails(item.id)"><img :src="item.images[0]"></div>
            <p class="productPrice">¥<span class="price">{{item.currentprice}}</span> </p>
            <!-- <p class="productName" @click="gotoDetails(item.id)"><span style="flex: 3">{{item.name}}</span> <span style="flex:1;float: right;font-size: 12px;" v-if="item.status == 1">已下架</span> </p> -->
            <el-row>
              <el-col :span="17" :offset="1">
                <div class="productName">{{item.name}}</div>
              </el-col>
              <el-col :span="4" :offset="1">
                <span style="font-size: 12px;" v-if="item.status == '2'">已下架</span>
              </el-col>
            </el-row>
            <div class="userPlay">
              <span>评价 {{item.comments}}</span>
              <span><button @click="deleteLove(item.id)">取消收藏</button></span>
            </div>
          </li>
        </ul>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import moment from "moment";
  export default{
    data(){
      return {
        userlove: '',
        imgblur: ''
      }
    },
    created() {
      //请求用户数据
      this.loadMyCollect();
    },
    methods:{
      loadMyCollect(){
        this.$axios.get('/product/selectMyCollectById', {
          params: {
              id: this.$store.state.userid
          }
        }).then(res => {
          res.data.forEach((res,index) => {
            if(res.images != null){
              res.images = eval('('+res.images+')');
            }
          });
          this.userlove = res.data;
        });
      },
      //跳转商品详情页面
      gotoDetails (pid) {
        this.$router.push({
          name: 'productDetails',
          query: {
            id: pid
          }
        });
      },
      //添加商品进收藏方法
      deleteLove(id){
        this.$confirm('是否删除该商品?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //商品添加收藏
          this.$axios.post('/product/deleteProductToLove', {
            uid: this.$store.state.userid,
            pid: id
          }).then(res => {
            this.loadMyCollect();
            if(res.data == 1){
              this.$message({
                message: '删除成功！',
                type: 'success'
              });
            }
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '删除取消'
          });
        })
      },
       //统计点击量
       clickNum(){
         //判断数据库中是否存在当前日期
         this.$axios.post('/utils/selectDateFromStatis',{
           dates: moment().format('YYYY-MM-DD')
         }).then(res => {
           if(res.data.length == 0){
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
  .container{
    width: 95%;
    min-height: 540px;
    margin: auto;
    color: #777;
    padding: 0 20px;
    margin-bottom: 10px;
    padding-bottom: 5px;
    border-radius: 10px;
    border: 1px solid #eee;
  }
  .typeProduct li{
    width: calc(25% - 8px);
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
    width: 100%;
    padding: 10px;
  }
  .typeProduct li img{
    width: 200px;
    height: 200px;
    background-repeat: no-repeat;
    background-position:center center;
    background-size: cover;
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
    overflow: hidden;/*超出部分隐藏*/
    text-overflow:ellipsis;/* 超出部分显示省略号 */
    white-space: nowrap;/*规定段落中的文本不进行换行 */
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
    padding-top: 300px;
    background-image: url(../../../static/img/noData/nodata05.png);
    background-repeat:no-repeat;
    background-position: center ;
  }
</style>
