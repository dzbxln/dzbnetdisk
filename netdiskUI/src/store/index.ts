import { createStore } from 'vuex'
// 共享参数设置

export default createStore({
  state: {
    // 文件夹列表数据
    CardData: [{
      display: true,
    }],
    // 父文件夹id
    masterId: '',
    file : undefined,
    videoUrl:'',
  },
  getters: {
  },
  // 涉及到共享参数的增删
  mutations: {
    // 新增文件夹
    addData(state, data) {
        state.CardData.push(data)
      // }
    },
    // 移除文件夹
    deleteData(state, data) {
      state.CardData.splice(state.CardData.indexOf(data), 1)
    },
    // 文件夹列表整体替换
    coverData(state, data) {
      state.CardData = data
    },
    // 文件列表进行添加
    fileAddData(state, data){
      state.CardData.push(data)
    },
    // 文件列表批量
    fileListAdd(state, data){
      state.CardData.push.apply(state.CardData,data)
    }

  },
  actions: {
  },
  modules: {
  }
})
