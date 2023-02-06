<script>
	// 想要使用vue，需要创建一个vue实例，并初始化参数
	// 真正开发只有一个实例,并配合组件一起使用
	// 1. 创建实例的参数
	export default {// 这个常量是一个json,是生成vue实例的参数,里面包含数据,方法...
		// 1.0 以下为参数的固定写法

		// 1.1 data(){} ->  表示一个数据
		// 		vue中的数据生成方法
		// 		在json里面写一个json数据生成的方法,并返回数据,相当于是一个json数据
		data() {//	这里相当于js中的function
			// 这里可以写业务代码
			var p = 0
			var m = "VIP"
			var member = {
				"name": "AA小美",
				"age": "25",
				"vip": "$" + m,
				"intro": "你好我是超级无敌小美, 大家可以叫我大美丽, 在今后的日子里请大家多多关照"
			}
			return {// 返回的json对象，就是App中data的值
				// 前端中key可以不加""
				points: p,
				member: member
			}
		},

		// 1.2  methods: {} ->  表示自定义方法
		methods: {

		},

		// 1.3  components: {} ->  表示组件
		components: {

		},
	}
</script>

<template>
	<!-- {{}} 这是一个表达式，里面需要写vue 方法 中 返回的 属性 -->
	<!-- 表达式中支持 所有运算符 方法的调用(包含自定义方法和默认方法), 不支持逻辑语句和赋值 -->
	<h1>欢迎 {{member.vip}} : {{ member.name + '_芳龄' + member.age}}</h1>
	<!-- 个人简介: {{ member.intro.substring(0,10) + '......' }} -->
	<!-- <p> 您的积分为: {{ points + 100 }}</p> -->
</template>