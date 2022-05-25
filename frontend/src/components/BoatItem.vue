<template>
    <li>
        <a href @click.prevent="showBoatDetails">{{ name }} ({{ description }})</a>
        <button @click="editBoat"><i class="fa fa-edit"></i></button>
        <button @click="deleteBoat"><i class="fa fa-trash"></i></button>
    </li>
    <BoatActionModal v-if="openEditModal" 
        :id="id"
        :name="name"
        :description="description"
        type="edit"
        @close="closeEditModal"
        @boat-updated="$emit('boat-modified')"
    ></BoatActionModal>
    <BoatDetailsModal v-if="displayBoatDetailsModal"
        :name="name"
        :description="description"
        @close="closeBoatDetailsModal"
    >
    </BoatDetailsModal>
</template>
<script>
import { boatService } from '@/services/boat.service'
import BoatActionModal from '@/components/BoatActionModal.vue'
import BoatDetailsModal from '@/components/BoatDetailsModal.vue'

export default {

    props: {
        'id': Number,
        'name': String,
        'description': String,
    },
    emits: ['boat-modified'],
    data() {
        return {
            openEditModal: false,
            displayBoatDetailsModal: false
        }
    },
    components: { BoatActionModal, BoatDetailsModal },
    methods: {
        deleteBoat() {
            if (confirm('Are you sure you want to delete this boat?')) {
                boatService.deleteBoat(this.id).then(() => {
                    console.log('Boat deleted');
                    this.$emit('boat-modified', this.id);
                })
            }
        },
        editBoat() {
            this.openEditModal = true;
        },
        closeEditModal() {
            this.openEditModal = false;
        },
        showBoatDetails() {
            this.displayBoatDetailsModal = true;
        },
        closeBoatDetailsModal() {
            this.displayBoatDetailsModal = false;
        }
    }
}
</script>