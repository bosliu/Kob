<template>
  <div>
    <div>Bot Name: {{ bot_name }}</div>
    <div>Bot Fighting {{ bot_rating }}</div>
  </div>
  <router-view/>
</template>

<script>


import { ref } from 'vue';
import $ from 'jquery';


export default {
  name: "App",
  setup: () => {
    let bot_name = ref("");
    let bot_rating = ref("");

    $.ajax({
      url: "http://127.0.0.1:8081/pk/getbotinfo/",
      type: "get",
      success: resp => {
        //console.log(resp);
        bot_name.value = resp.name
      }

    });

    return {
      bot_name,
      bot_rating
    }


  }
}

</script>

<style>
body{
  background-image: url("@/assets/background.png");
  background-size: cover;
}

</style>
