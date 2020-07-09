<template>
    <Card style="margin-top: 20px">
        <Col span="6">
            <Card  style="width:280px">
                <div style="text-align:center">
                    <img src="https://file.iviewui.com/dist/2ecd3b0452aa197097d5131afacab7b8.png">
                    <h3>A high quality UI Toolkit based on Vue.js</h3>
                </div>
            </Card>
        </Col>
        <Col span="6" offset="2">
            <Card  style="width:280px">
                <div style="text-align:center">
                    <img src="https://file.iviewui.com/dist/2ecd3b0452aa197097d5131afacab7b8.png">
                    <h3>A high quality UI Toolkit based on Vue.js</h3>
                </div>
            </Card>
        </Col>
        <Col span="6" offset="2">
            <Card  style="width:280px">
                <div style="text-align:center">
                    <img src="https://file.iviewui.com/dist/2ecd3b0452aa197097d5131afacab7b8.png">
                    <h3>A high quality UI Toolkit based on Vue.js</h3>
                </div>
            </Card>
        </Col>
        <div id="myChart" :style="{width: '300px', height: '300px'}"></div>
    </Card>
</template>

<script>
    export default {
        name: 'hello',
        data() {
            return {
                msg: 'Welcome to Your Vue.js App'
            }
        },
        mounted() {
            this.drawLine();
        },
        methods: {
            drawLine() {
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myChart'))
                // 绘制图表
                myChart.setOption({
                    title: {text: '在Vue中使用echarts'},
                    tooltip: {},
                    xAxis: {
                        data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: [5, 20, 36, 10, 10, 20]
                    }]
                });
            }
        }
    }
</script>

<style scoped>

</style>