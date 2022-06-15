<template>
  <div class="containerMine">
    <search></search>
    <el-row>
      <el-col :span="14" :offset="5">
        <el-tabs
          tab-position="left"
          v-model="activeName"
          @tab-click="swithcTab"
          style="min-height: 515px;">
          <el-tab-pane label="我的账户" name="我的账户">
            <Myaccount v-if="activeTab===1"></Myaccount>
          </el-tab-pane>
          <el-tab-pane label="发布商品" name="发布商品">
            <Myissue v-if="activeTab===2"></Myissue>
          </el-tab-pane>
          <el-tab-pane label="我的收藏" name="我的收藏">
            <Mycollect v-if="activeTab===3"></Mycollect>
          </el-tab-pane>
          <el-tab-pane label="我的商品" name="我的商品">
            <Myproduct v-if="activeTab===4"></Myproduct>
          </el-tab-pane>
          <el-tab-pane label="我的订单" name="我的订单">
            <Myorder v-if="activeTab===5"></Myorder>
          </el-tab-pane>
          <el-tab-pane label="我的购物车" name="我的购物车">
            <Mycart v-if="activeTab===6"></Mycart>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
    <footer-bottom></footer-bottom>
  </div>
</template>
<script>
  import Search from "@/components/Search.vue";
  import FooterBottom from "@/components/Footer.vue";

  import Myaccount from "@/components/User/Myaccount.vue";
  import Myissue from "@/components/User/Myissue.vue";
  import Mycollect from "@/components/User/Mycollect.vue";
  import Myproduct from "@/components/User/Myproduct.vue";
  import Myorder from "@/components/User/Myorder.vue";
  import Mycart from "@/components/User/Mycart.vue";


  export default {
    components: {
      Search,
      FooterBottom,
      Myaccount,//我的账户
      Myissue,//我的发布
      Mycollect,//我的收藏
      Myproduct,//我的商品
      Myorder,//我的订单
      Mycart//我的购物车
    },
    data() {
      return {
        activeName: "我的账户",//默认页
        activeTab: 1
      }
    },
    created() {
      if(this.$store.state.userid === 0){
        this.$router.push({
          name: 'login',
          query: {
            name: 'login'
          },
        });
      }
    },
    methods: {
      swithcTab(){
        console.log("666",this.activeName);
        switch (this.activeName){
          case "我的账户":
            this.activeTab = 1;
            break;
          case "发布商品":
            this.activeTab = 2;
            break;
          case "我的收藏":
            this.activeTab = 3;
            break;
          case "我的商品":
            this.activeTab = 4;
            break;
          case "我的订单":
            this.activeTab = 5;
            break;
          case "我的购物车":
            this.activeTab = 6;
            break;

        }
      }
    }
  }
</script>
<style>

</style>
