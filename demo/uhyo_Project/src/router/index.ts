import { createRouter, createWebHistory } from 'vue-router'
import TaskList from '../components/TaskList.vue'
import Select1 from '../components/select1.vue'
import ExpiredList from '@/components/ExpiredList.vue'
import HelloWorld from '@/components/HelloWorld.vue'
import TheWelcome from '@/components/TheWelcome.vue'
import WelcomeItem from '@/components/WelcomeItem.vue'

const routes = [
  {path: '/select1', name: 'Select1', component: Select1 },
  {path: '/tasklist', name: 'TaskList', component: TaskList },
  {path: '/ExpiredList', name: 'ExpiredList', component: ExpiredList},
  {path: '/HelloWorld', name: 'HelloWorld', component: HelloWorld},
  {path: '/TheWelcome', name: 'TheWelcome', component: TheWelcome},
  {path: '/WelcomeItem', name: 'WelcomeItem', component: WelcomeItem},
]


const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
