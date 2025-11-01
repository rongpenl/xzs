// initial state
const state = {
  user: {
    sexEnum: [
      { key: 1, value: 'Male' },
      { key: 2, value: 'Female' }
    ],
    levelEnum: [
      { key: 1, value: 'Grade 1' },
      { key: 2, value: 'Grade 2' },
      { key: 3, value: 'Grade 3' },
      { key: 4, value: 'Grade 4' },
      { key: 5, value: 'Grade 5' },
      { key: 6, value: 'Grade 6' },
      { key: 7, value: 'Grade 7' },
      { key: 8, value: 'Grade 8' },
      { key: 9, value: 'Grade 9' },
      { key: 10, value: 'Grade 10' },
      { key: 11, value: 'Grade 11' },
      { key: 12, value: 'Grade 12' },
      { key: 13, value: 'G12+' }
    ],
    roleEnum: [
      { key: 1, value: 'Student' },
      { key: 2, value: 'Teacher' },
      { key: 3, value: 'Admin' }
    ],
    message: {
      readTag: [
        { key: true, value: 'success' },
        { key: false, value: 'warning' }
      ],
      readText: [
        { key: true, value: 'Read' },
        { key: false, value: 'Unread' }
      ]
    }
  },
  exam: {
    examPaper: {
      paperTypeEnum: [
        { key: 1, value: 'Regular Exam' },
        { key: 4, value: 'Timed Exam' },
        { key: 6, value: 'One-time Exam' }
      ]
    },
    examPaperAnswer: {
      statusEnum: [
        { key: 1, value: 'Pending Review' },
        { key: 2, value: 'Completed' }
      ],
      statusTag: [
        { key: 1, value: 'warning' },
        { key: 2, value: 'success' }
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
      answer: {
        doRightTag: [
          { key: true, value: 'success' },
          { key: false, value: 'danger' },
          { key: null, value: 'warning' }
        ],
        doRightEnum: [
          { key: true, value: 'Correct' },
          { key: false, value: 'Incorrect' },
          { key: null, value: 'Pending Review' }
        ],
        doCompletedTag: [
          { key: false, value: 'info' },
          { key: true, value: 'success' }
        ]
      }
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
