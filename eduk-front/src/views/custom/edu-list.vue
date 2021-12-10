<template>
  <b-container class="mt-5">
    <div class="myProfile">
      <ul>
        <li>
          <h1>{{userData.name}}</h1>
        </li>
        <li>
          {{userData.email}}
        </li>
        <li id="profile-image"></li>
      </ul>
    </div>

    <div class="classbtn">
    <h3>내 강의</h3>
    <b-button
      variant="primary"
      @click="enterClassRoom">
      강의실 입장
    </b-button>
    <b-button 
      variant="primary"
      @click="createClassRoom">
      강의실 생성
    </b-button>
    </div>

    <b-row class="match-height">
      <b-col cols="3" v-for="edu in eduList" :key="edu.classRoomId">
        <b-card
          tag="article"
          class="mb-1"
          li
          @click="enterClassRoom(edu.classRoomId)"
        >
          <img img-top alt="Image" :src="edu.classRoomImage"/>
          <b-card-title>{{ edu.classRoomName }}</b-card-title>
          <b-card-text>{{ edu.detail }}</b-card-text>
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
      userData: "",
    };
  },

  created() {
    this.userData = JSON.parse(localStorage.getItem("userData"));
    this.$http.get("/member/selectMemberInfo")
      .then((res) => {
        this.userData = res.data; console.log(this.userData);
        if(this.userData.profileImage != null){
          document.getElementById("profile-image")
          .style.backgroundImage = `url('https:/localhost:1234/file/down/${this.userData.profileImage}')`;
        }else{
          document.getElementById("profile-image")
          .style.backgroundImage = `url('https:/localhost:1234/file/down/noimage.png')`;
        }
      })
    this.fetchEduList();    
  },
  methods: {
    fetchEduList() {
      this.$http
        .get("class-room/list")
        .then((resp) => {
          this.eduList = resp.data; console.log(resp.data);
        })
    },
    createClassRoom() {
      this.$router.push({name:"create-room"})
    },
    enterClassRoom(classRoomId) {
      this.$http.get("class-room/" + classRoomId).then((resp) => {
        console.log(resp);
        Vue.prototype.$local.classRoom = resp.data;
        console.log(Vue.prototype.$local.classRoom);
        this.$router.push({ name: "welcome"});
      }, console.log);
    },
  },
};
</script>

<style>
  .myProfile{
    text-align: center;
    border-bottom: 1px solid #CDCB9E;
  }
  ul{
    list-style: none;
  }
  #profile-image{
    width: 100px;
    height: 100px;
    margin: 10px auto 0px;
    border: 2px solid #5E5873;
    background-repeat: no-repeat;
    background-size: 100% 100%;
  }
  h1{
    color: #CDCB9E;
  }
  h3{
    display: inline-block;
    float:left;
    margin-top: 7px;
  }
  h4{
    margin: 10px;
    margin-bottom: 10px;
  }
  .classbtn{
    text-align: right;
    padding: 20px;
  }
  .classbtn button{
    margin-left: 15px;
  }
  .match-height{
    padding: 0px 20px;
  }
  .card-body{
    padding: 0 0 10px 0;
  }
  .card-body p{
    margin: -10px 10px 0px 10px;
  }
  .mb-1:hover{
    cursor: pointer;
    box-shadow: 0px 2px 5px 0px #ccc;
  }
  img{
    width: 100%;
    height: 150px;
  }
</style>
