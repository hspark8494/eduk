<template>
  <div>
    <!-- Table Container Card -->
    <b-card no-body class="mb-0">
      <div class="m-2">
        <b-button variant="primary" @click="write">
          <span class="text-nowrap">글쓰기</span>
        </b-button>
      </div>

      <table>
        <colgroup>
          <col width="10%" />
          <col width="35%" />
          <col width="20%" />
          <col width="10%" />
          <col width="35%" />
        </colgroup>
        <tr class="th">
          <td>글번호</td>
          <td>제목</td>
          <td>작성자</td>
          <td>조회수</td>
          <td>작성일</td>
        </tr>
        <tr v-if="postNull">
          <td colspan="10">
            <p>등록된 게시물이 없습니다.</p>
          </td>
        </tr>
        <tr
          onmouseover="this.style.background='#eaeaea'"
          onmouseout="this.style.background='white'"
          v-for="(post, index) in postList.content"
          :key="index"
        >
          <td>
            {{ post.postId }}
          </td>
          <td>
            <router-link
              :to="{
                name: 'post-read',
                params: { boardId: $route.params.boardId, postId: post.postId },
              }"
              >{{ post.postTitle }}
            </router-link>
          </td>
          <td>
            {{ post.member.name }}
          </td>
          <td>
            {{ post.readnum }}
          </td>
          <td>
            {{ post.createdTime.substring(0, 19).replace("T", "\t") }}
          </td>
        </tr>
      </table>
      <nav class="pagination-container">
        <div class="pagination">
          <p class="pagination-inner">
            <feather-icon
              class="custombtn prevbtn"
              icon="ChevronLeftIcon"
              size="18"
              v-if="startPage - blockCount > 0"
              @click="prevPage"
            />
            <span v-for="(p, i) in postList.content" :key="i">
              <!-- <a class="#"
                          href="#"
                          v-if="i<blockCount&&postList.totalPages>=(startPage+i)"
                          >{{startPage+i}}</a> -->
              <button
                type="button"
                class="custombtn"
                v-if="
                  i < blockCount &&
                  postList.totalPages >= startPage + i &&
                  nowPage != startPage + i
                "
                @click="movePage"
              >
                {{ startPage + i }}
              </button>
              <button
                type="button"
                class="pagination-active"
                v-if="nowPage == startPage + i"
                @click="movePage"
              >
                {{ startPage + i }}
              </button>
            </span>
            <feather-icon
              class="custombtn nextbtn"
              icon="ChevronRightIcon"
              size="18"
              v-if="startPage + blockCount <= postList.totalPages"
              @click="nextPage"
            />
          </p>
        </div>
      </nav>
    </b-card>
  </div>
</template>

<script>
import {
  BCard,
  BRow,
  BCol,
  BFormInput,
  BButton,
  BTable,
  BMedia,
  BAvatar,
  BLink,
  BBadge,
  BDropdown,
  BDropdownItem,
  BPagination,
} from "bootstrap-vue";
import vSelect from "vue-select";
import store from "@/store";
import { ref, onUnmounted } from "@vue/composition-api";
import { avatarText } from "@core/utils/filter";

export default {
  components: {
    BCard,
    BRow,
    BCol,
    BFormInput,
    BButton,
    BTable,
    BMedia,
    BAvatar,
    BLink,
    BBadge,
    BDropdown,
    BDropdownItem,
    BPagination,

    vSelect,
  },
  data() {
    return {
      postList: {
        board: { boardId: "" },
        postId: "",
        postTitle: "",
        readnum: "",
        createdTime: "",
        member: { name: "" },
      },
      postNull: true,
      blockCount: "",
      startPage: "",
      nowPage: "",
    };
  },
  watch: {
    $route(to, from) {
      this.refreshList();
    },
  },
  created() {
    this.refreshList();
  },
  methods: {
    write() {
      this.$router.push("/" + this.$route.params.boardId + "/postWrite");
    },
    prevPage() {
      this.$router.push(
        "/" + this.$route.params.boardId + "/postList/" + (this.startPage - 1)
      );
    },
    nextPage() {
      this.$router.push(
        "/" +
          this.$route.params.boardId +
          "/postList/" +
          (this.startPage + this.blockCount)
      );
    },
    movePage: function (event) {
      console.log(event);
      const targetId = event.target.innerText;
      this.$router.push(
        "/" + this.$route.params.boardId + "/postList/" + targetId
      );
    },
    refreshList: function () {
      this.$http
        .get(
          this.$route.params.boardId +
            "/post?nowPage=" +
            this.$route.params.nowPage
        )
        .then((res) => {
          this.postList = res.data.postList;
          console.log(res.data);
          if (res.data.postList.empty == false) this.postNull = false;
          this.blockCount = res.data.blockCount;
          this.startPage = res.data.startPage;
          this.nowPage = res.data.nowPage;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    parseTime(timeStr) {
      return timeStr.substring(0, 19).replace("T", " ");
    },
  },
};
</script>

<style lang="scss" scoped>
.per-page-selector {
  width: 90px;
}
</style>

<style lang="scss">
@import "@core/scss/vue/libs/vue-select.scss";
</style>

<style>
table {
  text-align: center;
}
td {
  padding: 20px;
}
.pagination-inner {
  border-radius: 25px;
  background-color: #f3f2f7;
}
.custombtn,
.pagination-active {
  width: 25px;
  height: 25px;
  border-radius: 50%;
  border: none;
}
.th,
.custombtn {
  background-color: #f3f2f7;
}
.custombtn:hover {
  transition: 0.2s ease-out;
  background-color: #e3e1ec;
}
.prevbtn,
.nextbtn {
  transition-property: background-color;
  transition-duration: 0.2s;
  transition-timing-function: ease-out;
  margin: 0 5px;
}
.prevbtn:hover,
.nextbtn:hover {
  cursor: pointer;
}
.pagination-active,
.prevbtn:hover,
.nextbtn:hover {
  background-color: #7367f0;
  color: #fff;
}
.m-2 {
  text-align: right;
}
.pagination-container {
  text-align: center;
  margin-top: 15px;
}
.pagination {
  display: inline-block;
}
</style>