<template>
  <div class="navbar-container d-flex content align-items-center">

    <!-- Left Col -->
    <div>
      <a href="/">
        <img alt="logo" src="testlogo.png" width="100px" height="30px">
      </a>
    </div>

    <b-navbar-nav class="nav align-items-center ml-auto">
      <ul v-if="notlogin">
        <li>
          <a href="${pageContext.request.contextPath}/loginForm">로그인</a>
        </li>
        <li>
          <b-button variant="primary" block type="button" @click="signUp">회원 가입</b-button>
        </li>
      </ul>
      <ul v-if="login">
        <li>
          <a href="${pageContext.request.contextPath}/logout">로그아웃</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/myInfo">마이 페이지</a>
        </li>
        <li>
          <b-button variant="primary" block type="button" @click="createClassRoom">강의실 생성</b-button>
        </li>
        <li>
          <b-button variant="primary" block type="button" @click="enterClassRoom">강의실 입장</b-button>
        </li>
      </ul>
    </b-navbar-nav>
  </div>
</template>

<script>
import {
  BLink, BNavbarNav, BNavItemDropdown, BDropdownItem, BDropdownDivider, BAvatar, BButton,
} from 'bootstrap-vue'
import DarkToggler from '@core/layouts/components/app-navbar/components/DarkToggler.vue'

export default {
  components: {
    BLink,
    BNavbarNav,
    BNavItemDropdown,
    BDropdownItem,
    BDropdownDivider,
    BAvatar,
    BButton,
    // Navbar Components
    DarkToggler,
  },
  props: {
    toggleVerticalMenuActive: {
      type: Function,
      default: () => {},
    },
  },
  data(){
    return{
      login: false,
      notlogin: true,
    }
  }
}
</script>
<style>
  ul{
    list-style: none
  }
  ul li{
    float: left;
    margin-right: 30px;
  }
  ul li:last-child{
    margin-right: 0;
  }
  ul li a{
    position: relative;
    top: 10px;
  }
</style>