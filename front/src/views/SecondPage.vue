<template>
  <b-card title="Create Awesome 🙌">
    <input v-bind="name">
    <input v-bind="email">
    <input v-bind="password">
    <button @click="registry">회원가입</button>
    <button @click="getUsers">getUsers()</button>
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
      name : "",
      email : "",
      password : "",
      users : [],
      }
  },
    methods:{
    registry(){
      const user = JSON.stringify({"name" : "park", "email" : "park@naver.com", "password" : "1234"});
      console.log(user)
      axios.post("https://localhost:1234/register")
      .then(
        () => alert("회원가입 성공!"),
        (err) => alert(`회원가입 실패 ${err}`)
      )
    },
    getUsers(){
      axios.get("https://localhost:1234/users?id=1234")
      .then(
        (d) => this.users = d,
        (err) => alert(`에러 : ${err}`)
      )
    }

  }
}
</script>

<style>

</style>
