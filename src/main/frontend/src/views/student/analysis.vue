<template>
    <card style="margin-top: 20px;height: 2000px">
        <Row>
            <Col span="6">
                <Card style="width:290px">
                    <div style="text-align:center">
                        <img src="https://file.iviewui.com/dist/2ecd3b0452aa197097d5131afacab7b8.png">
                        <h3>A high quality UI Toolkit based on Vue.js</h3>
                    </div>
                </Card>
            </Col>

            <Col span="6" offset="2">
                <Card style="width:290px">
                    <div style="text-align:center">
                        <img src="https://file.iviewui.com/dist/2ecd3b0452aa197097d5131afacab7b8.png">
                        <h3>A high quality UI Toolkit based on Vue.js</h3>
                    </div>
                </Card>
            </Col>

            <Col span="6" offset="2">
                <Card style="width:290px">
                    <div style="text-align:center">
                        <img src="https://file.iviewui.com/dist/2ecd3b0452aa197097d5131afacab7b8.png">
                        <h3>A high quality UI Toolkit based on Vue.js</h3>
                    </div>
                </Card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="chartPie" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>

        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="chartLine" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
    </card>
</template>

<script>
    export default {
        data() {
            return {
                lineData: {
                    testName: [],
                    testScore: []
                },
                pieData: []
            }
        },
        computed: {
            getPieNames: function () {
                const array = [];
                this.pieData && this.pieData.forEach(item => {
                    array.push(item.name);
                });
                return array;
            }
        },
        mounted() {
            this.requestData();
        },
        methods: {
            drawLine() {
                const _this = this;
                // 基于准备好的dom，初始化echarts实例
                let myChart = _this.$echarts.init(document.getElementById('chartLine'));
                // 绘制图表
                myChart.setOption({
                    title: {text: '在Vue中使用echarts'},
                    tooltip: {},
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: _this.lineData ? _this.lineData.testName : []
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: _this.lineData ? _this.lineData.testScore : [],
                        type: 'line',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        areaStyle: {}
                    }]
                });
            },
            drawPie() {
                const _this = this;
                // 基于准备好的dom，初始化echarts实例
                let myChart = _this.$echarts.init(document.getElementById('chartPie'));
                // 绘制图表
                myChart.setOption({
                    title: {
                        text: '天气情况统计',
                        subtext: '虚构数据',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        bottom: 10,
                        left: 'center',
                        data: this.getPieNames,
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '65%',
                            center: ['50%', '50%'],
                            selectedMode: 'single',
                            label: {
                                normal: {
                                    formatter: '\n{b} : {c} ({d}%)'
                                }
                            },
                            data: _this.pieData
                        }
                    ]
                });
            },
            requestData() {
                const _this = this;
                this.$request.get(`/analysis/student/${this.$route.params.studentId}`)
                    .then(value => {
                        _this.pieData = value.data.pies;
                        _this.lineData = value.data.line;
                        this.drawLine();
                        this.drawPie();
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            }
        },

    }
</script>

<style scoped>

</style>