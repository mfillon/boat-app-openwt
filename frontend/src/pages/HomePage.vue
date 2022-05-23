<template>
    <div>
        <h2>Home Page</h2>

        <ul>
            <!-- TODO extract to component BoatItem.vue -->
            <li v-for="boat in boats" :key="boat.id">{{ boat.name }} ({{ boat.description }})</li>
        </ul>
        <p v-if="!boats.length">No boat!</p>
    </div>
</template>
<script>

import { boatService } from '@/services/boat.service'
export default {
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