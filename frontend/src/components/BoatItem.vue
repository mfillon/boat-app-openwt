<template>
    <li>
        {{ name }} ({{ description }})
        <button @click="editBoat"><i class="fa fa-edit"></i></button>
        <button @click="deleteBoat"><i class="fa fa-trash"></i></button>
    </li>
    <EditBoatModal v-if="openEditModal" 
        :id="id"
        :name="name"
        :description="description"
        @close="closeEditModal" @boat-updated="$emit('boat-modified')"
    ></EditBoatModal>
</template>
<script>
import { boatService } from '@/services/boat.service'
import EditBoatModal from '@/components/EditBoatModal.vue'

export default {

    props: {
        'id': Number,
        'name': String,
        'description': String,
    },
    emits: ['boat-modified'],
    data() {
        return {
            openEditModal: false
        }
    },
    components: { EditBoatModal },
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
        }
    }
}
</script>