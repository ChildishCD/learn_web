import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
      {
        path:'/',
        component: ()=>import('/src/views/HelloWorld.vue')
      },
      {
        path: '/book',
        children: [
          {
            path:'',
            component: ()=>import('/src/views/BookList.vue')
        },
        {
          path:'user',
          component: ()=>import('/src/views/User.vue')
        }
      ]
      }
    ]
  })
  
  export default router