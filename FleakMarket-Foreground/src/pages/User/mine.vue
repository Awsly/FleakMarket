<template>
  <div class="containerMine">
    <search></search>
    <el-row>
      <el-col :span="14" :offset="5">
        <el-tabs tab-position="left" v-model="activeName" style="min-height: 515px;">
          <el-tab-pane label="我的账户" name="我的账户">
            <Myaccount></Myaccount>
          </el-tab-pane>
          <el-tab-pane label="发布商品" name="发布商品">
            <Myissue></Myissue>
          </el-tab-pane>
          <el-tab-pane label="我的收藏" name="我的收藏">
            <Mycollect></Mycollect>
          </el-tab-pane>
          <el-tab-pane label="我的商品" name="我的商品">
            <Myproduct></Myproduct>
          </el-tab-pane>
          <!-- <el-tab-pane label="我的购物车" name="我的购物车">
            <Mycart></Mycart>
          </el-tab-pane> -->
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
  import Mycart from "@/components/User/Mycart.vue";

  export default {
    components: {
      Search,
      FooterBottom,
      Myaccount,//我的账户
      Myissue,//我的发布
      Mycollect,//我的收藏
      Myproduct,//我的商品
      Mycart//我的购物车
    },
    data() {
      return {
        activeName: "我的账户",//默认页
      }
    },
    created() {

    },
    methods: {

    }
  }
</script>
<style scoped>

</style>
