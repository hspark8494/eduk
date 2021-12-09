<template>
  <!-- content -->
  <div class="blog-detail-wrapper">
    <b-row>
      <!-- blogs -->
      <b-col cols="12">
        <b-card :title="postDetail.postTitle">
          <b-media no-body>
            <b-media-aside vertical-align="center" class="mr-50">
              <b-avatar size="24" :src="postDetail.member.profileImage" />
            </b-media-aside>
            <b-media-body>
              <small class="text-muted mr-50">by</small>
              <small>
                <b-link class="text-body">{{ postDetail.member.name }}</b-link>
              </small>
              <span class="text-muted ml-75 mr-50">|</span>
              <small class="text-muted">{{ postDetail.createdTime }}</small>
            </b-media-body>
          </b-media>
          <div class="my-1 py-25"></div>
          <!-- eslint-disable vue/no-v-html -->
          <div class="blog-content" v-html="postDetail.postContent" />
        </b-card>
      </b-col>
      <!--/ blogs -->

      <b-col v-if="fileName != null">
        <h6 class="section-label">첨부파일</h6>
        <b-card>
          <a class="fileDownload" :href="downURL">
            <feather-icon icon="DownloadIcon" size="18" />
            <span class="fileSpan">{{ fileName }}</span>
          </a>
        </b-card>
      </b-col>

      <b-col cols="12" class="mt-2">
        <h6 class="section-label">댓글 달기</h6>
        <b-card>
          <b-form>
            <b-row>
              <b-col cols="12">
                <b-form-group class="mb-2">
                  <b-form-textarea
                    v-model="replyWrite"
                    name="textarea"
                    rows="4"
                    placeholder="댓글 입력"
                  />
                </b-form-group>
              </b-col>
              <b-col cols="12">
                <b-button
                  v-ripple.400="'rgba(255, 255, 255, 0.15)'"
                  variant="primary"
                  @click="write"
                >
                  작성
                </b-button>
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>

      <!-- blog comment -->
      <b-col id="blogComment" cols="12" class="mt-2">
        <h6 class="section-label">댓글</h6>
        <div v-for="(reply, index) in replies" :key="index">
          <b-card v-if="reply.parentId == null">
            <b-media no-body>
              <b-media-aside class="mr-15">
                <b-avatar :src="reply.member.profileImage" size="38" />
              </b-media-aside>
              <b-media-body>
                <h6 class="font-weight-bolder mb-25">
                  {{ reply.member.name }}
                </h6>
                <b-card-text>{{ reply.createdTime }}</b-card-text>
                <b-card-text>
                  {{ reply.replyContent }}
                </b-card-text>
                <b-link>
                  <div
                    class="d-inline-flex align-items-center"
                    @click="showArea"
                  >
                    <feather-icon
                      icon="CornerUpLeftIcon"
                      size="18"
                      class="mr-50"
                    />
                    <span>답글</span>
                  </div>
                </b-link>
                <div v-if="hiddenArea">
                  <b-col cols="12">
                    <b-form-group class="mb-2">
                      <b-form-textarea
                        v-model="reReplyWrite"
                        rows="2"
                        placeholder="답글 입력"
                      />
                    </b-form-group>
                  </b-col>
                  <b-col cols="12">
                    <b-button
                      v-ripple.400="'rgba(255, 255, 255, 0.15)'"
                      variant="primary"
                      @click="write"
                    >
                      작성
                    </b-button>
                  </b-col>
                </div>
                <!-- <div
                  v-if="reply.parentId!=reply.replyId"
                    >
                      <div>
                        <b-avatar
                          :src="rep.member.profileImage"
                          size="26"
                        />
                      </div>
                      <div>
                        <h6 class="font-weight-bolder mb-25">
                          {{ rep.member.name}}
                        </h6>
                        <b-card-text>{{ rep.createdTime }}</b-card-text>
                        <b-card-text>
                          {{ rep.replyContent }}
                        </b-card-text>
                      </div>
                    </div> -->
              </b-media-body>
            </b-media>
          </b-card>
        </div>
      </b-col>
      <!--/ blog comment -->
    </b-row>
    <!--/ blogs -->
  </div>
  <!--/ content -->
</template>

<script>
import {
  BFormInput,
  BMedia,
  BAvatar,
  BMediaAside,
  BMediaBody,
  BImg,
  BLink,
  BFormGroup,
  BInputGroup,
  BInputGroupAppend,
  BCard,
  BRow,
  BCol,
  BBadge,
  BCardText,
  BDropdown,
  BDropdownItem,
  BForm,
  BFormTextarea,
  BFormCheckbox,
  BButton,
} from "bootstrap-vue";
import Ripple from "vue-ripple-directive";
import { kFormatter } from "@core/utils/filter";

export default {
  components: {
    BFormInput,
    BMedia,
    BAvatar,
    BMediaAside,
    BMediaBody,
    BLink,
    BCard,
    BRow,
    BCol,
    BFormGroup,
    BInputGroup,
    BInputGroupAppend,
    BImg,
    BBadge,
    BCardText,
    BDropdown,
    BForm,
    BDropdownItem,
    BFormTextarea,
    BFormCheckbox,
    BButton,
  },
  directives: {
    Ripple,
  },
  data() {
    return {
      search_query: "",
      commentCheckmark: "",
      replyWrite: "",
      reReplyWrite: "",
      postDetail: {
        member: { name: "" },
        createdTime: "",
        postTitle: "",
        postContent: "",
      },
      replies: [],
      fileName: null,
      downURL: "https://localhost:1234",
      hiddenArea: false,
    };
  },
  created() {
    this.$http
      .get(
        "/" + this.$route.params.boardId + "/post/" + this.$route.params.postId
      )
      .then((res) => {
        this.postDetail = res.data;
        console.log(res.data);
        this.$http
          .get("/file/info/" + this.$route.params.postId)
          .then((response) => {
            this.fileName = response.data.fileName;
            this.downURL = this.downURL + "/file/down/" + this.fileName;
          });
        this.refreshReply();
      });
  },
  methods: {
    kFormatter,
    write() {
      this.$http
        .post("/" + this.$route.params.postId + "/reply", {
          replyContent: this.replyWrite,
        })
        .then((res) => {
          this.refreshReply();
        });
    },
    down() {
      this.$http
        .get("/file/down/" + this.fileName)
        .then((resp) => {
          console.log("success");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    refreshReply: function () {
      this.$http
        .get("/" + this.$route.params.postId + "/reply")
        .then((resp) => {
          this.replies = resp.data;
          console.log(resp.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    showArea() {
      this.hiddenArea = true;
    },
  },
};
</script>

<style lang="scss">
@import "@core/scss/vue/pages/page-blog.scss";
</style>
<style>
.fileSpan {
  margin-left: 5px;
}
.fileDownload {
  color: #000;
}
.fileDownload :hover {
  cursor: pointer;
}

.blog-content {
  min-height: 200px;
}
</style>