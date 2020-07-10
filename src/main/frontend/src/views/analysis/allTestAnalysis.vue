<template>
    <card style="margin-top: 20px;height: 5000px">
        <p slot="title">
            <Icon type="ios-home"/>
            test
        </p>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="avg" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="max" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="min" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="ten" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="ninety" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="eighty" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="seventy" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="sixty" :style="{width: '900px', height: '400px'}"></div>
                </card>
            </Col>
        </Row>
        <Row style="margin-top: 20px">
            <Col>
                <card>
                    <div id="fifty" :style="{width: '900px', height: '400px'}"></div>
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
                }
            }
        },
        mounted() {
            this.requestData();
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

    }
</script>