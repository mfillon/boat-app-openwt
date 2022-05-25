<template>
  <header>
    <div class="container">
      <div class="row row-header">
        <div class="col-12 col-sm-5 offset-sm-3">
          <h2>Boat App Challenge</h2>
        </div>
        <div class="col-12 col-sm align-self-center">
          <router-link to="/logout">Logout</router-link>
          <!-- TODO : insert logo here -->
          <!-- <img src="img/logo.png" class="img-fluid"> -->
        </div>
      </div>
    </div>
  </header>
  <div class="container">
    <div class="row">
      <div class="col-sm-6 offset-sm-3">
        <router-view></router-view>
      </div>
    </div>
  </div>
  <footer class="footer">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-auto">
          <p>© Copyright 2022 Boat Management</p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "app",
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Jost&display=swap");

* {
  box-sizing: border-box;
}

html {
  font-family: sans-serif;
}
</style>