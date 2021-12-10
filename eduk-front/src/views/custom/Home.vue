<template>
  <div>
    <b-card title="에듀케이에 오신것을 환영합니다! 🚀">
      <b-img
        :src="require('@/assets/images/illustration/badge.svg')"
        class="congratulation-medal"
        alt="Medal Pic"
      />
      <b-card-text
        >에듀케이와 함께 손쉽고 간편한 수업을 시작해보세요!</b-card-text
      >
      <b-card-text
        >수업에 관한 더 자세한 내용을 알고싶으신가요?
        <b-link
          href="https://pixinvent.com/demo/vuexy-vuejs-admin-dashboard-template/documentation/"
          target="_blank"
        >
          여기
        </b-link>
        를 눌러 더 많은 정보를 확인해보세요!</b-card-text
      >
      <b-img
        :src="require('@/assets/images/illustration/email.svg')"
        alt="Meeting Pic"
        height="450"
      />
    </b-card>
  </div>
</template>

<script>
import { BCard, BCardText, BLink, BImg } from "bootstrap-vue";

export default {
  components: {
    BCard,
    BCardText,
    BLink,
    BImg,
  },
};
</script>

<style>
</style>
