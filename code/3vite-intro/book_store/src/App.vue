<script setup>
import HelloWorld from "./views/HelloWorld.vue";
import YDHeader from "./components/YDHeader.vue";
import YDFooter from "./components/YDFooter.vue";
import list from "./views/BookList.vue";
</script>

<template>
  <el-container>
    <el-header>
      <YDHeader />
    </el-header>
    <!-- 动态的设置组件中变量的值, 路由也可以设置 -->
    <!-- <HelloWorld msg="Vite + Vue" /> -->
    <el-main>
      <RouterView />
    </el-main>
    <el-footer>
      <YDFooter />
    </el-footer>
  </el-container>
</template>
