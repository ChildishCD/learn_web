<script setup>
// setup 中看作是js的代码不是vue的规则
import { ref } from "vue";
import { Search } from "@element-plus/icons-vue";

//以下都是当前vue中才能用的变量
//字符串变量(通过了ref包装)
var msg = ref("Hello world!"); //v-model 需要
var hello = "Hello world!";
var hi = "Hi, how are you?";
//函数
function sayHi() {
  if (msg.value == hello) {
    msg.value = hi;
  } else {
    msg.value = hello;
  }
}
//可以向外暴露方法,但一般用js文件写方法方便引用
</script>

<template>
  <main>
    -- This is Home.vue! --
    <h1>{{ msg }}</h1>
    <el-button type="primary" @click="sayHi">OK</el-button>
    <el-button :icon="Search" />
  </main>
</template>
