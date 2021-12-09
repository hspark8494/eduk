<template>
  <b-card>
    <!-- media -->
    <b-media no-body>
      <b-media-aside>
        <div id="profile-image"></div>
        <!--/ avatar -->
      </b-media-aside>

      <b-media-body class="mt-75 ml-75">
        <label for="image-file" class="uploadLabel">
          <span class="uploadbtn">업로드</span>
          <b-form-file
            id="image-file"
            v-model="profileFile"
            accept=".jpg, .png, .gif"
            plain
          />
        </label>
        <!--/ upload button -->

        <!-- reset -->
        <button class="resetbtn">리셋</button>
        <!--/ reset -->
        <b-card-text>JPG, GIF, PNG 허용 / 최대 크기 800kB</b-card-text>
      </b-media-body>
    </b-media>
    <!--/ media -->

    <!-- form -->
    <b-form class="mt-3">
      <b-row>
        <b-col sm="6">
          <b-form-group label="이메일" label-for="account-e-mail">
            <b-form-input
              v-model="member.email"
              name="email"
              placeholder="이메일"
              disabled
            />
          </b-form-group>
        </b-col>
        <b-col sm="6">
          <b-form-group label="이름" label-for="account-name">
            <b-form-input
              v-model="member.name"
              name="name"
              placeholder="이름"
            />
          </b-form-group>
        </b-col>

        <b-col sm="12">
          <b-form-group label="상태 메세지" label-for="account-company">
            <b-form-input
              v-model="member.introduction"
              name="company"
              placeholder="안녕하세요!"
            />
          </b-form-group>
        </b-col>

        <b-col cols="12">
          <b-button
            v-ripple.400="'rgba(255, 255, 255, 0.15)'"
            variant="primary"
            class="mt-2 mr-1"
            @click="saveProfile"
          >
            저장
          </b-button>
          <b-button
            v-ripple.400="'rgba(186, 191, 199, 0.15)'"
            variant="outline-secondary"
            type="reset"
            class="mt-2"
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
  BFormFile,
  BButton,
  BForm,
  BFormGroup,
  BFormInput,
  BRow,
  BCol,
  BAlert,
  BCard,
  BCardText,
  BMedia,
  BMediaAside,
  BMediaBody,
  BLink,
  BImg,
} from "bootstrap-vue";
import Ripple from "vue-ripple-directive";
import { useInputImageRenderer } from "@core/comp-functions/forms/form-utils";
import { ref } from "@vue/composition-api";
import axios from "axios";
import ToastificationContent from "@core/components/toastification/ToastificationContent.vue";

export default {
  components: {
    BButton,
    BForm,
    BImg,
    BFormFile,
    BFormGroup,
    BFormInput,
    BRow,
    BCol,
    BAlert,
    BCard,
    BCardText,
    BMedia,
    BMediaAside,
    BMediaBody,
    BLink,
  },
  directives: {
    Ripple,
  },
  props: {
    generalData: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      optionsLocal: JSON.parse(JSON.stringify(this.generalData)),
      profileFile: null,
      profileIamge: null,
      member: {
        eamil: "",
        name: "",
        introduction: "",
      },
    };
  },
  methods: {
    resetForm() {
      this.optionsLocal = JSON.parse(JSON.stringify(this.generalData));
    },
    saveProfile() {
      console.log("this.profileFile" + this.profileFile);
      if (this.profileFile != null) {
        var frm = new FormData();
        var thisFile = document.getElementById("image-file");
        frm.append("file", thisFile.files[0]);
        //frm.append("file", this.profileFile)
        frm.append("memberId", this.member.memberId);
        frm.append("email", this.member.email);
        frm.append("name", this.member.name);
        frm.append("password", this.member.password);
        frm.append("introduction", this.member.introduction);
        frm.append("profileImage", this.profileFile.name);
        axios
          .post("https://localhost:1234/member/saveImage", frm, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((res) => {
            this.toast();
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.$http
          .put("/member/updateMemberInfo", {
            memberId: this.member.memberId,
            email: this.member.email,
            name: this.member.name,
            introduction: this.member.introduction,
            password: this.member.password,
          })
          .then((res) => {
            this.toast();
          });
      }
    },
    toast() {
      this.$toast({
        component: ToastificationContent,
        position: "top-right",
        props: {
          title: `변경 완료`,
          icon: "CoffeeIcon",
          variant: "success",
        },
      });
    },
  },
  created() {
    this.$http
      .get("/member/selectMemberInfo")
      .then((res) => {
        this.member = res.data;
        this.profileImage = res.data.profileImage;
        document.getElementById("profile-image").style.backgroundRepeat =
          "no-repeat";
        document.getElementById("profile-image").style.backgroundImage =
          "url('/../../../../../src/main/webapp/save/" +
          this.profileImage +
          "')";
        document.getElementById("profile-image").style.backgroundSize =
          "100% 100%";
      })
      .catch((err) => {
        console.log(err);
      });
  },
  setup() {
    const refInputEl = ref(null);
    const previewEl = ref(null);

    const { inputImageRenderer } = useInputImageRenderer(refInputEl, previewEl);

    return {
      refInputEl,
      previewEl,
      inputImageRenderer,
    };
  },
};
</script>

<style>
#profile-image {
  width: 80px;
  height: 80px;
}
#image-file {
  display: none;
}
.uploadbtn,
.resetbtn {
  border-radius: 5px;
  font-size: 14px;
}
.uploadbtn {
  padding: 6px 14px;
  background-color: #7367f0;
  color: #fff;
  margin-right: 10px;
}
.uploadbtn:hover {
  cursor: pointer;
  box-shadow: 0px 5px 10px -1px #7367f0;
}
.resetbtn {
  border: 1px solid #82868b;
  padding: 4px 14px;
  background-color: #fff;
  color: #82868b;
  margin-bottom: 10px;
}
.resetbtn:hover {
  background-color: #f5f5f6;
}
</style>