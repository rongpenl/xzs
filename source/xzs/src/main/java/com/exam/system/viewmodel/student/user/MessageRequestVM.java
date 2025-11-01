package com.exam.system.viewmodel.student.user;

import com.exam.system.base.BasePage;

public class MessageRequestVM extends BasePage {

    private Integer receiveUserId;

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }
}
