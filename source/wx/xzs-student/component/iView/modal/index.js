Component({
    externalClasses: ['i-class', 'i-class-mask'],

    properties: {
        visible: {
            type: Boolean,
            value: false
        },
        title: {
            type: String,
            value: ''
        },
        showOk: {
            type: Boolean,
            value: true
        },
        showCancel: {
            type: Boolean,
            value: true
        },
        okText: {
            type: String,
            value: 'Confirm'
        },
        cancelText: {
            type: String,
            value: 'Cancel'
        },
        actions: {
            type: Array,
            value: []
        },
        // horizontal || vertical
        actionMode: {
            type: String,
            value: 'horizontal'
        }
    },

    methods: {
        handleClickItem ({ currentTarget = {} }) {
            const dataset = currentTarget.dataset || {};
            const { index } = dataset;
            this.triggerEvent('click', { index });
        },
        handleClickOk () {
            this.triggerEvent('ok');
        },
        handleClickCancel () {
            this.triggerEvent('cancel');
        }
    }
});
