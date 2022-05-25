<template>
    <li>
        {{ name }} ({{ description }})
    </li>
</template>
<script>

export default {

    props: {
        'id': Number,
        'name': String,
        'description': String,
    },
    data() {
        return {

        };
    }
}
</script>