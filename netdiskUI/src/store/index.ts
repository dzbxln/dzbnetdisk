import { createStore } from 'vuex'

export default createStore({
  state: {
    CardData: [{
      image: '',
      display: true,
      folderName: '',
      fId: '',
    }],
    masterId: ''
  },
  getters: {
  },
  mutations: {
    addData(state, data) {
      let isor = true
      for (let i = 0; i < state.CardData.length; i++) {
        if (!state.CardData[i].folderName || state.CardData[i].folderName.trim().length === 0) {
          isor = false
        }
      }
      if (isor) {
        state.CardData.push(data)
      }
    },
    deleteData(state, data) {
      state.CardData.splice(state.CardData.indexOf(data), 1)
    },
    coverData(state, data) {
      state.CardData = data
    }
  },
  actions: {
  },
  modules: {
  }
})
