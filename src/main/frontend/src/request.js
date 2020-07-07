import axios from 'axios';
import qs from 'qs'
import router from './router'
import store from './store'

axios.defaults.timeout = 5000;
axios.defaults.baseURL = process.env.VUE_APP_BASE_URL; //填写域名

//http request 拦截器
axios.interceptors.request.use(
    config => {
        config.data = config.data || {};
        if (config.headers['Content-Type'] === 'application/x-www-form-urlencoded') {
            config.data = qs.stringify(config.data);
        }
        return config;
    },
    err => {
        return Promise.reject(err);
    }
);

axios.interceptors.response.use(value => {
        if (value && value.data) {
            //未登录
            if (value.data.code === 401) {
                router.push({path: '/login'}).catch(reason => {
                });
            }
            if (value.headers['refresh-token']) {
                store.commit("updateToken", value.headers['refresh-token']);
                axios.defaults.headers['token'] = value.headers['refresh-token'];
            }
        }
        return Promise.resolve(value);
    },
    error => {
        return Promise.reject(error);
    });

export default axios;