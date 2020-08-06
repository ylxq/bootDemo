<style scoped>

    .one-line {
        top: 50%;
    }

    .two-line {
        top: 50%;
        margin-top: 10px
    }

</style>
<template>
    <Card :style="{margin: '50px 0'}" style="min-height: 600px;">
        <p slot="title">
            <Icon type="ios-home"/>
            班级
        </p>
        <Row>
            <Col v-for="(item,index) in classes" span="6" style="margin-top: 10px">
                <Card style="min-height: 160px">
                    <p @click="editClazz(item)" slot="title">{{item.name}}</p>
                    <CellGroup>
                        <Cell title="学生详情" :to="'/student/'+item.id">
                            <Badge :count="item.students?item.students.length:0" slot="extra" type="primary"/>
                        </Cell>
                        <Cell title="考试详情" :to="'/test/'+item.id">
                            <Badge :count="item.test?item.test.length:0" slot="extra" type="primary"/>
                        </Cell>
                    </CellGroup>
                </Card>
            </Col>

            <Col span="6" :offset="classes.length%3===0?0:1" :class="classes.length>3?'one-line':'two-line'">
                <Card style="text-align:center;min-height: 160px;">
                    <Button @click="addClazz" style="margin: 19%;" size="large" icon="md-add" type="primary"
                            shape="circle"></Button>
                </Card>
            </Col>
        </Row>

        <Modal :loading="loading"
               :mask-closable="false"
               v-model="showAddClazzModal"
               :title="formValidate.name?formValidate.name:'新增班级'"
               @on-ok="handleSubmit('formValidate')">
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                <FormItem label="Name" prop="name">
                    <Input v-model="formValidate.name" placeholder="Enter your name"></Input>
                </FormItem>
                <FormItem hidden label="id" prop="id">
                    <Input v-model="formValidate.id" placeholder="Enter your name"></Input>
                </FormItem>
            </Form>
        </Modal>
    </Card>
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
                    name: [
                        {required: true, message: '班级姓名不为空', trigger: 'blur'}
                    ]
                },
                classes: []
            }
        },
        methods: {
            addClazz() {
                this.formValidate = {
                    id: null,
                    name: null
                };
                this.showAddClazzModal = true;
            },
            editClazz(item) {

                this.showAddClazzModal = true;
                this.formValidate = {
                    id: item.id,
                    name: item.name
                }
            },
            handleSubmit(val) {
                const _this = this;
                this.$refs[val].validate((valid) => {
                    if (valid) {
                        _this.$request.post('/clazz/?teacherId=' + this.$store.getters.userInfo.id,
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
            },
            loadData() {
                const _this = this;
                this.$request.get('/teacher/' + this.$store.getters.userInfo.id)
                    .then(value => {
                        _this.classes = value.data.clazzList;
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
