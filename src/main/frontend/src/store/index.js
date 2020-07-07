import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: ''
    },
    mutations: {
        setUserInfo(state, userInfo) {
            state.userInfo = userInfo;
        },
        updateToken(state, token) {
            state.userInfo && (state.userInfo.token = token);
        }
    },
    actions: {},
    modules: {},
    getters: {
        userInfo: state => {
            return state.userInfo;
        }
    },
    plugins: [createPersistedState({
        storage: window.localStorage
    })]
})
