//引入vue-router插件
import { createRouter, createWebHistory } from 'vue-router'
//必须有views文件夹
//引入的名字可以和文件名不一样
import Home from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',//网页访问的根路径
      name: 'home',
      component: Home//转发到的组件，服务器行为
      // 可以访问的时候再引入,性能的取舍有不同
      // component: () => import('../views/HomeView.vue)
    },
    {//重定向,浏览器行为
      path:'/home',
      redirect: '/'
    },
    {
      path:'/book',
      // component:()=>import('/src/views/Book.vue')
      children:[
        {
          //写/则表示根路径
        path:'list',
        component:()=>import('/src/views/Book.vue')
      },
      {
        path:'user',
        component:()=>import('/src/views/User.vue')
      },
      {
        //表示'/book'
        path:'',
        redirect:'/book/list'
      },
      {
        path:'/cookie',
        component:()=>import('/src/views/Cookie.vue')
      },
      {
        path:'/test',
        component:()=>import('/src/views/Test.vue')
      }

    ]
    }
  ]
})

export default router
//跟 export default {} 作用相同