<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  html,body,#app{
    margin: 0;
    padding: 0;
    height: 100%;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  a {
    color: #000000;
    text-decoration: none;
  }
  input,button{
    border: 0;
    outline: none;
  }
</style>
