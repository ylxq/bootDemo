<style scoped>
    .layout {
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }

    .layout-logo {
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }

    .layout-nav {
        width: 420px;
        margin: 0 auto;
        margin-right: 20px;
    }

    .layout-footer-center {
        text-align: center;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <Header>
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                        <MenuItem name="4" style="float: right">
                            <Avatar style="background-color: #87d068" icon="ios-person"/>
                            欢迎您！{{$store.getters.userInfo.name}}
                        </MenuItem>
                    </div>
                </Menu>
            </Header>
            <Content :style="{padding: '0 50px'}">
                <Card :style="{margin: '50px 0'}" style="min-height: 600px;">
                    <p slot="title">
                        <Icon type="ios-film-outline"></Icon>
                        Classic film
                    </p>
                    <a href="#" slot="extra" @click.prevent="addClazz">
                        <Icon type="ios-loop-strong"></Icon>
                        Change
                    </a>
                    <div>
                        Content
                    </div>

                </Card>
            </Content>
            <Footer class="layout-footer-center">2020-2016 &copy; TalkingData</Footer>
        </Layout>
    </div>
</template>
<script>
    export default {
        data() {
            return {}
        },
        methods: {
            addClazz() {

            }
        }
    }
</script>
