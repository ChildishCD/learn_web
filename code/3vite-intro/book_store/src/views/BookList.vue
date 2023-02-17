<template>
  <!-- main -->
  <el-row class="search">
    <el-col :span="2"></el-col>
    <el-col :span="14">
      <el-input
        v-model="keyword"
        placeholder="请输入关键字"
        class="input-with-select"
        @change="currentQuery"
      >
        <template #append>
          <el-button @click="currentQuery">搜索</el-button>
        </template>
      </el-input>
    </el-col>
    <el-col :span="1"></el-col>
    <el-col :span="3">
      <el-button
        class="main-button"
        type="primary"
        color="#DF012a"
        @click="dialogChange('添加图书', null, 'add', true)"
      >
        + 添 加
      </el-button>
      <el-dialog v-model="dialogVisible" :title="dialogInfo.title">
        <el-form :model="bookOne">
          <el-form-item label="书名" :label-width="formLabelWidth">
            <el-input
              v-model="bookOne.bookName"
              autocomplete="off"
              required
              placeholder="请输入书名"
            />
          </el-form-item>
          <el-form-item label="作者" :label-width="formLabelWidth">
            <el-input
              v-model="bookOne.authorName"
              autocomplete="off"
              required
              placeholder="请输入作者名"
            />
          </el-form-item>
          <span class="dialog-footer">
            <el-button
              @click="
                dialogVisible = false;
                bookOne = shotOne();
              "
              >取消</el-button
            >
            <el-button type="primary" @click="update"> 确认 </el-button>
          </span>
        </el-form>
      </el-dialog>
    </el-col>
    <el-col :span="2">
      <el-dropdown>
        <el-button class="main-button" type="primary" color="#DF012a"> 管 理 </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>用户中心</el-dropdown-item>
            <el-dropdown-item>修改密码</el-dropdown-item>
            <el-dropdown-item>登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>
    <el-col :span="2"></el-col>
  </el-row>

  <el-row>
    <el-col :span="2"></el-col>
    <el-col :span="20">
      <el-table :data="bookList" stripe style="width: 100%">
        <el-table-column label="封面" width="150">
          <template #default="scope">
            <img :src="scope.row.coverUrl" class="cover" />
          </template>
        </el-table-column>
        <el-table-column prop="bookName" label="书名" width="150"></el-table-column>
        <el-table-column prop="authorName" label="作者" width="100"></el-table-column>
        <el-table-column prop="description" label="简介"></el-table-column>
        <el-table-column label="操作" width="90" #default="scope">
          <el-button
            @click="
              dialogChange('修改图书', scope.row.bookId, 'update', false);
              changeOne();
            "
            >修改</el-button
          >
          <br />
          <el-button
            @click="
              dialogChange('删除图书', scope.row.bookId, 'del', false);
              update();
            "
            >删除</el-button
          >
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="2"></el-col>
  </el-row>
  <el-row>
    <div class="pagination-block">
      <el-col :lg="18">
        <el-pagination
          v-model:current-page="page.pageNum"
          v-model:page-size="page.pageSize"
          :page-sizes="[5, 10, 20]"
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.total"
          @size-change="sizeQuery"
          @current-change="currentQuery"
        />
      </el-col>
    </div>
  </el-row>
  <el-affix position="bottom" :offset="500">
    <el-button type="primary" color="#DF012a" class="home" @click="home"
      >返回首页</el-button
    >
  </el-affix>
</template>

<script setup>
import axios from "/src/axios/axios.js";
// 在各个页面中使用公共的值
import store from "/src/store/BookStore.js";
const bookStore = store();
// onMounted(() => {})
</script>

<script>
export default {
  data() {
    return {
      dialogInfo: {
        title: "",
        bookId: "",
        func: "",
      },
      //   dataUrl: "",
      keyword: "",
      page: {
        total: 0,
        pageNum: 1,
        pageSize: 10,
      },
      dialogVisible: false,
      bookOne: this.shotOne(),
      bookList: [],
    };
  },
  methods: {
    shotOne() {
      return {
        bookId: null,
        bookName: "",
        authorName: "",
      };
    },
    getGetStr(method) {
      return {
        method: method,
        keyword: this.keyword,
        total: this.page.total,
        pageNum: this.page.pageNum,
        pageSize: this.page.pageSize,
      };
    },
    getPostStr(method) {
      return {
        method: method,
        book: JSON.stringify(this.bookOne),
      };
      //   "book=" + JSON.stringify(this.bookOne) + "&method=" + method;
    },
    sizeQuery() {
      this.page.pageNum = 1;
      this.currentQuery();
    },
    currentQuery() {
      axios.get("", this.getGetStr("list")).then((data) => {
        if (data.code == 200) {
          this.bookList = data.obj.list;
          this.page.total = data.obj.total;
        } else {
          alert(data.msg);
        }
      });
    },
    home() {
      this.keyword = "";
      this.page.pageNum = 1;
      this.currentQuery();
    },
    dialogChange(title, bookId, func, showDia) {
      this.dialogInfo = {
        title: title,
        bookId: bookId,
        func: func,
      };
      this.dialogVisible = showDia;
    },
    changeOne() {
      this.bookOne.bookId = this.dialogInfo.bookId;
      //   axios.post(this.dataUrl, this.getPostStr("bookById")).then((response) => {
      //     var data = response.data;
      //     if (data.code == 200) {
      //       this.bookOne = data.obj;
      //       this.dialogVisible = true;
      //     } else {
      //       alert(data.msg);
      //     }
      //   });
    },
    update() {
      switch (this.dialogInfo.func) {
        case "add":
          if (this.bookOne.bookName == "") {
            alert("请输入书名!");
          } else {
            // axios.post(this.dataUrl, this.getPostStr("add")).then((response) => {
            //   var data = response.data;
            //   if (data.code == 200) {
            //     console.log(data);
            //     this.dialogVisible = false;
            //     this.bookOne = this.shotOne();
            //     this.home();
            //     alert("添加成功");
            //   } else {
            //     alert(data.msg);
            //   }
            // });
          }
          break;
        case "del":
          if (confirm("您确定删除吗?")) {
            this.bookOne.bookId = this.dialogInfo.bookId;
            // axios.post(this.dataUrl, this.getPostStr("del")).then((response) => {
            //   var data = response.data;
            //   if (data.code == 200) {
            //     console.log(data);
            //     this.bookOne = this.shotOne();
            //     this.home();
            //     alert("删除成功");
            //   } else {
            //     alert(data.msg);
            //   }
            // });
            this.bookOne.bookId = null;
          }
          break;
        case "update":
          if (this.bookOne.bookName == "") {
            alert("请输入书名!");
          } else {
            // axios.post(this.dataUrl, this.getPostStr("update")).then((response) => {
            //   var data = response.data;
            //   if (data.code == 200) {
            //     console.log(data);
            //     this.dialogVisible = false;
            //     this.bookOne = this.shotOne();
            //     this.home();
            //     alert("修改成功");
            //   } else {
            //     alert(data.msg);
            //   }
            // });
          }
          break;
        default:
          break;
      }
    },
  },
  mounted() {
    this.currentQuery();
  },
};
</script>
