<template>
  <div class="auth-wrapper auth-v1 px-2">
    <div class="auth-inner py-2">

      <!-- Register v1 -->
      <b-card class="mb-0">
        <b-link class="brand-logo">
          <vuexy-logo />
          <h2 class="brand-text text-primary ml-1">
            eduK
          </h2>
        </b-link>

        <b-card-title class="mb-1">
          강의실 생성하기
        </b-card-title>
        <b-card-text class="mb-2">
          나만의 강의실을 생성해보세요!
        </b-card-text>

        <!-- form -->
        <validation-observer ref="registerForm">
          <b-form
            class="auth-register-form mt-2"
            @submit.prevent="validationForm"
          >

            <label for="image-file" id="classRoom-image">
            <div>
            <p>+</p>
            <input type="file" id="image-file" name="classimage"/>
            </div>
            </label>

            <b-form-group
              label="강의실 이름"
              label-for="classname"
            >
              <validation-provider
                #default="{ errors }"
                name="classname"
                rules="required"
              >
                <b-form-input
                  id="classname"
                  v-model="classname"
                  :state="errors.length > 0 ? false:null"
                  name="register-classname"
                  placeholder="강의실명 입력란"
                />
              </validation-provider>
            </b-form-group>

            <b-form-group
              label="강의실 설명"
              label-for="classdetail"
            >
              <validation-provider
                #default="{ errors }"
                name="classdetail"
                rules="required"
              >
                <b-form-input
                  id="classdetail"
                  v-model="classdetail"
                  :state="errors.length > 0 ? false:null"
                  name="register-classdetail"
                  placeholder="강의실 설명 입력란"
                />
              </validation-provider>
            </b-form-group>

            <!-- submit button -->
            <b-button
              variant="primary"
              block
              type="submit"
            >
              생성하기
            </b-button>
          </b-form>
        </validation-observer>
      </b-card>
    <!-- /Register v1 -->
    </div>
  </div>

</template>

<script>
import { ValidationProvider, ValidationObserver } from 'vee-validate'
import {
  BCard, BLink, BCardTitle, BCardText, BForm,
  BButton, BFormInput, BFormGroup, BInputGroup, BInputGroupAppend, BFormCheckbox,
} from 'bootstrap-vue'
import VuexyLogo from '@core/layouts/components/Logo.vue'
import { required, email } from '@validations'
import { togglePasswordVisibility } from '@core/mixins/ui/forms'
import ToastificationContent from '@core/components/toastification/ToastificationContent.vue'
import axios from 'axios'

export default {
  components: {
    VuexyLogo,
    // BSV
    BCard,
    BLink,
    BCardTitle,
    BCardText,
    BForm,
    BButton,
    BFormInput,
    BFormGroup,
    BInputGroup,
    BInputGroupAppend,
    BFormCheckbox,
    // validations
    ValidationProvider,
    ValidationObserver,
  },
  mixins: [togglePasswordVisibility],
  data() {
    return {
      classname: '',
      classdetail: '',
      status: '',

      // validation rules
      required,
      email,
    }
  },
  computed: {
    passwordToggleIcon() {
      return this.passwordFieldType === 'password' ? 'EyeIcon' : 'EyeOffIcon'
    },
  },
  methods: {
    validationForm() {
    const frm = new FormData();
    frm.append('className', this.classname);
    frm.append('classDetail', this.classdetail);
    var file = document.getElementById("image-file");
    frm.append("classImage", file.files[0]);
    this$axios.post('/createClassRoom', null, frm, {
      headers: {
         'Content-Type': 'multipart/form-data'
      }
    })
    .then((response) => {
      document.getElementById("#classRoom-image").style.backgroundImage= response;
    })
    },
  },
}
</script>

<style lang="scss">
@import '@core/scss/vue/pages/page-auth.scss';
</style>
<style>
  #classRoom-image{
    border: 1px dashed black;
    border-radius: 50%;
    width: 100px;
    height: 100px;
    margin: 0 auto;
    background-image: url('noimage.png');
    background-size: 100% 100%;
    display: block;
  }
  #classRoom-image:hover{
    cursor: pointer;
  }
  #classRoom-image p{
    position: relative;
    top: 70px;
    left: 70px;
    border: none;
    border-radius: 50%;
    width: 30px;
    height: 30px;
    padding: 5px 12px;
    background-color: #7367F0;
    color: #fff;
  }
  #classRoom-image input[type=file]{
    display: none;
  }
</style>