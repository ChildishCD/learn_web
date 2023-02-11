<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%
        //获取 css 的文件路径
        String cssPath = String.valueOf(request.getRequestURL().append("/../css/table.css"));
        //查询book的初始值
    %>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <script src="https://unpkg.com/vue@next"></script>
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>" />
</head>
<body>
<div id="root">
    <div class="wrapper">
        <span class="title">内藏府 </span>

        <a href="book">返回图书列表</a>
        <c:if test="${submit}">
        <form action="<%----%>" method="post">
        </c:if>
            <div class="table">
                <div class="row header">
                    <div class="cell">
                        必填
                    </div>
                    <div class="cell">
                        选填
                    </div>
                </div>


                <div class="row">
                    <div class="cell" data-title="必填">
                        <hr>
                        <p>书籍名:
                            <input type="text" placeholder="请输入书籍名称" v-model="bookOne.bookName">
                        </p>
                        <p>作者名:
                            <input type="text" placeholder="请输入作者名称" v-model="bookOne.authorName">
                        </p>
                        <p>书籍封面:
                            <input type="url" placeholder="请输入封面图片路径"
                                   v-model="bookOne.coverUrl">
                        </p>
<%--                        <div id="cover" class="cover" ></div>--%>
                        <img :src="bookOne.coverUrl" class="cover">
                        <hr>
                        <c:if test="${submit}">
                            <input type="submit" value="提交" class="submit">
                        </c:if>
                    </div>
                    <div class="cell" data-title="选填">
                        <hr>
                        <p>总字数:
                            <input type="number" oninput="this.value=this.value.replace(/\D/g);"
                                   @change="count($event)" placeholder="请输入总字数" v-model="bookOne.wordCount">
                        </p>
                        <p>一级分类:
                            <select @change="showStype()" v-model="bookOne.ftypeId">
                                <option value="null">---请选择---</option>
                                <option v-for="ft in ftypes" :value="ft.typeId">{{ft.typeName}}</option>
                            </select>
                        </p>
                        <p>二级分类:
                            <select @change="showAttr()" v-model="bookOne.stypeId">
                                <option value="null">---请选择---</option>
                                <option v-for="st in stypes" :value="st.typeId">{{st.typeName}}</option>
                            </select>
                        </p>
                        <p>频道：
                            <span class="attribution">
                                    <span v-show="bookOne.attribution == null">请选择图书分类</span>
                                    <span v-show="bookOne.attribution == 1">男频</span>
                                    <span v-show="bookOne.attribution == 2">女频</span>
                                    <span v-show="bookOne.attribution == 3">出版</span>
                                </span>
                        </p>
                        <p>书籍状态：
                            <label><input type="radio" value="1" v-model="bookOne.status">连载</label>
                            <label><input type="radio" value="2" v-model="bookOne.status">完本</label>
                        </p>
                        <p>其他：
                            <!-- checkbox绑定的是checked -->
                            <label><input type="checkbox" @change="vip()" v-model="vipBool" :value="bookOne.isVip">收费</label>
                            <label><input type="checkbox" @change="Rec()" v-model="recBool" :value="bookOne.isRecommand">首页推荐</label>
                        </p>
                        <p>关键字:
                        <p><textarea cols="30" rows="2" placeholder="请输入关键字"
                                     v-model="bookOne.keyword"></textarea></p>
                        </p>
                        <p>简介:
                        <p><textarea cols="30" rows="5" placeholder="请输入简介"
                                     v-model="bookOne.description"></textarea></p>
                        </p>
                    </div>
                </div>
            </div>
            <input type="text" name="bookOne" v-model="bookOneJSON" v-show="false">
        <c:if test="${submit}">
        </form>
        </c:if>
    </div>
</div>

<script>
    Vue.createApp({
        data() {
            var one = ${currentBookJSON}
            var vBool = one.isVip ==1
            var rBool = one.isRecommand ==1
            var ftypesTemp=${sessionScope.ftypeJSON}
            var stypeListTemp=${sessionScope.stypeJSON}
            var stypesTemp = []
            if (one.ftypeId != null) {
                stypeListTemp.forEach(st => {
                    if (st.ftypeId == one.ftypeId) {
                        stypesTemp.push(st)
                    }
                })
            }
            return {
                ftypes: ftypesTemp,
                stypeList: stypeListTemp,
                stypes: stypesTemp,
                bookOne : one,
                vipBool : vBool,
                recBool : rBool
            }
        },
        computed:{
            bookOneJSON(){
                return JSON.stringify(this.bookOne)
            }
        },
        methods: {
            showStype() {
                this.stypes = []
                if (this.bookOne.ftypeId != null) {
                    this.stypeList.forEach(st => {
                        if (st.ftypeId == this.bookOne.ftypeId) {
                            this.stypes.push(st)
                        }
                    })
                }
                this.bookOne.stypeId = null
                this.bookOne.attribution = null
            },
            showAttr() {
                if (this.bookOne.stypeId == null) {
                    this.bookOne.attribution = null
                } else {
                    this.stypes.forEach(st => {
                        if (st.typeId == this.bookOne.stypeId) {
                            this.bookOne.attribution = st.attribution
                        }
                    })
                }
            },
            vip(event) {
                //得到事件的对象
                this.bookOne.isVip = this.vipBool ? 1 : 0
            },
            Rec(event){
                this.bookOne.isRecommand = this.recBool ? 1 : 0
            },
            count(event) {
                if (this.bookOne.wordCount == '') {
                    this.bookOne.wordCount = 0
                }
            },
        }
    }).mount("#root")
</script>
</body>
</html>
