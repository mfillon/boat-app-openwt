import axios from 'axios';

import { userService } from '@/services/user.service.js';

export default function basicAuthInterceptor() {
    axios.interceptors.request.use(request => {
        // add basic auth header if account is logged in and request is to the api url
        const user = userService.getCurrentUser();
        const isLoggedIn = user;
        const isApiUrl = request.url.startsWith('/api/');
        if (isLoggedIn && isApiUrl) {
            console.log('Interceptor. adding basic auth data : ', user.authdata)
            request.headers.Authorization = `Basic ${user.authdata}`;
        }

        return request;
    });
}