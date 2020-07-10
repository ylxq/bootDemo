<template>
    <card style="margin-top: 20px;height: 2000px">
        <p slot="title">
            <Icon type="ios-home"/>
            {{this.$route.params.analysisType==='test'?'测试':'sss'}}
        </p>
        <Row>
            <Col span="6">
                <Card style="width:290px">
                    <div style="text-align:center">
                        <p class="score">{{maxScore}}</p>
                        <P class="scoreName">max</P>
                    </div>
                </Card>
            </Col>

            <Col span="6" offset="2">
                <Card style="width:290px">
                    <div style="text-align:center">
                        <p class="score">{{minScore}}</p>
                        <P class="scoreName">max</P>
                    </div>
                </Card>
            </Col>

            <Col span="6" offset="2">
                <Card style="width:290px">
                    <div style="text-align:center">
                        <p class="score">{{avgScore}}</p>
                        <P class="scoreName">max</P>
                    </div>
                </Card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="chartPie" class="line-size"></div>
                </card>
            </Col>
        </Row>

        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="chartLine" class="line-size"></div>
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
                pieData: [],
                maxScore: 0,
                minScore: 0,
                avgScore: 0,
                screenWidth: document.body.clientWidth
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
            const that = this;
            window.onresize = () => {
                return (() => {
                    window.screenWidth = document.body.clientWidth;
                    that.screenWidth = window.screenWidth;
                })()
            }
        },
        methods: {
            drawLine() {
                const _this = this;
                // 基于准备好的dom，初始化echarts实例
                let myChart = _this.$echarts.init(document.getElementById('chartLine'));
                // 绘制图表
                myChart.setOption({
                    title: {
                        text: '在Vue中使用echarts',
                        left: 'center'
                    },
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
                this.$request.get(`/analysis/test/?clazzId=${this.$route.params.studentId}`)
                    .then(value => {
                        _this.pieData = value.data.pies;
                        _this.lineData = value.data.line;
                        _this.avgScore = value.data.avgScore ? value.data.avgScore : 0;
                        _this.maxScore = value.data.maxScore ? value.data.maxScore : 0;
                        _this.minScore = value.data.minScore ? value.data.minScore : 0;
                        this.drawLine();
                        this.drawPie();
                    }).catch(reason => {
                    _this.$Message.error("服务器异常," + reason);
                });
            }
        },
        watch: {
            screenWidth(val) {
                if (!this.timer) {
                    this.screenWidth = val;
                    this.timer = true;
                    let that = this;
                    setTimeout(function () {
                        // 打印screenWidth变化的值
                        location.reload();
                        that.timer = false
                    }, 400)
                }
            }
        }

    }
</script>

<style scoped>
    .score {
        font-size: 50px;
        color: green;
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    }

    .scoreName {
        font-size: 25px;
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    }

    @media (min-width: 1600px) {
        .line-size {
            width: 1500px;
            height: 400px;
        }
    }

    @media (max-width: 1600px) and ( min-width: 1400px) {
        .line-size {
            width: 1300px;
            height: 400px;
        }
    }

    @media (max-width: 1400px) and ( min-width: 1100px)  {
        .line-size {
            width: 1200px;
            height: 400px;
        }
    }


    @media (max-width: 1100px) {
        .line-size {
            width: 900px;
            height: 400px;
        }
    }
</style>