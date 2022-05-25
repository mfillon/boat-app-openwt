<template>
    <div>
        <h2>Home Page</h2>

        <ul>
            <boat-item v-for="boat in boats" 
                :key="boat.id"
                :id="boat.id"
                :name="boat.name"
                :description="boat.description"
            ></boat-item>
        </ul>
        <p v-if="!boats.length">No boat!</p>
    </div>
</template>
<script>

import { boatService } from '@/services/boat.service'
import BoatItem from '@/components/BoatItem.vue'
export default {
    components: {
        BoatItem
    },
    data() {
        return {
            boats: []
        }
    },
    created() {
        boatService.getAllForCurrentUser().then((boats) => {
            this.boats = boats
        })
    }
}
</script>