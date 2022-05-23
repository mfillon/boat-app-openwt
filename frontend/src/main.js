import { createApp } from 'vue'
import router from './router.js'
import basicAuthInterceptor from '@/services/basic-auth.interceptor'

import App from './App.vue'

const app = createApp(App)

app.use(router)

app.mount('#app')

basicAuthInterceptor()