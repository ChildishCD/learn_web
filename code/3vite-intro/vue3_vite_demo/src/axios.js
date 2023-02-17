import axios from "axios";
import qs from "qs";

axios.defaults.baseURL = 'http://localhost:8080/bookstore/book'  //接口路径

//post请求头
axios.defaults.headers.post["Content-Type"] ="application/x-www-form-urlencoded;charset=UTF-8";
//设置超时
axios.defaults.timeout = 10000;

axios.interceptors.request.use(
  config => {
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
 
axios.interceptors.response.use(
   response => {
      if (response.status == 200) {
        return Promise.resolve(response);
      } else {
        return Promise.reject(response);
      }
   },
  
   error => {
      alert(`异常请求：${JSON.stringify(error.message)}`)
   }
);
export default {
  //post方法
  post(url, data) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'post',
        url,
        data: qs.stringify(data),
      }).then(res => {
        resolve(res.data)
      }).catch(err => {
        reject(err)
      });
    })
  },
  //get方法
  get(url, data) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'get',
        url,
        params: data,
      }).then(res => {
        resolve(res.data)
      }).catch(err => {
        reject(err)
      })
    })
  }
};