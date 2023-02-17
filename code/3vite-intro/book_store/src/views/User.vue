<template>
  {{ bookStore.user.userName }}
</template>

<script setup>
import store from "/src/store/BookStore.js";
const bookStore = store();
</script>
