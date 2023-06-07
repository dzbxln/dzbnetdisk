import { createStore } from 'vuex'
// 共享参数设置

export default createStore({
  state: {
    // 文件夹列表数据
    CardData: [{
      image: '',
      display: true,
      folderName: '',
      fId: '',
    }],
    // 父文件夹id
    masterId: '',
    file : undefined,
  },
  getters: {
  },
  // 涉及到共享参数的增删
  mutations: {
    // 新增文件夹
    addData(state, data) {
      // let isor = true
      // for (let i = 0; i < state.CardData.length; i++) {
      //   // 如果存在空命名的文件夹
      //   if (!state.CardData[i].folderName || state.CardData[i].folderName.trim().length === 0) {
      //     isor = false
      //   }
      // }
      // if (isor) {
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
    }
  },
  actions: {
  },
  modules: {
  }
})
