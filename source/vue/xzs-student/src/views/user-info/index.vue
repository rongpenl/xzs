<template>
  <div style="margin-top: 10px" class="app-contain">
    <el-row :gutter="50">
      <el-col :span="7">
        <el-card>
          <div slot="header" class="clearfix">
            <span>Personal Information</span>
          </div>
          <el-row style="text-align: center">
            <el-upload action="/api/student/upload/image"  accept=".jpg,.png" :show-file-list="false"  :on-success="uploadSuccess">
              <el-avatar class="el-dropdown-avatar" :size="100" :src="form.imagePath === null ? require('@/assets/avatar.png') : form.imagePath"></el-avatar>
            </el-upload>
          </el-row>
          <el-row class="user-info-userName">
            <label>{{form.userName}}</label>
          </el-row>
          <el-divider/>
          <el-row class="user-info-fullInfo">
            <label>Name: {{form.realName}}</label><br/>
            <label>Grade: {{levelFormatter(form.userLevel)}}</label><br/>
            <label>Registration Time: {{form.createTime}}</label><br/>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="17">
        <el-card shadow="hover">
          <el-tabs active-name="event" type="card">
            <el-tab-pane label="User Activity" name="event">
              <div class="block">
                <el-timeline>
                  <el-timeline-item  :timestamp="item.createTime" placement="top" :key="item.id" v-for="item in event">
                    <el-card>
                      <p>{{item.content}}</p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </div>
            </el-tab-pane>
            <el-tab-pane label="Update Information" name="update">
              <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading" :rules="rules">
                <el-form-item label="Real Name：" prop="realName">
                  <el-input v-model="form.realName"></el-input>
                </el-form-item>
                <el-form-item label="Age：">
                  <el-input v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="Gender：">
                  <el-select v-model="form.sex" placeholder="Gender" clearable>
                    <el-option v-for="item in sexEnum" :key="item.key" :value="item.key"
                               :label="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="Date of Birth：">
                  <el-date-picker v-model="form.birthDay" value-format="yyyy-MM-dd" type="date" placeholder="date of birth"/>
                </el-form-item>
                <el-form-item label="Phone Number：">
                  <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="Grade：" prop="userLevel" required>
                  <el-select v-model="form.userLevel" placeholder="Grade">
                    <el-option v-for="item in levelEnum" :key="item.key" :value="item.key"
                               :label="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm">Update</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userApi from '@/api/user'
import { mapGetters, mapState } from 'vuex'

export default {
  data () {
    return {
      event: [],
      form: {
        userName: '',
        realName: '',
        age: '',
        sex: '',
        birthDay: null,
        phone: null,
        userLevel: null,
        createTime: null,
        imagePath: null
      },
      formLoading: false,
      rules: {
        userLevel: [
          { required: true, message: 'Please select a grade', trigger: 'change' }
        ]
      }
    }
  },
  created () {
    let _this = this
    userApi.getUserEvent().then(re => {
      _this.event = re.response
    })
    userApi.getCurrentUser().then(re => {
      _this.form = re.response
    })
  },
  methods: {
    uploadSuccess (re, file) {
      if (re.code === 1) {
        window.location.reload()
      } else {
        this.$message.error(re.message)
      }
    },
    submitForm () {
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          userApi.update(this.form).then(data => {
            if (data.code === 1) {
              _this.$message.success(data.message)
            } else {
              _this.$message.error(data.message)
            }
            _this.formLoading = false
          }).catch(e => {
            _this.formLoading = false
          })
        } else {
          return false
        }
      })
    },
    levelFormatter (level) {
      return this.enumFormat(this.levelEnum, level)
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      sexEnum: state => state.user.sexEnum,
      levelEnum: state => state.user.levelEnum
    })
  }
}
</script>

<style lang="scss" scoped>

</style>
