Component({
    externalClasses: ['i-class'],
    properties : {
        status : {
            type : String,
            //wait、process、finish、error
            value : ''
        },
        title : {
            type : String,
            value : ''
        },
        content : {
            type : String,
            value : ''
        },
        icon : {
            type : String,
            value : ''
        }
    },
    options: {
        multipleSlots: true
    },
    relations : {
        '../steps/index' : {
            type : 'parent'
        }
    },
    data : {
        //step length
        len : 1,
        //current in step index
        index : 0,
        //parent component select current index
        current : 0,
        //css direction
        direction : 'horizontal'
    },
    methods : {
        updateDataChange( options ){
            this.setData({
                len : options.len,
                index : options.index,
                current : options.current,
                direction : options.direction
            })
        }
    }

})