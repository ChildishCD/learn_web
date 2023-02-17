import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";

import ElementPlus from "element-plus";
import zhCn from "element-plus/dist/locale/zh-cn.mjs";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import "element-plus/dist/index.css";

import { createPinia } from "pinia";
import persistedstate from "pinia-plugin-persistedstate";

import "./style.css";

//!app只能为const
const app = createApp(App);

//路由插件
app.use(router);

//ElementPlus插件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
app.use(ElementPlus, {
  locale: zhCn,
});

const pinia = createPinia();
pinia.use(persistedstate);
app.use(pinia);

app.mount("#app");
