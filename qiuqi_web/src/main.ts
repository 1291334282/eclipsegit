import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import Qs from 'qs';
import echarts from 'echarts'
Vue.prototype.$echarts = echarts
Vue.prototype.$qs = Qs
Vue.prototype.$axios = axios
Vue.config.productionTip = false;
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
