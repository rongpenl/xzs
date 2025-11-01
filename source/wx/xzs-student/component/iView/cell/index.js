const warn = (msg, getValue) => {
    console.warn(msg);

Component({
    externalClasses: ['i-class'],

    options: {
        multipleSlots: true
    },

    relations: {
        '../cell-group/index': {
            type: 'parent'
        }
    },

    properties: {

        title: {
            type: String
        },

        label: {
            type: String
        },

        value: {
            type: String
        },

        onlyTapFooter: {
            type: Boolean
        },

        isLink: {
            type: null,
            value: ''
        },
        linkType: {
            type: String,
            value: 'navigateTo'
        },
        url: {
            type: String,
            value: ''
        }
    },

    data: {
        isLastCell: true
    },

    methods: {
        navigateTo () {
            const { url } = this.data;
            const type = typeof this.data.isLink;

            this.triggerEvent('click', {});

            if (!this.data.isLink || !url || url === 'true' || url === 'false') return;

            if (type !== 'boolean' && type !== 'string') {
                warn('isLink must be a string or boolean', this.data.isLink);
                return;
            }

            if (['navigateTo', 'redirectTo', 'switchTab', 'reLaunch'].indexOf(this.data.linkType) === -1) {
                warn('linkType must be one of navigateTo, redirectTo, switchTab, reLaunch', this.data.linkType);
                return;
            }
            wx[this.data.linkType].call(wx, {url});
        },
        handleTap () {
            if (!this.data.onlyTapFooter) {
                this.navigateTo();
            }
        },

        updateIsLastCell (isLastCell) {
            this.setData({ isLastCell });
        }
    }
});
