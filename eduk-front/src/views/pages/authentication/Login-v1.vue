<template>
  <div class="auth-wrapper auth-v1 px-2">
    <div class="auth-inner py-2">
      <!-- Login v1 -->
      <b-card class="mb-0">
        <b-link class="brand-logo">
          <vuexy-logo />

          <h2 class="brand-text text-primary ml-1">EduK</h2>
        </b-link>

        <b-card-title class="mb-1">
          에듀케이에 오신 것을 환영합니다! 👋
        </b-card-title>
        <b-card-text class="mb-2">
          서비스를 이용하시려면 먼저 로그인을 해주세요!
        </b-card-text>

        <!-- form -->
        <validation-observer ref="loginForm" #default="{ invalid }">
          <b-form class="auth-login-form mt-2" @submit.prevent="login">
            <!-- email -->
            <b-form-group label-for="email" label="Email">
              <validation-provider
                #default="{ errors }"
                name="Email"
                rules="required|email"
              >
                <b-form-input
                  id="email"
                  v-model="userEmail"
                  name="login-email"
                  :state="errors.length > 0 ? false : null"
                  placeholder="student@eduk.com"
                  autofocus
                />
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>

            <!-- password -->
            <b-form-group>
              <div class="d-flex justify-content-between">
                <label for="password">비밀번호</label>
                <b-link :to="{ name: 'auth-forgot-password-v1' }">
                  <small>비밀번호 찾기</small>
                </b-link>
              </div>
              <validation-provider
                #default="{ errors }"
                name="Password"
                rules="required"
              >
                <b-input-group
                  class="input-group-merge"
                  :class="errors.length > 0 ? 'is-invalid' : null"
                >
                  <b-form-input
                    id="password"
                    v-model="password"
                    :type="passwordFieldType"
                    class="form-control-merge"
                    :state="errors.length > 0 ? false : null"
                    name="login-password"
                    placeholder="Password"
                  />

                  <b-input-group-append is-text>
                    <feather-icon
                      class="cursor-pointer"
                      :icon="passwordToggleIcon"
                      @click="togglePasswordVisibility"
                    />
                  </b-input-group-append>
                </b-input-group>
                <small class="text-danger">{{ errors[0] }}</small>
              </validation-provider>
            </b-form-group>

            <!-- checkbox -->
            <b-form-group>
              <b-form-checkbox
                id="remember-me"
                v-model="status"
                name="checkbox-1"
              >
                로그인 유지
              </b-form-checkbox>
            </b-form-group>

            <!-- submit button -->
            <b-button
              variant="primary"
              class="mt-3 mb-2"
              type="submit"
              block
              :disabled="invalid"
            >
              로그인
            </b-button>
          </b-form>
        </validation-observer>
        <b-card-text class="text-center mt-2">
          <span>처음 방문하셨나요? </span>
          <b-link :to="{ name: 'auth-register' }">
            <span>계정을 만들어보세요!</span>
          </b-link>
        </b-card-text>
      </b-card>
      <!-- /Login v1 -->
    </div>
  </div>
</template>

<script>
import { ValidationProvider, ValidationObserver } from "vee-validate";
import {
  BButton,
  BForm,
  BFormInput,
  BFormGroup,
  BCard,
  BLink,
  BCardTitle,
  BCardText,
  BInputGroup,
  BInputGroupAppend,
  BFormCheckbox,
} from "bootstrap-vue";
import VuexyLogo from "@core/layouts/components/Logo.vue";
import { required, email } from "@validations";
import { togglePasswordVisibility } from "@core/mixins/ui/forms";
import useJwt from "@/auth/jwt/useJwt";
import { getHomeRouteForLoggedInUser } from "@/auth/utils";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";
import Vue from "vue";

export default {
  components: {
    // BSV
    BButton,
    BForm,
    BFormInput,
    BFormGroup,
    BCard,
    BCardTitle,
    BLink,
    VuexyLogo,
    BCardText,
    BInputGroup,
    BInputGroupAppend,
    BFormCheckbox,
    ValidationProvider,
    ValidationObserver,
  },
  mixins: [togglePasswordVisibility],
  data() {
    return {
      userEmail: "",
      password: "",
      status: "",
      // validation rules
      required,
      email,
    };
  },
  computed: {
    passwordToggleIcon() {
      return this.passwordFieldType === "password" ? "EyeIcon" : "EyeOffIcon";
    },
    login() {
      console.log("click");
      this.$refs.loginForm.validate().then((success) => {
        if (success) {
          console.log("start");
          useJwt
            .login({
              email: this.userEmail,
              password: this.password,
            })
            .then((response) => {
              console.log(response);
              let userData = response.data.member;
              Vue.prototype.$local.member = userData;

              console.log(userData);
              userData.id = userData.memberId;
              userData.memberId = userData.memberId;
              userData.username = userData.email;
              userData.fullName = userData.name;
              userData.avatar =
                "https://localhost:1234/file/down/" + userData.profileImage;
              userData.role = "admin";
              userData.ability = [{ action: "manage", subject: "all" }];
              //const { userData } = response.data
              useJwt.setToken(response.data.accessToken);
              useJwt.setRefreshToken(response.data.refreshToken);
              localStorage.setItem("userData", JSON.stringify(userData));
              this.$ability.update(userData.ability);

              // ? This is just for demo purpose as well.
              // ? Because we are showing eCommerce app's cart items count in navbar
              //this.$store.commit('app-ecommerce/UPDATE_CART_ITEMS_COUNT', userData.extras.eCommerceCartItemsCount)

              // ? This is just for demo purpose. Don't think CASL is role based in this case, we used role in if condition just for ease
              //this.$router.replace(getHomeRouteForLoggedInUser(userData.role))
              this.$router.replace("/main/edu-list").then(() => {
                this.$toast({
                  component: ToastificationContent,
                  position: "top-right",
                  props: {
                    title: `로그인 : ${userData.fullName || userData.username}`,
                    icon: "CoffeeIcon",
                    variant: "success",
                    text: `돌아오신걸 환영합니다!`,
                  },
                });
              });
            })
            .catch((error) => {
              console.log(error);
              if (error && error.status === 403) {
                //비밀 번호 틀렸을 때
                console.log("dfgdfg");
                this.$toast({
                  component: ToastificationContent,
                  position: "top-right",
                  props: {
                    title: `Welcome`,
                    icon: "CoffeeIcon",
                    variant: "danger",
                    text: `You have successfully logged in as Now you can start to explore!`,
                  },
                });
              }
              this.$refs.loginForm.setErrors(error.response.data.error);
            });
        }
      });
    },
  },
};
</script>

<style lang="scss">
@import "@core/scss/vue/pages/page-auth.scss";
</style>
