<template>
  <teleport to="body">
    <!-- <div @click="$emit('close')"> -->
      <dialog open>
        <header>
          <h2>{{ title }}</h2>
        </header>
        <form @submit.prevent="addNewResource">
          <section>
            <div>
              <label for="name">Name</label>
              <input type="text" ref="name" :value="name" />
            </div>
            <div>
              <label for="description">Description</label>
              <textarea id="description" rows="3" ref="description" :value="description"></textarea>
            </div>
          </section>
          <menu>
            <button type="submit" @click="triggerAction">{{actionCaption}}</button>
            <button type="button" @click="$emit('close')">Close</button>
          </menu>
        </form>
      </dialog>
    <!-- </div> -->
  </teleport>
</template>
<script>

import { boatService } from '@/services/boat.service'

export default {
  props: ['id', 'name', 'description', 'type'],
  emits: ['close', 'boat-updated', 'boat-created'],
  methods: {
    triggerAction() {
      if (this.isUpdateAction) {
        this.updateBoat();
      } else {
        this.createBoat();
      }
    },
    updateBoat() {
      boatService.editBoat(this.id, this.$refs.name.value, this.$refs.description.value).then(() => {
        this.$emit('boat-updated', this.id);
        this.$emit('close');
      })
    },
    createBoat() {
      boatService.createBoat(this.$refs.name.value, this.$refs.description.value).then(() => {
        this.$emit('boat-created', this.id);
        this.$emit('close');
      })
    }
  },
  computed: {
    title() {
      return this.isUpdateAction?'Edit boat':'Create boat';
    },
    actionCaption() {
      return this.isUpdateAction?'Update':'Create';
    },
    isUpdateAction() {
      return this.type === 'edit';
    },
    iscreateAction() {
      return this.type === 'action';
    }
  }
};
</script>
<style scoped>
/* div {
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.75);
  z-index: 10;
} */

dialog {
  margin: 0;
  padding: 0;
  position: fixed;
  top: 20vh;
  left: 10%;
  width: 80%;
  background-color: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  border-radius: 12px;
  border: none;
}

header {
  background-color: purple;
  border-radius: 12px 12px 0 0;
  width: 100%;
  padding: 1rem;
}

h2 {
  margin: 0;
  color: white;
}

section {
    padding: 1rem;
}

menu {
    display: flex;
    justify-content: flex-end;
    padding: 1rem;
    margin: 0;
}
</style>