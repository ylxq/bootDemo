<template>
    <div style="margin-top: 30px">
        <Button @click="addTest" type="primary">新增</Button>
        <Button @click="analysisAll" type="primary">分析</Button>
        <Table style="margin-top: 10px" border :columns="columns" :data="data"></Table>
        <Modal :loading="loading"
               :mask-closable="false"
               v-model="showAddClazzModal"
               :title="formValidate.name?formValidate.name:'新增考试'"
               @on-ok="handleSubmit('formValidate')">
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                <FormItem label="考试名称" prop="name">
                    <Input v-model="formValidate.name" placeholder=""></Input>
                </FormItem>
                <FormItem hidden label="id" prop="id">
                    <Input v-model="formValidate.id" placeholder=""></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                showAddClazzModal: false,
                loading: true,
                formValidate: {
                    id: null,
                    name: ''
                },
                ruleValidate: {
                    name: [{required: true, message: '考试不为空', trigger: 'blur'}],
                },
                columns: [
                    {
                        title: '名称',
                        key: 'name',
                        fixed: 'left',
                        sortable: true
                    },
                    {
                        title: '总分',
                        key: 'sumScore',
                        sortable: true
                    },
                    {
                        title: '平均分',
                        key: 'avgScore',
                        sortable: true
                    },
                    {
                        title: '最高分',
                        key: 'maxScore',
                        sortable: true
                    },
                    {
                        title: '最低分',
                        key: 'minScore',
                        sortable: true
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.editTest(params)
                                        }
                                    }
                                }, '编辑'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.$router.push(`/test/${this.$route.params.id}/${params.row.id}`)
                                        }
                                    }
                                }, '详情')
                            ]);
                        }
                    }
                ],
                data: []
            }
        },
        methods: {
            loadData() {
                const _this = this;
                this.$request.get('/test/clazzId/' + this.$route.params.id)
                    .then(value => {
                        _this.data = value.data;
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            },
            analysisAll(){
                this.$router.push(`/analysis/all/test/${this.$route.params.id}`)
            },
            addTest() {
                this.formValidate = {
                    id: null,
                    name: ''
                };
                this.showAddClazzModal = true;
            }, editTest(value) {
                const object = value.row;
                this.formValidate = {
                    id: object.id,
                    name: object.name,
                };
                this.showAddClazzModal = true;
            },
            handleSubmit(val) {
                const _this = this;
                this.$refs[val].validate((valid) => {
                    if (valid) {
                        _this.$request.post('/test/?clazzId=' + this.$route.params.id,
                            this.formValidate)
                            .then(value => {
                                console.log(value);
                                _this.showAddClazzModal = false;
                                _this.loadData();
                            }).catch(reason => {
                            _this.$Message.error("创建失败," + reason);
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        })
                    } else {
                        this.loading = false;
                        this.$nextTick(() => {
                            this.loading = true;
                        });
                    }
                    this.loading = false;
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