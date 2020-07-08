<template>
    <Table style="margin-top: 20px" border :columns="columns" :data="data"></Table>
</template>

<script>
    export default {
        data() {
            return {
                columns: [
                    {
                        title: '学号',
                        key: 'xueHao'
                    },{
                        title: '名字',
                        key: 'name'
                    },
                    {
                        title: '性别',
                        key: 'sex'
                    },
                    {
                        title: '身份证',
                        key: 'cardCode'
                    },
                    {
                        title: '地址',
                        key: 'address'
                    }
                ],
                data: []
            }
        },
        methods: {
            loadData() {
                const _this = this;
                this.$request.get('/student/?clazzId=' + this.$route.params.id)
                    .then(value => {
                        console.log(value)
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            }
        },
        created() {
            this.loadData();
        }
    }
</script>

<style scoped>

</style>