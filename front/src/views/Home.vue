<template>
  <div>
    <b-alert
      variant="primary"
      show
    >
      <div class="alert-body">
        <p>
          <strong>Info: </strong>
          <span>Please check the </span>
          <b-link
            class="alert-link"
            href="https://pixinvent.com/demo/vuexy-vuejs-admin-dashboard-template/documentation/guide/layout/page-layout-examples.html#layout-without-menu"
            target="blank"
          >Layout Without Navigation Menu documentation</b-link>
          <span> for more details.</span>
        </p>
      </div>
    </b-alert>
    
    <b-card title="Home 🙌">
      <b-card-text>All the best for your new project.</b-card-text>
    </b-card>
  </div>
</template>

<script>
import { BAlert, BLink, BCard } from 'bootstrap-vue'

export default {
  components: {
    BAlert,
    BLink,
    BCard,
  },
  data() {
    return {
      menuHidden: this.$store.state.appConfig.layout.menu.hidden,
    }
  },
  created() {
    this.$store.commit('appConfig/UPDATE_NAV_MENU_HIDDEN', true)
  },
  destroyed() {
    this.$store.commit('appConfig/UPDATE_NAV_MENU_HIDDEN', this.menuHidden)
  },
}
</script>
