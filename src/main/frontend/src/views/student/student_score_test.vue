<template>
    <div style="margin-top: 30px">
        <Button @click="analysisStudent" type="primary">分析</Button>
        <Table style="margin-top: 10px" border :columns="columns" :data="data"></Table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                columns: [
                    {
                        title: '学生名称',
                        key: 'studentName',
                    }, {
                        title: '考试名称',
                        key: 'testName',
                    },
                    {
                        title: '成绩',
                        key: 'score',
                    },
                    {
                        title: '排名',
                        key: 'rank',
                    }
                ],
                data: []
            }
        },
        methods: {
            loadData() {
                const _this = this;
                this.$request.get(`/test/student/${this.$route.params.id}`)
                    .then(value => {
                        _this.data = value.data;
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            },
            analysisStudent() {
                this.$router.push(`/analysis/student/${this.$route.params.id}`)
            }
        },
        created() {
            this.loadData();
        }
    }
</script>

<style scoped>

</style>