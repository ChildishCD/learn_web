import { ref } from "vue";
import { defineStore } from "pinia";

export default defineStore("bookStore", {
  state: () => {
    const user = ref({
      userId: 0,
      userName: "",
      password: "",
    });
    return {
      user,
    };
  },
  actions: {
    //对于该store的方法
  },
  persist: {
    storage: localStorage,
    paths: ["book"],
  },
});
