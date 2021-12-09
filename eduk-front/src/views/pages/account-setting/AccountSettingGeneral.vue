<template>
  <b-card>

    <!-- media -->
    <b-media no-body>
      <b-media-aside>
        <b-link>
          <b-img
            ref="previewEl"
            rounded
            :src="optionsLocal.avatar"
            height="80"
          />
        </b-link>
        <!--/ avatar -->
      </b-media-aside>

      <b-media-body class="mt-75 ml-75">
         <label for="image-file" class="uploadLabel">
            <span class="uploadbtn">업로드</span>
            <b-form-file
              id="image-file"
              ref="refInputEl"
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
    <b-form class="mt-2">
      <b-row>
        <b-col sm="6">
          <b-form-group
            label="이메일"
            label-for="account-e-mail"
          >
            <b-form-input
              v-model="member.email"
              name="email"
              placeholder="이메일"
            />

          </b-form-group>
        </b-col>
        <b-col sm="6">
          <b-form-group
            label="이름"
            label-for="account-name"
          >
            <b-form-input
              v-model="member.name"
              name="name"
              placeholder="이름"
            />
          </b-form-group>
        </b-col>
        
        <b-col sm="12">
          <b-form-group
            label="소개"
            label-for="account-company"
          >
            <b-form-input
              v-model="member.introduction"
              name="company"
              placeholder="소개"
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
  BFormFile, BButton, BForm, BFormGroup, BFormInput, BRow, BCol, BAlert, BCard, BCardText, BMedia, BMediaAside, BMediaBody, BLink, BImg,
} from 'bootstrap-vue'
import Ripple from 'vue-ripple-directive'
import { useInputImageRenderer } from '@core/comp-functions/forms/form-utils'
import { ref } from '@vue/composition-api'
import axios from 'axios'

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
      member: {
        eamil:'',
        name:'',
        introduction:'',
      },
    }
  },
  methods: {
    resetForm() {
      this.optionsLocal = JSON.parse(JSON.stringify(this.generalData))
    },
    saveProfile(){
      console.log('this.profileFile'+this.profileFile)
      if(this.profileFile != null){
      var frm = new FormData();
      /* var thisFile = document.getElementById("uploadFile");
      frm.append("file", thisFile.files[0]), */
      frm.append("file", this.profileFile)
      frm.append("memberId", this.member.memberId)
      console.log('NOT NULL - this.memberId : '+this.member.memberId)
      frm.append("email", this.member.email)
      console.log('this.email : '+this.email)
      frm.append("name", this.member.name)
      console.log('this.name : '+this.name)
      frm.append("introduction", this.member.introduction)
      console.log('this.introduction : '+this.introduction)
      frm.append("profileImage", this.profileFile.name)
      console.log('this.profileFile.name : '+this.profileFile.name)
      axios.post('https://localhost:1234/member/saveImage', frm, {
        headers:{
          'Content-Type': 'multipart/form-data'
        }})
      .then(res => {
        console.log('success')
      })
      .catch(err => {
        console.log(err)
      })
      }else{
        console.log('NULL인 경우 - this.profileFile : '+this.profileFile)
        console.log('this.memberId : '+this.member.memberId)
        console.log('this.email : '+this.member.email)
        console.log('this.name : '+this.member.name)
        console.log('this.introduction : '+this.member.introduction)
        this.$http.put('/member/updateMemberInfo',{
          "memberId": this.member.memberId,
          "email": this.member.email,
          "name": this.member.name,
          "introduction": this.member.introduction,
        })
        .then(res => {

        })
      }
    },
  },
  created(){
    this.$http.get('/member/selectMemberInfo')
        .then(res => { this.member= res.data; this.profileFile=res.data.profileImage; console.log(res.data)})
        .catch(err => {console.log(err)})
  },
  setup() {
    const refInputEl = ref(null)
    const previewEl = ref(null)

    const { inputImageRenderer } = useInputImageRenderer(refInputEl, previewEl)

    return {
      refInputEl,
      previewEl,
      inputImageRenderer,
    }
  },
}
</script>

<style>
  #image-file{
    display: none;
  }
  .uploadbtn, .resetbtn{
    border-radius: 5px;
    font-size: 14px;
  }
  .uploadbtn{
    padding: 6px 14px;
    background-color: #7367F0;
    color: #fff;
    margin-right: 10px;
  }
  .uploadbtn:hover{
    cursor: pointer;
    box-shadow: 0px 5px 10px -1px #7367F0;
  }
  .resetbtn{
    border: 1px solid #82868B;
    padding: 4px 14px;
    background-color: #fff;
    color: #82868B;
    margin-bottom: 10px;
  }
  .resetbtn:hover{
    background-color: #F5F5F6;
  }
</style>