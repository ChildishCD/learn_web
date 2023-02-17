//1.1引入组件
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// 导入 Element-Plus插件
import ElementPlus from 'element-plus'
//element-plus 全局 国际化插件
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
//element-plus 图标插件
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//不用app.use的组件可以在其他的页面引入
// import axios from "axios";
// import qs from "qs";

//1.2引入css样式
import './assets/main.css'
// 导入 element-plus样式表 (不是本地文件)
import 'element-plus/dist/index.css'

//2.1创建和操作Vue对象
const app = createApp(App)

app.use(router)
//配置ElementPlus插件给app实例，并配置国际化
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
  app.use(ElementPlus, {
    locale: zhCn,
  })

app.mount('#app')
