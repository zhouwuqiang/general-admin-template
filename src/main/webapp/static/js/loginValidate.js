function userLoginFormValidate() {
    $('#user_login_form').formValidation({
        err: {
            container: 'tooltip'
        },
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        message: '字段输入不符合要求!',
        live: 'enabled',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                group: ".validate-group",
                message: '用户名不符合要求!',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空!'
                    }
                }
            },
            password: {
                group: ".validate-group",
                message: '密码不符合要求!',
                validators: {
                    notEmpty: {
                        message: '密码不能为空!'
                    }
                }
            }
        }
    });

    // $('#user_login_form').formValidation({
    //     err: {
    //         container: 'tooltip'
    //     },
    //     //        trigger: 'blur',
    //     icon: {
    //         valid: 'glyphicon glyphicon-ok',
    //         invalid: 'glyphicon glyphicon-remove',
    //         validating: 'glyphicon glyphicon-refresh'
    //     },
    //     fields: {
    //         username: {
    //             validators: {
    //                 stringLength: {
    //                     enabled: false,
    //                     min: 4,
    //                     message: 'The first name must be more than 5 characters'
    //                 },
    //                 notEmpty: {
    //                     message: 'The first name is required'
    //                 },
    //                 regexp: {
    //                     enabled: true,
    //                     regexp: /^[a-z]+$/i,
    //                     message: 'The first name must consist of a-z, A-Z characters only'
    //                 }
    //             }
    //         },
    //         password: {
    //             validators: {
    //                 stringLength: {
    //                     min: 4,
    //                     message: 'The last name must be more than 5 characters'
    //                 },
    //                 notEmpty: {
    //                     message: 'The last name is required'
    //                 },
    //                 regexp: {
    //                     regexp: /^[a-z]+$/i,
    //                     message: 'The last name must consist of a-z, A-Z characters only'
    //                 }
    //             }
    //         }
    //     }
    // });
}


