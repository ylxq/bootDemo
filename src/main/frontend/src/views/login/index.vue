<template>
    <div class="login-page">
        <div class="login-form">
            <div class="logo">
                <div class="logo-ico"></div>
                <div class="logo-name">
                    API中台-网关管理系统
                </div>
            </div>
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate">
                <FormItem prop="userName">
                    <Input v-model="formValidate.userName" placeholder="用户名" @on-keypress="focusPass($event)"></Input>
                </FormItem>
                <FormItem prop="password">
                    <Input ref="pass" @on-keypress="enterSubmit($event)" v-model="formValidate.password" type="password"
                           placeholder="密码"></Input>
                </FormItem>
                <form-item>
                    <Button type="primary" :loading="logining" long @click="handleSubmit()">登&nbsp;&nbsp;&nbsp;录
                    </Button>
                </form-item>
            </Form>
        </div>

    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                logining: false,
                formValidate: {
                    userName: '',
                    password: ''
                },
                ruleValidate: {
                    userName: [
                        {required: true, message: '用户名不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            focusPass($event) {
                if ($event.keyCode === 13)
                    this.$refs.pass.focus();
            },
            enterSubmit($event) {
                if ($event.keyCode === 13)
                    this.handleSubmit();
            },
            handleSubmit() {
                const _this = this;
                this.$refs['formValidate'].validate((valid) => {
                    if (valid) {
                        //登录操作
                        _this.logining = true;
                        _this.$request.post('/login',
                            this.formValidate, {
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded'
                                }
                            })
                            .then(value => {
                                console.log(value)
                                if (value.data.code === 200) {
                                    //登录成功
                                    _this.$store.commit("setUserInfo", value.data.data);
                                    _this.$request.defaults.headers['token'] = value.data.data.id;
                                    _this.$router.push("/");
                                } else {
                                    _this.$Message.error(value.data.message || "登录失败")
                                }
                                _this.logining = false;
                            }).catch(reason => {
                            _this.logining = false;
                            _this.$Message.error("登录失败," + reason)
                        })
                    }
                })
            }
        }
    }
</script>

<style lang="less" scoped>
    .login-page {
        background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
        width: 100%;
        height: 100%;

        .logo {
            display: inline-block;
            height: 74px;

            .logo-ico {
                height: 53px;
                display: inline-block;
                width: 82px;
                background: url('../../asserts/images/logo.png') no-repeat;
            }

            .logo-name {
                padding-left: 1em;
                height: 53px;
                display: inline-block;
                line-height: 53px;
                vertical-align: top;
                font-size: 23px;
            }
        }

        .login-form {
            width: 400px;
            padding: 40px 30px 10px;
            border-radius: 10px;
            background: #FFFFFF;
            box-shadow: 0 0 10px #adadad;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -60%);
        }


    }

</style>