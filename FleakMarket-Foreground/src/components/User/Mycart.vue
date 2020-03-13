<template>
  <div>
    <el-row class="cartContainer" v-for="item in 13">
    	<el-col :span="3">
    		<div class="proImg">
          <img src="../../../static/img/product/bj.jpg" alt="">
        </div>
    	</el-col>
    	<el-col :span="7">
    		<div class="proName">商品名称</div>
    		<div class="proDetail">商品描述</div>
    	</el-col>
    	<el-col :span="3">
    		<div class="proOP"><s>¥70.00</s></div>
    		<div class="proCP">¥99999</div>
    	</el-col>
    	<el-col :span="4">
    		<div class="proNumber">
          <el-input-number v-model="num" @change="handleChange" size="small" :min="1" :max="10" label="描述文字"></el-input-number>
        </div>
    	</el-col>
    	<el-col :span="3">
    		<div class="proTotal">¥99999</div>
    	</el-col>
      <el-col :span="4">
      	<div class="proOperator">
          <div>移入收藏夹</div>
          <div>删除</div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
      data() {
        return {
          num: 1
        };
      },
      methods: {
        handleChange(value) {
          console.log(value);
        }
      }
    };
</script>

<style>
  .cartContainer{
    height: 140px;
    margin: 5px 40px;
    padding: 20px;
    background-color: #fcfcfc;
    border: 1px solid #F0F0F0;
  }
  .proImg{
    height: 100px;
    width: 100px;
    background-color: #007DDB;
  }
  .proImg img{
    height: 100px;
  }
  .proName{
    font-size: 15px;
  }
  .proDetail{
    font-size: 12px;
    color: #222;
  }
  .proOP{
    font-size: 12px;
    color: #9c9c9c;
  }
  .proCP{
    font-size: 15px;
    margin-top: 5px;
    font-weight: 600;
    color: #3c3c3c;
  }
  .proTotal{
    font-weight: 700;
    color: #f40;
    text-align-last: center;
  }
  .proOperator{
    font-size: 12px;
    margin-left: 50px;
  }
</style>
