<template>
    <div>
        <div class="alert alert-info">
            <p>Username: user1</p>
            <p>Password: password</p>
        </div>
        <h2>Login</h2>
        <form @submit.prevent="login">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" ref="username" name="username" class="form-control" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" ref="password" name="password" class="form-control" />
            </div>
            <div class="form-group">
                <button class="btn btn-primary" :disabled="loading">Login</button>
                <i v-show="loading" class="fa fa-spinner" />
            </div>
            <div v-if="error" class="alert alert-danger">{{error}}</div>
        </form>
    </div>
</template>

<script>
import router from '@/router';
import { userService } from '@/services/user.service';

export default {
    data() {
        return {
            loading: false,
            returnUrl: null,
            error: null
        };
    },
    created() {
        this.returnUrl = this.$route.query.returnUrl || "/";
    },
    methods: {
        login() {
            let username = this.$refs.username.value;
            let password = this.$refs.password.value;
            if (!(username && password)) {
                this.error = 'Username and password are required'
            }

            this.loading = true;
            userService.login(username, password).then(
                () => router.push(this.returnUrl)
            ).catch((error) => {
                this.loading = false;
                this.error = error
            });
        }
    }
}
</script>