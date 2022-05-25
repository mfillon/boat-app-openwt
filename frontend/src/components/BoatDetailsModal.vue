<template>
  <teleport to="body">
      <dialog open>
        <header>
          <h2>{{name}}</h2>
        </header>
          <section>
            <div>
                <p>{{ description }}</p>
            </div>
          </section>
          <menu>
            <button type="button" @click="$emit('close')">Close</button>
          </menu>
      </dialog>
  </teleport>
</template>
<script>

export default {
  props: ['name', 'description'],
  emits: ['close'],
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