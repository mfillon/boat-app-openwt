import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '@/pages/HomePage'
import LoginPage from '@/pages/LoginPage'
import { userService } from '@/services/user.service';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', component: HomePage },
    { path: '/login', component: LoginPage },
    { path: '/logout'},

    // otherwise redirect to home
    { path: '/:notFound(.*)', redirect: '/' },
  ]
});

router.beforeEach((to, from, next) => {
    if (to.path === '/logout') {
        console.log('logout');
        userService.logout();
        return next('/login');
    }
    const authRequired = to.path !== '/login';
    const loggedIn = userService.getCurrentUser();

    if (authRequired && !loggedIn) {
        return next({
        path: '/login',
        query: { returnUrl: to.path }
        });
    }

  next();
})

export default router;
