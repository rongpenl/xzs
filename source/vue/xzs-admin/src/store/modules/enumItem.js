// initial state
const state = {
  user: {
    sexEnum: [
      { key: 1, value: 'Male' },
      { key: 2, value: 'Female' }
    ],
    statusEnum: [
      { key: 1, value: 'Enabled' },
      { key: 2, value: 'Disabled' }
    ],
    levelEnum: [
      { key: 1, value: 'G1' },
      { key: 2, value: 'G2' },
      { key: 3, value: 'G3' },
      { key: 4, value: 'G4' },
      { key: 5, value: 'G5' },
      { key: 6, value: 'G6' },
      { key: 7, value: 'G7' },
      { key: 8, value: 'G8' },
      { key: 9, value: 'G9' },
      { key: 10, value: 'G10' },
      { key: 11, value: 'G11' },
      { key: 12, value: 'G12' },
      { key: 13, value: 'G12+' }
    ],
    roleEnum: [
      { key: 1, value: 'Student' },
      { key: 2, value: 'Teacher' },
      { key: 3, value: 'Admin' }
    ],
    statusTag: [
      { key: 1, value: 'success' },
      { key: 2, value: 'danger' }
    ],
    statusBtn: [
      { key: 1, value: 'Disabled' },
      { key: 2, value: 'Enabled' }
    ]
  },
  exam: {
    examPaper: {
      paperTypeEnum: [
        { key: 1, value: 'Regular Exam' },
        { key: 4, value: 'Timed Exam' },
        { key: 6, value: 'One-time Exam' }
      ]
    },
    question: {
      typeEnum: [
        { key: 1, value: 'Single Choice' },
        { key: 2, value: 'Multiple Choice' },
        { key: 3, value: 'True/False' },
        { key: 4, value: 'Fill in the Blank' },
        { key: 5, value: 'Short Answer' }
      ],
      editUrlEnum: [
        { key: 1, value: '/exam/question/edit/singleChoice', name: 'Single Choice Question' },
        { key: 2, value: '/exam/question/edit/multipleChoice', name: 'Multiple Choice Question' },
        { key: 3, value: '/exam/question/edit/trueFalse', name: 'True/False Question' },
        { key: 4, value: '/exam/question/edit/gapFilling', name: 'Gap Filling Question' },
        { key: 5, value: '/exam/question/edit/shortAnswer', name: 'Short Answer Question' }
      ]
    }
  }
}

// getters
const getters = {
  enumFormat: (state) => (arrary, key) => {
    return format(arrary, key)
  }
}

// actions
const actions = {}

// mutations
const mutations = {}

const format = function (array, key) {
  for (let item of array) {
    if (item.key === key) {
      return item.value
    }
  }
  return null
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
