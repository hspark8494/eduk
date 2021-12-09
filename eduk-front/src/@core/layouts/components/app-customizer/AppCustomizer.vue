<template>
  <div class="customizer d-md-block" :class="{ open: isCustomizerOpen }">
    <!-- Toggler -->
    <b-link
      class="customizer-toggle d-flex align-items-center justify-content-center"
      @click="isCustomizerOpen = !isCustomizerOpen"
    >
      <feather-icon icon="RadioIcon" size="15" class="spinner" />
    </b-link>
    <div>
      <openvidu-webcomponent></openvidu-webcomponent>
    </div>
  </div>
</template>
    
<script>
import {
  BFormRadioGroup,
  BFormGroup,
  BFormCheckbox,
  BCard,
  BCardText,
  BLink,
  BCardFooter,
  BButton,
} from "bootstrap-vue";
import vSelect from "vue-select";
import useAppCustomizer from "./useAppCustomizer";
import UserVideo from "./UserVideo";
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import Vue from "vue";
import VuePerfectScrollbar from "vue-perfect-scrollbar";
import $ from "jquery";
axios.defaults.headers.post["Content-Type"] = "application/json";

var OPENVIDU_SERVER_URL =
  "https://ec2-3-36-74-223.ap-northeast-2.compute.amazonaws.com";
var OPENVIDU_SERVER_SECRET = "1234";

export default {
  components: {
    // BSV
    UserVideo,
    BLink,
    BFormRadioGroup,
    BFormCheckbox,
    BFormGroup,
    BCard,
    BCardText,
    BCardFooter,
    BButton,
    // 3rd party
    vSelect,
    VuePerfectScrollbar,
  },
  setup() {
    const { isCustomizerOpen } = useAppCustomizer();

    // Perfect Scrollbar
    const perfectScrollbarSettings = {
      maxScrollbarLength: 60,
      wheelPropagation: false,
    };

    return {
      isCustomizerOpen,
      perfectScrollbarSettings,
    };
  },

  mounted() {
    var webComponent = document.querySelector("openvidu-webcomponent");

    const userData = JSON.parse(localStorage.getItem("userData"));

    if (
      webComponent.getAttribute("openvidu-secret") != undefined &&
      webComponent.getAttribute("openvidu-server-url") != undefined
    ) {
      webComponent.style.display = "block";
    }

    webComponent.addEventListener("sessionCreated", (event) => {
      var session = event.detail;

      // You can see the session documentation here
      // https://docs.openvidu.io/en/stable/api/openvidu-browser/classes/session.html

      session.on("connectionCreated", (e) => {
        console.log("connectionCreated", e);
      });

      session.on("streamDestroyed", (e) => {
        console.log("streamDestroyed", e);
      });

      session.on("streamCreated", (e) => {
        console.log("streamCreated", e);
      });

      session.on("sessionDisconnected", (event) => {
        console.warn("sessionDisconnected event");
        document.body.style.backgroundColor = "white";
        form.style.display = "block";
        webComponent.style.display = "none";
      });

      session.on("exception", (exception) => {
        console.warn(exception);
      });
    });

    webComponent.addEventListener("publisherCreated", (event) => {
      var publisher = event.detail;

      // You can see the publisher documentation here
      // https://docs.openvidu.io/en/stable/api/openvidu-browser/classes/publisher.html

      publisher?.on("streamCreated", (e) => {
        console.warn("Publisher streamCreated", e);
      });

      publisher?.on("streamPlaying", (e) => {
        console.warn("Publisher streamPlaying", e);
      });

      webComponent.style.display = "block";
    });

    webComponent.addEventListener("error", (event) => {
      console.log("Error event", event.detail);
    });

    async function joinSession() {
      var sessionName = "room";
      var user = userData.fullName;
      var webComponent = document.querySelector("openvidu-webcomponent");
      var ovSettings = {
        chat: true,
        autopublish: true,
        toolbar: true,
        footer: false,
        toolbarButtons: {
          audio: true,
          video: true,
          screenShare: true,
          fullscreen: true,
          layoutSpeaking: true,
          exit: true,
        },
      };
      var tokens = [];

      if (
        webComponent.getAttribute("openvidu-secret") != undefined &&
        webComponent.getAttribute("openvidu-server-url") != undefined
      ) {
        location.reload();
      } else {
        var token1 = await getToken(sessionName);
        var token2 = await getToken(sessionName);
        tokens.push(token1, token2);
        webComponent.sessionConfig = { sessionName, user, tokens, ovSettings };
      }
    }

    function getToken(sessionName) {
      return createSession(sessionName).then((sessionId) =>
        createToken(sessionId)
      );
    }

    function createSession(sessionName) {
      // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
      return new Promise((resolve, reject) => {
        $.ajax({
          type: "POST",
          url: OPENVIDU_SERVER_URL + "/openvidu/api/sessions",
          data: JSON.stringify({ customSessionId: sessionName }),
          headers: {
            Authorization:
              "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
          success: (response) => resolve(response.id),
          error: (error) => {
            if (error.status === 409) {
              resolve(sessionName);
            } else {
              console.warn(
                "No connection to OpenVidu Server. This may be a certificate error at " +
                  OPENVIDU_SERVER_URL
              );
              if (
                window.confirm(
                  'No connection to OpenVidu Server. This may be a certificate error at "' +
                    OPENVIDU_SERVER_URL +
                    '"\n\nClick OK to navigate and accept it. ' +
                    'If no certificate warning is shown, then check that your OpenVidu Server is up and running at "' +
                    OPENVIDU_SERVER_URL +
                    '"'
                )
              ) {
                location.assign(OPENVIDU_SERVER_URL + "/accept-certificate");
              }
            }
          },
        });
      });
    }

    function createToken(sessionId) {
      // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
      return new Promise((resolve, reject) => {
        $.ajax({
          type: "POST",
          url:
            OPENVIDU_SERVER_URL +
            "/openvidu/api/sessions/" +
            sessionId +
            "/connection",
          data: JSON.stringify({}),
          headers: {
            Authorization:
              "Basic " + btoa("OPENVIDUAPP:" + OPENVIDU_SERVER_SECRET),
            "Content-Type": "application/json",
          },
          success: (response) => resolve(response.token),
          error: (error) => reject(error),
        });
      });
    }
    joinSession();
  },
};
</script>

<style lang="scss">
@import "@core/scss/vue/libs/vue-select.scss";
</style>

<style lang="scss" scoped>
@import "~@core/scss/base/bootstrap-extended/include";
@import "~@core/scss/base/components/variables-dark";
.customizer-section {
  padding: 1.5rem;
  border-bottom: 1px solid #ebe9f1;

  .dark-layout & {
    border-color: $theme-dark-border-color;
  }

  #skin-radio-group ::v-deep {
    .custom-control-inline {
      margin-right: 0.7rem;
    }
  }

  .form-group {
    margin-bottom: 1.5rem;
    &:last-of-type {
      margin-bottom: 0;
    }
    ::v-deep legend {
      font-weight: 500;
    }
  }
}

.mark-active {
  box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.5);
}

.ps-customizer-area {
  height: calc(100% - 83px);
}

app-toolbar-logo {
  visibility: hidden !important;
}
#messageInput {
  height: 50px !important;
  padding: 0px !important;
}
</style>