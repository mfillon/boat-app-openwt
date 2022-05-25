<template>
    <li>
        {{ name }} ({{ description }}) <button @click="deleteBoat">X</button>
    </li>
</template>
<script>
import { boatService } from '@/services/boat.service'

export default {

    props: {
        'id': Number,
        'name': String,
        'description': String,
    },
    emits: ['boat-deleted'],
    methods: {
        deleteBoat() {
            if(confirm('Are you sure you want to delete this boat?')) {
                boatService.deleteBoat(this.id).then(() => {
                    console.log('Boat deleted');
                    this.$emit('boat-deleted', this.id);
                })
            }
        }
    }
}
</script>