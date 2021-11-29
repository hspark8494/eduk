<template>
  <b-card title="Create Awesome 🙌">
    <b-card-text>This is your second page.</b-card-text>
    <b-card-text>Chocolate sesame snaps pie carrot cake pastry pie lollipop muffin. Carrot cake dragée chupa chups jujubes. Macaroon liquorice cookie wafer tart marzipan bonbon. Gingerbread jelly-o dragée chocolate.</b-card-text>
    <button @click="getUser">Hello World!</button>
    <p>{{users}}</p>
  </b-card>
</template>

<script>
import { BCard, BCardText } from 'bootstrap-vue'
import axios from "axios"

export default {
  components: {
    BCard,
    BCardText,
  },
  data: ()=> {
    return{
      users : [1,2,3,4]
      }
  },
    methods:{
    getUser(){
      axios.get("https://localhost:1234/users").then(d => this.users = d)
    }
  }
}
</script>

<style>

</style>
