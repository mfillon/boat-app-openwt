<template>
    <div>
        <h2>Home Page</h2>

        <ul>
            <boat-item v-for="boat in boats" 
                :key="boat.id"
                :id="boat.id"
                :name="boat.name"
                :description="boat.description"
                @boat-modified="reloadBoats"
            ></boat-item>
        </ul>
        <p v-if="!boats.length">No boat!</p>
    </div>
</template>
<script>

import BoatItem from '@/components/BoatItem.vue'
import { boatService } from '@/services/boat.service'
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
        this.reloadBoats();
    },
    methods: {
        reloadBoats() {
            boatService.getAllForCurrentUser().then((boats) => {
                this.boats = boats
            })
        }
    }
}
</script>