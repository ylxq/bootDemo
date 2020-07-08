import Vue from 'vue'
import ViewUI from 'view-design';
import VueRouter from 'vue-router';
import Routers from './router';
import App from './app.vue';
import 'view-design/dist/styles/iview.css';
import request from './request'
import store from './store/index'

Vue.prototype.$request = request;
Vue.use(VueRouter);
Vue.use(ViewUI);

// 路由配置
const RouterConfig = {
    // mode: 'history',
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    ViewUI.LoadingBar.start();
    if (to.path !== '/login' && !store.getters.userInfo) {
        router.push({path: '/login'}).then(r => next());
    } else if (to.path === '/login' && store.getters.userInfo) {
        router.push({path: '/clazz'}).then(r => next());
    } else if (to.path === '/') {
        router.push({path: '/clazz'}).then(r => next());
    } else {
        next();
    }
});

router.afterEach((to, from, next) => {
    ViewUI.LoadingBar.finish();
    window.scrollTo(0, 0);
});

new Vue({
    el: '#app',
    router: router,
    store: store,
    render: h => h(App)
});
