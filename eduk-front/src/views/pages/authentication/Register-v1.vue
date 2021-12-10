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
          eduk를 즉시 이용해보세요~!
        </b-card-title>
        <b-card-text class="mb-2">
          eduk는 편리하게 회의를 열어 참여하거나 교육을 수강할 수 있습니다.
        </b-card-text>

        <!-- form -->
        <validation-observer ref="registerForm">
          <b-form
            class="auth-register-form mt-2"
            @submit.prevent="register"
          >
            <!-- username -->
            <b-form-group
              label="이름"
              label-for="username"
            >
              <validation-provider
                #default="{ errors }"
                name="Username"
                rules="required"
              >
                <b-form-input
                  id="username"
                  v-model="username"
                  :state="errors.length > 0 ? false:null"
                  name="register-username"
                  placeholder="johndoe"
                />
              </validation-provider>
            </b-form-group>

            <!-- email -->
            <b-form-group
              label="이메일"
              label-for="email"
            >
              <validation-provider
                #default="{ errors }"
                name="Email"
                rules="required|email"
              >
                <b-form-input
                  id="email"
                  v-model="regEmail"
                  :state="errors.length > 0 ? false:null"
                  name="register-email"
                  placeholder="john@example.com"
                />
              </validation-provider>
            </b-form-group>

            <!-- password -->
            <b-form-group
              label="비밀번호"
              label-for="password"
            >
              <validation-provider
                #default="{ errors }"
                name="Password"
                rules="required"
              >
                <b-input-group
                  class="input-group-merge"
                  :class="errors.length > 0 ? 'is-invalid':null"
                >
                  <b-form-input
                    id="password"
                    v-model="password"
                    :type="passwordFieldType"
                    :state="errors.length > 0 ? false:null"
                    class="form-control-merge"
                    name="register-password"
                    placeholder="············"
                  />
                  <b-input-group-append is-text>
                    <feather-icon
                      :icon="passwordToggleIcon"
                      class="cursor-pointer"
                      @click="togglePasswordVisibility"
                    />
                  </b-input-group-append>
                </b-input-group>
              </validation-provider>
            </b-form-group>

            <!-- checkbox -->
            <b-form-group>
              <b-form-checkbox
                id="register-privacy-policy"
                v-model="status"
                name="checkbox-1"
              >
                <b-link>개인정보보호정책 및 약관</b-link>
                에 동의합니다.
              </b-form-checkbox>
            </b-form-group>

            <!-- submit button -->
            <b-button
              variant="primary"
              block
              type="submit"
            >
              완료
            </b-button>
          </b-form>
        </validation-observer>

        <b-card-text class="text-center mt-2">
          <span>이미 계정을 가지고 계신가요? </span>
          <b-link :to="{name:'auth-login-v1'}">
            <span>로그인하세요~!</span>
          </b-link>
        </b-card-text>

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

import useJwt from '@/auth/jwt/useJwt'

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
      regEmail: '',
      username: '',
      password: '',
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
    register() {
      this.$refs.registerForm.validate().then(success => {
        if (success) {
          useJwt.register({
            name : this.username,
            email: this.regEmail,
            password: this.password,
          })
          
            .then(response => {
              console.log(response);
              useJwt.setToken(response.data.accessToken)
              useJwt.setRefreshToken(response.data.refreshToken)
              // localStorage.setItem('userData', JSON.stringify(response.data.userData))
              // this.$ability.update(response.data.userData.ability)
              this.$router.push('/')

              //TODO 기현 : 모달창이나 이메일 인증창으로 넘겨주기 
                        this.$toast({
            component: ToastificationContent,
            props: {
              title: '회원가입 완료',
              icon: 'EditIcon',
              variant: 'success',
            },
          })
            })
            .catch(error => {
              this.$refs.registerForm.setErrors(error.response.data.error)
            })
        }
      })
    },
  },
}
</script>

<style lang="scss">
@import '@core/scss/vue/pages/page-auth.scss';
</style>
