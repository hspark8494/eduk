<template>
  <b-container class="mt-5">
    <button @click="fetchEduList">강의 가져오기</button>
    <button @click="createClassRoom">강의 생성하기</button><br />
    <input v-model="classRoomName" /><br />
    <input v-model="detail" /><br />
    <input v-model="classRoomImage" /><br />

    <b-row class="match-height">
      {{ eduList }}

      <b-col cols="3" v-for="edu in eduList" :key="edu.classRoomId">
        <b-card
          img-src="https://picsum.photos/600/300/?image=25"
          img-alt="Image"
          img-top
          tag="article"
          class="mb-1"
          li
        >
          <b-card-title>{{ edu.classRoomName }}</b-card-title>
          <b-card-text>
            {{ edu.detail }}
          </b-card-text>

          <b-button
            href="#"
            variant="primary"
            class="w-100"
            @click="enterClassRoom(edu.classRoomId)"
            >강의 입장</b-button
          >
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {
  BCard,
  BCardText,
  BButton,
  BRow,
  BCol,
  BImg,
  BCardBody,
  BCardTitle,
  BCardSubTitle,
  BLink,
  BContainer,
  BNavbar,
  BNavbarToggle,
  BCollapse,
  BTabs,
  BNavItem,
} from "bootstrap-vue";
import Ripple from "vue-ripple-directive";
import Vue from "vue";
export default {
  components: {
    BCard,
    BCardText,
    BButton,
    BCardBody,
    BCardTitle,
    BCardSubTitle,
    BRow,
    BCol,
    BLink,
    BImg,
    BContainer,
    BTabs,
    BNavItem,
    BNavbar,
    BNavbarToggle,
    BCollapse,
  },
  directives: {
    Ripple,
  },
  data() {
    return {
      eduList: [],
      classRoomName: "",
      detail: "",
      classRoomImage: "",
    };
  },

  created() {
    this.fetchEduList();
  },
  methods: {
    fetchEduList() {
      this.$http
        .get("class-room/list")
        .then((resp) => (this.eduList = resp.data), console.log);
    },
    createClassRoom() {
      let userData = JSON.parse(localStorage.getItem("userData"));
      this.$http.post("class-room", {
        classRoomName: this.classRoomName,
        detail: this.detail,
        classRoomImage: this.classRoomImage,
        memberId: userData.id,
      });
    },
    enterClassRoom(classRoomId) {
      this.$http.get("class-room/" + classRoomId).then((resp) => {
        console.log(resp);
        Vue.prototype.$local.classRoom = resp.data;
        console.log(Vue.prototype.$local.classRoom);
        this.$router.push({ name: "apps-calendar", classRoomId: classRoomId });
      }, console.log);
    },
  },
};
</script>

<style scoped>
</style>
