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
        <button @click="createBoat">New boat!</button>
        <BoatActionModal v-if="openCreateModal"
            type="create"
            @boat-created="reloadBoats"
            @close="closeCreateModal"
        ></BoatActionModal>
    </div>
</template>
<script>

import BoatItem from '@/components/BoatItem.vue'
import { boatService } from '@/services/boat.service'
import BoatActionModal from '@/components/BoatActionModal.vue';
export default {
    components: {
    BoatItem,
    BoatActionModal
},
    data() {
        return {
            boats: [],
            openCreateModal: false
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
        },
        createBoat() {
            this.openCreateModal = true;
        },
        closeCreateModal() {
            this.openCreateModal = false;
        }
    }
}
</script>