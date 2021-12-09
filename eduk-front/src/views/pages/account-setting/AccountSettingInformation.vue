<template>
  <b-card>
    <!-- form -->
    <b-form>
      <b-row>
        <!-- bio -->
        <b-col cols="12">
          <b-form-group label="소개" label-for="bio-area">
            <b-form-textarea
              id="bio-area"
              v-model="localOptions.bio"
              rows="4"
              placeholder="소개글을 입력하세요!"
            />
          </b-form-group>
        </b-col>
        <!--/ bio -->
        <!-- website -->
        <b-col md="6">
          <b-form-group label-for="website" label="홈페이지">
            <b-form-input
              id="website"
              v-model="localOptions.website"
              placeholder="www.eduk.com"
            />
          </b-form-group>
        </b-col>
        <!--/ website -->

        <!-- phone -->
        <b-col md="6">
          <b-form-group label-for="phone" label="전화번호">
            <cleave
              id="phone"
              class="form-control"
              :raw="false"
              :options="clevePhone"
              placeholder="010-0000-0000"
            />
          </b-form-group>
        </b-col>
        <!-- phone -->

        <b-col cols="12">
          <b-button
            v-ripple.400="'rgba(255, 255, 255, 0.15)'"
            variant="primary"
            class="mt-1 mr-1"
          >
            저장
          </b-button>
          <b-button
            v-ripple.400="'rgba(186, 191, 199, 0.15)'"
            type="reset"
            class="mt-1"
            variant="outline-secondary"
            @click.prevent="resetForm"
          >
            취소
          </b-button>
        </b-col>
      </b-row>
    </b-form>
  </b-card>
</template>

<script>
import {
  BButton,
  BForm,
  BFormGroup,
  BFormInput,
  BRow,
  BCol,
  BCard,
  BFormTextarea,
} from "bootstrap-vue";
import vSelect from "vue-select";
import flatPickr from "vue-flatpickr-component";
import Ripple from "vue-ripple-directive";
import Cleave from "vue-cleave-component";

// eslint-disable-next-line import/no-extraneous-dependencies
import "cleave.js/dist/addons/cleave-phone.us";

export default {
  components: {
    BButton,
    BForm,
    BFormGroup,
    BFormInput,
    BRow,
    BCol,
    BCard,
    BFormTextarea,
    vSelect,
    flatPickr,
    Cleave,
  },
  directives: {
    Ripple,
  },
  props: {
    informationData: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      countryOption: ["USA", "India", "Canada"],
      localOptions: JSON.parse(JSON.stringify(this.informationData)),
      clevePhone: {
        phone: true,
        phoneRegionCode: "US",
      },
    };
  },
  methods: {
    resetForm() {
      this.localOptions = JSON.parse(JSON.stringify(this.informationData));
    },
  },
};
</script>

<style lang="scss">
@import "@core/scss/vue/libs/vue-select.scss";
@import "@core/scss/vue/libs/vue-flatpicker.scss";
</style>
