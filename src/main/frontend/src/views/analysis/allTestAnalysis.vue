<template>
    <card style="margin-top: 20px;height: 5000px;text-align: center">
        <p slot="title">
            <Icon type="ios-home"/>
            test
        </p>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="avg" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="max" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="min" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="ten" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="ninety" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="eighty" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="seventy" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="sixty" class="line-size"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="fifty" class="line-size"></div>
                </card>
            </Col>
        </Row>
    </card>
</template>

<script>
    export default {
        data() {
            return {
                lineData: {},
                lineName: {
                    avg: "haha1",
                    max: "haha2",
                    min: "haha3",
                    ten: "haha4",
                    ninety: "haha5",
                    eighty: "haha6",
                    seventy: "haha7",
                    sixty: "haha8",
                    fifty: "haha9",
                },
                screenWidth: document.body.clientWidth
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
            drawLine(name) {
                const _this = this;
                // 基于准备好的dom，初始化echarts实例
                let myChart = _this.$echarts.init(document.getElementById(name));
                // 绘制图表
                myChart.setOption({
                    title: {
                        text: _this.lineName[name],
                        left: 'center'
                    },
                    tooltip: {},
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: _this.lineData && _this.lineData[name] ? _this.lineData[name].testName : []
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: _this.lineData && _this.lineData[name] ? _this.lineData[name].testScore : [],
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
            requestData() {
                const _this = this;
                this.$request.get(`/analysis/test?clazzId=${this.$route.params.clazzId}`)
                    .then(value => {
                        _this.lineData = value.data;
                        for (let lineNameKey in _this.lineName) {
                            this.drawLine(lineNameKey);
                        }
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

<style>


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