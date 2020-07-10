<style scoped>
    .layout {
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: auto;
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
    <Row class="layout">
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
                <router-view></router-view>
            </Content>
            <Footer class="layout-footer-center">2020-2100 &copy; TalkingData</Footer>

        </Layout>

    </Row>
</template>

