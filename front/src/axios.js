import Vue from 'vue'

// axios
import axios from 'axios'

const axiosIns = axios.create({
  baseURL: "http://localhost:8081",
  headers: {
      "Content-type": "application/json"
  },
  timeout : 1000
})

Vue.prototype.$axios = axiosIns

export default axiosIns