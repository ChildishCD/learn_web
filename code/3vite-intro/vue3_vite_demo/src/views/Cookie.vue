<script setup>
import { ref } from "vue";

var username = ref("");
var password = ref("");

var f1 = () => {
  localStorage.setItem("uname", username.value);
  localStorage.setItem("passwd", password.value);
};
</script>

<template>
  <el-container :direction="vertical">
    <el-header height=""> </el-header>
    <el-container :direction="horizontal">
      <el-container :direction="vertical">
        <el-main height="">
          <el-row :gutter="20">
            <el-col :span="2" :offset="0"></el-col>
            <el-col :span="9" :offset="0">
              <el-input
                v-model="username"
                placeholder="请输入用户名"
                size="normal"
                clearable
                @change=""
              ></el-input>
            </el-col>
            <el-col :span="2" :offset="0"></el-col>
            <el-col :span="9" :offset="0">
              <el-input
                v-model="password"
                placeholder="请输入密码"
                size="normal"
                clearable
                @change=""
              ></el-input>
            </el-col>
            <el-col :span="2" :offset="0"></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12" :offset="0">
              <el-button type="primary" size="default" @click="f1"></el-button>
            </el-col>
            <el-col :span="12" :offset="0">
              <!-- <el-button type="primary" size="default" @click=""></el-button> -->
            </el-col>
          </el-row>
        </el-main>
        <el-footer height="">
          <!-- Footer content -->
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>
