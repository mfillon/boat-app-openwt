import axios from 'axios';
import { userService } from '@/services/user.service';

export const boatService = {
    getAllForCurrentUser,
    deleteBoat,
    // getCurrentUser
};

function getAllForCurrentUser() {
    let user = userService.getCurrentUser();
    if (!user) {
        console.log("No user connected");
        return []
    }
    let username = user.username;
    return axios.get('/api/boats', {
        params: { owner: username }
    }).then((response) => {
        return response.data;
    }).catch((error) => {
        console.log('Error : ' + error.message)
        return Promise.reject(error);
    });

}

function deleteBoat(boatId) {
    return axios.delete('/api/boats/' + boatId);
}