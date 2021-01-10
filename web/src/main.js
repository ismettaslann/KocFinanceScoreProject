import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import './plugins/bootstrap-vue'
import App from './App.vue'

Vue.config.productionTip = false

const app = new Vue({
  render: h => h(App),
}).$mount('#app')

app.use(VueAxios, axios);
