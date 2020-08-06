<template>
    <div style="margin-top: 30px">
        <Spin size="large" fix v-if="spinShow"></Spin>
        <Button @click="downloadScore" style="margin-left: 10px" type="primary">导出成绩模板</Button>
        <Button ghost type="text">
            <Upload :on-success="uploadScore"
                    :on-error="changeStatus"
                    :before-upload="changeStatus"
                    :action="'//192.168.1.4:8080/score/upload?clazzId='+this.$route.params.id">
                <Button icon="ios-cloud-upload-outline">导入成绩</Button>
            </Upload>
        </Button>
        <Button style="margin-left: 10px" @click="editScored" type="primary">编辑</Button>
        <Button style="margin-left: 10px" @click="saveAllScore" type="primary">保存</Button>
        <Button style="margin-left: 10px" @click="analysisTest" type="primary">分析</Button>
        <Table style="margin-top: 10px" border :columns="columns" :data="data"></Table>

        <Modal>

        </Modal>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                spinShow: false,
                scoreModal: false,
                editable: false,
                loading: true,
                columns: [
                    {
                        title: '学号',
                        key: 'studentNo',
                        fixed: 'left',
                        sortable: true
                    },
                    {
                        title: '姓名',
                        key: 'studentName',
                        sortable: true,
                        filters: [                             // 过滤条件
                            {
                                label: '不及格',
                                value: 'EXAM_FLUNK'
                            },
                            {
                                label: '未考试',
                                value: 'EXAM_NO'
                            },
                            {
                                label: '及格',
                                value: 'EXAM_PASS'
                            }
                        ],
                        filterMethod: (value, row) => {
                            console.log(value)
                        }
                    },
                    {
                        title: '成绩',
                        key: 'score',
                        sortable: true,
                        render: (h, params) => {
                            const _this = this;
                            if (this.editable) {
                                return h('div', [
                                    h('Input', {
                                        props: {
                                            value: params.row.score
                                        },
                                        on: {
                                            input: function (value) {
                                                params.row.score = value;
                                            },
                                            'on-blur': function () {
                                                _this.saveScore(params.row);
                                            }
                                        }
                                    })
                                ])
                            } else {
                                return h('div', params.row.score);
                            }
                        }
                    }
                ],
                data: []
            }
        },
        methods: {
            changeStatus() {
                this.spinShow = !this.spinShow
            },
            analysisTest(){
                this.$router.push(`/analysis/test/${this.$route.params.testId}`)
            },
            loadData() {
                const _this = this;
                this.$request.get('/score/findByTestIdAndClazzId?testId=' + this.$route.params.testId + '&clazzId=' + this.$route.params.id)
                    .then(value => {
                        _this.data = value.data;
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            },
            downloadScore() {
                window.open(`/score/download?clazzId=${this.$route.params.id}&testId=${this.$route.params.testId}`, '_blank')
            },
            uploadScore() {
                this.loadData();
                this.spinShow = !this.spinShow
            },
            editScored() {
                this.editable = true;
            },
            saveAllScore() {
                this.editable = false;
            },
            saveScore(row) {
                this.$request.post('/score/',
                    {
                        score: row.score,
                        testId: row.testId,
                        studentId: row.studentId
                    })
                    .then(value => {
                    }).catch(reason => {
                    this.$Message.error("创建失败," + reason);
                })
            }
        },
        created() {
            this.loadData();
        }
    }
</script>

<style scoped>

</style>