import axios from 'axios';

export const userService = {
    login,
    logout,
    getCurrentUser
};

function login(username, password) {
    return axios.get('/api/user', {
        auth: {
            username: username,
            password: password
        }
    }).then((response) => {
        if (response.status === 200) {
            console.log('login succesful, storing credentials');
            console.log(response.data);

            localStorage.setItem('user', JSON.stringify({
                username: username,
                authdata: window.btoa(username + ':' + password)
            }));

            return true;
        }
    }).catch((error) => {
        if (error.response) {
            if (error.response.status === 401) {
                console.log("Wrong credentials");
            }
        }
        console.log('Error : ' + error.message)
        return Promise.reject(error);
    });

}

function logout() {
    localStorage.removeItem('user');
}

function getCurrentUser() {
    return localStorage.user && JSON.parse(localStorage.user);
}
