import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from 'axios';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.config.productionTip = false;
// 設置 Axios 的全局基礎 URL
axios.defaults.baseURL = 'http://localhost:8080';

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

new Vue({
    router,
    render: (h) => h(App),
}).$mount("#app");
