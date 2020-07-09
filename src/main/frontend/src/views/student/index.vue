<template>
    <div style="margin-top: 30px">
        <Button @click="addStudent" type="primary">新增</Button>
        <Button @click="downloadStudent" style="margin-left: 10px" type="primary">导出excel模板</Button>
        <Button ghost  type="text">
            <Upload :on-success="loadData" :action="'//192.168.1.4:8080/student/upload?clazzId='+this.$route.params.id">
                <Button icon="ios-cloud-upload-outline">批量excel导入</Button>
            </Upload>
        </Button>

        <Table style="margin-top: 10px" border :columns="columns" :data="data"></Table>

        <Modal :loading="loading"
               :mask-closable="false"
               v-model="showAddClazzModal"
               :title="formValidate.name?formValidate.name:'新增学生'"
               @on-ok="handleSubmit('formValidate')">
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                <FormItem label="学号" prop="xueHao">
                    <Input v-model="formValidate.xueHao" placeholder=""></Input>
                </FormItem>
                <FormItem label="名字" prop="name">
                    <Input v-model="formValidate.name" placeholder=""></Input>
                </FormItem>
                <FormItem label="性别" prop="sex">
                    <RadioGroup v-model="formValidate.sex">
                        <Radio label="0">
                            <Icon type="logo-apple"></Icon>
                            <span>男</span>
                        </Radio>
                        <Radio label="1">
                            <Icon type="logo-android"></Icon>
                            <span>女</span>
                        </Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="身份证" prop="cardCode">
                    <Input v-model="formValidate.cardCode" placeholder=""></Input>
                </FormItem>
                <FormItem label="住址" prop="address">
                    <Input v-model="formValidate.address" placeholder=""></Input>
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
                    xueHao: '',
                    name: '',
                    sex: '0',
                    cardCode: '',
                    address: '',

                },
                ruleValidate: {
                    xueHao: [{required: true, message: '学号不为空', trigger: 'blur'}],
                    name: [{required: true, message: '班级姓名不为空', trigger: 'blur'}],
                    sex: [{required: true, message: '性别不为空', trigger: 'blur'}],
                    cardCode: [{required: true, message: '身份证不为空', trigger: 'blur'}],
                    address: [{required: true, message: '地址不为空', trigger: 'blur'}],
                },
                columns: [
                    {
                        title: '学号',
                        key: 'xueHao',
                        width: 80,
                        fixed: 'left',
                        sortable: true
                    }, {
                        title: '名字',
                        key: 'name',
                        width: 120,
                        sortable: true
                    },
                    {
                        title: '性别',
                        key: 'sex',
                        width: 80,
                        render: (h, params) => {
                            return h('div', [
                                h('span', {
                                    props: {
                                        type: 'person'
                                    }
                                }),
                                h('span', params.row.sex === 0 ? '男' : '女')
                            ]);
                        }
                    },
                    {
                        title: '身份证',
                        key: 'cardCode',
                        width: 240,
                    },
                    {
                        title: '地址',
                        key: 'address'
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
                                            this.editStudent(params)
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
                                            this.remove(params.index)
                                        }
                                    }
                                }, '成绩')
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
                this.$request.get('/student/?clazzId=' + this.$route.params.id)
                    .then(value => {
                        _this.data = value.data;
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            },
            addStudent() {
                this.formValidate = {
                    id: null,
                    xueHao: '',
                    name: '',
                    sex: '0',
                    cardCode: '',
                    address: '',

                };
                this.showAddClazzModal = true;
            }, editStudent(value) {
                const object = value.row;
                this.formValidate = {
                    id: object.id,
                    xueHao: object.xueHao + '',
                    name: object.name,
                    sex: object.sex + '',
                    cardCode: object.cardCode,
                    address: object.address,

                };
                this.showAddClazzModal = true;
            },
            downloadStudent() {
                window.open(`/student/download?clazzId=${this.$route.params.id}`, '_blank')
            },
            batchAddStudent() {
            },
            handleSubmit(val) {
                const _this = this;
                this.$refs[val].validate((valid) => {
                    if (valid) {
                        _this.$request.post('/student/?clazzId=' + this.$route.params.id,
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