<template>
    <div class="blog-detail-wrapper">
      <b-row>
        <b-col
          id="file-box"
          cols="12"
          class="mt-2"
        >
          <h5 class="section-label">
            파일 목록
          </h5>
          <a
            v-for="(file,index) in fileList"
            :key="index"
            :href="downURL+file.fileName">
          <b-card>
            <b-media no-body>
              <b-media-aside class="mr-15">
                <feather-icon
                  icon="SaveIcon"
                  size="38"
                />
              </b-media-aside>
              <b-media-body>
                <b-card-text class="p-mb-5">{{ file.fileName}}</b-card-text>
                <b-card-text>{{ file.createTime }}</b-card-text>
              </b-media-body>
            </b-media>
          </b-card>
          </a>
        </b-col>

      </b-row>
    </div>

</template>

<script>
import {
  BCard, BRow, BCol, BCardText,
} from 'bootstrap-vue'

export default {
  components: {
    BCard,
    BRow,
    BCol,
    BCardText,
  },
  data() {
    return {
      fileList: null,
      downURL: 'https://localhost:1234',
    }
  },  
  created() {
   this.$http.get('/file/list/'+this.$route.params.classRoomId)
   .then(res => {
     console.log(res);
     this.fileList = res.data;
     this.downURL = this.downURL+'/file/down/';
   })
   .catch(err => {
     console.log(err);
   })
  },
  methods: {
  },
}
</script>

<style lang="scss">
@import '@core/scss/vue/pages/page-blog.scss';
</style>
<style>
  #file-box a, #file-box h5{
    text-decoration: none;
    color: #5E5873;
  }
  #file-box a:hover, #file-box h5:hover{
    color: #7367F0;
  }
  #file-box b-media-aside, #file-box b-media-body{
    margin-right: 10px;
    float: left;
  }
  .fileSpan{
    margin-left: 5px;
  }
  .fileDownload{
    color: #000;
  }
  .fileDownload :hover{
      cursor: pointer;
  }
  .p-mb-5{
    margin-bottom:2px;
    font-weight: bold;
  }
</style>